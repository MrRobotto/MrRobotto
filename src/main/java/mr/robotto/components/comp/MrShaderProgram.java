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

import java.util.HashMap;
import java.util.Map;

import mr.robotto.components.data.shader.MrAttribute;
import mr.robotto.components.data.shader.MrShader;
import mr.robotto.components.data.shader.MrUniform;
import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;

public class MrShaderProgram extends MrComponent {
    private Data mData;
    private View mPresenter;

    public MrShaderProgram(String name, MrShader vertexShader, MrShader fragmentShader) {
        mData = new Data(name, vertexShader, fragmentShader);
        mPresenter = new View();
    }

    @Override
    public MrComponent.Data getData() {
        return mData;
    }

    @Override
    public MrComponent.View getView() {
        return mPresenter;
    }

    @Override
    public String getName() {
        return mData.getName();
    }

    public int getId() {
        return mData.getId();
    }

    public void setId(int id) {
        mData.setId(id);
    }

    public MrShader getVertexShader() {
        return mData.getVertexShader();
    }

    public MrShader getFragmentShader() {
        return mData.getFragmentShader();
    }

    public Map<String, MrUniform> getUniforms() {
        return mData.getUniforms();
    }

    public void addUniform(MrUniform uniform) {
        mData.addUniform(uniform);
    }

    public void addAttribute(MrAttribute attribute) {
        mData.addAttribute(attribute);
    }

    public Map<Integer, MrAttribute> getAttributes() {
        return mData.getAttributes();
    }

    public void bindUniforms(Map<String, MrUniformKey> uniformKeys) {
        mPresenter.bindUniforms(uniformKeys);
    }

    protected static class Data extends MrComponent.Data {
        private String mName;
        private MrShader mVertexShader;
        private MrShader mFragmentShader;
        private Map<String, MrUniform> mUniforms;
        private Map<Integer, MrAttribute> mAttributes;

        private int mId;

        public Data(String name, MrShader vertexShader, MrShader fragmentShader) {
            mName = name;
            mVertexShader = vertexShader;
            mFragmentShader = fragmentShader;
            init();
        }

        private void init() {
            mUniforms = new HashMap<String, MrUniform>();
            mAttributes = new HashMap<>();
        }

        @Override
        public String getName() {
            return mName;
        }

        public int getId() {
            return mId;
        }

        public void setId(int id) {
            this.mId = id;
        }

        public MrShader getVertexShader() {
            return mVertexShader;
        }

        public MrShader getFragmentShader() {
            return mFragmentShader;
        }

        public void addUniform(MrUniform uniform) {
            mUniforms.put(uniform.getName(), uniform);
        }

        public Map<String, MrUniform> getUniforms() {
            return mUniforms;
        }

        public void addAttribute(MrAttribute attribute) {
            mAttributes.put(attribute.getAttributeType(), attribute);
        }

        public Map<Integer, MrAttribute> getAttributes() {
            return mAttributes;
        }
    }

    protected class View extends MrComponent.View {

        private MrUniform[] mUniformsList;

        @Override
        public void bind() {
            GLES20.glUseProgram(mData.getId());
        }

        @Override
        public void unbind() {
            GLES20.glUseProgram(0);
        }

        //TODO: Intentar colocar esto en otro lugar
        public void bindUniforms(Map<String, MrUniformKey> uniformKeys) {
            //for (MrUniform uniform : mData.getUniforms().values()) {
            //    MrUniformKey key = uniformKeys.get(uniform.getUniformType());
            //    bindUniform(uniform, key.getValue());
            //}
            for (MrUniform uniform : mUniformsList) {
                MrUniformKey key = uniformKeys.get(uniform.getUniformType());
                if (key == null) {
                    throw new RuntimeException("Can't find uniformkey for uniform: "+uniform.toString());
                }
                bindUniform(uniform, key.getValue());
            }
        }

        public void bindUniform(MrUniformKey key) {
            MrUniform uniform = mData.getUniforms().get(key.getUniformType());
            bindUniform(uniform, key.getValue());
        }

        //TODO: Check uniform/element count, uniform/element datatype
        private void bindUniform(MrUniform uniform, MrLinearAlgebraObject element) {
            int uniformId = uniform.getId();

            int uniformCount = uniform.getCount();
            float[] values = element.getValues();
            switch (element.getDataType()) {
                case MAT4:
                    GLES20.glUniformMatrix4fv(uniformId, uniformCount, false, values, 0);
                    break;
                case VEC3:
                    GLES20.glUniform3fv(uniformId, uniformCount,values, 0);
                    break;
                case VEC4:
                    GLES20.glUniform4fv(uniformId, uniformCount,values, 0);
                    break;
                case SAMPLER2D:
                    GLES20.glUniform1i(uniformId, (int)values[0]);
            }
        }

        private void initialize(MrShader shader) {
            int id = GLES20.glCreateShader(shader.getShaderType());

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
                if (shader.getShaderType() == MrShader.SHADERTYPE_VERTEX_SHADER) {
                    throw new RuntimeException("Error creating vertex shader. " + errorMessage + " GLERROR:" + error);
                } else {
                    throw new RuntimeException("Error creating fragment shader. " + errorMessage + " GLERROR:" + error);
                }
            }
        }

        private void initialize(MrAttribute attribute) {
            GLES20.glBindAttribLocation(mData.getId(), attribute.getIndex(), attribute.getName());
        }

        private void initialize(MrUniform uniform) {
            int location = GLES20.glGetUniformLocation(mData.getId(), uniform.getName());
            uniform.setId(location);
        }

        //TODO: Check runtime errors
        @Override
        public void initialize() {
            initialize(mData.getVertexShader());
            initialize(mData.getFragmentShader());

            int id = GLES20.glCreateProgram();
            mData.setId(id);
            GLES20.glAttachShader(id, mData.getVertexShader().getId());
            GLES20.glAttachShader(id, mData.getFragmentShader().getId());
            for (MrAttribute attribute : mData.getAttributes().values()) {
                initialize(attribute);
            }

            GLES20.glLinkProgram(id);

            mUniformsList = new MrUniform[mData.getUniforms().size()];
            int i = 0;
            for (MrUniform uniform : mData.getUniforms().values()) {
                initialize(uniform);
                mUniformsList[i] = uniform;
                i++;
            }

            final int[] linkStatus = new int[1];
            GLES20.glGetProgramiv(id, GLES20.GL_LINK_STATUS, linkStatus, 0);
            if (linkStatus[0] == 0) {
                GLES20.glDeleteProgram(id);
                throw new RuntimeException("Error creating program.");
            }
        }
    }
}
