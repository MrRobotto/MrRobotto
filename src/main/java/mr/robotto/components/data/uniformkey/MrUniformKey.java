/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.uniformkey;

//TODO: Id for uniformkey and bufferkey in the same way!
public class MrUniformKey {

    private String mUniformType;

    public MrUniformKey(String uniformType) {
        mUniformType = uniformType;
    }

    public String getUniformType() {
        return mUniformType;
    }
}
