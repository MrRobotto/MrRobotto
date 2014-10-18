/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.data.model;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ModelNode implements Comparable<ModelNode> {
    private ModelNode parent;
    private ArrayList<ModelNode> children;
    private int depth;

    public ModelNode() {
        children = new ArrayList<ModelNode>();
        setParent(null);
    }

    public ModelNode(ModelNode parent) {
        children = new ArrayList<ModelNode>();
        setParent(parent);
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public int compareTo(ModelNode modelNode) {
        if (this.depth > modelNode.getDepth()) {
            return 1;
        } else {
            return this.depth < modelNode.getDepth() ? -1 : 0;
        }
    }

    public void setParent(ModelNode parent) {
        if (this.parent != null) {
            this.parent.removeChild(this);
        }
        if (parent == null) {
            this.parent = null;
            depth = 0;
        } else {
            this.parent = parent;
            depth = this.parent.getDepth()+1;
            this.parent.addChild(this);
        }
    }

    public ModelNode getParent() {
        return parent;
    }

    public boolean addChild(ModelNode r) {
        return children.add(r);
    }

    public boolean removeChild(ModelNode node) {
        return children.remove(node);
    }

    public Collection<ModelNode> getChildren() {
        return children;
    }
}
