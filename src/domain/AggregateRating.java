package domain;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import util.Constants;

@Namespace(Constants.SCHEMA)
@RdfType("AggregateRating")
public class AggregateRating extends Rating {

	@RdfProperty(Constants.SCHEMA + "reviewCount")
	private int reviewCount;

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

}
