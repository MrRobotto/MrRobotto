/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.components.data.action;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;


/**
 * Created by aaron on 26/04/2015.
 */
public class MrKeyFrameList implements Iterable<MrFrame>{
    private TreeMap<Integer, MrFrame> mKeyFrames;

    public MrKeyFrameList() {
        mKeyFrames = new TreeMap<>();
    }

    public void addKeyFrame(MrFrame frame) {
        mKeyFrames.put(frame.getFrameNumber(), frame);
    }

    public void removeKeyFrame(int numberFrame) {
        mKeyFrames.remove(numberFrame);
    }

    public Set<Integer> getKeyFrameIndices() {
        return mKeyFrames.navigableKeySet();
    }

    @Override
    public Iterator<MrFrame> iterator() {
        return new MrFrameIterator(mKeyFrames);
    }

    private class MrFrameIterator implements Iterator<MrFrame> {

        private TreeMap<Integer, MrFrame> mKeyFrames;
        private Set<Integer> mKeyFrameIndices;
        private int mCurrent;
        private int mEnd;
        private int mStart;

        public MrFrameIterator(TreeMap<Integer, MrFrame> keyFrames) {
            mKeyFrames = keyFrames;
            mKeyFrameIndices = keyFrames.keySet();
            mStart = mKeyFrames.firstKey();
            mEnd = mKeyFrames.lastKey();
            mCurrent = 0;
        }

        @Override
        public boolean hasNext() {
            return mCurrent <= mEnd;
        }

        @Override
        public MrFrame next() {
            if (mCurrent < mStart) {
                return mKeyFrames.firstEntry().getValue();
            }
            if (mKeyFrameIndices.contains(mCurrent)) {
                return mKeyFrames.get(mCurrent);
            }
            MrFrame frame2 = mKeyFrames.higherEntry(mCurrent).getValue();
            MrFrame frame1 = mKeyFrames.lowerEntry(mCurrent).getValue();
            MrFrame frame = MrFrame.interpolate(mCurrent, frame1, frame2);
            mCurrent++;
            return frame;
        }

        @Override
        public void remove() {

        }
    }
}
