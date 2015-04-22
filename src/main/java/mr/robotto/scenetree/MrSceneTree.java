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
import mr.robotto.core.MrObject;

/**
 * Created by Aarón on 31/12/2014.
 */
//TODO: Check all names, it should be a MrTreeMap, and others should be MrHashMap and MrSortedMap
public class MrSceneTree extends MrTreeMap<String, MrObject> {

    private HashMap<MrSceneObjectType, List<MrObject>> mTags;

    public MrSceneTree() {
        super(createMapFunction());
        init();
    }

    public MrSceneTree(MrObject root) {
        super(root, createMapFunction());
        init();
    }

    private static MrMapFunction<String, MrObject> createMapFunction() {
        return new MrMapFunction<String, MrObject>() {
            @Override
            public String getKeyOf(MrObject mrObject) {
                return mrObject.getName();
            }
        };
    }

    private void init() {
        mTags = new HashMap<MrSceneObjectType, List<MrObject>>();
        for (MrSceneObjectType type : MrSceneObjectType.values()) {
            mTags.put(type, new ArrayList<MrObject>());
        }
    }

    //TODO: Make tests of mTags behaviour, cuando agregas y sustituyes, se elimina de tags?
    //TODO: Hay que cuidar el setTree, en más métodos hará falta no?
    private void addByTag(MrObject object) {
        MrSceneObjectType type = object.getSceneObjectType();
        mTags.get(type).add(object);
        object.setTree(this);
    }

    private void removeByTag(MrObject object) {
        MrSceneObjectType type = object.getSceneObjectType();
        mTags.get(type).remove(object);
        object.setTree(null);
    }

    @Override
    public boolean addChildByKey(String parentKey, MrObject data) {
        addByTag(data);
        return super.addChildByKey(parentKey, data);
    }

    @Override
    public boolean addChild(MrObject parent, MrObject data) {
        addByTag(data);
        return super.addChild(parent, data);
    }

    @Override
    public boolean removeByKey(String parentKey) {
        removeByTag(findByKey(parentKey));
        return super.removeByKey(parentKey);
    }

    @Override
    public boolean remove(MrObject data) {
        removeByTag(data);
        return super.remove(data);
    }

    public List<MrObject> getByType(MrSceneObjectType type) {
        return mTags.get(type);
    }
}
