/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.physics.collision;

import mr.robotto.linearalgebra.MrQuaternion;
import mr.robotto.linearalgebra.MrVector3f;

/**
 * Created by aaron on 16/04/2015.
 */
public class MrAABB {

//    /**
//     * Axis aligned bounding box class.
//     * @class AABB
//     * @constructor
//     * @param {Object} [options]
//     * @param {Vec3}   [options.upperBound]
//     * @param {Vec3}   [options.lowerBound]
//     */
//    function AABB(options){
//        options = options || {};
//
    /**
     * The lower bound of the bounding box.
     * @property lowerBound
     * @type {Vec3}
     */
    private MrVector3f mLowerBound = new MrVector3f();
    //this.lowerBound = new Vec3();
    //if(options.lowerBound){
    //    this.lowerBound.copy(options.lowerBound);
    //}

    /**
     * The upper bound of the bounding box.
     * @property upperBound
     * @type {Vec3}
     */
    private MrVector3f mUpperBound = new MrVector3f();
    //this.upperBound = new Vec3();
    //if(options.upperBound){
    //    this.upperBound.copy(options.upperBound);
    //}
    //}


    /**
     * Set the AABB bounds from a set of points.
     * @method setFromPoints
     * @param {Array} points An array of Vec3's.
     * @param {Vec3} position
     * @param {Quaternion} quaternion
     * @param {number} skinSize
     * @return {AABB} The self object
     */
    public MrAABB setFromPoints(float[] points, MrVector3f position, MrQuaternion quaternion, float skinSize) {
        MrVector3f l = mLowerBound;
        MrVector3f u = mUpperBound;
        MrQuaternion q = quaternion;
//    AABB.prototype.setFromPoints = function(points, position, quaternion, skinSize){
//        var l = this.lowerBound,
//                u = this.upperBound,
//                q = quaternion;
//


//        // Set to the first point
//        l.copy(points[0]);
//        if(q){
//            q.vmult(l, l);
//        }
//        u.copy(l);
//
//        for(var i = 1; i<points.length; i++){
//            var p = points[i];
//
//            if(q){
//                q.vmult(p, tmp);
//                p = tmp;
//            }
//
//            if(p.x > u.x){ u.x = p.x; }
//            if(p.x < l.x){ l.x = p.x; }
//            if(p.y > u.y){ u.y = p.y; }
//            if(p.y < l.y){ l.y = p.y; }
//            if(p.z > u.z){ u.z = p.z; }
//            if(p.z < l.z){ l.z = p.z; }
//        }
//
//        // Add offset
//        if (position) {
//            position.vadd(l, l);
//            position.vadd(u, u);
//        }
//
//        if(skinSize){
//            l.x -= skinSize;
//            l.y -= skinSize;
//            l.z -= skinSize;
//            u.x += skinSize;
//            u.y += skinSize;
//            u.z += skinSize;
//        }
//
//        return this;
//    };
        return this;
    }
    /**
     * Copy bounds from an AABB to this AABB
     * @method copy
     * @param  {AABB} aabb Source to copy from
     * @return {AABB} The this object, for chainability
     */
    public MrAABB copy(MrAABB aabb) {
        mLowerBound.copyValues(aabb.mLowerBound);
        mUpperBound.copyValues(aabb.mUpperBound);
        return this;
    }
//    AABB.prototype.copy = function(aabb){
//        this.lowerBound.copy(aabb.lowerBound);
//        this.upperBound.copy(aabb.upperBound);
//        return this;
//    };
//
///**
// * Clone an AABB
// * @method clone
// */
//    AABB.prototype.clone = function(){
//        return new AABB().copy(this);
//    };
//
///**
// * Extend this AABB so that it covers the given AABB too.
// * @method extend
// * @param  {AABB} aabb
// */
//    AABB.prototype.extend = function(aabb){
//        this.lowerBound.x = Math.min(this.lowerBound.x, aabb.lowerBound.x);
//        this.upperBound.x = Math.max(this.upperBound.x, aabb.upperBound.x);
//        this.lowerBound.y = Math.min(this.lowerBound.y, aabb.lowerBound.y);
//        this.upperBound.y = Math.max(this.upperBound.y, aabb.upperBound.y);
//        this.lowerBound.z = Math.min(this.lowerBound.z, aabb.lowerBound.z);
//        this.upperBound.z = Math.max(this.upperBound.z, aabb.upperBound.z);
//    };
//
    /**
     * Returns true if the given AABB overlaps this AABB.
     * @method overlaps
     * @param  {AABB} aabb
     * @return {Boolean}
     */
    public boolean overlaps(MrAABB aabb) {
        MrVector3f l1 = mLowerBound;
        MrVector3f u1 = mUpperBound;
        MrVector3f l2 = aabb.mLowerBound;
        MrVector3f u2 = aabb.mUpperBound;

//    AABB.prototype.overlaps = function(aabb){
//        var l1 = this.lowerBound,
//                u1 = this.upperBound,
//                l2 = aabb.lowerBound,
//                u2 = aabb.upperBound;
//
//        //      l2        u2
//        //      |---------|
//        // |--------|
//        // l1       u1

        boolean overlapsX = ((l2.x <= u1.x && u1.x <= u2.x) || (l1.x <= u2.x && u2.x <= u1.x));
        boolean overlapsY = ((l2.y <= u1.y && u1.y <= u2.y) || (l1.y <= u2.y && u2.y <= u1.y));
        boolean overlapsZ = ((l2.z <= u1.z && u1.z <= u2.z) || (l1.z <= u2.z && u2.z <= u1.z));

        return overlapsX && overlapsY && overlapsZ;
    }

    public float volume() {
        MrVector3f l = mLowerBound;
        MrVector3f u = mUpperBound;
        return (u.x - l.x) * (u.y - l.y) * (u.z - l.z);
    }
//// Mostly for debugging
//    AABB.prototype.volume = function(){
//        var l = this.lowerBound,
//                u = this.upperBound;
//        return (u.x - l.x) * (u.y - l.y) * (u.z - l.z);
//    };
//

///**
// * Returns true if the given AABB is fully contained in this AABB.
// * @method contains
// * @param {AABB} aabb
// * @return {Boolean}
// */
//    AABB.prototype.contains = function(aabb){
//        var l1 = this.lowerBound,
//                u1 = this.upperBound,
//                l2 = aabb.lowerBound,
//                u2 = aabb.upperBound;
//
//        //      l2        u2
//        //      |---------|
//        // |---------------|
//        // l1              u1
//
//        return (
//                (l1.x <= l2.x && u1.x >= u2.x) &&
//                        (l1.y <= l2.y && u1.y >= u2.y) &&
//                        (l1.z <= l2.z && u1.z >= u2.z)
//        );
//    };
//
///**
// * @method getCorners
// * @param {Vec3} a
// * @param {Vec3} b
// * @param {Vec3} c
// * @param {Vec3} d
// * @param {Vec3} e
// * @param {Vec3} f
// * @param {Vec3} g
// * @param {Vec3} h
// */
//    AABB.prototype.getCorners = function(a, b, c, d, e, f, g, h){
//        var l = this.lowerBound,
//                u = this.upperBound;
//
//        a.copy(l);
//        b.set( u.x, l.y, l.z );
//        c.set( u.x, u.y, l.z );
//        d.set( l.x, u.y, u.z );
//        e.set( u.x, l.y, l.z );
//        f.set( l.x, u.y, l.z );
//        g.set( l.x, l.y, u.z );
//        h.copy(u);
//    };
//
//    var transformIntoFrame_corners = [
//            new Vec3(),
//    new Vec3(),
//    new Vec3(),
//    new Vec3(),
//    new Vec3(),
//    new Vec3(),
//    new Vec3(),
//    new Vec3()
//    ];
//
///**
// * Get the representation of an AABB in another frame.
// * @method toLocalFrame
// * @param  {Transform} frame
// * @param  {AABB} target
// * @return {AABB} The "target" AABB object.
// */
//    AABB.prototype.toLocalFrame = function(frame, target){
//
//        var corners = transformIntoFrame_corners;
//        var a = corners[0];
//        var b = corners[1];
//        var c = corners[2];
//        var d = corners[3];
//        var e = corners[4];
//        var f = corners[5];
//        var g = corners[6];
//        var h = corners[7];
//
//        // Get corners in current frame
//        this.getCorners(a, b, c, d, e, f, g, h);
//
//        // Transform them to new local frame
//        for(var i=0; i !== 8; i++){
//            var corner = corners[i];
//            frame.pointToLocal(corner, corner);
//        }
//
//        return target.setFromPoints(corners);
//    };
//
///**
// * Get the representation of an AABB in the global frame.
// * @method toWorldFrame
// * @param  {Transform} frame
// * @param  {AABB} target
// * @return {AABB} The "target" AABB object.
// */
//    AABB.prototype.toWorldFrame = function(frame, target){
//
//        var corners = transformIntoFrame_corners;
//        var a = corners[0];
//        var b = corners[1];
//        var c = corners[2];
//        var d = corners[3];
//        var e = corners[4];
//        var f = corners[5];
//        var g = corners[6];
//        var h = corners[7];
//
//        // Get corners in current frame
//        this.getCorners(a, b, c, d, e, f, g, h);
//
//        // Transform them to new local frame
//        for(var i=0; i !== 8; i++){
//            var corner = corners[i];
//            frame.pointToWorld(corner, corner);
//        }
//
//        return target.setFromPoints(corners);
//    };

}