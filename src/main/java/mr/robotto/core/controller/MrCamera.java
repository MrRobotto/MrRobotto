/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller;

import mr.robotto.core.data.MrCameraData;
import mr.robotto.core.rendereable.MrCameraRender;

/**
 * Created by Aarón on 01/12/2014.
 */
public class MrCamera extends MrObject<MrCameraData, MrCameraRender> {
    protected MrCamera(MrCameraData data, MrCameraRender render) {
        super(data, render);
    }
}
