/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data;

import mr.robotto.core.data.commons.MrSceneObjectType;
import mr.robotto.core.data.resources.lens.MrLens;
import mr.robotto.linearalgebra.MrMatrix4f;
import mr.robotto.linearalgebra.MrVector3f;

public class MrCameraData extends MrObjectData {

    private MrVector3f lookAt;
    private MrLens lens;
    private int width;
    private int height;

    public MrCameraData(String name, MrLens lens, MrVector3f lookAt) {
        super(name, MrSceneObjectType.CAMERA);
        this.lens = lens;
        this.lookAt = lookAt;
    }

    /*public MrCamera(String name, MrTransform transform, MrLens lens, MrVector3f lookAt) {

    }*/

    //TODO: Quizas esto debería ser otra clase, se encarga de coger los uniformKeyList y los transforma en matrices o así!
    public MrCameraData(String name, MrLens lens) {
        this(name, lens, new MrVector3f());
    }

    public MrMatrix4f getViewMatrix() {
        return getTransform().getAsMatrix();
    }

    public MrMatrix4f getViewProjectionMatrix() {
        MrMatrix4f vp = new MrMatrix4f();
        MrMatrix4f.ops.mult(vp, lens.getProjectionMatrix(), getViewMatrix());
        return vp;
    }

    public MrMatrix4f getProjectionMatrix() {
        return lens.getProjectionMatrix();
    }
}
