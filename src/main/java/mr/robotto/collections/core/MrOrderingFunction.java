/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.collections.core;

/**
 * This interface is used to assign order to elements of type V.
 * Extends the @see MrGroupingFunction interface
 * The group value of the elements is Integer
 * @param <V>
 */
public interface MrOrderingFunction<V> extends MrGroupingFunction<Integer, V> {
}
