/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.shaders;

import java.util.ArrayList;
import java.util.List;

//TODO: Change the arraylist for hashmaps or specialized classes
public class MrShaderProgram {
    private MrVertexShader vertexShader;
    private MrFragmentShader fragmentShader;
    private ArrayList<MrUniform> uniforms;
    private ArrayList<MrAttribute> attributes;

    private int id;

    public MrShaderProgram(MrVertexShader vertexShader, MrFragmentShader fragmentShader) {
        this.vertexShader = vertexShader;
        this.fragmentShader = fragmentShader;
        init();
    }

    private void init() {
        uniforms = new ArrayList<MrUniform>();
        attributes = new ArrayList<MrAttribute>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MrVertexShader getVertexShader() {
        return vertexShader;
    }

    public MrFragmentShader getFragmentShader() {
        return fragmentShader;
    }

    public void addUniform(MrUniform uniform) {
        uniforms.add(uniform);
    }

    public List<MrUniform> getUniforms() {
        return uniforms;
    }

    public void addAttribute(MrAttribute attribute) {
        attributes.add(attribute);
    }

    public List<MrAttribute> getAttributes() {
        return attributes;
    }
}
