/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.uniformgenerators;

import java.util.Map;

import mr.robotto.engine.components.shader.MrUniform;
import mr.robotto.engine.components.uniformkey.MrUniformKey;
import mr.robotto.engine.core.data.MrObjectData;
import mr.robotto.engine.core.data.MrSceneData;
import mr.robotto.engine.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.engine.linearalgebra.MrMatrix4f;
import mr.robotto.engine.scenetree.MrObjectsDataTree;

/**
 * Created by aaron on 14/06/2015.
 */
public class MrSceneUniformsGeneratorManager implements MrUniformsGeneratorManager {

    @Override
    public void initializeUniforms(MrObjectData object, Map<String, MrUniformKey.Generator> uniformGenerators) {
        MrSceneData scene = (MrSceneData) object;
        uniformGenerators.put(MrUniformKey.GENERATOR_MODEL_VIEW_PROJECTION_MATRIX, new MVPMatrixGenerator());
        uniformGenerators.put(MrUniformKey.GENERATOR_MODEL_VIEW_MATRIX, new ModelViewMatrixGenerator());
        //uniformGenerators.put(MrUniformGenerator.GENERATOR_NORMAL_MATRIX, new NormalMatrixGenerator());
    }

    private static class MVPMatrixGenerator implements MrUniformKey.Generator {
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

    private static class ModelViewMatrixGenerator implements MrUniformKey.Generator {
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

    private static class NormalMatrixGenerator implements MrUniformKey.Generator {
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
}
