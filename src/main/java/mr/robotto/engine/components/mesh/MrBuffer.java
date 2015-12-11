/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.mesh;

import android.opengl.GLES20;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import mr.robotto.engine.commons.MrDataType;

/**
 * Stores a numerical buffer.
 * Its data is stored in native memory.
 */
public class MrBuffer {

    /**
     * Target type for VBO
     */
    public static final int TARGET_ARRAY_BUFFER = GLES20.GL_ARRAY_BUFFER;
    /**
     * Target type for IBO
     */
    public static final int TARGET_ELEMENT_ARRAY_BUFFER = GLES20.GL_ELEMENT_ARRAY_BUFFER;

    /**
     * Usage draw type
     */
    public static final int USAGE_STATIC_DRAW = GLES20.GL_STATIC_DRAW;

    private ByteBuffer mBufferData;
    private int mBufferPosition;
    private MrDataType mBufferDataType;
    private int mBufferUsage;
    private int mBufferTarget;
    private int mBufferId;
    private int mBufferCapacity;

    /**
     * Creates a new buffer
     * @param capacity capacity of this buffer, required to memory initialization
     * @param dataType Type of data will be stored, usually short or float
     * @param target Target type for VBO or IBO
     * @param usage Usage of this buffer, usually it will not change, so STATIC_DRAW is used
     */
    public MrBuffer(int capacity, MrDataType dataType, int target, int usage) {
        mBufferPosition = 0;
        mBufferDataType = dataType;
        mBufferTarget = target;
        mBufferUsage = usage;
        mBufferData = ByteBuffer.allocateDirect(capacity * mBufferDataType.getByteSize());
        mBufferData.order(ByteOrder.nativeOrder());
        mBufferCapacity = capacity;
    }

    /**
     * Creates an IBO with the given capacity
     * @param capacity
     * @return new IBO
     */
    public static MrBuffer genIndexBuffer(int capacity) {
        return new MrBuffer(capacity, MrDataType.UNSIGNED_SHORT, TARGET_ELEMENT_ARRAY_BUFFER, USAGE_STATIC_DRAW);
    }

    /**
     * Creates a VBO with the given capacity
     * @param capacity
     * @return new VBO
     */
    public static MrBuffer genVertexBuffer(int capacity) {
        return new MrBuffer(capacity, MrDataType.FLOAT, TARGET_ARRAY_BUFFER, USAGE_STATIC_DRAW);
    }

    /**
     * Gets the GPU id of this buffer
     * @return
     */
    public int getId() {
        return mBufferId;
    }

    /**
     * Sets the GPU id given to this buffer
     * @param id
     */
    public void setId(int id) {
        mBufferId = id;
    }

    /**
     * Gets the buffer target
     * @return
     */
    public int getBufferTarget() {
        return mBufferTarget;
    }

    /**
     * Sets the buffer target to be used.
     * This must be set before initialization to take effect
     * @param bufferTarget
     */
    public void setBufferTarget(int bufferTarget) {
        this.mBufferTarget = bufferTarget;
    }

    /**
     * Gets the data type used in this buffer
     * @return
     */
    public MrDataType getBufferDataType() {
        return mBufferDataType;
    }

    /**
     * Gets the buffer usage
     * @return
     */
    public int getBufferUsage() {
        return mBufferUsage;
    }

    /**
     * Sets the buffer usage.
     * This must be set before initialization to take effect
     * @param bufferUsage
     */
    public void setBufferUsage(int bufferUsage) {
        this.mBufferUsage = bufferUsage;
    }

    /**
     * Returns the capacity of this buffer
     * @return
     */
    public int getBufferSize() {
        return mBufferCapacity;
    }

    /**
     * Returns this buffer as a Java buffer object
     * @return
     */
    public Buffer asBuffer() {
        mBufferData.position(0);
        return mBufferData;
    }

    //TODO: Make buffer invariant once it is "released", change the name...
    /**
     * Removes and releases the data of this buffer
     */
    public void releaseBuffer() {
        mBufferData.limit(0);
        mBufferData.clear();
        mBufferData = null;
    }

    //TODO: Check buffer overflow

    /**
     * Puts a byte in this buffer
     * @param b
     */
    public void putByte(Byte b) {
        mBufferData.position(mBufferPosition);
        mBufferData.put(b);
        mBufferPosition = mBufferData.position();
    }

    /**
     * Puts a char in this buffer
     * @param c
     */
    public void putChar(char c) {
        mBufferData.position(mBufferPosition);
        mBufferData.putChar(c);
        mBufferPosition = mBufferData.position();
    }

    /**
     * Puts a double in this buffer
     * @param d
     */
    public void putDouble(double d) {
        mBufferData.position(mBufferPosition);
        mBufferData.putDouble(d);
        mBufferPosition = mBufferData.position();
    }

    /**
     * Puts a float in this buffer
     * @param f
     */
    public void putFloat(float f) {
        mBufferData.position(mBufferPosition);
        mBufferData.putFloat(f);
        mBufferPosition = mBufferData.position();
    }

    /**
     * Puts an integer in this buffer
     * @param i
     */
    public void putInt(int i) {
        mBufferData.position(mBufferPosition);
        mBufferData.putInt(i);
        mBufferPosition = mBufferData.position();
    }

    /**
     * Puts a long in this buffer
     * @param l
     */
    public void putLong(long l) {
        mBufferData.position(mBufferPosition);
        mBufferData.putLong(l);
        mBufferPosition = mBufferData.position();
    }

    /**
     * Puts a short in this buffer
     * @param s
     */
    public void putShort(short s) {
        mBufferData.position(mBufferPosition);
        mBufferData.putShort(s);
        mBufferPosition = mBufferData.position();
    }

    /**
     * Puts a byte array in this buffer
     * @param bytes
     */
    public void putBytes(byte[] bytes) {
        for (byte b : bytes) {
            putByte(b);
        }
    }

    /**
     * Puts a char array in this buffer
     * @param chars
     */
    public void putChars(char[] chars) {
        for (char c : chars) {
            putChar(c);
        }
    }

    /**
     * Puts a double array in this buffer
     * @param doubles
     */
    public void putDoubles(double[] doubles) {
        for (double d : doubles) {
            putDouble(d);
        }
    }

    /**
     * Puts a float array in this buffer
     * @param floats
     */
    public void putFloats(float[] floats) {
        for (float f : floats) {
            putFloat(f);
        }
    }

    /**
     * Puts an integer array in this buffer
     * @param ints
     */
    public void putInts(int[] ints) {
        for (int i : ints) {
            putInt(i);
        }
    }

    /**
     * Puts a long array in this buffer
     * @param longs
     */
    public void putLongs(long[] longs) {
        for (long l : longs) {
            putLong(l);
        }
    }

    /**
     * Puts a short array in this buffer
     * @param shorts
     */
    public void putShorts(short[] shorts) {
        for (short s : shorts) {
            putShort(s);
        }
    }
}
