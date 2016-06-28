package idk.Variant;

public class VariantFloatValue extends VariantValue {
	public VariantFloatValue(VariantAttribute attrib, boolean obligatory) {
		super(attrib, VariantAttribute.AttribType.FloatValue, obligatory);
	}
	public VariantFloatValue(VariantAttribute attrib, boolean obligatory, boolean defaultValue) {
		super(attrib, VariantAttribute.AttribType.FloatValue, obligatory);
		value = (defaultValue)?"True":"False";
	}
	
	public float getFloatValue() throws InvalidFormatException {
		return Float.parseFloat(value);
	}
	
	public void setValue(float v) {
		value = Float.toString(v);
	}
}

