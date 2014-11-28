/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data.resources.mesh.bufferkeys;

import mr.robotto.collections.MrMapContainer;
import mr.robotto.collections.core.MrMapFunction;
import mr.robotto.core.data.resources.shaders.input.MrAttributeType;

/**
 * Created by Aarón on 22/11/2014.
 */
public class MrBufferKeyContainer extends MrMapContainer<MrAttributeType, MrBufferKey> {

    private static MrMapFunction<MrAttributeType, MrBufferKey> getMapFunction() {
        return new MrMapFunction<MrAttributeType, MrBufferKey>() {
            @Override
            public MrAttributeType getIdOf(MrBufferKey mrBufferKey) {
                return mrBufferKey.getAttributeType();
            }
        };
    }

    public MrBufferKeyContainer() {
        super(getMapFunction());
    }
}
