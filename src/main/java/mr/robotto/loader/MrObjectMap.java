/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader;

import mr.robotto.collections.MrHashMap;
import mr.robotto.collections.core.MrMapFunction;
import mr.robotto.core.controller.MrObject;
import mr.robotto.core.data.MrObjectData;

/**
 * Created by Aarón on 23/11/2014.
 */
public class MrObjectMap extends MrHashMap<String, MrObject> {

    public MrObjectMap() {
        super(getMapFunction());
    }

    private static MrMapFunction<String, MrObject> getMapFunction() {
        return new MrMapFunction<String, MrObject>() {
            @Override
            public String getKeyOf(MrObject data) {
                return data.getName();
            }
        };
    }

}
