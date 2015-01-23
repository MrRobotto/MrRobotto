/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.primitives;

import mr.robotto.commons.MrDataType;
import mr.robotto.core.data.resources.mesh.MrBuffer;
import mr.robotto.core.data.resources.mesh.MrBufferKey;
import mr.robotto.core.data.resources.mesh.MrBufferKeyContainer;
import mr.robotto.core.data.resources.mesh.MrIndexBuffer;
import mr.robotto.core.data.resources.mesh.MrMesh;
import mr.robotto.core.data.resources.mesh.MrVertexBuffer;
import mr.robotto.core.data.resources.shader.MrAttribute;

public class MrAxis extends MrMesh {

    private final static String AXIS_NAME = "MrAxis";

    public MrAxis() {
        super(AXIS_NAME, 6, MrMesh.DRAWTYPE_LINES, genKeyList(), genVertexBuffer(), genIndexBuffer());
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
        MrBufferKey vertexKey = new MrBufferKey(MrAttribute.VERTICES, MrDataType.FLOAT, 3, 6, 0);
        MrBufferKey colorKey = new MrBufferKey(MrAttribute.COLOR, MrDataType.FLOAT, 3, 6, 3);
        keyList.add(vertexKey);
        keyList.add(colorKey);
        return keyList;
    }
}
