package ch.waan.io;

import java.io.InputStream;
import java.net.URL;

import ch.waan.util.Result;

final class URLSource extends AStreamSource {

	private final String	url;

	URLSource(String url) {
		this.url = url;
	}

	@Override
	protected Result<InputStream> getStream() {
		return Result.of(this.url)
				.map(URL::new)
				.map(URL::openStream);
	}
}
