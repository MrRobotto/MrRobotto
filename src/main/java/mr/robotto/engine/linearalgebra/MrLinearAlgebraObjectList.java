/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.linearalgebra;

import mr.robotto.engine.commons.MrDataType;


public class MrLinearAlgebraObjectList implements MrLinearAlgebraObject {
    private MrLinearAlgebraObject[] mAlgebraObjects;
    private float[] mValues;
    private int mCount;
    private int mSize;
    private MrDataType mDataType;

    public MrLinearAlgebraObjectList(MrDataType dataType, int count, int size) {
        mDataType = dataType;
        mCount = count;
        mSize = size;
        init();
    }

    public MrLinearAlgebraObjectList(MrDataType dataType, int count) {
        mDataType = dataType;
        mCount = count;
        mSize = dataType.getCount();
        init();
    }

    private void init() {
        mAlgebraObjects = new MrLinearAlgebraObject[mCount];
        mValues = new float[mSize * mCount];
    }

    public void insert(int index, MrLinearAlgebraObject algebraObject) {
        //TODO: Add asserts here
        mAlgebraObjects[index] = algebraObject;
        insert(index, algebraObject.getValues());
    }

    public void insert(int index, float[] values) {
        //TODO: Check if this is index*mCount or it needs a -1
        //System.arraycopy(values, 0, mValues, index*mCount, values.length);
        System.arraycopy(values, 0, mValues, index * mSize, values.length);
    }

    public void insert(int index, float value) {
        mValues[index * mSize] = value;
    }

    @Override
    public float[] getValues() {
        return mValues;
    }

    @Override
    public int getCount() {
        return mAlgebraObjects.length;
    }

    @Override
    public MrDataType getDataType() {
        return mDataType;
    }
}
