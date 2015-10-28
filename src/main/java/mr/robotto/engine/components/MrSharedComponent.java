/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components;

import mr.robotto.engine.renderer.MrRenderingContext;

/**
 * Base class for an element with a Model-Renderer-Controller architecture.
 * The model class allows access to data layer
 * The renderer class allows access to rendering function
 * And the controller manages access to model and renderer layers from outside
 */
public abstract class MrSharedComponent {

    public static final String TYPE_MESH = "Mesh";
    public static final String TYPE_SHADERPROGRAM = "ShaderProgram";
    public static final String TYPE_TEXTURE = "Texture";

    protected boolean mInitialized = false;
    protected boolean mBound = false;
    protected MrRenderingContext mRenderingContext;

    /**
     * Gets the type of this component
     *
     * @return type
     */
    public abstract String getType();

    /**
     * Gets the name of this component
     *
     * @return the name
     */
    public abstract String getName();

    /**
     * Gets the data layer linked to this object
     * @return the data layer
     */
    public abstract Data getData();

    /**
     * Gets the rendering layer linked to this object
     * @return the rendering layer
     */
    public abstract View getView();

    /**
     * Checks if {@link MrSharedComponent#initialize(MrRenderingContext)} has been already called
     * @return true if it is initialized, false otherwise
     */
    public boolean isInitialized() {
        return mInitialized;
    }

    /**
     * Checks if {@link MrSharedComponent#bind()} has been called
     * @return true if the component is bound, false otherwise
     */
    public boolean isBound() {
        return mBound;
    }

    /**
     * Initializes the current component
     * @param context Rendering context
     */
    public void initialize(MrRenderingContext context) {
        if (!isInitialized()) {
            mRenderingContext = context;
            getView().initialize();
            mInitialized = true;
        }
    }

    /**
     * Binds the component
     */
    public void bind() {
        //if (!isInitialized()) {
        //    initialize();
        //}
        getView().bind();
        mBound = true;
    }

    /**
     * Unbinds the component
     */
    public void unbind() {
        if (isBound()) {
            //getView().unbind();
            mBound = false;
        }
    }

    /**
     * Base class for data layer
     */
    protected static abstract class Data {
        /**
         * Gets the name, it will be used in {@link MrSharedComponent#getName()}
         * @return
         */
        public abstract String getName();
    }

    /**
     * Base class for rendering layer
     */
    protected static abstract class View {
        /**
         * Initializes the rendering layer.
         * This method will be used in {@link MrSharedComponent#initialize(MrRenderingContext)}
         */
        public abstract void initialize();

        /**
         * Binds the rendering layer.
         * This method will be used in {@link MrSharedComponent#bind()}
         */
        public abstract void bind();

        /**
         * Unbinds the rendering layer.
         * This method will be used in {@link MrSharedComponent#unbind()}
         */
        public abstract void unbind();
    }
}
