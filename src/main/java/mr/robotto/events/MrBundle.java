/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.events;

import android.view.MotionEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bundle to store multiple types of data.
 * Used to pass data between an Android event received in a {@link MrEventDispatcher} and
 * processed in a {@link MrEventsListener}
 */
public class MrBundle {
    private HashMap<String, Object> mBundle = new HashMap<>();

    /**
     * Clears the bundle
     */
    public void clear() {
        mBundle.clear();
    }

    /**
     * Removes an element from this bundle
     * @param key
     */
    public void remove(String key) {
        mBundle.remove(key);
    }

    public boolean isEmpty() {
        return mBundle.isEmpty();
    }

    public Set<String> keySet() {
        return mBundle.keySet();
    }

    public boolean containsKey(String key) {
        return mBundle.containsKey(key);
    }

    public int size() {
        return mBundle.size();
    }

    public void putAll(Map<String, Object> bundle) {
        mBundle.putAll(bundle);
    }

    public void putByte(String key, byte value) {
        mBundle.put(key, value);
    }

    public void putDoubleArray(String key, double[] value) {
        mBundle.put(key, value);
    }

    public void putBoolean(String key, boolean value) {
        mBundle.put(key, value);
    }

    public void putInt(String key, int value) {
        mBundle.put(key, value);
    }

    public void putMotionEvent(String key, MotionEvent motionEvent) {
        mBundle.put(key, motionEvent);
    }

    public void putDouble(String key, double value) {
        mBundle.put(key, value);
    }

    public void putCharArray(String key, char[] value) {
        mBundle.put(key, value);
    }

    public void putShortArray(String key, short[] value) {
        mBundle.put(key, value);
    }

    public void putChar(String key, char value) {
        mBundle.put(key, value);
    }

    public void putStringArray(String key, String[] value) {
        mBundle.put(key, value);
    }

    public void putIntArray(String key, int[] value) {
        mBundle.put(key, value);
    }

    public void putByteArray(String key, byte[] value) {
        mBundle.put(key, value);
    }

    public void putLongArray(String key, long[] value) {
        mBundle.put(key, value);
    }

    public void putShort(String key, short value) {
        mBundle.put(key, value);
    }

    public void putFloat(String key, float value) {
        mBundle.put(key, value);
    }

    public void putString(String key, String value) {
        mBundle.put(key, value);
    }

    public void putBooleanArray(String key, boolean[] value) {
        mBundle.put(key, value);
    }

    public void putFloatArray(String key, float[] value) {
        mBundle.put(key, value);
    }

    public void putLong(String key, long value) {
        mBundle.put(key, value);
    }

    public MotionEvent getMotionEvent(String key) {
        return (MotionEvent) mBundle.get(key);
    }

    public double getDouble(String key) {
        return (Double)mBundle.get(key);
    }

    public String[] getStringArray(String key) {
        return (String[]) mBundle.get(key);
    }

    public char getChar(String key) {
        return (Character) mBundle.get(key);
    }

    public byte getByte(String key) {
        return (Byte) mBundle.get(key);
    }

    public double[] getDoubleArray(String key) {
        return (double[]) mBundle.get(key);
    }

    public int[] getIntArray(String key) {
        return (int[]) mBundle.get(key);
    }

    public short getShort(String key) {
        return (Short)mBundle.get(key);
    }

    public float[] getFloatArray(String key) {
        return (float[])mBundle.get(key);
    }

    public byte[] getByteArray(String key) {
        return (byte[])mBundle.get(key);
    }

    public boolean[] getBooleanArray(String key) {
        return (boolean[])mBundle.get(key);
    }

    public String getString(String key) {
        return (String)mBundle.get(key);
    }

    public int getInt(String key) {
        return (Integer) mBundle.get(key);
    }

    public float getFloat(String key) {
        return (Float)mBundle.get(key);
    }

    public short[] getShortArray(String key) {
        return (short[]) mBundle.get(key);
    }

    public boolean getBoolean(String key) {
        return (Boolean) mBundle.get(key);
    }

    public long[] getLongArray(String key) {
        return (long[]) mBundle.get(key);
    }

    public char[] getCharArray(String key) {
        return (char[]) mBundle.get(key);
    }

    public Object get(String key) {
        return mBundle.get(key);
    }

    public long getLong(String key) {
        return (Long)mBundle.get(key);
    }

    @Override
    public String toString() {
        return mBundle.toString();
    }
}
