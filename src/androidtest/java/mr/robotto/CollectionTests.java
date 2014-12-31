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

import java.util.Iterator;

import mr.robotto.collections.MrListNode;
import mr.robotto.collections.MrMapTree;
import mr.robotto.collections.core.MrMapFunction;

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

    public void testMapTree() {
        MrMapTree<String, Integer> tree = new MrMapTree<String, Integer>(1, new MrMapFunction<String, Integer>() {
            @Override
            public String getIdOf(Integer integer) {
                return String.valueOf(integer);
            }
        });
        //1
        //  2
        //    6
        //      14
        //      15
        //  3
        //    7
        //    8
        //    9
        //  4
        //    10
        //    11
        //  5
        //    12
        //    13

        assertEquals(tree.size(), 1);
        tree.addChild("1", 2);
        assertEquals(tree.size(), 2);
        tree.addChild("1", 3);
        tree.addChild("1", 4);
        tree.addChild("1", 5);
        assertEquals(tree.size(), 5);
        tree.addChild("2", 6);
        tree.addChild("6", 14);
        tree.addChild("6", 15);
        assertEquals(tree.size(), 8);
        tree.addChild("3", 7);
        tree.addChild("3", 8);
        tree.addChild("3", 9);
        tree.addChild("4", 10);
        tree.addChild("4", 11);
        tree.addChild("5", 12);
        tree.addChild("5", 13);
        assertEquals(tree.size(), 15);
        assertEquals(tree.getChildrenOf("5").get(0).intValue(), 12);
        assertEquals(tree.getChildrenOf("5").get(1).intValue(), 13);

        Iterator<Integer> it1 = tree.breadthTraversal();
        for (int i = 1; i < 16; i++) {
            assertEquals(it1.next().intValue(), i);
        }

        tree.removeChild("4");
        assertEquals(tree.size(), 12);
        assertEquals(tree.hasKey("4"), false);
        assertEquals(tree.hasKey("10"), false);
        assertEquals(tree.hasKey("11"), false);
        assertEquals(tree.getChildrenOf("1").size(), 3);

        tree.addChild("5", "12", 14);
        assertEquals(tree.getChildrenOf("5").size(), 2);
        assertEquals(tree.getChildrenOf("5").get(1).intValue(), 14);

        MrMapTree<String, Integer> subtree = tree.getSubTree("6");
        assertEquals(subtree.size(), 3);
        assertEquals(subtree.getRoot().intValue(), 6);
    }
}
