/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data.model.mesh;

import mr.robotto.commons.MrDataType;
import mr.robotto.core.data.types.MrBufferTarget;
import mr.robotto.core.data.types.MrBufferUsage;

public class MrVertexBuffer extends MrBuffer {
    public MrVertexBuffer(int capacity) {
        super(capacity, MrDataType.FLOAT, MrBufferTarget.ARRAY_BUFFER, MrBufferUsage.STATIC_DRAW);
    }
}
