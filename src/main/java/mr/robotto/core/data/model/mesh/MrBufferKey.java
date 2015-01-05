/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data.model.mesh;

import mr.robotto.commons.MrDataType;
import mr.robotto.core.data.types.MrAttributeType;

public class MrBufferKey {
    private MrAttributeType mAttributeType;
    private int mIndex;
    private int mSize;
    private MrDataType mDataType;
    private int mStride;
    private int mPointer;

    public MrBufferKey(MrAttributeType attribute, MrDataType dataType, int size, int stride, int pointer) {
        this.mAttributeType = attribute;
        this.mDataType = dataType;
        this.mSize = size;
        this.mStride = stride * dataType.getSize();
        this.mPointer = pointer * dataType.getSize();
        this.mIndex = -1;
    }

    public MrAttributeType getAttributeType() {
        return mAttributeType;
    }

    public int getSize() {
        return mSize;
    }

    public int getStride() {
        return this.mStride;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public int getPointer() {
        return mPointer;
    }

    public MrDataType getDataType() {
        return mDataType;
    }

    @Override
    public int hashCode() {
        return mAttributeType.getValue();
    }

    @Override
    public String toString() {
        return "MrBufferKey{" +
                "AttributeType=" + mAttributeType +
                ", Index=" + mIndex +
                ", Size=" + mSize +
                ", BufferDataType=" + mDataType +
                ", Stride=" + mStride +
                ", Pointer=" + mPointer +
                '}';
    }
}
