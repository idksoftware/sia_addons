package idk.imgarchive.base.cataloger;

import idk.archiveutils.FileUtils;
import idk.config.ConfigInfo;
import idk.imgarchive.base.workspacemanager.PartitionAddress;

import java.io.File;
import java.io.IOException;

public class CatalogInstanceEnviroment {
	/*
	 * static public class ImageEnviroment {
	 * 
	 * 
	 * }
	 */
	static public class PreviewImageEnviroment {
		String baseWwwPath = null; // Base www path i.e.
		// U:\cluster\DCIM\00000000\www
		String cssPath = null; // Base www\css path i.e.
		// U:\cluster\DCIM\00000000\www\css
		String htmlPath = null; // Base www\html path i.e.
		// U:\cluster\DCIM\00000000\www\html
		String imagePath = null; // Base www\html path i.e.
		// U:\cluster\DCIM\00000000\www\image
		String xmlPath = null; // Base www\xml path i.e.

		// U:\cluster\DCIM\00000000\www\xml

		public PreviewImageEnviroment(final PartitionAddress item) throws IOException {
			baseWwwPath = item.getPath() + File.separator + "www";
			// String wwwRootPath = new File(item.getPath()).getParent() +
			// File.separator + "www";

			// make css path
			cssPath = baseWwwPath + File.separator + "css";
			if (FileUtils.makePath(cssPath) == false) {
				throw new IOException("Error: Cannot make path \"" + cssPath + "\"");
			}

			// make css path
			xmlPath = baseWwwPath + File.separator + "xml";
			if (FileUtils.makePath(xmlPath) == false) {
				throw new IOException("Error: Cannot make path \"" + xmlPath + "\"");
			}

			// Detail
			htmlPath = baseWwwPath + File.separator + "html";
			if (FileUtils.makePath(htmlPath) == false) {
				throw new IOException("Error: Cannot make path \"" + htmlPath + "\"");
			}

			// Image
			imagePath = baseWwwPath + File.separator + "image";
			if (FileUtils.makePath(imagePath) == false) {
				throw new IOException("Error: Cannot make path \"" + imagePath + "\"");
			}
		}

		/**
		 * @return the baseWwwPath
		 */
		public final String getBaseWwwPath() {
			return baseWwwPath;
		}

		/**
		 * @return the cssPath
		 */
		public final String getCssPath() {
			return cssPath;
		}

		/**
		 * @return the htmlPath
		 */
		public final String getHtmlPath() {
			return htmlPath;
		}

		/**
		 * @return the imagePath
		 */
		public final String getImagePath() {
			return imagePath;
		}

		/**
		 * @return the xmlPath
		 */
		public final String getXmlPath() {
			return xmlPath;
		}
	}

	static public class PartitionEnviroment {
		String baseWwwPath = null; // Base www path i.e.
		// U:\cluster\DCIM\00000000\www
		String cssPath = null; // Base www\css path i.e.
		// U:\cluster\DCIM\00000000\www\css
		String htmlPath = null; // Base www\html path i.e.
		// U:\cluster\DCIM\00000000\www\html
		String imagePath = null; // Base www\html path i.e.
		// U:\cluster\DCIM\00000000\www\image
		String xmlPath = null; // Base www\xml path i.e.

		// U:\cluster\DCIM\00000000\www\xml

		public PartitionEnviroment(final PartitionAddress item) throws IOException {
			baseWwwPath = ConfigInfo.getInstanceWWWPath() + File.separator + PartitionAddress.makeAddressString(item.getCurrent())
					+ File.separator + "www";
			// String wwwRootPath = new File(item.getPath()).getParent() +
			// File.separator + "www";

			// make css path
			cssPath = baseWwwPath + File.separator + "css";
			if (FileUtils.makePath(cssPath) == false) {
				throw new IOException("Error: Cannot make path \"" + cssPath + "\"");
			}

			// make css path
			xmlPath = baseWwwPath + File.separator + "xml";
			if (FileUtils.makePath(xmlPath) == false) {
				throw new IOException("Error: Cannot make path \"" + xmlPath + "\"");
			}

			// Detail
			htmlPath = baseWwwPath + File.separator + "html";
			if (FileUtils.makePath(htmlPath) == false) {
				throw new IOException("Error: Cannot make path \"" + htmlPath + "\"");
			}

			// Image
			imagePath = baseWwwPath + File.separator + "image";
			if (FileUtils.makePath(imagePath) == false) {
				throw new IOException("Error: Cannot make path \"" + imagePath + "\"");
			}
		}

		/**
		 * @return the baseWwwPath
		 */
		public final String getBaseWwwPath() {
			return baseWwwPath;
		}

		/**
		 * @return the cssPath
		 */
		public final String getCssPath() {
			return cssPath;
		}

		/**
		 * @return the htmlPath
		 */
		public final String getHtmlPath() {
			return htmlPath;
		}

		/**
		 * @return the imagePath
		 */
		public final String getImagePath() {
			return imagePath;
		}

		/**
		 * @return the xmlPath
		 */
		public final String getXmlPath() {
			return xmlPath;
		}
	}

	public static CatalogInstanceEnviroment make() throws IOException {
		final CatalogInstanceEnviroment catalogEnviroment = new CatalogInstanceEnviroment();
		return catalogEnviroment;
	}

	public static CatalogInstanceEnviroment make(final PartitionAddress item) throws IOException {

		final File partitionPath = new File(item.getPath());
		final CatalogInstanceEnviroment catalogEnviroment = new CatalogInstanceEnviroment(partitionPath);
		catalogEnviroment.setPartitionEnviroment(new CatalogInstanceEnviroment.PartitionEnviroment(item));
		return catalogEnviroment;
	}

	String baseInstancePath = null; // U:\\cluster\\DCIM
	String baseInstanceWwwPath = null; // Base instance www path i.e.
	// U:\Instance\www
	String baseInstanceWwwPartitionPath = null; // Base instance www path i.e.

	// U:\Instance\www\parition
	String basePath = null; // U:\\cluster\\DCIM

	String baseWwwPath = null; // Base www path i.e. U:\cluster\DCIM\www
	String cssInstancePath = null; // Base instance www\css path i.e.
	// U:\Instance\www\css
	String cssPath = null; // Base www\css path i.e. U:\cluster\DCIM\www\css
	String htmlInstancePath = null; // Base instance www\html path i.e.
	// U:\Instance\www\html
	String htmlPath = null; // Base www\html path i.e. U:\cluster\DCIM\www\html
	PartitionEnviroment partitionEnviroment = null;

	String xmlInstancePath = null; // Base instance www\xml path i.e.
	// U:\Instance\www\xml

	String xmlPath = null; // Base www\xml path i.e. U:\cluster\DCIM\www\xml

	private CatalogInstanceEnviroment() throws IOException {
		init();
	}

	private CatalogInstanceEnviroment(final File partitionPath) throws IOException {
		init();
		basePath = partitionPath.getParent();

		// make xml path
		baseWwwPath = basePath + File.separator + "www";
		xmlPath = baseWwwPath + File.separator + "xml";
		if (FileUtils.makePath(xmlPath) == false) {
			throw new IOException("Error: Cannot make path \"" + xmlPath + "\"");
		}

		// make css path
		cssPath = baseWwwPath + File.separator + "css";
		if (FileUtils.makePath(cssPath) == false) {
			throw new IOException("Error: Cannot make path \"" + cssPath + "\"");
		}
		// make html
		htmlPath = baseWwwPath + File.separator + "html";
		if (FileUtils.makePath(baseWwwPath + File.separator + "html") == false) {
			throw new IOException("Error: Cannot make path \"" + htmlPath + "\"");
		}

	}

	private void init() throws IOException {
		baseInstancePath = ConfigInfo.getInstancePath();

		// make xml path
		baseInstanceWwwPath = baseInstancePath + File.separator + "www";
		baseInstanceWwwPartitionPath = baseInstanceWwwPath + File.separator + "partition";

		xmlInstancePath = baseInstanceWwwPartitionPath + File.separator + "xml";
		if (FileUtils.makePath(xmlInstancePath) == false) {
			throw new IOException("Error: Cannot make path \"" + xmlInstancePath + "\"");
		}

		// make css path
		cssInstancePath = baseInstanceWwwPartitionPath + File.separator + "css";
		if (FileUtils.makePath(cssInstancePath) == false) {
			throw new IOException("Error: Cannot make path \"" + cssInstancePath + "\"");
		}
		// make html
		htmlInstancePath = baseInstanceWwwPartitionPath + File.separator + "html";
		if (FileUtils.makePath(htmlInstancePath) == false) {
			throw new IOException("Error: Cannot make path \"" + htmlInstancePath + "\"");
		}

	}

	/**
	 * @return the baseInstancePath
	 */
	public final String getBaseInstancePath() {
		return baseInstancePath;
	}

	/**
	 * @return the baseInstanceWwwPath
	 */
	public final String getBaseInstanceWwwPath() {
		return baseInstanceWwwPath;
	}

	/**
	 * @return the baseInstanceWwwPartitionPath
	 */
	public final String getBaseInstanceWwwPartitionPath() {
		return baseInstanceWwwPartitionPath;
	}

	/**
	 * @return the htmlInstancePath
	 */

	/**
	 * @return the basePath
	 */
	public final String getBasePath() {
		return basePath;
	}

	/**
	 * @return the baseWwwPath
	 */
	public final String getBaseWwwPath() {
		return baseWwwPath;
	}

	/**
	 * @return the cssInstancePath
	 */
	public final String getCssInstancePath() {
		return cssInstancePath;
	}

	/**
	 * @return the cssPath
	 */
	public final String getCssPath() {
		return cssPath;
	}

	public final String getHtmlInstancePath() {
		return htmlInstancePath;
	}

	/**
	 * @return the htmlPath
	 */
	public final String getHtmlPath() {
		return htmlPath;
	}

	/**
	 * @return the partitionEnviroment
	 */
	public final PartitionEnviroment getPartitionEnviroment() {
		return partitionEnviroment;
	}

	/**
	 * @return the xmlInstancePath
	 */
	public final String getXmlInstancePath() {
		return xmlInstancePath;
	}

	/**
	 * @return the xmlPath
	 */
	public final String getXmlPath() {
		return xmlPath;
	}

	/**
	 * @param basePath
	 *            the basePath to set
	 */
	public final void setBasePath(final String basePath) {
		this.basePath = basePath;
	}

	/**
	 * @param partitionEnviroment
	 *            the partitionEnviroment to set
	 */
	protected final void setPartitionEnviroment(final PartitionEnviroment partitionEnviroment) {
		this.partitionEnviroment = partitionEnviroment;
	}
}
