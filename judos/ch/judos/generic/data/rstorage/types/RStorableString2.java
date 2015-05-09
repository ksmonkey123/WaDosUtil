package ch.judos.generic.data.rstorage.types;

import java.io.IOException;
import java.io.Writer;

import ch.judos.generic.data.StringUtils;
import ch.judos.generic.data.rstorage.helper.CheckReader2;
import ch.judos.generic.data.rstorage.interfaces.RStorableWrapper;
import ch.judos.generic.data.rstorage.interfaces.RStoreInternal;

/**
 * @since 26.04.2015
 * @author Julian Schelker
 */
public class RStorableString2 implements RStorableWrapper {

	private String	string;

	@Override
	public boolean isTrackedAsObject() {
		return true;
	}

	@Override
	public boolean showOnOneLine() {
		return true;
	}

	@Override
	public void read(CheckReader2 r, RStoreInternal storage) throws IOException {
		r.readMustMatch("\"");
		StringBuffer s = new StringBuffer();
		char c;
		boolean isEscaped = false;
		// check that last " is not escaped, before stopping the reading
		do {
			c = (char) r.read();

			if (!isEscaped && c == '\"')
				break;
			s.append(c);
			if (!isEscaped && c == '\\')
				isEscaped = true;
			else if (isEscaped)
				isEscaped = false;

		} while (true);

		this.string = decodeLine(s.toString());
	}

	@Override
	public void store(Writer w, RStoreInternal storage) throws IOException {
		w.write("\"");
		w.write(encodeLine(this.string));
		w.write("\"");
	}

	@Override
	public void initWrapped(Object o) {
		String os = (String) o;
		try {
			this.string = new String(os.toCharArray());
		}
		catch (NullPointerException e) {
			this.string = "";
		}
	}

	@Override
	public Object getWrapped() {
		return this.string;
	}

	public static String encodeLine(String s) {
		String[] s1 = {"\\", "\\\\"};
		String[] s2 = {"\"", "\\\""};
		s = StringUtils.replaceAll(s, s1, s2);
		return s;
	}

	public static String decodeLine(String s) {
		String[] s1 = {"\\\"", "\""};
		String[] s2 = {"\\\\", "\\"};
		s = StringUtils.replaceAll(s, s1, s2);
		return s;
	}
}
