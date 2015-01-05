/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Aarón on 05/01/2015.
 */
public class MrUniformManager implements Iterable<MrUniformGenerator> {

    private SortedSet<MrUniformGenerator> mGenerators;
    //private Map<String, MrUniformGenerator> mObjectsMap;


    public MrUniformManager() {
        init();
    }

    private void init() {
        //mObjectsMap = new HashMap<String, MrUniformGenerator>();
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

    public boolean addUniformGenerator(MrUniformGenerator generator) {
        //mObjectsMap.put(generator.getObjectName(), generator);
        return mGenerators.add(generator);
    }

    @Override
    public Iterator<MrUniformGenerator> iterator() {
        return mGenerators.iterator();
    }
}
