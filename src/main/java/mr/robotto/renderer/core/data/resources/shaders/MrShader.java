/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.resources.shaders;

public class MrShader {
    private MrShaderType shaderType;
    private String source;
    private int id;

    public MrShader(MrShaderType shaderType, String source) {
        this.shaderType = shaderType;
        this.source = source;
    }

    public MrShaderType getShaderType() {
        return shaderType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }
}
