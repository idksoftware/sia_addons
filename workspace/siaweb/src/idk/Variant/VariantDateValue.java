package idk.Variant;


import idk.Variant.VariantAttribute.AttribType;

import java.text.ParseException;
import java.util.Date;

import xmlutils.DateUtils;

public class VariantDateValue extends VariantValue {
	VariantDateValue(VariantAttribute attrib, boolean obligatory) {
		super(attrib, VariantAttribute.AttribType.DateValue, obligatory);
	}
	VariantDateValue(VariantAttribute attrib, boolean obligatory, boolean defaultValue) {
		super(attrib, AttribType.DateValue, obligatory);
		value = (defaultValue)?"True":"False";
	}
	
	Date getDateValue() throws ParseException {
		Date date = DateUtils.parseDDMMYYYY(value);
		return date;
	}
	
	void setValue(Date v) {
		value = DateUtils.formatDDMMYYYY(v);
	}
	
	
}
