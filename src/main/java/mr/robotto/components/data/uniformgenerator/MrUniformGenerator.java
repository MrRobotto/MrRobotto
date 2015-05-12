/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.uniformgenerator;

import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.scenetree.MrSceneTree;
import mr.robotto.scenetree.MrSceneTreeData;

/**
 * Created by Aarón on 05/01/2015.
 */
public abstract class MrUniformGenerator {

    private String mUniformType;

    public MrUniformGenerator(String uniformType) {
        mUniformType = uniformType;
    }

    public String getUniformType() {
        return mUniformType;
    }

    public abstract MrLinearAlgebraObject generateUniform(final MrSceneTreeData.View tree, final MrUniformKeyMap.View uniforms, final MrObjectData object);
}
