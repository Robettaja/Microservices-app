using System.Security.Cryptography;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using urlshortener.Data;
using urlshortener.Models;

namespace urlshortener.Controllers;

[ApiController]
public class UrlController : ControllerBase
{
  private const string Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  private const int ShortIdLength = 7;
  private const int MaxAttempts = 5;
  private readonly Context _db;
  public UrlController(Context db)
  {
    _db = db;
  }
  [HttpPost("url")]
  public async Task<IActionResult> UrlAdd([FromBody] AddUrlRequest request)
  {
    if (!Uri.TryCreate(request.Url, UriKind.Absolute, out var uri) ||
        (uri.Scheme != Uri.UriSchemeHttp && uri.Scheme != Uri.UriSchemeHttps))
    {
      return BadRequest("Url must be a valid absolute http or https URL.");
    }

    var existing = await _db.ShortenedUrl
      .FirstOrDefaultAsync(u => u.InputUrl == uri.AbsoluteUri);
    if (existing is not null)
    {
      return Ok(ToResponse(existing));
    }

    for (var attempt = 0; attempt < MaxAttempts; attempt++)
    {
      var entity = new ShortenedUrl
      {
        InputUrl = uri.AbsoluteUri,
        ShortId = GenerateShortId()
      };

      _db.ShortenedUrl.Add(entity);
      try
      {
        await _db.SaveChangesAsync();
        return Ok(ToResponse(entity));
      }
      catch (DbUpdateException)
      {
        // Most likely a ShortId collision (unique index). Detach and retry.
        _db.Entry(entity).State = EntityState.Detached;
      }
    }

    return StatusCode(500, "Could not generate a unique short ID. Please try again.");
  }
  [HttpGet("url")]
  public async Task<IActionResult> UrlGet([FromQuery] string? shortId)
  {
    if (string.IsNullOrWhiteSpace(shortId))
    {
      var urls = await _db.ShortenedUrl.ToListAsync();
      return Ok(urls);
    }

    var url = await _db.ShortenedUrl
        .FirstOrDefaultAsync(x => x.ShortId == shortId);

    if (url is null)
      return NotFound();

    return Ok(url);
  }

  [HttpDelete("url")]
  public async Task<IActionResult> UrlDelete([FromQuery] string shortId)
  {
    if (string.IsNullOrWhiteSpace(shortId))
      return BadRequest("shortId is required.");

    var url = await _db.ShortenedUrl
        .FirstOrDefaultAsync(x => x.ShortId == shortId);

    if (url is null)
      return NotFound();

    _db.ShortenedUrl.Remove(url);
    await _db.SaveChangesAsync();

    return NoContent();
  }
  private AddUrlResponse ToResponse(ShortenedUrl e) =>
      new(e.Id, e.InputUrl, e.ShortId, $"{Request.Scheme}://{Request.Host}/{e.ShortId}");

  private static string GenerateShortId()
  {
    Span<char> chars = stackalloc char[ShortIdLength];
    for (var i = 0; i < chars.Length; i++)
    {
      chars[i] = Alphabet[RandomNumberGenerator.GetInt32(Alphabet.Length)];
    }
    return new string(chars);
  }

}
