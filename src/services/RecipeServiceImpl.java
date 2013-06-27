package services;

import java.util.Collection;

import domain.Recipe;

public class RecipeServiceImpl implements RecipeService {

		@Override
		public Collection<Recipe> getAllRecipes(String name, String author, String allRecipeCategories, String hasImage,
				String totalTime, String prepTime, String cookTime, String allIngredients, String minAggregateRatingValue, 
				String minCarbs, String maxCarbs, String minSatFat, String maxSatFat, String minCholesterol, String maxCholesterol, 
				String minSodium, String maxSodium, String minFat, String maxFat, String minFiber, String maxFiber, 
				String minSugar, String maxSugar, String minProtein, String maxProtein, String minCalories, String maxCalories) {

			QueryService queryService = new QueryService();
			Collection<Recipe> recipes = queryService.getAllRecipes(name, author, allRecipeCategories, hasImage,
					totalTime, prepTime, cookTime, allIngredients, minAggregateRatingValue, minCarbs, maxCarbs,
					minSatFat, maxSatFat, minCholesterol, maxCholesterol, minSodium, maxSodium, minFat, maxFat, 
					minFiber, maxFiber, minSugar, maxSugar, minProtein, maxProtein, minCalories, maxCalories);
			
			return recipes;		
			
		}
		
}
