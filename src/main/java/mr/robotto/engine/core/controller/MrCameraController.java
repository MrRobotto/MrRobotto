/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.controller;

import java.util.Map;

import mr.robotto.engine.components.MrShaderProgram;
import mr.robotto.engine.components.data.lens.MrLens;
import mr.robotto.engine.components.data.uniformkey.MrUniformKey;
import mr.robotto.engine.core.data.MrCameraData;
import mr.robotto.engine.core.renderer.MrCameraRender;
import mr.robotto.engine.linearalgebra.MrMatrix4f;
import mr.robotto.engine.linearalgebra.MrTransform;
import mr.robotto.engine.linearalgebra.MrVector3f;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrCameraController extends MrObjectController {

    private MrMatrix4f mView;
    private MrMatrix4f mProjection;
    public MrCameraController(String name, MrTransform transform, Map<String, MrUniformKey> uniformKeys, MrShaderProgram shaderProgram, MrLens lens) {
        super(createCameraData(name, transform, uniformKeys, shaderProgram, lens), new MrCameraRender());
        mProjection = new MrMatrix4f();
        mView = new MrMatrix4f();
    }

    private static MrCameraData createCameraData(String name, MrTransform transform, Map<String, MrUniformKey> uniformKeys, MrShaderProgram program, MrLens lens) {
        MrCameraData.Builder builder = new MrCameraData.Builder();
        builder
                .setLens(lens)
                .setName(name)
                .setTransform(transform)
                .setProgram(program)
                .setUniformKeys(uniformKeys);
        return builder.build();
    }

    @Override
    public void initializeSizeDependant(int widthScreen, int heightScreen) {
        getLens().setDimension(widthScreen, heightScreen);
    }

    //TODO: Check this method
    public MrMatrix4f getViewMatrix() {
        return mView;
    }

    public MrVector3f getLookAt() {
        return ((MrCameraData)mData).getLookAt();
    }

    public MrVector3f getUp() {
        return ((MrCameraData)mData).getUp();
    }

    public MrLens getLens() {
        return ((MrCameraData)mData).getLens();
    }

    public void setLens(MrLens lens) {
        ((MrCameraData)mData).setLens(lens);
    }


}
