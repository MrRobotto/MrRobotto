/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core;

import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.controller.MrSceneController;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector4f;

public class MrScene extends MrObject {
    public MrScene(String name, MrTransform transform, MrShaderProgram program, MrUniformKeyMap uniformKeys, MrVector4f clearColor) {
        super(new MrSceneController(name, transform, program, uniformKeys, clearColor));
    }

    @Override
    public MrSceneController getController() {
        return (MrSceneController) super.getController();
    }

    public void setClearColor(MrVector4f clearColor) {
        getController().setClearColor(clearColor);
    }

    public MrVector4f getClearColor() {
        return getController().getClearColor();
    }

    public void setClearColor(float r, float g, float b, float a) {
        getController().setClearColor(r, g, b, a);
    }
}
