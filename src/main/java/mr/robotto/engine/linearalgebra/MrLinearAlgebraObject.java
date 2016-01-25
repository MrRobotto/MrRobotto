/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.linearalgebra;

import mr.robotto.engine.commons.MrDataType;

/**
 * Basic interface for all mathematical objects
 */
public interface MrLinearAlgebraObject {

    /**
     * Gets the values contained
     * @return
     */
    float[] getValues();

    /**
     * Gets the number of instances of this type stored
     * @return
     */
    int getCount();

    /**
     * Returns the {@link MrDataType} of this object
     * @return
     */
    MrDataType getDataType();
}
