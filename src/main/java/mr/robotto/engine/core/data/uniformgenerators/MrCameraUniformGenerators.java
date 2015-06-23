/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.data.uniformgenerators;

import java.util.Map;

import mr.robotto.engine.components.data.uniformgenerator.MrUniformGenerator;
import mr.robotto.engine.components.data.uniformkey.MrUniformKey;
import mr.robotto.engine.core.data.MrCameraData;
import mr.robotto.engine.core.data.MrObjectData;
import mr.robotto.engine.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.engine.linearalgebra.MrMatrix4f;
import mr.robotto.engine.linearalgebra.MrVector3f;
import mr.robotto.engine.scenetree.MrObjectsDataTree;

/**
 * Created by aaron on 14/06/2015.
 */
public class MrCameraUniformGenerators implements MrObjectUniformsGenerators{

    @Override
    public void initializeUniforms(MrObjectData object, Map<String, MrUniformGenerator> uniformGenerators) {
        MrCameraData camera = (MrCameraData) object;
        uniformGenerators.put(MrUniformGenerator.GENERATOR_VIEW_MATRIX, new ViewMatrixGenerator());
        uniformGenerators.put(MrUniformGenerator.GENERATOR_PROJECTION_MATRIX, new ProjectionMatrixGenerator());
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
}
