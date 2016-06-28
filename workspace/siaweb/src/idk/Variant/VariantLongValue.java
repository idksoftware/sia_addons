package idk.Variant;

public class VariantLongValue extends VariantValue {
	boolean formatType = false;
	public VariantLongValue(VariantAttribute attrib, boolean obligatory) {
		super(attrib, VariantAttribute.AttribType.LongValue, obligatory);
	}
	public VariantLongValue(VariantAttribute attrib, boolean obligatory, boolean hex) {
		super(attrib, VariantAttribute.AttribType.LongValue, obligatory);
		formatType = hex;
	}
	public VariantLongValue(VariantAttribute attrib, boolean obligatory, boolean defaultValue, boolean hex) {
		super(attrib, VariantAttribute.AttribType.LongValue, obligatory);
		value = (defaultValue)?"True":"False";
		formatType = hex;
	}
	
	public long getLongValue() throws InvalidFormatException {
		return Long.parseLong(value);
		
	}
	
	public void setValue(long v) {
		if (formatType == true) {
			value = Long.toHexString(v);
		} else {
			value = Long.toString(v);
		}
	}
}