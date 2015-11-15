package mr.robotto;

import junit.framework.TestCase;

import java.io.InputStream;

import mr.robotto.engine.loader.file.MrMrrLoader;
import mr.robotto.sceneobjects.MrObject;
import mr.robotto.sceneobjects.MrSceneTree;

/**
 * Created by aaron on 03/11/2015.
 */
public class LoaderTest extends TestCase {

    public void testLoadFile() throws Exception {
        InputStream stream = null;
        MrMrrLoader loader = new MrMrrLoader(stream);
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
