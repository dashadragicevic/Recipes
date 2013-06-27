package main;

import java.util.Collection;

import com.hp.hpl.jena.rdf.model.Model;

import domain.Recipe;

import parsing.Parser;
import persistance.DataModelManager;
import rest.RecipeRestService;
import services.QueryService;

public class Main {

	public static void main(String[] args) {
		try {
			/*
			Parser.parsePages("http://www.food.com/browse/allrecipes/?letter=123&pg=1");
			DataModelManager.getInstance().write("Recipes.rdf", "TURTLE");
			System.out.println("Done parsing.");
			*/
			
			//QueryService qs = new QueryService();
			//qs.getAllRecipes("", "", "", "", "15min and less", "", "15min and less", "", "", "", "", "", "", "", "", "", "", "",  "", "", "", "", "", "", "", "", "130");		
			//DataModelManager.getInstance().closeDataModel();
			
			//RecipeRestService rrs = new RecipeRestService();
			//rrs.getAllRecipes("", "", "", "", "15min and less", "", "15min and less", "", "", "", "", "", "", "", "", "", "", "",  "", "", "", "", "", "", "", "", "130");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
