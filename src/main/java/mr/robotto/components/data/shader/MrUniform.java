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

    /*
    MATRIX_MODEL,
    MATRIX_VIEW,
    MATRIX_PROJECTION,
    MATRIX_MODEL_VIEW,
    MATRIX_VIEW_PROJECTION,
    MATRIX_MODEL_VIEW_PROJECTION,
    MATRIX_TRANSP_MODEL_VIEW,
    MATRIX_INVERSE_TRANSP_MODEL_VIEW,
    MATRIX_TEXTURE_0,
    MATRIX_TEXTURE_1,
    MATRIX_TEXTURE_2,
    MATRIX_TEXTURE_3
     */

    public static final String MODEL_MATRIX = "Matrix_Model";
    public static final String VIEW_MATRIX = "Matrix_View";
    public static final String PROJECTION_MATRIX = "Matrix_Projection";
    public static final String MODEL_VIEW_PROJECTION_MATRIX = "Matrix_Model_View_Projection";
    public static final String UNIFORM_MATERIAL_AMBIENT_COLOR = "Ambient_Color";
    public static final String UNIFORM_MATERIAL_AMBIENT_INTENSITY = "Ambient_Intensity";
    public static final String UNIFORM_MATERIAL_DIFFUSE_COLOR = "Diffuse_Color";
    public static final String UNIFORM_MATERIAL_DIFFUSE_INTENSITY = "Diffuse_Intensity";
    public static final String UNIFORM_MATERIAL_SPECULAR_COLOR = "Specular_Color";
    public static final String UNIFORM_MATERIAL_SPECULAR_INTENSITY = "Specular_Intensity";
    public static final String UNIFORM_BONE_MATRIX = "Bone_Matrix";

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
}

