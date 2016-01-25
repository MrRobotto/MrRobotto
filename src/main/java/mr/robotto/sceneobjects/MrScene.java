/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.sceneobjects;

import mr.robotto.engine.core.controller.MrSceneController;
import mr.robotto.engine.core.data.MrSceneData;
import mr.robotto.engine.core.renderer.MrSceneRender;
import mr.robotto.engine.linearalgebra.MrVector4f;

public class MrScene extends MrObject {

    public MrScene(MrSceneController controller) {
        super(controller);
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

    public static class Builder extends MrObject.Builder<Builder> {
        private MrVector4f mClearColor = new MrVector4f(0.5f);
        private MrSceneRender mRender = new MrSceneRender();

        @Override
        protected Builder getThis() {
            return this;
        }

        //TODO: Add set with 4 floats
        public Builder setClearColor(MrVector4f clearColor) {
            mClearColor = clearColor;
            return this;
        }

        public Builder setRender(MrSceneRender render) {
            mRender = render;
            return this;
        }

        @Override
        public MrScene build() {
            MrSceneData sceneData = new MrSceneData(mName);
            setObjectAttributes(sceneData);
            sceneData.setClearColor(mClearColor);
            MrSceneController controller = new MrSceneController(sceneData, mRender);
            return new MrScene(controller);
        }
    }
}
