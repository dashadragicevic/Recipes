package domain;

import java.util.Collection;
import java.util.LinkedList;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import util.Constants;

@Namespace(Constants.SCHEMA)
@RdfType("Recipe")
public class Recipe extends CreativeWork {
	
	@RdfProperty(Constants.SCHEMA + "recipeCategory")
	private Collection<String> recipeCategory;
	
	@RdfProperty(Constants.SCHEMA + "totalTime")
	private String totalTime;
	
	@RdfProperty(Constants.SCHEMA + "prepTime")
	private String prepTime;
	
	@RdfProperty(Constants.SCHEMA + "cookTime")
	private String cookTime;
	
	@RdfProperty(Constants.SCHEMA + "recipeYield")
	private Collection<String> recipeYield;
	
	@RdfProperty(Constants.SCHEMA + "ingredients")
	private Collection<String> ingredients;
	
	@RdfProperty(Constants.SCHEMA + "recipeInstructions")
	private String recipeInstructions;
	
	@RdfProperty(Constants.SCHEMA + "nutrition")
	private NutritionInformation nutrition;
	
	

	public Recipe() {
		recipeCategory = new LinkedList<String>();
		recipeYield = new LinkedList<String>();
		ingredients = new LinkedList<String>();
	}

	public Collection<String> getRecipeCategory() {
		return recipeCategory;
	}

	public void setRecipeCategory(Collection<String> recipeCategory) {
		this.recipeCategory = recipeCategory;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(String prepTime) {
		this.prepTime = prepTime;
	}

	public String getCookTime() {
		return cookTime;
	}

	public void setCookTime(String cookTime) {
		this.cookTime = cookTime;
	}

	public Collection<String> getRecipeYield() {
		return recipeYield;
	}

	public void setRecipeYield(Collection<String> recipeYield) {
		this.recipeYield = recipeYield;
	}

	public Collection<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Collection<String> ingredients) {
		this.ingredients = ingredients;
	}

	public String getRecipeInstructions() {
		return recipeInstructions;
	}

	public void setRecipeInstructions(String recipeInstructions) {
		this.recipeInstructions = recipeInstructions;
	}

	public NutritionInformation getNutrition() {
		return nutrition;
	}

	public void setNutrition(NutritionInformation nutrition) {
		this.nutrition = nutrition;
	}
	
}
