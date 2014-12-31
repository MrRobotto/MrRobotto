/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;

import mr.robotto.collections.MrMapTree;
import mr.robotto.collections.core.MrMapFunction;
import mr.robotto.core.data.commons.MrObjectData;

/**
 * Created by Aarón on 31/12/2014.
 */
public class MrSceneObjectTree extends MrMapTree<String, MrObjectData> {

    public MrSceneObjectTree(MrObjectData root) {
        super(root, createMapFunction());
    }

    public MrSceneObjectTree(String rootKey, MrObjectData root) {
        super(rootKey, root, createMapFunction());
    }

    private static MrMapFunction<String, MrObjectData> createMapFunction() {
        return new MrMapFunction<String, MrObjectData>() {
            @Override
            public String getIdOf(MrObjectData mrObjectData) {
                return mrObjectData.getName();
            }
        };
    }
}
