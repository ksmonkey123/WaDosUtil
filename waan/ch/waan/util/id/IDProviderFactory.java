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
 * This Factory is designed to provide {@link IDProvider} instances with a
 * desired range.
 *
 * @author Andreas Waelchli (andreas.waelchli@me.com)
 * @version 1.1, 2014-10-21
 * @since Util 1.0
 */
public class IDProviderFactory {

    /**
     * generates and returns an {@link IDProvider IDProvider} able to hold the
     * specified address length.<br>
     * For any value {@code n} of {@code size} the Provider will be able to hold
     * {@code 2^8n} addresses. The minimal size however is 8 bits (complete
     * {@code short} range) and the maximal size is 32 bit (complete {@code int}
     * range). Therefore the available Provider sizes are 8, 16, 24 and 32 bit.
     *
     * @param size
     *            the address size in bytes (must be 1, 2, 3 or 4). Any other
     *            value will throw an Exception.
     * @return the newly generated {@link IDProvider IDProvider} of the
     *         specified size
     * @throws IllegalArgumentException
     *             if the requested size is larger than 4 or smaller than 1
     */
    public static IDProvider getIDProvider(byte size) {
        return IDProviderFactory.getIDProvider(size, false);
    }

    static IDProvider getIDProvider(byte size, boolean preset) {
        switch (size) {
            case 1:
                return new BaseIDProvider(preset);
            case 2:
            case 3:
            case 4:
                return new LayeredIDProvider(size - 1, preset);
            default:
                throw new IllegalArgumentException(
                        "Provider size must be at least 1 and at most 4. "
                                + size + " is no valid size!");
        }
    }
}
