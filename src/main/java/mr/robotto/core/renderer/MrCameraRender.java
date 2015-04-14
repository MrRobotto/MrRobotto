/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.renderer;

import mr.robotto.core.data.MrCameraData;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.renderer.MrRenderingContext;

/**
 * Created by Aarón on 01/12/2014.
 */
public class MrCameraRender implements MrObjectRender {

    private MrCameraData mCameraData;

    private boolean mInitialized;

    public MrCameraRender() {
        mInitialized = false;
    }

    @Override
    public boolean isInitialized() {
        return mInitialized;
    }

    @Override
    public void initializeRender(MrRenderingContext context, MrObjectData link) {
        mCameraData = (MrCameraData) link;

        mInitialized = true;
    }


    @Override
    public void initializeSizeDependant(int w, int h) {

    }

    @Override
    public void render() {

    }
}
