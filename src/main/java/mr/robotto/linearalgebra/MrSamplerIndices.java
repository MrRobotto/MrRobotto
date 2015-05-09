/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.linearalgebra;

import mr.robotto.commons.MrDataType;

/**
 * Created by aaron on 04/05/2015.
 */
public class MrSamplerIndices implements MrLinearAlgebraObject {

    private float[] mIndices;
    private int mCount;

    public MrSamplerIndices(int count) {
        mCount = count;
        mIndices = new float[mCount];
    }

    public void addTextureIndex(int index, int textureIndex) {
        mIndices[index] = textureIndex;
    }

    @Override
    public float[] getValues() {
        return mIndices;
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public MrDataType getDataType() {
        return MrDataType.SAMPLER2D;
    }
}
