/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.linearalgebra;

import mr.robotto.renderer.commons.MrDataType;
import mr.robotto.renderer.exceptions.MrLinearAlgebraException;

public class MrVector4f implements MrLinearAlgebraObject
{
    public float w;
    public float x;
    public float y;
    public float z;

    private float values[];

    public MrVector4f() {
        values = new float[4];
        ops.setZero(this);
    }

    public MrVector4f(float w, float x, float y, float z) {
        values = new float[4];
        setValues(w,x,y,z);
    }

    public MrVector4f(float v) {
        this(v,v,v,v);
    }

    public MrVector4f(float[] values) {
        this.values = new float[4];
        if (values.length == 4)
        {
            setValues(values[0],values[1],values[2],values[3]);
        }
        else
        {
            MrLinearAlgebraException.throwInvalidVectorDimensionException();
        }
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public float[] getValues()
    {
        values[0] = w;
        values[1] = x;
        values[2] = y;
        values[3] = z;
        return values;
    }

    @Override
    public MrDataType getDataType() {
        return MrDataType.VEC4;
    }

    public void setValues(float w, float x, float y, float z)
    {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void copyValues(MrVector4f v)
    {
        setValues(v.w,v.x,v.y,v.z);
    }



    public static class ops
    {
        public static void setZero(MrVector4f result)
        {
            result.w = 0;
            result.x = 0;
            result.y = 0;
            result.z = 0;
        }

        public static void vectorFromVec3(MrVector4f result, MrVector3f v)
        {
            result.setValues(v.x,v.y,v.z,0);
        }

        public static void pointFromVec3(MrVector4f result, MrVector3f v)
        {
            result.setValues(v.x,v.y,v.z,1);
        }
    }


}
