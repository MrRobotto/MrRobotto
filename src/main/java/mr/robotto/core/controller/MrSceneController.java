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

    private static MrUniformGenerator generateMVPMatrix() {
        return new MrUniformGenerator(MrUniformGenerator.MODEL_VIEW_PROJECTION_MATRIX) {
            /*@Override
            public MrLinearAlgebraObject generateUniform(MrSceneObjectsTree tree, MrUniformGeneratorMapView uniforms, MrObjectData object) {
                MrMatrix4f modelMatrix = (MrMatrix4f) uniforms.findByKey(MrUniform.MODEL_MATRIX);
                MrMatrix4f viewMatrix = (MrMatrix4f) uniforms.findByKey(MrUniform.VIEW_MATRIX);
                MrMatrix4f projectionMatrix = (MrMatrix4f) uniforms.findByKey(MrUniform.PROJECTION_MATRIX);
                MrMatrix4f mvp = new MrMatrix4f();
                MrMatrix4f.Operator op = MrMatrix4f.getOperator();
                op.mult(mvp, viewMatrix, modelMatrix);
                op.mult(mvp, projectionMatrix, mvp);
                return mvp;
            }*/

            @Override
            public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
                MrMatrix4f modelMatrix = (MrMatrix4f) uniforms.get(MrUniform.MODEL_MATRIX).getValue();
                MrMatrix4f viewMatrix = (MrMatrix4f) uniforms.get(MrUniform.VIEW_MATRIX).getValue();
                MrMatrix4f projectionMatrix = (MrMatrix4f) uniforms.get(MrUniform.PROJECTION_MATRIX).getValue();
                MrMatrix4f mvp = new MrMatrix4f();
                MrMatrix4f.Operator op = MrMatrix4f.getOperator();
                op.mult(mvp, viewMatrix, modelMatrix);
                op.mult(mvp, projectionMatrix, mvp);
                return mvp;
            }
        };
    }

    private static class MVPMatrixGenerator extends MrUniformGenerator {
        private final MrMatrix4f mMvp;

        public MVPMatrixGenerator() {
            super(MrUniformGenerator.MODEL_VIEW_PROJECTION_MATRIX);
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

    @Override
    public void initializeUniforms(Map<String, MrUniformGenerator> uniformGenerators) {
        super.initializeUniforms(uniformGenerators);
        uniformGenerators.put(MrUniformGenerator.MODEL_VIEW_PROJECTION_MATRIX, new MVPMatrixGenerator());
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
