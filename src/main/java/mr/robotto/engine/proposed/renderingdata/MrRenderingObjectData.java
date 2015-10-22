package mr.robotto.engine.proposed.renderingdata;

import java.util.HashMap;
import java.util.Map;

import mr.robotto.engine.components.MrShaderProgram;
import mr.robotto.engine.components.data.uniformkey.MrUniformKey;

/**
 * Created by aaron on 04/10/2015.
 */
public class MrRenderingObjectData {
    protected MrShaderProgram mShaderProgram;
    protected Map<String, MrUniformKey> mUniformKeys;

    public MrRenderingObjectData() {
        mUniformKeys = new HashMap<>();
    }

    public MrShaderProgram getShaderProgram() {
        return mShaderProgram;
    }

    public void setShaderProgram(MrShaderProgram shaderProgram) {
        mShaderProgram = shaderProgram;
    }

    public Map<String, MrUniformKey> getUniformKeys() {
        return mUniformKeys;
    }

    public void setUniformKeys(Map<String, MrUniformKey> uniformKeys) {
        mUniformKeys = uniformKeys;
    }

    public void updateUniformKeys(Map<String, MrUniformKey> uniformKeys) {
        mUniformKeys.putAll(uniformKeys);
    }

    public void updateUniformKeys(String key, MrUniformKey value) {
        mUniformKeys.put(key, value);
    }
}
