/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.physics.body;

import mr.robotto.linearalgebra.MrQuaternion;
import mr.robotto.linearalgebra.MrVector3f;
import mr.robotto.physics.world.MrWorld;

/**
 * Created by aaron on 16/04/2015.
 */
public class MrBody {
    /**
     * Base class for all body types.
     * @class Body
     * @constructor
     * @extends EventTarget
     * @param {object} [options]
     * @param {Vec3} [options.position]
     * @param {Vec3} [options.velocity]
     * @param {Vec3} [options.angularVelocity]
     * @param {Quaternion} [options.quaternion]
     * @param {number} [options.mass]
     * @param {Material} [options.material]
     * @param {number} [options.type]
     * @param {number} [options.linearDamping=0.01]
     * @param {number} [options.angularDamping=0.01]
     * @param {boolean} [options.allowSleep=true]
     * @param {number} [options.sleepSpeedLimit=0.1]
     * @param {number} [options.sleepTimeLimit=1]
     * @param {number} [options.collisionFilterGroup=1]
     * @param {number} [options.collisionFilterMask=1]
     * @param {boolean} [options.fixedRotation=false]
     * @param {Body} [options.shape]
     * @example
     *     var body = new Body({
     *         mass: 1
     *     });
     *     var shape = new Sphere(1);
     *     body.addShape(shape);
     *     world.add(body);
     */
//    function Body(options){
//        options = options || {};
//
//        EventTarget.apply(this);

    private static int sIdCounter = 0;
    private int mId = sIdCounter++;

    /**
     * Reference to the world the body is living in
     * @property world
     * @type {World}
     */
    private MrWorld mWorld = null;

//    /**
//     * Callback function that is used BEFORE stepping the system. Use it to apply forces, for example. Inside the function, "this" will refer to this Body object.
//     * @property preStep
//     * @type {Function}
//     * @deprecated Use World events instead
//     */
//    this.preStep = null;
//
//    /**
//     * Callback function that is used AFTER stepping the system. Inside the function, "this" will refer to this Body object.
//     * @property postStep
//     * @type {Function}
//     * @deprecated Use World events instead
//     */
//    this.postStep = null;

    private MrVector3f mVlambda = new MrVector3f();

    /**
     * @property {Number} collisionFilterGroup
     */
    private int mCollisionFilterGroup = 1;
    //this.collisionFilterGroup = typeof(options.collisionFilterGroup) === 'number' ? options.collisionFilterGroup : 1;

    /**
     * @property {Number} collisionFilterMask
     */
    private int mCollisionFilterMask = 1;
    //this.collisionFilterMask = typeof(options.collisionFilterMask) === 'number' ? options.collisionFilterMask : 1;

    /**
     * Whether to produce contact forces when in contact with other bodies. Note that contacts will be generated, but they will be disabled.
     * @property {Number} collisionResponse
     */
    private boolean mCollisionResponse = true;

    /**
     * @property position
     * @type {Vec3}
     */
    private MrVector3f mPosition = new MrVector3f();

//    if(options.position){
//        this.position.copy(options.position);
//    }

    /**
     * @property {Vec3} previousPosition
     */
    private MrVector3f mPreviousPosition = new MrVector3f();

    /**
     * Initial position of the body
     * @property initPosition
     * @type {Vec3}
     */
    private MrVector3f mInitPosition = new MrVector3f();

    /**
     * @property velocity
     * @type {Vec3}
     */
    private MrVector3f mVelocity = new MrVector3f();
    //this.velocity = new Vec3();

//    if(options.velocity){
//        this.velocity.copy(options.velocity);
//    }

    /**
     * @property initVelocity
     * @type {Vec3}
     */
    private MrVector3f mInitVelocity = new MrVector3f();

    /**
     * Linear force on the body
     * @property force
     * @type {Vec3}
     */

    private MrVector3f mForce = new MrVector3f();

    //var mass = typeof(options.mass) === 'number' ? options.mass : 0;

    /**
     * @property mass
     * @type {Number}
     * @default 0
     */
    private float mMass = 0;
    //this.mass = mass;

    /**
     * @property invMass
     * @type {Number}
     */
    private float mInvMass = mMass > 0 ? 1.0f/mMass : 0;
    //this.invMass = mass > 0 ? 1.0 / mass : 0;

//    /**
//     * @property material
//     * @type {Material}
//     */
//    this.material = options.material || null;

    /**
     * @property linearDamping
     * @type {Number}
     */
    private float mLinearDamping = 0.01f;
    //this.linearDamping = typeof(options.linearDamping) === 'number' ? options.linearDamping : 0.01;

    /**
     * One of: Body.DYNAMIC, Body.STATIC and Body.KINEMATIC.
     * @property type
     * @type {Number}
     */
    private BodyType mType = BodyType.DYNAMIC;
    //this.type = (mass <= 0.0 ? Body.STATIC : Body.DYNAMIC);
    //if(typeof(options.type) === typeof(Body.STATIC)){
    //    this.type = options.type;
    //}

    /**
     * If true, the body will automatically fall to sleep.
     * @property allowSleep
     * @type {Boolean}
     * @default true
     */
    private boolean mAllowSleep = true;
     //this.allowSleep = typeof(options.allowSleep) !== 'undefined' ? options.allowSleep : true;

    /**
     * Current sleep state.
     * @property sleepState
     * @type {Number}
     */
    private BodyState mSleepState;
    //this.sleepState = 0;

    /**
     * If the speed (the norm of the velocity) is smaller than this value, the body is considered sleepy.
     * @property sleepSpeedLimit
     * @type {Number}
     * @default 0.1
     */
    private float mSleepSpeedLimit = 0.1f;
    //this.sleepSpeedLimit = typeof(options.sleepSpeedLimit) !== 'undefined' ? options.sleepSpeedLimit : 0.1;

    /**
     * If the body has been sleepy for this sleepTimeLimit seconds, it is considered sleeping.
     * @property sleepTimeLimit
     * @type {Number}
     * @default 1
     */
    private float mSleepTimeLimit = 1;
    //this.sleepTimeLimit = typeof(options.sleepTimeLimit) !== 'undefined' ? options.sleepTimeLimit : 1;

//    this.timeLastSleepy = 0;
//
//    this._wakeUpAfterNarrowphase = false;

//
    /**
     * Rotational force on the body, around center of mass
     * @property {Vec3} torque
     */
    private MrVector3f mTorque = new MrVector3f();
    //this.torque = new Vec3();

    /**
     * Orientation of the body
     * @property quaternion
     * @type {Quaternion}
     */
    private MrQuaternion mQuaternion = new MrQuaternion();
    //this.quaternion = new Quaternion();
//
//        if(options.quaternion){
//            this.quaternion.copy(options.quaternion);
//        }
//
//        /**
//         * @property initQuaternion
//         * @type {Quaternion}
//         */
//        this.initQuaternion = new Quaternion();
//
    /**
     * @property angularVelocity
     * @type {Vec3}
     */
    private MrVector3f mAngularVelocity = new MrVector3f();
    //this.angularVelocity = new Vec3();
//
//        if(options.angularVelocity){
//            this.angularVelocity.copy(options.angularVelocity);
//        }
//
    /**
     * @property initAngularVelocity
     * @type {Vec3}
     */
    private MrVector3f mInitAngularVelocity = new MrVector3f();
    //this.initAngularVelocity = new Vec3();

    private MrVector3f mInterpolatedPosition = new MrVector3f();
    private MrQuaternion mInterpolatedQuaternion = new MrQuaternion();
    //this.interpolatedPosition = new Vec3();
    //this.interpolatedQuaternion = new Quaternion();
//
//        /**
//         * @property shapes
//         * @type {array}
//         */
//        this.shapes = [];
//
//        /**
//         * @property shapeOffsets
//         * @type {array}
//         */
//        this.shapeOffsets = [];
//
//        /**
//         * @property shapeOrientations
//         * @type {array}
//         */
//        this.shapeOrientations = [];
//
    /**
     * @property inertia
     * @type {Vec3}
     */
    private MrVector3f mInertia = new MrVector3f();
    //this.inertia = new Vec3();

    /**
     * @property {Vec3} invInertia
     */
    private MrVector3f mInvInertia = new MrVector3f();
    //this.invInertia = new Vec3();
//
//        /**
//         * @property {Mat3} invInertiaWorld
//         */
//        this.invInertiaWorld = new Mat3();
//
    private float mInvMassSolve = 0;
    //this.invMassSolve = 0;

    /**
     * @property {Vec3} invInertiaSolve
     */
    private MrVector3f mInvInertiaSolve = new MrVector3f();
    //this.invInertiaSolve = new Vec3();
//
//        /**
//         * @property {Mat3} invInertiaWorldSolve
//         */
//        this.invInertiaWorldSolve = new Mat3();
//
    /**
     * Set to true if you don't want the body to rotate. Make sure to run .updateMassProperties() after changing this.
     * @property {Boolean} fixedRotation
     * @default false
     */
    private boolean mFixedRotation = false;
    //this.fixedRotation = typeof(options.fixedRotation) !== "undefined" ? options.fixedRotation : false;

    /**
     * @property {Number} angularDamping
     */
    private float mAngularDamping = 0.01f;
    //this.angularDamping = typeof(options.angularDamping) !== 'undefined' ? options.angularDamping : 0.01;

//        /**
//         * @property aabb
//         * @type {AABB}
//         */
//        this.aabb = new AABB();
//
    /**
     * Indicates if the AABB needs to be updated before use.
     * @property aabbNeedsUpdate
     * @type {Boolean}
     */
    private boolean mAabbNeedsUpdate = true;
    //this.aabbNeedsUpdate = true;

    private MrVector3f mWlambda = new MrVector3f();
    //this.wlambda = new Vec3();
//
//        if(options.shape){
//            this.addShape(options.shape);
//        }
//
//        this.updateMassProperties();
//    }
//    Body.prototype = new EventTarget();
//    Body.prototype.constructor = Body;
//
    public enum BodyType {
        /**
         * A dynamic body is fully simulated. Can be moved manually by the user, but normally they move according to forces. A dynamic body can collide with all body types. A dynamic body always has finite, non-zero mass.
         * @static
         * @property DYNAMIC
         * @type {Number}
         */
        DYNAMIC,
        /**
         * A static body does not move during simulation and behaves as if it has infinite mass. Static bodies can be moved manually by setting the position of the body. The velocity of a static body is always zero. Static bodies do not collide with other static or kinematic bodies.
         * @static
         * @property STATIC
         * @type {Number}
         */
        STATIC,
        /**
         * A kinematic body moves under simulation according to its velocity. They do not respond to forces. They can be moved manually, but normally a kinematic body is moved by setting its velocity. A kinematic body behaves as if it has infinite mass. Kinematic bodies do not collide with other static or kinematic bodies.
         * @static
         * @property KINEMATIC
         * @type {Number}
         */
        KINEMATIC
    }

    public enum BodyState {
        /**
         * @static
         * @property AWAKE
         * @type {number}
         */
        AWAKE,
        /**
         * @static
         * @property SLEEPY
         * @type {number}
         */
        SLEEPY,
        /**
         * @static
         * @property SLEEPING
         * @type {number}
         */
        SLEEPING
    }


//
//    Body.idCounter = 0;
//
///**
// * Wake the body up.
// * @method wakeUp
// */
//    Body.prototype.wakeUp = function(){
//        var s = this.sleepState;
//        this.sleepState = 0;
//        if(s === Body.SLEEPING){
//            this.dispatchEvent({type:"wakeup"});
//        }
//    };
//
///**
// * Force body sleep
// * @method sleep
// */
//    Body.prototype.sleep = function(){
//        this.sleepState = Body.SLEEPING;
//        this.velocity.set(0,0,0);
//        this.angularVelocity.set(0,0,0);
//    };
//
//    Body.sleepyEvent = {
//        type: "sleepy"
//    };
//
//    Body.sleepEvent = {
//        type: "sleep"
//    };
//
///**
// * Called every timestep to update internal sleep timer and change sleep state if needed.
// * @method sleepTick
// * @param {Number} time The world time in seconds
// */
//    Body.prototype.sleepTick = function(time){
//        if(this.allowSleep){
//            var sleepState = this.sleepState;
//            var speedSquared = this.velocity.norm2() + this.angularVelocity.norm2();
//            var speedLimitSquared = Math.pow(this.sleepSpeedLimit,2);
//            if(sleepState===Body.AWAKE && speedSquared < speedLimitSquared){
//                this.sleepState = Body.SLEEPY; // Sleepy
//                this.timeLastSleepy = time;
//                this.dispatchEvent(Body.sleepyEvent);
//            } else if(sleepState===Body.SLEEPY && speedSquared > speedLimitSquared){
//                this.wakeUp(); // Wake up
//            } else if(sleepState===Body.SLEEPY && (time - this.timeLastSleepy ) > this.sleepTimeLimit){
//                this.sleep(); // Sleeping
//                this.dispatchEvent(Body.sleepEvent);
//            }
//        }
//    };
//
    /**
     * If the body is sleeping, it should be immovable / have infinite mass during solve. We solve it by having a separate "solve mass".
     * @method updateSolveMassProperties
     */
    public void updateSolveMassProperties() {
        if (mSleepState == BodyState.SLEEPING || mType == BodyType.KINEMATIC) {
            mInvMassSolve = 0;
            mInvInertiaSolve.setValues(0,0,0);
//            this.invInertiaWorldSolve.setZero();
        } else {
            mInvMassSolve = mInvMass;
            mInvInertiaSolve.copyValues(mInvInertia);
//            this.invInertiaWorldSolve.copy(this.invInertiaWorld);
        }
    }


    /**
    * Convert a world point to local body frame.
    * @method pointToLocalFrame
    * @param  {Vec3} worldPoint
    * @param  {Vec3} result
    * @return {Vec3}
    */
    public MrVector3f pointToLocalFrame(MrVector3f worldPoint,  MrVector3f result) {
        MrVector3f res = result!=null ? result : new MrVector3f();
        MrQuaternion q = new MrQuaternion();
        MrVector3f.Operator opv3 = MrVector3f.getOperator();
        MrQuaternion.Operator opq = MrQuaternion.getOperator();
        opv3.substract(res, worldPoint, mPosition);
        //TODO: Creo que esto es solo una rotación, eliminar conjugate?
        opq.conjugate(q, mQuaternion);
        opv3.rotateVector(res, q, res);
        return res;
    }
//    Body.prototype.pointToLocalFrame = function(worldPoint,result){
//        var result = result || new Vec3();
//        worldPoint.vsub(this.position,result);
//        this.quaternion.conjugate().vmult(result,result);
//        return result;
//    };

///**
// * Convert a world vector to local body frame.
// * @method vectorToLocalFrame
// * @param  {Vec3} worldPoint
// * @param  {Vec3} result
// * @return {Vec3}
// */
//    Body.prototype.vectorToLocalFrame = function(worldVector, result){
//        var result = result || new Vec3();
//        this.quaternion.conjugate().vmult(worldVector,result);
//        return result;
//    };
//
    /**
     * Convert a local body point to world frame.
     * @method pointToWorldFrame
     * @param  {Vec3} localPoint
     * @param  {Vec3} result
     * @return {Vec3}
     */
    public MrVector3f pointToWorldFrame(MrVector3f localPoint, MrVector3f result) {
        MrVector3f res = result!=null ? result : new MrVector3f();
        MrVector3f.Operator opv3 = MrVector3f.getOperator();
        opv3.rotateVector(res, mQuaternion, localPoint);
        opv3.add(res, mPosition, res);
        return res;
    }
//        Body.prototype.pointToWorldFrame = function(localPoint,result){
//            var result = result || new Vec3();
//            this.quaternion.vmult(localPoint,result);
//            result.vadd(this.position,result);
//            return result;
//        };

///**
// * Convert a local body point to world frame.
// * @method vectorToWorldFrame
// * @param  {Vec3} localVector
// * @param  {Vec3} result
// * @return {Vec3}
// */
//    Body.prototype.vectorToWorldFrame = function(localVector, result){
//        var result = result || new Vec3();
//        this.quaternion.vmult(localVector, result);
//        return result;
//    };
//
//    var tmpVec = new Vec3();
//    var tmpQuat = new Quaternion();
//
///**
// * Add a shape to the body with a local offset and orientation.
// * @method addShape
// * @param {Shape} shape
// * @param {Vec3} offset
// * @param {Quaternion} quaternion
// * @return {Body} The body object, for chainability.
// */
//    Body.prototype.addShape = function(shape, _offset, _orientation){
//        var offset = new Vec3();
//        var orientation = new Quaternion();
//
//        if(_offset){
//            offset.copy(_offset);
//        }
//        if(_orientation){
//            orientation.copy(_orientation);
//        }
//
//        this.shapes.push(shape);
//        this.shapeOffsets.push(offset);
//        this.shapeOrientations.push(orientation);
//        this.updateMassProperties();
//        this.updateBoundingRadius();
//
//        this.aabbNeedsUpdate = true;
//
//        return this;
//    };
//
///**
// * Update the bounding radius of the body. Should be done if any of the shapes are changed.
// * @method updateBoundingRadius
// */
//    Body.prototype.updateBoundingRadius = function(){
//        var shapes = this.shapes,
//                shapeOffsets = this.shapeOffsets,
//                N = shapes.length,
//                radius = 0;
//
//        for(var i=0; i!==N; i++){
//            var shape = shapes[i];
//            shape.updateBoundingSphereRadius();
//            var offset = shapeOffsets[i].norm(),
//                    r = shape.boundingSphereRadius;
//            if(offset + r > radius){
//                radius = offset + r;
//            }
//        }
//
//        this.boundingRadius = radius;
//    };
//
//    var computeAABB_shapeAABB = new AABB();
//
///**
// * Updates the .aabb
// * @method computeAABB
// * @todo rename to updateAABB()
// */
//    Body.prototype.computeAABB = function(){
//        var shapes = this.shapes,
//                shapeOffsets = this.shapeOffsets,
//                shapeOrientations = this.shapeOrientations,
//                N = shapes.length,
//                offset = tmpVec,
//                orientation = tmpQuat,
//                bodyQuat = this.quaternion,
//                aabb = this.aabb,
//                shapeAABB = computeAABB_shapeAABB;
//
//        for(var i=0; i!==N; i++){
//            var shape = shapes[i];
//
//            // Get shape world position
//            bodyQuat.vmult(shapeOffsets[i], offset);
//            offset.vadd(this.position, offset);
//
//            // Get shape world quaternion
//            shapeOrientations[i].mult(bodyQuat, orientation);
//
//            // Get shape AABB
//            shape.calculateWorldAABB(offset, orientation, shapeAABB.lowerBound, shapeAABB.upperBound);
//
//            if(i === 0){
//                aabb.copy(shapeAABB);
//            } else {
//                aabb.extend(shapeAABB);
//            }
//        }
//
//        this.aabbNeedsUpdate = false;
//    };
//
//    var uiw_m1 = new Mat3(),
//            uiw_m2 = new Mat3(),
//            uiw_m3 = new Mat3();
//
///**
// * Update .inertiaWorld and .invInertiaWorld
// * @method updateInertiaWorld
// */
//    Body.prototype.updateInertiaWorld = function(force){
//        var I = this.invInertia;
//        if (I.x === I.y && I.y === I.z && !force) {
//            // If inertia M = s*I, where I is identity and s a scalar, then
//            //    R*M*R' = R*(s*I)*R' = s*R*I*R' = s*R*R' = s*I = M
//            // where R is the rotation matrix.
//            // In other words, we don't have to transform the inertia if all
//            // inertia diagonal entries are equal.
//        } else {
//            var m1 = uiw_m1,
//                    m2 = uiw_m2,
//                    m3 = uiw_m3;
//            m1.setRotationFromQuaternion(this.quaternion);
//            m1.transpose(m2);
//            m1.scale(I,m1);
//            m1.mmult(m2,this.invInertiaWorld);
//        }
//    };
//
//    /**
//     * Apply force to a world point. This could for example be a point on the Body surface. Applying force this way will add to Body.force and Body.torque.
//     * @method applyForce
//     * @param  {Vec3} force The amount of force to add.
//     * @param  {Vec3} worldPoint A world point to apply the force on.
//     */
//    var Body_applyForce_r = new Vec3();
//    var Body_applyForce_rotForce = new Vec3();
//    Body.prototype.applyForce = function(force,worldPoint){
//        if(this.type !== Body.DYNAMIC){
//            return;
//        }
//
//        // Compute point position relative to the body center
//        var r = Body_applyForce_r;
//        worldPoint.vsub(this.position,r);
//
//        // Compute produced rotational force
//        var rotForce = Body_applyForce_rotForce;
//        r.cross(force,rotForce);
//
//        // Add linear force
//        this.force.vadd(force,this.force);
//
//        // Add rotational force
//        this.torque.vadd(rotForce,this.torque);
//    };
//
//    /**
//     * Apply force to a local point in the body.
//     * @method applyLocalForce
//     * @param  {Vec3} force The force vector to apply, defined locally in the body frame.
//     * @param  {Vec3} localPoint A local point in the body to apply the force on.
//     */
//    var Body_applyLocalForce_worldForce = new Vec3();
//    var Body_applyLocalForce_worldPoint = new Vec3();
//    Body.prototype.applyLocalForce = function(localForce, localPoint){
//        if(this.type !== Body.DYNAMIC){
//            return;
//        }
//
//        var worldForce = Body_applyLocalForce_worldForce;
//        var worldPoint = Body_applyLocalForce_worldPoint;
//
//        // Transform the force vector to world space
//        this.vectorToWorldFrame(localForce, worldForce);
//        this.pointToWorldFrame(localPoint, worldPoint);
//
//        this.applyForce(worldForce, worldPoint);
//    };
//
//    /**
//     * Apply impulse to a world point. This could for example be a point on the Body surface. An impulse is a force added to a body during a short period of time (impulse = force * time). Impulses will be added to Body.velocity and Body.angularVelocity.
//     * @method applyImpulse
//     * @param  {Vec3} impulse The amount of impulse to add.
//     * @param  {Vec3} worldPoint A world point to apply the force on.
//     */
//    var Body_applyImpulse_r = new Vec3();
//    var Body_applyImpulse_velo = new Vec3();
//    var Body_applyImpulse_rotVelo = new Vec3();
//    Body.prototype.applyImpulse = function(impulse, worldPoint){
//        if(this.type !== Body.DYNAMIC){
//            return;
//        }
//
//        // Compute point position relative to the body center
//        var r = Body_applyImpulse_r;
//        worldPoint.vsub(this.position,r);
//
//        // Compute produced central impulse velocity
//        var velo = Body_applyImpulse_velo;
//        velo.copy(impulse);
//        velo.mult(this.invMass,velo);
//
//        // Add linear impulse
//        this.velocity.vadd(velo, this.velocity);
//
//        // Compute produced rotational impulse velocity
//        var rotVelo = Body_applyImpulse_rotVelo;
//        r.cross(impulse,rotVelo);
//
//    /*
//    rotVelo.x *= this.invInertia.x;
//    rotVelo.y *= this.invInertia.y;
//    rotVelo.z *= this.invInertia.z;
//    */
//        this.invInertiaWorld.vmult(rotVelo,rotVelo);
//
//        // Add rotational Impulse
//        this.angularVelocity.vadd(rotVelo, this.angularVelocity);
//    };
//
//    /**
//     * Apply locally-defined impulse to a local point in the body.
//     * @method applyLocalImpulse
//     * @param  {Vec3} force The force vector to apply, defined locally in the body frame.
//     * @param  {Vec3} localPoint A local point in the body to apply the force on.
//     */
//    var Body_applyLocalImpulse_worldImpulse = new Vec3();
//    var Body_applyLocalImpulse_worldPoint = new Vec3();
//    Body.prototype.applyLocalImpulse = function(localImpulse, localPoint){
//        if(this.type !== Body.DYNAMIC){
//            return;
//        }
//
//        var worldImpulse = Body_applyLocalImpulse_worldImpulse;
//        var worldPoint = Body_applyLocalImpulse_worldPoint;
//
//        // Transform the force vector to world space
//        this.vectorToWorldFrame(localImpulse, worldImpulse);
//        this.pointToWorldFrame(localPoint, worldPoint);
//
//        this.applyImpulse(worldImpulse, worldPoint);
//    };
//
//    var Body_updateMassProperties_halfExtents = new Vec3();
//
///**
// * Should be called whenever you change the body shape or mass.
// * @method updateMassProperties
// */
//    Body.prototype.updateMassProperties = function(){
//        var halfExtents = Body_updateMassProperties_halfExtents;
//
//        this.invMass = this.mass > 0 ? 1.0 / this.mass : 0;
//        var I = this.inertia;
//        var fixed = this.fixedRotation;
//
//        // Approximate with AABB box
//        this.computeAABB();
//        halfExtents.set(
//                (this.aabb.upperBound.x-this.aabb.lowerBound.x) / 2,
//                (this.aabb.upperBound.y-this.aabb.lowerBound.y) / 2,
//                (this.aabb.upperBound.z-this.aabb.lowerBound.z) / 2
//        );
//        Box.calculateInertia(halfExtents, this.mass, I);
//
//        this.invInertia.set(
//                I.x > 0 && !fixed ? 1.0 / I.x : 0,
//                I.y > 0 && !fixed ? 1.0 / I.y : 0,
//                I.z > 0 && !fixed ? 1.0 / I.z : 0
//        );
//        this.updateInertiaWorld(true);
//    };
//
///**
// * Get world velocity of a point in the body.
// * @method getVelocityAtWorldPoint
// * @param  {Vec3} worldPoint
// * @param  {Vec3} result
// * @return {Vec3} The result vector.
// */
//    Body.prototype.getVelocityAtWorldPoint = function(worldPoint, result){
//        var r = new Vec3();
//        worldPoint.vsub(this.position, r);
//        this.angularVelocity.cross(r, result);
//        this.velocity.vadd(result, result);
//        return result;
//    };


}
