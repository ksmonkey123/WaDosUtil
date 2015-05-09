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
 * An ID Provider is a structure with the ability to mark numbers as used,
 * unused, check if a number is used and provide a new unused number with a
 * length of up to 32 bit. The implementation and the generated number sequence
 * do not have to satisfy any further conditions, however the data volume must
 * should be considered.
 * 
 * @author Andreas Waelchli (andreas.waelchli@me.com)
 * @version 1.1, 2014-10-21
 * @since Util 1.0
 */
public interface IDProvider {

    /**
     * indicates if the Provider has exhausted its ID range.
     * 
     * @return {@code true} if the Provider has no free IDs
     * @since Util 1.0
     */
    public boolean isFull();

    /**
     * indicates if the given ID is registered. A provider is allowed to only
     * respect selected bits of the {@code id}.
     * 
     * @param id
     *            the {@code id} to check for.
     * @return {@code true} if the given{@code id} is used, {@code false}
     *         otherwise.
     * @since Util 1.0
     */
    public boolean isUsed(int id);

    /**
     * mark the given ID as used.
     * 
     * @param id
     *            the {@code id} to register. A provider is allowed to only
     *            respect selected bits of the {@code id}.
     * @return {@code true} if the ID actually was set. This is used internally
     *         to optimize compression cycles.
     * @since Util 1.0
     */
    public boolean set(int id);

    /**
     * mark the given ID as unused.
     * 
     * @param id
     *            the {@code id} to unregister. A provider is allowed to only
     *            respect selected bits of the {@code id}.
     * @return {@code true} if the ID actually was freed. This is used
     *         internally to optimize compression cycles.
     * @since Util 1.0
     */
    public boolean free(int id);

    /**
     * mark an ID as used and return it. The Provider is free in its
     * implementation. and the generated {@code id} sequence does not have to
     * satisfy any further conditions.
     * 
     * @return the newly locked {@code id}
     * @since Util 1.0
     */
    public int get();

    /**
     * indicates if there's no ID used
     * 
     * @return {@code true} if no ID has been set.
     * @since Util 1.0
     */
    public boolean isEmpty();

    /**
     * retrieves the number of free IDs as an unsigned 32-bit integer.
     * 
     * @return the amount of free IDs
     * @since Util 1.0
     */
    public long getFreeIDCount();

    /**
     * extracts the local ID from an address
     * 
     * @param id
     *            the ID to extract from
     * @param layer
     *            the address layer to extract
     * @return the ID component associated with the given layer
     * @since Util 1.0
     */
    static short localAddress(int id, byte layer) {
        return (short) ((id >>> (8 * layer)) & 0xFF);
    }

}
