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
import mr.robotto.components.data.lens.MrLens;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.controller.MrCameraController;
import mr.robotto.linearalgebra.MrMatrix4f;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector3f;

/**
 * Created by Aarón on 01/12/2014.
 */
public class MrCamera extends MrObject {

    public MrCamera(String name, MrTransform transform, MrUniformKeyMap uniformKeys, MrShaderProgram shaderProgram, MrLens lens) {
        super(new MrCameraController(name, transform, uniformKeys, shaderProgram, lens));
    }

    @Override
    protected MrCameraController getController() {
        return (MrCameraController) super.getController();
    }

    public void setLens(MrLens lens) {
        getController().setLens(lens);
    }

    public MrLens getLens() {
        return getController().getLens();
    }

    public MrMatrix4f getViewMatrix() {
        return getController().getViewMatrix();
    }

    public MrVector3f getLookAt() {
        return getController().getLookAt();
    }
}
