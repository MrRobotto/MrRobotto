/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller;

import java.util.Iterator;

import mr.robotto.context.MrSceneObjectsTree;
import mr.robotto.core.MrUniformGenerator;
import mr.robotto.core.MrUniformGeneratorContainer;
import mr.robotto.core.data.model.MrModelData;
import mr.robotto.core.renderer.MrObjectRender;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.linearalgebra.MrMatrix4f;

public class MrModel extends MrObject {
    public MrModel(MrModelData data, MrObjectRender render) {
        super(data, render);
    }

    private static MrUniformGenerator generateModelMatrix(MrModel model) {
        return new MrUniformGenerator("ModelMatrix", MrUniformGenerator.OBJECT_LEVEL) {
            @Override
            public MrLinearAlgebraObject generateUniform(MrSceneObjectsTree tree, MrObject object) {
                MrMatrix4f m = new MrMatrix4f();
                Iterator<MrObject> it = tree.parentTraversal(object);
                while (it.hasNext()) {
                    MrMatrix4f.ops.mult(m, it.next().getTransform().getAsMatrix(), m);
                }
                return m;
            }
        };
    }

    public MrMatrix4f getModelMatrix() {
        return getData().getTransform().getAsMatrix();
    }

    @Override
    public void initializeUniforms(MrUniformGeneratorContainer uniformGenerators) {
        super.initializeUniforms(uniformGenerators);
        uniformGenerators.add(generateModelMatrix(this));
    }
}
