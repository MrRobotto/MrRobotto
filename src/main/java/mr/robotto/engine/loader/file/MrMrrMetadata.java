/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.loader.file;

/**
 * Created by aaron on 03/11/2015.
 */
class MrMrrMetadata {
    private String mVersion;
    private boolean mHasHierarchy;

    public MrMrrMetadata(String version, boolean hasHierarchy) {
        mVersion = version;
        mHasHierarchy = hasHierarchy;
    }

    public String getVersion() {
        return mVersion;
    }

    public boolean hasHierarchy() {
        return mHasHierarchy;
    }
}
