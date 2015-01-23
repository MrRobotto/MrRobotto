/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data.resources.mesh;

import android.opengl.GLES20;

import mr.robotto.collections.MrMapContainer;

public class MrMesh {

    public static final int DRAWTYPE_LINES = GLES20.GL_LINES;
    public static final int DRAWTYPE_TRIANGLES = GLES20.GL_TRIANGLES;

    private String mName;
    private int mCount;
    private int mDrawType;
    private MrMapContainer<Integer, MrBufferKey> mKeys;
    private MrBuffer mVertexBuffer;
    private MrBuffer mIndexBuffer;

    public MrMesh(String name, int count, int drawType, MrMapContainer<Integer, MrBufferKey> keys, MrBuffer vertexBuffer, MrBuffer indexBuffer) {
        mName = name;
        mCount = count;
        mDrawType = drawType;
        mKeys = keys;
        mVertexBuffer = vertexBuffer;
        mIndexBuffer = indexBuffer;
    }

    public MrMesh(MrMesh mesh) {
        this(mesh.mName, mesh.mCount, mesh.mDrawType, mesh.mKeys, mesh.mVertexBuffer, mesh.mIndexBuffer);
    }

    public String getName() {
        return mName;
    }

    public int getCount() {
        return mCount;
    }

    public int getDrawType() {
        return mDrawType;
    }

    public void setDrawType(int drawType) {
        this.mDrawType = drawType;
    }

    public MrBuffer getVertexBuffer() {
        return mVertexBuffer;
    }

    public MrBuffer getIndexBuffer() {
        return mIndexBuffer;
    }

    public MrMapContainer<Integer, MrBufferKey> getBufferKeys() {
        return mKeys;
    }

}
