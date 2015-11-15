/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.loader.components.shader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.engine.components.shader.MrAttribute;
import mr.robotto.engine.components.shader.MrShader;
import mr.robotto.engine.components.shader.MrShaderProgram;
import mr.robotto.engine.components.shader.MrUniform;
import mr.robotto.engine.loader.base.MrJsonBaseLoader;

public class MrShaderProgramLoader extends MrJsonBaseLoader<MrShaderProgram> {

    public MrShaderProgramLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrShaderProgram parse() throws JSONException {
        String name = getShaderProgramName();
        MrShaderProgram program = getResources().getProgram(name);
        if (program != null) {
            return program;
        }
        program = new MrShaderProgram(name, getVertexShader(), getFragmentShader());
        loadAttributes(program);
        loadUniforms(program);
        getResources().addShaderProgram(program);
        return program;
    }

    private String getShaderProgramName() throws JSONException {
        String name = mRoot.getString("Name");
        return name;
    }

    private MrShader getVertexShader() throws JSONException {
        String source = mRoot.getString("VertexShaderSource");
        return MrShader.genVertexShader(source);
    }

    private MrShader getFragmentShader() throws JSONException {
        String source = mRoot.getString("FragmentShaderSource");
        return MrShader.genFragmentShader(source);
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
