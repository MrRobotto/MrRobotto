/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader.animation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mr.robotto.components.data.action.MrActionMap;
import mr.robotto.components.data.action.MrSkeletalAction;
import mr.robotto.components.data.bone.MrBone;
import mr.robotto.components.data.bone.MrBoneMap;
import mr.robotto.components.data.bone.MrSkeleton;
import mr.robotto.loader.MrBaseLoader;

/**
 * Created by aaron on 27/04/2015.
 */
public class MrSkeletonLoader extends MrBaseLoader<MrSkeleton> {
    public MrSkeletonLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrSkeleton parse() throws JSONException {
        MrSkeleton skeleton = new MrSkeleton(loadPose(), loadBoneOrder(), loadActions());
        return skeleton;
    }

    private ArrayList<String> loadBoneOrder() throws JSONException {
        JSONArray boneOrderJson = mRoot.getJSONArray("BoneOrder");
        ArrayList<String> boneOrder = new ArrayList<>();
        for (int i = 0; i < boneOrderJson.length(); i++) {
            String name = boneOrderJson.getString(i);
            boneOrder.add(name);
        }
        return boneOrder;
    }

    private MrBoneMap loadPose() throws JSONException {
        JSONArray poseJson = mRoot.getJSONArray("Pose");
        MrBoneMap bones = new MrBoneMap();
        for (int i = 0; i < poseJson.length(); i++) {
            MrBoneLoader boneLoader = new MrBoneLoader(poseJson.getJSONObject(i));
            MrBone bone = boneLoader.parse();
            bones.add(bone);
        }
        return bones;
    }

    private MrActionMap loadActions() throws JSONException {
        JSONArray actionsJson = mRoot.getJSONArray("Actions");
        MrActionMap actions = new MrActionMap();
        for (int i = 0; i < actionsJson.length(); i++) {
            MrSkeletalActionLoader loader = new MrSkeletalActionLoader(actionsJson.getJSONObject(i));
            MrSkeletalAction action = loader.parse();
            actions.add(action);
        }
        return actions;
    }
}
