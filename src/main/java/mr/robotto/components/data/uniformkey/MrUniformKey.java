/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.uniformkey;

import mr.robotto.linearalgebra.MrLinearAlgebraObject;

//TODO: Id for uniformkey and bufferkey in the same way!
public class MrUniformKey {

    public final static int OBJECT_LEVEL = 0;
    public final static int SCENE_LEVEL = 1;
    public final static int TOP_SCENE_LEVEL = 2;
    public final static int USER_LEVEL = 3;

    private String mUniformType;
    private int mCount;
    private int mLevel;
    private MrLinearAlgebraObject mValue;

    public MrUniformKey(String uniformType, int level, int count) {
        mUniformType = uniformType;
        mCount = count;
        mLevel = level;
        mValue = null;
    }

    public MrLinearAlgebraObject getValue() {
        return mValue;
    }

    public void setValue(MrLinearAlgebraObject value) {
        mValue = value;
    }

    public String getUniformType() {
        return mUniformType;
    }

    public int getCount() {
        return mCount;
    }

    public int getLevel() {
        return mLevel;
    }
}
