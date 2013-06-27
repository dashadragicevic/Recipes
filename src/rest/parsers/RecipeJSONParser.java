package rest.parsers;

import java.util.Collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
		
		recipeJson.addProperty("url", r.getUrl().toString());
		recipeJson.addProperty("name", r.getName());
		recipeJson.addProperty("author", r.getAuthor());
		recipeJson.addProperty("datePublished", r.getDatePublished().toString());
		recipeJson.addProperty("recipeCategory", r.getRecipeCategory().toString());			
		if(r.getImage()!=null)
			recipeJson.addProperty("image", r.getImage().toString());
		recipeJson.addProperty("totalTime", r.getTotalTime());
		recipeJson.addProperty("prepTime", r.getPrepTime());
		recipeJson.addProperty("cookTime", r.getCookTime());
		recipeJson.addProperty("description", r.getDescription());
		recipeJson.addProperty("recipeYield", r.getRecipeYield().toString());			
		recipeJson.addProperty("ingredients", r.getIngredients().toString());
		recipeJson.addProperty("recipeInstructions", r.getRecipeInstructions());
		recipeJson.add("reviews", serializeReview(r.getReviews()));
		recipeJson.add("aggregateRating", serializeAggregateRating(r.getAggregateRating()));
		recipeJson.add("nutrition", serializeNutritionInformation(r.getNutrition()));
		
		return recipeJson;
	}
	
	public static JsonArray serializeReview(Collection<Review> rev){
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
				
		return reviews;
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
/*
	public static Recipe parse(String jsonString) {
		
		JsonObject recipeJson = (JsonObject) new JsonParser().parse(jsonString);
		
		Recipe r = new Recipe();
		
		if (recipeJson.get("id") != null) {
			String idString = recipeJson.get("id").getAsString();
			r.setId(Long.parseLong(idString));
		}
		
		String name = recipeJson.get("name").getAsString();
		r.setName(name);
		
		String price = recipeJson.get("price").getAsString();
		r.setPrice(Double.parseDouble(price));
		
		String currency = recipeJson.get("currency").getAsString();
		r.setCurrency(currency);
		
		return r;
	}
*/
}
