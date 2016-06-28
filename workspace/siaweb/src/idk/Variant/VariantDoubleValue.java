package idk.Variant;

public class VariantDoubleValue extends VariantValue {
	public VariantDoubleValue(VariantAttribute attrib, boolean obligatory) {
		super(attrib, VariantAttribute.AttribType.DoubleValue, obligatory);
	}
	public VariantDoubleValue(VariantAttribute attrib, boolean obligatory, double defaultValue) {
		super(attrib, VariantAttribute.AttribType.DoubleValue, obligatory);
		value = Double.toString(defaultValue);
	}
	
	public double getDoubleValue() throws InvalidFormatException {
		return Double.parseDouble(value);
	}
	
	public void setValue(double v) {
		value = Double.toString(v);
	}
}

