/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.uniformkey;

import mr.robotto.collections.MrHashMap;
import mr.robotto.collections.core.MrMapFunction;

/**
 * Created by Aarón on 23/11/2014.
 */
public class MrUniformKeyMap extends MrHashMap<String, MrUniformKey> {

    public MrUniformKeyMap() {
        super(getMapFunction());
    }

    private static MrMapFunction<String, MrUniformKey> getMapFunction() {
        return new MrMapFunction<String, MrUniformKey>() {
            @Override
            public String getKeyOf(MrUniformKey mrUniformKey) {
                return mrUniformKey.getUniformType();
            }
        };
    }

    /**
     * Puts all the elements of list in this
     *
     * @param list
     */
    public void mergeWith(MrUniformKeyMap list) {
        for (MrUniformKey uniformKey : list) {
            add(uniformKey);
        }
    }
}
