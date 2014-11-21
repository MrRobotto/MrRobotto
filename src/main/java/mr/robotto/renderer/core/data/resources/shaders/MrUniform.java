/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.resources.shaders;

import mr.robotto.renderer.commons.MrDataType;

public class MrUniform {
    private MrUniformType uniformType;
    private String name;
    private MrDataType dataType;
    private int count;
    private int id;

    public MrUniform(MrUniformType uniformType, String name, MrDataType dataType, int count) {
        this.name = name;
        this.uniformType = uniformType;
        this.dataType = dataType;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public MrUniformType getUniformType() {
        return uniformType;
    }

    public MrDataType getDataType() {
        return dataType;
    }

    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

