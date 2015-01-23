/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.linearalgebra;

import android.opengl.Matrix;

import mr.robotto.commons.MrDataType;

public class MrMatrix4f implements MrLinearAlgebraObject
{
    private float[] values;

    /**
     * Gets the a new matrix set to identity
     */
    public MrMatrix4f()
    {
        init();
    }

    public MrMatrix4f(MrMatrix4f m)
    {
        init();
        copyValues(m);
    }

    public MrMatrix4f(float[] values)
    {
        init();
        setValues(values);
    }

    public static Operator getOperator() {
        return new Operator();
    }

    private void init() {
        values = new float[16];
        getOperator().setIdentity(this);
    }

    @Override
    public int getCount() {
        return 1;
    }

    /**
     * Get matrix values as a array
     *
     * @return values of the matrix
     */
    @Override
    public float[] getValues() {
        return values;
    }

    public void setValues(float[] values) {
        System.arraycopy(values, 0, this.values, 0, 16);
    }

    @Override
    public MrDataType getDataType() {
        return MrDataType.MAT4;
    }

    /**
     * Set the value in the matrix
     * @param i index of matrix seen as an array of 16 elements
     * @param f new value
     */
    private void setValueAt(int i, float f) {
        values[i] = f;
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
        return values[k];
    }

    public void copyValues(MrMatrix4f m) {
        setValues(m.values);
    }

    @Override
    public String toString() {
        String str = "Matrix:\n";
        for (int i = 0; i < 4; i++) {
            str += "|";
            for (int j = 0; j < 4; j++) {
                int k = 4 * j + i;
                str += values[k] + ", ";
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

        private Operator() {

        }

        public void setIdentity(MrMatrix4f result) {
            Matrix.setIdentityM(result.values, 0);
        }

        //TODO: Check invertible matrix
        public void invert(MrMatrix4f result, MrMatrix4f m) {
            Matrix.invertM(result.values, 0, m.values, 0);
        }

        public void transpose(MrMatrix4f result, MrMatrix4f m) {
            Matrix.transposeM(result.values, 0, m.values, 0);
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
                result.values[i] = m1.values[i] + m2.values[i];
            }
        }

        public void substract(MrMatrix4f result, MrMatrix4f m1, MrMatrix4f m2) {
            for (int i = 0; i < 16; i++) {
                result.values[i] = m1.values[i] - m2.values[i];
            }
        }

        public void mult(MrMatrix4f result, MrMatrix4f m1, MrMatrix4f m2) {
            op1Mult.copyValues(m1);
            op2Mult.copyValues(m2);
            Matrix.multiplyMM(result.values, 0, op1Mult.values, 0, op2Mult.values, 0);
        }

        //TODO: Change these to vector classes
        public void multV(MrVector4f result, MrMatrix4f m, MrVector4f v) {
            opMultV4.copyValues(v);
            Matrix.multiplyMV(result.getValues(), 0, m.values, 0, opMultV4.getValues(), 0);
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
            for (int i = 0; i < result.values.length; i++) {
                result.values[i] = s * result.values[i];
            }
        }

        public void translate(MrMatrix4f result, float x, float y, float z) {
            Matrix.translateM(result.values, 0, x, y, z);
        }

        public void translate(MrMatrix4f result, MrVector3f v) {
            translate(result, v.x, v.y, v.z);
        }

        //TODO: Quizas haya que cambiar el nombre por fromAngleAxis
        public void rotate(MrMatrix4f result, float angle, float x, float y, float z) {
            Matrix.rotateM(result.values, 0, angle, x, y, z);
        }

        public void rotate(MrMatrix4f result, float angle, MrVector3f v) {
            rotate(result, angle, v.x, v.y, v.z);
        }

        //TODO: Esto no funciona, el opsAuxMatrix es sobreescrito
        public void rotate(MrMatrix4f result, MrQuaternion q) {
            fromQuaternion(opRotQuat, q);
            mult(result, result, opRotQuat);
        }

        public void scale(MrMatrix4f result, float sx, float sy, float sz) {
            Matrix.scaleM(result.values, 0, sx, sy, sz);
        }

        public void scale(MrMatrix4f result, float s) {
            scale(result, s, s, s);
        }

        public void scale(MrMatrix4f result, MrVector3f v) {
            scale(result, v.x, v.y, v.z);
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
            Matrix.frustumM(result.values, 0, left, right, bottom, top, near, far);
        }

        public void ortho(MrMatrix4f result, float left, float right, float bottom, float top, float near, float far) {
            Matrix.orthoM(result.values, 0, left, right, bottom, top, near, far);
        }

        public void perspective(MrMatrix4f result, float fovy, float aspect, float zNear, float zFar) {
            //Matrix.perspectiveM(result.values,0,fovy,aspect,zNear,zFar);
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

            float[] f = result.values;

            f[0] = w;
            f[5] = h;
            f[10] = q;
            f[11] = -1;
            f[14] = qn;
        }

        public void lookAt(MrMatrix4f result, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
            Matrix.setLookAtM(result.values, 0, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        }

        public void lookAt(MrMatrix4f result, MrVector3f eye, MrVector3f center, MrVector3f up) {
            lookAt(result,
                    eye.x, eye.y, eye.z,
                    center.x, center.y, center.z,
                    up.x,up.y,up.z);
        }
    }
}
