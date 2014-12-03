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
import mr.robotto.core.data.resources.uniformkeys.MrUniformKeyContainer;

/**
 * Created by Aarón on 01/12/2014.
 */
public class MrCameraRender implements MrObjectRender<MrCameraData> {

    private MrCameraData mCameraData;

    private boolean mLinked;
    private boolean mInitialized;

    public MrCameraRender() {
        mLinked = false;
        mInitialized = false;
    }

    @Override
    public void linkWith(MrCameraData link) {
        mCameraData = link;
        mLinked = true;
    }

    @Override
    public boolean isLinked() {
        return mLinked;
    }

    @Override
    public void setUniforms(MrUniformKeyContainer uniformList) {

    }

    @Override
    public void render() {

    }

    public void initializeSizeDependant(int w, int h) {

    }

    @Override
    public void initialize() {
        mInitialized = true;
    }

    @Override
    public boolean isInitialized() {
        return mInitialized;
    }
}
