package ch.judos.generic.network.udp.model;

import ch.judos.generic.data.Serializer;
import ch.judos.generic.data.format.ByteData;

/**
 * @since 11.07.2013
 * @author Julian Schelker
 */
public class BigPacketSend {

	public static final int	CONTROL_DATA_BYTES	= 6;

	private byte[]			data;
	private short			id;
	private int				packetDataLimit;
	private short			parts;

	public BigPacketSend(short id, byte[] data, int maxPacketSize) {
		int sizeData = maxPacketSize - CONTROL_DATA_BYTES;
		if (data.length > Short.MAX_VALUE * sizeData)
			throw new RuntimeException("Max size of "
				+ ByteData.autoFormat(Short.MAX_VALUE * sizeData)
				+ " for a big packet exceeded.");

		this.data = data;
		this.packetDataLimit = sizeData;
		this.id = id;
		this.parts = (short) (data.length / sizeData);
		if (data.length - this.parts * sizeData > 0)
			this.parts++;

	}

	public byte[][] getPackets() {
		byte[][] packets = new byte[this.parts][];
		for (short i = 0; i < parts; i++) {
			int dataStartPos = i * packetDataLimit;
			int dataLength = packetDataLimit;
			if (dataStartPos + dataLength > data.length)
				dataLength = data.length - dataStartPos;

			byte[] packetData = new byte[CONTROL_DATA_BYTES + dataLength];
			Serializer.short2bytes(packetData, 0, id);
			Serializer.short2bytes(packetData, 2, i);
			Serializer.short2bytes(packetData, 4, parts);
			System.arraycopy(data, dataStartPos, packetData, CONTROL_DATA_BYTES,
				dataLength);
			packets[i] = packetData;
		}
		return packets;
	}
}
