/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.controller;

import mr.robotto.renderer.core.data.MrModelData;
import mr.robotto.renderer.core.rendereable.objectrenderers.MrModelRender;


public class MrModel extends MrObject<MrModelData, MrModelRender> {
    public MrModel(MrModelData data, MrModelRender render) {
        super(data, render);
    }
}
