/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.sceneobjects;

import mr.robotto.engine.core.controller.MrLightController;
import mr.robotto.engine.core.data.MrLightData;
import mr.robotto.engine.core.renderer.MrLightRender;
import mr.robotto.engine.linearalgebra.MrVector3f;

/**
 * Created by aaron on 11/05/2015.
 */
public class MrLight extends MrObject {

    public MrLight(MrLightController controller) {
        super(controller);
    }

    public static class Builder extends MrObject.Builder<Builder> {
        private MrVector3f mLightColor;
        private MrLightRender mRender = new MrLightRender();

        @Override
        protected Builder getThis() {
            return this;
        }

        public Builder setLightColor(MrVector3f lightColor) {
            mLightColor = lightColor;
            return this;
        }

        public Builder setRender(MrLightRender render) {
            mRender = render;
            return this;
        }

        @Override
        public MrLight build() {
            MrLightData lightData = new MrLightData(mName);
            setObjectAttributes(lightData);
            lightData.setColor(mLightColor);
            MrLightController controller = new MrLightController(lightData, mRender);
            return new MrLight(controller);
        }
    }
}
