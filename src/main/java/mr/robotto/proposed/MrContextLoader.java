/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.collections.MrListNode;
import mr.robotto.loader.MrAbstractLoader;
import mr.robotto.loader.MrObjectLoader;

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
    private MrObjectDataContainer getObjectsData() throws JSONException {
        MrObjectDataContainer objectDataList = new MrObjectDataContainer();
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
    private void getNodes(JSONObject jsonNode, MrListNode<String> node) throws JSONException {
        JSONArray jsonChildren = jsonNode.getJSONArray("Children");
        for (int i = 0; i < jsonChildren.length(); i++) {
            JSONObject jsonChildNode = jsonChildren.getJSONObject(i);
            String childNodeData = jsonChildNode.getString("Name");
            MrListNode<String> childNode = new MrListNode<String>(node, childNodeData);
            getNodes(jsonChildNode, childNode);
        }
    }

    /**
     * Gets all the hierarchy
     * @return
     * @throws JSONException
     */
    private MrListNode<String> getHierarchy() throws JSONException {
        JSONObject jsonRoot = mRoot.getJSONObject("Hierarchy");
        String rootData = jsonRoot.getString("Name");
        MrListNode<String> rootNode = new MrListNode<String>(null, rootData);
        getNodes(jsonRoot, rootNode);
        return rootNode;
    }
}
