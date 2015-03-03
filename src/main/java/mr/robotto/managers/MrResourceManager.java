/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.managers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mr.robotto.collections.MrTreeMap;
import mr.robotto.core.controller.MrCamera;
import mr.robotto.core.controller.MrModel;
import mr.robotto.core.controller.MrObject;
import mr.robotto.core.controller.MrScene;
import mr.robotto.core.data.MrCameraData;
import mr.robotto.core.data.MrModelData;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.core.data.MrSceneData;
import mr.robotto.core.renderer.MrCameraRender;
import mr.robotto.core.renderer.MrModelRender;
import mr.robotto.core.renderer.MrObjectRender;
import mr.robotto.core.renderer.MrSceneRender;
import mr.robotto.loader.MrObjectDataMap;
import mr.robotto.scenetree.MrSceneObjectsTree;

/*
TODO: Has de crear un findByKey al menos que te busque en los objectsdata
Ese findByKey te devolverá un nodo, con los hijos ya colgando de él
además podrás decirle cuando construya "Oye quiero que para el objeto
con nombre fulanito le apliques el controlador o el renderer este
 */
public class MrResourceManager {
    private MrObjectDataMap mObjectsData;
    private MrTreeMap<String, String> mKeysTree;

    public MrResourceManager(MrObjectDataMap objectDatas, MrTreeMap<String, String> keysTree) {
        mObjectsData = objectDatas;
        mKeysTree = keysTree;
    }

    public MrObjectDataMap getObjectsData() {
        return mObjectsData;
    }

    public MrTreeMap<String, String> getKeysTree() {
        return mKeysTree;
    }

    public static class Builder {
        private MrResourceManager mManager;
        private HashMap<String, MrObjectRender> mRenderers;

        public Builder(MrResourceManager manager) {
            mManager = manager;
        }

        private MrObjectRender getRenderer(MrObjectData object) {
            //si esta en los renderers cogelo de ahi, si no
            switch (object.getSceneObjectType()) {
                case CAMERA:
                    return new MrCameraRender();
                case MODEL:
                    return new MrModelRender();
                case SCENE:
                    return new MrSceneRender();
                default:
                    return null;
            }
        }

        private MrObject getObject(MrObjectData objectData) {
            MrObjectRender render = getRenderer(objectData);
            switch (objectData.getSceneObjectType()) {
                case CAMERA:
                    return new MrCamera((MrCameraData) objectData, render);
                case MODEL:
                    return new MrModel((MrModelData) objectData, render);
                case SCENE:
                    return new MrScene((MrSceneData) objectData, render);
                default:
                    return null;
            }
        }

        public MrSceneObjectsTree buildSceneObjectsTree() {
            MrTreeMap<String, String> keyTree = mManager.getKeysTree();
            MrObjectDataMap objects = mManager.getObjectsData();
            MrObjectData rootData = objects.findByKey(keyTree.getRoot());
            MrObjectRender render = getRenderer(rootData);
            MrSceneObjectsTree tree = new MrSceneObjectsTree(getObject(rootData));
            Iterator<Map.Entry<String, String>> it = keyTree.parentKeyChildValueTraversal();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                //Gets the value
                MrObjectData objectData = objects.findByKey(entry.getValue());
                //Gets the root key via entry.getKey
                tree.addChildByKey(entry.getKey(), getObject(objectData));
            }
            return tree;
        }
    }
}
