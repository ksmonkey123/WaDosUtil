package ch.judos.generic.data.parse;

/**
 * @since 02.07.2013
 * @author Julian Schelker
 * @version 1.0 / 02.07.2013
 */
public class ComposedFormat extends Format {

	/**
	 * stores the individual formats
	 */
	protected Format[]	parts;

	/**
	 * @param parts
	 */
	public ComposedFormat(Object... parts) {
		init(parts);
	}

	/**
	 */
	protected ComposedFormat() {
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see ch.judos.generic.data.parse.Format#cleanup()
	 */
	@Override
	public void cleanup() {
		for (Format p : this.parts)
			p.cleanup();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see ch.judos.generic.data.parse.Format#matchAndConsume(java.lang.String)
	 */
	@Override
	public String matchAndConsume(String input) {
		for (Format p : this.parts) {
			input = p.matchAndConsume(input);
			if (input == null)
				return null;
		}
		this.set = true;
		return input;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see ch.judos.generic.data.parse.Format#removeData()
	 */
	@Override
	public void removeData() {
		this.set = false;
		for (Format p : this.parts)
			p.removeData();
	}

	/**
	 * @return the array with all data inside
	 */
	@Override
	protected Object getObject() {
		Object[] data = new Object[this.parts.length];
		int index = 0;
		for (Format p : this.parts)
			data[index++] = p.get();
		return data;
	}

	/**
	 * create a format by its parts
	 * 
	 * @param parts
	 */
	protected void init(Format... parts) {
		this.parts = parts;
	}

	/**
	 * allows any object as formatParts.<br>
	 * Objects that don't extend FormatPart will be interpretated as
	 * ConstantFP's with value of their toString() method
	 * 
	 * @param parts
	 */
	protected void init(Object... parts) {
		this.parts = new Format[parts.length];
		int index = 0;
		for (Object o : parts) {
			if (!(o instanceof Format))
				this.parts[index++] = new ConstantFormat(o.toString());
			else
				this.parts[index++] = (Format) o;
		}
	}

}
