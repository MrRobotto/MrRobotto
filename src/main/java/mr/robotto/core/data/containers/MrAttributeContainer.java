/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data.containers;

import mr.robotto.collections.MrMapContainer;
import mr.robotto.collections.core.MrMapFunction;
import mr.robotto.core.data.commons.shader.MrAttribute;
import mr.robotto.core.data.types.MrAttributeType;

/**
 * Created by Aarón on 23/11/2014.
 */
public class MrAttributeContainer extends MrMapContainer<MrAttributeType, MrAttribute> {

    public MrAttributeContainer() {
        super(getMapFunction());
    }

    private static MrMapFunction<MrAttributeType, MrAttribute> getMapFunction() {
        return new MrMapFunction<MrAttributeType, MrAttribute>() {
            @Override
            public MrAttributeType getKeyOf(MrAttribute mrAttribute) {
                return mrAttribute.getAttributeType();
            }
        };
    }
}
