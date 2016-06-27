package idk.config;

public enum VersionType {

	SingleUser(0, "SingleUser"), MultiUser(1, "MultiUser"), Enterprise(3, "Enterprise");

	VersionType(final int value, final String str) {
		this.value = value;
		this.str = str;
	}

	VersionType match(final String s) {
		if (s.compareTo(SingleUser.getStr()) == 0) {
			return SingleUser;
		} else if (s.compareTo(MultiUser.getStr()) == 0) {
			return MultiUser;
		} else if (s.compareTo(MultiUser.getStr()) == 0) {
			return MultiUser;
		}
		return null;
	}

	/**
	 * @return the value
	 */
	public final int getValue() {
		return value;
	}

	/**
	 * @return the str
	 */
	public final String getStr() {
		return str;
	}

	@Override
	public String toString() {
		return str;
	}

	private int value = -1;
	private String str = null;

}
