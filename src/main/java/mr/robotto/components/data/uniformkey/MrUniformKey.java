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
public class MrUniformKey implements Comparable<MrUniformKey> {

    public final static int OBJECT_LEVEL = 0;
    public final static int SCENE_LEVEL = 1;
    public final static int TOP_SCENE_LEVEL = 2;
    public final static int USER_LEVEL = 3;

    private String mGeneratorName;
    private String mUniformType;
    private int mLevel;
    private MrLinearAlgebraObject mValue;

    public MrUniformKey(String generatorName, String uniformType, int level) {
        mGeneratorName = generatorName;
        mUniformType = uniformType;
        mLevel = level;
        mValue = null;
    }

    public MrLinearAlgebraObject getValue() {
        return mValue;
    }

    public void setValue(MrLinearAlgebraObject value) {
        mValue = value;
    }

    public String getGeneratorName() {
        return mGeneratorName;
    }

    public String getUniformType() {
        return mUniformType;
    }

    public int getLevel() {
        return mLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MrUniformKey)) return false;

        MrUniformKey that = (MrUniformKey) o;

        if (mLevel != that.mLevel) return false;
        if (!mGeneratorName.equals(that.mGeneratorName)) return false;
        return mUniformType.equals(that.mUniformType);

    }

    @Override
    public int hashCode() {
        int result = mGeneratorName.hashCode();
        result = 31 * result + mUniformType.hashCode();
        result = 31 * result + mLevel;
        return result;
    }

    @Override
    public int compareTo(MrUniformKey another) {
        if (mLevel < another.mLevel) return -1;
        if (mLevel > another.mLevel) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "MrUniformKey{" +
                "mUniformType='" + mUniformType + '\'' +
                ", mGeneratorName='" + mGeneratorName + '\'' +
                '}';
    }
}
