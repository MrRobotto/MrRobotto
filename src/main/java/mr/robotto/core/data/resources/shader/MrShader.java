/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data.resources.shader;

import android.opengl.GLES20;


public class MrShader {

    public static final int SHADERTYPE_VERTEX_SHADER = GLES20.GL_VERTEX_SHADER;
    public static final int SHADERTYPE_FRAGMENT_SHADER = GLES20.GL_FRAGMENT_SHADER;

    private int mShaderType;
    private String mSource;
    private int mId;

    public MrShader(int shaderType, String source) {
        this.mShaderType = shaderType;
        this.mSource = source;
    }

    public int getShaderType() {
        return mShaderType;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getSource() {
        return mSource;
    }
}
