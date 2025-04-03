using Microsoft.EntityFrameworkCore;
using YummyCookbook.Data;
using YummyCookbook.Models;

var builder = WebApplication.CreateBuilder(args);

// Zmień konfigurację bazy danych na In-Memory
builder.Services.AddDbContext<RecipeContext>(options =>
    options.UseInMemoryDatabase("YummyCookbook"));

// Add services to the container.
builder.Services.AddControllersWithViews();

var app = builder.Build();
using (var scope = app.Services.CreateScope())
{
    var context = scope.ServiceProvider.GetRequiredService<RecipeContext>();
    
    if (!context.Recipes.Any())
    {
        context.Recipes.AddRange(
            new Recipe
            {
                Name = "Naleśniki",
                Time = 30,
                Difficulty = "Easy",
                NumberOfLikes = 150,
                Ingredients = "1 szkl. mąki\n2 jajka\n1 szkl. mleka\nszczypta soli",
                Process = "1. Wymieszaj składniki\n2. Smaż na rozgrzanej patelni\n3. Podawaj z ulubionym dodatkiem",
                TipsAndTricks = "Ciasto odstaw na 30 minut przed smażeniem"
            },
            new Recipe
            {
                Name = "Spaghetti Carbonara",
                Time = 45,
                Difficulty = "Medium",
                NumberOfLikes = 230,
                Ingredients = "400 g makaronu\n150 g pancetty\n2 jajka\n50 g parmezanu",
                Process = "1. Ugotuj makaron\n2. Usmaż pancettę\n3. Wymieszaj z sosem jajecznym",
                TipsAndTricks = "Używaj zawsze świeżo mielonego pieprzu"
            }
        );
        context.SaveChanges();
    }
}
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");

    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseRouting();

app.UseAuthorization();

app.MapStaticAssets();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}")
    .WithStaticAssets();


app.Run();
