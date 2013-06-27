package persistance;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

import domain.Recipe;

public class QueryExecutor {

	public Collection<String> executeOneVariableSelectSparqlQuery(String query,
			String variable, Model model) {

		List<String> results = new LinkedList<String>();

		Query q = QueryFactory.create(query);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(q, model);
		ResultSet resultSet = qe.execSelect();

		while (resultSet.hasNext()) {
			try {
				QuerySolution solution = resultSet.nextSolution();
				RDFNode value = solution.get(variable);
				
				if (value.isLiteral())
					results.add(((Literal) value).getLexicalForm());
				else
					results.add(((Resource) value).getURI());
			} catch (Exception e) {
				continue;
			}			
		}

		qe.close();

		return results;
	}

	public Model executeDescribeSparqlQuery(String queryString,
			Model model) {
		
		Query query = QueryFactory.create(queryString);
		
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		Model resultModel = qe.execDescribe();
		
		// Important - free up resources used running the query
		qe.close();
		
		return resultModel;
	}
	
	public Recipe getRecipe (String uri) {
		return (Recipe) DataModelManager.getInstance().load(uri);
	}

}
