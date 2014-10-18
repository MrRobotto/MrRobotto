/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.data.scene;

import java.util.HashMap;

import mr.robotto.renderer.data.object.MrObjectData;
import mr.robotto.renderer.data.MrSceneObjType;
import mr.robotto.renderer.data.model.MrModelData;
import mr.robotto.renderer.renderer.rendereables.objectrenderers.MrSceneRender;
import mr.robotto.renderer.linearalgebra.MrVector4f;

public class MrSceneData extends MrObjectData {
    private MrVector4f clearColor;
    private HashMap<String,MrObjectData> objects;
    private HashMap<String,MrModelData> models;



    public MrSceneData(String name, MrVector4f clearColor) {
        super(name, MrSceneObjType.SCENE, new MrSceneRender());
        this.clearColor = clearColor;
        init();
    }

    public MrSceneData(String name) {
        this(name, new MrVector4f(0.5f));
    }

    private void init() {
        objects = new HashMap<String, MrObjectData>();
        models = new HashMap<String, MrModelData>();
        //setRenderer(new MrSceneRender());
    }

    public MrVector4f getClearColor() {
        return clearColor;
    }

    public void setClearColor(float r, float g, float b, float a) {
        this.clearColor = new MrVector4f(r,g,b,a);
    }

    @Override
    public void addChild(MrObjectData node) {
        super.addChild(node);
        switch (node.getSceneObjType()) {
            case MODEL:
                models.put(node.getName(), (MrModelData)node);
        }
    }

    public void setClearColor(MrVector4f clearColor) {
        this.clearColor = clearColor;
    }

    public void addObject(MrObjectData object) {
        objects.put(object.getName(), object);
    }

    public MrObjectData getObject(String name) {
        return objects.get(name);
    }

    public MrModelData getModel(String name) {
        return models.get(name);
    }

    public void initializeSizeDependant(int width, int height) {

    }
}
