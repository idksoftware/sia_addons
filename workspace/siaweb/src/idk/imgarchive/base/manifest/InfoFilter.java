package idk.imgarchive.base.manifest;

public abstract class InfoFilter {
	public abstract boolean accept(Object s);

	public abstract String getDescription();
}
