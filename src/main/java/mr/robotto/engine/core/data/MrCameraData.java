/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.data;

import java.util.Map;

import mr.robotto.engine.components.MrShaderProgram;
import mr.robotto.engine.components.data.lens.MrLens;
import mr.robotto.engine.components.data.uniformkey.MrUniformKey;
import mr.robotto.engine.core.MrSceneObjectType;
import mr.robotto.engine.core.data.uniformgenerators.MrCameraUniformGenerators;
import mr.robotto.engine.linearalgebra.MrTransform;
import mr.robotto.engine.linearalgebra.MrVector3f;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrCameraData extends MrObjectData {

    private MrVector3f mLookAt;
    private MrVector3f mUp;
    private MrLens mLens;

    private MrCameraData(String name, MrTransform transform, MrShaderProgram program, Map<String, MrUniformKey> uniformKeys, MrVector3f lookAt, MrVector3f up, MrLens lens) {
        super(name, MrSceneObjectType.CAMERA, transform, program, uniformKeys);
        mLookAt = lookAt;
        mUp = up;
        mLens = lens;
        init();
    }

    private void init() {
        mObjectUniformsGenerators = new MrCameraUniformGenerators();
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

    public void setLookAt(MrVector3f lookAt) {
        mLookAt = lookAt;
    }

    public MrVector3f getUp() {
        if (mUp != null) {
            return mUp;
        }
        return mTransform.getUp();
    }

    public void setUp(MrVector3f up) {
        mUp = up;
    }

    public MrLens getLens() {
        return mLens;
    }

    public void setLens(MrLens lens) {
        mLens = lens;
    }

    public static class Builder extends MrObjectData.BuilderBase {
        private MrVector3f mLookAt;
        private MrVector3f mUp;
        private MrLens mLens;

        public Builder setLookAt(MrVector3f lookAt) {
            mLookAt = lookAt;
            return this;
        }

        public Builder setUp(MrVector3f up) {
            mUp = up;
            return this;
        }

        public Builder setLens(MrLens lens) {
            mLens = lens;
            return this;
        }

        public MrCameraData build() {
            return new MrCameraData(mName, mTransform, mProgram, mUniformKeys, mLookAt, mUp, mLens);
        }
    }
}
