/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.action;

import mr.robotto.collections.MrHashMap;
import mr.robotto.components.data.bone.MrBone;
import mr.robotto.components.data.bone.MrBoneMap;
import mr.robotto.linearalgebra.MrQuaternion;
import mr.robotto.linearalgebra.MrVector3f;

/**
 * Created by aaron on 24/04/2015.
 */
public class MrFrame {
    private int mNumber;
    private MrBoneMap mBones;

    public MrFrame(int number) {
        mNumber = number;
        mBones = new MrBoneMap();
    }

    public int getFrameNumber() {
        return mNumber;
    }

    public MrHashMap<String, MrBone> getBones() {
        return mBones;
    }

    public void addBone(MrBone bone) {
        mBones.add(bone);
    }

    public static MrFrame interpolate(int numFrame, MrFrame frame1, MrFrame frame2) {
        MrFrame frame = new MrFrame(numFrame);
        float e1 = frame1.getFrameNumber(), e2 = frame2.getFrameNumber();
        float t = -1/(e1 - e2) * (numFrame - e1);
        MrHashMap<String, MrBone> f2Bones = frame2.getBones();
        for (MrBone bone1 : frame1.getBones()) {
            MrBone bone2 = f2Bones.findByKey(bone1.getName());
            MrQuaternion q1 = bone1.getRotation();
            MrQuaternion q2 = bone2.getRotation();
            MrQuaternion q = new MrQuaternion();
            MrQuaternion.getOperator().slerp(q, t, q1, q2);

            MrVector3f l1 = bone1.getLocation();
            MrVector3f l2 = bone2.getLocation();
            MrVector3f l = new MrVector3f();
            MrVector3f.getOperator().lerp(l, t, l1, l2);

            MrVector3f s1 = bone1.getScale();
            MrVector3f s2 = bone2.getScale();
            MrVector3f s = new MrVector3f();
            MrVector3f.getOperator().lerp(s, t, s1, s2);

            MrBone bone = new MrBone(bone1.getName(), l, q, s);
            frame.addBone(bone);
        }
        return frame;
    }
}
