package mr.robotto.engine.loader.file;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.engine.collections.MrMapFunction;
import mr.robotto.engine.collections.MrTreeMap;
import mr.robotto.engine.loader.base.MrJsonBaseLoader;

/**
 * Created by aaron on 03/11/2015.
 */
class MrHierarchyLoader extends MrJsonBaseLoader<MrTreeMap<String, String>> {

    public MrHierarchyLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrTreeMap<String, String> parse() throws JSONException {
        return loadHierarchy();
    }

    private void getNodes(MrTreeMap<String, String> tree, String parentKey, JSONObject node) throws JSONException {
        JSONArray children = node.getJSONArray("Children");
        for (int i = 0; i < children.length(); i++) {
            JSONObject n = children.getJSONObject(i);
            String key = n.getString("Name");
            tree.addChildByKey(parentKey, key);
            getNodes(tree, key, n);
        }
    }

    private MrTreeMap<String, String> loadHierarchy() throws JSONException {
        String rootKey = mRoot.getString("Name");
        MrTreeMap<String, String> tree = new MrTreeMap<String, String>(rootKey, new MrMapFunction<String, String>() {
            @Override
            public String getKeyOf(String s) {
                return s;
            }
        });
        getNodes(tree, rootKey, mRoot);
        return tree;
    }
}
