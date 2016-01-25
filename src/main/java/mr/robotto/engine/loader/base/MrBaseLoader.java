/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

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
