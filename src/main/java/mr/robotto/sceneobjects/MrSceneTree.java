/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.sceneobjects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mr.robotto.MrRobottoEngine;
import mr.robotto.engine.core.MrSceneObjectType;
import mr.robotto.engine.core.controller.MrLightController;
import mr.robotto.engine.core.controller.MrModelController;
import mr.robotto.engine.core.controller.MrObjectController;
import mr.robotto.engine.scenetree.MrSceneTreeController;
import mr.robotto.engine.scenetree.MrSceneTreeData;
import mr.robotto.engine.scenetree.MrSceneTreeRender;

/**
 * Main class for storing hierarchically, retrieve and traversal scene objects.
 */
public class MrSceneTree implements Iterable<MrObject> {

    private MrSceneTreeController mController;
    private MrSceneTreeData mData;
    private HashMap<String, MrObject> mObjects;
    private MrRobottoEngine mRobottoEngine;

    /**
     * Creates a new Scene Tree using the given object as root
     *
     * @param root
     */
    public MrSceneTree(MrObject root) {
        mController = new MrSceneTreeController(new MrSceneTreeData(root.getController()), new MrSceneTreeRender());
        //mController.setRobottoEngine(robottoEngine);
        init();
        addObject(root);
    }

    private void init() {
        mData = mController.getSceneTreeData();
        mObjects = new HashMap<>(mData.size());
    }

    /**
     * Gets the RobottoEngine instance this SceneTree is attached to
     * @return
     */
    public MrRobottoEngine getRobottoEngine() {
        return mRobottoEngine;
    }

    /**
     * Sets the RobottoEngine attached to this SceneTree
     * @param robottoEngine
     */
    public void setRobottoEngine(MrRobottoEngine robottoEngine) {
        mRobottoEngine = robottoEngine;
        for (MrObject obj : this) {
            obj.setRobottoEngine(robottoEngine);
        }
    }

    private void addObject(MrObject object) {
        mObjects.put(object.getName(), object);
    }

    private void removeObject(MrObject object) {
        mObjects.remove(object.getName());
    }

    private List<MrObject> buildListFromControllers(List<MrObjectController> controllers) {
        ArrayList<MrObject> objects = new ArrayList<>(controllers.size());
        for (MrObjectController controller : controllers) {
            objects.add(mObjects.get(controller.getName()));
        }
        return objects;
    }

    private MrObject objectFromController(MrObjectController controller) {
        return mObjects.get(controller.getName());
    }

    private Iterator<MrObject> buildDelegateObjectsIterator(Iterator<MrObjectController> controllerIterator) {
        return new DelegateControllerIterator(controllerIterator, mObjects);
    }

    private Iterator<Map.Entry<String,MrObject>> buildDelegateParentObjectsIterator(Iterator<Map.Entry<String,MrObjectController>> controllerIterator) {
        return new DelegateParentValueIterator(controllerIterator, mObjects);
    }

    public MrSceneTreeController getController() {
        return mController;
    }

    //TODO: Al agregar o eliminar jerarquías estas no son agregadas o borradas
    public boolean addChildByKey(String parentKey, MrObject object) {
        addObject(object);
        return mData.addChildByKey(parentKey, object.getController());
    }

    
    public boolean addChild(MrObject parent, MrObject object) {
        addObject(object);
        return mData.addChild(parent.getController(), object.getController());
    }

    
    public boolean removeByKey(String parentKey) {
        return mData.removeByKey(parentKey);
    }

    
    public boolean remove(MrObject object) {
        removeObject(object);
        return mData.remove(object.getController());
    }

    public List<MrObject> getByType(MrSceneObjectType type) {
        return buildListFromControllers(mData.getByType(type));
    }

    public MrObject getRoot() {
        return objectFromController(mData.getRoot());
    }

    public MrObject findByKey(String key) {
        return objectFromController(mData.findByKey(key));
    }

    public boolean containsKey(String key) {
        return mData.containsKey(key);
    }

    public boolean contains(MrObject object) {
        return containsKey(object.getName());
    }

    public void clear() {
        mObjects.clear();
        mData.clear();
    }

    public Collection<String> keys() {
        return mData.keys();
    }

    public int size() {
        return mData.size();
    }

    public List<MrObject> getChildrenOfByKey(String key) {
        return buildListFromControllers(mData.getChildrenOfByKey(key));
    }

    public MrObject getParentOf(MrObject object) {
        return getParentOfByKey(object.getName());
    }

    public MrObject getParentOfByKey(String key) {
        return objectFromController(mData.getParentOfByKey(key));
    }

    public List<MrObject> getChildrenOf(MrObject object) {
        return getChildrenOfByKey(object.getName());
    }

    //TODO:
    //public MrTreeMap<String, MrObject> getSubTreeByKey(String key) {
    //    return mData.getSubTreeByKey(key);
    //}

    //TODO:
    //public MrTreeMap<String, MrObject> getSubTree(MrObject object) {
    //    return mData.getSubTree(object);
    //}

    public Iterator<MrObject> parentTraversalByKey(String key) {
        return buildDelegateObjectsIterator(mData.parentTraversalByKey(key));
    }

    public Iterator<MrObject> breadthTraversalByKey(String key) {
        return buildDelegateObjectsIterator(mData.breadthTraversalByKey(key));
    }

    public Iterator<MrObject> depthTraversalByKey(String key) {
        return buildDelegateObjectsIterator(mData.depthTraversalByKey(key));
    }

    public Iterator<Map.Entry<String, MrObject>> parentKeyChildValueTraversalByKey(String key) {
        return buildDelegateParentObjectsIterator(mData.parentKeyChildValueTraversalByKey(key));
    }

    public Iterator<MrObject> parentTraversal(MrObject object) {
        return parentTraversalByKey(object.getName());
    }

    public Iterator<MrObject> breadthTraversal(MrObject object) {
        return breadthTraversalByKey(object.getName());
    }

    public Iterator<MrObject> depthTraversal(MrObject object) {
        return depthTraversalByKey(object.getName());
    }

    public Iterator<Map.Entry<String, MrObject>> parentKeyChildValueTraversal(MrObject object) {
        return parentKeyChildValueTraversalByKey(object.getName());
    }

    public Iterator<MrObject> breadthTraversal() {
        return buildDelegateObjectsIterator(mData.breadthTraversal());
    }

    public Iterator<MrObject> depthTraversal() {
        return buildDelegateObjectsIterator(mData.depthTraversal());
    }

    public Iterator<Map.Entry<String, MrObject>> parentKeyChildValueTraversal() {
        return buildDelegateParentObjectsIterator(mData.parentKeyChildValueTraversal());
    }

    public Iterator<MrObject> iterator() {
        return buildDelegateObjectsIterator(mData.iterator());
    }

    //TODO: Optimize these methods
    public List<MrLight> getLights() {
        ArrayList<MrLight> lights = new ArrayList<>();
        for (MrLightController l : mData.getLights()) {
            lights.add((MrLight) l.getAttachedObject());
        }
        return lights;
    }

    public List<MrModel> getModels() {
        ArrayList<MrModel> models = new ArrayList<>();
        for (MrModelController l : mData.getModels()) {
            models.add((MrModel) l.getAttachedObject());
        }
        return models;
    }

    public MrScene getScene() {
        return (MrScene) mData.getScene().getAttachedObject();
    }

    public MrCamera getActiveCamera() {
        return (MrCamera) mData.getActiveCamera().getAttachedObject();
    }

    public void setActiveCamera(MrCamera camera) {
        mData.setActiveCamera(camera.getController());
    }

    //TODO: Revisar los iteradores cuando se eliminan elementos en la iteracion
    private static class DelegateControllerIterator implements Iterator<MrObject> {

        private Iterator<MrObjectController> mIt;
        private HashMap<String, MrObject> mObjects;

        public DelegateControllerIterator(Iterator<MrObjectController> it, HashMap<String, MrObject> objects) {
            mIt = it;
            mObjects = objects;
        }

        @Override
        public boolean hasNext() {
            return mIt.hasNext();
        }

        @Override
        public MrObject next() {
            MrObjectController ctr = mIt.next();
            return mObjects.get(ctr.getName());
        }

        @Override
        public void remove() {

        }
    }

    private static class DelegateParentValueIterator implements Iterator<Map.Entry<String, MrObject>> {

        private Iterator<Map.Entry<String, MrObjectController>> mIt;
        private HashMap<String, MrObject> mObjects;

        public DelegateParentValueIterator(Iterator<Map.Entry<String, MrObjectController>> controllersIterators, HashMap<String, MrObject> objectsMap) {
            mIt = controllersIterators;
            mObjects = objectsMap;
        }

        @Override
        public boolean hasNext() {
            return mIt.hasNext();
        }

        @Override
        public Map.Entry<String, MrObject> next() {
            Map.Entry<String, MrObjectController> entry = mIt.next();
            String key = entry.getKey();
            DelegateMapEntry ret = new DelegateMapEntry(key, mObjects.get(key));
            return ret;
        }

        @Override
        public void remove() {

        }
    }

    private static class DelegateMapEntry implements Map.Entry<String, MrObject> {

        private String mKey;
        private MrObject mValue;

        public DelegateMapEntry(String key, MrObject value) {
            mKey = key;
            mValue = value;
        }

        @Override
        public String getKey() {
            return mKey;
        }

        @Override
        public MrObject getValue() {
            return mValue;
        }

        @Override
        public MrObject setValue(MrObject object) {
            return null;
        }
    }
}
