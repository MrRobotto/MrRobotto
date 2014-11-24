/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data.resources.mesh;

import mr.robotto.core.data.resources.mesh.bufferkeys.MrBufferKeyContainer;
import mr.robotto.core.data.resources.mesh.buffers.MrBuffer;

public class MrMesh
{
    private String mName;
    private int mCount;
    private MrMeshDrawType mDrawType;
    private MrBufferKeyContainer mKeys;
    private MrBuffer mVertexBuffer;
    private MrBuffer mIndexBuffer;

    public MrMesh(String name, int count, MrMeshDrawType drawType, MrBufferKeyContainer keys, MrBuffer vertexBuffer, MrBuffer indexBuffer)
    {
        mName = name;
        mCount = count;
        mDrawType = drawType;
        mKeys = keys;
        mVertexBuffer = vertexBuffer;
        mIndexBuffer = indexBuffer;
    }

    public MrMesh(MrMesh mesh)
    {
        this(mesh.mName, mesh.mCount, mesh.mDrawType, mesh.mKeys, mesh.mVertexBuffer, mesh.mIndexBuffer);
    }

    public String getName() {
        return mName;
    }

    public int getCount()
    {
        return mCount;
    }

    public MrMeshDrawType getDrawType()
    {
        return mDrawType;
    }

    public void setDrawType(MrMeshDrawType drawType)
    {
        this.mDrawType = drawType;
    }

    public MrBuffer getVertexBuffer()
    {
        return mVertexBuffer;
    }

    public MrBuffer getIndexBuffer()
    {
        return mIndexBuffer;
    }

    public MrBufferKeyContainer getKeys() {return mKeys;}

}
