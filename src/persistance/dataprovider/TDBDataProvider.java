package persistance.dataprovider;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;

public class TDBDataProvider implements DataProvider {
	
	private static final String dir = "C:\\Users\\Dasa\\workspace\\VezbaZaISTest\\Recipes\\tdb";
	private Dataset dataset;
	
	public TDBDataProvider() {
		dataset = TDBFactory.createDataset(dir);
	}

	@Override
	public Model getDataModel() {
		return dataset.getDefaultModel();
	}

	@Override
	public void close() {
		dataset.close();
	}

	@Override
	public Dataset getDataset() {
		return dataset;
	}
}
