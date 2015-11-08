package mr.robotto.engine.loader.proposed;

/**
 * Created by aaron on 03/11/2015.
 */
public class MrMrrMetadata {
    private String mVersion;
    private boolean mHasHierarchy;

    public MrMrrMetadata(String version, boolean hasHierarchy) {
        mVersion = version;
        mHasHierarchy = hasHierarchy;
    }

    public String getVersion() {
        return mVersion;
    }

    public boolean hasHierarchy() {
        return mHasHierarchy;
    }
}
