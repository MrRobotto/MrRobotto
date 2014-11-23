/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.proposed;

import mr.robotto.renderer.collections.MrMapContainer;
import mr.robotto.renderer.collections.MrMapFunction;
import mr.robotto.renderer.core.data.MrObjectData;

/**
 * Created by Aarón on 23/11/2014.
 */
public class MrObjectDataContainer extends MrMapContainer<String, MrObjectData> {

    private static MrMapFunction<String, MrObjectData> getMapFunction() {
        return new MrMapFunction<String, MrObjectData>() {
            @Override
            public String getIdOf(MrObjectData mrObjectData) {
                return mrObjectData.getName();
            }
        };
    }

    public MrObjectDataContainer() {
        super(getMapFunction());
    }

}
