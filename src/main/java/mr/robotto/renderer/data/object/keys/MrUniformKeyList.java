/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.data.object.keys;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import mr.robotto.renderer.shaders.MrUniformType;
import mr.robotto.renderer.linearalgebra.MrLinearAlgebraObject;

//TODO: This should be extend MrContainer
public class MrUniformKeyList implements Iterable<MrUniformKey> {

    private HashMap<MrUniformType, MrUniformKey> uniforms;

    public MrUniformKeyList() {
        init();
    }

    private void init() {
        uniforms = new HashMap<MrUniformType, MrUniformKey>();
    }


    public void addKey(MrUniformType uniformType) {
        uniforms.put(uniformType, new MrUniformKey(uniformType));
    }

    public boolean hasKey(MrUniformType key) {
        return uniforms.containsKey(key);
    }

    public MrUniformKey getUniformKey(MrUniformType type) {
        return uniforms.get(type);
    }



    public void setUniformKeyValues(MrUniformType type, MrLinearAlgebraObject value) {
        if (hasKey(type)) {
            MrUniformKey key = getUniformKey(type);
            key.setValue(value);
        }
    }

    /**
     * Puts all the elements of list in this
     * @param list
     */
    public void mergeWith(MrUniformKeyList list) {
        for (MrUniformKey uniformKey : list) {
            setUniformKeyValues(uniformKey.getUniformType(), uniformKey.getValue());
        }
    }

    public Collection<MrUniformType> keys() {
        return uniforms.keySet();
    }

    @Override
    public Iterator<MrUniformKey> iterator() {
        return uniforms.values().iterator();
    }
}
