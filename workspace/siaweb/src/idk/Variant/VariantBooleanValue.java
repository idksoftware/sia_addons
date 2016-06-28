package idk.Variant;

import idk.Variant.VariantAttribute.AttribType;




public class VariantBooleanValue extends VariantValue {
	VariantBooleanValue(VariantAttribute attrib, boolean obligatory) {
		super(attrib, AttribType.BooleanValue, obligatory);
	}
	VariantBooleanValue(VariantAttribute attrib, boolean obligatory, boolean defaultValue) {
		super(attrib, VariantAttribute.AttribType.BooleanValue, obligatory);
		value = (defaultValue)?"True":"False";
	}
	
	boolean getBooleanValue() throws InvalidFormatException {
		return Boolean.parseBoolean(value);
	}
	
	void setValue(boolean v) {
		value = Boolean.toString(v);
	}
}
