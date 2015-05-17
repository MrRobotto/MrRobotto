/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.scenetree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mr.robotto.collections.MrTreeMap;
import mr.robotto.collections.core.MrMapFunction;
import mr.robotto.core.MrSceneObjectType;
import mr.robotto.core.controller.MrObjectController;

/**
 * Created by aaron on 12/05/2015.
 */
public class MrSceneTreeData extends MrTreeMap<String, MrObjectController> {

    private HashMap<MrSceneObjectType, List<MrObjectController>> mTags;
    private MrObjectsDataTree mObjectsDataTree;

    public MrSceneTreeData() {
        super(createMapFunction());
        init();
    }

    public MrSceneTreeData(MrObjectController root) {
        super(root, createMapFunction());
        init();
    }

    public MrObjectsDataTree getObjectsDataTree() {
        return mObjectsDataTree;
    }

    private static MrMapFunction<String, MrObjectController> createMapFunction() {
        return new MrMapFunction<String, MrObjectController>() {
            @Override
            public String getKeyOf(MrObjectController mrObject) {
                return mrObject.getName();
            }
        };
    }

    private void init() {
        mTags = new HashMap<MrSceneObjectType, List<MrObjectController>>();
        for (MrSceneObjectType type : MrSceneObjectType.values()) {
            mTags.put(type, new ArrayList<MrObjectController>());
        }
        mObjectsDataTree = new MrObjectsDataTree(this);
    }

    //TODO: Make tests of mTags behaviour, cuando agregas y sustituyes, se elimina de tags?
    //TODO: Hay que cuidar el setTree, en más métodos hará falta no?
    private void addByTag(MrObjectController object) {
        MrSceneObjectType type = object.getSceneObjectType();
        mTags.get(type).add(object);
    }

    private void removeByTag(MrObjectController object) {
        MrSceneObjectType type = object.getSceneObjectType();
        mTags.get(type).remove(object);
    }

    @Override
    public boolean addChildByKey(String parentKey, MrObjectController data) {
        addByTag(data);
        return super.addChildByKey(parentKey, data);
    }

    @Override
    public boolean addChild(MrObjectController parent, MrObjectController data) {
        addByTag(data);
        return super.addChild(parent, data);
    }

    @Override
    public boolean removeByKey(String parentKey) {
        removeByTag(findByKey(parentKey));
        return super.removeByKey(parentKey);
    }

    @Override
    public boolean remove(MrObjectController data) {
        removeByTag(data);
        return super.remove(data);
    }

    public List<MrObjectController> getByType(MrSceneObjectType type) {
        return mTags.get(type);
    }
}