/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.ui;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import mr.robotto.engine.renderer.MrRenderer;

/**
 * Specialized class of {@link GLSurfaceView} for using alongside MrRobotto 3D Engine.
 * This view must be present in order to use MrRobotto 3D Engine.
 */
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

    /**
     * Gets the Renderer used
     *
     * @return
     */
    public MrRenderer getRenderer() {
        return renderer;
    }

    private void setEngine() {
        renderer = new MrRenderer();
        setEGLContextClientVersion(2);
        setRenderer(renderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setPreserveEGLContextOnPause(true);
    }
}
