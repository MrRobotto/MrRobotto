/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller;

import mr.robotto.components.data.shader.MrUniform;
import mr.robotto.core.data.MrSceneData;
import mr.robotto.core.renderer.MrObjectRender;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.linearalgebra.MrMatrix4f;
import mr.robotto.renderer.uniformgenerator.MrUniformGenerator;
import mr.robotto.renderer.uniformgenerator.MrUniformGeneratorMap;
import mr.robotto.renderer.uniformgenerator.MrUniformGeneratorMapView;
import mr.robotto.scenetree.MrSceneObjectsTree;

public class MrScene extends MrObject {
    public MrScene(MrSceneData data, MrObjectRender render) {
        super(data, render);
    }

    private static MrUniformGenerator generateMVPMatrix() {
        return new MrUniformGenerator(MrUniform.MODEL_VIEW_PROJECTION_MATRIX, MrUniformGenerator.SCENE_LEVEL) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneObjectsTree tree, MrUniformGeneratorMapView uniforms, MrObject object) {
                /*MrMatrix4f.Operator op = MrMatrix4f.getOperator();
                MrMatrix4f m1 = (MrMatrix4f) uniforms.findByKey("Matrix_Model");
                MrMatrix4f m2 = new MrMatrix4f();
                m2.copyValues(m1);
                op.translate(m2, 1, 0, 0);
                return m2;*/
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
    public void initializeUniforms(MrUniformGeneratorMap uniformGenerators) {
        super.initializeUniforms(uniformGenerators);
        uniformGenerators.add(generateMVPMatrix());
    }
}
