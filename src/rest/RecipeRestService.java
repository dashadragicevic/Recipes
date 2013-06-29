package rest;

import java.util.Collection;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import persistance.DataModelManager;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hp.hpl.jena.tdb.TDB;

import domain.Recipe;
import domain.Review;

import rest.parsers.RecipeJSONParser;
import services.RecipeServiceImpl;

@Path("/recipes")
public class RecipeRestService {
	
	private RecipeServiceImpl recipeRepository;
	
	public RecipeRestService(){
		recipeRepository = new RecipeServiceImpl();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllRecipes(@DefaultValue("") @QueryParam("name") String name, @DefaultValue("") @QueryParam("author") String author, 
			@DefaultValue("") @QueryParam("recipeCategory") String allRecipeCategories, @DefaultValue("") @QueryParam("hasImage") String hasImage,
			@DefaultValue("") @QueryParam("totalTime") String totalTime, @DefaultValue("") @QueryParam("prepTime") String prepTime, 
			@DefaultValue("") @QueryParam("cookTime") String cookTime, @DefaultValue("") @QueryParam("ingredients") String allIngredients, 
			@DefaultValue("") @QueryParam("minAggregateRatingValue") String minAggregateRatingValue, 
			@DefaultValue("") @QueryParam("minCarbs") String minCarbs, @DefaultValue("") @QueryParam("maxCarbs") String maxCarbs, 
			@DefaultValue("") @QueryParam("minSatFat") String minSatFat, @DefaultValue("") @QueryParam("maxSatFat") String maxSatFat, 
			@DefaultValue("") @QueryParam("minCholesterol") String minCholesterol, @DefaultValue("") @QueryParam("maxCholesterol") String maxCholesterol, 
			@DefaultValue("") @QueryParam("minSodium") String minSodium, @DefaultValue("") @QueryParam("maxSodium") String maxSodium, 
			@DefaultValue("") @QueryParam("minFat") String minFat, @DefaultValue("") @QueryParam("maxFat") String maxFat, 
			@DefaultValue("") @QueryParam("minFiber") String minFiber, @DefaultValue("") @QueryParam("maxFiber") String maxFiber, 
			@DefaultValue("") @QueryParam("minSugar") String minSugar, @DefaultValue("") @QueryParam("maxSugar") String maxSugar, 
			@DefaultValue("") @QueryParam("minProtein") String minProtein, @DefaultValue("") @QueryParam("maxProtein") String maxProtein, 
			@DefaultValue("") @QueryParam("minCalories") String minCalories, @DefaultValue("") @QueryParam("maxCalories") String maxCalories){
		
		Collection<Recipe> recipes = recipeRepository.getAllRecipes(name, author, allRecipeCategories, hasImage, totalTime, prepTime, cookTime, 
				allIngredients, minAggregateRatingValue, minCarbs, maxCarbs, minSatFat, maxSatFat, minCholesterol, maxCholesterol, minSodium, maxSodium, 
				minFat, maxFat, minFiber, maxFiber, minSugar, maxSugar, minProtein, maxProtein, minCalories, maxCalories);
		
		if (recipes != null && !recipes.isEmpty()) {
			JsonArray recipeArray = new JsonArray();
			
			for (Recipe r : recipes) {
				JsonObject recipeJson = RecipeJSONParser.serialize(r);
				recipeArray.add(recipeJson);
			}
			TDB.sync(DataModelManager.getInstance().getModel());
			
			return recipeArray.toString();
		}
		throw new WebApplicationException(Response.Status.NO_CONTENT);
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRecipe(@PathParam("id") String id){
		Recipe r = recipeRepository.getRecipe(id);
		
		if(r!=null){
			JsonObject recipeJson = RecipeJSONParser.serialize(r);
			return recipeJson.toString();
		}
		
		throw new WebApplicationException(Response.Status.NO_CONTENT);
	}
	
	@GET
	@Path("{id}/reviews")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRecipeReveiws(@PathParam("id") String id){
		Collection<Review> reviews = recipeRepository.getReviewsForRecipe(id);
		
		if (reviews != null && !reviews.isEmpty()) {
			JsonObject reviewsJson = RecipeJSONParser.serializeReview(reviews, id);
			return reviewsJson.toString();
		}
		
		throw new WebApplicationException(Response.Status.NO_CONTENT);
	}

}
