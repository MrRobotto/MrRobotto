/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.data.camera;

import mr.robotto.renderer.linearalgebra.MrMatrix4f;

public class MrPerspectiveLens extends MrLens {
    private float fovy;
    private float aspectRation;
    private float clipStart;
    private float clipEnd;

    public MrPerspectiveLens(float fovy, float aspectRation, float clipStart, float clipEnd) {
        this.fovy = fovy;
        this.aspectRation = aspectRation;
        this.clipStart = clipStart;
        this.clipEnd = clipEnd;
    }

    //TODO: Do not recalc this everytime
    @Override
    public MrMatrix4f getProjectionMatrix() {
        MrMatrix4f.ops.perspective(matrix, fovy, aspectRation, clipStart, clipEnd);
        return matrix;
    }
}
