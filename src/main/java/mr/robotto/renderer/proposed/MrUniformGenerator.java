/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.proposed;

import mr.robotto.renderer.core.data.MrCameraData;
import mr.robotto.renderer.core.data.resources.lens.MrLens;
import mr.robotto.renderer.core.data.MrModelData;
import mr.robotto.renderer.linearalgebra.MrMatrix4f;

public class MrUniformGenerator {

    public static MrMatrix4f getModelViewProjectionMatrix(MrCameraData camera, MrModelData model) {
        return null;
    }

    public static MrMatrix4f getModelViewMatrix(MrCameraData camera) {
        return null;
    }

    public static MrMatrix4f getModelMatrix(MrModelData model) {
        return null;
    }

    public static MrMatrix4f getViewMatrix(MrCameraData camera) {
        return null;
    }

    public static MrMatrix4f getProjectionMatrix(MrLens lens) {
        return null;
    }

    public static MrMatrix4f getViewProjectionMatrix(MrCameraData camera) {
        return null;
    }

    public static MrMatrix4f getTransposedModelViewMatrix(MrCameraData camera, MrModelData model) {
        return null;
    }

    public static MrMatrix4f getInvertedTransposedModelViewMatrix(MrCameraData camera, MrModelData model) {
        return null;
    }

    /*public MrMatrix4f getTextureTransformationMatrix() {

    }*/
}
