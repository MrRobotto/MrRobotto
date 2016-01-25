/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.linearalgebra;

import java.util.HashMap;

import mr.robotto.engine.commons.MrDataType;
import mr.robotto.engine.exceptions.MrLinearAlgebraException;

public final class MrVector4f implements MrLinearAlgebraObject {
    public static final int SIZE = 4;
    public static final int COUNT = 1;
    public static final MrDataType TYPE = MrDataType.VEC4;

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
        if (values.length == 4) {
            setValues(values[0],values[1],values[2],values[3]);
        } else {
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

    //TODO: Agregar o una constante de clase o algo en el operador que permita saber el count y size de cada clase
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

        public void add(MrVector4f result, MrVector4f op1, MrVector4f op2) {
            result.w = op1.w + op2.w;
            result.x = op1.x + op2.x;
            result.y = op1.y + op2.y;
            result.z = op1.z + op2.z;
        }

        public void substract(MrVector4f result, MrVector4f op1, MrVector4f op2) {
            result.w = op1.w - op2.w;
            result.x = op1.x - op2.x;
            result.y = op1.y - op2.y;
            result.z = op1.z - op2.z;
        }

        public void multScalar(MrVector4f result, float scalar) {
            result.w = scalar * result.w;
            result.x = scalar * result.x;
            result.y = scalar * result.y;
            result.z = scalar * result.z;
        }

        public void multScalar(MrVector4f result, MrVector4f v, float scalar) {
            result.copyValues(v);
            multScalar(result, scalar);
        }

        public float norm2(MrVector4f v) {
            return (float) Math.sqrt(dot(v, v));
        }

        public float dot(MrVector4f op1, MrVector4f op2) {
            return op1.w * op2.w + op1.x * op2.x + op1.y * op2.y + op1.z * op2.z;
        }

        public void lerp(MrVector4f result, float t, MrVector4f op1, MrVector4f op2) {
            result.w = (1 - t) * op1.w + t * op2.w;
            result.x = (1 - t) * op1.x + t * op2.x;
            result.y = (1 - t) * op1.y + t * op2.y;
            result.z = (1 - t) * op1.z + t * op2.z;
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
