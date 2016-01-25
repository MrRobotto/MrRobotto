/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.scenetree;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import mr.robotto.engine.core.controller.MrObjectController;
import mr.robotto.engine.core.data.MrObjectData;

/**
 * Created by aaron on 13/05/2015.
 */
public class MrObjectsDataTree {

    private MrSceneTreeData mData;

    public MrObjectsDataTree(MrSceneTreeData data) {
        mData = data;
    }

    public boolean containsKey(String key) {
        return mData.containsKey(key);
    }

    public Iterator<MrObjectData> breadthTraversalByKey(String key) {
        return new DelegateIterator(mData.breadthTraversalByKey(key));
    }

    public Iterator<MrObjectData> iterator() {
        return new DelegateIterator(mData.iterator());
    }

    public MrObjectData getParentOfByKey(String key) {
        return mData.getParentOfByKey(key).getData();
    }

    public MrObjectData getParentOf(MrObjectData data) {
        return mData.getParentOfByKey(data.getName()).getData();
    }

    public Iterator<MrObjectData> depthTraversal() {
        return new DelegateIterator(mData.depthTraversal());
    }

    public boolean contains(MrObjectData data) {
        return containsKey(data.getName());
    }

    public MrObjectData getRoot() {
        return mData.getRoot().getData();
    }

    public int size() {
        return mData.size();
    }

    public Iterator<MrObjectData> depthTraversalByKey(String key) {
        return new DelegateIterator(mData.depthTraversalByKey(key));
    }

    public Iterator<MrObjectData> parentTraversalByKey(String key) {
        return new DelegateIterator(mData.parentTraversalByKey(key));
    }

    public Iterator<Map.Entry<String, MrObjectData>> parentKeyChildValueTraversal(MrObjectData data) {
        return parentKeyChildValueTraversalByKey(data.getName());
    }

    public Collection<String> keys() {
        return mData.keys();
    }

    public Iterator<Map.Entry<String, MrObjectController>> parentKeyChildValueTraversal() {
        return mData.parentKeyChildValueTraversal();
    }

    public Iterator<MrObjectData> depthTraversal(MrObjectData data) {
        return depthTraversalByKey(data.getName());
    }

    public Iterator<MrObjectData> parentTraversal(MrObjectData data) {
        return parentTraversalByKey(data.getName());
    }

    public Iterator<Map.Entry<String, MrObjectData>> parentKeyChildValueTraversalByKey(String key) {
        return new ParentKeyDelegateIterator(mData.parentKeyChildValueTraversalByKey(key));
    }

    public Iterator<MrObjectData> breadthTraversal(MrObjectData data) {
        return breadthTraversalByKey(data.getName());
    }

    public Iterator<MrObjectData> breadthTraversal() {
        return new DelegateIterator(mData.breadthTraversal());
    }

    public MrObjectData findByKey(String key) {
        return mData.findByKey(key).getData();
    }

    //TODO:
    //public List<MrObjectController> getByType(MrSceneObjectType type) {
    //    return mData.getByType(type);
    //}

    //TODO:
    //public List<MrObjectController> getChildrenOfByKey(String key) {
    //    return mData.getChildrenOfByKey(key);
    //}

    //TODO:
    //public MrTreeMap<String, MrObjectData> getSubTree(MrObjectData data) {
    //    return mData.getSubTreeByKey(data.loadName());
    //}

    //TODO:
    //public MrTreeMap<String, MrObjectController> getSubTreeByKey(String key) {
    //    return mData.getSubTreeByKey(key);
    //}

    //TODO:
    //public List<MrObjectData> getChildrenOf(MrObjectController data) {
    //    return mData.getChildrenOf(data);
    //}

    private static class DelegateIterator implements Iterator<MrObjectData> {

        private Iterator<MrObjectController> mIt;

        public  DelegateIterator(Iterator<MrObjectController> it) {
            mIt = it;
        }

        @Override
        public boolean hasNext() {
            return mIt.hasNext();
        }

        @Override
        public MrObjectData next() {
            return mIt.next().getData();
        }

        @Override
        public void remove() {

        }
    }

    private static class ParentKeyDelegateIterator implements Iterator<Map.Entry<String, MrObjectData>> {

        private Iterator<Map.Entry<String, MrObjectController>> mIt;

        public ParentKeyDelegateIterator(Iterator<Map.Entry<String, MrObjectController>> it) {
            mIt = it;
        }

        @Override
        public boolean hasNext() {
            return mIt.hasNext();
        }

        @Override
        public Map.Entry<String, MrObjectData> next() {
            Map.Entry<String, MrObjectController> entry = mIt.next();
            Map.Entry<String, MrObjectData> ret = new StringObjectDataEntry(entry);
            return ret;
        }

        @Override
        public void remove() {

        }
    }

    private static class StringObjectDataEntry implements Map.Entry<String, MrObjectData> {

        private Map.Entry<String, MrObjectController> mEntry;
        public StringObjectDataEntry(Map.Entry<String, MrObjectController> entry) {
            mEntry = entry;
        }

        @Override
        public String getKey() {
            return mEntry.getKey();
        }

        @Override
        public MrObjectData getValue() {
            return mEntry.getValue().getData();
        }

        @Override
        public MrObjectData setValue(MrObjectData object) {
            return null;
        }
    }
}
