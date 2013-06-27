package domain;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import util.Constants;

@Namespace(Constants.SCHEMA)
@RdfType("Rating")
public class Rating extends Thing {
	
	@RdfProperty(Constants.SCHEMA + "bestRating")
	private int bestRating;
	
	@RdfProperty(Constants.SCHEMA + "ratingValue")
	private double ratingValue;
	
	@RdfProperty(Constants.SCHEMA + "worstRating")
	private int worstRating;

	public int getBestRating() {
		return bestRating;
	}

	public void setBestRating(int bestRating) {
		this.bestRating = bestRating;
	}

	public double getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(double ratingValue) {
		this.ratingValue = ratingValue;
	}

	public int getWorstRating() {
		return worstRating;
	}

	public void setWorstRating(int worstRating) {
		this.worstRating = worstRating;
	}

}
