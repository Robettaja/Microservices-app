using Microsoft.EntityFrameworkCore;
using urlshortener.Models;
namespace urlshortener.Data;

public class Context(DbContextOptions<Context> options)
    : DbContext(options)
{
  public DbSet<ShortenedUrl> ShortenedUrl { get; set; }
}
