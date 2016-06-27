package idk.imgarchive.base.log4j;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;

public class IAPatternLayout extends PatternLayout {
	public IAPatternLayout() {
		this(DEFAULT_CONVERSION_PATTERN);
	}

	public IAPatternLayout(final String pattern) {
		super(pattern);
	}

	@Override
	public PatternParser createPatternParser(final String pattern) {
		return new IAPatternParser(pattern == null ? DEFAULT_CONVERSION_PATTERN : pattern);
	}

}
