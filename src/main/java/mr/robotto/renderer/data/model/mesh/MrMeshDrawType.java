/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.data.model.mesh;

import android.opengl.GLES20;

public enum MrMeshDrawType
{
    LINES(GLES20.GL_LINES), TRIANGLES(GLES20.GL_TRIANGLES);

    private int value;

    MrMeshDrawType(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}

