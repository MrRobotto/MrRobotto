/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.rendereable.resources;

import android.opengl.GLES20;

import java.nio.IntBuffer;

import mr.robotto.core.data.resources.mesh.MrMesh;
import mr.robotto.core.data.resources.mesh.bufferkeys.MrBufferKey;
import mr.robotto.core.data.resources.mesh.buffers.MrBuffer;
import mr.robotto.core.rendereable.core.MrDrawable;
import mr.robotto.core.rendereable.core.MrLinkable;

//TODO: Controlar errores
public class MrMeshDrawer implements MrLinkable<MrMesh>, MrDrawable{

    private MrMesh mMesh;
    private boolean mInitialized = false;
    private boolean mLinked = false;
    private boolean mBinded = false;

    @Override
    public void linkWith(MrMesh link) {
        mMesh = link;
        mLinked = true;
    }

    @Override
    public boolean isLinked() {
        return mLinked;
    }

    @Override
    public void initialize() {
        initialize(mMesh.getVertexBuffer());
        initialize(mMesh.getIndexBuffer());
        mInitialized = true;
    }

    @Override
    public boolean isInitialized() {
        return mInitialized;
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
        bind(mMesh.getVertexBuffer());
        bind(mMesh.getIndexBuffer());
        for (MrBufferKey key : mMesh.getKeys()) {
            if (key.getIndex() >= 0)
                bind(key);
        }
        mBinded = true;
    }

    @Override
    public void unbind() {
        for (MrBufferKey key : mMesh.getKeys()) {
            if (key.getIndex() >= 0)
                unbind(key);
        }
        mBinded = false;
    }

    @Override
    public boolean isBinded() {
        return mBinded;
    }

    //TODO: Mirar el false este con el normalized
    private void bind(MrBufferKey key)
    {
        GLES20.glEnableVertexAttribArray(key.getIndex());
        GLES20.glVertexAttribPointer(key.getIndex(), key.getSize(), key.getDataType().getValue(), false, key.getStride(), key.getPointer());
    }

    private void bind(MrBuffer buffer)
    {
        GLES20.glBindBuffer(buffer.getBufferTarget().getValue(), buffer.getBufferId().get(0));
    }

    private void unbind(MrBufferKey key)
    {
        GLES20.glDisableVertexAttribArray(key.getIndex());
    }

    @Override
    public void update() {

    }

    //TODO: Check the cullface of objects
    @Override
    public void draw() {
        GLES20.glDrawElements(mMesh.getDrawType().getValue(), mMesh.getCount(), mMesh.getIndexBuffer().getBufferDataType().getValue(), 0);
    }
}
