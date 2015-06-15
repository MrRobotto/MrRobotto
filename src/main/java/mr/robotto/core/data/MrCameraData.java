/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data;

import java.util.Map;

import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.data.lens.MrLens;
import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.core.MrSceneObjectType;
import mr.robotto.core.data.uniformgenerators.MrCameraUniformGenerators;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector3f;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrCameraData extends MrObjectData {

    private MrVector3f mLookAt;
    private MrVector3f mUp;
    private MrLens mLens;

    public MrCameraData(String name, MrTransform transform, Map<String, MrUniformKey> uniformKeys, MrShaderProgram shaderProgram, MrLens lens) {
        super(name, MrSceneObjectType.CAMERA, transform, shaderProgram, uniformKeys);
        mLens = lens;
        mLookAt = null;
        mUp = null;
    }

    @Override
    public void initializeUniforms() {
        super.initializeUniforms();
        new MrCameraUniformGenerators().initializeUniforms(this, mUniformGenerators);
    }

    public void setLookAt(MrVector3f lookAt) {
        mLookAt = lookAt;
    }

    public MrVector3f getLookAt() {
        if (mLookAt != null) {
            return mLookAt;
        }
        MrVector3f v = new MrVector3f();
        float clipEnd = mLens.getClipEnd();
        v.copyValues(mTransform.getForward());
        MrVector3f.getOperator().multScalar(v, clipEnd);
        return v;
    }

    public void setUp(MrVector3f up) {
        mUp = up;
    }


    public MrVector3f getUp() {
        if (mUp != null) {
            return mUp;
        }
        return mTransform.getUp();
    }


    public MrLens getLens() {
        return mLens;
    }

    public void setLens(MrLens lens) {
        mLens = lens;
    }
}
