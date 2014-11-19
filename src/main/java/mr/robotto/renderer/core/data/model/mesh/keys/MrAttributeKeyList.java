/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.model.mesh.keys;

import java.util.ArrayList;
import java.util.Iterator;

//TODO: Change the arraylist for a hashmap
public class MrAttributeKeyList implements Iterable<MrAttributeKey> {
    private ArrayList<MrAttributeKey> mKeys;

    public MrAttributeKeyList() {
        init();
    }

    private void init() {
        mKeys = new ArrayList<MrAttributeKey>();
    }

    public MrAttributeKey getAttributeKeyAt(int i) {
        return mKeys.get(i);
    }

    public int getNumBufferKeys() {
        return mKeys.size();
    }

    public void addAttributeKey(MrAttributeKey key) {
        mKeys.add(key);
    }

    @Override
    public Iterator<MrAttributeKey> iterator() {
        return mKeys.iterator();
    }
}
