/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.linearalgebra;

import mr.robotto.commons.MrDataType;

public interface MrLinearAlgebraObject {
    public float[] getValues();
    public int getCount();
    public MrDataType getDataType();
}
