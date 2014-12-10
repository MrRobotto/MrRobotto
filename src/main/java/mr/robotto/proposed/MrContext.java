/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;

import mr.robotto.collections.MrListNode;
import mr.robotto.collections.core.MrNode;
import mr.robotto.core.controller.MrModel;
import mr.robotto.core.controller.MrScene;
import mr.robotto.core.data.MrModelData;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.core.data.MrSceneData;
import mr.robotto.core.data.commons.MrSceneObjectType;
import mr.robotto.core.rendereable.MrModelRender;
import mr.robotto.core.rendereable.MrSceneRender;

/*
TODO: Has de crear un findByKey al menos que te busque en los objectsdata
Ese findByKey te devolverá un nodo, con los hijos ya colgando de él
además podrás decirle cuando construya "Oye quiero que para el objeto
con nombre fulanito le apliques el controlador o el renderer este
 */
public class MrContext {
    private MrObjectDataContainer mrObjectsData;
    private MrListNode<String> mObjectsHierarchy;

    public MrContext(MrObjectDataContainer objectDatas, MrListNode<String> hierarchy) {
        mrObjectsData = objectDatas;
        mObjectsHierarchy = hierarchy;
    }

    public MrObjectDataContainer getObjectsData() {
        return mrObjectsData;
    }

    public MrListNode<String> getObjectsHierarchy() {
        return mObjectsHierarchy;
    }

    public class Builder {

        private  MrContext mContext;

        public Builder(MrContext context) {
            mContext = context;
        }

        private MrSceneObjectsTree getSceneNode(MrSceneObjectsTree parent, MrNode<String> node) {
            MrObjectData objectData = getObjectsData().findByKey(node.getData());
            MrSceneObjectType type = objectData.getSceneObjectType();
            switch (type) {
                case MODEL:
                    MrModel model = new MrModel((MrModelData) objectData, new MrModelRender());
                    return new MrSceneObjectsTree(parent, model);
                case SCENE:
                    MrScene scene = new MrScene((MrSceneData) objectData, new MrSceneRender());
                    return new MrSceneObjectsTree(parent, scene);
            }
            return null;
        }

        public void build() {
            MrNode<String> root = getObjectsHierarchy().getRoot();
            MrSceneObjectsTree rootTree = getSceneNode(null, root);

            //MrSceneObjectsTree sceneObjectsTree = new MrSceneObjectsTree();
            for (MrListNode<String> strNode : getObjectsHierarchy()) {
                String name = strNode.getData();
                MrObjectData objectData = getObjectsData().findByKey(name);
                MrSceneObjectType type = objectData.getSceneObjectType();
                switch (type) {
                    case MODEL:

                        break;
                    case SCENE:

                        break;
                }
            }
            /*MrNode2<MrObjectData> dataNode;
            for (MrListNode<String> node : mObjectsHierarchy) {
                String name = node.getData();
                MrObjectData objectData = mrObjectsData.findByKey(name);

            }*/
        }
    }
}
