/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.uniformgenerators;

import java.util.Iterator;
import java.util.Map;

import mr.robotto.engine.commons.MrDataType;
import mr.robotto.engine.components.material.MrMaterial;
import mr.robotto.engine.components.material.MrTexture;
import mr.robotto.engine.components.shader.MrUniform;
import mr.robotto.engine.components.skeleton.MrBone;
import mr.robotto.engine.components.skeleton.MrSkeleton;
import mr.robotto.engine.components.uniformkey.MrUniformKey;
import mr.robotto.engine.core.data.MrModelData;
import mr.robotto.engine.core.data.MrObjectData;
import mr.robotto.engine.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.engine.linearalgebra.MrLinearAlgebraObjectList;
import mr.robotto.engine.linearalgebra.MrMatrix4f;
import mr.robotto.engine.linearalgebra.MrVector4f;
import mr.robotto.engine.scenetree.MrObjectsDataTree;

/**
 * Created by aaron on 14/06/2015.
 */
public class MrModelUniformsGeneratorManager implements MrUniformsGeneratorManager {

    @Override
    public void initializeUniforms(MrObjectData object, Map<String, MrUniformKey.Generator> uniformGenerators) {
        MrModelData model = (MrModelData) object;
        uniformGenerators.put(MrUniformKey.GENERATOR_MODEL_MATRIX, new ModelGenerator());
        uniformGenerators.put(MrUniformKey.GENERATOR_NORMAL_MATRIX, new NormalMatrixGenerator());
        uniformGenerators.put(MrUniformKey.GENERATOR_BONE_MATRIX, new BoneMatricesGenerator(model));
        uniformGenerators.put(MrUniformKey.GENERATOR_MATERIAL_DIFFUSE_COLOR, new DiffuseColorGenerator(model));
        uniformGenerators.put(MrUniformKey.GENERATOR_MATERIAL_AMBIENT_COLOR, new AmbientColorGenerator(model));
        uniformGenerators.put(MrUniformKey.GENERATOR_MATERIAL_SPECULAR_COLOR, new SpecularColorGenerator(model));
        uniformGenerators.put(MrUniformKey.GENERATOR_MATERIAL_AMBIENT_INTENSITY, new AmbientIntensityGenerator(model));
        uniformGenerators.put(MrUniformKey.GENERATOR_MATERIAL_DIFFUSE_INTENSITY, new DiffuseIntensityGenerator(model));
        uniformGenerators.put(MrUniformKey.GENERATOR_MATERIAL_SPECULAR_INTENSITY, new SpecularIntensityGenerator(model));
        uniformGenerators.put(MrUniformKey.GENERATOR_TEXTURE_SAMPLER, new TextureSamplerIndexGenerator(model));
    }

    private static class AmbientColorGenerator implements MrUniformKey.Generator {
        private final MrLinearAlgebraObjectList mAmbientColorUniform;

        public AmbientColorGenerator(MrModelData model) {
            mAmbientColorUniform = new MrLinearAlgebraObjectList(MrDataType.VEC4, model.getMaterials().length, MrVector4f.SIZE);
            int  i = 0;
            for (MrMaterial material : model.getMaterials()) {
                mAmbientColorUniform.insert(i, material.getAmbient().getColor());
                i++;
            }
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            return mAmbientColorUniform;
        }
    }

    private static class DiffuseColorGenerator implements MrUniformKey.Generator {
        private final MrLinearAlgebraObjectList mDiffuseColorUniform;

        public DiffuseColorGenerator(MrModelData model) {
            mDiffuseColorUniform = new MrLinearAlgebraObjectList(MrDataType.VEC4, model.getMaterials().length, MrVector4f.SIZE);
            int  i = 0;
            for (MrMaterial material : model.getMaterials()) {
                mDiffuseColorUniform.insert(i, material.getDiffuse().getColor());
                i++;
            }
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            return mDiffuseColorUniform;
        }
    }

    private static class SpecularColorGenerator implements MrUniformKey.Generator {
        private final MrLinearAlgebraObjectList mSpecularColorUniform;

        public SpecularColorGenerator(MrModelData model) {
            mSpecularColorUniform = new MrLinearAlgebraObjectList(MrDataType.VEC4, model.getMaterials().length, MrVector4f.SIZE);
            int  i = 0;
            for (MrMaterial material : model.getMaterials()) {
                mSpecularColorUniform.insert(i, material.getSpecular().getColor());
                i++;
            }
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            return mSpecularColorUniform;
        }
    }

    private static class AmbientIntensityGenerator implements MrUniformKey.Generator {
        private final MrLinearAlgebraObjectList mIntensity;

        public AmbientIntensityGenerator(MrModelData model) {
            mIntensity = new MrLinearAlgebraObjectList(MrDataType.FLOAT, model.getMaterials().length);
            int  i = 0;
            for (MrMaterial material : model.getMaterials()) {
                mIntensity.insert(i, material.getAmbient().getIntensity());
                i++;
            }
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            return mIntensity;
        }
    }

    private static class SpecularIntensityGenerator implements MrUniformKey.Generator {
        private final MrLinearAlgebraObjectList mIntensity;

        public SpecularIntensityGenerator(MrModelData model) {
            mIntensity = new MrLinearAlgebraObjectList(MrDataType.FLOAT, model.getMaterials().length);
            int  i = 0;
            for (MrMaterial material : model.getMaterials()) {
                mIntensity.insert(i, material.getSpecular().getIntensity());
                i++;
            }
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            return mIntensity;
        }
    }

    private static class DiffuseIntensityGenerator implements MrUniformKey.Generator {
        private final MrLinearAlgebraObjectList mIntensity;

        public DiffuseIntensityGenerator(MrModelData model) {
            mIntensity = new MrLinearAlgebraObjectList(MrDataType.FLOAT, model.getMaterials().length);
            int  i = 0;
            for (MrMaterial material : model.getMaterials()) {
                mIntensity.insert(i, material.getDiffuse().getIntensity());
                i++;
            }
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            return mIntensity;
        }
    }

    private static class ModelGenerator implements MrUniformKey.Generator {
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

    private static class NormalMatrixGenerator implements MrUniformKey.Generator {
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

    private static class BoneMatricesGenerator implements MrUniformKey.Generator {
        private final MrLinearAlgebraObjectList mBones;

        public BoneMatricesGenerator(MrModelData model) {
            if (model.getSkeleton() != null)
                mBones = new MrLinearAlgebraObjectList(MrDataType.MAT4, model.getSkeleton().getNumBones());
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
                mBones.insert(i, bone.getBoneMatrix());
                i++;
            }
            return mBones;
        }
    }

    private static class TextureSamplerIndexGenerator implements MrUniformKey.Generator {
        private final MrLinearAlgebraObjectList mIndices;

        public TextureSamplerIndexGenerator(MrModelData model) {
            MrTexture[] textures = model.getTextures();
            mIndices = new MrLinearAlgebraObjectList(MrDataType.SAMPLER2D, textures.length);
            int i = 0;
            for (MrTexture tex : textures) {
                mIndices.insert(i, tex.getIndex());
                i++;
            }
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            return mIndices;
        }
    }
}
