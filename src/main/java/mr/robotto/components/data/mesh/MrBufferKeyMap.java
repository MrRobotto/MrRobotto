/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.mesh;

import mr.robotto.collections.MrHashMap;
import mr.robotto.collections.core.MrMapFunction;

/**
 * Created by aaron on 01/03/2015.
 */
public class MrBufferKeyMap extends MrHashMap<Integer, MrBufferKey> {

    public MrBufferKeyMap() {
        super(genMapFunction());
    }

    private static MrMapFunction<Integer, MrBufferKey> genMapFunction() {
        return new MrMapFunction<Integer, MrBufferKey>() {
            @Override
            public Integer getKeyOf(MrBufferKey mrBufferKey) {
                return mrBufferKey.getAttributeType();
            }
        };
    }

    //TODO: Fill this
    //a method to bind shader program attributes to the values of this?
}
