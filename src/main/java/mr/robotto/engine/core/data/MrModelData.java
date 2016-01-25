/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.data;

import java.util.ArrayList;
import java.util.Map;

import mr.robotto.engine.components.action.MrSkeletalAction;
import mr.robotto.engine.components.material.MrMaterial;
import mr.robotto.engine.components.material.MrTexture;
import mr.robotto.engine.components.mesh.MrMesh;
import mr.robotto.engine.components.skeleton.MrSkeleton;
import mr.robotto.engine.core.MrSceneObjectType;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrModelData extends MrObjectData {
    private MrMesh mMesh;
    private MrMaterial[] mMaterials;
    private MrTexture[] mTextures;
    private MrSkeleton mSkeleton;
    private boolean mIsVisible = true;

    public MrModelData(String name) {
        super(name, MrSceneObjectType.MODEL);
    }

    private void init() {
        getTexturesFromMaterials();
    }

    //TODO: Given a material extract the texture
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

    public void setMesh(MrMesh mesh) {
        mMesh = mesh;
    }

    public MrMaterial[] getMaterials() {
        return mMaterials;
    }

    public void setMaterials(MrMaterial[] materials) {
        mMaterials = materials;
        getTexturesFromMaterials();
    }

    public boolean hasSkeleton() {
        return mSkeleton != null;
    }

    public MrSkeleton getSkeleton() {
        return mSkeleton;
    }

    public void setSkeleton(MrSkeleton skeleton) {
        mSkeleton = skeleton;
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
