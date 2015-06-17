/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader.components.skeleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mr.robotto.components.data.action.MrSkeletalAction;
import mr.robotto.components.data.skeleton.MrBone;
import mr.robotto.components.data.skeleton.MrSkeleton;
import mr.robotto.loader.components.action.MrSkeletalActionLoader;
import mr.robotto.loader.core.MrJsonBaseLoader;

/**
 * Created by aaron on 27/04/2015.
 */
public class MrSkeletonLoader extends MrJsonBaseLoader<MrSkeleton> {
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

    private Map<String, MrBone> loadPose() throws JSONException {
        JSONArray poseJson = mRoot.getJSONArray("Pose");
        Map<String, MrBone> bones = new HashMap<String, MrBone>();
        for (int i = 0; i < poseJson.length(); i++) {
            MrBoneLoader boneLoader = new MrBoneLoader(poseJson.getJSONObject(i));
            MrBone bone = boneLoader.parse();
            bones.put(bone.getName(), bone);
        }
        return bones;
    }

    private Map<String, MrSkeletalAction> loadActions() throws JSONException {
        JSONArray actionsJson = mRoot.getJSONArray("Actions");
        Map<String, MrSkeletalAction> actions = new HashMap<String, MrSkeletalAction>();
        for (int i = 0; i < actionsJson.length(); i++) {
            MrSkeletalActionLoader loader = new MrSkeletalActionLoader(actionsJson.getJSONObject(i));
            MrSkeletalAction action = loader.parse();
            actions.put(action.getName(), action);
        }
        return actions;
    }
}
