/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.primitives;

import mr.robotto.renderer.commons.MrDataType;
import mr.robotto.renderer.core.data.model.mesh.MrMesh;
import mr.robotto.renderer.core.data.model.mesh.MrMeshDrawType;
import mr.robotto.renderer.core.data.model.mesh.buffers.MrBuffer;
import mr.robotto.renderer.core.data.model.mesh.keys.MrAttributeKey;
import mr.robotto.renderer.core.data.model.mesh.keys.MrAttributeKeyList;
import mr.robotto.renderer.shaders.MrAttributeType;
import mr.robotto.renderer.core.data.model.mesh.buffers.MrIndexBuffer;
import mr.robotto.renderer.core.data.model.mesh.buffers.MrVertexBuffer;

public class MrAxis extends MrMesh {

    private static MrBuffer genVertexBuffer() {
        float[] vertices = new float[] {
               -10,  0,  0,     1,  0,  0,
                10,  0,  0,     1,  0,  0,
                 0,-10,  0,     0,  1,  0,
                 0, 10,  0,     0,  1,  0,
                 0,  0, -10,    0,  0,  1,
                 0,  0,  10,    0,  0,  1};
        MrBuffer buffer = new MrVertexBuffer(vertices.length);
        buffer.putFloats(vertices);
        return buffer;
    }

    private static MrBuffer genIndexBuffer() {
        short[] indices = new short[] {
                0,1,
                2,3,
                4,5
        };
        MrBuffer buffer = new MrIndexBuffer(indices.length);
        buffer.putShorts(indices);
        return buffer;
    }

    //TODO: Too much hardocre for my eyes!
    private static MrAttributeKeyList genKeyList() {
        MrAttributeKeyList keyList = new MrAttributeKeyList();
        MrAttributeKey vertexKey = new MrAttributeKey(MrAttributeType.VERTICES, "vertices", 0, MrDataType.FLOAT, 3, 6, 0);
        MrAttributeKey colorKey = new MrAttributeKey(MrAttributeType.COLOR, "color", 1, MrDataType.FLOAT, 3, 6, 3);
        keyList.addAttributeKey(vertexKey);
        keyList.addAttributeKey(colorKey);
        return keyList;
    }

    private final static String AXIS_NAME = "MrAxis";

    public MrAxis() {
        super(AXIS_NAME, 6, MrMeshDrawType.LINES, genKeyList(), genVertexBuffer(), genIndexBuffer());
    }
}
