package services;

import java.util.Collection;

import domain.Recipe;
import domain.Review;

public interface RecipeService {
	
	Collection<Recipe> getAllRecipes(String name, String author, String allRecipeCategories, String hasImage,
			String totalTime, String prepTime, String cookTime, String allIngredients, String minAggregateRatingValue, 
			String minCarbs, String maxCarbs, String minSatFat, String maxSatFat, String minCholesterol, String maxCholesterol, 
			String minSodium, String maxSodium, String minFat, String maxFat, String minFiber, String maxFiber, 
			String minSugar, String maxSugar, String minProtein, String maxProtein, String minCalories, String maxCalories);

	Recipe getRecipe(String id);
	
	Collection<Review> getReviewsForRecipe(String id);
	
}
