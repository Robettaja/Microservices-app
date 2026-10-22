using System.ComponentModel.DataAnnotations;

namespace urlshortener.Models;

public class AddUrlRequest
{
  [Required]
  public string Url { get; set; } = string.Empty;
}

public record AddUrlResponse(int Id, string InputUrl, string ShortId, string ShortUrl);
