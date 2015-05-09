package ch.judos.generic.data.rstorage.tests.objects;

import java.io.IOException;
import java.io.Writer;

import ch.judos.generic.data.rstorage.helper.CheckReader2;
import ch.judos.generic.data.rstorage.interfaces.RStorableManual2;
import ch.judos.generic.data.rstorage.interfaces.RStoreInternal;

/**
 * @since 06.05.2015
 * @author Julian Schelker
 */
public class A implements RStorableManual2 {

	@Override
	public boolean isTrackedAsObject() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean showOnOneLine() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void read(CheckReader2 r, RStoreInternal storage) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void store(Writer w, RStoreInternal storage) throws IOException {
		// TODO Auto-generated method stub

	}

}
