/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.resources;

import mr.robotto.renderer.linearalgebra.MrMatrix4f;
import mr.robotto.renderer.linearalgebra.MrQuaternion;
import mr.robotto.renderer.linearalgebra.MrVector3f;

public class MrTransform
{
    private MrMatrix4f matrix;
    private MrVector3f location;
    private MrQuaternion rotation;
    private MrVector3f scale;

    private MrVector3f forward;
    private MrVector3f up;
    private MrVector3f right;

    private boolean change;

    public MrTransform()
    {
        this.matrix = new MrMatrix4f();
        this.rotation = new MrQuaternion();
        this.location = new MrVector3f(0);
        this.scale = new MrVector3f(1);

        this.right = new MrVector3f(1,0,0);
        this.up = new MrVector3f(0,0,1);
        this.forward = new MrVector3f(0,1,0);

        flipChange();
    }

    public MrTransform(MrVector3f location, MrQuaternion rotation , MrVector3f scale) {
        this.location = location;
        this.rotation = rotation;
        this.scale = scale;
        flipChange();
    }

    public MrTransform(MrVector3f location, MrQuaternion rotation) {
        this.location = location;
        this.rotation = rotation;
        this.scale = new MrVector3f(1);
        flipChange();
    }

    /**Getters**/
    public MrQuaternion getRotation()
    {
        return rotation;
    }

    public MrVector3f getLocation()
    {
        return location;
    }

    public MrVector3f getScale()
    {
        return scale;
    }

    /**Setters**/
    public void setLocation(MrVector3f location) {
        this.location = location;
        flipChange();
    }

    public void setRotation(MrQuaternion rotation) {
        this.rotation = rotation;
        flipChange();
    }

    public void setRotation(float angle, float x, float y, float z)
    {
        MrQuaternion q = new MrQuaternion();
        MrQuaternion.ops.fromAngleAxis(q, angle, x, y, z);
        setRotation(q);
    }

    public void setRotation(float angle, MrVector3f axis)
    {
        MrQuaternion q = new MrQuaternion();
        MrQuaternion.ops.fromAngleAxis(q, angle, axis);
        setRotation(q);
    }

    public void setScale(MrVector3f scale) {
        this.scale = scale;
        flipChange();
    }

    public void setLookAt(MrVector3f look, MrVector3f up)
    {
        MrMatrix4f m = new MrMatrix4f();
        MrQuaternion q = new MrQuaternion();
        MrMatrix4f.ops.lookAt(m, location, look, up);
        MrQuaternion.ops.fromMatrix4(rotation, m);
        flipChange();
    }

    public void setLookAt(MrVector3f look)
    {
        setLookAt(look, up);
    }

    /**Methods**/
    public void translate(float x, float y, float z)
    {
        location.x += x;
        location.y += y;
        location.z += z;
        flipChange();
    }

    public void translate(MrVector3f v)
    {
        translate(v.x, v.y, v.z);
    }
      
    public void scale(float sx, float sy, float sz)
    {
        scale.setValues(sx,sy,sz);
        flipChange();
    }

    public void scale(float s)
    {
        scale(s,s,s);
    }

    public void scale(MrVector3f s)
    {
        scale(s.x,s.y,s.z);
    }

    public void rotate(MrQuaternion q)
    {
        MrQuaternion.ops.mult(rotation, rotation, q);
        flipChange();
    }

    public void rotate(float angle, float x, float y, float z)
    {
        MrQuaternion q = new MrQuaternion();
        MrQuaternion.ops.fromAngleAxis(q,angle,x,y,z);
        rotate(q);
    }

    public void rotate(float angle, MrVector3f axis)
    {
        rotate(angle, axis.x, axis.y, axis.z);
    }

    public void rotateAround(float angle, MrVector3f point, MrVector3f axis, MrVector3f through)
    {
        MrQuaternion q = new MrQuaternion();
        MrQuaternion.ops.fromAngleAxis(q,angle,axis);
        MrQuaternion.ops.mult(rotation, rotation, q);

        MrVector3f aux = new MrVector3f();
        //V-P
        MrVector3f.ops.substract(aux,through,point);
        //R(V-P)
        MrVector3f.ops.rotateVector(aux,rotation,aux);
        //Loc = P + R(V-P)
        MrVector3f.ops.add(location,point,aux);
        flipChange();
    }

    public void rotateAround(float angle, MrVector3f point, MrVector3f axis)
    {
        rotateAround(angle, point, axis, location);
    }


    /**Internal methods**/
    private void calcMatrix()
    {
        MrMatrix4f.ops.setIdentity(matrix);
        MrMatrix4f.ops.translate(matrix, location);
        MrMatrix4f.ops.rotate(matrix, rotation);
        MrMatrix4f.ops.scale(matrix,scale);
    }

    private void flipChange()
    {
        change = true;
    }

    private void checkChange()
    {
        if (change) {
            calcMatrix();
            transformLocalAxis();
            change = false;
        }
    }

    private void transformLocalAxis()
    {
        MrMatrix4f.ops.multV(forward,matrix,forward);
        MrMatrix4f.ops.multV(up,matrix,up);
        MrMatrix4f.ops.multV(right,matrix,right);
    }

    //TODO: Remove this method
    public MrMatrix4f getAsMatrix()
    {
        checkChange();
        return matrix;
    }
}
