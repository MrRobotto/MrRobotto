/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class MrAbstractLoader<T>
{
    //TODO: Cambiar en los parser a la versión opt
    protected JSONObject mRoot;

    public MrAbstractLoader(JSONObject obj)
    {
        mRoot = obj;
    }

    public abstract T parse() throws JSONException;
}
