/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.lens;

import mr.robotto.linearalgebra.MrMatrix4f;

public class MrPerspectiveLens extends MrLens {
    private boolean mChanged;
    private float mFovy;
    private float mAspectRatio;
    private float mClipStart;
    private float mClipEnd;

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
            op.perspective(mProjectionMatrix, mFovy, mAspectRatio, mClipStart, mClipEnd);
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

    public float getAspectRatio() {
        return mAspectRatio;
    }

    public void setAspectRatio(float aspectRatio) {
        mAspectRatio = aspectRatio;
        mChanged = true;
    }

    public float getClipStart() {
        return mClipStart;
    }

    public void setClipStart(float clipStart) {
        mClipStart = clipStart;
        mChanged = true;
    }

    public float getClipEnd() {
        return mClipEnd;
    }

    public void setClipEnd(float clipEnd) {
        mClipEnd = clipEnd;
        mChanged = true;
    }
}
