/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.sceneobjects;

import java.util.Map;

import mr.robotto.engine.components.lens.MrLens;
import mr.robotto.engine.components.shader.MrShaderProgram;
import mr.robotto.engine.components.uniformkey.MrUniformKey;
import mr.robotto.engine.core.controller.MrCameraController;
import mr.robotto.engine.linearalgebra.MrMatrix4f;
import mr.robotto.engine.linearalgebra.MrTransform;
import mr.robotto.engine.linearalgebra.MrVector3f;

/**
 * Created by Aarón on 01/12/2014.
 */
public class MrCamera extends MrObject {

    public MrCamera(String name, MrTransform transform, Map<String, MrUniformKey> uniformKeys, MrShaderProgram shaderProgram, MrLens lens) {
        super(new MrCameraController(name, transform, uniformKeys, shaderProgram, lens));
    }

    @Override
    public MrCameraController getController() {
        return (MrCameraController) super.getController();
    }

    public MrLens getLens() {
        return getController().getLens();
    }

    public void setLens(MrLens lens) {
        getController().setLens(lens);
    }

    public MrMatrix4f getViewMatrix() {
        return getController().getViewMatrix();
    }

    public MrVector3f getLookAt() {
        return getController().getLookAt();
    }

    /**
     * Created by aaron on 16/06/2015.
     */
    public static class Builder extends MrObjectBuilder {
        private MrLens mLens;

        @Override
        public Builder setName(String name) {
            return (Builder) super.setName(name);
        }

        @Override
        public Builder setTransform(MrTransform transform) {
            return (Builder) super.setTransform(transform);
        }

        @Override
        public Builder setUniformKeys(Map<String, MrUniformKey> uniformKeys) {
            return (Builder) super.setUniformKeys(uniformKeys);
        }

        @Override
        public Builder setShaderProgram(MrShaderProgram shaderProgram) {
            return (Builder) super.setShaderProgram(shaderProgram);
        }

        public Builder setLens(MrLens lens) {
            mLens = lens;
            return this;
        }

        public MrCamera createCamera() {
            return new MrCamera(mName, mTransform, mUniformKeys, mShaderProgram, mLens);
        }
    }
}
