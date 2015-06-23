/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.renderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import mr.robotto.engine.components.comp.MrComponent;
import mr.robotto.engine.components.comp.MrShaderProgram;
import mr.robotto.engine.components.comp.MrTexture;
import mr.robotto.engine.core.controller.MrModelController;

/**
 * Created by aaron on 18/06/2015.
 */
public class MrRenderingSorter {

    private List<MrModelController> mModels;
    private List<MrModelController> mSortedModels;
    private HashMap<String, ArrayList<MrModelController>> mComponents;

    public MrRenderingSorter(List<MrModelController> models) {
        mModels = models;
        mSortedModels = new ArrayList<>(models.size());
        mComponents = new HashMap<>();
    }

    private void addComponentModelPair(MrComponent component, MrModelController model) {
        String key = component.getType() + component.getName();
        if (mComponents.containsKey(key)) {
            mComponents.get(key).add(model);
        } else {
            ArrayList<MrModelController> array = new ArrayList<>();
            array.add(model);
            mComponents.put(key, array);
        }
    }

    private void generateSortedList() {
        ArrayList<List<MrModelController>> listOfLists = new ArrayList<>();
        for (List<MrModelController> l : mComponents.values()) {
            listOfLists.add(l);
        }
        Collections.sort(listOfLists, new Comparator<List<MrModelController>>() {
            @Override
            public int compare(List<MrModelController> lhs, List<MrModelController> rhs) {
                if (lhs.size() < rhs.size()) {
                    return 1;
                } else if (lhs.size() > rhs.size()) {
                    return -1;
                }
                return 0;
            }
        });
        for (List<MrModelController> l : listOfLists) {
            for (MrModelController m : l) {
                if (!mSortedModels.contains(m)) {
                    mSortedModels.add(m);
                }
            }
        }
    }

    public void sort() {
        for (MrModelController model : mModels) {
            MrShaderProgram program = model.getShaderProgram();
            addComponentModelPair(program, model);

            MrTexture[] textures = model.getTextures();
            for (MrTexture texture : textures) {
                addComponentModelPair(texture, model);
            }
        }
        generateSortedList();
    }

    public List<MrModelController> getSortedModels() {
        return mSortedModels;
    }
}
