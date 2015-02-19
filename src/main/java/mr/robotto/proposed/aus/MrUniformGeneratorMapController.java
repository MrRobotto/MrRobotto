/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed.aus;

import java.util.Iterator;
import java.util.Set;

import mr.robotto.linearalgebra.MrLinearAlgebraObject;

/**
 * Created by aaron on 14/02/2015.
 */
public class MrUniformGeneratorMapController {

    private MrUniformGeneratorMap mUniformGenerators;
    private MrUniformGeneratorMapView mView;
    private int mVisibility;

    public MrUniformGeneratorMapController(MrUniformGeneratorMap uniforms) {
        mUniformGenerators = uniforms;
        mView = new MrUniformGeneratorMapView(this);
        mVisibility = 0;
    }

    public MrUniformGeneratorMapView getView() {
        return mView;
    }

    public MrUniformGeneratorMap getUniformGenerators() {
        return mUniformGenerators;
    }

    public void setVisibility(int visibility) {
        mVisibility = visibility;
    }

    //TODO: The generator can be null
    public MrLinearAlgebraObject findByKey(String s) {
        MrUniformGenerator generator = mUniformGenerators.findByKey(s);
        int priority = generator.getPriority();
        if (priority <= mVisibility) {
            return generator.getUniformValue();
        } else {
            return null;
        }
    }

    public boolean containsKey(String s) {
        MrUniformGenerator generator = mUniformGenerators.findByKey(s);
        return generator != null && generator.getPriority() <= mVisibility;
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
    public Iterator<MrUniformGenerator> iterator() {
        throw new UnsupportedOperationException();
    }

    private class MrUniformGeneratorsIterator implements Iterator<MrLinearAlgebraObject> {

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
