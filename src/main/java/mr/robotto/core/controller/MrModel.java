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

import mr.robotto.core.data.MrModelData;
import mr.robotto.core.renderer.MrObjectRender;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.linearalgebra.MrMatrix4f;
import mr.robotto.proposed.aus.MrUniformGenerator;
import mr.robotto.proposed.aus.MrUniformGeneratorMap;
import mr.robotto.proposed.aus.MrUniformGeneratorMapView;
import mr.robotto.scenetree.MrSceneObjectsTree;

public class MrModel extends MrObject {
    public MrModel(MrModelData data, MrObjectRender render) {
        super(data, render);
    }

    //TODO: La primera pasada a es null, después ya no, en teoría el nivel de prioridad impide eso no?
    private static MrUniformGenerator generateModelMatrix(MrModel model) {
        return new MrUniformGenerator("Matrix_Model", MrUniformGenerator.OBJECT_LEVEL) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneObjectsTree tree, MrUniformGeneratorMapView uniforms, MrObject object) {
                MrMatrix4f m = new MrMatrix4f();
                MrMatrix4f.Operator op = MrMatrix4f.getOperator();
                Iterator<MrObject> it = tree.parentTraversal(object);
                //MrLinearAlgebraObject a = uniforms.findByKey("Matrix_Model_View_Projection");
                while (it.hasNext()) {
                    op.mult(m, it.next().getTransform().getAsMatrix(), m);
                    //new UnsupportedOperationException("Not implemented yet");
                }
                //return m;
                //return new MrMatrix4f(new float[]{ -1.6310f,0,0,0,
                //                                    0,0.97014f,-0.29643f,-0.24253f,
                //                                    0,0.24253f,1.18572f,0.97014f,
                //                                    0,0,7.8564f,8.24621f});
                //MrMatrix4f m2 = new MrMatrix4f();
                //op.multScalar(m2, m2 , 2);
                m = new MrMatrix4f(new float[]{-1.6310f, 0, 0, 0,
                        0, 0.97014f, -0.29643f, -0.24253f,
                        0, 0.24253f, 1.18572f, 0.97014f,
                        0, 0, 7.8564f, 8.24621f});
                //op.mult(m, m, m2);
                op.translate(m, -1, 0, 0);
                return m;
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
    public void initializeUniforms(MrUniformGeneratorMap uniformGenerators) {
        super.initializeUniforms(uniformGenerators);
        uniformGenerators.add(generateModelMatrix(this));
    }
}
