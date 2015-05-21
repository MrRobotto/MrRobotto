/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.skeleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mr.robotto.collections.MrTreeMap;
import mr.robotto.components.data.action.MrSkeletalAction;

/**
 * Created by aaron on 26/04/2015.
 */
public class MrSkeleton {
    private Map<String, MrSkeletalAction> mActions;
    private MrTreeMap<String, String> mBoneHierarchy;
    private Map<String, MrBone> mPose;
    private ArrayList<String> mBoneOrder;

    private MrSkeletalAction mCurrentAction;

    public MrSkeleton(Map<String, MrBone> pose, ArrayList<String> boneOrder, Map<String, MrSkeletalAction> actions) {
        mActions = actions;
        mPose = pose;
        mBoneOrder = boneOrder;
    }

    public MrSkeleton(MrTreeMap<String, String> boneHierarchy, Map<String, MrBone> pose, ArrayList<String> boneOrder, Map<String, MrSkeletalAction> actions) {
        mActions = actions;
        mBoneHierarchy = boneHierarchy;
        mPose = pose;
        mBoneOrder = boneOrder;
    }

    public MrSkeleton() {
        mActions = new HashMap<String, MrSkeletalAction>();
        mPose = new HashMap<String, MrBone>();
        mBoneOrder = new ArrayList<>();
        mCurrentAction = null;
    }

    public int getNumBones() {
        return mBoneOrder.size();
    }

    public void setBoneOrder(List<String> boneOrder) {
        mBoneOrder.addAll(boneOrder);
    }

    public void addBoneOrder(String boneName) {
        mBoneOrder.add(boneName);
    }

    public Map<String, MrSkeletalAction> getActions() {
        return mActions;
    }

    public void addAction(MrSkeletalAction action) {
        mActions.put(action.getName(), action);
    }

    //TODO: Add exception
    public void playAction(String actionName) {
        mCurrentAction = mActions.get(actionName);
        if (mCurrentAction != null)
            mCurrentAction.play();
    }

    public void playActionContinuosly(String actionName) {
        mCurrentAction = mActions.get(actionName);
        if (mCurrentAction != null) {
            mCurrentAction.playContinuosly();
        }
    }

    private MrBone[] sortBones(Map<String, MrBone> boneMap) {
        MrBone[] bones = new MrBone[boneMap.size()];
        int i = 0;
        for (String boneName : mBoneOrder) {
            bones[i] = boneMap.get(boneName);
            i++;
        }
        return bones;
    }

    public MrBone[] getPose() {
        if (mCurrentAction == null) {
            return sortBones(mPose);
        }
        Map<String, MrBone> boneMap = mCurrentAction.step();
        if (boneMap == null) {
            return sortBones(mPose);
        }
        return sortBones(boneMap);
    }
}
