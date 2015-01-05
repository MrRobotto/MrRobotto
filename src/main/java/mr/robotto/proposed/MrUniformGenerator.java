/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;

import mr.robotto.core.controller.MrObject;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;

/**
 * Created by Aarón on 05/01/2015.
 */
public abstract class MrUniformGenerator {

    public final static int OBJECT_LEVEL = 0;
    public final static int SCENE_LEVEL = 1;
    public final static int USER_LEVEL = 2;

    private String mObjectName;
    private String mUniform;
    private int mPriority;

    public MrUniformGenerator(String objectName, String uniform, int priority) {
        mObjectName = objectName;
        mUniform = uniform;
        mPriority = priority;
    }

    public String getObjectName() {
        return mObjectName;
    }

    public String getUniform() {
        return mUniform;
    }

    public int getPriority() {
        return mPriority;
    }

    public abstract MrLinearAlgebraObject generateUniform(MrSceneObjectsTree tree, MrObject object);
}
