/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.uniformgenerator;

import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.scenetree.MrObjectsDataTree;

/**
 * Created by Aarón on 05/01/2015.
 */
public abstract class MrUniformGenerator {

    public static final String MODEL_MATRIX = "Generator_Matrix_Model";
    public static final String VIEW_MATRIX = "Generator_Matrix_View";
    public static final String PROJECTION_MATRIX = "Generator_Matrix_Projection";
    public static final String MODEL_VIEW_PROJECTION_MATRIX = "Generator_Matrix_Model_View_Projection";
    public static final String UNIFORM_MATERIAL_AMBIENT_COLOR = "Generator_Ambient_Color";
    public static final String UNIFORM_MATERIAL_AMBIENT_INTENSITY = "Generator_Ambient_Intensity";
    public static final String UNIFORM_MATERIAL_DIFFUSE_COLOR = "Generator_Diffuse_Color";
    public static final String UNIFORM_MATERIAL_DIFFUSE_INTENSITY = "Generator_Diffuse_Intensity";
    public static final String UNIFORM_MATERIAL_SPECULAR_COLOR = "Generator_Specular_Color";
    public static final String UNIFORM_MATERIAL_SPECULAR_INTENSITY = "Generator_Specular_Intensity";
    public static final String UNIFORM_BONE_MATRIX = "Generator_Bone_Matrix";
    public static final String UNIFORM_TEXTURE = "Generator_Texture_Sampler";

    private String mName;

    public MrUniformGenerator(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public abstract MrLinearAlgebraObject generateUniform(final MrObjectsDataTree tree, final MrUniformKeyMap uniforms, final MrObjectData object);
}
