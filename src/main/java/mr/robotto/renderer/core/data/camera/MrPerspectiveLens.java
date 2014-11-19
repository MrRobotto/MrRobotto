/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.camera;

import mr.robotto.renderer.linearalgebra.MrMatrix4f;

public class MrPerspectiveLens extends MrLens {
    private float mFovy;
    private float mAspectRatio;
    private float mClipStart;
    private float mClipEnd;

    public MrPerspectiveLens(float fovy, float aspectRatio, float clipStart, float clipEnd) {
        mFovy = fovy;
        mAspectRatio = aspectRatio;
        mClipStart = clipStart;
        mClipEnd = clipEnd;
    }

    //TODO: Do not recalc this everytime
    @Override
    public MrMatrix4f getProjectionMatrix() {
        MrMatrix4f.ops.perspective(matrix, mFovy, mAspectRatio, mClipStart, mClipEnd);
        return matrix;
    }
}
