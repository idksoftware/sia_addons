package idk.imgarchive.base.metadata;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import idk.Variant.InvalidFormatException;
import idk.Variant.VariantContainer;
import idk.Variant.VariantDirectory;

@SuppressWarnings("serial")
public class Metadata extends VariantContainer {
	
	final static String METADATA_NAME = "Metadata";
	final static String DISCRIPTION = "Media Metadata";
	
	IdentityPropertiesDirectory identityPropertiesDirectory = null;
	JounalPropertiesDirectory jounalPropertiesDirectory = null;
	FilePropertiesDirectory fileDirectory = null;
	AssetPropertiesDirectory assetPropertiesDirectory = null;
	CameraPropertiesDirectory cameraPropertiesDirectory = null; 
	IPTCPropertiesDirectory iptcPropertiesDirectory = null; 
	CopyRightPropertiesDirectory copyRightPropertiesDirectory = null; 

	public Metadata() {
		IdentityPropertiesDirectory identiityPropertiesDirectory = new IdentityPropertiesDirectory();
		this.add(identiityPropertiesDirectory);
		JounalPropertiesDirectory jounalPropertiesDirectory = new JounalPropertiesDirectory();
		this.add(jounalPropertiesDirectory);
		FilePropertiesDirectory fileDirectory = new FilePropertiesDirectory();
		this.add(fileDirectory);
	
	}
	
	@Override
	public String getXMLName() {
		return METADATA_NAME;
	}
	
	@Override
	public String getDiscription() {
		// TODO Auto-generated method stub
		return DISCRIPTION;
	}
	
	void addSummaryMetadata(SummaryMetadata sm)
	{
		for (VariantDirectory item : sm) {
			add(item);
		}
	}
	
	/**
	 * @return the identiityPropertiesDirectory
	 */
	public final IdentityPropertiesDirectory getIdentityPropertiesDirectory() {
		return identityPropertiesDirectory;
	}

	/**
	 * @param identiityPropertiesDirectory the identiityPropertiesDirectory to set
	 */
	public final void setIdentiityPropertiesDirectory(IdentityPropertiesDirectory identityPropertiesDirectory) {
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
	 * @return the fileDirectory
	 */
	public final FilePropertiesDirectory getFileDirectory() {
		return fileDirectory;
	}

	/**
	 * @param fileDirectory the fileDirectory to set
	 */
	public final void setFileDirectory(FilePropertiesDirectory fileDirectory) {
		this.fileDirectory = fileDirectory;
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

	@Override
	public Object clone() {
		Metadata metadata = new Metadata();
		try {
			metadata.merge(this);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return metadata;
	}

	

	

}
