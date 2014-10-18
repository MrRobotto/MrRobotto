/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.exceptions;

public class MrException extends Exception
{
    private MrException(String msg)
    {
        super(msg);
    }

    public static void throwTypeMismatchException() throws MrException
    {
        throw new MrException("The targetBuffer doesn't match");
    }


}
