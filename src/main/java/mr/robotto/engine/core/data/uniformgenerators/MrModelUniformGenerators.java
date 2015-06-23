/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.data.uniformgenerators;

import java.util.Iterator;
import java.util.Map;

import mr.robotto.engine.commons.MrDataType;
import mr.robotto.engine.components.comp.MrTexture;
import mr.robotto.engine.components.data.material.MrMaterial;
import mr.robotto.engine.components.data.shader.MrUniform;
import mr.robotto.engine.components.data.skeleton.MrBone;
import mr.robotto.engine.components.data.skeleton.MrSkeleton;
import mr.robotto.engine.components.data.uniformgenerator.MrUniformGenerator;
import mr.robotto.engine.components.data.uniformkey.MrUniformKey;
import mr.robotto.engine.core.data.MrModelData;
import mr.robotto.engine.core.data.MrObjectData;
import mr.robotto.engine.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.engine.linearalgebra.MrLinearAlgebraObjectContainer;
import mr.robotto.engine.linearalgebra.MrMatrix4f;
import mr.robotto.engine.linearalgebra.MrSamplerIndices;
import mr.robotto.engine.linearalgebra.MrVector4f;
import mr.robotto.engine.scenetree.MrObjectsDataTree;

/**
 * Created by aaron on 14/06/2015.
 */
public class MrModelUniformGenerators implements MrObjectUniformsGenerators{

    @Override
    public void initializeUniforms(MrObjectData object, Map<String, MrUniformGenerator> uniformGenerators) {
        MrModelData model = (MrModelData) object;
        uniformGenerators.put(MrUniformGenerator.GENERATOR_MODEL_MATRIX, new ModelGenerator());
        uniformGenerators.put(MrUniformGenerator.GENERATOR_NORMAL_MATRIX, new NormalMatrixGenerator());
        uniformGenerators.put(MrUniformGenerator.GENERATOR_BONE_MATRIX, new BoneMatricesGenerator(model));
        uniformGenerators.put(MrUniformGenerator.GENERATOR_MATERIAL_DIFFUSE_COLOR, new DiffuseColorGenerator(model));
        uniformGenerators.put(MrUniformGenerator.GENERATOR_MATERIAL_AMBIENT_COLOR, new AmbientColorGenerator(model));
        uniformGenerators.put(MrUniformGenerator.GENERATOR_MATERIAL_SPECULAR_COLOR, new SpecularColorGenerator(model));
        uniformGenerators.put(MrUniformGenerator.GENERATOR_MATERIAL_AMBIENT_INTENSITY, new AmbientIntensityGenerator(model));
        uniformGenerators.put(MrUniformGenerator.GENERATOR_MATERIAL_DIFFUSE_INTENSITY, new DiffuseIntensityGenerator(model));
        uniformGenerators.put(MrUniformGenerator.GENERATOR_MATERIAL_SPECULAR_INTENSITY, new SpecularIntensityGenerator(model));
        uniformGenerators.put(MrUniformGenerator.GENERATOR_TEXTURE_SAMPLER, new TextureSamplerIndexGenerator(model));
    }

    private static class AmbientColorGenerator extends MrUniformGenerator {
        private final MrLinearAlgebraObjectContainer mAmbientColorUniform;

        public AmbientColorGenerator(MrModelData model) {
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

    private static class DiffuseColorGenerator extends MrUniformGenerator {
        private final MrLinearAlgebraObjectContainer mDiffuseColorUniform;

        public DiffuseColorGenerator(MrModelData model) {
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

    private static class SpecularColorGenerator extends MrUniformGenerator {
        private final MrLinearAlgebraObjectContainer mSpecularColorUniform;

        public SpecularColorGenerator(MrModelData model) {
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

    private static class AmbientIntensityGenerator extends MrUniformGenerator {
        private final MrLinearAlgebraObjectContainer mIntensity;

        public AmbientIntensityGenerator(MrModelData model) {
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

    private static class SpecularIntensityGenerator extends MrUniformGenerator {
        private final MrLinearAlgebraObjectContainer mIntensity;

        public SpecularIntensityGenerator(MrModelData model) {
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

    private static class DiffuseIntensityGenerator extends MrUniformGenerator {
        private final MrLinearAlgebraObjectContainer mIntensity;

        public DiffuseIntensityGenerator(MrModelData model) {
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

    private static class ModelGenerator extends MrUniformGenerator {
        private final MrMatrix4f mModel;

        public ModelGenerator() {
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

    private static class NormalMatrixGenerator extends MrUniformGenerator {
        private final MrMatrix4f mNormal = new MrMatrix4f();

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            MrMatrix4f.Operator op = MrMatrix4f.getOperator();
            MrMatrix4f model = (MrMatrix4f) uniforms.get(MrUniform.MODEL_MATRIX).getValue();
            op.invert(mNormal, model);
            op.transpose(mNormal, mNormal);
            return mNormal;
        }
    }

    private static class BoneMatricesGenerator extends MrUniformGenerator {
        private final MrLinearAlgebraObjectContainer mBones;

        public BoneMatricesGenerator(MrModelData model) {
            if (model.getSkeleton() != null)
                mBones = new MrLinearAlgebraObjectContainer(MrDataType.MAT4, model.getSkeleton().getNumBones());
            else
                mBones = null;
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

    private static class TextureSamplerIndexGenerator extends MrUniformGenerator {
        private final MrSamplerIndices mIndices;

        public TextureSamplerIndexGenerator(MrModelData model) {
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
}
