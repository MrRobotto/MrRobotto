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

import mr.robotto.renderer.renderer.rendereables.core.MrBindable;
import mr.robotto.renderer.renderer.rendereables.core.MrLinkable;
import mr.robotto.renderer.shaders.MrAttribute;
import mr.robotto.renderer.shaders.MrShader;
import mr.robotto.renderer.shaders.MrShaderProgram;
import mr.robotto.renderer.shaders.MrShaderType;
import mr.robotto.renderer.shaders.MrUniform;
import mr.robotto.renderer.linearalgebra.MrLinearAlgebraObject;

//TODO: Controlar errores
public class MrShaderProgramBinder implements MrBindable<MrShaderProgram> {

    private MrShaderProgram program;
    private boolean initialized;
    private boolean linked;
    private boolean binded = false;

    @Override
    public void bind() {
        GLES20.glUseProgram(program.getId());
        binded = true;
    }

    @Override
    public void unbind() {
        GLES20.glUseProgram(0);
        binded = false;
    }

    @Override
    public boolean isBinded() {
        return binded;
    }

    //TODO: Check uniform/element count, uniform/element datatype
    public void bindUniform(MrUniform uniform, MrLinearAlgebraObject element) {
        int programId = program.getId();
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

    private void initialize(MrShader shader)
    {
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
        GLES20.glBindAttribLocation(program.getId(), attribute.getIndex(), attribute.getName());
    }

    private void initialize(MrUniform uniform) {
        int location = GLES20.glGetUniformLocation(program.getId(), uniform.getName());
        uniform.setId(location);
    }

    @Override
    public void initialize() {
        initialize(program.getVertexShader());
        initialize(program.getFragmentShader());

        int id = GLES20.glCreateProgram();
        program.setId(id);
        GLES20.glAttachShader(id, program.getVertexShader().getId());
        GLES20.glAttachShader(id, program.getFragmentShader().getId());
        for (MrAttribute attribute : program.getAttributes()) {
            initialize(attribute);
        }

        GLES20.glLinkProgram(id);

        final int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(id, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) {
            GLES20.glDeleteProgram(id);
            throw new RuntimeException("Error creating program.");
        }
        initialized = true;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public void linkWith(MrShaderProgram link) {
        program = link;
        linked = true;
    }

    @Override
    public boolean isLinked() {
        return linked;
    }
}
