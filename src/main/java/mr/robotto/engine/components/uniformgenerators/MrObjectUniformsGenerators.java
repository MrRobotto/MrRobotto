/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.uniformgenerators;

import java.util.Map;

import mr.robotto.engine.core.data.MrObjectData;

/**
 * Created by aaron on 14/06/2015.
 */
public interface MrObjectUniformsGenerators {
    void initializeUniforms(MrObjectData object, Map<String, MrUniformGenerator> uniformGenerators);
}
