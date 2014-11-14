package mr.robotto.renderer.proposed;

import mr.robotto.renderer.core.data.object.MrObjectData;

/*
TODO: Has de crear un get al menos que te busque en los objectsdata
Ese get te devolverá un nodo, con los hijos ya colgando de él
además podrás decirle cuando construya "Oye quiero que para el objeto
con nombre fulanito le apliques el controlador o el renderer este
 */
public class MrContext {
    private MrObjectDataList mrObjectsData;
    private MrNode<String> mObjectsHierarchy;

    public MrContext(MrObjectDataList objectDatas, MrNode<String> hierarchy) {
        mrObjectsData = objectDatas;
        mObjectsHierarchy = hierarchy;
    }

    public MrObjectDataList getObjectsData() {
        return mrObjectsData;
    }

    public MrNode<String> getObjectsHierarchy() {
        return mObjectsHierarchy;
    }

    public class Builder {

        private  MrContext mContext;

        public Builder(MrContext context) {
            mContext = context;
        }

        public void build() {
            MrNode<MrObjectData> dataNode;
            for (MrNode<String> node : mObjectsHierarchy) {
                String name = node.getData();
                MrObjectData objectData = mrObjectsData.get(name);

            }
        }
    }
}
