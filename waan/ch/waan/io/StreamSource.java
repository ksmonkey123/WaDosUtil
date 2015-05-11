package ch.waan.io;

import java.io.InputStream;

import ch.waan.util.Result;

class StreamSource extends AStreamSource {

	private final InputStream	stream;

	StreamSource(InputStream stream) {
		this.stream = stream;
	}

	@Override
	protected Result<InputStream> getStream() {
		return Result.of(this.stream);
	}

}
