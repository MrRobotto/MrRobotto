/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.rendereable.core;

import mr.robotto.core.data.resources.uniformkeys.MrUniformKeyContainer;

public interface MrUniformReciever {
    public void setUniforms(MrUniformKeyContainer uniformList);
}
