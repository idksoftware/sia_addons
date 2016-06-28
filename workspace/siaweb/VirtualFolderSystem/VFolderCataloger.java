package idk.imgarchive.base.VirtualFolderSystem;

import idk.archiveutils.FileUtils;
import idk.imgarchive.base.hooks.Css;
import idk.imgarchive.base.hooks.XmlHtml;
import idk.imgarchive.base.log4j.Log;
import idk.imgarchive.base.workspacemanager.ImageAddress;
import idk.imgarchive.base.workspacemanager.ImageCursor;
import idk.imgarchive.base.workspacemanager.PartitionAddress;
import idk.imgarchive.base.workspacemanager.Workspace;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.w3c.dom.Document;

import xmlutils.XMLUtil;
import xmlutils.XMLWriteHelper;

public class VFolderCataloger extends FolderCatalogerBase {

	@SuppressWarnings("serial")
	static class WWWPath extends ArrayList<WWWLink> {
		void pop() {
			final int size = size();
			if (size > 0) {
				remove(size - 1);
			}
		}

		void push(final WWWLink folder) {
			add(folder);
		}
	}

	WWWPath currentPath = new WWWPath();
	VirtualFolder currVF = null;
	String xmlPath;
	String htmlPath;
	String cssPath;

	int level = 0;

	VirtualFolder parentVF = null;
	String primaryName = null;
	String rootPath;
	String wwwRoot;

	String secondaryName = null;

	public VFolderCataloger(final String primaryName, final String secondaryName, final String wwwRoot, final String rootPath,
			final String xmlPath, final String htmlPath, final String cssPath) {
		this.primaryName = primaryName;
		this.secondaryName = secondaryName;
		this.rootPath = rootPath;
		this.xmlPath = xmlPath;
		this.htmlPath = htmlPath;
		this.cssPath = cssPath;
		this.wwwRoot = wwwRoot;
	}

	public boolean readIndex(final String filename) throws IOException, ParseException {

		final String htmlFilePath = htmlPath + File.separator + primaryName + File.separator + secondaryName + File.separator
				+ "html";
		final String cssFilePath = htmlPath + File.separator + primaryName + File.separator + secondaryName + File.separator
				+ "css";

		FileUtils.makePath(htmlFilePath);
		FileUtils.makePath(cssFilePath);

		XmlHtml.makeHtml("foldercatalog.xsl", xmlPath + File.separator + filename + ".xml", htmlPath + File.separator + filename
				+ ".html");

		return Css.Copy("AuthorIndex.css", cssPath, "index.css");
	}

	@Override
	public void visitNode(final VFSystemNode n) throws ParseException, IOException {
		if (n instanceof VFFolderNode) {
			final VFFolderNode node = (VFFolderNode) n;
			for (int i = 0; i < level; i++) {
				System.out.print(" ");
			}
			String name = node.getDirectory().getName();
			name = name.matches("root") ? "Home" : name;
			currentPath.push(new WWWLink(name, FileUtils.stripFileExtension(node.getDirectory().getFilename())));
			System.out.println("<Folder>" + node.getDirectory().getName());
			level++;

			final ArrayList<WWWLink> pageList = writePages(node.getDirectory(), 10);

			final VFSystemNode[] list = node.getChildren();
			for (final VFSystemNode c : list) {
				c.visit(this);
				/*
				 * if (c instanceof VFFolderNode) { try {
				 * writePages(((VFFolderNode) c).getDirectory(), 10); } catch
				 * (final IOException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); }
				 * 
				 * } else if (c instanceof VFImageNode) {
				 * 
				 * } else { final Class<? extends VFSystemNode> cls =
				 * c.getClass(); throw new RuntimeException("Invalid type " +
				 * cls.getName()); }
				 */
			}

			final FolderCatalogerHeader folderCatalogerHeader = new FolderCatalogerHeader(currentPath, xmlPath,
					node.getDirectory(), pageList);
			folderCatalogerHeader.writeHeader();
			System.out.println("</Folder>");
			currentPath.pop();
			level--;
		} else if (n instanceof VFImageNode) {
			final VFImageNode node = (VFImageNode) n;
			for (int i = 0; i < level; i++) {
				System.out.print(" ");
			}
			System.out.println("<Img>" + node.getImage() + "</Img>");
		}

	}

	public void visitFileNode(final VFSystemNode n) {

	}

	/*
	 * This will hold the folder infomation and the image pages assocated with
	 * this folder.
	 */

	/*
	 * public void writeHeader(VirtualFolder vf) {
	 * 
	 * final String name = vf.filename.substring(0, vf.filename.indexOf("."));
	 * final XmlWriter xmlWriter = new XmlWriter(xmlPath, name + "@header.xml");
	 * xmlWriter.startElement("VirtualFolder"); xmlWriter.startElement("Name");
	 * xmlWriter.element(vf.name); xmlWriter.endElement("Name");
	 * xmlWriter.startElement("Filename"); xmlWriter.element(vf.filename);
	 * xmlWriter.endElement("Filename"); xmlWriter.startElement("LastModified");
	 * String dateString = DateUtils.formatDDMMYYYY(vf.getLastModified());
	 * xmlWriter.element(dateString); xmlWriter.endElement("LastModified");
	 * xmlWriter.startElement("Created"); dateString =
	 * DateUtils.formatDDMMYYYY(vf.getCreatedDate());
	 * xmlWriter.element(dateString); xmlWriter.endElement("Created");
	 * 
	 * // Parent folder List xmlWriter.startElement("ParentFolderList"); for
	 * (final String item : currentPath) { xmlWriter.startElement("Folder");
	 * xmlWriter.element(item); xmlWriter.endElement("Folder"); }
	 * xmlWriter.endElement("ParentFolderList"); // Folder List
	 * xmlWriter.startElement("FolderList"); final ArrayList<VirtualFolderInfo>
	 * folderList = vf.getFolderList(); for (final VirtualFolderInfo item :
	 * folderList) { xmlWriter.startElement("Folder");
	 * xmlWriter.startElement("Name"); xmlWriter.element(item.name);
	 * xmlWriter.endElement("Name"); xmlWriter.startElement("Filename");
	 * xmlWriter.element(item.filename); xmlWriter.endElement("Filename");
	 * xmlWriter.endElement("Folder"); } xmlWriter.endElement("FolderList");
	 * 
	 * // Image List xmlWriter.startElement("PageList"); for (final String page
	 * : pageFileList) { xmlWriter.startElement("Name");
	 * xmlWriter.element(page); xmlWriter.endElement("Name"); }
	 * xmlWriter.endElement("PageList"); xmlWriter.endElement("VirtualFolder");
	 * xmlWriter.close(); }
	 */
	public ArrayList<WWWLink> writePages(final VirtualFolder vf, final int imagesPerPage) throws IOException, ParseException {
		// Image List
		final ArrayList<WWWLink> pageList = new ArrayList<WWWLink>();

		final ArrayList<String> imageList = vf.getImageList();

		final Document indexDoc = creatIndexFile();
		int noOfPages = imageList.size() / imagesPerPage;
		noOfPages = imageList.size() % imagesPerPage != 0 ? noOfPages + 1 : noOfPages;
		final ImageCursor imageCursor = Workspace.createImageCursor();

		int imageCount = 0;
		int pageCount = 0;

		String name = vf.filename.substring(0, vf.filename.indexOf("."));
		Integer pageIdx = pageCount + 1;
		String fileIdx = name + "@" + pageIdx.toString();
		Document catDoc = creatCatalogFile();
		Document imageDocument = null;

		for (final String item : imageList) {

			imageCursor.absolute(ImageAddress.makeLong(item));

			final ImageAddress imageAddress = imageCursor.getCurrent();
			final File partitionPath = new File(imageAddress.getPath()).getParentFile();

			final String xmlFilePath = wwwRoot + File.separator
					+ ImageAddress.makeAddressString(imageAddress.getCurrentPartition()) + File.separator + "www" + File.separator
					+ "xml" + File.separator + PartitionAddress.makeAddressString(imageAddress.getCurrent()) + ".xml";
			try {
				imageDocument = XMLUtil.parse(xmlFilePath);
			} catch (final IOException e2) {
				Log.IOException(xmlFilePath, e2);
			} catch (final ParseException e2) {
				Log.ParseException(xmlFilePath, e2);
			}

			appendCatalogFile(catDoc, imageDocument);
			if (imageCount == imagesPerPage) {
				// Complete last page
				indexElem(catDoc, pageCount, noOfPages, xmlPath, name);
				indexTag(indexDoc, pageCount, null);
				XMLWriteHelper.writeXmlFile(catDoc, xmlPath + File.separator + fileIdx + ".xml");
				pageList.add(new WWWLink(pageIdx.toString(), fileIdx));
				// start new page
				name = vf.filename.substring(0, vf.filename.indexOf("."));
				pageCount++;
				pageIdx = pageCount + 1;
				fileIdx = name + "@" + pageIdx.toString();
				catDoc = creatCatalogFile();
				imageCount = 0;

			}
			imageCount++;
		}
		// Complete remaining page
		pageIdx = pageCount + 1;
		indexElem(catDoc, pageCount, noOfPages, xmlPath, name);
		indexTag(indexDoc, pageCount, null);
		XMLWriteHelper.writeXmlFile(catDoc, xmlPath + File.separator + fileIdx + ".xml");
		pageList.add(new WWWLink(pageIdx.toString(), fileIdx));
		// Write the index file
		XMLWriteHelper.writeXmlFile(indexDoc, xmlPath + File.separator + name + "@index.xml");

		/*
		 * for (int i = 0; i <= pageCount; i++) {
		 * 
		 * name = vf.filename.substring(0, vf.filename.indexOf(".")); pageIdx =
		 * i + 1; fileIdx = name + "@" + pageIdx.toString() + ".xml";
		 * readIndex(fileIdx);
		 * 
		 * }
		 */
		return pageList;
	}

	@Override
	public boolean createItemPages(final String mainTag, final String secondaryTag, final String path, final int idx,
			final int imagesPerPage) throws IOException, ParseException {
		// TODO Auto-generated method stub
		return false;
	}
}
