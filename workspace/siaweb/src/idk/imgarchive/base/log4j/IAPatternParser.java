package idk.imgarchive.base.log4j;

import org.apache.log4j.helpers.FormattingInfo;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;

public class IAPatternParser extends PatternParser {

	private class UserDirPatternConverter extends PatternConverter {
		UserDirPatternConverter(final FormattingInfo formattingInfo) {
			super(formattingInfo);
		}

		@Override
		public String convert(final LoggingEvent event) {
			return String.valueOf(++counter);
		}
	}

	int counter = 0;

	public IAPatternParser(final String pattern) {
		super(pattern);
	}

	@Override
	public void finalizeConverter(final char c) {
		if (c == '#') {
			addConverter(new UserDirPatternConverter(formattingInfo));
			currentLiteral.setLength(0);
		} else {
			super.finalizeConverter(c);
		}
	}
}
