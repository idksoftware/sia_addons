package idk.imgarchive.base.manifest;

import java.util.Date;

public class PartitionInfo {
	private final String errorMsg = null;
	private String id = null;
	private final boolean isError = false;
	private Date lastModified = null;
	private long size = 0;

	public PartitionInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public String getId() {
		return id;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public long getSize() {
		return size;
	}

	public boolean isError() {
		return isError;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public void setLastModified(final Date lastModified) {
		this.lastModified = lastModified;
	}

	public void setSize(final long size) {
		this.size = size;
	}
}
