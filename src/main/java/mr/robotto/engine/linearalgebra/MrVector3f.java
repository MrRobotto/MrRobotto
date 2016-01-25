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

public final class MrVector3f implements MrLinearAlgebraObject {
    private final static HashMap<Long, Operator> sOperators = new HashMap<>();
    public float x;
    public float y;
    public float z;

    private float values[];

    public MrVector3f()
    {
        values = new float[3];
        setValues(x, y, z);
    }

    public MrVector3f(float x, float y, float z)
    {
        values = new float[3];
        setValues(x,y,z);
    }

    public MrVector3f(float v)
    {
        this(v,v,v);
    }

    public MrVector3f(float[] values)
    {
        if (values.length == 3)
        {
            this.values = new float[3];
            setValues(values[0],values[1],values[2]);
        }
        else
        {
            MrLinearAlgebraException.throwInvalidVectorDimensionException();
        }
    }

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

    public void setValues(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public float[] getValues() {
        values[0] = x;
        values[1] = y;
        values[2] = z;
        return values;
    }

    @Override
    public MrDataType getDataType() {
        return MrDataType.VEC3;
    }

    /**
     * Copy values from vector v to this
     *
     * @param v
     */
    public void copyValues(MrVector3f v) {
        setValues(v.x, v.y, v.z);
    }

    @Override
    public String toString() {
        return "MrVector3f{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public static class Operator {
        private final MrQuaternion quat1 = new MrQuaternion();
        private final MrQuaternion quat2 = new MrQuaternion();
        private final MrQuaternion quat3 = new MrQuaternion();
        private final MrVector3f tmp1 = new MrVector3f();
        private final MrVector3f tmp2 = new MrVector3f();

        private Operator() {

        }

        public void add(MrVector3f result, MrVector3f op1, MrVector3f op2) {
            result.x = op1.x + op2.x;
            result.y = op1.y + op2.y;
            result.z = op1.z + op2.z;
        }

        public void substract(MrVector3f result, MrVector3f op1, MrVector3f op2) {
            result.x = op1.x - op2.x;
            result.y = op1.y - op2.y;
            result.z = op1.z - op2.z;
        }

        public void setZero(MrVector3f result) {
            result.x = 0;
            result.y = 0;
            result.z = 0;
        }

        public void multScalar(MrVector3f result, float scalar) {
            result.x = scalar * result.x;
            result.y = scalar * result.y;
            result.z = scalar * result.z;
        }

        public void multScalar(MrVector3f result, MrVector3f v, float scalar) {
            result.copyValues(v);
            multScalar(result, scalar);
        }

        public float norm2(MrVector3f v) {
            return (float) Math.sqrt(dot(v, v));
        }

        public void cross(MrVector3f result, MrVector3f op1, MrVector3f op2) {
            tmp1.copyValues(op1);
            tmp2.copyValues(op2);
            result.x = tmp1.y * tmp2.z - tmp1.z * tmp2.y;
            result.y = tmp1.z * tmp2.x - tmp1.x * tmp2.z;
            result.z = tmp1.x * tmp2.y - tmp1.y * tmp2.x;
        }

        public float dot(MrVector3f op1, MrVector3f op2) {
            return op1.x * op2.x + op1.y * op2.y + op1.z * op2.z;
        }

        public void lerp(MrVector3f result, float t, MrVector3f op1, MrVector3f op2) {
            result.x = (1 - t) * op1.x + t * op2.x;
            result.y = (1 - t) * op1.y + t * op2.y;
            result.z = (1 - t) * op1.z + t * op2.z;
        }

        public void rotateVector(MrVector3f result, MrQuaternion q, MrVector3f v) {
            MrQuaternion.Operator quatOp = MrQuaternion.getOperator();
            //Qv = Q(0,vx,vy,vz)
            quatOp.fromVec3(quat1, v);
            //q=q/||q||
            quatOp.normalize(quat2, q);

            //q*Qv
            quatOp.mult(quat1, quat2, quat1);
            //q^
            quatOp.conjugate(quat2, quat2);
            //q*Qv*q^
            quatOp.mult(quat1, quat1, quat2);
            result.setValues(quat1.x, quat1.y, quat1.z);
        }

        public void vectorFromVec4(MrVector3f result, MrVector4f v) {
            if (v.z != 0.0 && -v.z != 0.0) {
                result.setValues(v.w / v.z, v.x / v.z, v.y / v.z);
            } else {
                result.setValues(v.w, v.x * 1.0f, v.y);
            }
        }
    }
}
