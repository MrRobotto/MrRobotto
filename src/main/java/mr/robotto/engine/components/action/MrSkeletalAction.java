/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.action;


import java.util.Iterator;
import java.util.Map;

import mr.robotto.engine.components.skeleton.MrBone;

/**
 * This class represents a skeletal animation, its based on KeyFrames, represented as {@link MrFrame}
 * and stored inside an instance of {@link MrKeyFrameList} and it must be related to an {@link mr.robotto.engine.components.skeleton.MrSkeleton}
 */
public class MrSkeletalAction {
    //TODO: Move this to a more generic action class
    public static final int ACTIONTYPE_SKELETAL = 1;
    private String mName;
    private float mSpeed;
    private MrKeyFrameList mKeyFrames;
    private int mActionType;
    private State mState;
    private Iterator<MrFrame> mFrameIterator;
    /**
     * Creates an skeletal animation
     *
     * @param name  Name of this animation
     * @param speed Speed of this animation
     */
    public MrSkeletalAction(String name, float speed) {
        mName = name;
        mSpeed = speed;
        mActionType = MrSkeletalAction.ACTIONTYPE_SKELETAL;
        mKeyFrames = new MrKeyFrameList();
        mState = State.STOPPED;
    }

    /**
     * The name of this action
     * @return
     */
    public String getName() {
        return mName;
    }

    /**
     * Returns the type of this action
     * @return
     */
    public int getActionType() {
        return mActionType;
    }

    /**
     * Gets the speed which this action will be run
     *
     * @return
     */
    public float getSpeed() {
        return mSpeed;
    }

    /**
     * Sets the speed of this action
     *
     * @param speed
     */
    public void setSpeed(float speed) {
        mSpeed = speed;
    }

    /**
     * Adds a new keyframe to this action
     * @param frame
     */
    public void addKeyFrame(MrFrame frame) {
        mKeyFrames.addKeyFrame(frame);
    }

    /**
     * Checks if this action is being playing
     * @return
     */
    public boolean isPlaying() {
        return mState == State.PLAYING;
    }

    /**
     * Plays this action
     */
    public void play() {
        mState = State.PLAYING;
        mFrameIterator = mKeyFrames.iterator();
    }

    /**
     * Plays this action in loop
     */
    public void playContinuosly() {
        mState = State.LOOP;
        mFrameIterator = mKeyFrames.iterator();
    }

    /**
     * Pauses this action
     */
    public void pause() {
        mState = State.PAUSED;
    }

    /**
     * Stops this action
     */
    public void stop() {
        mState = State.STOPPED;
    }

    /**
     * This method steps the action
     * This user usually don't need to call this method directly
     * @return
     */
    public Map<String, MrBone> step() {
        //if (mState == State.STOPPED || mState == State.PAUSED) {
        //    return null;
        //}
        if (mFrameIterator.hasNext()) {
            return mFrameIterator.next().getBones();
        } else if (mState == State.LOOP){
            mFrameIterator = mKeyFrames.iterator();
            return step();
        }
        return null;
    }

    public enum State {
        PLAYING, LOOP, STOPPED, PAUSED
    }
}
