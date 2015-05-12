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
import java.util.Map;

import mr.robotto.commons.MrDataType;
import mr.robotto.components.comp.MrMesh;
import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.comp.MrTexture;
import mr.robotto.components.data.bone.MrBone;
import mr.robotto.components.data.bone.MrSkeleton;
import mr.robotto.components.data.material.MrMaterial;
import mr.robotto.components.data.shader.MrUniform;
import mr.robotto.components.data.uniformgenerator.MrUniformGenerator;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.MrObject;
import mr.robotto.core.data.MrModelData;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.core.renderer.MrModelRender;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.linearalgebra.MrLinearAlgebraObjectContainer;
import mr.robotto.linearalgebra.MrMatrix4f;
import mr.robotto.linearalgebra.MrSamplerIndices;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector4f;
import mr.robotto.scenetree.MrSceneTree;
import mr.robotto.scenetree.MrSceneTreeData;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrModelController extends MrObjectController {

    public MrModelController(String name, MrTransform transform, MrUniformKeyMap uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh, MrMaterial[] materials) {
        super(new MrModelData(name, transform, uniformKeys, shaderProgram, mesh, materials), new MrModelRender());
    }

    public MrModelController(String name, MrTransform transform, MrUniformKeyMap uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh, MrMaterial[] materials, MrSkeleton skeleton) {
        super(new MrModelData(name, transform, uniformKeys, shaderProgram, mesh, materials, skeleton), new MrModelRender());
    }

    private static MrUniformGenerator generateMaterialAmbientColor() {
        return new MrUniformGenerator(MrUniform.UNIFORM_MATERIAL_DIFFUSE_COLOR) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneTreeData.View tree, MrUniformKeyMap.View uniforms, MrObjectData object) {
                MrModelData model = (MrModelData) object;
                MrMaterial[] materials = model.getMaterials();
                MrLinearAlgebraObjectContainer container = new MrLinearAlgebraObjectContainer(MrDataType.VEC4, materials.length, MrVector4f.SIZE);
                int  i = 0;
                for (MrMaterial material : materials) {
                    container.setAlgebraObject(i, material.getAmbient().getColor());
                    i++;
                }
                return container;
            }
        };
    }

    private static MrUniformGenerator generateMaterialDiffuseColor() {
        return new MrUniformGenerator(MrUniform.UNIFORM_MATERIAL_DIFFUSE_COLOR) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneTreeData.View tree, MrUniformKeyMap.View uniforms, MrObjectData object) {
                MrModelData model = (MrModelData) object;
                MrMaterial[] materials = model.getMaterials();
                MrLinearAlgebraObjectContainer container = new MrLinearAlgebraObjectContainer(MrDataType.VEC4, materials.length, MrVector4f.SIZE);
                int  i = 0;
                for (MrMaterial material : materials) {
                    container.setAlgebraObject(i, material.getDiffuse().getColor());
                    i++;
                }
                return container;
            }
        };
    }

    private static MrUniformGenerator generateMaterialSpecularColor() {
        return new MrUniformGenerator(MrUniform.UNIFORM_MATERIAL_DIFFUSE_COLOR) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneTreeData.View tree, MrUniformKeyMap.View uniforms, MrObjectData object) {
                MrModelData model = (MrModelData) object;
                MrMaterial[] materials = model.getMaterials();
                MrLinearAlgebraObjectContainer container = new MrLinearAlgebraObjectContainer(MrDataType.VEC4, materials.length, MrVector4f.SIZE);
                int  i = 0;
                for (MrMaterial material : materials) {
                    container.setAlgebraObject(i, material.getSpecular().getColor());
                    i++;
                }
                return container;
            }
        };
    }

    private static MrUniformGenerator generateMaterialAmbientIntensity() {
        return new MrUniformGenerator(MrUniform.UNIFORM_MATERIAL_AMBIENT_INTENSITY) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneTreeData.View tree, MrUniformKeyMap.View uniforms, MrObjectData object) {
                MrModelData model = (MrModelData) object;
                MrMaterial[] materials = model.getMaterials();
                MrLinearAlgebraObjectContainer container = new MrLinearAlgebraObjectContainer(MrDataType.FLOAT, materials.length);
                int  i = 0;
                for (MrMaterial material : materials) {
                    container.setValue(i, material.getAmbient().getIntensity());
                    i++;
                }
                return container;
            }
        };
    }

    private static MrUniformGenerator generateMaterialSpecularIntensity() {
        return new MrUniformGenerator(MrUniform.UNIFORM_MATERIAL_SPECULAR_INTENSITY) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneTreeData.View tree, MrUniformKeyMap.View uniforms, MrObjectData object) {
                MrModelData model = (MrModelData) object;
                MrMaterial[] materials = model.getMaterials();
                MrLinearAlgebraObjectContainer container = new MrLinearAlgebraObjectContainer(MrDataType.FLOAT, materials.length);
                int  i = 0;
                for (MrMaterial material : materials) {
                    container.setValue(i, material.getSpecular().getIntensity());
                    i++;
                }
                return container;
            }
        };
    }

    private static MrUniformGenerator generateMaterialDiffuseIntensity() {
        return new MrUniformGenerator(MrUniform.UNIFORM_MATERIAL_DIFFUSE_INTENSITY) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneTreeData.View tree, MrUniformKeyMap.View uniforms, MrObjectData object) {
                MrModelData model = (MrModelData) object;
                MrMaterial[] materials = model.getMaterials();
                MrLinearAlgebraObjectContainer container = new MrLinearAlgebraObjectContainer(MrDataType.FLOAT, materials.length);
                int  i = 0;
                for (MrMaterial material : materials) {
                    container.setValue(i, material.getDiffuse().getIntensity());
                    i++;
                }
                return container;
            }
        };
    }



    //TODO: La primera pasada a es null, después ya no, en teoría el nivel de prioridad impide eso no?
    private static MrUniformGenerator generateModelMatrix() {
        return new MrUniformGenerator(MrUniform.MODEL_MATRIX) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneTreeData.View tree, MrUniformKeyMap.View uniforms, MrObjectData object) {
                MrMatrix4f m = new MrMatrix4f();
                MrMatrix4f.Operator op = MrMatrix4f.getOperator();
                Iterator<MrObjectData> it = tree.parentTraversalByKey(object.getName());

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

    private static MrUniformGenerator generateBonesMatrices() {
        return new MrUniformGenerator(MrUniform.UNIFORM_BONE_MATRIX) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneTreeData.View tree, MrUniformKeyMap.View uniforms, MrObjectData object) {
                MrModelData model = (MrModelData) object;
                MrSkeleton skeleton = model.getSkeleton();
                MrBone[] bones = skeleton.getPose();
                MrLinearAlgebraObjectContainer container = new MrLinearAlgebraObjectContainer(MrDataType.MAT4, bones.length);
                int i = 0;
                for (MrBone bone : bones) {
                    container.setAlgebraObject(i, bone.getBoneMatrix());
                    i++;
                }
                return container;
            }
        };
    }

    private static MrUniformGenerator generateTextureSamplerIndex() {
        return new MrUniformGenerator(MrUniform.UNIFORM_TEXTURE) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneTreeData.View tree, MrUniformKeyMap.View uniforms, MrObjectData object) {
                MrModelData model = (MrModelData) object;
                MrTexture[] textures = model.getTextures();
                MrSamplerIndices indices = new MrSamplerIndices(textures.length);
                int i = 0;
                for (MrTexture tex : textures) {
                    indices.addTextureIndex(i, tex.getIndex());
                    i++;
                }
                return indices;
            }
        };
    }

    //public MrMatrix4f getModelMatrix() {
    //    return getSceneTree().getTransform().getAsMatrix();
    //}

    @Override
    public void initializeUniforms(Map<String, MrUniformGenerator> uniformGenerators) {
        super.initializeUniforms(uniformGenerators);
        uniformGenerators.put(MrUniform.MODEL_MATRIX, generateModelMatrix());
        uniformGenerators.put(MrUniform.UNIFORM_BONE_MATRIX, generateBonesMatrices());
        uniformGenerators.put(MrUniform.UNIFORM_MATERIAL_DIFFUSE_COLOR, generateMaterialDiffuseColor());
        uniformGenerators.put(MrUniform.UNIFORM_MATERIAL_AMBIENT_COLOR, generateMaterialAmbientColor());
        uniformGenerators.put(MrUniform.UNIFORM_MATERIAL_SPECULAR_COLOR, generateMaterialSpecularColor());
        uniformGenerators.put(MrUniform.UNIFORM_MATERIAL_AMBIENT_INTENSITY, generateMaterialAmbientIntensity());
        uniformGenerators.put(MrUniform.UNIFORM_MATERIAL_DIFFUSE_INTENSITY, generateMaterialDiffuseIntensity());
        uniformGenerators.put(MrUniform.UNIFORM_MATERIAL_SPECULAR_INTENSITY, generateMaterialSpecularIntensity());
        uniformGenerators.put(MrUniform.UNIFORM_TEXTURE, generateTextureSamplerIndex());
    }

    public MrMesh getMesh() {
        return ((MrModelData) mData).getMesh();
    }

    public MrMaterial[] getMaterials() {
        return ((MrModelData) mData).getMaterials();
    }

    public MrSkeleton getSkeleton() {
        return ((MrModelData) mData).getSkeleton();
    }
}
