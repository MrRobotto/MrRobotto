/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.model.mesh.buffers;

import android.opengl.GLES20;

public enum MrBufferTarget
{
    ARRAY_BUFFER(GLES20.GL_ARRAY_BUFFER),
    ELEMENT_ARRAY_BUFFER(GLES20.GL_ELEMENT_ARRAY_BUFFER);

    private int mValue;

    MrBufferTarget(int value)
    {
        this.mValue = value;
    }

    public int getValue()
    {
        return mValue;
    }
}
