package idk.imgarchive.base.metadata;

import idk.Variant.VariantAttribute;

import java.util.ArrayList;
import java.util.Date;




public enum AssetAttribute implements VariantAttribute {
	
	IMG_LABEL("Label", "Label"),
	IMG_CAPTION("Caption", "Caption"),
	IMG_CATEGORY("Category", "Category"),
	IMG_HARD_COPY_LOCATION("Hard Copy Location", "HardCopyLocation"),
	IMG_LANGUAGE("Language", "Language"),
	IMG_AUTHOR("Author", "Author"),
	IMG_TYPE("Type", "Type"),
	IMG_USER_COMMENT("Comment", "Comment"),
	IMG_EDITOR("Editor", "Editor"),
	IMG_TAGLIST("Tags", "Tags"),
	IMG_INTELLECTUAL_GENRE("Intellectual Genre", "IntellectualGenre"),
	IMG_RATING("Rating", "Rating"),
	IMG_DATE_CREATED("Date Created", "DateCreated"),
	IMG_MEDIA_TYPE("Media Type", "MediaType"),
	IMG_TAG("Tag", "Tag")
		;
	
	public final static String AssetProperties = "AssetProperties";
	
	protected String friendlyLable = null;
	protected String xmlLable = null;
	protected VariantAttribute.AttribType attribType = null;
	
	AssetAttribute(String friendlyLable, String xmlLable) {
		this.friendlyLable = friendlyLable;
		this.xmlLable = xmlLable;	
	}
	
	/**
	 * @return the friendlyLable
	 */
	public final String getFriendlyLable() {
		return friendlyLable;
	}
	/**
	 * @return the xmlLable
	 */
	public final String getXmlLable() {
		return xmlLable;
	}

	@Override
	public String getXmlNamespace() {
		return AssetProperties;
	}

		
}
