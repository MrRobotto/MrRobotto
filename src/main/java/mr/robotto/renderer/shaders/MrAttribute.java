/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.shaders;

import mr.robotto.renderer.commons.MrDataType;

public class MrAttribute {
    private MrAttributeType attributeType;
    private String name;
    private int index;
    private MrDataType dataType;

    public MrAttribute(MrAttributeType attributeType, String name, int index, MrDataType dataType) {
        this.attributeType = attributeType;
        this.name = name;
        this.index = index;
        this.dataType = dataType;
    }

    public MrAttributeType getAttributeType() {
        return attributeType;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public MrDataType getDataType() {
        return dataType;
    }
}
