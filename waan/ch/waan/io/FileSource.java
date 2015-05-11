package ch.waan.io;

import java.io.FileInputStream;
import java.io.InputStream;

import ch.waan.util.Result;

final class FileSource extends AStreamSource {

	private final String	filename;

	FileSource(String filename) {
		this.filename = filename;
	}

	@Override
	protected Result<InputStream> getStream() {
		return Result.eval(() -> new FileInputStream(this.filename));
	}

}
