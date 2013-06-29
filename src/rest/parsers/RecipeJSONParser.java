package rest.parsers;

import java.util.Collection;

import util.Constants;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import domain.AggregateRating;
import domain.Energy;
import domain.Mass;
import domain.NutritionInformation;
import domain.Rating;
import domain.Recipe;
import domain.Review;

public class RecipeJSONParser {
	
	public static JsonObject serialize(Recipe r){
		JsonObject recipeJson = new JsonObject();
		
		recipeJson.addProperty("uri", r.getUri().toString());
		recipeJson.addProperty("url", r.getUrl().toString());
		recipeJson.addProperty("name", r.getName());
		recipeJson.addProperty("author", r.getAuthor());
		recipeJson.addProperty("datePublished", r.getDatePublished().toString());
		JsonArray recipeCategories = new JsonArray();
		for (String category : r.getRecipeCategory()) {
			recipeCategories.add(new JsonPrimitive(category));
		}
		recipeJson.add("recipeCategory", recipeCategories);
		if(r.getImage()!=null)
			recipeJson.addProperty("image", r.getImage().toString());
		recipeJson.addProperty("totalTime", r.getTotalTime());
		recipeJson.addProperty("prepTime", r.getPrepTime());
		recipeJson.addProperty("cookTime", r.getCookTime());
		recipeJson.addProperty("description", r.getDescription());
		JsonArray recipeYields = new JsonArray();
		for (String yield : r.getRecipeYield()) {
			recipeYields.add(new JsonPrimitive(yield));
		}
		recipeJson.add("recipeYield", recipeYields);
		JsonArray recipeIngredients = new JsonArray();
		for (String ingredient : r.getIngredients()) {
			recipeIngredients.add(new JsonPrimitive(ingredient));
		}
		recipeJson.add("ingredients", recipeIngredients);
		recipeJson.addProperty("recipeInstructions", r.getRecipeInstructions());
		if(!r.getReviews().isEmpty())
			recipeJson.addProperty("reviews", r.getUri().toString()+"/reviews");
		recipeJson.add("aggregateRating", serializeAggregateRating(r.getAggregateRating()));
		recipeJson.add("nutrition", serializeNutritionInformation(r.getNutrition()));
		
		return recipeJson;
	}
	
	public static JsonObject serializeReview(Collection<Review> rev, String id){
		JsonObject reviewsJson = new JsonObject();
		
		reviewsJson.addProperty("uri", Constants.NS + id + "/reviews");
		reviewsJson.addProperty("recipe", Constants.NS + id);		
		JsonArray reviews = new JsonArray();
		for (Review r : rev) {
			JsonObject json = new JsonObject();			
			json.addProperty("about", r.getAbout());
			json.addProperty("author", r.getAuthor());
			json.addProperty("datePublished", r.getDatePublished().toString());
			json.addProperty("description", r.getDescription());
			if(r.getReviewRating()!=null)
				json.add("reviewRating", serializeReviewRating(r.getReviewRating()));
			reviews.add(json);
		}
		reviewsJson.add("reviews", reviews);
				
		return reviewsJson;
	}
	
	public static JsonObject serializeReviewRating(Rating r){
		JsonObject json = new JsonObject();
		
		json.addProperty("bestRating", r.getBestRating());
		json.addProperty("ratingValue", r.getRatingValue());
		json.addProperty("worstRating", r.getWorstRating());
				
		return json;
	}
	
	public static JsonObject serializeAggregateRating(AggregateRating ar){
		JsonObject json = new JsonObject();
		
		json.addProperty("ratingValue", ar.getRatingValue());
		json.addProperty("reviewCount", ar.getReviewCount());
				
		return json;
	}
	
	public static JsonObject serializeNutritionInformation(NutritionInformation ni){
		JsonObject json = new JsonObject();
		
		json.add("carbohydrateContent", serializeMass(ni.getCarbohydrateContent()));
		json.add("saturatedFatContent", serializeMass(ni.getSaturatedFatContent()));
		json.add("cholesterolContent", serializeMass(ni.getCholesterolContent()));
		json.add("sodiumContent", serializeMass(ni.getSodiumContent()));
		json.add("fatContent", serializeMass(ni.getFatContent()));
		json.add("fiberContent", serializeMass(ni.getFiberContent()));
		json.add("sugarContent", serializeMass(ni.getSugarContent()));
		json.add("proteinContent", serializeMass(ni.getProteinContent()));
		json.add("calories", serializeEnergy(ni.getCalories()));
		
		return json;
	}
	
	public static JsonObject serializeMass(Mass m){
		JsonObject json = new JsonObject();
		
		json.addProperty("number", m.getNumber());
		json.addProperty("unitOfMeasure", m.getUnitOfMeasure());
				
		return json;
	}
	
	public static JsonObject serializeEnergy(Energy e){
		JsonObject json = new JsonObject();
		
		json.addProperty("number", e.getNumber());
		json.addProperty("unitOfMeasure", e.getUnitOfMeasure());
				
		return json;
	}

}
