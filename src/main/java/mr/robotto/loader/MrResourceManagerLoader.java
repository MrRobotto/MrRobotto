/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.collections.MrTreeMap;
import mr.robotto.collections.core.MrMapFunction;
import mr.robotto.managers.MrResourceManager;

public class MrResourceManagerLoader extends MrBaseLoader<MrResourceManager> {

    public MrResourceManagerLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrResourceManager parse() throws JSONException {
        MrResourceManager context = new MrResourceManager(getObjectsData(), getHierarchy());
        return context;
    }

    /**
     * Gets all objects-data stored in the JSONObject
     *
     * @return
     * @throws JSONException
     */
    private MrObjectMap getObjectsData() throws JSONException {
        MrObjectMap objectDataList = new MrObjectMap();
        JSONArray jsonObjects = mRoot.getJSONArray("SceneObjects");
        for (int i = 0; i < jsonObjects.length(); i++) {
            MrObjectLoader objectLoader = new MrObjectLoader(jsonObjects.getJSONObject(i));
            objectDataList.add(objectLoader.parse());
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

    private MrTreeMap<String, String> getHierarchy() throws JSONException {
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
}
