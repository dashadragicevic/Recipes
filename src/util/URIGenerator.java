package util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import domain.Thing;
import util.Constants;

public class URIGenerator {
	
	public synchronized static <T extends Thing> URI generate(T resource) throws URISyntaxException{
		String uri = Constants.NS + UUID.randomUUID();
		return new URI(uri);
	}

}
