/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed.aus;

import mr.robotto.linearalgebra.MrLinearAlgebraObject;

/**
 * Created by aaron on 14/02/2015.
 */
public class MrUniformGeneratorMapView {
    private MrUniformGeneratorMapController mController;

    public MrUniformGeneratorMapView(MrUniformGeneratorMapController ctrl) {
        mController = ctrl;
    }

    public MrLinearAlgebraObject findByKey(String s) {
        return mController.findByKey(s);
    }

    public boolean containsKey(String s) {
        return mController.containsKey(s);
    }
}
