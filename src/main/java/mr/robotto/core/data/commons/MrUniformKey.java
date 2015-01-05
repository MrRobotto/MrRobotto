/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data.commons;

import mr.robotto.core.data.types.MrUniformType;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;

//TODO: Id for uniformkey and bufferkey in the same way!
public class MrUniformKey {

    private MrUniformType mUniformType;
    private MrLinearAlgebraObject mValue;
    private int id;

    public MrUniformKey(MrUniformType uniformType) {
        mUniformType = uniformType;
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
