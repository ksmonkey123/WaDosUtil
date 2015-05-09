/*
 * AwaeGameAPI - easy to use 2D game API
 * Copyright (C) 2015 Andreas Waelchli
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.waan.game.util;

import java.util.function.BooleanSupplier;

import org.eclipse.jdt.annotation.NonNull;

/**
 * An adapter object designed for detecting raising edges on boolean values to
 * allow for simpler client handling.
 * 
 * @author Andreas Waelchli
 * @version 1.1, 2015-02-28
 * @since AwaeGameAPI 0.2
 */
public final class BooleanEdge {

	private final @NonNull BooleanSupplier expression;

	private boolean lastValue = false;

	/**
	 * creates a new edge adapter for the given expression.
	 * 
	 * @param expression
	 *            the expression to observe
	 */
	public BooleanEdge(@NonNull BooleanSupplier expression) {
		this.expression = expression;
	}

	/**
	 * Updates the edge adapter
	 * 
	 * @return {@code true} if a raising edge was detected. {@code false}
	 *         otherwise.
	 * @throws RuntimeException
	 *             if the observed expression yields an exception
	 */
	public boolean update() {
		try {
			if (this.lastValue != this.expression.getAsBoolean()) {
				this.lastValue = !this.lastValue;
				return this.lastValue;
			}
			return false;
		} catch (RuntimeException ex) {
			throw new RuntimeException(ex);
		}
	}

}
