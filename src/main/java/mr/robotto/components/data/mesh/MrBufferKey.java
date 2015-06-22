/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.mesh;

import mr.robotto.commons.MrDataType;

/**
 * Class used to determine position and order of {@link mr.robotto.components.data.shader.MrAttribute} data stored inside a {@link MrBuffer}
 */
public class MrBufferKey {
    private int mAttributeType;
    private int mId;
    private int mSize;
    private MrDataType mDataType;
    private int mStride;
    private int mPointer;

    /**
     * Creates a new Buffer Key
     * @param attribute which attribute it is linked to
     * @param dataType Type stored, it will be usually a numeric type such as float, short,...
     * @param size Lenght of data. For example, if data is stored as a vector, the lenght of that vector
     * @param stride Number of elements between an element of this attribute and the next one
     * @param pointer Index inside the buffer where this element starts
     */
    public MrBufferKey(int attribute, MrDataType dataType, int size, int stride, int pointer) {
        this.mAttributeType = attribute;
        this.mDataType = dataType;
        this.mSize = size;
        this.mStride = stride * dataType.getByteSize();
        this.mPointer = pointer * dataType.getByteSize();
        this.mId = -1;
    }

    /**
     * Gets the attribute type
     * @return See {@link mr.robotto.components.data.shader.MrAttribute} to see returned values
     */
    public int getAttributeType() {
        return mAttributeType;
    }

    /**
     * Lenght of data. For example, if data is stored as a vector, the lenght of that vector
     * @return
     */
    public int getSize() {
        return mSize;
    }

    /**
     * Number of elements between an element of this attribute and the next one
     * @return
     */
    public int getStride() {
        return this.mStride;
    }

    /**
     * Gets the attribute id obtained from GPU
     * @return
     */
    public int getId() {
        return mId;
    }

    /**
     * Sets the GPU id for this attribute
     * @param index
     */
    public void setId(int index) {
        mId = index;
    }

    /**
     * Index inside the buffer where this element starts
     * @return
     */
    public int getPointer() {
        return mPointer;
    }

    /**
     * Type stored, it will be usually a numeric type such as float, short,...
     * @return
     */
    public MrDataType getDataType() {
        return mDataType;
    }

    @Override
    public String toString() {
        return "MrBufferKey{" +
                "AttributeType=" + mAttributeType +
                ", Id=" + mId +
                ", Size=" + mSize +
                ", BufferDataType=" + mDataType +
                ", Stride=" + mStride +
                ", Pointer=" + mPointer +
                '}';
    }
}
