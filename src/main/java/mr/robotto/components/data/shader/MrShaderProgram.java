/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.shader;

import mr.robotto.collections.MrHashMap;
import mr.robotto.collections.core.MrMapFunction;

public class MrShaderProgram {
    private MrShader mVertexShader;
    private MrShader mFragmentShader;
    private MrHashMap<String, MrUniform> mUniforms;
    private MrHashMap<Integer, MrAttribute> mAttributes;

    private int mId;

    public MrShaderProgram(MrShader vertexShader, MrShader fragmentShader) {
        mVertexShader = vertexShader;
        mFragmentShader = fragmentShader;
        init();
    }

    private void init() {
        mUniforms = new MrHashMap<>(new MrMapFunction<String, MrUniform>() {
            @Override
            public String getKeyOf(MrUniform mrUniform) {
                return mrUniform.getUniformType();
            }
        });
        mAttributes = new MrHashMap<>(new MrMapFunction<Integer, MrAttribute>() {
            @Override
            public Integer getKeyOf(MrAttribute mrAttribute) {
                return mrAttribute.getAttributeType();
            }
        });
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
        mUniforms.add(uniform);
    }

    public MrHashMap<String, MrUniform> getUniforms() {
        return mUniforms;
    }

    public void addAttribute(MrAttribute attribute) {
        mAttributes.add(attribute);
    }

    public MrHashMap<Integer, MrAttribute> getAttributes() {
        return mAttributes;
    }
}
