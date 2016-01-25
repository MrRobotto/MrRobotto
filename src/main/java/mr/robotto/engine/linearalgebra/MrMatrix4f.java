/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.linearalgebra;

import android.opengl.Matrix;

import java.util.HashMap;

import mr.robotto.engine.commons.MrDataType;

public final class MrMatrix4f implements MrLinearAlgebraObject {
    private final static HashMap<Long, Operator> sOperators = new HashMap<>();
    private float[] mValues;

    public MrMatrix4f() {
        init();
    }

    public MrMatrix4f(MrMatrix4f m) {
        init();
        copyValues(m);
    }

    public MrMatrix4f(float[] values) {
        init();
        setValues(values);
    }

    //TODO: Check this synchronized
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

    private void init() {
        mValues = new float[16];
        Matrix.setIdentityM(mValues, 0);
        //getOperator().setIdentity(this);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public float[] getValues() {
        return mValues;
    }

    public void setValues(float[] values) {
        System.arraycopy(values, 0, this.mValues, 0, 16);
    }

    @Override
    public MrDataType getDataType() {
        return MrDataType.MAT4;
    }

    /**
     * Set the value in the mProjectionMatrix
     *
     * @param i index of Matrix seen as an array of 16 elements
     * @param f new value
     */
    private void setValueAt(int i, float f) {
        mValues[i] = f;
    }

    /**
     * Sets a certain value of the matix
     *
     * @param i row index
     * @param j column index
     * @param f value
     */
    public void setValueAt(int i, int j, float f) {
        int k = 4 * j + i;
        setValueAt(k, f);
    }

    /**
     * Gets the value of row i and column j
     *
     * @param i
     * @param j
     * @return
     */
    public float getValueAt(int i, int j) {
        int k = 4 * j + i;
        return mValues[k];
    }

    public void copyValues(MrMatrix4f m) {
        System.arraycopy(m.mValues, 0, this.mValues, 0, 16);
    }

    @Override
    public String toString() {
        String str = "Matrix:\n";
        for (int i = 0; i < 4; i++) {
            str += "|";
            for (int j = 0; j < 4; j++) {
                int k = 4 * j + i;
                str += mValues[k] + ", ";
            }
            str += "|\n";
        }
        return str;
    }

    public static class Operator {
        private final MrMatrix4f op1Mult = new MrMatrix4f();
        private final MrMatrix4f op2Mult = new MrMatrix4f();
        private final MrMatrix4f opRotQuat = new MrMatrix4f();
        private final MrVector4f op1MultV3 = new MrVector4f();
        private final MrVector4f op2MultV3 = new MrVector4f();
        private final MrVector4f opMultV4 = new MrVector4f();
        private final MrMatrix4f opMatChange = new MrMatrix4f();
        private final float[] opMultV = new float[4];

        private Operator() {

        }

        public void setIdentity(MrMatrix4f result) {
            Matrix.setIdentityM(result.mValues, 0);
        }

        //TODO: Check invertible Matrix
        public void invert(MrMatrix4f result, MrMatrix4f m) {
            opMatChange.copyValues(m);
            Matrix.invertM(result.mValues, 0, opMatChange.mValues, 0);
        }

        public void transpose(MrMatrix4f result, MrMatrix4f m) {
            opMatChange.copyValues(m);
            Matrix.transposeM(result.mValues, 0, opMatChange.mValues, 0);
        }

        /**
         * Add two matrices and saves the result in 'result'
         *
         * @param result
         * @param m1
         * @param m2
         * @return
         */
        public void add(MrMatrix4f result, MrMatrix4f m1, MrMatrix4f m2) {
            for (int i = 0; i < 16; i++) {
                result.mValues[i] = m1.mValues[i] + m2.mValues[i];
            }
        }

        public void substract(MrMatrix4f result, MrMatrix4f m1, MrMatrix4f m2) {
            for (int i = 0; i < 16; i++) {
                result.mValues[i] = m1.mValues[i] - m2.mValues[i];
            }
        }

        public void mult(MrMatrix4f result, MrMatrix4f m1, MrMatrix4f m2) {
            op1Mult.copyValues(m1);
            op2Mult.copyValues(m2);
            Matrix.multiplyMM(result.mValues, 0, op1Mult.mValues, 0, op2Mult.mValues, 0);
        }

        //TODO: Change these to vector classes
        //TODO: BIG BUG, the result.values are changed but not the w,x,y,z...
        public void multV(MrVector4f result, MrMatrix4f m, MrVector4f v) {
            opMultV4.copyValues(v);
            Matrix.multiplyMV(opMultV, 0, m.mValues, 0, opMultV4.getValues(), 0);
            result.setValues(opMultV[0], opMultV[1], opMultV[2], opMultV[3]);
        }

        public void multV(MrVector3f result, MrMatrix4f m, MrVector3f v) {
            MrVector3f.Operator vec3Op = MrVector3f.getOperator();
            MrVector4f.Operator vec4Op = MrVector4f.getOperator();
            vec4Op.vectorFromVec3(op1MultV3, result);
            vec4Op.vectorFromVec3(op2MultV3, v);
            multV(op1MultV3, m, op2MultV3);
            vec3Op.vectorFromVec4(result, op1MultV3);
        }

        public void multScalar(MrMatrix4f result, float s) {
            for (int i = 0; i < result.mValues.length; i++) {
                result.mValues[i] = s * result.mValues[i];
            }
        }

        public void multScalar(MrMatrix4f result, MrMatrix4f m, float s) {
            for (int i = 0; i < result.mValues.length; i++) {
                result.mValues[i] = s * m.mValues[i];
            }
        }

        public void translate(MrMatrix4f result, float x, float y, float z) {
            Matrix.translateM(result.mValues, 0, x, y, z);
        }

        public void translate(MrMatrix4f result, MrMatrix4f m, float x, float y, float z) {
            result.copyValues(m);
            translate(result, x, y, z);
        }

        public void translate(MrMatrix4f result, MrVector3f v) {
            translate(result, v.x, v.y, v.z);
        }

        public void translate(MrMatrix4f result, MrMatrix4f m, MrVector3f v) {
            result.copyValues(m);
            translate(result, v.x, v.y, v.z);
        }

        public void rotate(MrMatrix4f result, float angle, float x, float y, float z) {
            Matrix.rotateM(result.mValues, 0, angle, x, y, z);
        }

        public void rotate(MrMatrix4f result, MrMatrix4f m, float angle, float x, float y, float z) {
            result.copyValues(m);
            rotate(result, angle, x, y, z);
        }

        public void rotate(MrMatrix4f result, float angle, MrVector3f v) {
            rotate(result, angle, v.x, v.y, v.z);
        }

        public void rotate(MrMatrix4f result, MrMatrix4f m, float angle, MrVector3f v) {
            result.copyValues(m);
            rotate(result, angle, v);
        }

        public void rotate(MrMatrix4f result, MrQuaternion q) {
            fromQuaternion(opRotQuat, q);
            mult(result, result, opRotQuat);
        }

        public void rotate(MrMatrix4f result, MrMatrix4f m, MrQuaternion q) {
            result.copyValues(m);
            rotate(result, q);
        }

        public void scale(MrMatrix4f result, float sx, float sy, float sz) {
            Matrix.scaleM(result.mValues, 0, sx, sy, sz);
        }

        public void scale(MrMatrix4f result, MrMatrix4f m, float sx, float sy, float sz) {
            result.copyValues(m);
            scale(result, sx, sy, sz);
        }

        public void scale(MrMatrix4f result, float s) {
            scale(result, s, s, s);
        }

        public void scale(MrMatrix4f result, MrMatrix4f m, float s) {
            result.copyValues(m);
            scale(result, s);
        }

        public void scale(MrMatrix4f result, MrVector3f v) {
            scale(result, v.x, v.y, v.z);
        }

        public void scale(MrMatrix4f result, MrMatrix4f m, MrVector3f v) {
            result.copyValues(m);
            scale(result, v);
        }

        public void fromQuaternion(MrMatrix4f result, MrQuaternion q) {
            setIdentity(result);
            float x, y, z, w;
            x = q.x;
            y = q.y;
            z = q.z;
            w = q.w;
            float xx = x * x;
            float xy = x * y;
            float xz = x * z;
            float xw = x * w;

            float yy = y * y;
            float yz = y * z;
            float yw = y * w;

            float zz = z * z;
            float zw = z * w;

            result.setValueAt(0, 0, 1 - 2 * (yy + zz));
            result.setValueAt(0, 1, 2 * (xy - zw));
            result.setValueAt(0, 2, 2 * (xz + yw));

            result.setValueAt(1, 0, 2 * (xy + zw));
            result.setValueAt(1, 1, 1 - 2 * (xx + zz));
            result.setValueAt(1, 2, 2 * (yz - xw));

            result.setValueAt(2, 0, 2 * (xz - yw));
            result.setValueAt(2, 1, 2 * (yz + xw));
            result.setValueAt(2, 2, 1 - 2 * (xx + yy));
        }

        //TODO: Use here a box element
        public void frustrum(MrMatrix4f result, float left, float right, float bottom, float top, float near, float far) {
            Matrix.frustumM(result.mValues, 0, left, right, bottom, top, near, far);
        }

        public void ortho(MrMatrix4f result, float left, float right, float bottom, float top, float near, float far) {
            Matrix.orthoM(result.mValues, 0, left, right, bottom, top, near, far);
        }

        public void perspective(MrMatrix4f result, float fovy, float aspect, float zNear, float zFar) {
            //Matrix.perspectiveM(result.mValues,0,fovy,aspect,zNear,zFar);
            float xymax = (float) (zNear * Math.tan(fovy * Math.PI / 360.0f));
            float ymin = -xymax;
            float xmin = -xymax;

            float width = xymax - xmin;
            float height = xymax - ymin;

            float depth = zFar - zNear;
            float q = -(zFar + zNear) / depth;
            float qn = -2 * (zFar * zNear) / depth;

            float w = 2 * zNear / width;
            w = w / aspect;
            float h = 2 * zNear / height;

            float[] f = result.mValues;

            f[0] = w;
            f[5] = h;
            f[10] = q;
            f[11] = -1;
            f[14] = qn;
        }

        public void lookAt(MrMatrix4f result, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
            Matrix.setLookAtM(result.mValues, 0, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        }

        public void lookAt(MrMatrix4f result, MrVector3f eye, MrVector3f center, MrVector3f up) {
            lookAt(result,
                    eye.x, eye.y, eye.z,
                    center.x, center.y, center.z,
                    up.x, up.y, up.z);
        }
    }
}
