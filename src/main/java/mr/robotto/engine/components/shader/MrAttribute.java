/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.shader;

import mr.robotto.engine.commons.MrDataType;

public class MrAttribute {

    public static final int VERTICES = 0;
    public static final int NORMALS = 1;
    public static final int COLOR = 2;
    public static final int MATERIAL_INDEX = 3;
    public static final int TEXTURE = 4;
    public static final int WEIGHT = 5;
    public static final int BONE_INDICES = 6;

    private int mAttributeType;
    private String mName;
    private int mIndex;
    private MrDataType mDataType;

    public MrAttribute(int attributeType, String name, int index, MrDataType dataType) {
        this.mAttributeType = attributeType;
        this.mName = name;
        this.mIndex = index;
        this.mDataType = dataType;
    }

    public int getAttributeType() {
        return mAttributeType;
    }

    public String getName() {
        return mName;
    }

    public int getIndex() {
        return mIndex;
    }

    public MrDataType getDataType() {
        return mDataType;
    }
}
