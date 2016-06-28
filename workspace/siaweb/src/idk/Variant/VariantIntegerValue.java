package idk.Variant;

public class VariantIntegerValue extends VariantValue {
	boolean formatType = false;
	public VariantIntegerValue(VariantAttribute attrib, boolean obligatory) {
		super(attrib, VariantAttribute.AttribType.IntegerValue, obligatory);
	}
	public VariantIntegerValue(VariantAttribute attrib, boolean obligatory, boolean hex) {
		super(attrib, VariantAttribute.AttribType.IntegerValue, obligatory);
		formatType = hex;
	}
	public VariantIntegerValue(VariantAttribute attrib, boolean obligatory, boolean defaultValue, boolean hex) {
		super(attrib, VariantAttribute.AttribType.IntegerValue, obligatory);
		value = (defaultValue)?"True":"False";
		formatType = hex;
	}
	
	public int getIntegerValue() throws InvalidFormatException {
		return Integer.parseInt(value);
		
	}
	
	public void setValue(int v) {
		if (formatType == true) {
			value = Integer.toHexString(v);
		} else {
			value = Integer.toString(v);
		}
	}
}