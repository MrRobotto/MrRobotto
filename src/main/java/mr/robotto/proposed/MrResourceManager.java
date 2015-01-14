/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mr.robotto.collections.MrMapTree;
import mr.robotto.context.MrObjectDataContainer;
import mr.robotto.context.MrSceneObjectsTree;
import mr.robotto.core.controller.MrModel;
import mr.robotto.core.controller.MrObject;
import mr.robotto.core.controller.MrScene;
import mr.robotto.core.data.commons.MrObjectData;
import mr.robotto.core.data.model.MrModelData;
import mr.robotto.core.data.scene.MrSceneData;
import mr.robotto.core.renderer.MrModelRender;
import mr.robotto.core.renderer.MrObjectRender;
import mr.robotto.core.renderer.MrSceneRender;

/*
TODO: Has de crear un findByKey al menos que te busque en los objectsdata
Ese findByKey te devolverá un nodo, con los hijos ya colgando de él
además podrás decirle cuando construya "Oye quiero que para el objeto
con nombre fulanito le apliques el controlador o el renderer este
 */
public class MrResourceManager {
    private MrObjectDataContainer mObjectsData;
    private MrMapTree<String, String> mKeysTree;

    public MrResourceManager(MrObjectDataContainer objectDatas, MrMapTree<String, String> keysTree) {
        mObjectsData = objectDatas;
        mKeysTree = keysTree;
    }

    public MrObjectDataContainer getObjectsData() {
        return mObjectsData;
    }

    public MrMapTree<String, String> getKeysTree() {
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
                    return null;
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
                    return null;
                case MODEL:
                    return new MrModel((MrModelData) objectData, render);
                case SCENE:
                    return new MrScene((MrSceneData) objectData, render);
                default:
                    return null;
            }
        }

        public MrSceneObjectsTree buildSceneObjectsTree() {
            MrMapTree<String, String> keyTree = mManager.getKeysTree();
            MrObjectDataContainer objects = mManager.getObjectsData();
            MrObjectData rootData = objects.findByKey(keyTree.getRoot());
            MrObjectRender render = getRenderer(rootData);
            MrSceneObjectsTree tree = new MrSceneObjectsTree(getObject(rootData));
            Iterator<Map.Entry<String, String>> it = keyTree.parentKeyChildValueTraversal();
            //Skip the root
            if (it.hasNext())
                it.next();
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
