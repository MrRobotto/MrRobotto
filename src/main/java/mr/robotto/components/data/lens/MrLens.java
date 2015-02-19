/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.lens;

import mr.robotto.linearalgebra.MrMatrix4f;

public abstract class MrLens {
    //private float dof;
    //private float pixelWidth;
    //private float pixelHeight;
    protected MrMatrix4f matrix;

    public abstract MrMatrix4f getProjectionMatrix();
}
