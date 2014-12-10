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

import mr.robotto.core.data.resources.shaders.MrShader;
import mr.robotto.core.data.resources.shaders.MrShaderProgram;
import mr.robotto.core.data.resources.shaders.MrShaderType;
import mr.robotto.core.data.resources.shaders.input.attributes.MrAttribute;
import mr.robotto.core.data.resources.shaders.input.uniforms.MrUniform;
import mr.robotto.core.rendereable.core.MrBindable;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;

//TODO: Controlar errores
public class MrShaderProgramBinder implements MrBindable<MrShaderProgram> {

    private MrShaderProgram mShaderProgram;
    private boolean mInitialized;
    private boolean mLinked;
    private boolean mBinded = false;

    @Override
    public void linkWith(MrShaderProgram link) {
        mShaderProgram = link;
        mLinked = true;
    }

    @Override
    public void bind() {
        GLES20.glUseProgram(mShaderProgram.getId());
        mBinded = true;
    }

    @Override
    public void unbind() {
        GLES20.glUseProgram(0);
        mBinded = false;
    }

    @Override
    public boolean isBinded() {
        return mBinded;
    }

    //TODO: Check uniform/element count, uniform/element datatype
    public void bindUniform(MrUniform uniform, MrLinearAlgebraObject element) {
        int programId = mShaderProgram.getId();
        int uniformId = uniform.getId();
        int uniformCount = uniform.getCount();
        float[] values = element.getValues();
        switch (element.getDataType()) {
            case MAT4:
                GLES20.glUniformMatrix4fv(programId, uniformId, false, values, uniformCount);
                break;
            case VEC3:
                GLES20.glUniform3fv(programId, uniformId, values, uniformCount);
                break;
            case VEC4:
                GLES20.glUniform4fv(programId, uniformId, values, uniformCount);
                break;
        }
    }

    private void initialize(MrShader shader) {
        int id = GLES20.glCreateShader(shader.getShaderType().getValue());

        GLES20.glShaderSource(id, shader.getSource());
        GLES20.glCompileShader(id);

        shader.setId(id);

        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shader.getId(), GLES20.GL_COMPILE_STATUS, compileStatus, 0);

        // If the compilation failed, delete the shader.
        if (compileStatus[0] == 0) {
            String errorMessage = GLES20.glGetShaderInfoLog(id);
            int error = GLES20.glGetError();
            GLES20.glDeleteShader(id);
            if (shader.getShaderType() == MrShaderType.VERTEX_SHADER) {
                throw new RuntimeException("Error creating vertex shader. " + errorMessage + " GLERROR:" + error);
            } else {
                throw new RuntimeException("Error creating fragment shader. " + errorMessage + " GLERROR:" + error);
            }
        }
    }

    private void initialize(MrAttribute attribute) {
        GLES20.glBindAttribLocation(mShaderProgram.getId(), attribute.getIndex(), attribute.getName());
    }

    private void initialize(MrUniform uniform) {
        int location = GLES20.glGetUniformLocation(mShaderProgram.getId(), uniform.getName());
        uniform.setId(location);
    }

    //TODO: Check runtime errors
    @Override
    public void initialize() {
        initialize(mShaderProgram.getVertexShader());
        initialize(mShaderProgram.getFragmentShader());

        int id = GLES20.glCreateProgram();
        mShaderProgram.setId(id);
        GLES20.glAttachShader(id, mShaderProgram.getVertexShader().getId());
        GLES20.glAttachShader(id, mShaderProgram.getFragmentShader().getId());
        for (MrAttribute attribute : mShaderProgram.getAttributes()) {
            initialize(attribute);
        }

        GLES20.glLinkProgram(id);

        final int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(id, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) {
            GLES20.glDeleteProgram(id);
            throw new RuntimeException("Error creating program.");
        }
        mInitialized = true;
    }

    @Override
    public boolean isInitialized() {
        return mInitialized;
    }


    @Override
    public boolean isLinked() {
        return mLinked;
    }
}
