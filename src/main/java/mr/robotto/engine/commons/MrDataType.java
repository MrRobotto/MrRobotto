/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.commons;

import android.opengl.GLES20;

/**
 * Enum for all used types in the MrRobotto Engine
 * It contains the OpenGL mValue of this type, the mByteSize in bytes of this kind of element
 * and how many elements it contains
 */
public enum MrDataType
{
    UNSIGNED_SHORT(GLES20.GL_UNSIGNED_SHORT, MrConstants.SHORT_SIZE, 1),
    SHORT(GLES20.GL_SHORT, MrConstants.SHORT_SIZE, 1) ,
    FLOAT(GLES20.GL_FLOAT, MrConstants.FLOAT_SIZE, 1),
    INT(GLES20.GL_INT, MrConstants.INT_SIZE, 1),
    VEC2(GLES20.GL_FLOAT_VEC2, MrConstants.FLOAT_SIZE * 2, 2),
    VEC3(GLES20.GL_FLOAT_VEC3, MrConstants.FLOAT_SIZE * 3, 3),
    VEC4(GLES20.GL_FLOAT_VEC4, MrConstants.FLOAT_SIZE * 4, 4),
    MAT3(GLES20.GL_FLOAT_MAT3, MrConstants.FLOAT_SIZE * 9, 9),
    MAT4(GLES20.GL_FLOAT_MAT4, MrConstants.FLOAT_SIZE * 16, 16),
    QUATERNION(GLES20.GL_FLOAT_VEC4, MrConstants.FLOAT_SIZE * 4, 4),
    SAMPLER2D(GLES20.GL_SAMPLER_2D, 0, 1);

    private int mValue;
    private int mByteSize;
    private int mCount;

    MrDataType(int value, int byteSize, int count)
    {
        this.mValue = value;
        this.mByteSize = byteSize;
        this.mCount = count;
    }

    /**
     * Gets the OpenGL mValue of this type
     *
     * @return the OpenGL mValue for this type
     */
    public int getValue()
    {
        return mValue;
    }

    /**
     * Gets the mByteSize in bytes of this type
     * @return the mByteSize in bytes
     */
    public int getByteSize()
    {
        return mByteSize;
    }

    /**
     * Gets the number of elements it contains, for instance, a vec3 will return 3
     * and matrix4 will return 16
     * @return the number of elements this type will contain
     */
    public int getCount() {
        return mCount;
    }
}