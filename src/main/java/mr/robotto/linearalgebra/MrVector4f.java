/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.linearalgebra;

import java.util.HashMap;

import mr.robotto.commons.MrDataType;
import mr.robotto.exceptions.MrLinearAlgebraException;

public final class MrVector4f implements MrLinearAlgebraObject {
    private final static HashMap<Long, Operator> sOperators = new HashMap<>();
    public float w;
    public float x;
    public float y;
    public float z;

    private float values[];

    public MrVector4f() {
        values = new float[4];
        setValues(0, 0, 0, 0);
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

    //TODO: Change this synchronized for a special hashmap?
    public static Operator getOperator() {
        long id = Thread.currentThread().getId();
        synchronized (sOperators) {
            if (sOperators.containsKey(id)) {
                return sOperators.get(id);
            } else {
                Operator op = new Operator();
                sOperators.put(id, op);
                return op;
            }
        }
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public float[] getValues() {
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

    public void copyValues(MrVector4f v) {
        setValues(v.w, v.x, v.y, v.z);
    }

    public static class Operator {

        private Operator() {

        }

        public void setZero(MrVector4f result)
        {
            result.w = 0;
            result.x = 0;
            result.y = 0;
            result.z = 0;
        }

        public void vectorFromVec3(MrVector4f result, MrVector3f v)
        {
            result.setValues(v.x,v.y,v.z,0);
        }

        public void pointFromVec3(MrVector4f result, MrVector3f v)
        {
            result.setValues(v.x,v.y,v.z,1);
        }
    }
}
