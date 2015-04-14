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
 * Created by aaron on 07/04/2015.
 */
public abstract class MrComponent {
    protected boolean mInitialized = false;
    protected boolean mBound = false;

    public abstract String getName();

    public abstract Data getData();

    public abstract View getView();

    public boolean isInitialized() {
        return mInitialized;
    }

    public boolean isBound() {
        return mBound;
    }

    public void initialize() {
        if (!isInitialized()) {
            getView().initialize();
            mInitialized = true;
        }
    }

    public void bind() {
        if (!isInitialized()) {
            initialize();
        }
        if (!isBound()) {
            getView().bind();
            mBound = true;
        }
    }

    public void unbind() {
        if (isBound()) {
            getView().unbind();
            mBound = false;
        }
    }

    protected static abstract class Data {
        public abstract String getName();
    }

    protected static abstract class View {
        public abstract void initialize();

        public abstract void bind();

        public abstract void unbind();
    }
}
