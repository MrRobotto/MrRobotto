/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.model.mesh;

import mr.robotto.renderer.core.data.model.mesh.buffers.MrBuffer;
import mr.robotto.renderer.core.data.model.mesh.keys.MrAttributeKeyList;

public class MrMesh
{
    private String name;
    private int count;
    private MrMeshDrawType drawType;
    private MrAttributeKeyList keys;
    private MrBuffer vertexBuffer;
    private MrBuffer indexBuffer;

    public MrMesh(String name, int count, MrMeshDrawType drawType, MrAttributeKeyList keys, MrBuffer vertexBuffer, MrBuffer indexBuffer)
    {
        this.name = name;
        this.count = count;
        this.drawType = drawType;
        this.keys = keys;
        this.vertexBuffer = vertexBuffer;
        this.indexBuffer = indexBuffer;
    }

    public MrMesh(MrMesh mesh)
    {
        this(mesh.name, mesh.count, mesh.drawType, mesh.keys, mesh.vertexBuffer, mesh.indexBuffer);
    }

    public String getName() {
        return name;
    }

    public int getCount()
    {
        return count;
    }

    public MrMeshDrawType getDrawType()
    {
        return drawType;
    }

    public void setDrawType(MrMeshDrawType drawType)
    {
        this.drawType = drawType;
    }

    public MrBuffer getVertexBuffer()
    {
        return vertexBuffer;
    }

    public MrBuffer getIndexBuffer()
    {
        return indexBuffer;
    }

    public MrAttributeKeyList getKeys() {return keys;}

}
