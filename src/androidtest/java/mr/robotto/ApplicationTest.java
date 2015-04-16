/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto;

/**
 * Created by Aarón on 18/11/2014.
 */

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.test.ApplicationTestCase;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

import mr.robotto.managers.MrResourceManager;
import mr.robotto.loader.MrResourceManagerLoader;
import mr.robotto.utils.MrFileReader;

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
        MrResourceManager context1 = null;
        try {
            InputStream stream = am.open("kingVer3.json");
            JSONObject drac = (JSONObject) new JSONTokener(MrFileReader.read(stream)).nextValue();
            /*MrObjectLoader loader = new MrObjectLoader(drac);
            MrSceneData ob = (MrSceneData)loader.parse();
            getRenderer().setScene(ob);
            getRenderer().model = new MrModelController((MrModelData)ob.getChildren().findByKey(0), new MrModelRender());*/
            MrResourceManagerLoader loader = new MrResourceManagerLoader(drac);
            context1 = loader.parse();
            //getRenderer().setScene((MrSceneData)context1.getObjectsData().findByKey("Scene"));
            //getRenderer().model = new MrModelController((MrModelData)context1.getObjectsData().findByKey("Cube"), new MrModelRender());
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