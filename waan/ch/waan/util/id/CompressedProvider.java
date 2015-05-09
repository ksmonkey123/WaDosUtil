/*
 * Copyright (C) 2014 Andreas Wälchli (andreas.waelchli@me.com)
 *
 * This file is part of AwaeUtil.
 *
 * AwaeUtil is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * AwaeUtil is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AwaeUtil. If not, see <http://www.gnu.org/licenses/>.
 *
 * Note: This Library is only compatible with Java 8 or newer.
 *  (developed under Java 1.8.0_25)
 */
package ch.waan.util.id;

/**
 * Empty Placeholder for the CompositeIDProvider Arrays
 *
 * @author Andreas Waelchli (andreas.waelchli@me.com)
 * @version 1.2, 2014-11-17
 * @since Util 1.0
 */
class CompressedProvider implements IDProvider {

    public static final CompressedProvider FALSE = new CompressedProvider(false);
    public static final CompressedProvider TRUE  = new CompressedProvider(true);

    private static void err() {
        throw new UnsupportedOperationException("Placeholder!");
    }

    private final boolean state;

    private CompressedProvider(boolean value) {
        this.state = value;
    }

    @Override
    public boolean free(int id) {
        if (this.state)
            CompressedProvider.err();
        return false;
    }

    @Override
    public boolean isEmpty() {
        return !this.state;
    }

    @Override
    public boolean isFull() {
        return this.state;
    }

    @Override
    public boolean isUsed(int id) {
        return this.state;
    }

    @Override
    public int get() {
        CompressedProvider.err();
        return 0;
    }

    @Override
    public boolean set(int id) {
        if (!this.state)
            CompressedProvider.err();
        return false;
    }

    @Override
    public long getFreeIDCount() {
        CompressedProvider.err();
        return 0;
    }

}
