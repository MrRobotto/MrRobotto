/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.proposed;

import mr.robotto.renderer.collections.MrListNode;

/*
TODO: Has de crear un find al menos que te busque en los objectsdata
Ese find te devolverá un nodo, con los hijos ya colgando de él
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

        public void build() {
            /*MrNode2<MrObjectData> dataNode;
            for (MrListNode<String> node : mObjectsHierarchy) {
                String name = node.getData();
                MrObjectData objectData = mrObjectsData.find(name);

            }*/
        }
    }
}
