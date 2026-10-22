namespace urlshortener.Models;

public class ShortenedUrl
{
  public int Id { get; set; }
  public required string InputUrl { get; set; }
  public required string ShortId { get; set; }

}
