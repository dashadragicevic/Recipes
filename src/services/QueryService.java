package services;

import java.util.ArrayList;
import java.util.Collection;

import domain.Recipe;

import persistance.DataModelManager;
import persistance.QueryExecutor;
import util.Constants;

public class QueryService {

	private QueryExecutor queryExecutor = new QueryExecutor();

	public Collection<Recipe> getAllRecipes(String name, String author, String allRecipeCategories, String hasImage,
			String totalTime, String prepTime, String cookTime, String allIngredients, String minAggregateRatingValue, 
			String minCarbs, String maxCarbs, String minSatFat, String maxSatFat, String minCholesterol, String maxCholesterol, 
			String minSodium, String maxSodium, String minFat, String maxFat, String minFiber, String maxFiber, 
			String minSugar, String maxSugar, String minProtein, String maxProtein, String minCalories, String maxCalories) {

		Collection<Recipe> recipes = new ArrayList<>();

		String where = " ?recipe a schema:Recipe. ";
		String filter = "";

		if (!name.isEmpty()) {
			where += "?recipe schema:name ?name. ";
			filter += "FILTER regex( ?name, \"" + name + "\", \"i\" ) ";
		}
		if (!author.isEmpty()) {
			where += "?recipe schema:author ?author. ";
			filter += "FILTER regex( ?author, \"" + author + "\", \"i\" ) ";
		}
		if (!allRecipeCategories.isEmpty()) {
			String[] categories = allRecipeCategories.split(",");
			for (String string : categories) {
				string = string.trim();
				where += "?recipe schema:recipeCategory ?" + string + ". ";
				filter += "FILTER regex( ?" + string + ", \"" + string	+ "\", \"i\" ) ";
			}
		}
		if (!hasImage.isEmpty()) {
			where += "?recipe schema:image ?image. ";
		}
		if (!totalTime.isEmpty()) {
			where += "?recipe schema:totalTime ?totalTime. ";
			if (!totalTime.equalsIgnoreCase("all")) {
				String totalTimeFilter = "FILTER ( ";
				if (totalTime.equals("15min and less")) {
					for (int i = 0; i <= 15; i++) {
						if (i < 15)
							totalTimeFilter += "regex( ?totalTime, \"PT" + i + "M\" ) || ";
						else if (i == 15)
							totalTimeFilter += "regex( ?totalTime, \"PT" + i + "M\" ) ) ";
					}
				}
				if (totalTime.equals("30min and less")) {
					for (int i = 0; i <= 30; i++) {
						if (i < 30)
							totalTimeFilter += "regex( ?totalTime, \"PT" + i + "M\" ) || ";
						else if (i == 30)
							totalTimeFilter += "regex( ?totalTime, \"PT" + i + "M\" ) ) ";
					}
				}
				if (totalTime.equals("1h and less")) {
					for (int i = 0; i <= 60; i++) {
						if (i < 60)
							totalTimeFilter += "regex( ?totalTime, \"PT" + i + "M\" ) || ";
						else if (i == 60)
							totalTimeFilter += "regex( ?totalTime, \"PT1H\" ) ) ";
					}
				}
				if (totalTime.equals("2h and less")) {
					for (int i = 0; i <= 120; i++) {
						if (i < 60)
							totalTimeFilter += "regex( ?totalTime, \"PT" + i + "M\" ) || ";
						else if (i == 60)
							totalTimeFilter += "regex( ?totalTime, \"PT1H\" ) || ";
						else if (i > 60 && i < 120)
							totalTimeFilter += "regex( ?totalTime, \"PT1H" + (i - 60) + "M\" ) || ";
						else if (i == 120)
							totalTimeFilter += "regex( ?totalTime, \"PT2H\" ) ) ";
					}
				}
				if (totalTime.equals("3h and less")) {
					for (int i = 0; i <= 180; i++) {
						if (i < 60)
							totalTimeFilter += "regex( ?totalTime, \"PT" + i + "M\" ) || ";
						else if (i == 60)
							totalTimeFilter += "regex( ?totalTime, \"PT1H\" ) || ";
						else if (i > 60 && i < 120)
							totalTimeFilter += "regex( ?totalTime, \"PT1H" + (i - 60) + "M\" ) || ";
						else if (i == 120)
							totalTimeFilter += "regex( ?totalTime, \"PT2H\" ) || ";
						else if (i > 120 && i < 180)
							totalTimeFilter += "regex( ?totalTime, \"PT2H" + (i - 120) + "M\" ) || ";
						else if (i == 180)
							totalTimeFilter += "regex( ?totalTime, \"PT3H\" ) ) ";
					}
				}
				if (totalTime.equals("4h and less")) {
					for (int i = 0; i <= 240; i++) {
						if (i < 60)
							totalTimeFilter += "regex( ?totalTime, \"PT" + i + "M\" ) || ";
						else if (i == 60)
							totalTimeFilter += "regex( ?totalTime, \"PT1H\" ) || ";
						else if (i > 60 && i < 120)
							totalTimeFilter += "regex( ?totalTime, \"PT1H" + (i - 60) + "M\" ) || ";
						else if (i == 120)
							totalTimeFilter += "regex( ?totalTime, \"PT2H\" ) || ";
						else if (i > 120 && i < 180)
							totalTimeFilter += "regex( ?totalTime, \"PT2H" + (i - 120) + "M\" ) || ";
						else if (i == 180)
							totalTimeFilter += "regex( ?totalTime, \"PT3H\" ) || ";
						else if (i > 180 && i < 240)
							totalTimeFilter += "regex( ?totalTime, \"PT3H" + (i - 180) + "M\" ) || ";
						else if (i == 240)
							totalTimeFilter += "regex( ?totalTime, \"PT4H\" ) ) ";
					}
				}
				filter += totalTimeFilter;
			}
		}
		if (!prepTime.isEmpty()) {
			where += "?recipe schema:prepTime ?prepTime. ";
			if (!prepTime.equalsIgnoreCase("all")) {
				String totalTimeFilter = "FILTER ( ";
				if (prepTime.equals("15min and less")) {
					for (int i = 0; i <= 15; i++) {
						if (i < 15)
							totalTimeFilter += "regex( ?prepTime, \"PT" + i + "M\" ) || ";
						else if (i == 15)
							totalTimeFilter += "regex( ?prepTime, \"PT" + i + "M\" ) ) ";
					}
				}
				if (prepTime.equals("30min and less")) {
					for (int i = 0; i <= 30; i++) {
						if (i < 30)
							totalTimeFilter += "regex( ?prepTime, \"PT" + i + "M\" ) || ";
						else if (i == 30)
							totalTimeFilter += "regex( ?prepTime, \"PT" + i + "M\" ) ) ";
					}
				}
				if (prepTime.equals("1h and less")) {
					for (int i = 0; i <= 60; i++) {
						if (i < 60)
							totalTimeFilter += "regex( ?prepTime, \"PT" + i + "M\" ) || ";
						else if (i == 60)
							totalTimeFilter += "regex( ?prepTime, \"PT1H\" ) ) ";
					}
				}
				if (prepTime.equals("2h and less")) {
					for (int i = 0; i <= 120; i++) {
						if (i < 60)
							totalTimeFilter += "regex( ?prepTime, \"PT" + i + "M\" ) || ";
						else if (i == 60)
							totalTimeFilter += "regex( ?prepTime, \"PT1H\" ) || ";
						else if (i > 60 && i < 120)
							totalTimeFilter += "regex( ?prepTime, \"PT1H" + (i - 60) + "M\" ) || ";
						else if (i == 120)
							totalTimeFilter += "regex( ?prepTime, \"PT2H\" ) ) ";
					}
				}
				if (prepTime.equals("3h and less")) {
					for (int i = 0; i <= 180; i++) {
						if (i < 60)
							totalTimeFilter += "regex( ?prepTime, \"PT" + i + "M\" ) || ";
						else if (i == 60)
							totalTimeFilter += "regex( ?prepTime, \"PT1H\" ) || ";
						else if (i > 60 && i < 120)
							totalTimeFilter += "regex( ?prepTime, \"PT1H" + (i - 60) + "M\" ) || ";
						else if (i == 120)
							totalTimeFilter += "regex( ?prepTime, \"PT2H\" ) || ";
						else if (i > 120 && i < 180)
							totalTimeFilter += "regex( ?prepTime, \"PT2H" + (i - 120) + "M\" ) || ";
						else if (i == 180)
							totalTimeFilter += "regex( ?prepTime, \"PT3H\" ) ) ";
					}
				}
				if (prepTime.equals("4h and less")) {
					for (int i = 0; i <= 240; i++) {
						if (i < 60)
							totalTimeFilter += "regex( ?prepTime, \"PT" + i + "M\" ) || ";
						else if (i == 60)
							totalTimeFilter += "regex( ?prepTime, \"PT1H\" ) || ";
						else if (i > 60 && i < 120)
							totalTimeFilter += "regex( ?prepTime, \"PT1H" + (i - 60) + "M\" ) || ";
						else if (i == 120)
							totalTimeFilter += "regex( ?prepTime, \"PT2H\" ) || ";
						else if (i > 120 && i < 180)
							totalTimeFilter += "regex( ?prepTime, \"PT2H" + (i - 120) + "M\" ) || ";
						else if (i == 180)
							totalTimeFilter += "regex( ?prepTime, \"PT3H\" ) || ";
						else if (i > 180 && i < 240)
							totalTimeFilter += "regex( ?prepTime, \"PT3H" + (i - 180) + "M\" ) || ";
						else if (i == 240)
							totalTimeFilter += "regex( ?prepTime, \"PT4H\" ) ) ";
					}
				}
				filter += totalTimeFilter;
			}
		}
		if (!cookTime.isEmpty()) {
			where += "?recipe schema:cookTime ?cookTime. ";
			if (!cookTime.equalsIgnoreCase("all")) {
				String totalTimeFilter = "FILTER ( ";
				if (cookTime.equals("15min and less")) {
					for (int i = 0; i <= 15; i++) {
						if (i < 15)
							totalTimeFilter += "regex( ?cookTime, \"PT" + i + "M\" ) || ";
						else if (i == 15)
							totalTimeFilter += "regex( ?cookTime, \"PT" + i + "M\" ) ) ";
					}
				}
				if (cookTime.equals("30min and less")) {
					for (int i = 0; i <= 30; i++) {
						if (i < 30)
							totalTimeFilter += "regex( ?cookTime, \"PT" + i + "M\" ) || ";
						else if (i == 30)
							totalTimeFilter += "regex( ?cookTime, \"PT" + i + "M\" ) ) ";
					}
				}
				if (cookTime.equals("1h and less")) {
					for (int i = 0; i <= 60; i++) {
						if (i < 60)
							totalTimeFilter += "regex( ?cookTime, \"PT" + i + "M\" ) || ";
						else if (i == 60)
							totalTimeFilter += "regex( ?cookTime, \"PT1H\" ) ) ";
					}
				}
				if (cookTime.equals("2h and less")) {
					for (int i = 0; i <= 120; i++) {
						if (i < 60)
							totalTimeFilter += "regex( ?cookTime, \"PT" + i + "M\" ) || ";
						else if (i == 60)
							totalTimeFilter += "regex( ?cookTime, \"PT1H\" ) || ";
						else if (i > 60 && i < 120)
							totalTimeFilter += "regex( ?cookTime, \"PT1H" + (i - 60) + "M\" ) || ";
						else if (i == 120)
							totalTimeFilter += "regex( ?cookTime, \"PT2H\" ) ) ";
					}
				}
				if (cookTime.equals("3h and less")) {
					for (int i = 0; i <= 180; i++) {
						if (i < 60)
							totalTimeFilter += "regex( ?cookTime, \"PT" + i + "M\" ) || ";
						else if (i == 60)
							totalTimeFilter += "regex( ?cookTime, \"PT1H\" ) || ";
						else if (i > 60 && i < 120)
							totalTimeFilter += "regex( ?cookTime, \"PT1H" + (i - 60) + "M\" ) || ";
						else if (i == 120)
							totalTimeFilter += "regex( ?cookTime, \"PT2H\" ) || ";
						else if (i > 120 && i < 180)
							totalTimeFilter += "regex( ?cookTime, \"PT2H" + (i - 120) + "M\" ) || ";
						else if (i == 180)
							totalTimeFilter += "regex( ?cookTime, \"PT3H\" ) ) ";
					}
				}
				if (cookTime.equals("4h and less")) {
					for (int i = 0; i <= 240; i++) {
						if (i < 60)
							totalTimeFilter += "regex( ?cookTime, \"PT" + i + "M\" ) || ";
						else if (i == 60)
							totalTimeFilter += "regex( ?cookTime, \"PT1H\" ) || ";
						else if (i > 60 && i < 120)
							totalTimeFilter += "regex( ?cookTime, \"PT1H" + (i - 60) + "M\" ) || ";
						else if (i == 120)
							totalTimeFilter += "regex( ?cookTime, \"PT2H\" ) || ";
						else if (i > 120 && i < 180)
							totalTimeFilter += "regex( ?cookTime, \"PT2H" + (i - 120) + "M\" ) || ";
						else if (i == 180)
							totalTimeFilter += "regex( ?cookTime, \"PT3H\" ) || ";
						else if (i > 180 && i < 240)
							totalTimeFilter += "regex( ?cookTime, \"PT3H" + (i - 180) + "M\" ) || ";
						else if (i == 240)
							totalTimeFilter += "regex( ?cookTime, \"PT4H\" ) ) ";
					}
				}
				filter += totalTimeFilter;
			}
		}
		if (!allIngredients.isEmpty()) {
			String[] ingredients = allIngredients.split(",");
			for (String string : ingredients) {
				string = string.trim();
				where += "?recipe schema:ingredients ?" + string + ". ";
				filter += "FILTER regex( ?" + string + ", \"" + string + "\", \"i\" ) ";
			}
		}
		if (!minAggregateRatingValue.isEmpty()) {
			where += "?recipe schema:aggregateRating ?aggregateRating. "
					+ "?aggregateRating schema:ratingValue ?ratingValue. ";
			filter += "FILTER ( ?ratingValue >=" + minAggregateRatingValue + " ) ";
		}
		if (!minCarbs.isEmpty() || !maxCarbs.isEmpty() || !minSatFat.isEmpty() || !maxSatFat.isEmpty() || 
				!minCholesterol.isEmpty() || !maxCholesterol.isEmpty() || !minSodium.isEmpty() || !maxSodium.isEmpty() || 
				!minFat.isEmpty() || !maxFat.isEmpty() || !minFiber.isEmpty() || !maxFiber.isEmpty() || 
				!minSugar.isEmpty() || !maxSugar.isEmpty() || !minProtein.isEmpty() || !maxProtein.isEmpty() || 
				!minCalories.isEmpty() || !maxCalories.isEmpty()) {
			where += "?recipe schema:nutrition ?nutritionInformation. ";
			if (!minCarbs.isEmpty() ||!maxCarbs.isEmpty()) {
				where += "?nutritionInformation schema:carbohydrateContent ?massCarbohydrate. "
						+ "?massCarbohydrate schema:number ?numberCarbohydrate. ";
				if(!minCarbs.isEmpty())
					filter += "FILTER ( ?numberCarbohydrate >=" + minCarbs + " ) ";
				if(!maxCarbs.isEmpty())
					filter += "FILTER ( ?numberCarbohydrate <=" + maxCarbs + " ) ";
			}
			if (!minSatFat.isEmpty() || !maxSatFat.isEmpty()) {
				where += "?nutritionInformation schema:saturatedFatContent ?massSaturatedFat. "
						+ "?massSaturatedFat schema:number ?numberSaturatedFat. ";
				if(!minSatFat.isEmpty())
					filter += "FILTER ( ?numberSaturatedFat >=" + minSatFat + " ) ";
				if(!maxSatFat.isEmpty())
					filter += "FILTER ( ?numberSaturatedFat <=" + maxSatFat + " ) ";
			}
			if (!minCholesterol.isEmpty() || !maxCholesterol.isEmpty()) {
				where += "?nutritionInformation schema:cholesterolContent ?massCholesterol. "
						+ "?massCholesterol schema:number ?numberCholesterol. ";
				if(!minCholesterol.isEmpty())
					filter += "FILTER ( ?numberCholesterol >=" + minCholesterol + " ) ";
				if(!maxCholesterol.isEmpty())
					filter += "FILTER ( ?numberCholesterol <=" + maxCholesterol + " ) ";
			}
			if (!minSodium.isEmpty() || !maxSodium.isEmpty()) {
				where += "?nutritionInformation schema:sodiumContent ?massSoduim. "
						+ "?massSoduim schema:number ?numberSoduim. ";
				if(!minSodium.isEmpty())
					filter += "FILTER ( ?numberSoduim >=" + minSodium + " ) ";
				if(!maxSodium.isEmpty())
					filter += "FILTER ( ?numberSoduim <=" + maxSodium + " ) ";
			}
			if (!minFat.isEmpty() || !maxFat.isEmpty()) {
				where += "?nutritionInformation schema:fatContent ?massFat. "
						+ "?massFat schema:number ?numberFat. ";
				if(!minFat.isEmpty())
					filter += "FILTER ( ?numberFat >=" + minFat + " ) ";
				if(!maxFat.isEmpty())
					filter += "FILTER ( ?numberFat <=" + maxFat + " ) ";
			}
			if (!minFiber.isEmpty() || !maxFiber.isEmpty()) {
				where += "?nutritionInformation schema:fiberContent ?massFiber. "
						+ "?massFiber schema:number ?numberFiber. ";
				if(!minFiber.isEmpty())
					filter += "FILTER ( ?numberFiber >=" + minFiber + " ) ";
				if(!maxFiber.isEmpty())
					filter += "FILTER ( ?numberFiber <=" + maxFiber + " ) ";
			}
			if (!minSugar.isEmpty() || !maxSugar.isEmpty()) {
				where += "?nutritionInformation schema:sugarContent ?massSugar. "
						+ "?massSugar schema:number ?numberSugar. ";
				if(!minSugar.isEmpty())
					filter += "FILTER ( ?numberSugar >=" + minSugar + " ) ";
				if(!maxSugar.isEmpty())
					filter += "FILTER ( ?numberSugar <=" + maxSugar + " ) ";
			}
			if (!minProtein.isEmpty() || !maxProtein.isEmpty()) {
				where += "?nutritionInformation schema:proteinContent ?massProtein. "
						+ "?massProtein schema:number ?numberProtein. ";
				if(!minProtein.isEmpty())
					filter += "FILTER ( ?numberProtein >=" + minProtein + " ) ";
				if(!maxProtein.isEmpty())
					filter += "FILTER ( ?numberProtein <=" + maxProtein + " ) ";
			}
			if (!minCalories.isEmpty() || !maxCalories.isEmpty()) {
				where += "?nutritionInformation schema:calories ?energyCalories. "
						+ "?energyCalories schema:number ?numberCalories. ";
				if(!minCalories.isEmpty())
					filter += "FILTER ( ?numberCalories >=" + minCalories + " ) ";
				if(!maxCalories.isEmpty())
					filter += "FILTER ( ?numberCalories <=" + maxCalories + " ) ";
			}
		}

		String query = "PREFIX recipes: <" + Constants.NS + "> " + 
					   "PREFIX schema: <" + Constants.SCHEMA + "> " + 
					   "PREFIX xsd: <" + Constants.XSD + "> " + 
					   "SELECT ?recipe " + 
					   "WHERE { " + where + filter + " } ";

		Collection<String> recipeUris = queryExecutor.executeOneVariableSelectSparqlQuery(query, "recipe", DataModelManager.getInstance().getModel());

		for (String string : recipeUris) {
			Recipe recipe = getRecipe(string);
			recipes.add(recipe);
		}

		return recipes;
	}
	
	public Recipe getRecipe(String uri){
		Recipe recipe = queryExecutor.getRecipe(uri);
		return recipe;
	}

}
