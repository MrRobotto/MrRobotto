/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.primitives;

import mr.robotto.components.data.mesh.MrBuffer;
import mr.robotto.components.data.mesh.MrBufferKeyMap;
import mr.robotto.components.comp.MrMesh;

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
        //MrBuffer buffer = new MrVertexBuffer(vertices.length);
        MrBuffer buffer = MrBuffer.genVertexBuffer(vertices.length);
        buffer.putFloats(vertices);
        return buffer;
    }

    private static MrBuffer genIndexBuffer() {
        short[] indices = new short[]{
                0, 1,
                2, 3,
                4, 5
        };
        //MrBuffer buffer = new MrIndexBuffer(indices.length);
        MrBuffer buffer = MrBuffer.genIndexBuffer(indices.length);
        buffer.putShorts(indices);
        return buffer;
    }

    //TODO: Too much hardocre for my eyes!
    private static MrBufferKeyMap genKeyList() {
        /*MrBufferKeyMap keyList = new MrBufferKeyMap();
        MrBufferKey vertexKey = new MrBufferKey(MrAttribute.VERTICES, MrDataType.FLOAT, 3, 6, 0);
        MrBufferKey colorKey = new MrBufferKey(MrAttribute.COLOR, MrDataType.FLOAT, 3, 6, 3);
        keyList.add(vertexKey);
        keyList.add(colorKey);
        return keyList;*/
        return null;
    }
}
