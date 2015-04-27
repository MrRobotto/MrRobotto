/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.bone;

import mr.robotto.collections.MrHashMap;
import mr.robotto.collections.core.MrMapFunction;

/**
 * Created by aaron on 27/04/2015.
 */
public class MrBoneMap extends MrHashMap<String, MrBone> {

    private static MrMapFunction<String, MrBone> generateBonesMapFunction() {
        return new MrMapFunction<String, MrBone>() {
            @Override
            public String getKeyOf(MrBone mrBone) {
                return mrBone.getName();
            }
        };
    }

    public MrBoneMap() {
        super(generateBonesMapFunction());
    }
}
