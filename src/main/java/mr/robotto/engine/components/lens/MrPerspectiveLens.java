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

public class MrPerspectiveLens extends MrLens {
    private boolean mChanged;
    private float mFovy;
    private float mAspectRatio;

    public MrPerspectiveLens(float fovy, float aspectRatio, float clipStart, float clipEnd) {
        mFovy = fovy;
        mAspectRatio = aspectRatio;
        mClipStart = clipStart;
        mClipEnd = clipEnd;
        mChanged = true;
    }

    @Override
    public MrMatrix4f getProjectionMatrix() {
        //MrMatrix4f.ops.perspective(mProjectionMatrix, mFovy, mAspectRatio, mClipStart, mClipEnd);
        if (!mChanged) {
            return mProjectionMatrix;
        } else {
            MrMatrix4f.Operator op = MrMatrix4f.getOperator();
            op.perspective(mProjectionMatrix, mFovy, mWidth/mHeight, mClipStart, mClipEnd);
            mChanged = false;
            return mProjectionMatrix;
        }
    }

    public float getFovy() {
        return mFovy;
    }

    public void setFovy(float fovy) {
        mFovy = fovy;
        mChanged = true;
    }

    public void setAspectRatio(float aspectRatio) {
        mAspectRatio = aspectRatio;
        mChanged = true;
    }

    @Override
    public void setClipPlanes(float start, float end) {
        super.setClipPlanes(start, end);
        mChanged = true;
    }

    public void setClipStart(float clipStart) {
        mClipStart = clipStart;
        mChanged = true;
    }

    public void setClipEnd(float clipEnd) {
        mClipEnd = clipEnd;
        mChanged = true;
    }
}
