/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

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
