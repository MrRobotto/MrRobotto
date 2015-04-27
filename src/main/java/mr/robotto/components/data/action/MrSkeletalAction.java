/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.action;

import android.util.SparseArray;

import java.util.Iterator;

import mr.robotto.collections.MrHashMap;
import mr.robotto.components.data.bone.MrBone;

/**
 * Created by aaron on 24/04/2015.
 */
public class MrSkeletalAction {
    public static final int ACTIONTYPE_SKELETAL = 1;

    private String mName;
    private float mFps;
    private MrKeyFrameList mKeyFrames;
    private int mActionType;

    private boolean mPlaying;
    private Iterator<MrFrame> mFrameIterator;

    public MrSkeletalAction(String name, float fps) {
        mName = name;
        mFps = fps;
        mActionType = MrSkeletalAction.ACTIONTYPE_SKELETAL;
        mKeyFrames = new MrKeyFrameList();
        mPlaying = false;
    }

    public String getName() {
        return mName;
    }

    public int getActionType() {
        return mActionType;
    }

    public float getFps() {
        return mFps;
    }

    public void addKeyFrame(MrFrame frame) {
        mKeyFrames.addKeyFrame(frame);
    }

    public void setFps(float fps) {
        mFps = fps;
    }

    public boolean isPlaying() {
        return mPlaying;
    }

    public void play() {
        mPlaying = true;
        mFrameIterator = mKeyFrames.iterator();
    }

    public MrHashMap<String, MrBone> step() {
        if (mFrameIterator.hasNext()) {
            return mFrameIterator.next().getBones();
        }
        return null;
    }
}
