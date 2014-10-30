package mr.robotto.renderer.proposed;

public class MrContext {
    private MrObjectDataList mrObjectsData;
    private MrNode<String> mObjectsHierarchy;

    public MrContext(MrObjectDataList objectDatas, MrNode<String> hierarchy) {
        mrObjectsData = objectDatas;
        mObjectsHierarchy = hierarchy;
    }

    public MrObjectDataList getMrObjectsData() {
        return mrObjectsData;
    }

    public MrNode<String> getObjectsHierarchy() {
        return mObjectsHierarchy;
    }
}
