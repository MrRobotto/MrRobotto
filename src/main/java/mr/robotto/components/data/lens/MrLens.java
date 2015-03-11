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
    protected float mWidth = -1;
    protected float mHeight = -1;
    protected MrMatrix4f mProjectionMatrix = new MrMatrix4f();

    public void setDimension(float width, float height) {
        mWidth = width;
        mHeight = height;
    }

    public float getAspectRatio() {
        return mWidth/mHeight;
    }

    public float getWidth() {
        return mWidth;
    }

    public float getHeight() {
        return mHeight;
    }

    public abstract MrMatrix4f getProjectionMatrix();
}
