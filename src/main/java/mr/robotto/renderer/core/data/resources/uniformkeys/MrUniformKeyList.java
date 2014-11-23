/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.resources.uniformkeys;

import mr.robotto.renderer.collections.MrMapContainer;
import mr.robotto.renderer.collections.MrMapFunction;
import mr.robotto.renderer.core.data.resources.shaders.input.MrUniformType;

/**
 * Created by Aarón on 23/11/2014.
 */
public class MrUniformKeyList extends MrMapContainer<MrUniformType, MrUniformKey> {

    private static MrMapFunction<MrUniformType, MrUniformKey> getMapFunction() {
        return new MrMapFunction<MrUniformType, MrUniformKey>() {
            @Override
            public MrUniformType getIdOf(MrUniformKey mrUniformKey) {
                return mrUniformKey.getUniformType();
            }
        };
    }

    public MrUniformKeyList() {
        super(getMapFunction());
    }

    /**
     * Puts all the elements of list in this
     * @param list
     */
    public void mergeWith(MrUniformKeyList list) {
        for (MrUniformKey uniformKey : list) {
            add(uniformKey);
        }
    }
}
