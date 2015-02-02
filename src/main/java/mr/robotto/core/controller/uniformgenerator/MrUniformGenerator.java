/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller.uniformgenerator;

import mr.robotto.context.MrSceneObjectsTree;
import mr.robotto.core.controller.MrObject;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;

/**
 * Created by Aarón on 05/01/2015.
 */
public abstract class MrUniformGenerator {

    public final static int OBJECT_LEVEL = 0;
    public final static int SCENE_LEVEL = 1;
    public final static int USER_LEVEL = 2;

    private String mUniformType;
    private int mPriority;
    private MrLinearAlgebraObject mUniformValue;

    public MrUniformGenerator(String uniformType, int priority) {
        mUniformType = uniformType;
        mPriority = priority;
    }

    public MrLinearAlgebraObject getUniformValue() {
        return mUniformValue;
    }

    public void setUniformValue(MrLinearAlgebraObject uniformValue) {
        mUniformValue = uniformValue;
    }

    public String getUniformType() {
        return mUniformType;
    }

    public int getPriority() {
        return mPriority;
    }

    public abstract MrLinearAlgebraObject generateUniform(MrSceneObjectsTree tree, MrObject object);
}
