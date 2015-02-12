/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller.uniformgenerator;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import mr.robotto.collections.MrMap;
import mr.robotto.collections.MrMapContainer;
import mr.robotto.collections.core.MrMapFunction;

/**
 * Created by Aarón on 05/01/2015.
 */
public class MrUniformGeneratorContainer extends MrMapContainer<String, MrUniformGenerator> {

    private SortedSet<MrUniformGenerator> mGenerators;

    //TODO: Remove init2
    public MrUniformGeneratorContainer() {
        super(getMapFunction());
        init2();
        init();
    }

    private static MrMapFunction<String, MrUniformGenerator> getMapFunction() {
        return new MrMapFunction<String, MrUniformGenerator>() {
            @Override
            public String getKeyOf(MrUniformGenerator generator) {
                return generator.getUniformType();
            }
        };
    }

    private void init() {
        mGenerators = new TreeSet<MrUniformGenerator>(new Comparator<MrUniformGenerator>() {
            @Override
            public int compare(MrUniformGenerator lhs, MrUniformGenerator rhs) {
                int lp = lhs.getPriority();
                int rp = rhs.getPriority();
                if (lp == rp) {
                    return 0;
                }
                return (lp < rp) ? -1 : 1;
            }
        });
    }

    //TODO: Cuando añades no se sustituye en el generators...
    @Override
    public boolean addAll(MrMap<String, MrUniformGenerator> container) {
        boolean added = true;
        for (MrUniformGenerator generator : container) {
            added &= mGenerators.add(generator);
            super.add(generator);
        }
        return added;
    }

    @Override
    public boolean add(MrUniformGenerator generator) {
        mGenerators.add(generator);
        return super.add(generator);
    }

    @Override
    public void clear() {
        mGenerators.clear();
        super.clear();
    }

    @Override
    public boolean remove(MrUniformGenerator generator) {
        mGenerators.remove(generator);
        return super.remove(generator);
    }

    @Override
    public boolean removeByKey(String s) {
        mGenerators.remove(findByKey(s));
        return super.removeByKey(s);
    }

    @Override
    public Iterator<MrUniformGenerator> iterator() {
        return mGenerators.iterator();
    }
}
