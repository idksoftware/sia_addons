package idk.Variant;

public interface VariantAttribute {
	public enum AttribType {
		BooleanValue,
		DateValue,
		IntegerValue,
		LongValue,
		StringValue,
		FloatValue,
		DoubleValue,
		ListValue
	};
	/**
	 * @return the friendlyLable
	 */
	public String getFriendlyLable();
	
	/**
	 * @return the xmlLable
	 */
	public String getXmlLable();
	
	/**
	 * @return the xml namespace
	 */
	public String getXmlNamespace();
	
	
}
