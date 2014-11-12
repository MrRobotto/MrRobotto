package mr.robotto.renderer.loader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.renderer.proposed.MrContext;
import mr.robotto.renderer.proposed.MrNode;
import mr.robotto.renderer.proposed.MrObjectDataList;

public class MrContextLoader extends MrAbstractLoader<MrContext> {

    public MrContextLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrContext parse() throws JSONException {
        MrContext context = new MrContext(getObjectsData(),getHierarchy());
        return context;
    }

    /**
     * Gets all objects-data stored in the JSONObject
     * @return
     * @throws JSONException
     */
    private MrObjectDataList getObjectsData() throws JSONException {
        MrObjectDataList objectDataList = new MrObjectDataList();
        JSONArray jsonObjects = mRoot.getJSONArray("SceneObjects");
        for (int i = 0; i < jsonObjects.length(); i++) {
            MrObjectLoader objectLoader = new MrObjectLoader(jsonObjects.getJSONObject(i));
            objectDataList.add(objectLoader.parse());
        }
        return objectDataList;
    }

    /**
     * Gets all children of node recursively
     * @param jsonNode
     * @param node
     * @throws JSONException
     */
    private void getNodes(JSONObject jsonNode, MrNode<String> node) throws JSONException {
        JSONArray jsonChildren = jsonNode.getJSONArray("Children");
        for (int i = 0; i < jsonChildren.length(); i++) {
            JSONObject jsonChildNode = jsonChildren.getJSONObject(i);
            String childNodeData = jsonChildNode.getString("Name");
            MrNode<String> childNode = new MrNode<String>(node, childNodeData);
            getNodes(jsonChildNode, childNode);
        }
    }

    /**
     * Gets all the hierarchy
     * @return
     * @throws JSONException
     */
    private MrNode<String> getHierarchy() throws JSONException {
        JSONObject jsonRoot = mRoot.getJSONObject("Hierarchy");
        String rootData = jsonRoot.getString("Name");
        MrNode<String> rootNode = new MrNode<String>(null, rootData);
        getNodes(jsonRoot, rootNode);
        return rootNode;
    }
}
