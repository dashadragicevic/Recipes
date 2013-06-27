package parsing;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import persistance.DataModelManager;
import util.URIGenerator;

import domain.AggregateRating;
import domain.Energy;
import domain.Mass;
import domain.NutritionInformation;
import domain.Rating;
import domain.Recipe;
import domain.Review;

public class Parser {
	
	private static Recipe getBasicRecipeInformation(Element recipe) throws Exception{
		Recipe rcp = new Recipe();
		
		String url = recipe.select("span[itemprop=url]").text();
		if(!url.isEmpty()){
			rcp.setUrl(new URI(url));
		}
		
				
		String name = recipe.select("h1[itemprop=name]").text();
		rcp.setName(name);
		
		Element recipeUser = recipe.select("div.recipe-user").first();
		
		String author = recipeUser.select("span[itemprop=author]").text();
		rcp.setAuthor(author);
		
		String datePublished = recipeUser.select("meta[itemprop=datePublished]").attr("content");
		rcp.setDatePublished(new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(datePublished));
		
		Elements recipeCategories = recipeUser.select("span[itemprop=recipeCategory]");
		Collection<String> recipeCategory = new LinkedList<String>();
		for (Element rc : recipeCategories) {
			String rcpCat = rc.text();
			recipeCategory.add(rcpCat);			
		}
		rcp.setRecipeCategory(recipeCategory);
		
		String image = recipe.select("img[itemprop=image]").attr("src");
		if(!image.isEmpty()){
			rcp.setImage(new URI(image));
		}		
		
		String totalTime = recipe.select("meta[itemprop=totalTime]").attr("content");
		rcp.setTotalTime(totalTime);
		
		String prepTime = recipe.select("meta[itemprop=prepTime]").attr("content");
		rcp.setPrepTime(prepTime);
		
		String cookTime = recipe.select("meta[itemprop=cookTime]").attr("content");
		rcp.setCookTime(cookTime);		

		String description = recipe.select("p[itemprop=description]").text();
		rcp.setDescription(description);
		
		String recipeYield1 = recipe.select("p[itemprop=recipeYield]").text();
		String recipeYield2 = recipe.select("span[itemprop=recipeYield]").text();
		Collection<String> recipeYield = new LinkedList<String>();
		recipeYield.add(recipeYield1);
		recipeYield.add(recipeYield2);
		rcp.setRecipeYield(recipeYield);
		
		Elements recipeIngredients = recipe.select("li[itemprop=ingredients]");
		Collection<String> ingredients = new LinkedList<String>();
		for (Element ri : recipeIngredients) {
			String ingredient = ri.select("span.value").text() + " " + ri.select("span.type").text() + " " + ri.select("span.name").text();
			ingredients.add(ingredient);
		}
		rcp.setIngredients(ingredients);
		
		Elements instructions = recipe.select("span[itemprop=recipeInstructions]").select("li");
		String recipeInstructions = "";
		for (Element i : instructions) {
			String instruction = i.select("div.num").text() + " " + i.select("div.txt").text() + " ";
			recipeInstructions = recipeInstructions + instruction;
		}
		rcp.setRecipeInstructions(recipeInstructions);
		
		return rcp;
	}
	
	private static AggregateRating parseAndSaveAggregateRating(Element aggregateRating) throws Exception{
		AggregateRating aggRat = new AggregateRating();
		
		String ratingValue = aggregateRating.select("span[itemprop=ratingValue]").text();
		aggRat.setRatingValue(Double.parseDouble(ratingValue));
		
		String reviewCount = aggregateRating.select("span[itemprop=reviewCount]").text();
		aggRat.setReviewCount(Integer.parseInt(reviewCount));
		
		aggRat.setUri(URIGenerator.generate(aggRat));
		
		DataModelManager.getInstance().save(aggRat);
		
		return aggRat;
	}
	
	private static Rating parseAndSaveRating(Element reviewRating) throws Exception{
		Rating rat = new Rating();
		
		String ratingBestRating = reviewRating.select("span[itemprop=bestRating]").text();
		rat.setBestRating(Integer.parseInt(ratingBestRating));
		
		String ratingRatingValue = reviewRating.select("span[itemprop=ratingValue]").text();
		rat.setRatingValue(Double.parseDouble(ratingRatingValue));
		
		String ratingWorstRating = reviewRating.select("meta[itemprop=worstRating]").attr("content");
		rat.setWorstRating(Integer.parseInt(ratingWorstRating));
		
		rat.setUri(URIGenerator.generate(rat));
		
		DataModelManager.getInstance().save(rat);
		
		return rat;
	}
	
	private static Collection<Review> parseAndSaveRecipeReviews(Elements recipeReviews) throws Exception{
		Collection<Review> reviews = new ArrayList<>();
		
		for (Element r : recipeReviews) {
			Review rev = new Review();
			
			String reviewAbout = r.select("meta[itemprop=about]").attr("content");
			rev.setAbout(reviewAbout);
			
			String reviewAuthor = r.select("span[itemprop=author]").text();
			rev.setAuthor(reviewAuthor);
			
			String reviewDatePublished = r.select("meta[itemprop=datePublished]").attr("content");
			rev.setDatePublished(new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(reviewDatePublished));
			
			String reviewDescription = r.select("span[itemprop=description]").text();
			rev.setDescription(reviewDescription);
			
			Element reviewRating = r.select("p[itemprop=reviewRating][itemscope][itemtype=http://schema.org/Rating]").first();
			if(reviewRating!=null){
				Rating rat = parseAndSaveRating(reviewRating);
				rev.setReviewRating(rat);
			}
			rev.setUri(URIGenerator.generate(rev));

			DataModelManager.getInstance().save(rev);
			
			reviews.add(rev);
		}
		
		return reviews;
	}
	
	private static Energy parseAndSaveEnergy(String num, String uof) throws Exception{
		Energy e = new Energy();
		
		e.setNumber(Double.parseDouble(num));
		e.setUnitOfMeasure(uof);

		e.setUri(URIGenerator.generate(e));

		DataModelManager.getInstance().save(e);
		
		return e;
	}
	
	private static Mass parseAndSaveMass(String num, String uof) throws Exception{
		Mass m = new Mass();
		
		m.setNumber(Double.parseDouble(num));
		m.setUnitOfMeasure(uof);
		
		m.setUri(URIGenerator.generate(m));

		DataModelManager.getInstance().save(m);
		
		return m;
	}
	
	private static NutritionInformation parseAndSaveNutritionInformation(Element recipeNutrition) throws Exception{
		NutritionInformation nutInf = new NutritionInformation();
		
		String calories = recipeNutrition.select("span[itemprop=calories]").text();
		Energy cal = parseAndSaveEnergy(calories, "cal");
		nutInf.setCalories(cal);
		
		String fatContent = recipeNutrition.select("span[itemprop=fatContent]").text();
		Mass m1 = parseAndSaveMass(fatContent, "g");
		nutInf.setFatContent(m1);
		
		String saturatedFatContent = recipeNutrition.select("span[itemprop=saturatedFatContent]").text();
		Mass m2 = parseAndSaveMass(saturatedFatContent, "g");
		nutInf.setSaturatedFatContent(m2);
		
		String cholesterolContent = recipeNutrition.select("span[itemprop=cholesterolContent]").text();
		Mass m3 = parseAndSaveMass(cholesterolContent, "mg");
		nutInf.setCholesterolContent(m3);
		
		String sodiumContent = recipeNutrition.select("span[itemprop=sodiumContent]").text();
		Mass m4 = parseAndSaveMass(sodiumContent, "mg");
		nutInf.setSodiumContent(m4);
		
		String carbohydrateContent = recipeNutrition.select("span[itemprop=carbohydrateContent]").text();
		Mass m5 = parseAndSaveMass(carbohydrateContent, "g");
		nutInf.setCarbohydrateContent(m5);
		
		String fiberContent = recipeNutrition.select("span[itemprop=fiberContent]").text();
		Mass m6 = parseAndSaveMass(fiberContent, "g");
		nutInf.setFiberContent(m6);
		
		String sugarContent = recipeNutrition.select("span[itemprop=sugarContent]").text();
		Mass m7 = parseAndSaveMass(sugarContent, "g");
		nutInf.setSugarContent(m7);
		
		String proteinContent = recipeNutrition.select("span[itemprop=proteinContent]").text();
		Mass m8 = parseAndSaveMass(proteinContent, "g");
		nutInf.setProteinContent(m8);
		
		nutInf.setUri(URIGenerator.generate(nutInf));

		DataModelManager.getInstance().save(nutInf);
		
		return nutInf;
	}
	
	public static void parseAndSave(String recipeUrl) throws Exception{
		Document doc = Jsoup.connect(recipeUrl).timeout(0).get();
		
		//podaci o receptu
		Element recipe = doc.select("div[itemscope][itemtype=http://schema.org/Recipe]").first();
		if(recipe!=null){
			Recipe rcp = getBasicRecipeInformation(recipe);
			
			//podaci o aggregate ratingu
			Element aggregateRating = recipe.select("div[itemprop=aggregateRating][itemscope][itemtype=http://schema.org/AggregateRating]").first();
			if(aggregateRating!=null){
				AggregateRating aggRat = parseAndSaveAggregateRating(aggregateRating);
				rcp.setAggregateRating(aggRat);		
			}
			
			//podaci o review-ima recepta
			Elements recipeReviews = recipe.select("li[itemprop=reviews][itemscope][itemtype=http://schema.org/Review]");
			if(recipeReviews!=null){
				Collection<Review> reviews = parseAndSaveRecipeReviews(recipeReviews);
				rcp.setReviews(reviews);
			}
			
			//podaci o nutritivnim informacijama recepta
			Element recipeNutrition = recipe.select("div[itemprop=nutrition][itemscope][itemtype=http://schema.org/NutritionInformation]").first();
			if(recipeNutrition!=null){
				NutritionInformation nutInf = parseAndSaveNutritionInformation(recipeNutrition);
				rcp.setNutrition(nutInf);
			}
			rcp.setUri(URIGenerator.generate(rcp));
	
			DataModelManager.getInstance().save(rcp);
		}
	}
	
	public static void parsePages(String allRecipesUrl) throws Exception {
		Document doc = Jsoup.connect(allRecipesUrl).timeout(0).get();
		
		Element div = doc.select("div.bd-full").first();
		Elements lists = div.select("ul.list");
		
		MyThreadPoolExecutor mtpe = new MyThreadPoolExecutor();
		
		for (Element list : lists) {
			Elements links = list.select("li");
			for (Element link : links) {
				String linkToParse = link.select("a[href]").attr("href");
				
				ParserWorker pw = new ParserWorker(linkToParse);
				mtpe.runTask(pw);
			}
		}
		
		mtpe.shutDown();
	}
}
