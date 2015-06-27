/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.sceneobjects;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mr.robotto.MrRobottoEngine;
import mr.robotto.engine.components.MrShaderProgram;
import mr.robotto.engine.components.data.uniformgenerator.MrUniformGenerator;
import mr.robotto.engine.components.data.uniformkey.MrUniformKey;
import mr.robotto.engine.core.MrSceneObjectType;
import mr.robotto.engine.core.controller.MrObjectController;
import mr.robotto.engine.events.MrEventsListener;
import mr.robotto.engine.linearalgebra.MrQuaternion;
import mr.robotto.engine.linearalgebra.MrTransform;
import mr.robotto.engine.linearalgebra.MrVector3f;
import mr.robotto.scenetree.MrSceneTree;

/**
 * Base class for MrRobotto Object hierarchy.
 * This class provides access to the user to control an object.
 */
public abstract class MrObject {
    private MrRobottoEngine mRobotto;
    private MrObjectController mController;
    private MrSceneTree mTree;

    /**
     * Creates an MrObject using a controller
     * @param controller controller for this object
     */
    protected MrObject(MrObjectController controller) {
        mController = controller;
        mController.setAttachedObject(this);
        initializeUniforms(mController.getUniformGenerators());
    }

    /**
     * Gets the object controller of this object
     * @return The controller
     */
    public MrObjectController getController() {
        return mController;
    }

    /**
     * Gets the reference to the engine used.
     * @return gets the RobottoEngine instance this object is attached to
     */
    public MrRobottoEngine getRobottoEngine() {
        return mRobotto;
    }

    /**
     * Sets the reference to the engine.
     * This method should not be called directly by the user
     * @param robotto the engine which contains this object
     */
    public void setRobottoEngine(MrRobottoEngine robotto) {
        mRobotto = robotto;
    }

    /*******Controller******/

    //TODO: Change runnable to custom class
    /**
     * Queues a runnable.
     * Use when calling methods from the UI-Thread
     * @param runnable runnable to be executed
     */
    protected void queueEvent(Runnable runnable) {
        mRobotto.queueEvent(runnable);
    }

    /**
     * Call this method if you want to create a new uniform generator for this object
     * @param uniformGenerators uniform generator map to add new generators
     */
    public void initializeUniforms(Map<String, MrUniformGenerator> uniformGenerators) {
    }

    /**
     * Gets the type of this object
     * @return
     */
    public MrSceneObjectType getSceneObjectType() {
        return mController.getSceneObjectType();
    }

    /**
     * Checks if initialize has been called on this object
     * @return true if the object is initialized, false otherwise
     */
    public boolean isInitialized() {
        return mController.isInitialized();
    }

    /**
     * Gets the name of this object
     * @return
     */
    public String getName() {
        return mController.getName();
    }

    /**
     * Gets the transformation object of this object
     * @return
     */
    public MrTransform getTransform() {
        return mController.getTransform();
    }

    /**
     * Sets a new transform for this object
     * @param transform
     */
    public void setTransform(MrTransform transform) {
        mController.setTransform(transform);
    }

    /**
     * Gets the uniform generators associated to this object
     * @return a map containing generator name - generator items
     */
    public Map<String, MrUniformGenerator> getUniformGenerators() {
        return mController.getUniformGenerators();
    }

    /**
     * Gets the current shader program, if the object has any
     * @return
     */
    public MrShaderProgram getShaderProgram() {
        return mController.getShaderProgram();
    }

    /**
     * Gets the uniform keys provided by this object
     * @return a map containing uniform key type - uniform key items
     */
    public Map<String, MrUniformKey> getUniformKeys() {
        return mController.getUniformKeys();
    }

    /*****Tree*****/

    /**
     * Gets the SceneTree which this objects belongs to
     *
     * @return
     */
    public MrSceneTree getTree() {
        return mTree;
    }

    public void setTree(MrSceneTree tree) {
        mTree = tree;
    }

    public boolean addChild(MrObject data) {
        return mTree.addChild(this, data);
    }

    //TODO
    public boolean removeChild(MrObject data) {
        throw new UnsupportedOperationException();
        //return mTree.remove(data);
    }

    //TODO
    public List<MrObject> getByType(MrSceneObjectType type) {
        throw new UnsupportedOperationException();
        //return mTree.getByType(type);
    }


    public MrObject getRoot() {
        return mTree.getRoot();
    }

    //TODO
    public MrObject findChild(String key) {
        throw new UnsupportedOperationException();
        //return mTree.findByKey(key);
    }

    //TODO
    public boolean isChild(MrObject data) {
        throw new UnsupportedOperationException();
        //return mTree.contains(data);
    }

    //TODO
    public boolean isChild(String key) {
        throw new UnsupportedOperationException();
        //return mTree.containsKey(key);
    }

    public MrObject getParent() {
        return mTree.getParentOf(this);
    }

    public List<MrObject> getChildren() {
        return mTree.getChildrenOf(this);
    }

    public Iterator<MrObject> parentTraversal() {
        return mTree.parentTraversal(this);
    }

    public Iterator<MrObject> breadthTraversal() {
        return mTree.breadthTraversal(this);
    }

    public Iterator<MrObject> depthTraversal() {
        return mTree.depthTraversal(this);
    }

    public Iterator<Map.Entry<String, MrObject>> parentKeyChildValueTraversal() {
        return mTree.parentKeyChildValueTraversal(this);
    }


    /*******Events***********/

    /**
     * Gets the current event listener
     * @return
     */
    public MrEventsListener getEventsListener() {
        return mController.getEventsListener();
    }

    /**
     * Sets a new event listener
     * @param eventsListener
     */
    public void setEventsListener(MrEventsListener eventsListener) {
        mController.setEventsListener(eventsListener);
    }

    /**
     * Checks if the event is register in the event listener of this object
     * @param evName event name
     * @return
     */
    public boolean isEventRegistered(String evName) {
        return mController.isEventRegistered(evName);
    }

    /**
     * Gets a set of all registered events
     * @return
     */
    public Set<String> getRegisteredEvents() {
        return mController.getRegisteredEvents();
    }

    /**
     * Registers an event
     * @param eventName
     */
    public void registerEvent(String eventName) {
        mController.registerEvent(eventName);
    }

    /**
     * Unregisters an event
     * @param eventName
     */
    public void unregisterEvent(String eventName) {
        mController.unregisterEvent(eventName);
    }

    /*******Transform*******/

    /**
     * Gets the rotation quaternion
     * @return
     */
    public MrQuaternion getRotation() {
        return getTransform().getRotation();
    }

    /**
     * Sets the rotation of this object using a quaternion
     * @param rotation
     */
    public void setRotation(MrQuaternion rotation) {
        getTransform().setRotation(rotation);
    }

    /**
     * Rotates the object by a certain angle around the given axis
     * @param angle angle of rotation in degrees
     * @param axis axis in world coordinates
     */
    public void rotate(final float angle, final MrVector3f axis) {
        getTransform().rotate(angle, axis);
    }

    /**
     * Translates an object by x, y, z for every axis in world coordinates
     * @param x translation in x
     * @param y translation in y
     * @param z translation in z
     */
    public void translate(final float x, final float y, final float z) {
        getTransform().translate(x, y, z);
    }

    /**
     * Scales the object in all axis
     * @param s scale factor
     */
    public void scale(final float s) {
        getTransform().scale(s);
    }

    /**
     * Scales every axis by given values
     * @param s scale vector
     */
    public void scale(final MrVector3f s) {
        getTransform().scale(s);
    }

    /**
     * Gets the positive X axis of this object local axis in world coordinates
     * @return
     */
    public MrVector3f getRight() {
        return getTransform().getRight();
    }

    /**
     * Points this object to look with the given up vector
     * @param look
     * @param up
     */
    public void setLookAt(final MrVector3f look, final MrVector3f up) {
        getTransform().setLookAt(look, up);
    }

    /**
     * Scales every axis by given values
     * @param sx scale in X axis
     * @param sy scale in Y axis
     * @param sz scale in Z axis
     */
    public void scale(final float sx, final float sy, final float sz) {
        getTransform().scale(sx, sy, sz);
    }

    /**
     * Sets the scale of this object. This method operates in place
     * @param sx
     * @param sy
     * @param sz
     */
    public void setScale(float sx, float sy, float sz) {
        getTransform().setScale(sx, sy, sz);
    }

    /**
     * Translates an object by the given vector in world coordinates
     * @param v
     */
    public void translate(MrVector3f v) {
        getTransform().translate(v);
    }

    /**
     * Gets the positive Y axis of this object local axis in world coordinates
     * @return
     */
    public MrVector3f getForward() {
        return getTransform().getForward();
    }

    /**
     * Gets the scale vector of this object
     * @return
     */
    public MrVector3f getScale() {
        return getTransform().getScale();
    }

    /**
     * Sets the scale of this object. This method operates in place
     * @param scale
     */
    public void setScale(MrVector3f scale) {
        getTransform().setScale(scale);
    }

    /**
     * Sets the location of this object. This method operates in place
     * @param x
     * @param y
     * @param z
     */
    public void setLocation(float x, float y, float z) {
        getTransform().setLocation(x, y, z);
    }

    /**
     * Sets the rotation of this object. This method operates in place
     * @param angle angle of rotation in degrees
     * @param axis axis to rotate around in world coordinates
     */
    public void setRotation(float angle, MrVector3f axis) {
        getTransform().setRotation(angle, axis);
    }

    /**
     * Gets the positive Z axis of this object local axis in world coordinates
     * @return
     */
    public MrVector3f getUp() {
        return getTransform().getUp();
    }

    /**
     * Sets where this object will point to. This method operates in place
     * @param look
     */
    public void setLookAt(MrVector3f look) {
        getTransform().setLookAt(look);
    }

    /**
     * Sets the rotation of this object. This method operates in place
     * @param angle angle of rotation in degrees
     * @param x
     * @param y
     * @param z
     */
    public void setRotation(float angle, float x, float y, float z) {
        getTransform().setRotation(angle, x, y, z);
    }

    /**
     * Gets the location of this object
     * @return
     */
    public MrVector3f getLocation() {
        return getTransform().getLocation();
    }

    /**
     * Sets the location of this object. This method operates in place
     * @param location
     */
    public void setLocation(final MrVector3f location) {
        getTransform().setLocation(location);
    }

    /**
     * Rotates this object by the given angle, around the given axis, located at the given point
     * @param angle angle of rotation in degrees
     * @param point point where the object will be located
     * @param axis axis of rotation in world coordinates
     */
    public void rotateAround(float angle, MrVector3f point, MrVector3f axis) {
        getTransform().rotateAround(angle, point, axis);
    }

    /**
     * Rotates this object by the given rotation
     * @param q
     */
    public void rotate(MrQuaternion q) {
        getTransform().rotate(q);
    }

    /**
     * Rotates this object by the given angle, around the given axis and passing through the given point
     * @param angle angle of rotation in degrees
     * @param point point where the object will be located
     * @param axis axis of rotation in world coordinates
     * @param through point to pass through when rotates in world coordinates
     */
    public void rotateAround(float angle, MrVector3f point, MrVector3f axis, MrVector3f through) {
        getTransform().rotateAround(angle, point, axis, through);
    }

    /**
     * Rotates this object by the given angle around the given axis
     * @param angle
     * @param x
     * @param y
     * @param z
     */
    public void rotate(float angle, float x, float y, float z) {
        getTransform().rotate(angle, x, y, z);
    }

    @Override
    public String toString() {
        return "MrObject{" +
                "name=" + getName() +
                ",type=" + getSceneObjectType() +
                '}';
    }

    /**
     * Builder base class for MrObject creation
     */
    public static abstract class MrObjectBuilder {
        protected String mName;
        protected MrTransform mTransform = new MrTransform();
        protected Map<String, MrUniformKey> mUniformKeys = new HashMap<>();
        protected MrShaderProgram mShaderProgram = null;

        /**
         * Sets the name of this object. Required
         * @param name
         * @return
         */
        public MrObjectBuilder setName(String name) {
            mName = name;
            return this;
        }

        /**
         * Sets the transform of this object. Optional
         * @param transform
         * @return
         */
        public MrObjectBuilder setTransform(MrTransform transform) {
            mTransform = transform;
            return this;
        }

        /**
         * Sets the uniformkeys of this object. Optional
         * @param uniformKeys
         * @return
         */
        public MrObjectBuilder setUniformKeys(Map<String, MrUniformKey> uniformKeys) {
            mUniformKeys = uniformKeys;
            return this;
        }

        /**
         * Sets the shader program of this object. Optional
         * @param shaderProgram
         * @return
         */
        public MrObjectBuilder setShaderProgram(MrShaderProgram shaderProgram) {
            mShaderProgram = shaderProgram;
            return this;
        }
    }
}
