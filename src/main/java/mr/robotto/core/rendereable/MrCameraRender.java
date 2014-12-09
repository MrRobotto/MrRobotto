/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.rendereable;

import mr.robotto.core.data.MrCameraData;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.proposed.MrRenderingContext;

/**
 * Created by Aarón on 01/12/2014.
 */
public class MrCameraRender implements MrObjectRender {

    private MrCameraData mCameraData;

    private boolean mLinked;
    private boolean mInitialized;

    public MrCameraRender() {
        mLinked = false;
        mInitialized = false;
    }

    @Override
    public boolean isLinked() {
        return mLinked;
    }


    @Override
    public boolean isInitialized() {
        return mInitialized;
    }


    @Override
    public void linkWith(MrObjectData link, MrRenderingContext context) {
        mCameraData = (MrCameraData) link;
        mLinked = true;
    }

    @Override
    public void initializeRender() {
        mInitialized = true;
    }


    @Override
    public void initializeSizeDependant(int w, int h) {

    }

    @Override
    public void render() {

    }
}
