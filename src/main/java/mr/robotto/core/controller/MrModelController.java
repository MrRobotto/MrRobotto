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

import mr.robotto.commons.MrDataType;
import mr.robotto.components.comp.MrMesh;
import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.data.material.MrMaterial;
import mr.robotto.components.data.material.MrMaterialMap;
import mr.robotto.components.data.shader.MrUniform;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.MrObject;
import mr.robotto.core.data.MrModelData;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.core.renderer.MrModelRender;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.linearalgebra.MrLinearAlgebraObjectContainer;
import mr.robotto.linearalgebra.MrMatrix4f;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector4f;
import mr.robotto.components.data.uniformgenerator.MrUniformGenerator;
import mr.robotto.components.data.uniformgenerator.MrUniformGeneratorMap;
import mr.robotto.scenetree.MrSceneObjectsTree;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrModelController extends MrObjectController {

    public MrModelController(String name, MrTransform transform, MrUniformKeyMap uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh, MrMaterialMap materials) {
        super(new MrModelData(name, transform, uniformKeys, shaderProgram, mesh, materials), new MrModelRender());
    }

    private static MrUniformGenerator generateMaterialDiffuseColor(final MrModelController model) {
        return new MrUniformGenerator(MrUniform.UNIFORM_MATERIAL_DIFFUSE_COLOR) {
            /*@Override
            public MrLinearAlgebraObject generateUniform(MrSceneObjectsTree tree, MrUniformGeneratorMapView uniforms, MrObjectData object) {
                MrModelData model = (MrModelData) object;
                MrMaterialMap materials = model.getMaterials();
                MrLinearAlgebraObjectContainer container = new MrLinearAlgebraObjectContainer(MrDataType.VEC4, materials.size(), MrVector4f.SIZE);
                int  i = 0;
                for (MrMaterial material : materials) {
                    container.setAlgebraObject(i, material.getDiffuse().getColor());
                    i++;
                }
                return container;
            }*/

            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneObjectsTree tree, MrUniformKeyMap.MrUniformKeyMapView uniforms, MrObjectData object) {
                MrModelData model = (MrModelData) object;
                MrMaterialMap materials = model.getMaterials();
                MrLinearAlgebraObjectContainer container = new MrLinearAlgebraObjectContainer(MrDataType.VEC4, materials.size(), MrVector4f.SIZE);
                int  i = 0;
                for (MrMaterial material : materials) {
                    container.setAlgebraObject(i, material.getDiffuse().getColor());
                    i++;
                }
                return container;
            }
        };
    }

    //TODO: La primera pasada a es null, después ya no, en teoría el nivel de prioridad impide eso no?
    private static MrUniformGenerator generateModelMatrix(final MrModelController model) {
        return new MrUniformGenerator(MrUniform.MODEL_MATRIX) {
            /*@Override
            public MrLinearAlgebraObject generateUniform(MrSceneObjectsTree tree, MrUniformGeneratorMapView uniforms, MrObjectData object) {
                MrMatrix4f m = new MrMatrix4f();
                MrMatrix4f.Operator op = MrMatrix4f.getOperator();
                Iterator<MrObject> it = tree.parentTraversalByKey(object.getName());

                //MrTransform transform = it.next().getTransform();
                //op.mult(m, transform.getAsMatrix(), m);
                //transform = it.next().getTransform();
                //op.mult(m, transform.getAsMatrix(), m);
                while (it.hasNext()) {
                    op.mult(m, it.next().getTransform().getAsMatrix(), m);
                }
                return m;
                //return new MrMatrix4f(new float[]{ -1.6310f,0,0,0,
                //                                    0,0.97014f,-0.29643f,-0.24253f,
                //                                    0,0.24253f,1.18572f,0.97014f,
                //                                    0,0,7.8564f,8.24621f});
                //MrMatrix4f m2 = new MrMatrix4f();
                //op.multScalar(m2, m2 , 2);


                /*if (model.getName().equals("weapon2")) {
                    m = new MrMatrix4f(new float[]{-1.6310f, 0, 0, 0,
                            0, 0.97014f, -0.29643f, -0.24253f,
                            0, 0.24253f, 1.18572f, 0.97014f,
                            0, 0, 7.8564f, 8.24621f});
                    op.translate(m, -3, 0, 0);
                    return m;
                }
                m = new MrMatrix4f(new float[]{-1.6310f, 0, 0, 0,
                        0, 0.97014f, -0.29643f, -0.24253f,
                        0, 0.24253f, 1.18572f, 0.97014f,
                        0, 0, 7.8564f, 8.24621f});
                op.translate(m, -1, 0, 0);
                return m;
            }*/

            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneObjectsTree tree, MrUniformKeyMap.MrUniformKeyMapView uniforms, MrObjectData object) {
                MrMatrix4f m = new MrMatrix4f();
                MrMatrix4f.Operator op = MrMatrix4f.getOperator();
                Iterator<MrObject> it = tree.parentTraversalByKey(object.getName());

                //MrTransform transform = it.next().getTransform();
                //op.mult(m, transform.getAsMatrix(), m);
                //transform = it.next().getTransform();
                //op.mult(m, transform.getAsMatrix(), m);
                while (it.hasNext()) {
                    op.mult(m, it.next().getTransform().getAsMatrix(), m);
                }
                return m;
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
        uniformGenerators.add(generateMaterialDiffuseColor(this));
    }

    public MrMesh getMesh() {
        return ((MrModelData) mData).getMesh();
    }

    public MrMaterialMap getMaterials() {
        return ((MrModelData) mData).getMaterials();
    }

}
