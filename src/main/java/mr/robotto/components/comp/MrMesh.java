/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.comp;

import android.opengl.GLES20;

import java.nio.IntBuffer;
import java.util.Map;

import mr.robotto.components.data.mesh.MrBuffer;
import mr.robotto.components.data.mesh.MrBufferKey;

public class MrMesh extends MrComponent {
    public static final int DRAWTYPE_LINES = GLES20.GL_LINES;
    public static final int DRAWTYPE_TRIANGLES = GLES20.GL_TRIANGLES;

    private Data mData;
    private Render mRender;

    public MrMesh(String name, int count, int drawType, Map<Integer, MrBufferKey> keys, MrBuffer vertexBuffer, MrBuffer indexBuffer) {
        mData = new Data(name, count, drawType, keys, vertexBuffer, indexBuffer);
        mRender = new Render();
    }

    @Override
    public String getType() {
        return MrComponent.TYPE_MESH;
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
        if (mRenderingContext.getBoundMesh() != this) {
            super.bind();
            mRenderingContext.setMesh(this);
        }
    }

    public MrBuffer getIndexBuffer() {
        return mData.getIndexBuffer();
    }

    public Map<Integer, MrBufferKey> getBufferKeys() {
        return mData.getBufferKeys();
    }

    public int getCount() {
        return mData.getCount();
    }

    public MrBuffer getVertexBuffer() {
        return mData.getVertexBuffer();
    }

    public int getDrawType() {
        return mData.getDrawType();
    }

    public void draw() {
        mRender.draw();
    }

    protected static class Data extends MrComponent.Data {

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
            buffer.setBufferId(id);
            GLES20.glGenBuffers(1, id);
            GLES20.glBindBuffer(buffer.getBufferTarget(), buffer.getId());
            GLES20.glBufferData(buffer.getBufferTarget(), buffer.asBuffer().capacity(), buffer.asBuffer(), buffer.getBufferUsage());
            buffer.releaseBuffer();
        }

        public void bind() {
            bind(mData.getVertexBuffer());
            bind(mData.getIndexBuffer());
            //for (MrBufferKey key : mData.getBufferKeys().values()) {
            //    if (key.getIndex() >= 0)
            //        bind(key);
            //}
            for (MrBufferKey key : mKeysList) {
                if (key.getIndex() >= 0) {
                    bind(key);
                }
            }
        }

        public void unbind() {
            //for (MrBufferKey key : mData.getBufferKeys().values()) {
            //    if (key.getIndex() >= 0)
            //        unbind(key);
            //}
            for (MrBufferKey key : mKeysList) {
                if (key.getIndex() >= 0) {
                    unbind(key);
                }
            }
        }

        //TODO: Mirar el false este con el normalized
        private void bind(MrBufferKey key) {
            GLES20.glEnableVertexAttribArray(key.getIndex());
            GLES20.glVertexAttribPointer(key.getIndex(), key.getSize(), key.getDataType().getValue(), false, key.getStride(), key.getPointer());
        }

        private void bind(MrBuffer buffer) {
            GLES20.glBindBuffer(buffer.getBufferTarget(), buffer.getBufferId());
        }

        private void unbind(MrBufferKey key) {
            GLES20.glDisableVertexAttribArray(key.getIndex());
        }

        //TODO: Check the cullface of objects
        public void draw() {
            GLES20.glDrawElements(mData.getDrawType(), mData.getCount(), mData.getIndexBuffer().getBufferDataType().getValue(), 0);
        }
    }
}