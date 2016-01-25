/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.data;

import mr.robotto.engine.core.MrSceneObjectType;
import mr.robotto.engine.linearalgebra.MrVector4f;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrSceneData extends MrObjectData {
    private MrVector4f mClearColor;

    public MrSceneData(String name) {
        super(name, MrSceneObjectType.SCENE);
    }

    public MrVector4f getClearColor() {
        return mClearColor;
    }

    public void setClearColor(MrVector4f clearColor) {
        mClearColor = clearColor;
    }

    public void setClearColor(float r, float g, float b, float a) {
        mClearColor = new MrVector4f(r, g, b, a);
    }
}
