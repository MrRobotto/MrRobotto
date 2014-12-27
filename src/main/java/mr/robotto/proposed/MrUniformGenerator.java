/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;

import mr.robotto.core.data.camera.MrCameraData;
import mr.robotto.core.data.camera.lens.MrLens;
import mr.robotto.core.data.model.MrModelData;
import mr.robotto.linearalgebra.MrMatrix4f;

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
