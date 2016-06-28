package idk.imgarchive.base.system;

import idk.archiveutils.FileUtils;
import idk.config.ConfigInfo;
import idk.imgarchive.base.workspacemanager.WorkspaceInstancesReader;
import idk.imgarchive.base.workspacemanager.WorkspaceReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;

import xmlutils.XMLHelper;

public class ManageInstances {
	static final String DEFAULT_DATA_DIR_NAME = "repository";
	public static final String ENCODING_UTF8 = "UTF-8";

	public static boolean createClusterObject(final String path) throws IOException {

		final File pathFolder = new File(path);
		if (pathFolder.isDirectory() != false) {
			return false;
		}
		if (pathFolder.isAbsolute() == false) {
			return false;
		}

		if (FileUtils.makePath(path) == false) {
			return false;
		}
		final String dataPath = path + File.separator + "DCIM";
		if (FileUtils.makePath(dataPath) == false) {
			return false;
		}
		final String wwwPath = dataPath + File.separator + "www";
		if (FileUtils.makePath(wwwPath) == false) {
			return false;
		}
		if (FileUtils.makePath(wwwPath + File.separator + "html") == false) {
			return false;
		}
		if (FileUtils.makePath(wwwPath + File.separator + "css") == false) {
			return false;
		}
		return true;
	}

	static public boolean createDefault(final String configPath, final String name, final String instancePath,
			final String clusterPath, final String id, final long size, final int imagesPerPartition) throws IOException,
			NoSuchAlgorithmException {
		if (createInstanceObject(instancePath) == false) {
			return false;
		}
		if (createClusterObject(clusterPath) == false) {
			return false;
		}
		WorkspaceReader.writeDefault(configPath, name, instancePath, id, size, imagesPerPartition, clusterPath);
		WorkspaceInstancesReader.add(name);
		WorkspaceInstancesReader.setDefaultInstance(name);
		WorkspaceInstancesReader.writeXmlFile(configPath);
		writeNewInstancesFile(configPath, name);
		return true;
	}

	public static boolean createInstanceObject(final String path) throws IOException {

		final File pathFolder = new File(path);
		if (pathFolder.exists() == true) {
			return false;
		}

		if (pathFolder.isAbsolute() == false) {
			return false;
		}

		if (FileUtils.makePath(path) == false) {
			return false;
		}
		final String systemPath = path + File.separator + "system";
		if (FileUtils.makePath(systemPath) == false) {
			return false;
		}

		final File createSequenceFile = new File(systemPath, "00000000@00000000.000.sys");
		createSequenceFile.createNewFile();

		if (FileUtils.makePath(path + File.separator + "www") == false) {
			return false;
		}
		return true;
	}

	/*
	 * <?xml version="1.0" encoding="ISO-8859-1" ?> - <WorkSpaces>
	 * <Default>MyRepos</Default> - <Instances> <Item>Photos</Item>
	 * <Item>MyRepos</Item> </Instances> </WorkSpaces>
	 */
	static void writeNewInstancesFile(final String configPath, final String name) throws IOException {
		// log("Writing to file named " + fFileName + ". Encoding: " +
		// fEncoding);
		final Writer out = new OutputStreamWriter(
				new FileOutputStream(configPath + File.separator + ConfigInfo.INSTANCES_FILE_NAME), XMLHelper.ENCODING_UTF8);
		try {
			out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\r\n");
			out.write("<WorkSpace>" + "\r\n");
			out.write("<Default>" + name + "</Default>" + "\r\n");
			out.write("<Instances>" + "\r\n");
			out.write("<Item>" + name + "</Item>" + "\r\n");
			out.write("</Instances>" + "\r\n");
			out.write("</WorkSpace>" + "\r\n");
		} finally {
			out.close();
		}
	}

	/*
	 * <?xml version="1.0" encoding="ISO-8859-1" ?> - <WorkSpace>
	 * <System>c:/iarepos</System> - <Media> - <Cluster>
	 * <Identifier>001</Identifier> <SizeMb>15000000</SizeMb>
	 * <ImagesPerPartition>100</ImagesPerPartition>
	 * <FirstPartitionId>0</FirstPartitionId> <Path>c:/iarepos</Path> </Cluster>
	 * </Media> </WorkSpace>
	 */
	/*
	 * static void writeInstance(String configPath, String instanceName, String
	 * instancePath, String clusterPath) throws IOException {
	 * //log("Writing to file named " + fFileName + ". Encoding: " + fEncoding);
	 * Writer out = new OutputStreamWriter(new FileOutputStream(configPath +
	 * File.separator + instanceName + ".xml"), ENCODING_UTF8); try {
	 * out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\r\n");
	 * out.write("<WorkSpace>" + "\r\n"); out.write("<System>" + "\r\n");
	 * out.write(String.format("<Path>%s", instancePath) + File.separator +
	 * String.format("%s", ConfigInfo.SYSTEM_DIR_NAME) + "</Path>" + "\r\n");
	 * out.write("</System>" + "\r\n"); out.write("<Media>" + "\r\n");
	 * out.write("<Cluster>" + "\r\n");
	 * out.write("<Identifier>Drive1</Identifier>" + "\r\n");
	 * out.write("<SizeMb>1500000</SizeMb>" + "\r\n");
	 * out.write("<ImagesPerPartition>5</ImagesPerPartition>" + "\r\n");
	 * out.write("<FirstPartitionId>0</FirstPartitionId>" + "\r\n");
	 * out.write(String.format("<Path>%s", clusterPath) + "</Path>" + "\r\n");
	 * out.write("</Cluster>" + "\r\n"); out.write("</Media>" + "\r\n");
	 * out.write("</WorkSpace>" + "\r\n"); } finally { out.close(); } }
	 */
}
