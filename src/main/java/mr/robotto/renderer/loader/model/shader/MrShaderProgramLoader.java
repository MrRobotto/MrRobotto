/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.loader.model.shader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.renderer.loader.MrAbstractLoader;
import mr.robotto.renderer.core.data.model.shaders.MrAttribute;
import mr.robotto.renderer.core.data.model.shaders.MrFragmentShader;
import mr.robotto.renderer.core.data.model.shaders.MrShaderProgram;
import mr.robotto.renderer.core.data.model.shaders.MrUniform;
import mr.robotto.renderer.core.data.model.shaders.MrVertexShader;

public class MrShaderProgramLoader extends MrAbstractLoader<MrShaderProgram> {

    public MrShaderProgramLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrShaderProgram parse() throws JSONException {
        MrShaderProgram program = new MrShaderProgram(getVertexShader(), getFragmentShader());
        loadAttributes(program);
        loadUniforms(program);
        return program;
    }

    private MrVertexShader getVertexShader() throws JSONException {
        String source = mRoot.getString("VertexShaderSource");
        return new MrVertexShader(source);
    }

    private MrFragmentShader getFragmentShader() throws JSONException {
        String source = mRoot.getString("FragmentShaderSource");
        return new MrFragmentShader(source);
    }

    private void loadUniforms(MrShaderProgram program) throws JSONException {
        JSONArray uniformsJsonArray = mRoot.getJSONArray("Uniforms");
        for (int i = 0; i < uniformsJsonArray.length(); i++) {
            JSONObject uniformJson = uniformsJsonArray.getJSONObject(i);
            MrUniformLoader uniformLoader = new MrUniformLoader(uniformJson);
            MrUniform uniform = uniformLoader.parse();
            program.addUniform(uniform);
        }
    }

    private void loadAttributes(MrShaderProgram program) throws JSONException {
        JSONArray attributesJsonArray = mRoot.getJSONArray("Attributes");
        for (int i = 0; i < attributesJsonArray.length(); i++) {
            JSONObject attributeJson = attributesJsonArray.getJSONObject(i);
            MrAttributeLoader attributeLoader = new MrAttributeLoader(attributeJson);
            MrAttribute attribute = attributeLoader.parse();
            program.addAttribute(attribute);
        }
    }
}
