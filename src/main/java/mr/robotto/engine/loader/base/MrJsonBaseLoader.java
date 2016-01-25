/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.loader.base;

import org.json.JSONException;
import org.json.JSONObject;


public abstract class MrJsonBaseLoader<T> extends MrBaseLoader {
    //TODO: Cambiar el jsonobject al parse y pasar del constructor y convertir esto en una interfaz
    //TODO: Cambiar en los parser a la versión opt
    protected JSONObject mRoot;

    public MrJsonBaseLoader(JSONObject obj)
    {
        mRoot = obj;
    }

    public abstract T parse() throws JSONException;
}
