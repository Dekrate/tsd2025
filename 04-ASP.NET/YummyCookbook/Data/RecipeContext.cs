using Microsoft.EntityFrameworkCore;
using YummyCookbook.Models;

namespace YummyCookbook.Data
{
    public class RecipeContext : DbContext
    {
        public RecipeContext(DbContextOptions<RecipeContext> options)
            : base(options)
        {
        }

        public DbSet<Recipe> Recipes { get; set; }
    }
}