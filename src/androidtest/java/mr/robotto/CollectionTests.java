/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto;

import junit.framework.TestCase;

import mr.robotto.collections.MrListNode;

/**
 * Created by Aarón on 18/11/2014.
 */
public class CollectionTests extends TestCase {


    public void testNodeList() {
        MrListNode<String> root = new MrListNode<String>(null, "Root");
        assertNotNull(root);
        assertEquals(root.getData(), "Root");
        assertEquals(root.getDepth(), 0);
        assertTrue(root.isLeaf());
        assertFalse(root.hasParent());
        assertNull(root.getParent());
        assertEquals(root.getChildren().size(),0);
        root.clearChildren();
        assertEquals(root.getChildren().size(),0);
        root.clearParent();
        assertNull(root.getParent());

        MrListNode<String> n1 = new MrListNode<String>(root, "First");
        MrListNode<String> n2 = new MrListNode<String>(root, "Second");
        MrListNode<String> n11 = new MrListNode<String>(root, "FirstFirst");
        MrListNode<String> n12 = new MrListNode<String>(root, "FirstSecond");
        n1.addChild(n11);
        n1.addChild(n12);
        root.addChild(n1);
        root.addChild(n2);
        assertEquals(root.getChildren().size(),2);
        assertEquals(root.getDepth(),0);
        assertEquals(n1.getDepth(),1);
        assertEquals(n2.getDepth(),1);
        assertEquals(n11.getDepth(),2);
        assertEquals(n12.getDepth(),2);

        root.clearChildren();
        assertEquals(root.getChildren().size(),0);
        assertNull(n1.getParent());
        assertEquals(n1.getDepth(),0);

        n11.clearParent();
        assertEquals(n11.getParent(),null);
        assertEquals(n11.getDepth(),0);
        assertEquals(n1.getChildren().size(), 1);
    }
}
