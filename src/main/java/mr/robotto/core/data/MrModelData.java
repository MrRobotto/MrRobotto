/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data;

import java.util.ArrayList;
import java.util.Map;

import mr.robotto.components.comp.MrMesh;
import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.comp.MrTexture;
import mr.robotto.components.data.action.MrSkeletalAction;
import mr.robotto.components.data.material.MrMaterial;
import mr.robotto.components.data.skeleton.MrSkeleton;
import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.core.MrSceneObjectType;
import mr.robotto.core.data.uniformgenerators.MrModelUniformGenerators;
import mr.robotto.linearalgebra.MrTransform;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrModelData extends MrObjectData {
    private MrMesh mMesh;
    private MrMaterial[] mMaterials;
    private MrTexture[] mTextures;
    private MrSkeleton mSkeleton;
    private boolean mIsVisible;

    public MrModelData(String name, MrTransform transform, Map<String, MrUniformKey> uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh, MrMaterial[] materials, MrSkeleton skeleton) {
        super(name, MrSceneObjectType.MODEL, transform, shaderProgram, uniformKeys);
        mMesh = mesh;
        mMaterials = materials;
        mSkeleton = skeleton;
        mIsVisible = true;
        init();
    }

    private void init() {
        getTexturesFromMaterials();
    }

    @Override
    public void initializeUniforms() {
        super.initializeUniforms();
        new MrModelUniformGenerators().initializeUniforms(this, mUniformGenerators);
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
}
