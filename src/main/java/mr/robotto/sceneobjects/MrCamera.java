/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.sceneobjects;

import mr.robotto.engine.components.lens.MrLens;
import mr.robotto.engine.core.controller.MrCameraController;
import mr.robotto.engine.core.data.MrCameraData;
import mr.robotto.engine.core.renderer.MrCameraRender;
import mr.robotto.engine.linearalgebra.MrMatrix4f;
import mr.robotto.engine.linearalgebra.MrVector3f;

/**
 * Created by Aarón on 01/12/2014.
 */
public class MrCamera extends MrObject {

    public MrCamera(MrCameraController controller) {
        super(controller);
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

    public static class Builder extends MrObject.Builder<Builder> {
        private MrVector3f mLookAt;
        private MrVector3f mUp;
        private MrLens mLens;
        private MrCameraRender mRender = new MrCameraRender();

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

        public Builder setRender(MrCameraRender render) {
            mRender = render;
            return this;
        }

        @Override
        protected Builder getThis() {
            return this;
        }

        @Override
        public MrCamera build() {
            MrCameraData cameraData = new MrCameraData(mName);
            setObjectAttributes(cameraData);
            cameraData.setLens(mLens);
            cameraData.setLookAt(mLookAt);
            cameraData.setUp(mUp);
            MrCameraController controller = new MrCameraController(cameraData, mRender);
            return new MrCamera(controller);
        }
    }
}
