/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.resources.shaders;

import mr.robotto.renderer.core.data.resources.shaders.input.MrAttribute;
import mr.robotto.renderer.core.data.resources.shaders.input.MrAttributeContainer;
import mr.robotto.renderer.core.data.resources.shaders.input.MrUniform;
import mr.robotto.renderer.core.data.resources.shaders.input.MrUniformContainer;

//TODO: Change the arraylist for hashmaps or specialized classes
public class MrShaderProgram {
    private MrVertexShader mVertexShader;
    private MrFragmentShader mFragmentShader;
    private MrUniformContainer mUniforms;
    private MrAttributeContainer mAttributes;

    private int mId;

    public MrShaderProgram(MrVertexShader vertexShader, MrFragmentShader fragmentShader) {
        mVertexShader = vertexShader;
        mFragmentShader = fragmentShader;
        init();
    }

    private void init() {
        mUniforms = new MrUniformContainer();
        mAttributes = new MrAttributeContainer();
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

    public MrUniformContainer getUniforms() {
        return mUniforms;
    }

    public void addAttribute(MrAttribute attribute) {
        mAttributes.add(attribute);
    }

    public MrAttributeContainer getAttributes() {
        return mAttributes;
    }
}
