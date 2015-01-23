/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.renderer;

import mr.robotto.core.data.MrObjectData;
import mr.robotto.proposed.MrRenderingContext;

/**
 * Created by Aarón on 09/12/2014.
 */
public interface MrObjectRender {
    public void initializeRender(MrObjectData link, MrRenderingContext context);

    public void initializeSizeDependant(int w, int h);

    public void render();

    public boolean isInitialized();
}
