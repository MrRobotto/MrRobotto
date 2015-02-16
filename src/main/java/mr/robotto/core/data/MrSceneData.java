/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data;

import mr.robotto.core.MrSceneObjectType;
import mr.robotto.linearalgebra.MrVector4f;

public class MrSceneData extends MrObjectData {
    private MrVector4f mClearColor;

    public MrSceneData(String name, MrVector4f clearColor) {
        super(name, MrSceneObjectType.SCENE);
        this.mClearColor = clearColor;
        init();
    }

    public MrSceneData(String name) {
        this(name, new MrVector4f(0.5f));
    }

    private void init() {

    }

    public MrVector4f getClearColor() {
        return mClearColor;
    }

    public void setClearColor(MrVector4f clearColor) {
        this.mClearColor = clearColor;
    }

    public void setClearColor(float r, float g, float b, float a) {
        this.mClearColor = new MrVector4f(r, g, b, a);
    }
}
