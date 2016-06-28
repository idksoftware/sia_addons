package idk.imgarchive.base.metadata;

import java.io.File;

import org.w3c.dom.Element;

import idk.Variant.InvalidFormatException;
import idk.Variant.VariantContainer;



@SuppressWarnings("serial")
public class SummaryMetadata extends VariantContainer {
	final static String METADATA_NAME = "SummaryMetadata";
	final static String DISCRIPTION = "Media Summary Metadata";
	IdentityPropertiesDirectory identityPropertiesDirectory = null;
	JounalPropertiesDirectory jounalPropertiesDirectory = null;
	FilePropertiesDirectory filePropertiesDirectory = null;
	AssetPropertiesDirectory assetPropertiesDirectory = null;
	MediaPropertiesDirectory mediaPropertiesDirectory = null;
	CameraPropertiesDirectory cameraPropertiesDirectory = null; 
	GPSPropertiesDirectory gpsPropertiesDirectory = null;
	IPTCPropertiesDirectory iptcPropertiesDirectory = null; 
	CopyRightPropertiesDirectory copyRightPropertiesDirectory = null; 

	public SummaryMetadata() {
		identityPropertiesDirectory = new IdentityPropertiesDirectory();
		this.add(identityPropertiesDirectory);
		jounalPropertiesDirectory = new JounalPropertiesDirectory();
		this.add(jounalPropertiesDirectory);
		filePropertiesDirectory = new FilePropertiesDirectory();
		this.add(filePropertiesDirectory);
		assetPropertiesDirectory = new AssetPropertiesDirectory();
		this.add(assetPropertiesDirectory);
		mediaPropertiesDirectory = new MediaPropertiesDirectory();
		this.add(mediaPropertiesDirectory);
		cameraPropertiesDirectory = new CameraPropertiesDirectory();
		this.add(cameraPropertiesDirectory);
		gpsPropertiesDirectory = new GPSPropertiesDirectory();
		this.add(gpsPropertiesDirectory);
		copyRightPropertiesDirectory = new CopyRightPropertiesDirectory();
		this.add(copyRightPropertiesDirectory);
	}
	
	
	public SummaryMetadata(final Element root) throws InvalidFormatException {
		identityPropertiesDirectory = new IdentityPropertiesDirectory(root);
		this.add(identityPropertiesDirectory);
		jounalPropertiesDirectory = new JounalPropertiesDirectory(root);
		this.add(jounalPropertiesDirectory);
		filePropertiesDirectory = new FilePropertiesDirectory(root);
		this.add(filePropertiesDirectory);
		assetPropertiesDirectory = new AssetPropertiesDirectory(root);
		this.add(assetPropertiesDirectory);
		mediaPropertiesDirectory = new MediaPropertiesDirectory(root);
		this.add(mediaPropertiesDirectory);
		cameraPropertiesDirectory = new CameraPropertiesDirectory(root);
		this.add(cameraPropertiesDirectory);
		gpsPropertiesDirectory = new GPSPropertiesDirectory(root);
		this.add(gpsPropertiesDirectory);
		iptcPropertiesDirectory = new IPTCPropertiesDirectory(root);
		this.add(iptcPropertiesDirectory);
		copyRightPropertiesDirectory = new CopyRightPropertiesDirectory(root);
		this.add(copyRightPropertiesDirectory);
	}
	
	/**
	 * @return the identityPropertiesDirectory
	 */
	public final IdentityPropertiesDirectory getIdentityPropertiesDirectory() {
		return identityPropertiesDirectory;
	}


	/**
	 * @param identityPropertiesDirectory the identityPropertiesDirectory to set
	 */
	public final void setIdentityPropertiesDirectory(IdentityPropertiesDirectory identityPropertiesDirectory) {
		this.identityPropertiesDirectory = identityPropertiesDirectory;
	}


	/**
	 * @return the jounalPropertiesDirectory
	 */
	public final JounalPropertiesDirectory getJounalPropertiesDirectory() {
		return jounalPropertiesDirectory;
	}


	/**
	 * @param jounalPropertiesDirectory the jounalPropertiesDirectory to set
	 */
	public final void setJounalPropertiesDirectory(JounalPropertiesDirectory jounalPropertiesDirectory) {
		this.jounalPropertiesDirectory = jounalPropertiesDirectory;
	}


	/**
	 * @return the filePropertiesDirectory
	 */
	public final FilePropertiesDirectory getFilePropertiesDirectory() {
		return filePropertiesDirectory;
	}


	/**
	 * @param filePropertiesDirectory the filePropertiesDirectory to set
	 */
	public final void setFilePropertiesDirectory(FilePropertiesDirectory filePropertiesDirectory) {
		this.filePropertiesDirectory = filePropertiesDirectory;
	}


	@Override
	public Object clone() {
		SummaryMetadata metadata = new SummaryMetadata();
		try {
			metadata.merge(this);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return metadata;
	}

	/**
	 * @return the assetPropertiesDirectory
	 */
	public final AssetPropertiesDirectory getAssetPropertiesDirectory() {
		return assetPropertiesDirectory;
	}

	/**
	 * @param assetPropertiesDirectory the assetPropertiesDirectory to set
	 */
	public final void setAssetPropertiesDirectory(AssetPropertiesDirectory assetPropertiesDirectory) {
		this.assetPropertiesDirectory = assetPropertiesDirectory;
	}

	/**
	 * @return the cameraPropertiesDirectory
	 */
	public final CameraPropertiesDirectory getCameraPropertiesDirectory() {
		return cameraPropertiesDirectory;
	}

	/**
	 * @param cameraPropertiesDirectory the cameraPropertiesDirectory to set
	 */
	public final void setCameraPropertiesDirectory(CameraPropertiesDirectory cameraPropertiesDirectory) {
		this.cameraPropertiesDirectory = cameraPropertiesDirectory;
	}
	/**
	 * @return the mediaPropertiesDirectory
	 */
	public final MediaPropertiesDirectory getMediaPropertiesDirectory() {
		return mediaPropertiesDirectory;
	}

	/**
	 * @param mediaPropertiesDirectory the mediaPropertiesDirectory to set
	 */
	public final void setMediaPropertiesDirectory(MediaPropertiesDirectory mediaPropertiesDirectory) {
		this.mediaPropertiesDirectory = mediaPropertiesDirectory;
	}

	/**
	 * @return the gpsPropertiesDirectory
	 */
	public final GPSPropertiesDirectory getGpsPropertiesDirectory() {
		return gpsPropertiesDirectory;
	}

	/**
	 * @param gpsPropertiesDirectory the gpsPropertiesDirectory to set
	 */
	public final void setGpsPropertiesDirectory(GPSPropertiesDirectory gpsPropertiesDirectory) {
		this.gpsPropertiesDirectory = gpsPropertiesDirectory;
	}

	/**
	 * @return the iptcPropertiesDirectory
	 */
	public final IPTCPropertiesDirectory getIptcPropertiesDirectory() {
		return iptcPropertiesDirectory;
	}

	/**
	 * @param iptcPropertiesDirectory the iptcPropertiesDirectory to set
	 */
	public final void setIptcPropertiesDirectory(IPTCPropertiesDirectory iptcPropertiesDirectory) {
		this.iptcPropertiesDirectory = iptcPropertiesDirectory;
	}

	/**
	 * @return the copyRightPropertiesDirectory
	 */
	public final CopyRightPropertiesDirectory getCopyRightPropertiesDirectory() {
		return copyRightPropertiesDirectory;
	}

	/**
	 * @param copyRightPropertiesDirectory the copyRightPropertiesDirectory to set
	 */
	public final void setCopyRightPropertiesDirectory(CopyRightPropertiesDirectory copyRightPropertiesDirectory) {
		this.copyRightPropertiesDirectory = copyRightPropertiesDirectory;
	}

	public void read(Element presetsInfo) throws InvalidFormatException {
		readXML(presetsInfo);	
	}

/*
	public static SummaryMetadata read(File item) {
		// TODO Auto-generated method stub
		return null;
	}
*/

	@Override
	public String getXMLName() {
		return METADATA_NAME;
	}


	@Override
	public String getDiscription() {
		return DISCRIPTION;
	}

}
