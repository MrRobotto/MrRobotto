package mr.robotto;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.test.ApplicationTestCase;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

import mr.robotto.renderer.core.controller.MrModelController;
import mr.robotto.renderer.core.data.model.MrModelData;
import mr.robotto.renderer.core.data.scene.MrSceneData;
import mr.robotto.renderer.core.rendereable.objectrenderers.MrModelRender;
import mr.robotto.renderer.proposed.MrContext;
import mr.robotto.renderer.proposed.MrContextLoader;
import mr.robotto.renderer.utils.MrFileReader;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testLoaders() {
        Context context = getContext();
        AssetManager am = context.getAssets();
        MrContext context1 = null;
        try {
            InputStream stream = am.open("kingVer3.json");
            JSONObject drac = (JSONObject)new JSONTokener(MrFileReader.read(stream)).nextValue();
            /*MrObjectLoader loader = new MrObjectLoader(drac);
            MrSceneData ob = (MrSceneData)loader.parse();
            getRenderer().setScene(ob);
            getRenderer().model = new MrModelController((MrModelData)ob.getChildren().find(0), new MrModelRender());*/
            MrContextLoader loader = new MrContextLoader(drac);
            context1 = loader.parse();
            //getRenderer().setScene((MrSceneData)context1.getObjectsData().find("Scene"));
            //getRenderer().model = new MrModelController((MrModelData)context1.getObjectsData().find("Cube"), new MrModelRender());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertNotNull(context1);
    }

    public void testCollections() {
        
    }
}