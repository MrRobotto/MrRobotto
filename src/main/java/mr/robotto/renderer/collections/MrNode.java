/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.collections;

import java.util.Collection;

/**
 * Created by Aarón on 17/11/2014.
 */
public interface MrNode<T> extends Comparable<MrNode<T>> {

    public T getData();

    public int getDepth();

    public boolean isLeaf();

    public boolean hasParent();

    public MrNode<T> getParent();

    public Collection<? extends MrNode<T>> getChildren();

    public boolean addChild(MrNode<T> node);

    public boolean removeChild(MrNode<T> node);

    public void clearChildren();

    public void clearParent();

    public MrNode<T> getRoot();
}
