/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.ui;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import mr.robotto.renderer.renderer.MrRenderer;

public class MrSurfaceView extends GLSurfaceView {

    private MrRenderer renderer;

    public MrSurfaceView(Context context) {
        super(context);

        if (!isInEditMode()) {
            setEngine();
        }
    }

    public MrSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (!isInEditMode()) {
            setEngine();
        }
    }

    public MrRenderer getRenderer() {
        return renderer;
    }

    private void setEngine() {
        renderer = new MrRenderer();

        setEGLContextClientVersion(2);
        setRenderer(renderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
}
