/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mr.robotto.MrRobotto;
import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.data.uniformgenerator.MrUniformGenerator;
import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.core.controller.MrObjectController;
import mr.robotto.events.MrEventsListener;
import mr.robotto.linearalgebra.MrQuaternion;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector3f;
import mr.robotto.scenetree.MrSceneTree;

//TODO: Cambiar los objectdata y render a elementos de clases inferiores
public abstract class MrObject {
    protected MrRobotto mRobotto;
    private MrObjectController mController;
    private MrSceneTree mTree;

    protected MrObject(MrObjectController controller) {
        mController = controller;
        mController.setAttachedObject(this);
        //mRobotto = MrRobotto.getInstance();
    }

    public MrObjectController getController() {
        return mController;
    }

    public void setRobottoEngine(MrRobotto robotto) {
        mRobotto = robotto;
    }

    /*******Controller******/
    protected void queueEvent(Runnable runnable) {
        mRobotto.queueEvent(runnable);
    }

    public void initializeUniforms(Map<String, MrUniformGenerator> uniformGenerators) {

    }

    public MrSceneObjectType getSceneObjectType() {
        return mController.getSceneObjectType();
    }

    public boolean isInitialized() {
        return mController.isInitialized();
    }

    public String getName() {
        return mController.getName();
    }

    public MrTransform getTransform() {
        return mController.getTransform();
    }

    public void setTransform(MrTransform transform) {
        mController.setTransform(transform);
    }

    public Map<String, MrUniformGenerator> getUniformGenerators() {
        return mController.getUniformGenerators();
    }

    public MrShaderProgram getShaderProgram() {
        return mController.getShaderProgram();
    }

    public Map<String, MrUniformKey> getUniformKeys() {
        return mController.getUniformKeys();
    }

    public MrSceneTree getTree() {
        return mTree;
    }

    /*****Tree*****/
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
    public MrEventsListener getEventsListener() {
        return mController.getEventsListener();
    }

    public void setEventsListener(MrEventsListener eventsListener) {
        mController.setEventsListener(eventsListener);
    }

    /*******Transform*******/
    public MrQuaternion getRotation() {
        return getTransform().getRotation();
    }

    public void setRotation(MrQuaternion rotation) {
        getTransform().setRotation(rotation);
    }

    public void rotate(final float angle, final MrVector3f axis) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                getTransform().rotate(angle, axis);
            }
        });
    }

    public void translate(final float x, final float y, final float z) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                getTransform().translate(x, y, z);
            }
        });
    }

    public void scale(final float s) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                getTransform().scale(s);
            }
        });
    }

    public void scale(final MrVector3f s) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                getTransform().scale(s);
            }
        });
    }

    public MrVector3f getRight() {
        return getTransform().getRight();
    }

    public void setLookAt(final MrVector3f look, final MrVector3f up) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                getTransform().setLookAt(look, up);
            }
        });
    }

    public void scale(final float sx, final float sy, final float sz) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                getTransform().scale(sx, sy, sz);
            }
        });
    }

    public void setScale(float sx, float sy, float sz) {
        getTransform().setScale(sx, sy, sz);
    }

    public void translate(MrVector3f v) {
        getTransform().translate(v);
    }

    public MrVector3f getForward() {
        return getTransform().getForward();
    }

    public MrVector3f getScale() {
        return getTransform().getScale();
    }

    public void setScale(MrVector3f scale) {
        getTransform().setScale(scale);
    }

    public void setLocation(float x, float y, float z) {
        getTransform().setLocation(x, y, z);
    }

    public void setRotation(float angle, MrVector3f axis) {
        getTransform().setRotation(angle, axis);
    }

    public MrVector3f getUp() {
        return getTransform().getUp();
    }

    public void setLookAt(MrVector3f look) {
        getTransform().setLookAt(look);
    }

    public void setRotation(float angle, float x, float y, float z) {
        getTransform().setRotation(angle, x, y, z);
    }

    public MrVector3f getLocation() {
        return getTransform().getLocation();
    }

    public void setLocation(final MrVector3f location) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                getTransform().setLocation(location);
            }
        });
    }

    public void rotateAround(float angle, MrVector3f point, MrVector3f axis) {
        getTransform().rotateAround(angle, point, axis);
    }

    public void rotate(MrQuaternion q) {
        getTransform().rotate(q);
    }

    public void rotateAround(float angle, MrVector3f point, MrVector3f axis, MrVector3f through) {
        getTransform().rotateAround(angle, point, axis, through);
    }

    public void rotate(float angle, float x, float y, float z) {
        getTransform().rotate(angle, x, y, z);
    }

    /**
     * Created by aaron on 16/06/2015.
     */
    public static abstract class MrObjectBuilder {
        protected String mName;
        protected MrTransform mTransform = new MrTransform();
        protected Map<String, MrUniformKey> mUniformKeys = new HashMap<>();
        protected MrShaderProgram mShaderProgram = null;

        public MrObjectBuilder setName(String name) {
            mName = name;
            return this;
        }

        public MrObjectBuilder setTransform(MrTransform transform) {
            mTransform = transform;
            return this;
        }

        public MrObjectBuilder setUniformKeys(Map<String, MrUniformKey> uniformKeys) {
            mUniformKeys = uniformKeys;
            return this;
        }

        public MrObjectBuilder setShaderProgram(MrShaderProgram shaderProgram) {
            mShaderProgram = shaderProgram;
            return this;
        }
    }
}
