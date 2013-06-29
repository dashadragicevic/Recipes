package persistance.dataprovider;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;

public interface DataProvider {
	
	public Model getDataModel();
	
	public void close();
	
	public Dataset getDataset();

}
