/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.linearalgebra;

//TODO: Evade all news used in methods
//TODO: Maybe create a transform operation just like in matrix, vector,...
public class MrTransform {
    private MrMatrix4f mMatrix;
    private MrVector3f mLocation;
    private MrQuaternion mRotation;
    private MrVector3f mScale;

    private MrVector3f mForward;
    private MrVector3f mUp;
    private MrVector3f mRight;

    private boolean mChange;

    public MrTransform() {
        this.mMatrix = new MrMatrix4f();
        this.mRotation = new MrQuaternion();
        this.mLocation = new MrVector3f(0);
        this.mScale = new MrVector3f(1);

        //This seems to work
        this.mRight = new MrVector3f(1, 0, 0);
        this.mUp = new MrVector3f(0, 1, 0);
        this.mForward = new MrVector3f(0, 0, -1);

        //This is not opengl
        //mRight = new MrVector3f(1,0,0);
        //mUp = new MrVector3f(0, 0, 1);
        //mForward = new MrVector3f(0, 1, 0);

        //This is blender
        //mRight = new MrVector3f(1,0,0);
        //mUp = new MrVector3f(0, 0, 1);
        //mForward = new MrVector3f(0, -1, 0);

        flipChange();
    }

    public MrTransform(MrVector3f location, MrQuaternion rotation, MrVector3f scale) {
        this.mLocation = location;
        this.mRotation = rotation;
        this.mScale = scale;
        flipChange();
    }

    public MrTransform(MrVector3f location, MrQuaternion rotation) {
        this.mLocation = location;
        this.mRotation = rotation;
        this.mScale = new MrVector3f(1);
        flipChange();
    }

    /**
     * Getters*
     */
    public MrQuaternion getRotation() {
        return mRotation;
    }

    public void setRotation(MrQuaternion rotation) {
        this.mRotation = rotation;
        flipChange();
    }

    public MrVector3f getLocation() {
        return mLocation;
    }

    public MrVector3f getUp() {
        checkChange();
        return mUp;
    }

    public MrVector3f getRight() {
        checkChange();
        return mRight;
    }

    public MrVector3f getForward() {
        checkChange();
        return mForward;
    }

    /**
     * Setters*
     */
    public void setLocation(MrVector3f location) {
        mLocation = location;
        flipChange();
    }

    public MrVector3f getScale() {
        return mScale;
    }

    public void setScale(MrVector3f scale) {
        mScale = scale;
        flipChange();
    }

    public void setLocation(float x, float y, float z) {
        mLocation.x = x;
        mLocation.y = y;
        mLocation.z = z;
        flipChange();
    }

    public void setScale(float sx, float sy, float sz) {
        mScale.x = sx;
        mScale.y = sy;
        mScale.z = sz;
        flipChange();
    }

    public void setRotation(float angle, float x, float y, float z) {
        MrQuaternion.Operator quatOp = MrQuaternion.getOperator();
        MrQuaternion q = new MrQuaternion();
        quatOp.fromAngleAxis(q, angle, x, y, z);
        setRotation(q);
    }

    public void setRotation(float angle, MrVector3f axis) {
        MrQuaternion.Operator quatOp = MrQuaternion.getOperator();
        MrQuaternion q = new MrQuaternion();
        quatOp.fromAngleAxis(q, angle, axis);
        setRotation(q);
    }

    public void setLookAt(MrVector3f look, MrVector3f up) {
        MrMatrix4f.Operator mat4Op = MrMatrix4f.getOperator();
        MrQuaternion.Operator quatOp = MrQuaternion.getOperator();
        MrMatrix4f m = new MrMatrix4f();
        MrQuaternion q = new MrQuaternion();
        mat4Op.lookAt(m, mLocation, look, up);
        quatOp.fromMatrix4(mRotation, m);
        flipChange();
    }

    public void setLookAt(MrVector3f look) {
        setLookAt(look, mUp);
    }

    /**
     * Methods*
     */
    public void translate(float x, float y, float z) {
        mLocation.x += x;
        mLocation.y += y;
        mLocation.z += z;
        flipChange();
    }

    public void translate(MrVector3f v) {
        translate(v.x, v.y, v.z);
    }

    public void scale(float sx, float sy, float sz) {
        mScale.setValues(sx, sy, sz);
        flipChange();
    }

    public void scale(float s) {
        scale(s, s, s);
    }

    public void scale(MrVector3f s) {
        scale(s.x, s.y, s.z);
    }

    public void rotate(MrQuaternion q) {
        MrQuaternion.Operator quatOp = MrQuaternion.getOperator();
        quatOp.mult(mRotation, mRotation, q);
        flipChange();
    }

    public void rotate(float angle, float x, float y, float z) {
        MrQuaternion.Operator quatOp = MrQuaternion.getOperator();
        MrQuaternion q = new MrQuaternion();
        quatOp.fromAngleAxis(q, angle, x, y, z);
        rotate(q);
    }

    public void rotate(float angle, MrVector3f axis) {
        rotate(angle, axis.x, axis.y, axis.z);
    }

    public void rotateAround(float angle, MrVector3f point, MrVector3f axis, MrVector3f through) {
        MrQuaternion.Operator quatOp = MrQuaternion.getOperator();
        MrVector3f.Operator vec3Op = MrVector3f.getOperator();
        MrQuaternion q = new MrQuaternion();
        quatOp.fromAngleAxis(q, angle, axis);
        quatOp.mult(mRotation, mRotation, q);

        MrVector3f aux = new MrVector3f();
        //V-P
        vec3Op.substract(aux, through, point);
        //R(V-P)
        vec3Op.rotateVector(aux, mRotation, aux);
        //Loc = P + R(V-P)
        vec3Op.add(mLocation, point, aux);
        flipChange();
    }

    public void rotateAround(float angle, MrVector3f point, MrVector3f axis) {
        rotateAround(angle, point, axis, mLocation);
    }


    /**
     * Internal methods*
     */

    private void flipChange() {
        mChange = true;
    }

    private void calcMatrix() {
        MrMatrix4f.Operator mat4Op = MrMatrix4f.getOperator();
        mat4Op.setIdentity(mMatrix);
        mat4Op.translate(mMatrix, mLocation);
        mat4Op.rotate(mMatrix, mRotation);
        mat4Op.scale(mMatrix, mScale);
    }

    private void transformLocalAxis() {
        MrMatrix4f.Operator mat4Op = MrMatrix4f.getOperator();
        mat4Op.multV(mForward, mMatrix, mForward);
        mat4Op.multV(mUp, mMatrix, mUp);
        mat4Op.multV(mRight, mMatrix, mRight);
    }

    private void checkChange() {
        if (mChange) {
            calcMatrix();
            transformLocalAxis();
            mChange = false;
        }
    }

    /*private void checkChange() {
        calcMatrix();
        transformLocalAxis();
    }*/

    //TODO: Remove this method
    public MrMatrix4f getAsMatrix() {
        checkChange();
        return mMatrix;
    }
}
