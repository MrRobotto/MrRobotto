package mr.robotto.loaders;

import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;

import java.io.InputStream;

import mr.robotto.engine.loader.file.MrMrrLoader;
import mr.robotto.sceneobjects.MrSceneTree;

/**
 * Created by aaron on 24/12/2015.
 */
public class SceneTreeLoaderTest extends InstrumentationTestCase {

    public void testLoadFullScene() throws Exception {
        AssetManager manager = getInstrumentation().getContext().getAssets();
        InputStream in = manager.open("robottorun.mrr");
        MrMrrLoader loader = new MrMrrLoader(in);
        assertEquals(loader.check(), true);
        MrSceneTree tree = loader.parseSceneTree();
        assertNotNull(tree);
    }
}
