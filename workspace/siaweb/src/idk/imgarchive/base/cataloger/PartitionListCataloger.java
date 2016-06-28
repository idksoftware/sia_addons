package idk.imgarchive.base.cataloger;

import idk.archiveutils.FileTypeCSTInclude;
import idk.archiveutils.FileUtils;
import idk.imgarchive.base.hooks.Css;
import idk.imgarchive.base.hooks.XmlHtml;
import idk.imgarchive.base.log4j.Log;
import idk.imgarchive.base.workspacemanager.PartitionAddress;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xmlutils.DateUtils;
import xmlutils.XMLReadHelper;
import xmlutils.XmlWriter;

public class PartitionListCataloger {

	static class PartitionItem {
		private String clusterPath = null;
		private Date dateCreated = null;
		private long fileCount = 0;
		private long fileSizeTotal = 0;
		private long imageCount = 0;
		private long imageFirst = 0;
		private long imageLast = 0;
		private long partitionID = 0;

		private PartitionItem(final PartitionAddress pa, final Date dateCreated, final long imageFirst, final long imageLast,
				final long imageCount, final long fileCount, final long fileSizeTotal) {
			clusterPath = new File(pa.getPath()).getParent();
			partitionID = pa.getCurrent();
			this.dateCreated = dateCreated;
			this.imageFirst = imageFirst;
			this.imageLast = imageLast;
			this.imageCount = imageCount;
			this.fileCount = fileCount;
			this.fileSizeTotal = fileSizeTotal;

		}

		private PartitionItem(final String clusterPath, final long partitionID, final Date dateCreated, final long imageFirst,
				final long imageLast, final long imageCount, final long fileCount, final long fileSizeTotal) {
			this.clusterPath = clusterPath;
			this.partitionID = partitionID;
			this.dateCreated = dateCreated;
			this.imageFirst = imageFirst;
			this.imageLast = imageLast;
			this.imageCount = imageCount;
			this.fileCount = fileCount;
			this.fileSizeTotal = fileSizeTotal;

		}

		/**
		 * @return the clusterPath
		 */
		public final String getclusterPath() {
			return clusterPath;
		}

		/**
		 * @return the dateCreated
		 */
		public final Date getDateCreated() {
			return dateCreated;
		}

		/**
		 * @return the fileCount
		 */
		public final long getFileCount() {
			return fileCount;
		}

		/**
		 * @return the fileSizeTotal
		 */
		public final long getFileSizeTotal() {
			return fileSizeTotal;
		}

		/**
		 * @return the imageCount
		 */
		public final long getImageCount() {
			return imageCount;
		}

		/**
		 * @return the imageFirst
		 */
		public final long getImageFirst() {
			return imageFirst;
		}

		/**
		 * @return the imageLast
		 */
		public final long getImageLast() {
			return imageLast;
		}

		/**
		 * @return the partitionID
		 */
		public final long getPartitionID() {
			return partitionID;
		}

	}

	static ArrayList<PartitionItem> list = new ArrayList<PartitionItem>();

	private CatalogInstanceEnviroment catalogEnviroment = null;

	public PartitionListCataloger() throws IOException {

		catalogEnviroment = CatalogInstanceEnviroment.make();
	}

	public void add(final PartitionCataloger pc) {
		final PartitionItem partitionItem = new PartitionItem(pc.getCurrentItem(), pc.getDateCreated(), pc.getImageFirst(),
				pc.getImageLast(), pc.getImageCount(), pc.getFileCount(), pc.getFileSizeTotal());
		list.add(partitionItem);
	}

	public void process() throws IOException, ParseException, ParserConfigurationException {

		final PartitionItem pi = readXML(catalogEnviroment.getXmlInstancePath());
		if (pi == null) {
			// error
			return;
		}

		writeXML();
		makeCatalogFile(catalogEnviroment.getXmlInstancePath() + File.separator + "PartitionSummary.xml");

	}

	protected void makeCatalogFile(final String fullXmlPath) throws IOException {

		// make Catalog .html
		final String htmlFilePath = catalogEnviroment.getHtmlInstancePath() + File.separator + "Summary.html";
		// final String xslPath = ConfigInfo.getConfigPath() + File.separator +
		// "partition_summary.xsl";

		try {
			XmlHtml.makeHtml("partition_summary.xsl", fullXmlPath, htmlFilePath);
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// final String styleSheetConfigPath = ConfigInfo.getCssPath() +
		// File.separator + "partition_catalog.css";
		// final String styleSheetDestPath =
		// catalogEnviroment.getCssInstancePath() + File.separator +
		// "partition_catalog.css";

		try {
			// FileUtils.copyIfNotExist(styleSheetDestPath,
			// styleSheetConfigPath);
			Css.Copy("partition_catalog.css", catalogEnviroment.getCssInstancePath());
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private PartitionItem readPartitionItem(final Document document) throws ParseException {
		final NodeList list = document.getElementsByTagName("Catalogue");
		final Element rootElement = (Element) list.item(0);
		final String clusterPath = XMLReadHelper.stringValue(rootElement, "ClusterPath", true);
		final long partitionID = XMLReadHelper.longValue(rootElement, "PartitionID", true, true);
		final Date dateCreated = XMLReadHelper.dateValue(rootElement, "DateCreated", true);
		final int numberOfFiles = XMLReadHelper.integerValue(rootElement, "NumberOfFiles", true, false);
		final long totalSize = XMLReadHelper.longValue(rootElement, "TotalSize", true, false);
		final PartitionItem partitionItem = new PartitionItem(clusterPath, partitionID, dateCreated, 0, 100, 100, numberOfFiles,
				totalSize);
		return partitionItem;
	}

	public PartitionItem readXML(final String partitionPath) throws IOException, ParseException {

		final File xmlFolder = new File(partitionPath);
		Document document = null;
		PartitionItem partitionItem = null;
		final File[] xmlFiles = xmlFolder.listFiles(new FileTypeCSTInclude());
		for (final File xmlFile : xmlFiles) {

			try {
				document = XMLReadHelper.decode(xmlFile);
			} catch (final IOException e) {
				Log.IOException(xmlFile.getName(), e);
			} catch (final ParseException e) {
				Log.ParseException(xmlFile.getName(), e);
			}
			partitionItem = readPartitionItem(document);
			list.add(partitionItem);
		}
		return partitionItem;

	}

	public void writePartitionItem(final PartitionItem item) {
		final String path = catalogEnviroment.getXmlInstancePath() + File.separator + "partition";
		FileUtils.makePath(path);

		final XmlWriter xmlWriter = new XmlWriter(path, PartitionAddress.makeAddressString(item.getPartitionID()) + ".xml");
		xmlWriter.startElement("Catalogue");
		final Date now = new Date();
		xmlWriter.startElement("Date");

		final String dateString = DateUtils.formatDDMMYYYY(now);
		xmlWriter.element(dateString);
		xmlWriter.endElement("Date");
		xmlWriter.startElement("Contents");

		writePartitionItem(xmlWriter, item);

		xmlWriter.endElement("Contents");

		xmlWriter.endElement("Catalogue");
		xmlWriter.close();
	}

	public static XmlWriter writePartitionItem(final XmlWriter xmlWriter, final PartitionItem item) {
		// String dateString = DateUtils.formatDDMMYYYY(item.dateCreated);
		xmlWriter.startElement("Partition");
		xmlWriter.startElement("ClusterPath");
		xmlWriter.element(item.clusterPath);
		xmlWriter.endElement("ClusterPath");
		xmlWriter.startElement("PartitionID");
		xmlWriter.element(PartitionAddress.makeAddressString(item.partitionID));
		xmlWriter.endElement("PartitionID");
		xmlWriter.startElement("Date");
		xmlWriter.element(DateUtils.formatDDMMYYYY(item.dateCreated));
		xmlWriter.endElement("Date");
		xmlWriter.startElement("ImageFirst");
		xmlWriter.element(Long.toString(item.getImageFirst()));
		xmlWriter.endElement("ImageFirst");
		xmlWriter.startElement("ImageLast");
		xmlWriter.element(Long.toString(item.getImageLast()));
		xmlWriter.endElement("ImageLast");
		xmlWriter.startElement("ImageCount");
		xmlWriter.element(Long.toString(item.getImageCount()));
		xmlWriter.endElement("ImageCount");
		xmlWriter.startElement("FileCount");
		xmlWriter.element(Long.toString(item.getFileCount()));
		xmlWriter.endElement("FileCount");
		xmlWriter.startElement("FileSizeTotal");
		xmlWriter.element(Long.toString(item.getFileSizeTotal()));
		xmlWriter.endElement("FileSizeTotal");
		xmlWriter.endElement("Partition");

		return xmlWriter;
	}

	public void writeXML() throws ParserConfigurationException {
		final XmlWriter xmlWriter = new XmlWriter(catalogEnviroment.getXmlInstancePath(), "PartitionSummary.xml");
		xmlWriter.startElement("Catalogue");
		final Date now = new Date();
		xmlWriter.startElement("Date");

		final String dateString = DateUtils.formatDDMMYYYY(now);
		xmlWriter.element(dateString);
		xmlWriter.endElement("Date");
		xmlWriter.startElement("Contents");

		for (final PartitionItem item : list) {
			// Writes out the PartitionItem in the Summary file
			writePartitionItem(xmlWriter, item);
			// Writes out the PartitionItem file
			writePartitionItem(item);
		}
		xmlWriter.endElement("Contents");

		xmlWriter.endElement("Catalogue");
		xmlWriter.close();
	}
}
