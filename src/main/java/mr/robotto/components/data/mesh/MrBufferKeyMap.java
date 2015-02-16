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
 * Created by Aarón on 22/11/2014.
 */
public class MrBufferKeyMap extends MrHashMap<Integer, MrBufferKey> {

    public MrBufferKeyMap() {
        super(getMapFunction());
    }

    private static MrMapFunction<Integer, MrBufferKey> getMapFunction() {
        return new MrMapFunction<Integer, MrBufferKey>() {
            @Override
            public Integer getKeyOf(MrBufferKey mrBufferKey) {
                return mrBufferKey.getAttributeType();
            }
        };
    }
}
