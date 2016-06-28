package idk.imgarchive.base.hooks;

import idk.archiveutils.MathParser;

import java.util.ArrayList;

public class SubstitutionProcessor {

	static enum SubType {
		String, Expression, Unknown
	};

	final static String TOOL_PATH = "ToolPath";
	final static String SOURCE_PATH = "SourcePath";
	final static String DESTINATION_PATH = "DestinationPath";
	final static String IMAGE_NAME = "ImageName";
	final static String ARCHIVE_NAME = "ArchiveName";
	final static String IMAGE_EXTENTION = "ImageExt";

	final static String WIDTH = "Width";
	final static String HEIGHT = "Height";
	final static String QUALITY = "Quality";

	class SubsValue {
		SubType subType = SubType.Unknown;
		String token = null;
		String value = null;

		SubsValue(final SubType subType, final String token, final String value) {
			this.subType = subType;
			this.token = token;
			this.value = value;
		}
	}

	ArrayList<SubsValue> subsValueList = new ArrayList<SubsValue>();

	public SubstitutionProcessor(final PresetVariables presetVariables) {
		subsValueList.add(new SubsValue(SubType.String, TOOL_PATH, presetVariables.getToolPath()));
		subsValueList.add(new SubsValue(SubType.String, SOURCE_PATH, presetVariables.getSourcePath()));
		subsValueList.add(new SubsValue(SubType.String, DESTINATION_PATH, presetVariables.getDestinationPath()));
		subsValueList.add(new SubsValue(SubType.String, IMAGE_NAME, presetVariables.getCurrentImageName()));
		subsValueList.add(new SubsValue(SubType.String, ARCHIVE_NAME, presetVariables.getCurrentArchiveName()));
		subsValueList.add(new SubsValue(SubType.String, IMAGE_EXTENTION, presetVariables.getCurrentImageExtention()));
		subsValueList.add(new SubsValue(SubType.String, WIDTH, Integer.toString(presetVariables.getWidth())));
		subsValueList.add(new SubsValue(SubType.String, HEIGHT, Integer.toString(presetVariables.getHeight())));
		subsValueList.add(new SubsValue(SubType.String, QUALITY, Integer.toString(presetVariables.getQuality())));
	}

	public void add(final SubType subType, final String token, final String value) {
		subsValueList.add(new SubsValue(subType, token, value));
	}

	public String processString(String line) {

		String newLine = null;
		int s = 0;
		int e = 0;
		do {
			s = line.indexOf("$(", s);
			if (s == -1) {
				if (newLine == null) {
					newLine = line;
				}
				break;
			}
			e = line.indexOf(")", e);
			final String sa = line.substring(s, e + 1);
			// System.out.println(sa);
			final String subs = lookup(sa);
			newLine = line.replace(sa, subs);
			line = newLine;
			// System.out.println(newLine);
			s = s + 1;
			e = e + 1;
		} while (true);

		return newLine;
	}

	String lookup(final String sa) {
		final String match = sa.substring(2, sa.length() - 1);
		for (final SubsValue value : subsValueList) {
			if (value.token.matches(match)) {
				String str = processString(value.value);
				if (value.subType == SubType.Expression) {
					str = Integer.toString(MathParser.processEquation(str));
				}
				return str;
			}
		}
		return null;
	}
}
