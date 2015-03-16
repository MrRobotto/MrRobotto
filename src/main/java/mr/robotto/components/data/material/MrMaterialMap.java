/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.material;

import mr.robotto.collections.MrHashMap;
import mr.robotto.collections.core.MrMapFunction;

/**
 * Created by aaron on 16/03/2015.
 */
public class MrMaterialMap extends MrHashMap<String, MrMaterial> {

    private static MrMapFunction<String, MrMaterial> getMapFunction() {
        return new MrMapFunction<String, MrMaterial>() {
            @Override
            public String getKeyOf(MrMaterial mrMaterial) {
                return mrMaterial.getName();
            }
        };
    }

    public MrMaterialMap() {
        super(getMapFunction());
    }
}
