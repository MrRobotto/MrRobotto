/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.uniformkey;

import java.util.Map;

import mr.robotto.engine.components.shader.MrShaderProgram;
import mr.robotto.engine.core.data.MrObjectData;
import mr.robotto.engine.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.engine.scenetree.MrObjectsDataTree;

/**
 * Class to intermediate between unifom values stored as {@link mr.robotto.engine.linearalgebra.MrLinearAlgebraObject} and
 * {@link mr.robotto.engine.components.shader.MrUniform} elements of {@link MrShaderProgram}
 */
public class MrUniformKey implements Comparable<MrUniformKey> {

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
    public static final String GENERATOR_LIGHT_POSITION = "Generator_Light_Position";
    public static final String GENERATOR_LIGHT_COLOR = "Generator_Light_Color";

    private String mGeneratorName;
    private String mUniform;
    private int mLevel;
    private int mCount;
    private MrLinearAlgebraObject mValue;
    private MrUniformGenerator mGenerator;

    public MrUniformKey(String uniform, String generatorName, int count, int level) {
        mGeneratorName = generatorName;
        mUniform = uniform;
        mCount = count;
        mLevel = level;
        mValue = null;
    }

    public MrUniformKey(MrUniformKeySchema schema) {
        mGeneratorName = schema.getGeneratorName();
        mUniform = schema.getUniform();
        mCount = schema.getCount();
        mLevel = schema.getLevel();
    }

    public MrUniformKey(MrUniformKeySchema schema, MrUniformGenerator generator) {
        mGeneratorName = schema.getGeneratorName();
        mUniform = schema.getUniform();
        mCount = schema.getCount();
        mLevel = schema.getLevel();
        mGenerator = generator;
    }

    /**
     * Gets the value stored in this uniform key
     *
     * @return
     */
    public MrLinearAlgebraObject getValue() {
        return mValue;
    }

    /**
     * Sets a new value for this uniform key
     * @param value
     */
    public void setValue(MrLinearAlgebraObject value) {
        mValue = value;
    }

    /**
     * Gets the generator name of this uniform key
     * @return
     */
    public String getGeneratorName() {
        return mGeneratorName;
    }

    /**
     * Gets the generator used in this uniform key
     *
     * @return
     */
    public MrUniformGenerator getGenerator() {
        return mGenerator;
    }

    /**
     * Sets the generator for this uniform key
     *
     * @param generator
     */
    public void setGenerator(MrUniformGenerator generator) {
        mGenerator = generator;
    }

    /**
     * Generates the value of this uniform key
     */
    public void generate(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData objectData) {
        if (mGenerator != null) {
            mValue = mGenerator.generateUniform(tree, uniforms, objectData);
        }
    }

    /**
     * Gets the uniform name which this uniform key generates
     * @return
     */
    public String getUniform() {
        return mUniform;
    }

    /**
     * Gets how many values will be stored in this uniform key
     * @return
     */
    public int getCount() {
        return mCount;
    }

    /**
     * The level of a uniform key represents the order of dependencies of this uniform key
     * 0 means that this uniform key only depends on one object
     * 1 means that it depends on some uniform keys generated by other objects
     * 2 means that it depends on uniform keys generated on lower levels
     * 3 and go on: the same as 2
     * @return
     */
    public int getLevel() {
        return mLevel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MrUniformKey)) return false;

        MrUniformKey that = (MrUniformKey) o;

        if (mLevel != that.mLevel) return false;
        if (!mGeneratorName.equals(that.mGeneratorName)) return false;
        return mUniform.equals(that.mUniform);
    }

    @Override
    public int hashCode() {
        int result = mGeneratorName.hashCode();
        result = 31 * result + mUniform.hashCode();
        result = 31 * result + mLevel;
        return result;
    }

    @Override
    public int compareTo(MrUniformKey another) {
        if (mLevel < another.mLevel) return -1;
        if (mLevel > another.mLevel) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "MrUniformKey{" +
                "mUniform='" + mUniform + '\'' +
                ", mGeneratorName='" + mGeneratorName + '\'' +
                '}';
    }

}
