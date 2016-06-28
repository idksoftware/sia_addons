package idk.Variant;

public class VariantStringValue extends VariantValue {
	public VariantStringValue(VariantAttribute attrib, boolean obligatory) {
		super(attrib, VariantAttribute.AttribType.StringValue, obligatory);
	}
	public VariantStringValue(VariantAttribute attrib, boolean obligatory, boolean defaultValue) {
		super(attrib, VariantAttribute.AttribType.StringValue, obligatory);
		value = (defaultValue)?"True":"False";
	}
	
	public String getStringValue() throws InvalidFormatException {
		return value;
	}
	
	public void setValue(String v) {
		value = v;
	}
}
