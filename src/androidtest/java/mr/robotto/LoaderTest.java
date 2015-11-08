package mr.robotto;

import junit.framework.TestCase;
import mr.robotto.engine.loader.proposed.MrMrrLoader2;
import mr.robotto.sceneobjects.MrObject;
import mr.robotto.sceneobjects.MrSceneTree;

import java.io.InputStream;

/**
 * Created by aaron on 03/11/2015.
 */
public class LoaderTest extends TestCase {

    public void testLoadFile() throws Exception {
        InputStream stream = null;
        MrMrrLoader2 loader = new MrMrrLoader2(stream);
        loader.check();
        if (loader.isValid()) {
            if (loader.hasHierarchy()) {
                MrSceneTree sceneTree = loader.parseSceneTree();
            } else {
                MrObject obj = loader.parseObject();
            }
        }
    }
}
