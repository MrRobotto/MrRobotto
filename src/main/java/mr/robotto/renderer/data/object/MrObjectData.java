/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.data.object;

import java.util.ArrayList;
import java.util.List;

import mr.robotto.renderer.data.MrSceneObjType;
import mr.robotto.renderer.proposed.MrIdentificable;
import mr.robotto.renderer.proposed.MrUniformKeyList;
import mr.robotto.renderer.renderer.rendereables.objectrenderers.MrObjectRender;
import mr.robotto.renderer.shaders.MrShaderProgram;
import mr.robotto.renderer.transform.MrTransform;

public abstract class MrObjectData implements Comparable<MrObjectData>,MrIdentificable<String> {


    //TODO: Convert the arraylist to some other custom class
    private MrObjectData parent;
    private ArrayList<MrObjectData> children;
    private int depth;

    private boolean active;
    private boolean initialized;

    private String name;
    private MrSceneObjType sceneObjType;
    private MrTransform transform;
    private MrUniformKeyList uniformKeys;
    private MrShaderProgram shaderProgram;
    private MrObjectRender renderer;


    //TODO: Aquí falta el parent, quizás deberías montar un builder?
    public MrObjectData(String name, MrSceneObjType sceneObjType, MrTransform transform, MrUniformKeyList uniformKeys, MrShaderProgram shaderProgram, MrObjectRender renderer) {
        this.name = name;
        this.transform = transform;
        this.sceneObjType = sceneObjType;
        this.shaderProgram = shaderProgram;
        this.uniformKeys = uniformKeys;
        setRenderer(renderer);
        init();
    }

    //TODO: Review this constructor
    public MrObjectData(String name, MrSceneObjType sceneObjType, MrObjectRender renderer) {
        this(name, sceneObjType, new MrTransform(), new MrUniformKeyList(), null, renderer);
    }

    private void init() {
        children = new ArrayList<MrObjectData>();
        initialized = false;
        active = false;
    }

    @Override
    public String getElementId() {
        return getName();
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void initialize() {
        initialized = true;
    }

    public void deInitialize() {
        initialized = false;
    }

    public MrSceneObjType getSceneObjType() {
        return sceneObjType;
    }

    public MrTransform getTransform() {
        return transform;
    }

    public void setTransform(MrTransform transform) {
        this.transform = transform;
    }

    public MrUniformKeyList getUniformKeys() {
        return uniformKeys;
    }

    public void setUniformKeys(MrUniformKeyList uniformKeys) {
        this.uniformKeys = uniformKeys;
    }

    public MrShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    public void setShaderProgram(MrShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
    }

    public MrObjectRender getRenderer() {
        return renderer;
    }

    public void setRenderer(MrObjectRender renderer) {
        this.renderer = renderer;
        if (renderer != null) {
            this.renderer.linkWith(this);
        }
    }

    @Override
    public int compareTo(MrObjectData node) {
        if (depth > node.getDepth()) {
            return 1;
        } else {
            return depth < node.getDepth() ? -1 : 0;
        }
    }

    public int getDepth() {
        return depth;
    }

    public void setParent(MrObjectData parent) {
        //If this node has already a parent
        if (hasParent()) {
            //We remove the children from the parent
            this.parent.children.remove(this);
        }
        if (parent == null) {
            this.depth = 0;
        } else {
            this.depth = parent.getDepth() + 1;
            parent.children.add(this);
        }
        this.parent = parent;
    }

    public MrObjectData getParent() {
        return parent;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public List<MrObjectData> getChildren() {
        return children;
    }

    public void addChild(MrObjectData node) {
        node.setParent(this);
    }

    public void removeChild(MrObjectData node) {
        node.setParent(null);
    }
}
