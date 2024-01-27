//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
///**
// * class contains junit tests for the RedBlackTree class
// */
//class RedBlackTreeTest {
//
//    /**
//     * case 3: method checks if rbt properties are maintained if a node is added whose parent and uncle are red
//     */
//    @Test
//    void test1() {
//        //create RBT
//        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
//
//        // insert values
//        rbt.insert(10);
//        rbt.insert(5);
//        rbt.insert(15);
//        rbt.insert(20);
//
//        //save correct output for level order traversal
//        String rbtLevelOrder = "[ 10, 5, 15, 20 ]";
//
//        //call assertEquals
//        Assertions.assertEquals(rbtLevelOrder, rbt.toLevelOrderString());
//    }
//
//    /**
//     * case 1,2, and 3: method checks if rbt properties are maintained if a node is added whose parent is red and uncle
//     * is black
//     * case 2: both uncle and node are right children
//     * case 3: uncle is right child, node is left child
//     */
//    @Test
//    void test2() {
//        //create RBT
//        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
//
//        // insert values
//        rbt.insert(50);
//        rbt.insert(20);
//        rbt.insert(60);
//        rbt.insert(10);
//        rbt.insert(30);
//        rbt.insert(40);
//        rbt.insert(25);
//        rbt.insert(70);
//        rbt.insert(21);
//
//        //save correct output for level order traversal
//        String rbtLevelOrder = "[ 30, 20, 50, 10, 25, 40, 60, 21, 70 ]";
//
//        //call assertEquals
//        Assertions.assertEquals(rbtLevelOrder, rbt.toLevelOrderString());
//    }
//
//    /**
//     * case 1,2, and 3: method checks if rbt properties are maintained if a node is added whose parent is red and uncle
//     * is black
//     * case 2: both uncle and node are left children
//     * case 3: uncle is left child, node is right child
//     */
//    @Test
//    void test3() {
//        //create RBT
//        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
//
//        // insert values
//        rbt.insert(50);
//        rbt.insert(80);
//        rbt.insert(40);
//        rbt.insert(90);
//        rbt.insert(70);
//        rbt.insert(60);
//        rbt.insert(75);
//        rbt.insert(30);
//        rbt.insert(79);
//
//        //save correct output for level order traversal
//        String rbtLevelOrder = "[ 70, 50, 80, 40, 60, 75, 90, 30, 79 ]";
//
//        //call assertEquals
//        Assertions.assertEquals(rbtLevelOrder, rbt.toLevelOrderString());
//    }
//}