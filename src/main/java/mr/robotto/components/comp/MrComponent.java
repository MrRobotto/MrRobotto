/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.comp;

/**
 * Base class for an element with a Model-Renderer-Controller architecture.
 * The model class allows access to data layer
 * The renderer class allows access to rendering function
 * And the controller manages access to model and renderer layers from outside
 */
public abstract class MrComponent {
    protected boolean mInitialized = false;
    protected boolean mBound = false;

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
     * Checks if {@link MrComponent#initialize()} has been already called
     * @return true if it is initialized, false otherwise
     */
    public boolean isInitialized() {
        return mInitialized;
    }

    /**
     * Checks if {@link MrComponent#bind()} has been called
     * @return true if the component is bound, false otherwise
     */
    public boolean isBound() {
        return mBound;
    }

    /**
     * Initializes the component
     */
    public void initialize() {
        if (!isInitialized()) {
            getView().initialize();
            mInitialized = true;
        }
    }

    /**
     * Binds the component
     */
    public void bind() {
        if (!isInitialized()) {
            initialize();
        }
        if (!isBound()) {
            getView().bind();
            mBound = true;
        }
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
         * Gets the name, it will be used in {@link MrComponent#getName()}
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
         * This method will be used in {@link MrComponent#initialize()}
         */
        public abstract void initialize();

        /**
         * Binds the rendering layer.
         * This method will be used in {@link MrComponent#bind()}
         */
        public abstract void bind();

        /**
         * Unbinds the rendering layer.
         * This method will be used in {@link MrComponent#unbind()}
         */
        public abstract void unbind();
    }
}
