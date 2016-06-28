/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idk.archiveutils;

import static org.w3c.dom.Node.ATTRIBUTE_NODE;
import static org.w3c.dom.Node.CDATA_SECTION_NODE;
import static org.w3c.dom.Node.COMMENT_NODE;
import static org.w3c.dom.Node.DOCUMENT_TYPE_NODE;
import static org.w3c.dom.Node.ELEMENT_NODE;
import static org.w3c.dom.Node.ENTITY_NODE;
import static org.w3c.dom.Node.ENTITY_REFERENCE_NODE;
import static org.w3c.dom.Node.NOTATION_NODE;
import static org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE;
import static org.w3c.dom.Node.TEXT_NODE;

import idk.imgarchive.base.log4j.Log;
import idk.imgarchive.base.metadata.SummaryMetadata;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlutils.XMLUtil;

/**
 * 
 * @author Iain Ferguson
 */
public class XMPFileReader {

	private static final int MAX_ATTRIB = 2;
	private static final int NAME_SPACE = 1;
	private static final String XMLNS_AUX = "xmlns:aux";
	private static final String XMLNS_CRSS = "xmlns:crss";
	private static final String XMLNS_DC = "xmlns:dc";
	private static final String XMLNS_EXIF = "xmlns:exif";
	private static final String XMLNS_IPTC4XMPCORE = "xmlns:Iptc4xmpCore";
	private static final String XMLNS_TIFF = "xmlns:tiff";
	private static final String XMLNS_XAP = "xmlns:xap";

	static String nodeType(final short type) {
		switch (type) {
		case ELEMENT_NODE:
			return "Element";
		case DOCUMENT_TYPE_NODE:
			return "Document type";
		case ENTITY_NODE:
			return "Entity";
		case ENTITY_REFERENCE_NODE:
			return "Entity reference";
		case NOTATION_NODE:
			return "Notation";
		case TEXT_NODE:
			return "Text";
		case COMMENT_NODE:
			return "Comment";
		case CDATA_SECTION_NODE:
			return "CDATA Section";
		case ATTRIBUTE_NODE:
			return "Attribute";
		case PROCESSING_INSTRUCTION_NODE:
			return "Attribute";
		}
		return "Unidentified";
	}

	private final File inputFile;
	private SummaryMetadata summaryMetadata = null;

	/**
	 * The DOM representation of the metadata.
	 */
	protected Document xmpDocument;

	public XMPFileReader(final File ifile, final SummaryMetadata summaryMetadata) {
		inputFile = ifile;
		this.summaryMetadata = summaryMetadata;
		if (this.summaryMetadata == null) {
			this.summaryMetadata = new SummaryMetadata();
		}
	}

	private void decode() throws IOException, ParseException {
		try {
			xmpDocument = XMLUtil.parse(inputFile.getAbsolutePath());
		} catch (final IOException e2) {
			Log.IOException(inputFile.getName(), e2);
		} catch (final ParseException e2) {
			Log.ParseException(inputFile.getName(), e2);
		}
	}

	public Document getDocument() {
		return xmpDocument;
	}

	public SummaryMetadata getMetaDataObject() {
		return summaryMetadata;
	}

	public void process() throws IOException, ParseException {
		decode();
		final Element root = xmpDocument.getDocumentElement();
		final NodeList rdf = root.getElementsByTagName("rdf:RDF");
		// NodeList rdfs = root.getElementsByTagNameNS(
		// "http://www.w3.org/1999/02/22-rdf-syntax-ns#", "*");
		final NodeList rdfNodes = ((Element) rdf.item(0)).getElementsByTagName("rdf:Description");
		for (int i = 0; i < rdfNodes.getLength(); i++) {
			final Node node = rdfNodes.item(i);
			// String namespace = node.getNamespaceURI();
			// String value = node.getNodeValue();
			final NamedNodeMap nmap = node.getAttributes();
			if (nmap.getLength() != MAX_ATTRIB) // There are always two
			// attributes
			// rdf:about="" xmlns:tiff="
			{
				return;
			}
			final String nameSpace = nmap.item(NAME_SPACE).getNodeName();
			if (nameSpace.matches(XMLNS_TIFF)) {
				xmlnsTiff(node);
				continue;
			} else if (nameSpace.matches(XMLNS_EXIF)) {
				xmlnsExif(node);
			} else if (nameSpace.matches(XMLNS_XAP)) {
				xmlnsXap(node);
			} else if (nameSpace.matches(XMLNS_AUX)) {
				xmlnsAux(node);
			} else if (nameSpace.matches(XMLNS_CRSS)) {
				xmlnsCrss(node);
			} else if (nameSpace.matches(XMLNS_DC)) {
				xmlnsDc(node);
			} else if (nameSpace.matches(XMLNS_IPTC4XMPCORE)) {
				xmlnsIptc4xmpCore(node);
			}

		}
	}

	void xmlnsAux(final Node node) {
		if (node.hasChildNodes() == true) {
			final NodeList childNodes = node.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {

				final Node cnode = childNodes.item(j);
				final String name = cnode.getNodeName();
				final String text = cnode.getTextContent();
				if (cnode.getNodeType() == ELEMENT_NODE) {
					// System.out.println(nodeType(cnode.getNodeType()) +
					// " Name: " + cnode.getNodeName() + "Text content: " +
					// text);
					if (name.matches("aux:SerialNumberText") == true) {
				//		summaryInfo.getAssetPropertiesInfo().caption = text;
					}
					if (name.matches("aux:LensInfoText") == true) {
				//		summaryInfo.getMediaPropertiesInfo().orientation = text;
					}
					if (name.matches("aux:LensText") == true) {
				//		summaryInfo.getMediaPropertiesInfo().orientation = text;
					}
					if (name.matches("aux:LensID") == true) {
				//		summaryInfo.getMediaPropertiesInfo().orientation = text;
					}
					if (name.matches("aux:ImageNumber") == true) {
				//		summaryInfo.getMediaPropertiesInfo().orientation = text;
					}
				}
			}

		}
	}

	void xmlnsCrss(final Node node) {
		if (node.hasChildNodes() == true) {
			final NodeList childNodes = node.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {

				final Node cnode = childNodes.item(j);
				final String name = cnode.getNodeName();
				final String text = cnode.getTextContent();
				if (cnode.getNodeType() == ELEMENT_NODE) {
					System.out.println(nodeType(cnode.getNodeType()) + " Name: " + cnode.getNodeName() + "Text content: " + text);
				}
			}

		}
	}

	void xmlnsDc(final Node node) {
		if (node.hasChildNodes() == true) {
			final NodeList childNodes = node.getChildNodes();

			for (int j = 0; j < childNodes.getLength(); j++) {

				final Node cnode = childNodes.item(j);
				final String name = cnode.getNodeName();
				final String text = cnode.getTextContent();
				if (cnode.getNodeType() == ELEMENT_NODE) {
					System.out.println(nodeType(cnode.getNodeType()) + " Name: " + cnode.getNodeName() + "Text content: " + text);
				}
			}

		}
	}

	void xmlnsExif(final Node node) {
		if (node.hasChildNodes() == true) {
			final NodeList childNodes = node.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {

				final Node cnode = childNodes.item(j);
				final String name = cnode.getNodeName();
				final String text = cnode.getTextContent();
				if (cnode.getNodeType() == ELEMENT_NODE) {
					// System.out.println(nodeType(cnode.getNodeType()) +
					// " Name: " + cnode.getNodeName() + " Text content: " +
					// text);
					if (name.matches("exif:ExifVersion") == true) {
				//		summaryInfo.getCameraPropertiesInfo().exifVersion = text;
					}
					if (name.matches("exif:ExposureTime") == true) {
				//		summaryInfo.getCameraPropertiesInfo().exposureTime = text;
					}
					if (name.matches("exif:ShutterSpeedValue") == true) {
				//		summaryInfo.getCameraPropertiesInfo().exposureTime = text;
					}
					if (name.matches("exif:FNumber") == true) {
				//		summaryInfo.getCameraPropertiesInfo().aperture = text;
					}
					if (name.matches("exif:ApertureValue") == true) {
				//		summaryInfo.getCameraPropertiesInfo().aperture = text;
					}
					if (name.matches("exif:ExposureProgram") == true) {
				//		summaryInfo.getCameraPropertiesInfo().exposureProgram = text;
					}
					if (name.matches("exif:ISOSpeedRatings") == true) {
				//		summaryInfo.getCameraPropertiesInfo().ISOSpeedRating = text;
					}

					// if (name.matches("exif:DateTimeOriginal") == true) //
					// 2008-09-19T12:27:23.30+01:00
					// {
					// metaDataObject.captureDate = text;
					// }
					if (name.matches("exif:DateTimeDigitized") == true) {
						// summaryInfo.getCameraPropertiesInfo().dateTimeDigitized
						// = text;
					}
					if (name.matches("exif:ExposureBiasValue") == true) {
				//		summaryInfo.getCameraPropertiesInfo().exposureBias = text;
					}
					// if (name.matches("exif:MaxApertureValue") == true)
					// {
					// metaDataObject. = text;
					// }
					// if (name.matches("exif:SubjectDistance") == true)
					// {
					// metaDataObject. = text;
					// }
					if (name.matches("exif:MeteringMode") == true) {
				//		summaryInfo.getCameraPropertiesInfo().meteringMode = text;
					}
					if (name.matches("exif:LightSource") == true) {
				//		summaryInfo.getCameraPropertiesInfo().lightSource = text;
					}
					if (name.matches("exif:Flash") == true) {
				//		summaryInfo.getCameraPropertiesInfo().flash = text;
					}
					// False
					// 0
					// 0
					// False
					// False

					if (name.matches("exif:FocalLength") == true) {
				//		summaryInfo.getCameraPropertiesInfo().focalLength = text;
					}
					if (name.matches("exif:SensingMethod") == true) {
				//		summaryInfo.getCameraPropertiesInfo().sensingMethod = text;
					}

					// if (name.matches("exif:FileSource") == true)
					// {
					// metaDataObject. = text;
					// }
					// if (name.matches("exif:SceneType") == true)
					// {
					// metaDataObject.maker = text;
					// }
					// if (name.matches("exif:FocalLengthIn35mmFilm") == true)
					// {
					// metaDataObject.maker = text;
					// }
					// if (name.matches("exif:CustomRendered") == true)
					// {
					// metaDataObject.maker = text;
					// }
					if (name.matches("exif:ExposureMode") == true) {
				//		summaryInfo.getCameraPropertiesInfo().exposureProgram = text;
					}
					if (name.matches("exif:WhiteBalance") == true) {
				//		summaryInfo.getCameraPropertiesInfo().whiteBalance = text;
					}
					if (name.matches("exif:SceneCaptureType") == true) {
				//		summaryInfo.getCameraPropertiesInfo().maker = text;
					}
					// if (name.matches("exif:GainControl") == true)
					// {
					// metaDataObject.maker = text;
					// }
					// if (name.matches("exif:Contrast") == true)
					// {
					// metaDataObject.maker = text;
					// }
					// if (name.matches("exif:Saturation") == true)
					// {
					// metaDataObject.maker = text;
					// }
					// if (name.matches("exif:Sharpnes") == true)
					// {
					// metaDataObject.maker = text;
					// }
					// if (name.matches("exif:SubjectDistanceRange") == true)
					// {
					// metaDataObject.maker = text;
					// }
					if (name.matches("exif:DigitalZoomRatio") == true) {
				//		summaryInfo.getCameraPropertiesInfo().digitalZoom = text;
					}
					// if (name.matches("exif:PixelXDimension") == true)
					// {
					// metaDataObject. = text;
					// }
					// if (name.matches("exif:PixelYDimension") == true)
					// {
					// metaDataObject.maker = text;
					// }
				}
			}

		}
	}

	void xmlnsIptc4xmpCore(final Node node) {
		if (node.hasChildNodes() == true) {
			final NodeList childNodes = node.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {

				final Node cnode = childNodes.item(j);
				final String name = cnode.getNodeName();
				final String text = cnode.getTextContent();
				if (cnode.getNodeType() == ELEMENT_NODE) {
					System.out.println(nodeType(cnode.getNodeType()) + " Name: " + cnode.getNodeName() + "Text content: " + text);
				}
			}

		}
	}

	void xmlnsTiff(final Node node) {
		if (node.hasChildNodes() == true) {
			final NodeList childNodes = node.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {

				final Node cnode = childNodes.item(j);

				if (cnode.getNodeType() == ELEMENT_NODE) {
					final String name = cnode.getNodeName();
					final String text = cnode.getTextContent();
					// System.out.println(nodeType(cnode.getNodeType()) +
					// " Name: " + cnode.getNodeName() + " Text content: " +
					// text);
					if (name.matches("tiff:Make") == true) {
				//		summaryInfo.getCameraPropertiesInfo().maker = text;
					}
					if (name.matches("tiff:Model") == true) {
				//		summaryInfo.getCameraPropertiesInfo().model = text;
					}
					if (name.matches("tiff:Orientation") == true) {
				//		summaryInfo.getMediaPropertiesInfo().orientation = text;
					}
					if (name.matches("tiff:ImageWidth") == true) {
				//		summaryInfo.getMediaPropertiesInfo().width = text;
					}
					if (name.matches("tiff:ImageLength") == true) {
				//		summaryInfo.getMediaPropertiesInfo().height = text;
					}

				}

			}

		}
	}

	void xmlnsXap(final Node node) {
		if (node.hasChildNodes() == true) {
			final NodeList childNodes = node.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {
				final Node cnode = childNodes.item(j);
				final String name = cnode.getNodeName();
				final String text = cnode.getTextContent();
				if (cnode.getNodeType() == ELEMENT_NODE) {
					// System.out.println(nodeType(cnode.getNodeType()) +
					// " Name: " + cnode.getNodeName() + " Text content: " +
					// text);
					if (name.matches("xap:ModifyDate Text") == true) {
				//		summaryInfo.getCameraPropertiesInfo().whiteBalance = text;
					}
					if (name.matches("xap:CreateDate") == true) {
				//		summaryInfo.getCameraPropertiesInfo().whiteBalance = text;
					}
					if (name.matches("xap:CreatorTool") == true) {
				//		summaryInfo.getCameraPropertiesInfo().whiteBalance = text;
					}
					if (name.matches("xap:Label") == true) {
				//		summaryInfo.getCameraPropertiesInfo().whiteBalance = text;
					}
					if (name.matches("xap:MetadataDate") == true) {
				//		summaryInfo.getCameraPropertiesInfo().whiteBalance = text;
					}
					if (name.matches("xap:Rating") == true) {
				//		summaryInfo.getCameraPropertiesInfo().whiteBalance = text;
					}

				}
			}

		}
	}
}
