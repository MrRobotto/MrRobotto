/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.data;

import java.util.ArrayList;
import java.util.Map;

import mr.robotto.engine.components.action.MrSkeletalAction;
import mr.robotto.engine.components.material.MrMaterial;
import mr.robotto.engine.components.material.MrTexture;
import mr.robotto.engine.components.mesh.MrMesh;
import mr.robotto.engine.components.shader.MrShaderProgram;
import mr.robotto.engine.components.skeleton.MrSkeleton;
import mr.robotto.engine.components.uniformgenerators.MrModelUniformGenerators;
import mr.robotto.engine.components.uniformkey.MrUniformKey;
import mr.robotto.engine.core.MrSceneObjectType;
import mr.robotto.engine.linearalgebra.MrTransform;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrModelData extends MrObjectData {
    private MrMesh mMesh;
    private MrMaterial[] mMaterials;
    private MrTexture[] mTextures;
    private MrSkeleton mSkeleton;
    private boolean mIsVisible;

    private MrModelData(String name, MrTransform transform, Map<String, MrUniformKey> uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh, MrMaterial[] materials, MrSkeleton skeleton) {
        super(name, MrSceneObjectType.MODEL, transform, shaderProgram, uniformKeys);
        mMesh = mesh;
        mMaterials = materials;
        mSkeleton = skeleton;
        mIsVisible = true;
        init();
    }

    private void init() {
        mObjectUniformsGenerators = new MrModelUniformGenerators();
        getTexturesFromMaterials();
    }

    private void getTexturesFromMaterials() {
        ArrayList<MrTexture> textures = new ArrayList<MrTexture>();
        for (MrMaterial m : mMaterials) {
            if (m.hasTexture()) {
                textures.add(m.getTexture());
            }
        }
        mTextures = new MrTexture[textures.size()];
        mTextures = textures.toArray(mTextures);
    }

    public MrMesh getMesh() {
        return mMesh;
    }

    public MrMaterial[] getMaterials() {
        return mMaterials;
    }

    public boolean hasSkeleton() {
        return mSkeleton != null;
    }

    public MrSkeleton getSkeleton() {
        return mSkeleton;
    }

    public boolean hasTextures() {
        return mTextures.length > 0;
    }

    public MrTexture[] getTextures() {
        return mTextures;
    }

    public Map<String, MrSkeletalAction> getSkeletalActions() {
        return mSkeleton.getActions();
    }

    public boolean isVisible() {
        return mIsVisible;
    }

    public void setVisibility(boolean isVisible) {
        mIsVisible = isVisible;
    }

    public static class Builder extends MrObjectData.BuilderBase {
        private MrMesh mMesh;
        private MrMaterial[] mMaterials = null;
        private MrTexture[] mTextures = null;
        private MrSkeleton mSkeleton = null;

        public Builder setMesh(MrMesh mesh) {
            mMesh = mesh;
            return this;
        }

        public Builder setMaterials(MrMaterial[] materials) {
            mMaterials = materials;
            return this;
        }

        public Builder setTextures(MrTexture[] textures) {
            mTextures = textures;
            return this;
        }

        public Builder setSkeleton(MrSkeleton skeleton) {
            mSkeleton = skeleton;
            return this;
        }

        public MrModelData build() {
            return new MrModelData(mName, mTransform, mUniformKeys, mProgram, mMesh, mMaterials, mSkeleton);
        }
    }
}
