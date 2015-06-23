/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.data.material;

import mr.robotto.engine.linearalgebra.MrVector4f;

/**
 * Created by aaron on 16/03/2015.
 */
public class MrMaterialLight {
    private MrVector4f mColor;
    private float mIntensity;

    public MrMaterialLight(float intensity, MrVector4f color) {
        mColor = color;
        mIntensity = intensity;
    }

    public MrVector4f getColor() {
        return mColor;
    }

    public float getIntensity() {
        return mIntensity;
    }
}
