using System.ComponentModel.DataAnnotations;

namespace YummyCookbook.Models
{
    public class Recipe
    {
        public int Id { get; set; }

        [Required]
        [Display(Name = "Recipe Name")]
        public string Name { get; set; }

        [Required]
        [Display(Name = "Cooking Time (minutes)")]
        [Range(1, int.MaxValue)]
        public int Time { get; set; }

        [Required]
        [Display(Name = "Difficulty Level")]
        public string Difficulty { get; set; }

        [Display(Name = "Likes")]
        [Range(0, int.MaxValue)]
        public int NumberOfLikes { get; set; } = 0;

        [Required]
        [Display(Name = "Ingredients")]
        public string Ingredients { get; set; }

        [Required]
        [Display(Name = "Preparation Steps")]
        public string Process { get; set; }

        [Display(Name = "Tips & Tricks")]
        public string TipsAndTricks { get; set; }
    }
}