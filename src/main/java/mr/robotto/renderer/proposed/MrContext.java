package mr.robotto.renderer.proposed;

import mr.robotto.renderer.collections.MrListNode;
import mr.robotto.renderer.core.data.object.MrObjectData;
import mr.robotto.renderer.proposed.containersold.MrNode2;

/*
TODO: Has de crear un find al menos que te busque en los objectsdata
Ese find te devolverá un nodo, con los hijos ya colgando de él
además podrás decirle cuando construya "Oye quiero que para el objeto
con nombre fulanito le apliques el controlador o el renderer este
 */
public class MrContext {
    private MrObjectDataList mrObjectsData;
    private MrListNode<String> mObjectsHierarchy;

    public MrContext(MrObjectDataList objectDatas, MrListNode<String> hierarchy) {
        mrObjectsData = objectDatas;
        mObjectsHierarchy = hierarchy;
    }

    public MrObjectDataList getObjectsData() {
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
            MrNode2<MrObjectData> dataNode;
            for (MrListNode<String> node : mObjectsHierarchy) {
                String name = node.getData();
                MrObjectData objectData = mrObjectsData.find(name);

            }
        }
    }
}
