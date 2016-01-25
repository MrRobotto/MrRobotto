/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.uniformkey;

import java.util.Map;

import mr.robotto.engine.core.data.MrObjectData;
import mr.robotto.engine.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.engine.scenetree.MrObjectsDataTree;

/**
 * Generator for uniform keys
 */
public interface MrUniformGenerator {

    /**
     * Returns the value generated for this uniform key
     *
     * @param tree
     * @param uniforms
     * @param object
     * @return
     */
    MrLinearAlgebraObject generateUniform(final MrObjectsDataTree tree, final Map<String, MrUniformKey> uniforms, final MrObjectData object);
}
