/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.action;

import mr.robotto.collections.MrHashMap;
import mr.robotto.collections.core.MrMapFunction;

/**
 * Created by aaron on 27/04/2015.
 */
public class MrActionMap extends MrHashMap<String, MrSkeletalAction> {

    private static MrMapFunction<String, MrSkeletalAction> generateActionsMapFunction() {
        return new MrMapFunction<String, MrSkeletalAction>() {
            @Override
            public String getKeyOf(MrSkeletalAction mrAction) {
                return mrAction.getName();
            }
        };
    }

    public MrActionMap() {
        super(generateActionsMapFunction());
    }
}
