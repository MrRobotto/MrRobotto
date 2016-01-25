/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.uniformkey;

/**
 * Created by aaron on 27/12/2015.
 */
public class MrUniformKeySchema {

    private String mUniform;
    private String mGeneratorName;
    private int mLevel;
    private int mCount;

    public MrUniformKeySchema(String uniform, int level, String generatorName, int count) {
        mGeneratorName = generatorName;
        mUniform = uniform;
        mLevel = level;
        mCount = count;
    }

    public String getUniform() {
        return mUniform;
    }

    public String getGeneratorName() {
        return mGeneratorName;
    }

    public int getLevel() {
        return mLevel;
    }

    public int getCount() {
        return mCount;
    }
}
