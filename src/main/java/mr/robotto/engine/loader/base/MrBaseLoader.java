package mr.robotto.engine.loader.base;

import mr.robotto.engine.loader.MrResources;
import mr.robotto.engine.utils.MrThreadPool;

/**
 * Created by aaron on 12/11/2015.
 */
public class MrBaseLoader {

    private MrThreadPool mThreadPool;

    public MrBaseLoader() {
        mThreadPool = new MrThreadPool();
    }

    protected MrResources getResources() {
        return MrResources.getInstance();
    }

    protected MrThreadPool getThreadPool() {
        return mThreadPool;
    }
}
