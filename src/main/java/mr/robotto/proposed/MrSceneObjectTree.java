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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import mr.robotto.collections.MrMapTree;
import mr.robotto.collections.core.MrMapFunction;
import mr.robotto.core.controller.MrObject;
import mr.robotto.core.data.types.MrSceneObjectType;

/**
 * Created by Aarón on 31/12/2014.
 */
public class MrSceneObjectTree extends MrMapTree<String, MrObject> {

    private HashMap<MrSceneObjectType, List<MrObject>> mTags;

    public MrSceneObjectTree(MrObject root) {
        super(root, createMapFunction());
        init();
    }

    public MrSceneObjectTree(String rootKey, MrObject root) {
        super(rootKey, root, createMapFunction());
        init();
    }

    private static MrMapFunction<String, MrObject> createMapFunction() {
        return new MrMapFunction<String, MrObject>() {
            @Override
            public String getIdOf(MrObject mrObject) {
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

    private void addByTag(MrObject object) {
        MrSceneObjectType type = object.getSceneObjectType();
        mTags.get(type).add(object);
    }

    private void removeByTag(MrObject object) {
        MrSceneObjectType type = object.getSceneObjectType();
        mTags.get(type).remove(object);
    }

    @Override
    public boolean addChild(String parentKey, String childKey, MrObject data) {
        addByTag(data);
        return super.addChild(parentKey, childKey, data);
    }

    @Override
    public boolean addChild(String parentKey, MrObject data) {
        addByTag(data);
        return super.addChild(parentKey, data);
    }

    @Override
    public boolean removeChild(String key) {
        removeByTag(find(key));
        return super.removeChild(key);
    }

    public Collection<MrObject> getByType(MrSceneObjectType type) {
        return mTags.get(type);
    }
}
