/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto;

import junit.framework.TestCase;

import java.util.Iterator;

import mr.robotto.collections.MrTreeMap;
import mr.robotto.collections.core.MrMapFunction;

/**
 * Created by Aarón on 18/11/2014.
 */
public class CollectionTests extends TestCase {

    public void testMapTree() {
        MrTreeMap<String, Integer> tree = new MrTreeMap<String, Integer>(1, new MrMapFunction<String, Integer>() {
            @Override
            public String getKeyOf(Integer integer) {
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
        tree.addChildByKey("1", 2);
        assertEquals(tree.size(), 2);
        tree.addChildByKey("1", 3);
        tree.addChildByKey("1", 4);
        tree.addChildByKey("1", 5);
        assertEquals(tree.size(), 5);
        tree.addChildByKey("2", 6);
        tree.addChildByKey("6", 14);
        tree.addChildByKey("6", 15);
        assertEquals(tree.size(), 8);
        tree.addChildByKey("3", 7);
        tree.addChildByKey("3", 8);
        tree.addChildByKey("3", 9);
        tree.addChildByKey("4", 10);
        tree.addChildByKey("4", 11);
        tree.addChildByKey("5", 12);
        tree.addChildByKey("5", 13);
        assertEquals(tree.size(), 15);
        assertEquals(tree.getChildrenOfByKey("5").get(0).intValue(), 12);
        assertEquals(tree.getChildrenOfByKey("5").get(1).intValue(), 13);

        Iterator<Integer> it1 = tree.breadthTraversal();
        for (int i = 1; i < 16; i++) {
            assertEquals(it1.next().intValue(), i);
        }

        tree.removeByKey("4");
        assertEquals(tree.size(), 12);
        assertEquals(tree.containsKey("4"), false);
        assertEquals(tree.containsKey("10"), false);
        assertEquals(tree.containsKey("11"), false);
        assertEquals(tree.getChildrenOfByKey("1").size(), 3);

        //tree.addChildByKey("5", "12", 14);
        assertEquals(tree.getChildrenOfByKey("5").size(), 2);
        assertEquals(tree.getChildrenOfByKey("5").get(1).intValue(), 14);

        MrTreeMap<String, Integer> subtree = tree.getSubTreeByKey("6");
        assertEquals(subtree.size(), 3);
        assertEquals(subtree.getRoot().intValue(), 6);
    }
}
