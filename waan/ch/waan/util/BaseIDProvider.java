/*
 * Copyright (C) 2014 Andreas Wälchli (andreas.waelchli@me.com)
 * 
 * This file is part of AwaeUtil.
 * 
 * AwaeUtil is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or any later version.
 * 
 * AwaeUtil is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * AwaeUtil. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Note: This Library is only compatible with Java 8 or newer. (developed under
 * Java 1.8.0_25)
 */
package ch.waan.util;

import java.util.function.Function;

/**
 * This is the bottom level implementation for the IDProvider system. it holds a
 * total of 256 addresses arranged in a 16-element 16-bit array.
 *
 * @author Andreas Waelchli (andreas.waelchli@me.com)
 * @version 1.1, 2014-10-21
 * @since Util 1.0
 */
class BaseIDProvider implements IDProvider {

	// use char as unsigned short
	private char[]								values;
	private short								usedCount;

	private static Function<Integer, Integer>	bitNum	= (id) -> id & 0x0F;
	private static Function<Integer, Integer>	arrInd	= (id) -> (id >>> 4) & 0x0F;

	BaseIDProvider(boolean defaultState) {
		this.values = new char[16];
		if (defaultState) {
			for (int i = 0; i < this.values.length; i++)
				this.values[i] = 0xFFFF;
			this.usedCount = 256;
		}
	}

	@Override
	public boolean isFull() {
		return this.usedCount == 256;
	}

	@Override
	public boolean isUsed(int ID) {
		int id = IDProvider.localAddress(ID, (byte) 0);
		if (this.isFull())
			return true;
		return (this.values[BaseIDProvider.arrInd.apply(id)] & (0x1 << BaseIDProvider.bitNum.apply(id))) != 0;
	}

	@Override
	public boolean set(int ID) {
		int id = IDProvider.localAddress(ID, (byte) 0);
		if (this.isFull() || this.isUsed(id))
			return false;
		this.values[BaseIDProvider.arrInd.apply(id)] |= (0x1 << BaseIDProvider.bitNum.apply(id));
		this.usedCount++;
		return true;
	}

	@Override
	public boolean free(int ID) {
		int id = IDProvider.localAddress(ID, (byte) 0);
		if (this.isEmpty() || !this.isUsed(id))
			return false;
		this.values[BaseIDProvider.arrInd.apply(id)] ^= (0x1 << BaseIDProvider.bitNum.apply(id));
		this.usedCount--;
		return true;
	}

	@Override
	public int get() {
		if (this.isFull())
			throw new IllegalStateException("Cannot select ID. no IDs left!");
		int index;
		int val = 0;
		for (index = 0; index < 16; index++) {
			if (this.values[index] != 0xFFFF) {
				val = this.values[index];
				break;
			}
		}
		int bitIndex;
		for (bitIndex = 0; bitIndex < 16; bitIndex++) {
			if (((val >>> bitIndex) & 1) == 0)
				break;
		}
		int id = (index << 4) | bitIndex;
		this.set(id);
		return id;
	}

	@Override
	public boolean isEmpty() {

		return this.usedCount == 0;
	}

	@Override
	public long getFreeIDCount() {
		return 256 - this.usedCount;
	}

}
