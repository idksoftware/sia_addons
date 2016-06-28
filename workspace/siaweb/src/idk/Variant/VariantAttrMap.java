package idk.Variant;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class VariantAttrMap extends ArrayList<VariantValue> {
	HashMap<VariantAttribute, VariantValue> map = new HashMap<VariantAttribute, VariantValue>();
	HashMap<String, VariantValue> xmlTagMap = new HashMap<String, VariantValue>();
	/**
	 * @return the map
	 */
	public final HashMap<VariantAttribute, VariantValue> getMap() {
		return map;
	}
	public final HashMap<String, VariantValue> getXMLMap() {
		return xmlTagMap;
	}
	public void put(VariantValue value) {
		map.put(value.getAttribute(), value);
		xmlTagMap.put(value.getAttribute().getXmlLable(), value);
		this.add(value);
	}
	
}
