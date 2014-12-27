/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller;

import mr.robotto.core.data.model.MrModelData;
import mr.robotto.core.renderer.MrModelRender;
import mr.robotto.linearalgebra.MrMatrix4f;


public class MrModel extends MrObject<MrModelData, MrModelRender> {
    public MrModel(MrModelData data, MrModelRender render) {
        super(data, render);
    }

    public MrMatrix4f getModelMatrix() {
        return getData().getTransform().getAsMatrix();
    }
}
