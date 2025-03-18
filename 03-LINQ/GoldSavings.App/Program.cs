using GoldSavings.App.Model;
using GoldSavings.App.Client;
using GoldSavings.App.Services;
using System.Xml.Serialization;
namespace GoldSavings.App;

class Program
{
    static void Main(string[] args)
    {
        Console.WriteLine("Hello, Gold Investor!");

        // Step 1: Get gold prices
        GoldDataService dataService = new GoldDataService();
        DateTime startDate = new DateTime(2024,09,18);
        DateTime endDate = DateTime.Now;
        List<GoldPrice> goldPrices = dataService.GetGoldPrices(startDate, endDate).GetAwaiter().GetResult();

        if (goldPrices.Count == 0)
        {
            Console.WriteLine("No data found. Exiting.");
            return;
        }

        Console.WriteLine($"Retrieved {goldPrices.Count} records. Ready for analysis.");

        // Step 2: Perform analysis
        GoldAnalysisService analysisService = new GoldAnalysisService(goldPrices);
        var avgPrice = analysisService.GetAveragePrice();

        // Step 3: Print results
        GoldResultPrinter.PrintSingleValue(Math.Round(avgPrice, 2), "Average Gold Price Last Half Year");

        Console.WriteLine("\nGold Analyis Queries with LINQ Completed.");
        // 2a
        var top3Highest = goldPrices.OrderByDescending(p => p.Price).Take(3);
        var top3Lowest = goldPrices.OrderBy(p => p.Price).Take(3);
        GoldResultPrinter.PrintPrices(top3Highest.ToList(), "Top 3 Highest Prices");
        GoldResultPrinter.PrintPrices(top3Lowest.ToList(), "Top 3 Lowest Prices");

        // 2b 
        var january2020Prices = goldPrices.Where(p => p.Date.Year == 2020 && p.Date.Month == 1);
        foreach (var price in january2020Prices)
        {
            var potentialProfitDays = goldPrices.Where(p => p.Price >= price.Price * 1.05);
            foreach (var day in potentialProfitDays)
            {
                Console.WriteLine($"Buying on {price.Date:yyyy-MM-dd} at {price.Price} PLN and selling on {day.Date:yyyy-MM-dd} at {day.Price} PLN gives more than 5% profit.");
            }
        }

        // 2c
        var rankedPrices = goldPrices.Where(p => p.Date.Year >= 2019 && p.Date.Year <= 2022)
                                     .OrderBy(p => p.Price)
                                     .Skip(10)
                                     .Take(3);
        GoldResultPrinter.PrintPrices(rankedPrices.ToList(), "Second Ten of Prices Ranking (2019-2022)");

        // 2d
        var avgYears = new Dictionary<int, double>();
        foreach (int year in new[] { 2020, 2023, 2024 })
        {
            var yearlyPrices = dataService.GetGoldPrices(new DateTime(year, 01, 01), new DateTime(year, 12, 31)).GetAwaiter().GetResult();
            if (yearlyPrices.Any())
            {
                avgYears[year] = yearlyPrices.Average(p => p.Price);
                GoldResultPrinter.PrintSingleValue(Math.Round(avgYears[year], 2), $"Average Gold Price in {year}");
            }
            else
            {
                Console.WriteLine($"No data available for {year}.");
            }
        }

        // 2e

        var allYears = new List<GoldPrice>();
        foreach (var year in avgYears.Keys)
        {
            var yearlyPrices = dataService.GetGoldPrices(new DateTime(year, 01, 01), new DateTime(year, 12, 31)).GetAwaiter().GetResult();
            allYears.AddRange(yearlyPrices);
        }

        var minPrice = allYears.Where(p => p.Date.Year >= 2020).OrderBy(p => p.Price).First();
        var maxPrice = allYears.Where(p => p.Date > minPrice.Date).OrderByDescending(p => p.Price).First();
        double roi = ((maxPrice.Price - minPrice.Price) / minPrice.Price) * 100;

        Console.WriteLine($"Best day to buy: {minPrice.Date:yyyy-MM-dd} at {minPrice.Price} PLN");
        Console.WriteLine($"Best day to sell: {maxPrice.Date:yyyy-MM-dd} at {maxPrice.Price} PLN");
        Console.WriteLine($"Return on investment: {Math.Round(roi, 2)}%\n");

        // xml test
        SaveToXml(goldPrices, "goldPrices.xml");
        var loadedPrices = LoadFromXml("goldPrices.xml");
    }

    public static void SaveToXml(List<GoldPrice> prices, string filePath)
    {
        XmlSerializer serializer = new XmlSerializer(typeof(List<GoldPrice>));
        using (StreamWriter writer = new StreamWriter(filePath))
        {
            serializer.Serialize(writer, prices);
        }
        Console.WriteLine($"Saved: {filePath}");
    }

    public static List<GoldPrice> LoadFromXml(string filePath) =>
        (List<GoldPrice>)new XmlSerializer(typeof(List<GoldPrice>)).Deserialize(new StreamReader(filePath))!;
}
