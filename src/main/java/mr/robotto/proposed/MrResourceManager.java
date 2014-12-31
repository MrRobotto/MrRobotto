/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;

import java.util.Iterator;
import java.util.Map;

import mr.robotto.collections.MrMapTree;
import mr.robotto.core.data.commons.MrObjectData;

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

    public static class Builder2 {
        private MrResourceManager mManager;

        public Builder2(MrResourceManager manager) {
            mManager = manager;
        }

        public MrSceneObjectTree buildSceneObjectsTree() {
            MrMapTree<String, String> keyTree = mManager.getKeysTree();
            MrObjectDataContainer objects = mManager.getObjectsData();
            MrObjectData root = objects.findByKey(keyTree.getRoot());
            MrSceneObjectTree tree = new MrSceneObjectTree(root);
            Iterator<Map.Entry<String, String>> it = keyTree.parentKeyChildValueTraversal();
            //Skip the root
            if (it.hasNext())
                it.next();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                tree.addChild(entry.getKey(), objects.findByKey(entry.getValue()));
            }
            return tree;
        }
    }
}
