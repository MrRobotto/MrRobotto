/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.physics.shapes;

/**
 * Created by aaron on 16/04/2015.
 */

import mr.robotto.linearalgebra.MrVector3f;

/**
 * Base class for shapes
 * @class Shape
 * @constructor
 * @author schteppe
 * @todo Should have a mechanism for caching bounding sphere radius instead of calculating it each time
 */
public abstract class MrShape {
    /**
     * Identifyer of the Shape.
     * @property {number} id
     */
    private static int sIdCounter = 0;
    private int mId = sIdCounter++;

    /**
     * The type of this shape. Must be set to an int > 0 by subclasses.
     * @property type
     * @type {Number}
     * @see Shape.types
     */
    protected ShapeType mType = null;

    /**
     * The local bounding sphere radius of this shape.
     * @property {Number} boundingSphereRadius
     */
    private float mBoundingSphereRadius = 0.0f;

    /**
     * Whether to produce contact forces when in contact with other bodies. Note that contacts will be generated, but they will be disabled.
     * @property {boolean} collisionResponse
     */
    private boolean mCollisionResponse = true;

//
//        /**
//         * @property {Material} material
//         */
//        this.material = null;
//        }
//        Shape.prototype.constructor = Shape;
//
    /**
     * Computes the bounding sphere radius. The result is stored in the property .boundingSphereRadius
     * @method updateBoundingSphereRadius
     * @return {Number}
     */
    public abstract float updateBoundingSphereRadius();

//        Shape.prototype.updateBoundingSphereRadius = function(){
//        throw "computeBoundingSphereRadius() not implemented for shape type "+this.type;
//        };

    /**
     * Get the volume of this shape
     * @method volume
     * @return {Number}
     */
    public abstract float volume();
//        Shape.prototype.volume = function(){
//        throw "volume() not implemented for shape type "+this.type;
//        };

    /**
     * Calculates the inertia in the local frame for this shape.
     * @method calculateLocalInertia
     * @return {Vec3}
     * @see http://en.wikipedia.org/wiki/List_of_moments_of_inertia
     */
    public abstract MrVector3f calculateLocalInertia(float mass, MrVector3f target);
//        Shape.prototype.calculateLocalInertia = function(mass,target){
//        throw "calculateLocalInertia() not implemented for shape type "+this.type;
//        };

    public enum ShapeType {
        SPHERE,
        PLANE,
        BOX,
        COMPOUND,
        CONVEXPOLYHEDRON,
        HEIGHTFIELD,
        PARTICLE,
        CYLINDER,
        TRIMESH
    }
///**
// * The available shape types.
// * @static
// * @property types
// * @type {Object}
// */
//        Shape.types = {
//        SPHERE:1,
//        PLANE:2,
//        BOX:4,
//        COMPOUND:8,
//        CONVEXPOLYHEDRON:16,
//        HEIGHTFIELD:32,
//        PARTICLE:64,
//        CYLINDER:128,
//        TRIMESH:256
//        };
}