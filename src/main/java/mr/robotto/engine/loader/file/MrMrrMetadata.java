package mr.robotto.engine.loader.file;

/**
 * Created by aaron on 03/11/2015.
 */
class MrMrrMetadata {
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
