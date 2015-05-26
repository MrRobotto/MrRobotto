/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.shader;

import mr.robotto.commons.MrDataType;

public class MrUniform {

    public static final String MODEL_MATRIX = "Model_Matrix";
    public static final String VIEW_MATRIX = "View_Matrix";
    public static final String PROJECTION_MATRIX = "Projection_Matrix";
    public static final String MODEL_VIEW_MATRIX = "Model_View_Matrix";
    public static final String NORMAL_MATRIX = "Normal_Matrix";
    public static final String MODEL_VIEW_PROJECTION_MATRIX = "Model_View_Projection_Matrix";
    public static final String UNIFORM_MATERIAL_AMBIENT_COLOR = "Ambient_Color";
    public static final String UNIFORM_MATERIAL_AMBIENT_INTENSITY = "Ambient_Intensity";
    public static final String UNIFORM_MATERIAL_DIFFUSE_COLOR = "Diffuse_Color";
    public static final String UNIFORM_MATERIAL_DIFFUSE_INTENSITY = "Diffuse_Intensity";
    public static final String UNIFORM_MATERIAL_SPECULAR_COLOR = "Specular_Color";
    public static final String UNIFORM_MATERIAL_SPECULAR_INTENSITY = "Specular_Intensity";
    public static final String UNIFORM_BONE_MATRIX = "Bone_Matrix";
    public static final String UNIFORM_TEXTURE = "Texture_Sampler";

    private String mUniformType;
    private String mName;
    private MrDataType mDataType;
    private int mCount;
    private int mId;

    public MrUniform(String uniformType, String name, MrDataType dataType, int count) {
        this.mName = name;
        this.mUniformType = uniformType;
        this.mDataType = dataType;
        this.mCount = count;
    }

    public String getName() {
        return mName;
    }

    public String getUniformType() {
        return mUniformType;
    }

    public MrDataType getDataType() {
        return mDataType;
    }

    public int getCount() {
        return mCount;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    @Override
    public String toString() {
        return "MrUniform{" +
                "mUniformType='" + mUniformType + '\'' +
                ", mName='" + mName + '\'' +
                ", mDataType=" + mDataType +
                '}';
    }
}

