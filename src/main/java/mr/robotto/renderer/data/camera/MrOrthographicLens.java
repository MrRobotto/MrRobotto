/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.data.camera;

import mr.robotto.renderer.linearalgebra.MrMatrix4f;

public class MrOrthographicLens extends MrLens{
    private float orthographicScale;

    public MrOrthographicLens(float orthographicScale) {
        this.orthographicScale = orthographicScale;
    }

    @Override
    public MrMatrix4f getProjectionMatrix() {
        return null;
    }
}
