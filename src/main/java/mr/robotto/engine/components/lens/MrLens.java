/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.lens;

import mr.robotto.engine.linearalgebra.MrMatrix4f;

/**
 * Base class for camera lenses
 */
public abstract class MrLens {
    protected float mWidth = -1;
    protected float mHeight = -1;
    protected float mClipStart;
    protected float mClipEnd;
    protected MrMatrix4f mProjectionMatrix = new MrMatrix4f();

    /**
     * Sets the dimension of the window in pixel size
     *
     * @param width
     * @param height
     */
    public void setDimension(float width, float height) {
        mWidth = width;
        mHeight = height;
    }

    /**
     * Sets the start and end clipping planes
     * @param start
     * @param end
     */
    public void setClipPlanes(float start, float end) {
        mClipStart = start;
        mClipEnd = end;
    }

    /**
     * Gets the near clipping plane
     * @return
     */
    public float getClipStart() {
        return mClipStart;
    }

    /**
     * Gets the far clipping plane
     * @return
     */
    public float getClipEnd() {
        return mClipEnd;
    }

    /**
     * Gets the aspect ratio of this lens
     * @return
     */
    public float getAspectRatio() {
        return mWidth/mHeight;
    }

    /**
     * Gets the width of the window
     * @return
     */
    public float getWidth() {
        return mWidth;
    }

    /**
     * Gets the height of the window
     * @return
     */
    public float getHeight() {
        return mHeight;
    }

    /**
     * Returns projection matrix
     * @return
     */
    public abstract MrMatrix4f getProjectionMatrix();
}
