/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.loader.file;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mr.robotto.engine.collections.MrMapFunction;
import mr.robotto.engine.collections.MrTreeMap;
import mr.robotto.engine.loader.core.MrJsonBaseLoader;
import mr.robotto.engine.loader.core.MrObjectLoader;
import mr.robotto.sceneobjects.MrObject;
import mr.robotto.scenetree.MrSceneTree;

public class MrRobottoJsonLoader extends MrJsonBaseLoader<MrSceneTree> {

    private MrTreeMap<String, String> mKeysTree;
    private HashMap<String, MrObject> mObjects;

    public MrRobottoJsonLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrSceneTree parse() throws JSONException {
        mKeysTree = loadHierarchy();
        mObjects = loadObjects();
        return buildSceneObjectsTree();
        //MrRobottoJson context = new MrRobottoJson(loadObjects(), loadHierarchy());
        //return context;
    }

    /**
     * Gets all objects-data stored in the JSONObject
     *
     * @return
     * @throws JSONException
     */
    private HashMap<String, MrObject> loadObjects() throws JSONException {
        HashMap<String, MrObject> objectDataList = new HashMap<>();
        JSONArray jsonObjects = mRoot.getJSONArray("SceneObjects");
        for (int i = 0; i < jsonObjects.length(); i++) {
            MrObjectLoader objectLoader = new MrObjectLoader(jsonObjects.getJSONObject(i));
            MrObject obj = objectLoader.parse();
            objectDataList.put(obj.getName(), obj);
        }
        return objectDataList;
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
        JSONObject root = mRoot.getJSONObject("Hierarchy");
        String rootKey = root.getString("Name");
        MrTreeMap<String, String> tree = new MrTreeMap<String, String>(rootKey, new MrMapFunction<String, String>() {
            @Override
            public String getKeyOf(String s) {
                return s;
            }
        });
        getNodes(tree, rootKey, root);
        return tree;
    }

    private MrSceneTree buildSceneObjectsTree() {
        MrTreeMap<String, String> keyTree = mKeysTree;
        HashMap<String, MrObject> objects = mObjects;
        MrObject rootData = objects.get(keyTree.getRoot());
        //MrObjectRender render = getRenderer(rootData);
        MrSceneTree tree = new MrSceneTree(rootData);
        Iterator<Map.Entry<String, String>> it = keyTree.parentKeyChildValueTraversal();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            //Gets the value
            MrObject objectData = objects.get(entry.getValue());
            //Gets the root key via entry.getKey
            tree.addChildByKey(entry.getKey(), objectData);
        }
        return tree;
    }
}
