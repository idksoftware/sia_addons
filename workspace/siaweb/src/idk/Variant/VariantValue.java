package idk.Variant;

import java.text.ParseException;
import java.util.Date;


public class VariantValue {
	
	
	protected VariantAttribute.AttribType attribType = null;
	protected boolean obligatory = false;
	protected String value = null;
	protected VariantAttribute imageAttribute = null;
	
	VariantValue(VariantAttribute imageAttribute, VariantAttribute.AttribType type, boolean obligatory) {
		this.attribType = type;
		this.obligatory = obligatory;
		this.imageAttribute = imageAttribute;
	}
	/**
	 * @return the imageAttribute
	 */
	public final VariantAttribute getAttribute() {
		return imageAttribute;
	}
	/**
	 * @return the attribType
	 */
	public final VariantAttribute.AttribType getAttribType() {
		return attribType;
	}
	/**
	 * @return the obligatory
	 */
	public final boolean isObligatory() {
		return obligatory;
	}
	/**
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}

	public void setString(String v) throws InvalidFormatException {
		if (v == null) {
			return;
		}
		value = v;
	}
	
	boolean getBooleanValue() throws InvalidFormatException {
		throw new InvalidFormatException(10, "Invalid type");
	}
	int getIntValue() throws InvalidFormatException {
		throw new InvalidFormatException(10, "Invalid type");
	}
	long getLongValue() throws InvalidFormatException {
		throw new InvalidFormatException(10, "Invalid type");
	}
	String getStringValue() throws InvalidFormatException {
		return value;
	}
	Date getDateValue() throws InvalidFormatException, ParseException {
		throw new InvalidFormatException(10, "Invalid type");
	}
	float getFloatValue() throws InvalidFormatException {
		throw new InvalidFormatException(10, "Invalid type");
	}
	
	double getDoubleValue() throws InvalidFormatException {
		throw new InvalidFormatException(10, "Invalid type");
	}
	
	void setValue(boolean v) throws InvalidFormatException {
		throw new InvalidFormatException(10, "Invalid type");
	}
	
	void setValue(int v) throws InvalidFormatException {
		throw new InvalidFormatException(10, "Invalid type");
	}

	void setValue(long v) throws InvalidFormatException {
		throw new InvalidFormatException(10, "Invalid type");
	}

	void setValue(String v) throws InvalidFormatException {
		throw new InvalidFormatException(10, "Invalid type");
	}

	void setValue(Date v) throws InvalidFormatException {
		if (v == null) {
			return;
		}
		throw new InvalidFormatException(10, "Invalid type");
	}
	
	void setValue(float v) throws InvalidFormatException {
		throw new InvalidFormatException(10, "Invalid type");
	}
	
	void setValue(double v) throws InvalidFormatException {
		throw new InvalidFormatException(10, "Invalid type");
	}
	
	
}


