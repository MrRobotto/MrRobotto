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

import mr.robotto.engine.components.MrShaderProgram;
import mr.robotto.engine.components.data.uniformkey.MrUniformKey;
import mr.robotto.engine.core.controller.MrSceneController;
import mr.robotto.engine.linearalgebra.MrTransform;
import mr.robotto.engine.linearalgebra.MrVector4f;

public class MrScene extends MrObject {
    public MrScene(String name, MrTransform transform, MrShaderProgram program, Map<String, MrUniformKey> uniformKeys, MrVector4f clearColor) {
        super(new MrSceneController(name, transform, program, uniformKeys, clearColor));
    }

    @Override
    public MrSceneController getController() {
        return (MrSceneController) super.getController();
    }

    public MrVector4f getClearColor() {
        return getController().getClearColor();
    }

    public void setClearColor(MrVector4f clearColor) {
        getController().setClearColor(clearColor);
    }

    public void setClearColor(float r, float g, float b, float a) {
        getController().setClearColor(r, g, b, a);
    }

    /**
     * Created by aaron on 16/06/2015.
     */
    public static class Builder extends MrObjectBuilder {
        private MrVector4f mClearColor = new MrVector4f(0.5f, 0.5f, 0.5f, 0.5f);

        @Override
        public Builder setName(String name) {
            return (Builder) super.setName(name);
        }

        @Override
        public Builder setTransform(MrTransform transform) {
            return (Builder) super.setTransform(transform);
        }

        @Override
        public Builder setShaderProgram(MrShaderProgram program) {
            return (Builder) super.setShaderProgram(program);
        }

        @Override
        public Builder setUniformKeys(Map<String, MrUniformKey> uniformKeys) {
            return (Builder) super.setUniformKeys(uniformKeys);
        }

        public Builder setClearColor(MrVector4f clearColor) {
            mClearColor = clearColor;
            return this;
        }

        public MrScene createScene() {
            return new MrScene(mName, mTransform, mShaderProgram, mUniformKeys, mClearColor);
        }
    }
}
