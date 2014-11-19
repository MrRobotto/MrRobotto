/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.object.keys;

import mr.robotto.renderer.core.data.model.shaders.MrUniformType;
import mr.robotto.renderer.linearalgebra.MrLinearAlgebraObject;

public class MrUniformKey {

    private MrUniformType mUniformType;
    private MrLinearAlgebraObject mValue;
    private int id;

    public MrUniformKey(MrUniformType uniformType) {
        mUniformType = uniformType;
        mValue = null;
    }

    public MrUniformType getUniformType() {
        return mUniformType;
    }

    public MrLinearAlgebraObject getValue() {
        return mValue;
    }

    public void setValue(MrLinearAlgebraObject value) {
        this.mValue = value;
    }
}
