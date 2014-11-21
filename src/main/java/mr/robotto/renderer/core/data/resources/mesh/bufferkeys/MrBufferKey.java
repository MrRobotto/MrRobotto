/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.resources.mesh.bufferkeys;

import mr.robotto.renderer.commons.MrDataType;
import mr.robotto.renderer.core.data.resources.shaders.MrAttributeType;

public class MrBufferKey
{
    private MrAttributeType mAttributeType;
    private String mName;
    private int mIndex;
    private int mSize;
    private MrDataType mDataType;
    private int mStride;
    private int mPointer;

    //TODO: I believe that this class doesn't need a name
    public MrBufferKey(MrAttributeType attribute, String name, int index, MrDataType dataType, int size, int stride, int pointer)
    {
        this.mAttributeType = attribute;
        this.mName = name;
        this.mIndex = index;
        this.mDataType = dataType;
        this.mSize = size;
        this.mStride = stride * dataType.getSize();
        this.mPointer = pointer * dataType.getSize();
    }

    public MrAttributeType getAttributeType()
    {
        return mAttributeType;
    }

    public int getSize()
    {
        return mSize;
    }

    public int getStride()
    {
        return this.mStride;
    }

    public int getIndex()
    {
        return mIndex;
    }

    public int getPointer()
    {
        return mPointer;
    }

    public MrDataType getDataType()
    {
        return mDataType;
    }

    @Override
    public String toString()
    {
        return "MrBufferKey{" +
                "AttributeType=" + mAttributeType +
                ", Name='" + mName + '\'' +
                ", Index=" + mIndex +
                ", Size=" + mSize +
                ", BufferDataType=" + mDataType +
                ", Stride=" + mStride +
                ", Pointer=" + mPointer +
                '}';
    }
}
