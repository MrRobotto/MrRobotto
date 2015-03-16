/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller;

import mr.robotto.components.data.lens.MrLens;
import mr.robotto.components.data.lens.MrPerspectiveLens;
import mr.robotto.components.data.shader.MrUniform;
import mr.robotto.core.data.MrCameraData;
import mr.robotto.core.renderer.MrCameraRender;
import mr.robotto.core.renderer.MrObjectRender;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.linearalgebra.MrMatrix4f;
import mr.robotto.linearalgebra.MrVector3f;
import mr.robotto.renderer.uniformgenerator.MrUniformGenerator;
import mr.robotto.renderer.uniformgenerator.MrUniformGeneratorMap;
import mr.robotto.renderer.uniformgenerator.MrUniformGeneratorMapView;
import mr.robotto.scenetree.MrSceneObjectsTree;

/**
 * Created by Aarón on 01/12/2014.
 */
public class MrCamera extends MrObject {

    private MrMatrix4f mView;
    private MrMatrix4f mProjection;
    private MrCameraData mCameraData;
    static float x = 0.02f,y=0.13f,z=0.99f;

    public MrCamera(MrCameraData data, MrObjectRender render) {
        super(data, render);
        mCameraData = (MrCameraData) getData();
        mView = new MrMatrix4f();
        mProjection = new MrMatrix4f();
    }

    private static MrUniformGenerator generateViewMatrix(final MrCamera camera) {
        return new MrUniformGenerator(MrUniform.VIEW_MATRIX, MrUniformGenerator.OBJECT_LEVEL) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneObjectsTree tree, MrUniformGeneratorMapView uniforms, MrObject object) {
                MrMatrix4f.Operator op = MrMatrix4f.getOperator();
                MrVector3f.Operator opv = MrVector3f.getOperator();
                MrVector3f loc = camera.getTransform().getLocation();
                MrVector3f lookat = camera.getLookAt();
                //opv.add(lookat, lookat, loc);
                MrVector3f up = camera.getUp();
                op.lookAt(camera.mView, loc, lookat, up);
                //op.lookAt(camera.mView, camera.getTransform().getLocation(), camera.getLookAt(), new MrVector3f(0,1,0));
                //op.lookAt(camera.mView, camera.getTransform().getLocation(), new MrVector3f(x,y,z), new MrVector3f(0,1,0));
                return camera.mView;
            }
        };
    }

    private static MrUniformGenerator generateProjectionMatrix(final MrCamera camera) {
        return new MrUniformGenerator(MrUniform.PROJECTION_MATRIX, MrUniformGenerator.OBJECT_LEVEL) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneObjectsTree tree, MrUniformGeneratorMapView uniforms, MrObject object) {
                return camera.getLens().getProjectionMatrix();
                /*MrMatrix4f.Operator op = MrMatrix4f.getOperator();
                MrPerspectiveLens lens = (MrPerspectiveLens) camera.getLens();
                float f = 1080.0f/1701.0f;
                float aspect = lens.getAspectRatio();
                //TODO: Set the aspect ratio correctly
                op.perspective(camera.mProjection, lens.getFovy(), aspect, lens.getClipStart(), lens.getClipEnd());
                return camera.mProjection;*/
            }
        };
    }

    @Override
    public void initializeUniforms(MrUniformGeneratorMap uniformGenerators) {
        super.initializeUniforms(uniformGenerators);
        uniformGenerators.add(generateViewMatrix(this));
        uniformGenerators.add(generateProjectionMatrix(this));
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
        return mCameraData.getLookAt();
    }

    /*public void setLookAt(MrVector3f lookAt) {
        mCameraData.setLookAt(lookAt);
    }*/

    public MrVector3f getUp() {
        return mCameraData.getUp();
    }

    /*public void setUp(MrVector3f up) {
        mCameraData.setUp(up);
    }*/

    public MrLens getLens() {
        return mCameraData.getLens();
    }

    public void setLens(MrLens lens) {
        mCameraData.setLens(lens);
    }
}
