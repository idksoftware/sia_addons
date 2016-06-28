package idk.imgarchive.base.hooks;

import java.text.ParseException;

public class HookPoints_ {
	CommandList forEachClusterList = null;
	CommandList forEachImageList = null;
	CommandList forEachInstanceList = null;
	CommandList forEachPartitionList = null;

	void forEachCluster() {

	}

	void forEachImage() {

	}

	void forEachInstance() {

	}

	void forEachPartition() {

	}

	void loadScript(final String path, final String file) throws ParseException {
		final ReadScript readScript = new ReadScript(path, file);
		forEachImageList = readScript.getForEachImageList();
		forEachPartitionList = readScript.getForEachPartitionList();
		;
		forEachClusterList = readScript.getForEachClusterList();
		;
		forEachInstanceList = readScript.getForEachInstanceList();
		;
	}

}
