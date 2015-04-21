/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.uniformkey;

import java.util.Iterator;
import java.util.Set;

import mr.robotto.collections.MrHashMap;
import mr.robotto.collections.core.MrMapFunction;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;

/**
 * Created by Aarón on 23/11/2014.
 */
public class MrUniformKeyMap extends MrHashMap<String, MrUniformKey> {

    private int mVisibility;
    private MrUniformKeyMapView mView;

    public MrUniformKeyMap() {
        super(getMapFunction());
        mView = new MrUniformKeyMapView(this);
    }

    private static MrMapFunction<String, MrUniformKey> getMapFunction() {
        return new MrMapFunction<String, MrUniformKey>() {
            @Override
            public String getKeyOf(MrUniformKey mrUniformKey) {
                return mrUniformKey.getUniformType();
            }
        };
    }

    public MrUniformKeyMapView getView() {
        return mView;
    }

    public int getVisibility() {
        return mVisibility;
    }

    public void setVisibility(int visibility) {
        mVisibility = visibility;
    }

    /**
     * Puts all the elements of list in this
     *
     * @param list
     */
    public void mergeWith(MrUniformKeyMap list) {
        for (MrUniformKey uniformKey : list) {
            add(uniformKey);
        }
    }

    /**
     * Created by aaron on 21/04/2015.
     */
    public static class MrUniformKeyMapView {
        private MrUniformKeyMap mUniforms;

        private MrUniformKeyMapView(MrUniformKeyMap uniforms) {
            mUniforms = uniforms;
        }

        //TODO: The generator can be null
        public MrLinearAlgebraObject findByKey(String s) {
            MrUniformKey key = mUniforms.findByKey(s);
            int priority = key.getLevel();
            if (priority <= mUniforms.getVisibility()) {
                return key.getValue();
            } else {
                return null;
            }
        }

        public boolean containsKey(String s) {
            MrUniformKey generator = mUniforms.findByKey(s);
            return generator != null && generator.getLevel() <= mUniforms.getVisibility();
        }

        //TODO: Completar
        public int size() {
            throw new UnsupportedOperationException();
        }

        //TODO: Completar
        public Set<String> getKeys() {
            throw new UnsupportedOperationException();
        }

        //TODO: Completar
        public Iterator<MrUniformKey> iterator() {
            throw new UnsupportedOperationException();
        }

        private class MrUniformKeyMapIterator implements Iterator<MrLinearAlgebraObject> {

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public MrLinearAlgebraObject next() {
                return null;
            }

            @Override
            public void remove() {

            }
        }
    }
}
