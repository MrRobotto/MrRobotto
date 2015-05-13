/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.uniformkey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import mr.robotto.collections.MrHashMap;
import mr.robotto.collections.core.MrMap;
import mr.robotto.collections.core.MrMapFunction;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;

/**
 * Created by Aarón on 23/11/2014.
 */
public class MrUniformKeyMap extends MrHashMap<String, MrUniformKey> {

    private HashMap<String, List<MrUniformKey>> mKeysByGenerator;

    public MrUniformKeyMap() {
        super(getMapFunction());
        mKeysByGenerator = new HashMap<>();
    }

    private static MrMapFunction<String, MrUniformKey> getMapFunction() {
        return new MrMapFunction<String, MrUniformKey>() {
            @Override
            public String getKeyOf(MrUniformKey mrUniformKey) {
                return mrUniformKey.getUniformType();
            }
        };
    }

    @Override
    public boolean add(MrUniformKey uniformKey) {
        if (!mKeysByGenerator.containsKey(uniformKey.getGeneratorName())) {
            mKeysByGenerator.put(uniformKey.getGeneratorName(), new ArrayList<MrUniformKey>());
        }
        mKeysByGenerator.get(uniformKey.getGeneratorName()).add(uniformKey);
        boolean r = super.add(uniformKey);
        Collections.sort(mKeysByGenerator.get(uniformKey.getGeneratorName()));
        return r;
    }

    @Override
    public boolean addAll(MrMap<String, MrUniformKey> container) {
        boolean added = true;
        for (MrUniformKey uniformKey : container) {
            added &= add(uniformKey);
        }
        return added;
    }

    @Override
    public boolean remove(MrUniformKey uniformKey) {
        List<MrUniformKey> uniformKeys = mKeysByGenerator.get(uniformKey.getGeneratorName());
        if (uniformKeys == null) {
            return false;
        }
        uniformKeys.remove(uniformKey);
        return super.remove(uniformKey);
    }

    @Override
    public boolean removeByKey(String s) {
        MrUniformKey key = findByKey(s);
        return remove(key);
    }

    @Override
    public void clear() {
        mKeysByGenerator.clear();
        super.clear();
    }

    public boolean containsGeneratorNameKey(String generatorName) {
        return mKeysByGenerator.containsKey(generatorName);
    }

    public List<MrUniformKey> findByGeneratorNameKey(String generatorName) {
        return mKeysByGenerator.get(generatorName);
    }

    public Set<String> getGeneratorNameKeys() {
        return mKeysByGenerator.keySet();
    }
}
