package domain;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import util.Constants;

@Namespace(Constants.SCHEMA)
@RdfType("Energy")
public class Energy extends Thing {
	
	@RdfProperty(Constants.SCHEMA + "number")
	private double number;
	
	@RdfProperty(Constants.SCHEMA + "unitOfMeasure")
	private String unitOfMeasure;

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

}
