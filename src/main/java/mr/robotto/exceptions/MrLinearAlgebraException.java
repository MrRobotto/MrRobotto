/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.exceptions;

public class MrLinearAlgebraException extends RuntimeException
{
    public MrLinearAlgebraException(String msg)
    {
        super(msg);
    }

    public static void throwMissMatchVectorDimensionException()
    {
        throw new MrLinearAlgebraException("The dimension doesn't match");
    }

    public static void throwInvalidVectorDimensionException()
    {
        throw new MrLinearAlgebraException("The dimension of vector is invalid");
    }

    public static void throwInvalidHomogeneusCoordinateException()
    {
        throw new MrLinearAlgebraException("A Vec4 with W=0 can't be converted to Vec3");
    }
}
