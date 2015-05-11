package ch.waan.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.waan.util.Result;

abstract class AStreamSource extends Source {

	@Override
	public Result<String> mkString() {

		return this.getStream()
				.map((stream) ->
					{
						try (BufferedReader in = new BufferedReader(
								new InputStreamReader(stream))) {
							StringBuilder sb = new StringBuilder();
							String inputLine;
							while ((inputLine = in.readLine()) != null) {
								sb.append(inputLine);
								sb.append('\n');
							}
							return sb.toString();
						}
					});
	}

	protected abstract Result<InputStream> getStream();

}
