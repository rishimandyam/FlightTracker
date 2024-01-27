// --== CS400 File Header Information ==--
// Name: <Shrey Ramesh>
// Email: <snramesh@wisc.edu>
// Team: <AL>
// TA: <Yelun>
// Lecturer: <Gary Dahl>
// Notes to Grader: <N/A>

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm. You can use this class' insert method to build
 * a regular binary search tree, and its toString method to display a
 * level-order
 * traversal of the tree.
 */
public class RedBlackTree<T extends Comparable<T>> implements SortedCollectionInterface<T> {

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always maintained.
     */
    public static class Node<T> {
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild;
        public Node<T> rightChild;
        public int blackHeight; // 0 = red, 1 = black, 2 = double black

        public Node(T data) {
            this.data = data;
            blackHeight = 0; // Set to red by default
        }

        /**
         * @return true when this node has a parent and is the left child of
         *         that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * 
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException     when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain
     *                                  equal data references
     */
    @Override
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if (data == null)
            throw new NullPointerException(
                    "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if (root == null) {
            root = newNode;
            size++;
            root.blackHeight = 1;
            return true;
        } // add first node to an empty tree
        else {
            boolean returnValue = insertHelper(newNode, root); // recursively insert into subtree
            if (returnValue)
                size++;
            else
                throw new IllegalArgumentException(
                        "This RedBlackTree already contains that value.");

            root.blackHeight = 1;
            return returnValue;
        }
    }

    /**
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * 
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the
     *                newNode should be inserted as a descenedent beneath
     * @return true is the value was inserted in subtree, false if not
     */
    private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if (compare == 0)
            return false;

        // store newNode within left subtree of subtree
        else if (compare < 0) {
            if (subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode); // Checking the validity of the tree
                return true;
                // otherwise continue recursive search for location to insert
            } else
                return insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else {
            if (subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode); // Checking the validity of the tree
                return true;
                // otherwise continue recursive search for location to insert
            } else
                return insertHelper(newNode, subtree.rightChild);
        }
    }

    /**
     * Recursive method which tests for any violations in the RBT after a new node
     * has been added to the tree.
     * 
     * @param possibleViolationNode the node that has just been added and may have a
     *                              red parent, causing a vialtion
     */
    protected void enforceRBTreePropertiesAfterInsert(Node<T> possibleViolationNode) {

        // Check: Is there a violation or have we reached past the root node?
        if (possibleViolationNode.parent == null || possibleViolationNode.parent.blackHeight == 1) {
            return; // No violation
        }

        Node<T> parentSibling = null;

        // Finding possibleViolationNode's parent's sibling: After parentSibling is
        // initialized it either points to a node or a null reference if there is no
        // parents sibling. This will be treated as a black node (Case 1 and 2)
        if (possibleViolationNode.parent.isLeftChild()) {
            if (possibleViolationNode.parent.parent.rightChild != null) {
                parentSibling = possibleViolationNode.parent.parent.rightChild;
            }
        } else if (possibleViolationNode.parent.parent.leftChild != null) {
            parentSibling = possibleViolationNode.parent.parent.leftChild;
        }

        // Case 1 and 2: Checking whether parent's sibling is either null or a black
        // node
        if (parentSibling == null || parentSibling.blackHeight == 1) {

            // Case 1: Parent's sibling is on the same side as the possibleViolationNode. We
            // only check whether the parent is on the opposite side as the
            // possibleViolationNode since that would mean its sibling is on the same side.
            // Once this condition is satisfied, we rotate the tree to put case 1 into a
            // case 2 format.
            if (possibleViolationNode.isLeftChild() != possibleViolationNode.parent.isLeftChild()) {
                // System.out.println("Case 1....");
                Node<T> newPossibleViolationNode = possibleViolationNode.parent;
                this.rotate(possibleViolationNode, possibleViolationNode.parent);
                possibleViolationNode = newPossibleViolationNode;
            }

            // Case 2: Parent's sibling is on the opposite side as the
            // possibleViolationNode. We again only check whether the parent is on the same
            // side as the possibleViolationNode since that would mean its sibling is on the
            // opposite side
            if (possibleViolationNode.isLeftChild() == possibleViolationNode.parent.isLeftChild()) {
                // Rotating the violation node's parent with its grandparent
                // System.out.println("Case 2....");
                Node<T> parent = possibleViolationNode.parent;
                Node<T> grandParent = possibleViolationNode.parent.parent;
                this.rotate(parent, grandParent);

                // Swaping colors between violation node's parent and grandparent
                int temp = parent.blackHeight;
                parent.blackHeight = grandParent.blackHeight;
                grandParent.blackHeight = temp;
            }
        }

        // Case 3: Parent's sibiling must be a red node
        if (parentSibling != null && parentSibling.blackHeight == 0) {
            // System.out.println("Case 3....");
            // Turning both parent and parent's sibling black;
            possibleViolationNode.parent.blackHeight = 1;
            parentSibling.blackHeight = 1;

            // Turning grandparent red;
            possibleViolationNode.parent.parent.blackHeight = 0;

            // Recursive call to ensure grandparent is not causing a red child-parent
            // violation
            enforceRBTreePropertiesAfterInsert(possibleViolationNode.parent.parent);
        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * rightChild of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * 
     * @param child  is the node being rotated from child to parent position
     *               (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *               (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *                                  node references are not initially
     *                                  (pre-rotation) related that way
     */
    protected void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        // Validating inputs
        if (!child.parent.equals(parent)) {
            throw new IllegalArgumentException(
                    "The child specified is not a child of the parent specified. This rotation will not work....");
        }
        if (child == null || parent == null) {
            throw new IllegalArgumentException(
                    "One of the specified arguments are null. This rotation will not work....");
        }

        // Checking whether the parent node is an internal node or the root node
        // IF : Internal Node
        if (!parent.equals(root)) {
            // Checking whether this rotation is to the right or left
            // IF: Right Rotation
            if (parent.leftChild != null && parent.leftChild.equals(child)) {
                // Saving right child of child
                Node<T> transferNode = child.rightChild;

                // Child pointers
                child.parent = parent.parent;
                // Checking if parent is a left or right child and changing grandparents pointer
                // accourdingly
                if (parent.isLeftChild()) {
                    parent.parent.leftChild = child;
                } else {
                    parent.parent.rightChild = child;
                }
                child.rightChild = parent;

                // Parent Pointers
                parent.parent = child;
                parent.leftChild = transferNode;
                if (transferNode != null) {
                    transferNode.parent = parent;
                }
            }

            // IF: : Left Rotation
            if (parent.rightChild != null && parent.rightChild.equals(child)) {
                // Saving left child of child
                Node<T> transferNode = child.leftChild;

                // try {
                // System.out.println("Child's left child: " + child.leftChild.data);
                // } catch (NullPointerException e) {
                // System.out.println("CHILD's LEFT CHILD IS NULL");
                // }

                // Child pointers
                child.parent = parent.parent;
                // Checking if parent is a left or right child and changing grandparents pointer
                // accourdingly
                if (parent.isLeftChild()) {
                    parent.parent.leftChild = child;
                } else {
                    parent.parent.rightChild = child;
                }
                // System.out.println("Setting child's left child to parent: " + parent.data);
                child.leftChild = parent;

                // Parent Pointers
                parent.parent = child;
                parent.rightChild = transferNode;
                if (transferNode != null) {
                    transferNode.parent = parent;
                }
                // System.out.println("After Case 2 left rotate with " + child.data + ", " +
                // parent.data + ": "
                // + this.toLevelOrderString());
            }
            // IF : Root Node
        } else {
            // Checking whether this rotation is to the right or to the left again
            // IF : Right Rotation
            if (parent.leftChild != null && parent.leftChild.equals(child)) {
                // Saving the left child of child
                Node<T> transferNode = child.rightChild;

                child.rightChild = parent;

                parent.parent = child;
                this.root = child;
                parent.leftChild = transferNode;
                if (transferNode != null) {
                    transferNode.parent = parent;
                }
            }

            // IF : Left Rotation
            if (parent.rightChild != null && parent.rightChild.equals(child)) {
                // Saving the left child of child
                Node<T> transferNode = child.leftChild;

                child.leftChild = parent;

                parent.parent = child;
                this.root = child;
                parent.rightChild = transferNode;
                if (transferNode != null) {
                    transferNode.parent = parent;
                }
            }
        }

    }

    /**
     * Get the size of the tree (its number of nodes).
     * 
     * @return the number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * 
     * @return true of this.size() return 0, false if this.size() > 0
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     * 
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    @Override
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if (data == null)
            throw new NullPointerException(
                    "This RedBlackTree cannot store null references.");
        return this.containsHelper(data, root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * 
     * @param data    the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, Node<T> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return containsHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return containsHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return true;
            }
        }
    }

    /**
     * Returns an iterator over the values in in-order (sorted) order.
     * 
     * @return iterator object that traverses the tree in in-order sequence
     */
    @Override
    public Iterator<T> iterator() {
        // use an anonymous class here that implements the Iterator interface
        // we create a new on-off object of this class everytime the iterator
        // method is called
        return new Iterator<T>() {
            // a stack and current reference store the progress of the traversal
            // so that we can return one value at a time with the Iterator
            Stack<Node<T>> stack = null;
            Node<T> current = root;

            /**
             * The next method is called for each value in the traversal sequence.
             * It returns one value at a time.
             * 
             * @return next value in the sequence of the traversal
             * @throws NoSuchElementException if there is no more elements in the sequence
             */
            public T next() {
                // if stack == null, we need to initialize the stack and current element
                if (stack == null) {
                    stack = new Stack<Node<T>>();
                    current = root;
                }
                // go left as far as possible in the sub tree we are in un8til we hit a null
                // leaf (current is null), pushing all the nodes we fund on our way onto the
                // stack to process later
                while (current != null) {
                    stack.push(current);
                    current = current.leftChild;
                }
                // as long as the stack is not empty, we haven't finished the traversal yet;
                // take the next element from the stack and return it, then start to step down
                // its right subtree (set its right sub tree to current)
                if (!stack.isEmpty()) {
                    Node<T> processedNode = stack.pop();
                    current = processedNode.rightChild;
                    return processedNode.data;
                } else {
                    // if the stack is empty, we are done with our traversal
                    throw new NoSuchElementException("There are no more elements in the tree");
                }

            }

            /**
             * Returns a boolean that indicates if the iterator has more elements (true),
             * or if the traversal has finished (false)
             * 
             * @return boolean indicating whether there are more elements / steps for the
             *         traversal
             */
            public boolean hasNext() {
                // return true if we either still have a current reference, or the stack
                // is not empty yet
                return !(current == null && (stack == null || stack.isEmpty()));
            }

        };
    }

    /**
     * This method performs an inorder traversal of the tree. The string
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     * 
     * @return string containing the ordered values of this tree (in-order
     *         traversal)
     */
    public String toInOrderString() {
        // use the inorder Iterator that we get by calling the iterator method above
        // to generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        Iterator<T> treeNodeIterator = this.iterator();
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (treeNodeIterator.hasNext())
            sb.append(treeNodeIterator.next());
        while (treeNodeIterator.hasNext()) {
            T data = treeNodeIterator.next();
            sb.append(", ");
            sb.append(data.toString());
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * This method performs a level order traversal of the tree rooted
     * at the current node. The string representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * Note that the Node's implementation of toString generates a level
     * order traversal. The toString of the RedBlackTree class below
     * produces an inorder traversal of the nodes / values of the tree.
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * 
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        String output = "[ ";
        LinkedList<Node<T>> q = new LinkedList<>();
        q.add(this.root);
        while (!q.isEmpty()) {
            Node<T> next = q.removeFirst();
            if (next.leftChild != null)
                q.add(next.leftChild);
            if (next.rightChild != null)
                q.add(next.rightChild);
            output += next.data.toString();
            if (!q.isEmpty())
                output += ", ";
        }
        return output + " ]";
    }

    @Override
    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "/nin order: " + this.toInOrderString();
    }

    /**
     * Testing Case 1 in the RBT: Parent's sibling is black or null and the added
     * node is on the same side as its parent's sibling.
     * 
     * @return true for an expected level-order output, false for an unexpected
     *         level-order output
     */
    @Test
    public void test1() {
        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
        Integer[] insertValues = { 8, 18, 5, 15, 17 }; // Adding 17 results in a Case 1 scenario

        for (Integer i : insertValues) {
            // System.out.println("Adding: " + i);
            rbt.insert(i);
            // System.out.println(rbt.toLevelOrderString());
        }

        Assertions.assertEquals("[ 8, 5, 17, 15, 18 ]", rbt.toLevelOrderString());

        // Testing a null parent's sibling: Adding 5, 8, 6 \
        rbt = new RedBlackTree<Integer>();
        Integer[] insertValues2 = { 5, 8, 6 };

        for (Integer i : insertValues2) {
            // System.out.println("Adding: " + i);
            rbt.insert(i);
            // System.out.println(rbt.toLevelOrderString());
        }

        Assertions.assertEquals("[ 6, 5, 8 ]", rbt.toLevelOrderString());
    }

    /**
     * Testing Case 2 in the RBT: Parent's sibling is black or null and the added
     * node is on the opposite side as its parent's sibling.
     * 
     * @return true for an expected level-order output, false for an unexpected
     *         level-order output
     */
    @Test
    public void test2() {
        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
        Integer[] insertValues = { 50, 20, 100, 10, 5 }; // Adding 5 results in a Case 2 scenario

        for (Integer i : insertValues) {
            // System.out.println("Adding: " + i);
            rbt.insert(i);
            // System.out.println(rbt.toLevelOrderString());
        }

        Assertions.assertEquals("[ 50, 10, 100, 5, 20 ]", rbt.toLevelOrderString());
    }

    /**
     * Testing Case 3 in the RBT: Parent's sibling is red.
     * 
     * @return true for an expected level-order output, false for an unexpected
     *         level-order output
     */
    @Test
    public void test3() {
        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
        Integer[] insertValues = { 50, 20, 100, 10, 5, 2 }; // Adding 5 results in a Case 2 scenario

        for (Integer i : insertValues) {
            // System.out.println("Adding: " + i);
            rbt.insert(i);
            // System.out.println(rbt.toLevelOrderString());
        }

        Assertions.assertEquals("[ 50, 10, 100, 5, 20, 2 ]", rbt.toLevelOrderString());
        Assertions.assertEquals(0, rbt.root.leftChild.blackHeight);
        Assertions.assertEquals(2,
                rbt.root.leftChild.leftChild.blackHeight + rbt.root.leftChild.rightChild.blackHeight);
    }

}