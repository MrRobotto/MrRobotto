/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.sceneobjects;

import java.util.Map;

import mr.robotto.engine.components.action.MrSkeletalAction;
import mr.robotto.engine.components.material.MrMaterial;
import mr.robotto.engine.components.material.MrTexture;
import mr.robotto.engine.components.mesh.MrMesh;
import mr.robotto.engine.components.skeleton.MrSkeleton;
import mr.robotto.engine.core.controller.MrModelController;
import mr.robotto.engine.core.data.MrModelData;
import mr.robotto.engine.core.renderer.MrModelRender;

public class MrModel extends MrObject {

    public MrModel(MrModelController controller) {
        super(controller);
    }

    @Override
    public MrModelController getController() {
        return (MrModelController) super.getController();
    }

    /**
     * Gets the materials used by this model
     *
     * @return
     */
    public MrMaterial[] getMaterials() {
        return getController().getMaterials();
    }

    /**
     * Gets the mesh attached to this model
     * @return
     */
    public MrMesh getMesh() {
        return getController().getMesh();
    }

    /**
     * Checks if this model has a skeleton
     * @return
     */
    public boolean hasSkeleton() {
        return getController().hasSkeleton();
    }

    /**
     * Gets the skeleton of this model
     * @return the skeleton, null if it does not have any
     */
    public MrSkeleton getSkeleton() {
        return getController().getSkeleton();
    }

    /**
     * Gets the textures used by this model
     * @return
     */
    public MrTexture[] getTextures() {
        return getController().getTextures();
    }

    /**
     * Gets the skeletal actions of this model
     * @return
     */
    public Map<String, MrSkeletalAction> getSkeletalActions() {
        return getController().getSkeletalActions();
    }

    /**
     * Plays an action if it exists
     * @param actionName
     */
    public void playAction(final String actionName) {
        getController().playAction(actionName);
    }

    /**
     * Plays an action infinitely
     * @param actionName
     */
    public void playActionContinuosly(final String actionName) {
        getController().playActionContinuosly(actionName);
    }

    /**
     * Checks if this model is visible
     * @return
     */
    public boolean isVisible() {
        return getController().isVisible();
    }

    /**
     * Sets the visibility of this model
     * @param isVisible
     */
    public void setVisibility(boolean isVisible) {
        getController().setVisibility(isVisible);
    }

    /**
     * Checks if this model has textures
     * @return
     */
    public boolean hasTextures() {
        return getController().hasTextures();
    }

    /**
     * Builder used to construct a {@link MrModel} instance
     */
    public static class Builder extends MrObject.Builder<Builder> {
        private MrMesh mMesh;
        private MrMaterial[] mMaterials = null;
        private MrSkeleton mSkeleton = null;
        private MrModelRender mRender = new MrModelRender();

        @Override
        protected Builder getThis() {
            return this;
        }

        /**
         * Sets the mesh of this model. Required
         * @param mesh
         * @return
         */
        public Builder setMesh(MrMesh mesh) {
            mMesh = mesh;
            return this;
        }

        /**
         * Sets the materials of this model. Optional
         * @param materials
         * @return
         */
        public Builder setMaterials(MrMaterial[] materials) {
            mMaterials = materials;
            return this;
        }

        /**
         * Sets the skeleton of this model. Optional
         * @param skeleton
         * @return
         */
        public Builder setSkeleton(MrSkeleton skeleton) {
            mSkeleton = skeleton;
            return this;
        }

        public Builder setRender(MrModelRender render) {
            mRender = render;
            return this;
        }

        /**
         * Creates a new {@link MrModel} instance from this builder
         * @return
         */
        public MrModel build() {
            MrModelData data = new MrModelData(mName);
            setObjectAttributes(data);
            data.setMesh(mMesh);
            data.setMaterials(mMaterials);
            data.setSkeleton(mSkeleton);
            MrModelController controller = new MrModelController(data, mRender);
            return new MrModel(controller);
        }
    }
}
