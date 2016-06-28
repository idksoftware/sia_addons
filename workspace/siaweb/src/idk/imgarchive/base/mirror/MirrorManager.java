package idk.imgarchive.base.mirror;

import java.util.ArrayList;

public class MirrorManager {

	public enum MirrorType {
		Disk, RemoteFtp, Unknown
	}

	protected static ArrayList<MirrorItem> diskMirrorList = new ArrayList<MirrorItem>();

	protected static ArrayList<MirrorItem> ftpMirrorList = new ArrayList<MirrorItem>();

	static protected String primaryMirrorPath = null;

	static MirrorManager This = null;

	static public MirrorManager create() {
		if (This == null) {
			This = new MirrorManager();
		}
		return This;
	}

	public static void setPrimaryMirrorPath(final String primaryMirrorPath) {
		MirrorManager.primaryMirrorPath = primaryMirrorPath;
	}

	private MirrorManager() {

	}

	public MirrorItem AddDiskMirror(final String path) {
		final MirrorItem mirrorItem = new MirrorItem();
		mirrorItem.mirrorType = MirrorType.Disk;
		mirrorItem.id = null;
		mirrorItem.path = path;
		diskMirrorList.add(mirrorItem);
		return mirrorItem;
	}

	public MirrorItem AddFtpMirror(final String id, final String path, final String userName, final String password,
			final String hostname) {
		final MirrorItem mirrorItem = new MirrorItem();
		mirrorItem.mirrorType = MirrorType.RemoteFtp;
		mirrorItem.id = null;
		mirrorItem.path = path;
		mirrorItem.userName = userName;
		mirrorItem.password = password;
		mirrorItem.hostname = hostname;
		ftpMirrorList.add(mirrorItem);
		return mirrorItem;
	}

	public MirrorItem getDiskMirrorListItem(final int i) {
		return diskMirrorList.get(i);
	}

	public MirrorItem getFtpMirrorList(final int i) {
		return ftpMirrorList.get(i);
	}

	public int getNumDiskMirrors() {
		return diskMirrorList.size();
	}

	public int getNumFtpMirrors() {
		return ftpMirrorList.size();
	}
}
