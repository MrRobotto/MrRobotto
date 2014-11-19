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

public enum MrBufferUsage
{
    STATIC_DRAW(GLES20.GL_STATIC_DRAW);

    private int mValue;

    MrBufferUsage(int value)
    {
        this.mValue = value;
    }

    public int getValue()
    {
        return mValue;
    }
}
