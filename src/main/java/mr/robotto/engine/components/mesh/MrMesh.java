/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.mesh;

import android.opengl.GLES20;

import java.nio.IntBuffer;
import java.util.Map;

import mr.robotto.engine.components.MrSharedComponent;

/**
 * This class represents a 3D Mesh and allows to draw it
 */
public class MrMesh extends MrSharedComponent {
    /**
     * Draws the mesh's faces as lines
     */
    public static final int DRAWTYPE_LINES = GLES20.GL_LINES;
    /**
     * Draws the mesh's faces using triangles
     */
    public static final int DRAWTYPE_TRIANGLES = GLES20.GL_TRIANGLES;

    private Data mData;
    private Render mRender;

    /**
     * Creates a new mesh
     *
     * @param name         Name of the mesh
     * @param count        Number of faces
     * @param drawType     Used drawtype
     * @param keys         Buffer keys of this mesh
     * @param vertexBuffer Vertex data as a buffer
     * @param indexBuffer  Vertex indices data as a buffer
     */
    public MrMesh(String name, int count, int drawType, Map<Integer, MrBufferKey> keys, MrBuffer vertexBuffer, MrBuffer indexBuffer) {
        mData = new Data(name, count, drawType, keys, vertexBuffer, indexBuffer);
        mRender = new Render();
    }

    @Override
    public String getType() {
        return TYPE_MESH;
    }

    @Override
    public String getName() {
        return mData.getName();
    }

    @Override
    public Data getData() {
        return mData;
    }

    @Override
    public View getView() {
        return mRender;
    }

    @Override
    public void bind() {
        MrMesh boundMesh = mRenderingContext.getBoundMesh();
        if (boundMesh != this) {
            if (boundMesh != null)
                boundMesh.unbind();
            super.bind();
            mRenderingContext.setMesh(this);
        }
    }

    /**
     * Gets the IBO linked to this mesh
     *
     * @return index array buffer object
     */
    public MrBuffer getIndexBuffer() {
        return mData.getIndexBuffer();
    }

    /**
     * Gets all buffer-keys defined in this mesh
     *
     * @return buffer keys
     */
    public Map<Integer, MrBufferKey> getBufferKeys() {
        return mData.getBufferKeys();
    }

    /**
     * Gets the number of faces of this mesh
     *
     * @return number of faces
     */
    public int getCount() {
        return mData.getCount();
    }

    /**
     * Gets the VBO linked to this mesh
     *
     * @return vertex array buffer object
     */
    public MrBuffer getVertexBuffer() {
        return mData.getVertexBuffer();
    }

    /**
     * Gets the current draw type for this mesh
     *
     * @return draw type
     */
    public int getDrawType() {
        return mData.getDrawType();
    }

    /**
     * Draws the mesh
     */
    public void draw() {
        mRender.draw();
    }

    protected static class Data extends MrSharedComponent.Data {

        private String mName;
        private int mCount;
        private int mDrawType;
        private Map<Integer, MrBufferKey> mKeys;
        private MrBuffer mVertexBuffer;
        private MrBuffer mIndexBuffer;

        public Data(String name, int count, int drawType, Map<Integer, MrBufferKey> keys, MrBuffer vertexBuffer, MrBuffer indexBuffer) {
            mName = name;
            mCount = count;
            mDrawType = drawType;
            mKeys = keys;
            mVertexBuffer = vertexBuffer;
            mIndexBuffer = indexBuffer;
        }

        @Override
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

        public Map<Integer, MrBufferKey> getBufferKeys() {
            return mKeys;
        }
    }

    protected class Render extends View {

        private MrBufferKey[] mKeysList;

        public void initialize() {
            initialize(mData.getVertexBuffer());
            initialize(mData.getIndexBuffer());
            mKeysList = new MrBufferKey[mData.mKeys.size()];
            int i = 0;
            for (MrBufferKey key : mData.mKeys.values()) {
                mKeysList[i] = key;
                i++;
            }
        }

        private void initialize(MrBuffer buffer) {
            IntBuffer id = IntBuffer.allocate(1);
            GLES20.glGenBuffers(1, id);
            buffer.setId(id.get(0));
            GLES20.glBindBuffer(buffer.getBufferTarget(), buffer.getId());
            GLES20.glBufferData(buffer.getBufferTarget(), buffer.asBuffer().capacity(), buffer.asBuffer(), buffer.getBufferUsage());
            buffer.releaseBuffer();
        }

        public void bind() {
            bind(mData.getVertexBuffer());
            bind(mData.getIndexBuffer());
            //for (MrBufferKey key : mData.getBufferKeys().values()) {
            //    if (key.getId() >= 0)
            //        bind(key);
            //}
            for (MrBufferKey key : mKeysList) {
                if (key.getId() >= 0) {
                    bind(key);
                }
            }
        }

        public void unbind() {
            //for (MrBufferKey key : mData.getBufferKeys().values()) {
            //    if (key.getId() >= 0)
            //        unbind(key);
            //}
            for (MrBufferKey key : mKeysList) {
                if (key.getId() >= 0) {
                    unbind(key);
                }
            }
        }

        //TODO: Mirar el false este con el normalized
        private void bind(MrBufferKey key) {
            GLES20.glEnableVertexAttribArray(key.getId());
            GLES20.glVertexAttribPointer(key.getId(), key.getSize(), key.getDataType().getValue(), false, key.getStride(), key.getPointer());
        }

        private void bind(MrBuffer buffer) {
            GLES20.glBindBuffer(buffer.getBufferTarget(), buffer.getId());
        }

        private void unbind(MrBufferKey key) {
            GLES20.glDisableVertexAttribArray(key.getId());
        }

        //TODO: Check the cullface of objects
        public void draw() {
            GLES20.glDrawElements(mData.getDrawType(), mData.getCount(), mData.getIndexBuffer().getBufferDataType().getValue(), 0);
        }
    }
}