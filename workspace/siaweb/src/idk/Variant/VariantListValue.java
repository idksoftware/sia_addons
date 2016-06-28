package idk.Variant;





import idk.Variant.VariantAttribute.AttribType;


public class VariantListValue extends VariantValue {
	VariantValue itemAttrib = null;
	AttribType itemType = AttribType.StringValue;
	AttribList list = new AttribList();
	
	/**
	 * @return the list
	 */
	public final AttribList getList() {
		return list;
	}
	public VariantListValue(VariantAttribute parentAttrib, VariantValue itemAttrValue, boolean obligatory) {
		super(parentAttrib, VariantAttribute.AttribType.ListValue, obligatory);
		this.itemAttrib = itemAttrValue;
		
	}
	public VariantListValue(VariantAttribute parentAttrib, VariantValue itemAttrValue, boolean obligatory, boolean defaultValue) {
		super(parentAttrib, VariantAttribute.AttribType.ListValue, obligatory);
		this.itemAttrib = itemAttrValue;
	}
	
	public String getStringValue() throws InvalidFormatException {
		return value;
	}
	
	public void setValue(String v) {
		value = v;
	}
	
	public void add(String v) throws InvalidFormatException {
		VariantValue item = null;
		switch(itemAttrib.getAttribType()) {
		case BooleanValue:
			item = new VariantBooleanValue(itemAttrib.getAttribute(), false);
			item.setValue(v);
			break;
		case DateValue:
			item = new VariantDateValue(itemAttrib.getAttribute(), false);
			item.setValue(v);
			break;
		case IntegerValue:
			item = new VariantIntegerValue(itemAttrib.getAttribute(), false);
			item.setValue(v);
			break;
		case LongValue:
			item = new VariantLongValue(itemAttrib.getAttribute(), false);
			item.setValue(v);
			break;
		case StringValue:
			item = new VariantStringValue(itemAttrib.getAttribute(), false);
			item.setValue(v);
			break;
		case FloatValue:
			item = new VariantFloatValue(itemAttrib.getAttribute(), false);
			item.setValue(v);
			break;
		case DoubleValue:
			item = new VariantDoubleValue(itemAttrib.getAttribute(), false);
			item.setValue(v);
			break;
		}
		list.add(item);
	}
}
