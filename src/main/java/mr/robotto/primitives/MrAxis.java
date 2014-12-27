/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.primitives;

import mr.robotto.commons.MrDataType;
import mr.robotto.core.data.containers.MrBufferKeyContainer;
import mr.robotto.core.data.model.mesh.MrBuffer;
import mr.robotto.core.data.model.mesh.MrBufferKey;
import mr.robotto.core.data.model.mesh.MrIndexBuffer;
import mr.robotto.core.data.model.mesh.MrMesh;
import mr.robotto.core.data.model.mesh.MrVertexBuffer;
import mr.robotto.core.data.types.MrAttributeType;
import mr.robotto.core.data.types.MrMeshDrawType;

public class MrAxis extends MrMesh {

    private final static String AXIS_NAME = "MrAxis";

    public MrAxis() {
        super(AXIS_NAME, 6, MrMeshDrawType.LINES, genKeyList(), genVertexBuffer(), genIndexBuffer());
    }

    private static MrBuffer genVertexBuffer() {
        float[] vertices = new float[]{
                -10, 0, 0, 1, 0, 0,
                10, 0, 0, 1, 0, 0,
                0, -10, 0, 0, 1, 0,
                0, 10, 0, 0, 1, 0,
                0, 0, -10, 0, 0, 1,
                0, 0, 10, 0, 0, 1};
        MrBuffer buffer = new MrVertexBuffer(vertices.length);
        buffer.putFloats(vertices);
        return buffer;
    }

    private static MrBuffer genIndexBuffer() {
        short[] indices = new short[]{
                0, 1,
                2, 3,
                4, 5
        };
        MrBuffer buffer = new MrIndexBuffer(indices.length);
        buffer.putShorts(indices);
        return buffer;
    }

    //TODO: Too much hardocre for my eyes!
    private static MrBufferKeyContainer genKeyList() {
        MrBufferKeyContainer keyList = new MrBufferKeyContainer();
        MrBufferKey vertexKey = new MrBufferKey(MrAttributeType.VERTICES, MrDataType.FLOAT, 3, 6, 0);
        MrBufferKey colorKey = new MrBufferKey(MrAttributeType.COLOR, MrDataType.FLOAT, 3, 6, 3);
        keyList.add(vertexKey);
        keyList.add(colorKey);
        return keyList;
    }
}
