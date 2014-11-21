/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.resources.shaders;

public enum MrAttributeType {
    VERTICES(0),
    NORMALS(1),
    COLOR(2),
    MATERIALINDEX(3),
    TEXTURE(4),
    WEIGHT(5),
    BONEINDICES(6);

    private int mValue;

    MrAttributeType(int value) {
        this.mValue = value;
    }

    public int getValue() {
        return mValue;
    }
}
