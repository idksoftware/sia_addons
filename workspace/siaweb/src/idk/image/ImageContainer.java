package idk.image;
import idk.imgarchive.base.metadata.SummaryMetadata;
import java.util.ArrayList;

/*
 * This Object is used to contain an image. This includes the metaDataObject along with
 * an array of image discriptors (ImageSetFile).
 */
@SuppressWarnings("serial")
public class ImageContainer extends ArrayList<ImageSetFile> {

	SummaryMetadata summaryMetadata = null;

	public ImageContainer(final SummaryMetadata summaryMetadata) {
		this.summaryMetadata = summaryMetadata;
	}

	public SummaryMetadata getMetaDataObject() {
		return summaryMetadata;
	}
	/*
	 * public void AddImageFile(ImageSetFile imageSetFile) {
	 * ImageSetFiles.add(imageSetFile); }
	 * 
	 * public ArrayList<ImageSetFile> getImageFiles() { return ImageSetFiles; }
	 */
}
