/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.data.action;

import java.util.HashMap;
import java.util.Map;

import mr.robotto.engine.components.data.skeleton.MrBone;
import mr.robotto.engine.linearalgebra.MrQuaternion;
import mr.robotto.engine.linearalgebra.MrVector3f;

/**
 * Frame of an {@link MrSkeletalAction}
 */
public class MrFrame {
    private final Map<String, MrBone> mBones;
    private int mNumber;

    /**
     * Creates the frame number
     * @param number Frame number
     */
    public MrFrame(int number) {
        mNumber = number;
        mBones = new HashMap<>();
    }

    /**
     * Interpolates two frames
     * @param frame Result frame. The frame number and bones will be overriden
     * @param numFrame Number of the result frame. It requires frame1.numFrame < numFrame < frame2.numFrame
     * @param frame1 The first frame.
     * @param frame2 The second frame
     */
    public static void interpolate(MrFrame frame, int numFrame, MrFrame frame1, MrFrame frame2) {
        frame.setNumber(numFrame);
        Map<String, MrBone> outBones = frame.getBones();
        float e1 = frame1.getFrameNumber(), e2 = frame2.getFrameNumber();
        float t = -1/(e1 - e2) * (numFrame - e1);
        Map<String, MrBone> f2Bones = frame2.getBones();
        for (MrBone bone1 : frame1.getBones().values()) {
            MrBone bone = outBones.get(bone1.getName());
            MrBone bone2 = f2Bones.get(bone1.getName());

            MrQuaternion q1 = bone1.getRotation();
            MrQuaternion q2 = bone2.getRotation();
            MrQuaternion q = bone.getRotation();
            MrQuaternion.getOperator().slerp(q, t, q1, q2);

            MrVector3f l1 = bone1.getLocation();
            MrVector3f l2 = bone2.getLocation();
            MrVector3f l = bone.getLocation();
            MrVector3f.getOperator().lerp(l, t, l1, l2);

            MrVector3f s1 = bone1.getScale();
            MrVector3f s2 = bone2.getScale();
            MrVector3f s = bone.getScale();
            MrVector3f.getOperator().lerp(s, t, s1, s2);
        }
    }

    /**
     * Gets the frame number
     *
     * @return
     */
    public int getFrameNumber() {
        return mNumber;
    }

    /**
     * Sets the frame number
     *
     * @param number
     */
    public void setNumber(int number) {
        mNumber = number;
    }

    /**
     * Gets the bone map of this bone
     *
     * @return
     */
    public Map<String, MrBone> getBones() {
        return mBones;
    }

    /**
     * Adds a bone affected by this frame
     *
     * @param bone
     */
    public void addBone(MrBone bone) {
        mBones.put(bone.getName(), bone);
    }
}
