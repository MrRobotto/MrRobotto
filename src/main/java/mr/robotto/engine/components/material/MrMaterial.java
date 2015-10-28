/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.material;

/**
 * Created by aaron on 11/03/2015.
 */
public class MrMaterial {
    private String mName;
    private MrMaterialLight mAmbient;
    private MrMaterialLight mDiffuse;
    private MrMaterialLight mSpecular;
    private MrTexture mTexture;

    public MrMaterial(String name, MrMaterialLight ambient, MrMaterialLight diffuse, MrMaterialLight specular, MrTexture texture) {
        mName = name;
        mAmbient = ambient;
        mDiffuse = diffuse;
        mSpecular = specular;
        mTexture = texture;
    }

    public String getName() {
        return mName;
    }

    public MrMaterialLight getAmbient() {
        return mAmbient;
    }

    public MrMaterialLight getDiffuse() {
        return mDiffuse;
    }

    public MrMaterialLight getSpecular() {
        return mSpecular;
    }

    public MrTexture getTexture() {
        return mTexture;
    }

    public boolean hasTexture() {
        return mTexture != null;
    }
}
