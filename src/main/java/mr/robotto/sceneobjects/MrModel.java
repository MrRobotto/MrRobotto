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

import mr.robotto.engine.components.MrMesh;
import mr.robotto.engine.components.MrShaderProgram;
import mr.robotto.engine.components.MrTexture;
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

        /**
         * Sets the shader program of this model. Required
         * @param shaderProgram
         * @return
         */
        @Override
        public Builder setShaderProgram(MrShaderProgram shaderProgram) {
            mShaderProgram = shaderProgram;
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

        /**
         * Creates a new model instance from this builder
         * @return
         */
        public MrModel createModel() {
            return new MrModel(mName, mTransform, mUniformKeys, mShaderProgram, mMesh, mMaterials, mSkeleton);
        }
    }
}
