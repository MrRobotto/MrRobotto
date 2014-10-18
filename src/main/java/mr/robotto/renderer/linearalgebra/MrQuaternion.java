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

public class MrQuaternion implements MrLinearAlgebraObject
{
    public float x;
    public float y;
    public float z;
    public float w;
    private float[] values;

    public MrQuaternion()
    {
        init();
    }

    public MrQuaternion(float w, float x, float y, float z)
    {
        init();
        setValues(w, x, y, z);
    }

    public MrQuaternion(MrQuaternion q)
    {
        this(q.w, q.x, q.y, q.z);
    }

    private void init()
    {
        ops.setIdentity(this);
        values = new float[4];
    }

    @Override
    public int getCount() {
        return 1;
    }

    public void setValues(float w, float x, float y, float z)
    {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Modifying these vaulues you will not change the current quaternion
     * values, this array is read only
     * @return
     */
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
        return MrDataType.QUATERNION;
    }

    public void copyValues(MrQuaternion q)
    {
        setValues(q.w,q.x,q.y,q.z);
    }

    public static class ops
    {
        private static MrQuaternion opRot = new MrQuaternion();

        public static void setIdentity(MrQuaternion result)
        {
            result.setValues(1.0f,0.0f,0.0f,0.0f);
        }

        public static void setZero(MrQuaternion result)
        {
            result.setValues(0.0f,0.0f,0.0f,0.0f);
        }

        public static void add(MrQuaternion result, MrQuaternion q1, MrQuaternion q2)
        {
            result.x = q1.x + q2.x;
            result.y = q1.y + q2.y;
            result.z = q1.z + q2.z;
            result.w = q1.w + q2.w;
        }

        public static void substract(MrQuaternion result, MrQuaternion q1, MrQuaternion q2)
        {
            result.x = q1.x - q2.x;
            result.y = q1.y - q2.y;
            result.z = q1.z - q2.z;
            result.w = q1.w - q2.w;
        }

        public static void conjugate(MrQuaternion result, MrQuaternion q)
        {
            result.w = q.w;
            result.x = -q.x;
            result.y = -q.y;
            result.z = -q.z;
        }

        public static float norm2(MrQuaternion q)
        {
            return (float)Math.sqrt(dot(q,q));
        }

        public static float dot(MrQuaternion q1, MrQuaternion q2)
        {
            return q1.x*q2.x + q1.y*q2.y + q1.z*q2.z + q1.w*q2.w;
        }

        public static void mult(MrQuaternion result, MrQuaternion q1, MrQuaternion q2)
        {
            float x1 = q1.x, y1 = q1.y, z1 = q1.z, w1 = q1.w;
            float x2 = q2.x, y2 = q2.y, z2 = q2.z, w2 = q2.w;
            result.w = w1*w2 - x1*x2 - y1*y2 - z1*z2;
            result.x = w1*x2 + x1*w2 + y1*z2 - z1*y2;
            result.y = w1*y2 + y1*w2 + z1*x2 - x1*z2;
            result.z = w1*z2 + z1*w2 + x1*y2 - y1*x2;
        }

        public static void multScalar(MrQuaternion result, float r, MrQuaternion q)
        {
            result.w = q.w*r;
            result.x = q.x*r;
            result.y = q.y*r;
            result.z = q.z*r;
        }

        public static void normalize(MrQuaternion result, MrQuaternion q)
        {
            float n = ops.norm2(q);
            multScalar(result,n,q);
        }

        public static void invert(MrQuaternion result, MrQuaternion q)
        {
            float n2 = ops.dot(q,q);
            conjugate(result, q);
            multScalar(result, n2, result);
        }

        public static void rotate(MrQuaternion result, float angle, MrVector3f axis)
        {
            rotate(result,angle,axis.x,axis.y,axis.z);
        }

        public static void rotate(MrQuaternion result, float angle, float x, float y, float z)
        {
            fromAngleAxis(opRot,angle,x,y,z);
            mult(result,result, opRot);
        }

        public static void fromAngleAxis(MrQuaternion result, float angle, float x, float y, float z)
        {
            //Axis normalization
            float norm = (float)Math.sqrt(x*x+y*y+z*z);
            norm = 1/norm;
            float xn = x*norm, yn = y*norm, zn = z*norm;

            //Calc of cos(angle/2) and sin(angle/2)
            float a = (float) Math.toRadians(angle/2);
            float c = (float) Math.cos(a);
            float s = (float) Math.sin(a);

            //The values of the quaternion will be
            //[cos(angle/2), axis.x*sin(angle/2), axis.y*sin(angle/2), axis.z*sin(angle/2)]
            result.w = c;
            result.x = s*xn;
            result.y = s*yn;
            result.z = s*zn;
            normalize(result,result);
        }

        public static void fromVec3(MrQuaternion result, MrVector3f v)
        {
            result.w = 0;
            result.x = v.x;
            result.y = v.y;
            result.z = v.z;
        }

        public static void fromAngleAxis(MrQuaternion result, float angle, MrVector3f axis)
        {
            fromAngleAxis(result,angle,axis.x,axis.y,axis.z);
        }

        //TODO: Optimize this method
        public static void fromMatrix4(MrQuaternion result, MrMatrix4f m)
        {
            float[] v = m.getValues();
            float m00 = v[0],
                  m01 = v[4],
                  m02 = v[8],
                  m10 = v[1],
                  m11 = v[5],
                  m12 = v[9],
                  m20 = v[2],
                  m21 = v[6],
                  m22 = v[10];
            float S, qw, qx, qy, qz;


            float trace = m00 + m11 + m22; // I removed + 1.0f; see discussion with Ethan
            if( trace > 0 ) {// I changed M_EPSILON to 0
                S = 0.5f / (float)Math.sqrt(trace+ 1.0f);
                qw = 0.25f / S;
                qx = (m21 - m12) * S;
                qy = (m02 - m20) * S;
                qz = (m10 - m01) * S;
            } else {
                if (m00 > m11 && m00 > m22)
                {
                    S = 2.0f * (float)Math.sqrt(1.0f + m00 - m11 - m22);
                    qw = (m21 - m12) / S;
                    qx = 0.25f * S;
                    qy = (m01 + m10) / S;
                    qz = (m02 + m20) / S;
                } else if (m11 > m22) {
                    S = 2.0f * (float)Math.sqrt(1.0f + m11 - m00 - m22);
                    qw = (m02 - m20) / S;
                    qx = (m01 + m10) / S;
                    qy = 0.25f * S;
                    qz = (m12 + m21) / S;
                } else {
                    S = 2.0f * (float)Math.sqrt( 1.0f + m22 - m00 - m11);
                    qw = (m10 - m01) / S;
                    qx = (m02 + m20) / S;
                    qy = (m12 + m21) / S;
                    qz = 0.25f * S;
                }
            }

            /*---------------------------*/



            /*float tr = m00 + m11 + m22;

            if (tr > 0) {
                S = (float) Math.sqrt(tr+1.0) * 2; // S=4*qw
                qw = 0.25f * S;
                qx = (m21 - m12) / S;
                qy = (m02 - m20) / S;
                qz = (m10 - m01) / S;
            } else if ((m00 > m11)&(m00 > m22)) {
                S = (float) Math.sqrt(1.0 + m00 - m11 - m22) * 2; // S=4*qx
                qw = (m21 - m12) / S;
                qx = 0.25f * S;
                qy = (m01 + m10) / S;
                qz = (m02 + m20) / S;
            } else if (m11 > m22) {
                S = (float) Math.sqrt(1.0 + m11 - m00 - m22) * 2; // S=4*qy
                qw = (m02 - m20) / S;
                qx = (m01 + m10) / S;
                qy = 0.25f * S;
                qz = (m12 + m21) / S;
            } else {
                S = (float) Math.sqrt(1.0 + m22 - m00 - m11) * 2; // S=4*qz
                qw = (m10 - m01) / S;
                qx = (m02 + m20) / S;
                qy = (m12 + m21) / S;
                qz = 0.25f * S;
            }*/

            result.w = qw;
            result.x = qx;
            result.y = qy;
            result.z = qz;
        }

        public static void slerp(MrQuaternion result, float t, MrQuaternion q1, MrQuaternion q2)
        {
            result.w = (1-t)*q1.w + t*q2.w;
            result.x = (1-t)*q1.x + t*q2.x;
            result.y = (1-t)*q1.y + t*q2.y;
            result.z = (1-t)*q1.z + t*q2.z;
            normalize(result,result);
        }
    }

    @Override
    public String toString()
    {
        return "MrQuaternion{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }
}
