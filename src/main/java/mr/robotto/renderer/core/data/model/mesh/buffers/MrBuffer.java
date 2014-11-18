/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.model.mesh.buffers;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import mr.robotto.renderer.commons.MrDataType;

public class MrBuffer
{
    private ByteBuffer bufferData;
    private int bufferPosition;
    private MrDataType bufferDataType;
    private MrBufferUsage bufferUsage;
    private MrBufferTarget bufferTarget;
    private IntBuffer bufferId;
    private int bufferCapacity;

    public MrBuffer(int capacity, MrDataType dataType, MrBufferTarget target, MrBufferUsage usage)
    {
        bufferPosition = 0;
        bufferDataType = dataType;
        bufferTarget = target;
        bufferUsage = usage;
        bufferData = ByteBuffer.allocate(capacity * bufferDataType.getSize());
        bufferData.order(ByteOrder.nativeOrder());
        bufferCapacity = capacity;
    }

    public void setBufferId(IntBuffer bufferId)
    {
        this.bufferId = bufferId;
    }

    public IntBuffer getBufferId()
    {
        return bufferId;
    }

    public int getId()
    {
        return bufferId.get(0);
    }

    public void setId(int id)
    {
        bufferId.put(0,id);
    }

    public MrBufferTarget getBufferTarget()
    {
        return bufferTarget;
    }

    public void setBufferTarget(MrBufferTarget bufferTarget)
    {
        this.bufferTarget = bufferTarget;
    }

    public MrDataType getBufferDataType()
    {
        return bufferDataType;
    }

    public void setBufferDataType(MrDataType bufferDataType)
    {
        this.bufferDataType = bufferDataType;
    }

    public MrBufferUsage getBufferUsage()
    {
        return bufferUsage;
    }

    public void setBufferUsage(MrBufferUsage bufferUsage)
    {
        this.bufferUsage = bufferUsage;
    }

    public int getBufferSize()
    {
        return bufferCapacity;
    }

    public Buffer asBuffer()
    {
        bufferData.position(0);
        return bufferData;
    }

    //TODO: Make buffer invariant once it is "released", change the name...
    public void releaseBuffer()
    {
        bufferData.limit(0);
        bufferData = null;
    }

    //TODO: Check buffer overflow
    public void putByte(Byte b)
    {
        bufferData.position(bufferPosition);
        bufferData.put(b);
        bufferPosition = bufferData.position();
    }

    public void putChar(char c)
    {
       bufferData.position(bufferPosition);
       bufferData.putChar(c);
       bufferPosition = bufferData.position();
    }

    public void putDouble(double d)
    {
        bufferData.position(bufferPosition);
        bufferData.putDouble(d);
        bufferPosition = bufferData.position();
    }

    public void putFloat(float f)
    {
        bufferData.position(bufferPosition);
        bufferData.putFloat(f);
        bufferPosition = bufferData.position();
    }

    public void putInt(int i)
    {
        bufferData.position(bufferPosition);
        bufferData.putInt(i);
        bufferPosition = bufferData.position();
    }

    public void putLong(long l)
    {
        bufferData.position(bufferPosition);
        bufferData.putLong(l);
        bufferPosition = bufferData.position();
    }

    public void putShort(short s)
    {
        bufferData.position(bufferPosition);
        bufferData.putShort(s);
        bufferPosition = bufferData.position();
    }

    public void putBytes(byte[] bytes)
    {
        for (byte b : bytes)
        {
            putByte(b);
        }
    }

    public void putChars(char[] chars)
    {
        for (char c : chars)
        {
            putChar(c);
        }
    }

    public void putDoubles(double[] doubles)
    {
        for (double d : doubles)
        {
            putDouble(d);
        }
    }

    public void putFloats(float[] floats)
    {
       for (float f : floats)
       {
           putFloat(f);
       }
    }

    public void putInts(int[] ints)
    {
        for (int i : ints)
        {
            putInt(i);
        }
    }

    public void putLongs(long[] longs)
    {
        for (long l : longs)
        {
            putLong(l);
        }
    }

    public void putShorts(short[] shorts)
    {
        for (short s : shorts)
        {
            putShort(s);
        }
    }
}
