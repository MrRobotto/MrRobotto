/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import mr.robotto.collections.MrMapNode;
import mr.robotto.collections.core.MrMapFunction;
import mr.robotto.core.controller.MrObject;
import mr.robotto.core.data.commons.MrSceneObjectType;

/**
 * Created by Aarón on 28/11/2014.
 */
public class MrSceneObjectsTree extends MrMapNode<String, MrObject> {

    private HashMap<MrSceneObjectType, List<MrObject>> mTags;

    public MrSceneObjectsTree(MrMapNode<String, MrObject> parent, MrObject data) {
        super(parent, data, getMapFunction());
        init();
    }

    public MrSceneObjectsTree(MrObject data) {
        super(data, getMapFunction());
        init();
    }

    private static MrMapFunction<String, MrObject> getMapFunction() {
        return new MrMapFunction<String, MrObject>() {
            @Override
            public String getIdOf(MrObject mrObject) {
                return mrObject.getName();
            }
        };
    }

    private void init() {
        mTags = new HashMap<MrSceneObjectType, List<MrObject>>();
    }

    private void addByTag(MrMapNode<String, MrObject> node) {
        MrObject o = node.getData();
        MrSceneObjectType type = o.getSceneObjectType();
        mTags.get(type).add(o);
    }

    @Override
    public boolean addChild(MrMapNode<String, MrObject> node) {
        for (MrMapNode<String, MrObject> n : node) {
            addByTag(n);
        }
        return super.addChild(node);
    }

    private void removeByTag(MrMapNode<String, MrObject> node) {
        MrObject o = node.getData();
        MrSceneObjectType type = o.getSceneObjectType();
        mTags.get(type).remove(o);
    }

    @Override
    public boolean removeChild(MrMapNode<String, MrObject> node) {
        for (MrMapNode<String, MrObject> n : node) {
            removeByTag(n);
        }
        return super.removeChild(node);
    }

    public Collection<MrObject> getByType(MrSceneObjectType type) {
        return mTags.get(type);
    }
}
