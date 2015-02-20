/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.mesh;

import android.opengl.GLES20;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import mr.robotto.commons.MrDataType;

public class MrBuffer {

    public static final int TARGET_ARRAY_BUFFER = GLES20.GL_ARRAY_BUFFER;
    public static final int TARGET_ELEMENT_ARRAY_BUFFER = GLES20.GL_ELEMENT_ARRAY_BUFFER;

    public static final int USAGE_STATIC_DRAW = GLES20.GL_STATIC_DRAW;

    private ByteBuffer mBufferData;
    private int mBufferPosition;
    private MrDataType mBufferDataType;
    private int mBufferUsage;
    private int mBufferTarget;
    private IntBuffer mBufferId;
    private int mBufferCapacity;

    public MrBuffer(int capacity, MrDataType dataType, int target, int usage) {
        mBufferPosition = 0;
        mBufferDataType = dataType;
        mBufferTarget = target;
        mBufferUsage = usage;
        mBufferData = ByteBuffer.allocate(capacity * mBufferDataType.getSize());
        mBufferData.order(ByteOrder.nativeOrder());
        mBufferCapacity = capacity;
    }

    public IntBuffer getBufferId() {
        return mBufferId;
    }

    public void setBufferId(IntBuffer bufferId) {
        this.mBufferId = bufferId;
    }

    public int getId() {
        return mBufferId.get(0);
    }

    public void setId(int id) {
        mBufferId.put(0, id);
    }

    public int getBufferTarget() {
        return mBufferTarget;
    }

    public void setBufferTarget(int bufferTarget) {
        this.mBufferTarget = bufferTarget;
    }

    public MrDataType getBufferDataType() {
        return mBufferDataType;
    }

    public void setBufferDataType(MrDataType bufferDataType) {
        this.mBufferDataType = bufferDataType;
    }

    public int getBufferUsage() {
        return mBufferUsage;
    }

    public void setBufferUsage(int bufferUsage) {
        this.mBufferUsage = bufferUsage;
    }

    public int getBufferSize() {
        return mBufferCapacity;
    }

    public Buffer asBuffer() {
        mBufferData.position(0);
        return mBufferData;
    }

    //TODO: Make buffer invariant once it is "released", change the name...
    public void releaseBuffer() {
        mBufferData.limit(0);
        mBufferData = null;
    }

    //TODO: Check buffer overflow
    public void putByte(Byte b) {
        mBufferData.position(mBufferPosition);
        mBufferData.put(b);
        mBufferPosition = mBufferData.position();
    }

    public void putChar(char c) {
        mBufferData.position(mBufferPosition);
        mBufferData.putChar(c);
        mBufferPosition = mBufferData.position();
    }

    public void putDouble(double d) {
        mBufferData.position(mBufferPosition);
        mBufferData.putDouble(d);
        mBufferPosition = mBufferData.position();
    }

    public void putFloat(float f) {
        mBufferData.position(mBufferPosition);
        mBufferData.putFloat(f);
        mBufferPosition = mBufferData.position();
    }

    public void putInt(int i) {
        mBufferData.position(mBufferPosition);
        mBufferData.putInt(i);
        mBufferPosition = mBufferData.position();
    }

    public void putLong(long l) {
        mBufferData.position(mBufferPosition);
        mBufferData.putLong(l);
        mBufferPosition = mBufferData.position();
    }

    public void putShort(short s) {
        mBufferData.position(mBufferPosition);
        mBufferData.putShort(s);
        mBufferPosition = mBufferData.position();
    }

    public void putBytes(byte[] bytes) {
        for (byte b : bytes) {
            putByte(b);
        }
    }

    public void putChars(char[] chars) {
        for (char c : chars) {
            putChar(c);
        }
    }

    public void putDoubles(double[] doubles) {
        for (double d : doubles) {
            putDouble(d);
        }
    }

    public void putFloats(float[] floats) {
        for (float f : floats) {
            putFloat(f);
        }
    }

    public void putInts(int[] ints) {
        for (int i : ints) {
            putInt(i);
        }
    }

    public void putLongs(long[] longs) {
        for (long l : longs) {
            putLong(l);
        }
    }

    public void putShorts(short[] shorts) {
        for (short s : shorts) {
            putShort(s);
        }
    }
}
