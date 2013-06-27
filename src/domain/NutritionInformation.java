package domain;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import util.Constants;

@Namespace(Constants.SCHEMA)
@RdfType("NutritionInformation")
public class NutritionInformation extends Thing {
	
	@RdfProperty(Constants.SCHEMA + "calories")
	private Energy calories;
	
	@RdfProperty(Constants.SCHEMA + "fatContent")
	private Mass fatContent;
	
	@RdfProperty(Constants.SCHEMA + "saturatedFatContent")
	private Mass saturatedFatContent;
	
	@RdfProperty(Constants.SCHEMA + "cholesterolContent")
	private Mass cholesterolContent;
	
	@RdfProperty(Constants.SCHEMA + "sodiumContent")
	private Mass sodiumContent;
	
	@RdfProperty(Constants.SCHEMA + "carbohydrateContent")
	private Mass carbohydrateContent;
	
	@RdfProperty(Constants.SCHEMA + "fiberContent")
	private Mass fiberContent;
	
	@RdfProperty(Constants.SCHEMA + "sugarContent")
	private Mass sugarContent;
	
	@RdfProperty(Constants.SCHEMA + "proteinContent")
	private Mass proteinContent;

	public Energy getCalories() {
		return calories;
	}

	public void setCalories(Energy calories) {
		this.calories = calories;
	}

	public Mass getFatContent() {
		return fatContent;
	}

	public void setFatContent(Mass fatContent) {
		this.fatContent = fatContent;
	}

	public Mass getSaturatedFatContent() {
		return saturatedFatContent;
	}

	public void setSaturatedFatContent(Mass saturatedFatContent) {
		this.saturatedFatContent = saturatedFatContent;
	}

	public Mass getCholesterolContent() {
		return cholesterolContent;
	}

	public void setCholesterolContent(Mass cholesterolContent) {
		this.cholesterolContent = cholesterolContent;
	}

	public Mass getSodiumContent() {
		return sodiumContent;
	}

	public void setSodiumContent(Mass sodiumContent) {
		this.sodiumContent = sodiumContent;
	}

	public Mass getCarbohydrateContent() {
		return carbohydrateContent;
	}

	public void setCarbohydrateContent(Mass carbohydrateContent) {
		this.carbohydrateContent = carbohydrateContent;
	}

	public Mass getFiberContent() {
		return fiberContent;
	}

	public void setFiberContent(Mass fiberContent) {
		this.fiberContent = fiberContent;
	}

	public Mass getSugarContent() {
		return sugarContent;
	}

	public void setSugarContent(Mass sugarContent) {
		this.sugarContent = sugarContent;
	}

	public Mass getProteinContent() {
		return proteinContent;
	}

	public void setProteinContent(Mass proteinContent) {
		this.proteinContent = proteinContent;
	}
	
}
