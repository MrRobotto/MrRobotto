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
import mr.robotto.core.data.commons.shader.MrUniform;

/**
 * Created by Aarón on 23/11/2014.
 */
public class MrUniformContainer extends MrMapContainer<String, MrUniform> {

    public MrUniformContainer() {
        super(getMapFunction());
    }

    private static MrMapFunction<String, MrUniform> getMapFunction() {
        return new MrMapFunction<String, MrUniform>() {
            @Override
            public String getKeyOf(MrUniform mrUniform) {
                return mrUniform.getName();
            }
        };
    }
}
