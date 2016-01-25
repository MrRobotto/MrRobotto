/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.lens;

import mr.robotto.engine.linearalgebra.MrMatrix4f;

public class MrOrthographicLens extends MrLens {
    private float mOrthographicScale;

    public MrOrthographicLens(float orthographicScale) {
        mOrthographicScale = orthographicScale;
    }

    @Override
    public MrMatrix4f getProjectionMatrix() {
        return null;
    }
}
