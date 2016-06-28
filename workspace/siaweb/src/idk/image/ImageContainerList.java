package idk.image;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class ImageContainerList extends ArrayList<ImageContainer> {

	private String imageSetName = null;
	private String imageSetTypeName = null;

	public String getImageSetName() {
		return imageSetName;
	}

	public String getImageSetTypeName() {
		return imageSetTypeName;
	}

	public void setImageSetName(final String imageSetName) {
		this.imageSetName = imageSetName;
	}

	public void setImageSetTypeName(final String imageSetTypeName) {
		this.imageSetTypeName = imageSetTypeName;
	}

}
