/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data.resources.mesh;

import mr.robotto.commons.MrDataType;

public class MrVertexBuffer extends MrBuffer {
    public MrVertexBuffer(int capacity) {
        super(capacity, MrDataType.FLOAT, TARGET_ARRAY_BUFFER, USAGE_STATIC_DRAW);
    }
}
