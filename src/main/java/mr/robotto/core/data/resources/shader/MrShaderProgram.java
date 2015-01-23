/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data.resources.shader;

import mr.robotto.collections.MrMapContainer;
import mr.robotto.collections.core.MrMapFunction;

public class MrShaderProgram {
    private MrVertexShader mVertexShader;
    private MrFragmentShader mFragmentShader;
    private MrMapContainer<String, MrUniform> mUniforms;
    private MrMapContainer<Integer, MrAttribute> mAttributes;

    private int mId;

    public MrShaderProgram(MrVertexShader vertexShader, MrFragmentShader fragmentShader) {
        mVertexShader = vertexShader;
        mFragmentShader = fragmentShader;
        init();
    }

    private void init() {
        mUniforms = new MrMapContainer<>(new MrMapFunction<String, MrUniform>() {
            @Override
            public String getKeyOf(MrUniform mrUniform) {
                return mrUniform.getUniformType();
            }
        });
        mAttributes = new MrMapContainer<>(new MrMapFunction<Integer, MrAttribute>() {
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

    public MrVertexShader getVertexShader() {
        return mVertexShader;
    }

    public MrFragmentShader getFragmentShader() {
        return mFragmentShader;
    }

    public void addUniform(MrUniform uniform) {
        mUniforms.add(uniform);
    }

    public MrMapContainer<String, MrUniform> getUniforms() {
        return mUniforms;
    }

    public void addAttribute(MrAttribute attribute) {
        mAttributes.add(attribute);
    }

    public MrMapContainer<Integer, MrAttribute> getAttributes() {
        return mAttributes;
    }
}
