package mr.robotto.renderer.loader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.renderer.proposed.MrContext;
import mr.robotto.renderer.proposed.MrObjectDataList;

public class MrContextLoader extends MrAbstractLoader<MrContext> {

    public MrContextLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrContext parse() throws JSONException {
        MrContext context = new MrContext(getObjectDatas());
        return context;
    }

    private MrObjectDataList getObjectDatas() throws JSONException {
        MrObjectDataList objectDataList = new MrObjectDataList();
        JSONArray jsonObjects = mRoot.getJSONArray("SceneObjects");
        for (int i = 0; i < jsonObjects.length(); i++) {
            MrObjectLoader objectLoader = new MrObjectLoader(jsonObjects.getJSONObject(i));
            objectDataList.add(objectLoader.parse());
        }
        return objectDataList;
    }
}
