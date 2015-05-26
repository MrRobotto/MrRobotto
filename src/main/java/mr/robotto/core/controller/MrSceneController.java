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
import mr.robotto.components.data.shader.MrUniform;
import mr.robotto.components.data.uniformgenerator.MrUniformGenerator;
import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.core.data.MrSceneData;
import mr.robotto.core.renderer.MrSceneRender;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.linearalgebra.MrMatrix4f;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector4f;
import mr.robotto.scenetree.MrObjectsDataTree;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrSceneController extends MrObjectController {

    public MrSceneController(String name, MrTransform transform, MrShaderProgram program, Map<String, MrUniformKey> uniformKeys, MrVector4f clearColor) {
        super(new MrSceneData(name, transform, program, uniformKeys, clearColor), new MrSceneRender());
    }

    private static class MVPMatrixGenerator extends MrUniformGenerator {
        private final MrMatrix4f mMvp;

        public MVPMatrixGenerator() {
            mMvp = new MrMatrix4f();
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            MrMatrix4f modelMatrix = (MrMatrix4f) uniforms.get(MrUniform.MODEL_MATRIX).getValue();
            MrMatrix4f viewMatrix = (MrMatrix4f) uniforms.get(MrUniform.VIEW_MATRIX).getValue();
            MrMatrix4f projectionMatrix = (MrMatrix4f) uniforms.get(MrUniform.PROJECTION_MATRIX).getValue();
            MrMatrix4f.Operator op = MrMatrix4f.getOperator();
            op.mult(mMvp, viewMatrix, modelMatrix);
            op.mult(mMvp, projectionMatrix, mMvp);
            return mMvp;
        }
    }

    private static class ModelViewMatrixGenerator extends MrUniformGenerator {
        private final MrMatrix4f mModelView;

        public ModelViewMatrixGenerator() {
            mModelView = new MrMatrix4f();
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            MrMatrix4f model = (MrMatrix4f) uniforms.get(MrUniform.MODEL_MATRIX).getValue();
            MrMatrix4f view = (MrMatrix4f) uniforms.get(MrUniform.VIEW_MATRIX).getValue();
            MrMatrix4f.Operator op = MrMatrix4f.getOperator();
            op.mult(mModelView, view, model);
            return mModelView;
        }
    }

    private static class NormalMatrixGenerator extends MrUniformGenerator {
        private final MrMatrix4f mNormal;

        public NormalMatrixGenerator() {
            mNormal = new MrMatrix4f();
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            MrMatrix4f.Operator op = MrMatrix4f.getOperator();
            MrMatrix4f viewmodel = (MrMatrix4f) uniforms.get(MrUniform.MODEL_VIEW_MATRIX).getValue();
            op.invert(mNormal, viewmodel);
            op.transpose(mNormal, mNormal);
            return mNormal;
        }
    }

    @Override
    public void initializeUniforms(Map<String, MrUniformGenerator> uniformGenerators) {
        super.initializeUniforms(uniformGenerators);
        uniformGenerators.put(MrUniformGenerator.GENERATOR_MODEL_VIEW_PROJECTION_MATRIX, new MVPMatrixGenerator());
        uniformGenerators.put(MrUniformGenerator.GENERATOR_MODEL_VIEW_MATRIX, new ModelViewMatrixGenerator());
        uniformGenerators.put(MrUniformGenerator.GENERATOR_NORMAL_MATRIX, new NormalMatrixGenerator());
    }

    public MrVector4f getClearColor() {
        return ((MrSceneData) mData).getClearColor();
    }

    public void setClearColor(MrVector4f clearColor) {
        ((MrSceneData) mData).setClearColor(clearColor);
    }

    public void setClearColor(float r, float g, float b, float a) {
        ((MrSceneData) mData).setClearColor(r, g, b, a);
    }
}
