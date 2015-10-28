/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.material;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import java.nio.IntBuffer;

import mr.robotto.engine.components.MrSharedComponent;

/**
 * A class for representing a texture
 */
public class MrTexture extends MrSharedComponent {

    public static final int MAG_FILTER_NEAREST = GLES20.GL_NEAREST;
    public static final int MAG_FILTER_LINEAR = GLES20.GL_LINEAR;

    public static final int MIN_FILTER_NEAREST = GLES20.GL_NEAREST;
    public static final int MIN_FILTER_LINEAR = GLES20.GL_NEAREST;
    public static final int MIN_FILTER_NEAREST_NEAREST = GLES20.GL_NEAREST_MIPMAP_NEAREST;
    public static final int MIN_FILTER_NEAREST_LINEAR = GLES20.GL_NEAREST_MIPMAP_LINEAR;
    public static final int MIN_FILTER_LINEAR_LINEAR = GLES20.GL_LINEAR_MIPMAP_LINEAR;
    public static final int MIN_FILTER_LINEAR_NEAREST = GLES20.GL_LINEAR_MIPMAP_NEAREST;

    private View mView;
    private Data mData;

    public MrTexture(String name, int index, int magFilter, int minFilter, Bitmap bitmap) {
        mData = new Data(name, index, magFilter, minFilter, bitmap);
        mView = new View();
    }

    @Override
    public String getType() {
        return TYPE_TEXTURE;
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
        return mView;
    }

    @Override
    public void bind() {
        MrTexture boundTexture = mRenderingContext.getBoundTexture();
        if (boundTexture != this) {
            if (boundTexture != null)
                boundTexture.unbind();
            super.bind();
            mRenderingContext.setTexture(this);
        }
    }

    public int getIndex() {
        return mData.getIndex();
    }

    public int getId() {
        return mData.getId();
    }

    public int getMagFilter() {
        return mData.getMagFilter();
    }

    public int getMinFilter() {
        return mData.getMinFilter();
    }

    public Bitmap getBitmap() {
        return mData.getBitmap();
    }

    protected static class Data extends MrSharedComponent.Data {

        private String mName;
        private int mIndex;
        private int mMagFilter;
        private int mMinFilter;
        private Bitmap mBitmap;
        private int mId;

        public Data(String name, int index, int magFilter, int minFilter, Bitmap bitmap) {
            mName = name;
            mIndex = index;
            mMagFilter = magFilter;
            mMinFilter = minFilter;
            mBitmap = bitmap;
        }

        @Override
        public String getName() {
            return mName;
        }

        public Bitmap getBitmap() {
            return mBitmap;
        }

        public int getId() {
            return mId;
        }

        public void setId(int id) {
            mId = id;
        }

        public int getIndex() {
            return mIndex;
        }

        public void setIndex(int index) {
            mIndex = index;
        }

        public int getMagFilter() {
            return mMagFilter;
        }

        public int getMinFilter() {
            return mMinFilter;
        }
    }

    protected class View extends MrSharedComponent.View {

        @Override
        public void initialize() {
            Bitmap bitmap = mData.mBitmap;
            IntBuffer buf = IntBuffer.allocate(1);

            GLES20.glGenTextures(1, buf);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, buf.get(0));

            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

            //GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);

            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, mData.mMinFilter);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, mData.mMagFilter);
            //GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            //GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
            //GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
            //GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);

            //bitmap.recycle();

            if (buf.get(0) == 0) {
                throw new RuntimeException("Error loading texture.");
            }

            mData.setId(buf.get(0));
        }

        @Override
        public void bind() {
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0+mData.mIndex);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mData.getId());
        }

        @Override
        public void unbind() {
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        }
    }
}
