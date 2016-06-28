package idk.imgarchive.base.workspacemanager;

import static org.w3c.dom.Node.ELEMENT_NODE;
import siawebapp.ApplicationError;
import siawebapp.ApplicationError.ErrorCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlutils.XMLUtil;

/**
 * 
 * This class is used to read the cluster information file. As it reads the file
 * it will generate a list of cluster information objects along with a path to
 * the system directory. This system directory will contain a working files for
 * the system such as the sequence file.
 * 
 * @author iain
 * 
 */
public class WorkspaceReader {
	static protected String instancePath = null;

	/**
	 * This function will initialise the clusters returning an error if it finds
	 * inconsistencies within the cluster information file.
	 * 
	 * @return true on success.
	 */
	/*
	 * private boolean InitClusters() {
	 * 
	 * for (ClusterInfo cluster : list) { DirectoryManager tempManager = new
	 * DirectoryManager(cluster.rootPath); String folder =
	 * tempManager.getDirectoryPath(cluster.firstPartitionId);
	 * 
	 * 
	 * if (cluster.firstPartitionId <= idx && cluster.lastPartitionId >= idx) {
	 * 
	 * return cluster; } }
	 * 
	 * return true; }
	 */
	/**
	 * @return the instance path
	 */
	public static final String getInstancePath() {
		return instancePath;
	}

	static public void writeDefault(final String configPath, final String instanceName, final String instancePath, final String id,
			final long size, final int imagesPerPartition, final String path) throws NoSuchAlgorithmException,
			FileNotFoundException {
		final Element e = null;
		Node n = null;
		// Document (Xerces implementation only).
		final Document xmldoc = new DocumentImpl();
		// Root element.
		final Element root = xmldoc.createElement("WorkSpace");
		final Element system = xmldoc.createElement("System");

		root.appendChild(system);
		n = xmldoc.createTextNode(instancePath);
		system.appendChild(n);

		final Element media = xmldoc.createElement("Media");
		root.appendChild(media);

		final Element cluster = xmldoc.createElement("Cluster");
		media.appendChild(cluster);

		Element ie = xmldoc.createElement("Identifier");

		n = xmldoc.createTextNode(id);
		ie.appendChild(n);
		cluster.appendChild(ie);

		ie = xmldoc.createElement("SizeMb");
		final Long longStr = Long.valueOf(size);
		n = xmldoc.createTextNode(longStr.toString());
		ie.appendChild(n);
		cluster.appendChild(ie);

		ie = xmldoc.createElement("ImagesPerPartition");
		final Integer intStr = imagesPerPartition;
		n = xmldoc.createTextNode(intStr.toString());
		ie.appendChild(n);
		cluster.appendChild(ie);

		ie = xmldoc.createElement("FirstPartitionId");
		n = xmldoc.createTextNode("0");
		ie.appendChild(n);
		cluster.appendChild(ie);

		ie = xmldoc.createElement("Path");
		n = xmldoc.createTextNode(path);
		ie.appendChild(n);
		cluster.appendChild(ie);

		xmldoc.appendChild(root);

		FileOutputStream fos = null;

		fos = new FileOutputStream(configPath + File.separator + instanceName + ".xml");
		// XERCES 1 or 2 additionnal classes.
		final OutputFormat of = new OutputFormat("XML", "ISO-8859-1", true);
		of.setIndent(1);
		of.setIndenting(true);
		// of.setDoctype(null,"users.dtd");
		final XMLSerializer serializer = new XMLSerializer(fos, of);
		// As a DOM Serializer
		try {
			serializer.asDOMSerializer();
			serializer.serialize(xmldoc.getDocumentElement());
			fos.close();
		} catch (final IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	protected ArrayList<ClusterInfo> list = new ArrayList<ClusterInfo>();
	/**
	 * Cluster XML Document
	 */
	protected Document xmlDocument;

	/**
	 * This decodes XML cluster information file.
	 * 
	 * @param inputFile
	 *            - Input file to be read.
	 * @return
	 */
	protected boolean decode(final File inputFile) {
		try {
			xmlDocument = XMLUtil.parse(inputFile.getAbsolutePath());
		} catch (final IOException e) {

			return false;
		} catch (final ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * Returns returns the list of cluster information objects.
	 * 
	 * @return
	 */
	public ArrayList<ClusterInfo> getList() {
		return list;
	}

	/**
	 * This reads the cluster information file
	 * 
	 * @param path
	 *            - path to the cluster information file
	 * @param file
	 *            - the filename of the cluster information file
	 * @return
	 */
	public boolean readInstanceFile(final String path, final String file) {
		final File inputFile = new File(path, file);
		if (inputFile.exists() == false) {

			ApplicationError.setErrorCode(ErrorCode.ClusterFileNotFound);
			return false;
		}
		if (decode(inputFile) == false) {
			ApplicationError.setErrorCode(ErrorCode.CanNotReadClusterFile);
			return false;
		}

		final Element rootElement = xmlDocument.getDocumentElement();
		final Element SystemPath = XMLUtil.getElement(rootElement, "System");
		instancePath = SystemPath.getTextContent();
		if (instancePath == null) {
			ApplicationError.setErrorCode(ErrorCode.SystemPathNotSpecified);
			return false;
		}
		final Element Media = XMLUtil.getElement(rootElement, "Media");
		if (Media.hasChildNodes()) {
			final NodeList nodeList = Media.getChildNodes();
			final int n = nodeList.getLength();
			for (int i = 0; i < n; i++) {
				final Node fileNode = nodeList.item(i);
				final short type = fileNode.getNodeType();

				long size = 0; // Size in bytes
				int imagesPerPartition = 0; // number of Partitions
				long firstPartitionId = 0;
				long lastPartitionId = 0;
				String rootPath = null; // Root path to Cluster.

				if (type == ELEMENT_NODE) {

					final Element ImagesPerPartition = XMLUtil.getElement((Element) fileNode, "ImagesPerPartition");
					if (ImagesPerPartition != null) {
						final String imagesPerPartitionString = ImagesPerPartition.getTextContent();
						imagesPerPartition = Integer.parseInt(imagesPerPartitionString, 10);
					}
					// Element LastModified =
					// XMLUtil.getElement((Element)fileNode, "LastModified");

					final Element Size = XMLUtil.getElement((Element) fileNode, "SizeMb");
					if (Size != null) {
						final String sizeString = Size.getTextContent();
						size = Long.parseLong(sizeString, 10);
					}

					final Element FirstPartitionId = XMLUtil.getElement((Element) fileNode, "FirstPartitionId");
					if (FirstPartitionId != null) {
						final String firstPartitionIdString = FirstPartitionId.getTextContent();
						firstPartitionId = Long.parseLong(firstPartitionIdString, 16);
					}

					final Element LastPartitionId = XMLUtil.getElement((Element) fileNode, "LastPartitionId");
					if (LastPartitionId != null) {
						final String lastPartitionIdString = LastPartitionId.getTextContent();
						lastPartitionId = Long.parseLong(lastPartitionIdString, 16);
					} else {
						lastPartitionId = Long.MAX_VALUE;
					}

					final Element Path = XMLUtil.getElement((Element) fileNode, "Path");
					if (Path != null) {
						rootPath = Path.getTextContent();
					}
					final ClusterInfo clusterInfo = new ClusterInfo(size, firstPartitionId, lastPartitionId, imagesPerPartition,
							rootPath);
					list.add(clusterInfo);

				}
			}
		}
		return true;

	}
}
