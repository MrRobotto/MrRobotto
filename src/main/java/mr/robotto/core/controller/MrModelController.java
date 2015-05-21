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
import mr.robotto.components.data.action.MrSkeletalAction;
import mr.robotto.components.data.skeleton.MrBone;
import mr.robotto.components.data.skeleton.MrSkeleton;
import mr.robotto.components.data.material.MrMaterial;
import mr.robotto.components.data.uniformgenerator.MrUniformGenerator;
import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.data.MrModelData;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.core.renderer.MrModelRender;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.linearalgebra.MrLinearAlgebraObjectContainer;
import mr.robotto.linearalgebra.MrMatrix4f;
import mr.robotto.linearalgebra.MrSamplerIndices;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector4f;
import mr.robotto.scenetree.MrObjectsDataTree;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrModelController extends MrObjectController {


    public MrModelController(String name, MrTransform transform, Map<String, MrUniformKey> uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh, MrMaterial[] materials, MrSkeleton skeleton) {
        super(new MrModelData(name, transform, uniformKeys, shaderProgram, mesh, materials, skeleton), new MrModelRender());
    }

    private static MrUniformGenerator generateMaterialAmbientColor() {
        return new MrUniformGenerator(MrUniformGenerator.UNIFORM_MATERIAL_AMBIENT_COLOR) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
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

    private static class AmbientColorGenerator extends MrUniformGenerator {
        private final MrLinearAlgebraObjectContainer mAmbientColorUniform;

        public AmbientColorGenerator(MrModelController model) {
            super(MrUniformGenerator.UNIFORM_MATERIAL_AMBIENT_COLOR);
            mAmbientColorUniform = new MrLinearAlgebraObjectContainer(MrDataType.VEC4, model.getMaterials().length, MrVector4f.SIZE);
            int  i = 0;
            for (MrMaterial material : model.getMaterials()) {
                mAmbientColorUniform.setAlgebraObject(i, material.getAmbient().getColor());
                i++;
            }
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            return mAmbientColorUniform;
        }
    }

    private static MrUniformGenerator generateMaterialDiffuseColor() {
        return new MrUniformGenerator(MrUniformGenerator.UNIFORM_MATERIAL_DIFFUSE_COLOR) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
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

    private static class DiffuseColorGenerator extends MrUniformGenerator {
        private final MrLinearAlgebraObjectContainer mDiffuseColorUniform;

        public DiffuseColorGenerator(MrModelController model) {
            super(MrUniformGenerator.UNIFORM_MATERIAL_DIFFUSE_COLOR);
            mDiffuseColorUniform = new MrLinearAlgebraObjectContainer(MrDataType.VEC4, model.getMaterials().length, MrVector4f.SIZE);
            int  i = 0;
            for (MrMaterial material : model.getMaterials()) {
                mDiffuseColorUniform.setAlgebraObject(i, material.getDiffuse().getColor());
                i++;
            }
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            return mDiffuseColorUniform;
        }
    }

    private static MrUniformGenerator generateMaterialSpecularColor() {
        return new MrUniformGenerator(MrUniformGenerator.UNIFORM_MATERIAL_DIFFUSE_COLOR) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
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

    private static class SpecularColorGenerator extends MrUniformGenerator {
        private final MrLinearAlgebraObjectContainer mSpecularColorUniform;

        public SpecularColorGenerator(MrModelController model) {
            super(MrUniformGenerator.UNIFORM_MATERIAL_SPECULAR_COLOR);
            mSpecularColorUniform = new MrLinearAlgebraObjectContainer(MrDataType.VEC4, model.getMaterials().length, MrVector4f.SIZE);
            int  i = 0;
            for (MrMaterial material : model.getMaterials()) {
                mSpecularColorUniform.setAlgebraObject(i, material.getSpecular().getColor());
                i++;
            }
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            return mSpecularColorUniform;
        }
    }

    private static MrUniformGenerator generateMaterialAmbientIntensity() {
        return new MrUniformGenerator(MrUniformGenerator.UNIFORM_MATERIAL_AMBIENT_INTENSITY) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
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

    private static class AmbientIntensityGenerator extends MrUniformGenerator {
        private final MrLinearAlgebraObjectContainer mIntensity;

        public AmbientIntensityGenerator(MrModelController model) {
            super(MrUniformGenerator.UNIFORM_MATERIAL_AMBIENT_INTENSITY);
            mIntensity = new MrLinearAlgebraObjectContainer(MrDataType.FLOAT, model.getMaterials().length);
            int  i = 0;
            for (MrMaterial material : model.getMaterials()) {
                mIntensity.setValue(i, material.getAmbient().getIntensity());
                i++;
            }
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            return mIntensity;
        }
    }

    private static MrUniformGenerator generateMaterialSpecularIntensity() {
        return new MrUniformGenerator(MrUniformGenerator.UNIFORM_MATERIAL_SPECULAR_INTENSITY) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
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

    private static class SpecularIntensityGenerator extends MrUniformGenerator {
        private final MrLinearAlgebraObjectContainer mIntensity;

        public SpecularIntensityGenerator(MrModelController model) {
            super(MrUniformGenerator.UNIFORM_MATERIAL_SPECULAR_INTENSITY);
            mIntensity = new MrLinearAlgebraObjectContainer(MrDataType.FLOAT, model.getMaterials().length);
            int  i = 0;
            for (MrMaterial material : model.getMaterials()) {
                mIntensity.setValue(i, material.getSpecular().getIntensity());
                i++;
            }
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            return mIntensity;
        }
    }

    private static MrUniformGenerator generateMaterialDiffuseIntensity() {
        return new MrUniformGenerator(MrUniformGenerator.UNIFORM_MATERIAL_DIFFUSE_INTENSITY) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
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

    private static class DiffuseIntensityGenerator extends MrUniformGenerator {
        private final MrLinearAlgebraObjectContainer mIntensity;

        public DiffuseIntensityGenerator(MrModelController model) {
            super(MrUniformGenerator.UNIFORM_MATERIAL_DIFFUSE_INTENSITY);
            mIntensity = new MrLinearAlgebraObjectContainer(MrDataType.FLOAT, model.getMaterials().length);
            int  i = 0;
            for (MrMaterial material : model.getMaterials()) {
                mIntensity.setValue(i, material.getDiffuse().getIntensity());
                i++;
            }
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            return mIntensity;
        }
    }


    //TODO: La primera pasada a es null, después ya no, en teoría el nivel de prioridad impide eso no?
    private static MrUniformGenerator generateModelMatrix() {
        return new MrUniformGenerator(MrUniformGenerator.MODEL_MATRIX) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
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

    private static class ModelGenerator extends MrUniformGenerator {
        private final MrMatrix4f mModel;

        public ModelGenerator() {
            super(MrUniformGenerator.MODEL_MATRIX);
            mModel = new MrMatrix4f();
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            MrMatrix4f.Operator op = MrMatrix4f.getOperator();
            op.setIdentity(mModel);
            Iterator<MrObjectData> it = tree.parentTraversalByKey(object.getName());
            while (it.hasNext()) {
                op.mult(mModel, it.next().getTransform().getAsMatrix(), mModel);
            }
            return mModel;
        }
    }

    private static MrUniformGenerator generateBonesMatrices() {
        return new MrUniformGenerator(MrUniformGenerator.UNIFORM_BONE_MATRIX) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
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

    private static class BoneMatricesGenerator extends MrUniformGenerator {
        private final MrLinearAlgebraObjectContainer mBones;

        public BoneMatricesGenerator(MrModelController model) {
            super(MrUniformGenerator.UNIFORM_BONE_MATRIX);
            mBones = new MrLinearAlgebraObjectContainer(MrDataType.MAT4, model.getSkeleton().getNumBones());
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            MrModelData model = (MrModelData) object;
            MrSkeleton skeleton = model.getSkeleton();
            MrBone[] bones = skeleton.getPose();
            int i = 0;
            for (MrBone bone : bones) {
                mBones.setAlgebraObject(i, bone.getBoneMatrix());
                i++;
            }
            return mBones;
        }
    }

    private static MrUniformGenerator generateTextureSamplerIndex() {
        return new MrUniformGenerator(MrUniformGenerator.UNIFORM_TEXTURE) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
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

    private static class TextureSamplerIndexGenerator extends MrUniformGenerator {
        private final MrSamplerIndices mIndices;

        public TextureSamplerIndexGenerator(MrModelController model) {
            super(MrUniformGenerator.UNIFORM_TEXTURE);
            MrTexture[] textures = model.getTextures();
            mIndices = new MrSamplerIndices(textures.length);
            int i = 0;
            for (MrTexture tex : textures) {
                mIndices.addTextureIndex(i, tex.getIndex());
                i++;
            }
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            return mIndices;
        }
    }

    //public MrMatrix4f getModelMatrix() {
    //    return getSceneTree().getTransform().getAsMatrix();
    //}

    @Override
    public void initializeUniforms(Map<String, MrUniformGenerator> uniformGenerators) {
        super.initializeUniforms(uniformGenerators);
        uniformGenerators.put(MrUniformGenerator.MODEL_MATRIX, new ModelGenerator());
        uniformGenerators.put(MrUniformGenerator.UNIFORM_BONE_MATRIX, new BoneMatricesGenerator(this));
        uniformGenerators.put(MrUniformGenerator.UNIFORM_MATERIAL_DIFFUSE_COLOR, new DiffuseColorGenerator(this));
        uniformGenerators.put(MrUniformGenerator.UNIFORM_MATERIAL_AMBIENT_COLOR, new AmbientColorGenerator(this));
        uniformGenerators.put(MrUniformGenerator.UNIFORM_MATERIAL_SPECULAR_COLOR, new SpecularColorGenerator(this));
        uniformGenerators.put(MrUniformGenerator.UNIFORM_MATERIAL_AMBIENT_INTENSITY, new AmbientIntensityGenerator(this));
        uniformGenerators.put(MrUniformGenerator.UNIFORM_MATERIAL_DIFFUSE_INTENSITY, new DiffuseIntensityGenerator(this));
        uniformGenerators.put(MrUniformGenerator.UNIFORM_MATERIAL_SPECULAR_INTENSITY, new SpecularIntensityGenerator(this));
        uniformGenerators.put(MrUniformGenerator.UNIFORM_TEXTURE, new TextureSamplerIndexGenerator(this));
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

    public MrTexture[] getTextures() {
        return ((MrModelData) mData).getTextures();
    }

    public Map<String, MrSkeletalAction> getSkeletalActions() {
        return ((MrModelData)mData).getSkeletalActions();
    }
}
