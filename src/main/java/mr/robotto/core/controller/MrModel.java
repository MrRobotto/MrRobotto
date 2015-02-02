/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller;

import java.util.Iterator;

import mr.robotto.context.MrSceneObjectsTree;
import mr.robotto.core.controller.uniformgenerator.MrUniformGenerator;
import mr.robotto.core.controller.uniformgenerator.MrUniformGeneratorContainer;
import mr.robotto.core.data.MrModelData;
import mr.robotto.core.renderer.MrObjectRender;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.linearalgebra.MrMatrix4f;

public class MrModel extends MrObject {
    public MrModel(MrModelData data, MrObjectRender render) {
        super(data, render);
    }

    private static MrUniformGenerator generateModelMatrix(MrModel model) {
        return new MrUniformGenerator("Matrix_Model_View_Projection", MrUniformGenerator.OBJECT_LEVEL) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneObjectsTree tree, MrObject object) {
                MrMatrix4f m = new MrMatrix4f();
                MrMatrix4f.Operator op = MrMatrix4f.getOperator();
                Iterator<MrObject> it = tree.parentTraversal(object);
                while (it.hasNext()) {
                    op.mult(m, it.next().getTransform().getAsMatrix(), m);
                    //new UnsupportedOperationException("Not implemented yet");
                }
                //return m;
                //return new MrMatrix4f(new float[]{ -1.6310f,0,0,0,
                //                                    0,0.97014f,-0.29643f,-0.24253f,
                //                                    0,0.24253f,1.18572f,0.97014f,
                //                                    0,0,7.8564f,8.24621f});

                return new MrMatrix4f(new float[]{-1.6310f, 0, 0, 0,
                        0, 0.97014f, -0.29643f, -0.24253f,
                        0, 0.24253f, 1.18572f, 0.97014f,
                        0, 0, 7.8564f, 8.24621f});
                //MrMatrix4f.getOperator().transpose(aux, aux);
                //return new MrMatrix4f();
                //return new MrMatrix4f(new float[]{})
                //return m;
            }
        };
    }

    //public MrMatrix4f getModelMatrix() {
    //    return getData().getTransform().getAsMatrix();
    //}

    @Override
    public void initializeUniforms(MrUniformGeneratorContainer uniformGenerators) {
        super.initializeUniforms(uniformGenerators);
        uniformGenerators.add(generateModelMatrix(this));
    }
}
