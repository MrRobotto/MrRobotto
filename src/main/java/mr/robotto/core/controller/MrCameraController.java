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
import mr.robotto.components.data.shader.MrUniform;
import mr.robotto.components.data.uniformgenerator.MrUniformGenerator;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
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

    public MrCameraController(String name, MrTransform transform, MrUniformKeyMap uniformKeys, MrShaderProgram shaderProgram, MrLens lens) {
        super(new MrCameraData(name, transform, uniformKeys, shaderProgram, lens), new MrCameraRender());
        mProjection = new MrMatrix4f();
        mView = new MrMatrix4f();
    }


    private static MrUniformGenerator generateViewMatrix() {
        return new MrUniformGenerator(MrUniformGenerator.VIEW_MATRIX) {

            @Override
            public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, MrUniformKeyMap uniforms, MrObjectData object) {
                MrMatrix4f.Operator op = MrMatrix4f.getOperator();
                MrVector3f.Operator opv = MrVector3f.getOperator();
                MrCameraData camera = (MrCameraData) object;
                MrVector3f loc = camera.getTransform().getLocation();
                MrVector3f lookat = camera.getLookAt();
                MrMatrix4f view = new MrMatrix4f();
                //opv.add(lookat, lookat, loc);
                MrVector3f up = camera.getUp();
                op.lookAt(view, loc, lookat, up);
                MrVector3f right = camera.getTransform().getRight();
                //op.lookAt(camera.mView, camera.getTransform().getLocation(), camera.getLookAt(), new MrVector3f(0,1,0));
                //op.lookAt(camera.mView, camera.getTransform().getLocation(), new MrVector3f(x,y,z), new MrVector3f(0,1,0));
                return view;
            }
        };
    }

    private static MrUniformGenerator generateProjectionMatrix() {
        return new MrUniformGenerator(MrUniformGenerator.PROJECTION_MATRIX) {
            /*@Override
            public MrLinearAlgebraObject generateUniform(MrSceneObjectsTree tree, MrUniformGeneratorMapView uniforms, MrObjectData object) {
                return camera.getLens().getProjectionMatrix();
//                MrMatrix4f.Operator op = MrMatrix4f.getOperator();
//                MrPerspectiveLens lens = (MrPerspectiveLens) camera.getLens();
//                float f = 1080.0f/1701.0f;
//                float aspect = lens.getAspectRatio();
//                //TODO: Set the aspect ratio correctly
//                op.perspective(camera.mProjection, lens.getFovy(), aspect, lens.getClipStart(), lens.getClipEnd());
//                return camera.mProjection;
            }*/

            @Override
            public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, MrUniformKeyMap uniforms, MrObjectData object) {
                MrCameraData camera = (MrCameraData) object;
                return camera.getLens().getProjectionMatrix();
            }
        };
    }

    @Override
    public void initializeUniforms(Map<String, MrUniformGenerator> uniformGenerators) {
        super.initializeUniforms(uniformGenerators);
        uniformGenerators.put(MrUniformGenerator.VIEW_MATRIX, generateViewMatrix());
        uniformGenerators.put(MrUniformGenerator.PROJECTION_MATRIX, generateProjectionMatrix());
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
