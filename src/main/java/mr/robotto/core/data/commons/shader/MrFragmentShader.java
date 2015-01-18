/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data.commons.shader;


public class MrFragmentShader extends MrShader {

    public MrFragmentShader(String source) {
        super(MrShader.SHADERTYPE_FRAGMENT_SHADER, source);
    }
}
