/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.bone;

import java.util.ArrayList;
import java.util.List;

import mr.robotto.collections.MrHashMap;
import mr.robotto.collections.MrTreeMap;
import mr.robotto.components.data.action.MrActionMap;
import mr.robotto.components.data.action.MrSkeletalAction;

/**
 * Created by aaron on 26/04/2015.
 */
public class MrSkeleton {
    private MrActionMap mActions;
    private MrTreeMap<String, String> mBoneHierarchy;
    private MrBoneMap mPose;
    private ArrayList<String> mBoneOrder;

    private MrSkeletalAction mCurrentAction;

    public MrSkeleton(MrBoneMap pose, ArrayList<String> boneOrder, MrActionMap actions) {
        mActions = actions;
        mPose = pose;
        mBoneOrder = boneOrder;
    }

    public MrSkeleton(MrTreeMap<String, String> boneHierarchy, MrBoneMap pose, ArrayList<String> boneOrder, MrActionMap actions) {
        mActions = actions;
        mBoneHierarchy = boneHierarchy;
        mPose = pose;
        mBoneOrder = boneOrder;
    }

    public MrSkeleton() {
        mActions = new MrActionMap();
        mPose = new MrBoneMap();
        mBoneOrder = new ArrayList<>();
        mCurrentAction = null;
    }

    public void setBoneOrder(List<String> boneOrder) {
        mBoneOrder.addAll(boneOrder);
    }

    public void addBoneOrder(String boneName) {
        mBoneOrder.add(boneName);
    }

    public MrHashMap<String, MrSkeletalAction> getActions() {
        return mActions;
    }

    public void addAction(MrSkeletalAction action) {
        mActions.add(action);
    }

    //TODO: Add exception
    public void playAction(String actionName) {
        mCurrentAction = mActions.findByKey(actionName);
        if (mCurrentAction != null)
            mCurrentAction.play();
    }

    public MrBone[] getPose() {
        MrHashMap<String, MrBone> boneMap = mCurrentAction.step();
        MrBone[] bones = new MrBone[boneMap.size()];
        int i = 0;
        for (String boneName : mBoneOrder) {
            bones[i] = boneMap.findByKey(boneName);
        }
        return bones;
    }
}
