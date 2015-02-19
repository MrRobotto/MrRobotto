/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader.resources;

import mr.robotto.collections.MrHashMap;
import mr.robotto.collections.core.MrMapFunction;
import mr.robotto.core.data.MrObjectData;

/**
 * Created by Aarón on 23/11/2014.
 */
public class MrObjectDataMap extends MrHashMap<String, MrObjectData> {

    public MrObjectDataMap() {
        super(getMapFunction());
    }

    private static MrMapFunction<String, MrObjectData> getMapFunction() {
        return new MrMapFunction<String, MrObjectData>() {
            @Override
            public String getKeyOf(MrObjectData mrObjectData) {
                return mrObjectData.getName();
            }
        };
    }

}
