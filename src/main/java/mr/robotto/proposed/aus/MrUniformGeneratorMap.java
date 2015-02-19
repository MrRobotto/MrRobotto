/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed.aus;

import mr.robotto.collections.MrSortedMap;
import mr.robotto.collections.core.MrMapFunction;
import mr.robotto.collections.core.MrOrderingFunction;

/**
 * Created by aaron on 15/02/2015.
 */
public class MrUniformGeneratorMap extends MrSortedMap<String, MrUniformGenerator> {

    public MrUniformGeneratorMap() {
        super(generateMapFunction(), generateOrderingFunction());
    }

    private static MrMapFunction<String, MrUniformGenerator> generateMapFunction() {
        return new MrMapFunction<String, MrUniformGenerator>() {
            @Override
            public String getKeyOf(MrUniformGenerator mrUniformGenerator) {
                return mrUniformGenerator.getUniformType();
            }
        };
    }

    private static MrOrderingFunction<MrUniformGenerator> generateOrderingFunction() {
        return new MrOrderingFunction<MrUniformGenerator>() {
            @Override
            public Integer getGroupOf(MrUniformGenerator v) {
                return v.getPriority();
            }
        };
    }
}
