/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.resources.shaders.input;

import mr.robotto.renderer.collections.MrMapContainer;
import mr.robotto.renderer.collections.MrMapFunction;

/**
 * Created by Aarón on 23/11/2014.
 */
public class MrAttributeContainer extends MrMapContainer<MrAttributeType, MrAttribute> {

    private static MrMapFunction<MrAttributeType, MrAttribute> getMapFunction() {
        return new MrMapFunction<MrAttributeType, MrAttribute>() {
            @Override
            public MrAttributeType getIdOf(MrAttribute mrAttribute) {
                return mrAttribute.getAttributeType();
            }
        };
    }

    public MrAttributeContainer() {
        super(getMapFunction());
    }
}
