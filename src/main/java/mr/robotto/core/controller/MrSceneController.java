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
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.core.data.MrSceneData;
import mr.robotto.core.renderer.MrSceneRender;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.linearalgebra.MrMatrix4f;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector4f;
import mr.robotto.scenetree.MrSceneTree;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrSceneController extends MrObjectController {

    public MrSceneController(String name, MrTransform transform, MrShaderProgram program, MrUniformKeyMap uniformKeys, MrVector4f clearColor) {
        super(new MrSceneData(name, transform, program, uniformKeys, clearColor), new MrSceneRender());
    }

    private static MrUniformGenerator generateMVPMatrix() {
        return new MrUniformGenerator(MrUniform.MODEL_VIEW_PROJECTION_MATRIX) {
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
            public MrLinearAlgebraObject generateUniform(MrSceneTree tree, MrUniformKeyMap.MrUniformKeyMapView uniforms, MrObjectData object) {
                MrMatrix4f modelMatrix = (MrMatrix4f) uniforms.findByKey(MrUniform.MODEL_MATRIX);
                MrMatrix4f viewMatrix = (MrMatrix4f) uniforms.findByKey(MrUniform.VIEW_MATRIX);
                MrMatrix4f projectionMatrix = (MrMatrix4f) uniforms.findByKey(MrUniform.PROJECTION_MATRIX);
                MrMatrix4f mvp = new MrMatrix4f();
                MrMatrix4f.Operator op = MrMatrix4f.getOperator();
                op.mult(mvp, viewMatrix, modelMatrix);
                op.mult(mvp, projectionMatrix, mvp);
                return mvp;
            }
        };
    }

    @Override
    public void initializeUniforms(Map<String, MrUniformGenerator> uniformGenerators) {
        super.initializeUniforms(uniformGenerators);
        uniformGenerators.put(MrUniform.MODEL_VIEW_PROJECTION_MATRIX, generateMVPMatrix());
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
