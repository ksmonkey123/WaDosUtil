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
package ch.waan.util.concurrent;

import ch.waan.util.id.IDProvider;

/**
 * This IDProvider Implementation provides a synchronisation layer for the
 * IDProvider system. Using a provider that is wrapped into an instance of this
 * class can enforce completely synchronized operation. Any access to the
 * provider through this class will always be synchronized.
 *
 * @author Andreas Waelchli (andreas.waelchli@me.com)
 * @version 1.1, 2014-11-24
 * @since Util 1.0
 */
public class SynchronizedIDProvider implements IDProvider {

    private IDProvider backer;

    /**
     * Instantiate a new synchronized IDProvider based on the provided provider.
     *
     * @param backer
     *            the provider this instance should be backed by. This may not
     *            be {@code null}
     * @throws NullPointerException
     *             if the backer is {@code null}
     */
    public SynchronizedIDProvider(IDProvider backer) {
        if (backer == null)
            throw new NullPointerException("backer may not be null");
        this.backer = backer;
    }

    @Override
    public synchronized boolean isFull() {
        return this.backer.isFull();
    }

    @Override
    public synchronized boolean isUsed(int id) {
        return this.backer.isUsed(id);
    }

    @Override
    public synchronized boolean set(int id) {
        return this.backer.set(id);
    }

    @Override
    public synchronized boolean free(int id) {
        return this.backer.free(id);
    }

    @Override
    public synchronized int get() {
        return this.backer.get();
    }

    @Override
    public synchronized boolean isEmpty() {
        return this.backer.isEmpty();
    }

    @Override
    public synchronized long getFreeIDCount() {
        return this.backer.getFreeIDCount();
    }

}
