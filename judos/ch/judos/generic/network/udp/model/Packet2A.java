package ch.judos.generic.network.udp.model;

/**
 * @since 29.09.2013
 * @author Julian Schelker
 */
import java.net.InetSocketAddress;

public abstract class Packet2A extends Packet2Hash implements Comparable<Packet2A> {

	private long		resendOn;
	protected byte[]	sendData;
	/**
	 * type of message (0-255) <br>
	 * +128 = confirmation requested<br>
	 * 0 =
	 */
	protected int		type;

	// private Packet2A() {
	// super();
	// }

	public Packet2A(InetSocketAddress dest, int nr, long timeoutResend) {
		super(dest, nr);
		resendIn(timeoutResend);
	}

	/**
	 * order according to their resend time
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Packet2A p2) {
		if (p2 == null)
			throw new NullPointerException();
		int i = (int) (this.resendOn - p2.resendOn);
		if (i != 0)
			return i;

		return dest.hashCode() - p2.dest.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Packet2A))
			return super.equals(obj);

		Packet2A p2 = (Packet2A) obj;
		return super.equals(p2) && type == p2.type
			&& sendData.length == p2.sendData.length;
	}

	public int getNr() {
		return this.nr;
	}

	/**
	 * @return the resendOn
	 */
	public long getResendOn() {
		return resendOn;
	}

	/**
	 * @return the sendData
	 */
	public byte[] getSendData() {
		return sendData;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	protected void resendIn(long timeout) {
		this.resendOn = System.currentTimeMillis() + timeout;
	}

	public abstract void wasResentNow();

	public abstract boolean hasConnectionIssues();

	public abstract boolean needsConfirmation();

}