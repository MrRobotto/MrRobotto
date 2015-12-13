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

import mr.robotto.engine.components.uniformkey.MrUniformKey;
import mr.robotto.engine.core.data.MrObjectData;

/**
 * Enables an easy access to create uniform generators for the scene objects
 */
public interface MrUniformsGeneratorManager {

    /**
     * Given an object and its uniform generator map this method will fill the map with the necessary uniform generators
     *
     * @param object
     * @param uniformGenerators
     */
    void initializeUniforms(MrObjectData object, Map<String, MrUniformKey.Generator> uniformGenerators);
}
