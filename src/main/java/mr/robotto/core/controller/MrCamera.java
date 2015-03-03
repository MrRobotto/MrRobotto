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
                //op.lookAt(camera.mView, new MrVector3f(0,-10,1.5f), new MrVector3f(), new MrVector3f(0,0,1));
                op.lookAt(camera.mView, camera.getTransform().getLocation(), camera.getLookAt(), camera.getUp());
                return camera.mView;
            }
        };
    }

    private static MrUniformGenerator generateProjectionMatrix(final MrCamera camera) {
        return new MrUniformGenerator(MrUniform.PROJECTION_MATRIX, MrUniformGenerator.OBJECT_LEVEL) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneObjectsTree tree, MrUniformGeneratorMapView uniforms, MrObject object) {
                //return camera.getLens().getProjectionMatrix();
                MrMatrix4f.Operator op = MrMatrix4f.getOperator();
                MrPerspectiveLens lens = (MrPerspectiveLens) camera.getLens();
                //1080.0f/1701.0f
                //TODO: Set the aspect ratio correctly
                op.perspective(camera.mProjection, lens.getFovy(), 1080.0f/1701.0f, lens.getClipStart(), lens.getClipEnd());
                return camera.mProjection;
            }
        };
    }

    @Override
    public void initializeUniforms(MrUniformGeneratorMap uniformGenerators) {
        super.initializeUniforms(uniformGenerators);
        uniformGenerators.add(generateViewMatrix(this));
        uniformGenerators.add(generateProjectionMatrix(this));
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
