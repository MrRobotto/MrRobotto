/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader.components.action;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.components.data.action.MrFrame;
import mr.robotto.components.data.action.MrSkeletalAction;
import mr.robotto.components.data.skeleton.MrBone;
import mr.robotto.loader.components.skeleton.MrBoneLoader;
import mr.robotto.loader.core.MrJsonBaseLoader;

/**
 * Loads an {@link MrSkeletalAction} from JSON
 */
public class MrSkeletalActionLoader extends MrJsonBaseLoader<MrSkeletalAction> {
    public MrSkeletalActionLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrSkeletalAction parse() throws JSONException {
        MrSkeletalAction action = new MrSkeletalAction(loadActionName(), loadFps());
        loadKeyFrames(action);
        return action;
    }

    private int loadFps() throws JSONException {
        return mRoot.getInt("FPS");
    }

    private String loadActionName() throws JSONException {
        return mRoot.getString("Name");
    }

    private void loadKeyFrames(MrSkeletalAction action) throws JSONException {
        JSONArray keyframesJson = mRoot.getJSONArray("KeyFrames");
        for (int i = 0; i < keyframesJson.length(); i++) {
            MrFrame frame = getFrame(keyframesJson.getJSONObject(i));
            action.addKeyFrame(frame);
        }
    }

    private MrFrame getFrame(JSONObject frameJson) throws JSONException {
        int keyFrameNumber = frameJson.getInt("Number");
        JSONArray bonesJson = frameJson.getJSONArray("Bones");
        MrFrame frame = new MrFrame(keyFrameNumber);
        for (int i = 0; i < bonesJson.length(); i++) {
            MrBoneLoader boneLoader = new MrBoneLoader(bonesJson.getJSONObject(i));
            MrBone bone = boneLoader.parse();
            frame.addBone(bone);
        }
        return frame;
    }
}
