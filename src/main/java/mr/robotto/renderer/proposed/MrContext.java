package mr.robotto.renderer.proposed;

public class MrContext {
    private MrObjectDataList mrObjectDatas;

    public MrContext(MrObjectDataList objectDatas) {
        mrObjectDatas = objectDatas;
    }

    public MrObjectDataList getMrObjectDatas() {
        return mrObjectDatas;
    }
}
