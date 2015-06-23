/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.sceneobjects;

import java.util.Map;

import mr.robotto.engine.components.comp.MrMesh;
import mr.robotto.engine.components.comp.MrShaderProgram;
import mr.robotto.engine.components.comp.MrTexture;
import mr.robotto.engine.components.data.action.MrSkeletalAction;
import mr.robotto.engine.components.data.material.MrMaterial;
import mr.robotto.engine.components.data.skeleton.MrSkeleton;
import mr.robotto.engine.components.data.uniformkey.MrUniformKey;
import mr.robotto.engine.core.controller.MrModelController;
import mr.robotto.engine.linearalgebra.MrTransform;

public class MrModel extends MrObject {

    public MrModel(String name, MrTransform transform, Map<String, MrUniformKey> uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh, MrMaterial[] materials, MrSkeleton skeleton) {
        super(new MrModelController(name, transform, uniformKeys, shaderProgram, mesh, materials, skeleton));
    }


    @Override
    public MrModelController getController() {
        return (MrModelController) super.getController();
    }

    public MrMaterial[] getMaterials() {
        return getController().getMaterials();
    }

    public MrMesh getMesh() {
        return getController().getMesh();
    }

    public boolean hasSkeleton() {
        return getController().hasSkeleton();
    }

    public MrSkeleton getSkeleton() {
        return getController().getSkeleton();
    }

    public MrTexture[] getTextures() {
        return getController().getTextures();
    }

    public Map<String, MrSkeletalAction> getSkeletalActions() {
        return getController().getSkeletalActions();
    }

    public void playAction(final String actionName) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                getSkeleton().playAction(actionName);
            }
        });
    }

    public void playActionContinuosly(final String actionName) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                getSkeleton().playActionContinuosly(actionName);
            }
        });
    }

    public Map<String, MrSkeletalAction> getActions() {
        return getSkeleton().getActions();
    }

    public boolean isVisible() {
        return getController().isVisible();
    }

    public void setVisibility(boolean isVisible) {
        getController().setVisibility(isVisible);
    }

    public boolean hasTextures() {
        return getController().hasTextures();
    }

    public static class Builder extends MrObjectBuilder {
        private MrShaderProgram mShaderProgram;
        private MrMesh mMesh;
        private MrMaterial[] mMaterials = new MrMaterial[0];
        private MrSkeleton mSkeleton = null;

        @Override
        public Builder setName(String name) {
            return (Builder) super.setName(name);
        }

        @Override
        public Builder setTransform(MrTransform transform) {
            return (Builder) super.setTransform(transform);
        }

        @Override
        public Builder setUniformKeys(Map<String, MrUniformKey> uniformKeys) {
            return (Builder) super.setUniformKeys(uniformKeys);
        }

        @Override
        public Builder setShaderProgram(MrShaderProgram shaderProgram) {
            mShaderProgram = shaderProgram;
            return this;
        }

        public Builder setMesh(MrMesh mesh) {
            mMesh = mesh;
            return this;
        }

        public Builder setMaterials(MrMaterial[] materials) {
            mMaterials = materials;
            return this;
        }

        public Builder setSkeleton(MrSkeleton skeleton) {
            mSkeleton = skeleton;
            return this;
        }

        public MrModel createModel() {
            return new MrModel(mName, mTransform, mUniformKeys, mShaderProgram, mMesh, mMaterials, mSkeleton);
        }
    }
}
