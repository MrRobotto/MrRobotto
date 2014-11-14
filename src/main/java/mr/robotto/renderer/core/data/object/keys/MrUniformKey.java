/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.object.keys;

import mr.robotto.renderer.shaders.MrUniformType;
import mr.robotto.renderer.linearalgebra.MrLinearAlgebraObject;

public class MrUniformKey {

    private MrUniformType uniformType;
    private MrLinearAlgebraObject value;
    private int id;

    public MrUniformKey(MrUniformType uniformType) {
        this.uniformType = uniformType;
        this.value = null;
    }

    public MrUniformType getUniformType() {
        return uniformType;
    }

    public MrLinearAlgebraObject getValue() {
        return value;
    }

    public void setValue(MrLinearAlgebraObject value) {
        this.value = value;
    }
}
