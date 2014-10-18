/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.renderer.rendereables.resources;

import android.opengl.GLES20;

import java.nio.IntBuffer;

import mr.robotto.renderer.data.model.mesh.MrMesh;
import mr.robotto.renderer.data.model.mesh.buffers.MrBuffer;
import mr.robotto.renderer.data.model.mesh.keys.MrAttributeKey;
import mr.robotto.renderer.renderer.rendereables.core.MrDrawable;
import mr.robotto.renderer.renderer.rendereables.core.MrLinkable;

//TODO: Controlar errores
public class MrMeshDrawer implements MrDrawable<MrMesh> {

    private MrMesh mesh;
    private boolean initialized = false;
    private boolean linked = false;
    private boolean binded = false;

    @Override
    public void linkWith(MrMesh link) {
        this.mesh = link;
        linked = true;
    }

    @Override
    public boolean isLinked() {
        return linked;
    }

    @Override
    public void initialize() {
        initialize(mesh.getVertexBuffer());
        initialize(mesh.getIndexBuffer());
        initialized = true;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    private void initialize(MrBuffer buffer)
    {
        IntBuffer id = IntBuffer.allocate(1);
        buffer.setBufferId(id);
        GLES20.glGenBuffers(1, id);
        GLES20.glBindBuffer(buffer.getBufferTarget().getValue(),buffer.getId());
        GLES20.glBufferData(buffer.getBufferTarget().getValue(), buffer.asBuffer().capacity(), buffer.asBuffer() ,buffer.getBufferUsage().getValue());
        //buffer.releaseBuffer();
    }

    @Override
    public void bind() {
        bind(mesh.getVertexBuffer());
        bind(mesh.getIndexBuffer());
        for (MrAttributeKey key : mesh.getKeys()) { bind(key); }
        binded = true;
    }

    @Override
    public void unbind() {
        for (MrAttributeKey key : mesh.getKeys()) { unbind(key); }
        binded = false;
    }

    @Override
    public boolean isBinded() {
        return binded;
    }

    //TODO: Mirar el false este con el normalized
    private void bind(MrAttributeKey key)
    {
        GLES20.glEnableVertexAttribArray(key.getIndex());
        GLES20.glVertexAttribPointer(key.getIndex(), key.getSize(), key.getDataType().getValue(), false, key.getStride(), key.getPointer());
    }

    private void bind(MrBuffer buffer)
    {
        GLES20.glBindBuffer(buffer.getBufferTarget().getValue(), buffer.getBufferId().get(0));
    }

    private void unbind(MrAttributeKey key)
    {
        GLES20.glDisableVertexAttribArray(key.getIndex());
    }

    @Override
    public void update() {

    }

    //TODO: Check the cullface of objects
    @Override
    public void draw() {
        GLES20.glDrawElements(mesh.getDrawType().getValue(), mesh.getCount(), mesh.getIndexBuffer().getBufferDataType().getValue(), 0);
    }
}
