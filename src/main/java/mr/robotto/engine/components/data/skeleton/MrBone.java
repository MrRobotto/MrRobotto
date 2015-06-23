/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.data.skeleton;

import mr.robotto.engine.linearalgebra.MrMatrix4f;
import mr.robotto.engine.linearalgebra.MrQuaternion;
import mr.robotto.engine.linearalgebra.MrVector3f;

/**
 * Created by aaron on 24/04/2015.
 */
public class MrBone {
    private final String mName;
    private final MrVector3f mScale;
    private final MrVector3f mLocation;
    private final MrQuaternion mRotation;
    private final MrMatrix4f mMatrix;

    public MrBone(String name, MrVector3f location, MrQuaternion rotation, MrVector3f scale) {
        mName = name;
        mScale = scale;
        mLocation = location;
        mRotation = rotation;
        mMatrix = new MrMatrix4f();
    }


    public String getName() {
        return mName;
    }

    public MrVector3f getScale() {
        return mScale;
    }

    public MrVector3f getLocation() {
        return mLocation;
    }

    public MrQuaternion getRotation() {
        return mRotation;
    }

    public MrMatrix4f getBoneMatrix() {
        MrMatrix4f.Operator mat4Op = MrMatrix4f.getOperator();
        mat4Op.setIdentity(mMatrix);
        mat4Op.translate(mMatrix, mLocation);
        mat4Op.rotate(mMatrix, mRotation);
        mat4Op.scale(mMatrix, mScale);
        return mMatrix;
    }
}
