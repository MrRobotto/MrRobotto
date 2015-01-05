/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mr.robotto.collections.MrMapTree;
import mr.robotto.collections.core.MrMapFunction;
import mr.robotto.core.controller.MrObject;
import mr.robotto.core.data.types.MrSceneObjectType;

/**
 * Created by Aarón on 31/12/2014.
 */
public class MrSceneObjectsTree extends MrMapTree<String, MrObject> {

    private HashMap<MrSceneObjectType, List<MrObject>> mTags;
    private MrRenderingContext mRenderingContext;

    public MrSceneObjectsTree(MrObject root) {
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
        mRenderingContext = new MrRenderingContext();
    }

    private void addByTag(MrObject object) {
        MrSceneObjectType type = object.getSceneObjectType();
        mTags.get(type).add(object);
    }

    private void removeByTag(MrObject object) {
        MrSceneObjectType type = object.getSceneObjectType();
        mTags.get(type).remove(object);
    }

    public MrRenderingContext getRenderingConte() {
        return mRenderingContext;
    }

    @Override
    public boolean addChildByKey(String parentKey, MrObject data) {
        addByTag(data);
        return super.addChildByKey(parentKey, data);
    }

    @Override
    public boolean addChildByValue(MrObject parent, MrObject data) {
        addByTag(data);
        return super.addChildByValue(parent, data);
    }

    @Override
    public boolean removeChildByKey(String key) {
        removeByTag(findByKey(key));
        return super.removeChildByKey(key);
    }

    @Override
    public boolean removeChildByValue(MrObject data) {
        removeByTag(data);
        return super.removeChildByValue(data);
    }

    public List<MrObject> getByType(MrSceneObjectType type) {
        return mTags.get(type);
    }
}
