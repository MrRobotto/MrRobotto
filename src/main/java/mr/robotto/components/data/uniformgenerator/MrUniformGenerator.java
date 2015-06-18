/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.uniformgenerator;

import java.util.Map;

import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.scenetree.MrObjectsDataTree;

/**
 * Created by Aarón on 05/01/2015.
 */
public abstract class MrUniformGenerator {

    public static final String GENERATOR_MODEL_MATRIX = "Generator_Model_Matrix";
    public static final String GENERATOR_VIEW_MATRIX = "Generator_View_Matrix";
    public static final String GENERATOR_PROJECTION_MATRIX = "Generator_Projection_Matrix";
    public static final String GENERATOR_MODEL_VIEW_MATRIX = "Generator_Model_View_Matrix";
    public static final String GENERATOR_NORMAL_MATRIX = "Generator_Normal_Matrix";
    public static final String GENERATOR_MODEL_VIEW_PROJECTION_MATRIX = "Generator_Model_View_Projection_Matrix";
    public static final String GENERATOR_MATERIAL_AMBIENT_COLOR = "Generator_Material_Ambient_Color";
    public static final String GENERATOR_MATERIAL_AMBIENT_INTENSITY = "Generator_Material_Ambient_Intensity";
    public static final String GENERATOR_MATERIAL_DIFFUSE_COLOR = "Generator_Material_Diffuse_Color";
    public static final String GENERATOR_MATERIAL_DIFFUSE_INTENSITY = "Generator_Material_Diffuse_Intensity";
    public static final String GENERATOR_MATERIAL_SPECULAR_COLOR = "Generator_Material_Specular_Color";
    public static final String GENERATOR_MATERIAL_SPECULAR_INTENSITY = "Generator_Material_Specular_Intensity";
    public static final String GENERATOR_BONE_MATRIX = "Generator_Bone_Matrix";
    public static final String GENERATOR_TEXTURE_SAMPLER = "Generator_Texture_Sampler";
    public static final String UNIFORMGENERATOR_LIGHT_POSITION = "Generator_Light_Position";
    public static final String UNIFORMGENERATOR_LIGHT_COLOR = "Generator_Light_Color";


    public MrUniformGenerator() {

    }

    public abstract MrLinearAlgebraObject generateUniform(final MrObjectsDataTree tree, final Map<String, MrUniformKey> uniforms, final MrObjectData object);
}
