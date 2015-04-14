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
import mr.robotto.renderer.MrRenderingContext;

/**
 * Created by aaron on 14/04/2015.
 */
public interface MrObjectRender {
    void initializeRender(MrRenderingContext context, MrObjectData link);

    void initializeSizeDependant(int w, int h);

    void render();

    boolean isInitialized();
}
