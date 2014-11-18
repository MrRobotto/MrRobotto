/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.model.mesh.keys;

import mr.robotto.renderer.commons.MrDataType;
import mr.robotto.renderer.shaders.MrAttributeType;

public class MrAttributeKey
{
    private MrAttributeType attributeType;
    private String name;
    private int index;
    private int size;
    private MrDataType dataType;
    private int stride;
    private int pointer;

    public MrAttributeKey(MrAttributeType attribute, String name, int index, MrDataType dataType, int size, int stride, int pointer)
    {
        this.attributeType = attribute;
        this.name = name;
        this.index = index;
        this.dataType = dataType;
        this.size = size;
        this.stride = stride * dataType.getSize();
        this.pointer = pointer * dataType.getSize();
    }

    public MrAttributeType getAttributeType()
    {
        return attributeType;
    }

    /*public String getName()
    {
        return name;
    }*/

    public int getSize()
    {
        return size;
    }

    public int getStride()
    {
        return this.stride;
    }

    public int getIndex()
    {
        return index;
    }

    public int getPointer()
    {
        return pointer;
    }

    public MrDataType getDataType()
    {
        return dataType;
    }

    @Override
    public String toString()
    {
        return "MrBufferKey{" +
                "attributeType=" + attributeType +
                ", name='" + name + '\'' +
                ", index=" + index +
                ", size=" + size +
                ", bufferDataType=" + dataType +
                ", stride=" + stride +
                ", pointer=" + pointer +
                '}';
    }
}
