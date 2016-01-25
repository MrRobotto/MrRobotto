/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mr.robotto.engine.components.shader.MrShaderProgram;
import mr.robotto.engine.components.uniformkey.MrUniformGenerator;
import mr.robotto.engine.components.uniformkey.MrUniformKeySchema;
import mr.robotto.engine.core.MrSceneObjectType;
import mr.robotto.engine.linearalgebra.MrTransform;

/**
 * Created by aaron on 14/04/2015.
 */
public abstract class MrObjectData {
    protected String mName;
    protected MrSceneObjectType mSceneObjType;
    protected MrTransform mTransform;
    protected MrShaderProgram mShaderProgram;
    protected Map<String, MrUniformGenerator> mUniformGenerators = new HashMap<>();
    protected Map<String, MrUniformKeySchema> mUniformKeySchemas = new HashMap<>();

    public MrObjectData(String name, MrSceneObjectType sceneObjType) {
        mName = name;
        mSceneObjType = sceneObjType;
    }

    public String getName() {
        return mName;
    }

    public MrSceneObjectType getSceneObjectType() {
        return mSceneObjType;
    }

    public MrTransform getTransform() {
        return mTransform;
    }

    public void setTransform(MrTransform transform) {
        this.mTransform = transform;
    }

    public MrShaderProgram getShaderProgram() {
        return mShaderProgram;
    }

    public void setShaderProgram(MrShaderProgram shaderProgram) {
        mShaderProgram = shaderProgram;
    }

    public Map<String, MrUniformKeySchema> getUniformKeySchemas() {
        return mUniformKeySchemas;
    }

    public void addUniformKeySchema(MrUniformKeySchema schema) {
        mUniformKeySchemas.put(schema.getUniform(), schema);
    }

    //TODO: Add delete methods for schemas and uniform keys
    public void addUniformKeySchema(Collection<MrUniformKeySchema> schemas) {
        for (MrUniformKeySchema schema : schemas) {
            addUniformKeySchema(schema);
        }
    }

    public Map<String, MrUniformGenerator> getUniformGenerators() {
        return mUniformGenerators;
    }

    public void putUniformGenerators(String uniformGeneratorName, MrUniformGenerator generator) {
        mUniformGenerators.put(uniformGeneratorName, generator);
    }
}
