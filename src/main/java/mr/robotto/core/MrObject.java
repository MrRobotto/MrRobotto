/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core;

import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.controller.MrObjectController;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.linearalgebra.MrQuaternion;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector3f;
import mr.robotto.renderer.MrRenderingContext;
import mr.robotto.components.data.uniformgenerator.MrUniformGeneratorMap;
import mr.robotto.scenetree.MrSceneObjectsTree;

//TODO: Cambiar los objectdata y render a elementos de clases inferiores
public abstract class MrObject {
    private MrObjectController mController;
    private MrSceneObjectsTree mTree;

    protected MrObject(MrObjectController controller) {
        mController = controller;
    }

    public MrObjectController getController() {
        return mController;
    }

    public void initializeRender(MrRenderingContext context) {
        initializeUniforms(mController.getUniformGenerators());
        mController.initializeRender(context);
    }

    public void initializeSizeDependant(int widthScreen, int heightScreen) {
        mController.initializeSizeDependant(widthScreen, heightScreen);
    }

    public void render() {
        mController.render();
    }

    public void initializeUniforms(MrUniformGeneratorMap uniformGenerators) {

    }

    public void setTree(MrSceneObjectsTree tree) {
        mTree = tree;
    }

    //TODO: Delete
    public MrObjectData getData() {
        return mController.getData();
    }

    public void updateUniform(MrUniformKey uniform, MrUniformKeyMap.MrUniformKeyMapView uniforms, MrSceneObjectsTree tree) {
        mController.updateUniform(uniform, uniforms, tree);
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

    public MrUniformGeneratorMap getUniformGenerators() {
        return mController.getUniformGenerators();
    }

    public MrShaderProgram getShaderProgram() {
        return mController.getShaderProgram();
    }

    public MrUniformKeyMap getUniformKeys() {
        return mController.getUniformKeys();
    }


    public MrQuaternion getRotation() {
        return getTransform().getRotation();
    }

    public void rotate(float angle, MrVector3f axis) {
        getTransform().rotate(angle, axis);
    }

    public void translate(float x, float y, float z) {
        getTransform().translate(x, y, z);
    }

    public void scale(float s) {
        getTransform().scale(s);
    }

    public void scale(MrVector3f s) {
        getTransform().scale(s);
    }

    public MrVector3f getRight() {
        return getTransform().getRight();
    }

    public void setLookAt(MrVector3f look, MrVector3f up) {
        getTransform().setLookAt(look, up);
    }

    public void setLocation(MrVector3f location) {
        getTransform().setLocation(location);
    }

    public void scale(float sx, float sy, float sz) {
        getTransform().scale(sx, sy, sz);
    }

    public void setScale(float sx, float sy, float sz) {
        getTransform().setScale(sx, sy, sz);
    }

    public void setScale(MrVector3f scale) {
        getTransform().setScale(scale);
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

    public void setRotation(MrQuaternion rotation) {
        getTransform().setRotation(rotation);
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
}