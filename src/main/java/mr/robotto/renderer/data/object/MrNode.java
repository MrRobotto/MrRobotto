/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.data.object;

import java.util.ArrayList;
import java.util.List;

public abstract class MrNode implements Comparable<MrNode> {

    private MrNode parent;
    private ArrayList<MrNode> children;
    private int depth;

    public MrNode(MrNode parent) {
        init();
        setParent(parent);
    }

    public MrNode() {
        this(null);
    }

    private void init() {
        children = new ArrayList<MrNode>();
    }

    @Override
    public int compareTo(MrNode node) {
        if (depth > node.getDepth()) {
            return 1;
        } else {
            return depth < node.getDepth() ? -1 : 0;
        }
    }

    public int getDepth() {
        return depth;
    }

    public void setParent(MrNode parent) {
        //If this node has already a parent
        if (hasParent()) {
            //We remove the children from the parent
            this.parent.children.remove(this);
        }
        if (parent == null) {
            this.depth = 0;
        } else {
            this.depth = parent.getDepth() + 1;
            parent.children.add(this);
        }
        this.parent = parent;
    }

    public MrNode getParent() {
        return parent;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public List<MrNode> getChildren() {
        return children;
    }

    public void addChild(MrNode node) {
        node.setParent(this);
    }

    public void removeChild(MrNode node) {
        node.setParent(null);
    }
}
