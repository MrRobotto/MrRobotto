package mr.robotto.engine.core.data;

import java.util.HashMap;
import java.util.Map;

import mr.robotto.engine.components.MrShaderProgram;
import mr.robotto.engine.components.data.uniformkey.MrUniformKey;
import mr.robotto.engine.core.MrSceneObjectType;
import mr.robotto.engine.linearalgebra.MrTransform;

public class MrObjectDataBuilder {
    private String mName;
    private MrSceneObjectType mSceneObjType;
    private MrTransform mTransform = new MrTransform();
    private MrShaderProgram mProgram = null;
    private Map<String, MrUniformKey> mUniformKeys = new HashMap<String, MrUniformKey>();

    public MrObjectDataBuilder setName(String name) {
        mName = name;
        return this;
    }

    public MrObjectDataBuilder setSceneObjType(MrSceneObjectType sceneObjType) {
        mSceneObjType = sceneObjType;
        return this;
    }

    public MrObjectDataBuilder setTransform(MrTransform transform) {
        mTransform = transform;
        return this;
    }

    public MrObjectDataBuilder setProgram(MrShaderProgram program) {
        mProgram = program;
        return this;
    }

    public MrObjectDataBuilder setUniformKeys(Map<String, MrUniformKey> uniformKeys) {
        mUniformKeys = uniformKeys;
        return this;
    }

    public MrObjectData createMrObjectData() {
        /*switch (mSceneObjType) {
            case SCENE:
                return
        }
        return new MrObjectData(mName, mSceneObjType, mTransform, mProgram, mUniformKeys);*/
        return null;
    }
}