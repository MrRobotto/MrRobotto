/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller;

import java.util.Map;

import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.data.lens.MrLens;
import mr.robotto.components.data.uniformgenerator.MrUniformGenerator;
import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.core.data.MrCameraData;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.core.renderer.MrCameraRender;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.linearalgebra.MrMatrix4f;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector3f;
import mr.robotto.scenetree.MrObjectsDataTree;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrCameraController extends MrObjectController {

    private MrMatrix4f mView;
    private MrMatrix4f mProjection;

    public MrCameraController(String name, MrTransform transform, Map<String, MrUniformKey> uniformKeys, MrShaderProgram shaderProgram, MrLens lens) {
        super(new MrCameraData(name, transform, uniformKeys, shaderProgram, lens), new MrCameraRender());
        mProjection = new MrMatrix4f();
        mView = new MrMatrix4f();
    }

    private static class ViewMatrixGenerator extends MrUniformGenerator {
        private final MrMatrix4f mView;

        public ViewMatrixGenerator() {
            mView = new MrMatrix4f();
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            MrMatrix4f.Operator op = MrMatrix4f.getOperator();
            MrCameraData camera = (MrCameraData) object;
            MrVector3f loc = camera.getTransform().getLocation();
            MrVector3f lookat = camera.getLookAt();
            MrVector3f up = camera.getUp();
            op.lookAt(mView, loc, lookat, up);
            return mView;
        }
    }

    private static class ProjectionMatrixGenerator extends MrUniformGenerator {
        public ProjectionMatrixGenerator() {
        }
        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            MrCameraData camera = (MrCameraData) object;
            return camera.getLens().getProjectionMatrix();
        }
    }

    @Override
    public void initializeUniforms(Map<String, MrUniformGenerator> uniformGenerators) {
        super.initializeUniforms(uniformGenerators);
        uniformGenerators.put(MrUniformGenerator.GENERATOR_VIEW_MATRIX, new ViewMatrixGenerator());
        uniformGenerators.put(MrUniformGenerator.GENERATOR_PROJECTION_MATRIX, new ProjectionMatrixGenerator());
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
