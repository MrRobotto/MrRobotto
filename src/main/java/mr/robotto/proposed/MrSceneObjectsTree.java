/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mr.robotto.collections.MrMapNode;
import mr.robotto.collections.core.MrContainer;
import mr.robotto.core.controller.MrObject;
import mr.robotto.core.data.resources.commons.MrSceneObjectType;

/**
 * Created by Aarón on 26/11/2014.
 */
public class MrSceneObjectsTree implements MrTreeMap<String, MrObject> {

    private MrMapNode<String,MrObject> mRoot;
    private Map<MrSceneObjectType, List<MrObject>> mTags;

    public MrSceneObjectsTree() {
        init();
    }

    private void init() {

    }

    @Override
    public int size() {
        return mRoot.size();
    }

    @Override
    public boolean putNode(String key, MrMapNode<String,MrObject> node) {
        MrObject o = node.getData();
        MrSceneObjectType type = o.getSceneObjectType();
        if (!mTags.containsKey(type)) {
            mTags.put(type, new ArrayList<MrObject>());
        }
        List<MrObject> list = mTags.get(type);
        for (MrMapNode<String, MrObject> n : node) {
            list.add(n.getData());
        }
        return mRoot.findByKey(key).addChild(node);
    }

    @Override
    public boolean removeNode(String key) {
        if (!mRoot.containsKey(key)) {
            return false;
        }
        MrMapNode<String, MrObject> node = mRoot.findByKey(key);
        MrSceneObjectType type = node.getData().getSceneObjectType();
        List<MrObject> list = mTags.get(type);
        for (MrMapNode<String, MrObject> n : node) {
            list.remove(n.getData());
        }
        return mRoot.removeByKey(key);
    }

    @Override
    public boolean clear() {

        return false;
    }

    @Override
    public MrMapNode<String,MrObject> getRoot() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(String key) {
        return false;
    }

    @Override
    public MrMapNode<String,MrObject> findNode(String key) {
        return null;
    }

    @Override
    public MrObject findByKey(String key) {
        return null;
    }

    public MrContainer<MrObject> findByType(MrSceneObjectType type) {
        return null;
    }

    @Override
    public Iterator<MrObject> iterator() {
        return null;
    }
}
