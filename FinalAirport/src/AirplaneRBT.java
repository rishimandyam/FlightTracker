import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class AirplaneRBT extends RedBlackTree<IAirplane> implements IAirplaneRBT<IAirplane> {

    @Override
    /**
     * This remove method remove the specified airplane from the RBT and adjust the
     * tree to conform to the rules of a RBT. It is called when a plane departs from
     * the airport.
     * 
     * @param data the specified airplane to remove
     * @return the removed node
     * @throws NoSuchElementException if the element does not exist in the RBT
     */
    public IAirplane remove(IAirplane data) throws NoSuchElementException {
        Node<IAirplane> dataShell = findNode(data);

        // Case 1: Node has one child - child must be red. We simply remove the ndoe and
        // set its child to be a black node.
        if ((dataShell.leftChild == null && dataShell.rightChild != null)
                || (dataShell.rightChild == null && dataShell.leftChild != null)) {
            if (dataShell.isLeftChild()) {
                if (dataShell.leftChild != null) {
                    dataShell.parent.leftChild = dataShell.leftChild;
                    dataShell.leftChild.parent = dataShell.parent;
                    dataShell.leftChild.blackHeight = 1;
                }
                if (dataShell.rightChild != null) {
                    dataShell.parent.leftChild = dataShell.rightChild;
                    dataShell.rightChild.parent = dataShell.parent;
                    dataShell.rightChild.blackHeight = 1;
                }
            } else {
                if (dataShell.leftChild != null) {
                    dataShell.parent.rightChild = dataShell.leftChild;
                    dataShell.leftChild.parent = dataShell.parent;
                    dataShell.leftChild.blackHeight = 1;
                }
                if (dataShell.rightChild != null) {
                    dataShell.parent.rightChild = dataShell.rightChild;
                    dataShell.rightChild.parent = dataShell.parent;
                    dataShell.rightChild.blackHeight = 1;
                }
            }
            this.size--;
            dataShell.parent = null; // Completely removing dataShell
            return dataShell.data;
        }

        // Case 2: Node has two children - The node will be replaced by the rightmost
        // child of the left subtree. Then, a recursive call will be made to remove the
        // rightmost child of the left subtree. This will either be a case 0 or case 1
        // since it can only have a left child or be a leaf node.
        if (dataShell.leftChild != null && dataShell.rightChild != null) {
            // Finding predecessorNode
            Node<IAirplane> predecessorNode = dataShell.leftChild;
            while (predecessorNode.rightChild != null) {
                predecessorNode = predecessorNode.rightChild;
            }

            // Recursive call to remove the predecessorNode - will go to case 0 or 1
            remove(predecessorNode.data);

            // Replacing data in dataShell with the predecessorNode, but keeping blackHeight
            // the same.
            dataShell.data = predecessorNode.data;
            return predecessorNode.data;
        }

        // Case 0: Node has zero children - If the node is red, we just remove the node
        // with no problems. If the node is black then we replace the with an empty
        // double black node. Then there are 3 cases.
        if (dataShell.leftChild == null && dataShell.rightChild == null) {
            // Removing node and creating null doubleBlNode
            Node<IAirplane> doubleBlNode = new Node<IAirplane>(null);
            doubleBlNode.blackHeight = 2;
            if (dataShell.isLeftChild()) {
                dataShell.parent.leftChild = doubleBlNode;
                doubleBlNode.parent = dataShell.parent;
            } else {
                dataShell.parent.rightChild = doubleBlNode;
                doubleBlNode.parent = dataShell.parent;
            }

            // If the removed node was red, then we are done.
            if (dataShell.blackHeight == 0) {
                dataShell.parent = null; // Commpletely removing dataShell from RBT
                destroyDBNode(doubleBlNode); // Destroying double black since we are done
                this.size--; // Updating size
                return dataShell.data;
            } else { // Node has to be a black node
                dataShell.parent = null; // Completely removing dataShell from RBT

                removeCase0Black(doubleBlNode);
                this.size--;
                return dataShell.data;
            }
        }
        return null; // Should be unreachable
    }

    private void removeCase0Black(Node<IAirplane> doubleBlNode) {

        // Checking whether doubleBlNode is the root node
        if (doubleBlNode.equals(this.root)) {
            doubleBlNode.blackHeight = 1; // Making it a regular black node
            return;
        }

        // Finding the double black's sibling
        Node<IAirplane> doubleBlNodeSibling = null;
        if (doubleBlNode.isLeftChild()) {
            doubleBlNodeSibling = doubleBlNode.parent.rightChild;
        } else {
            doubleBlNodeSibling = doubleBlNode.parent.leftChild;
        }

        // Case 1: If the sibling of double black node is a red node. Then we rotate the
        // RBT between the sibling and the double black's parent so that sibling of
        // double black is black. Then we should be in case 1 or 2.
        if (doubleBlNodeSibling != null && doubleBlNodeSibling.blackHeight == 0) {
            // Rotating and color swap - doubleBlNodeSibiling should be black at this point
            this.rotate(doubleBlNodeSibling, doubleBlNode.parent);
            int tempColor = doubleBlNodeSibling.blackHeight;
            doubleBlNodeSibling.blackHeight = doubleBlNode.parent.blackHeight;
            doubleBlNode.parent.blackHeight = tempColor;

            removeCase0Black(doubleBlNode);
            return;
        }

        // Case 2: If the sibling of the double black node is a black node
        // and that node has no red children. We then subtract a black height from the
        // double black and its sibling. We then add a black height to the double
        // black's parent. This then will either be black or double black. If it is
        // double black then we have to do a recursive call on that double black, if not
        // we are done;
        if (doubleBlNodeSibling.blackHeight == 1
                && (doubleBlNodeSibling.leftChild == null || doubleBlNodeSibling.leftChild.blackHeight == 1)
                && (doubleBlNodeSibling.rightChild == null || doubleBlNodeSibling.rightChild.blackHeight == 1)) {
            doubleBlNode.blackHeight -= 1; // Should just be black now
            doubleBlNodeSibling.blackHeight -= 1; // Should be red now
            doubleBlNode.parent.blackHeight += 1; // Should be either black or double black

            if (doubleBlNode.data == null) {
                destroyDBNode(doubleBlNode); // Destroying the old double black node if it is a null node
            }
            // Recursive call in the case of a double black
            if (doubleBlNodeSibling.parent.blackHeight == 2) {
                removeCase0Black(doubleBlNodeSibling.parent);
            } else {
                return;
            }
        }

        // Case 3: If the sibling of the double black node is a black node and that node
        // has any number of red children.
        if (doubleBlNodeSibling.blackHeight == 1) {
            if ((doubleBlNodeSibling.leftChild != null && doubleBlNodeSibling.leftChild.blackHeight == 0)
                    || (doubleBlNodeSibling.rightChild != null && doubleBlNodeSibling.rightChild.blackHeight == 0)) {
                Node<IAirplane> outerChild = null;
                Node<IAirplane> innerChild = null;

                // Finding outer and inner children of the doubleBlNodeSibling
                if (doubleBlNodeSibling.isLeftChild()) {
                    outerChild = doubleBlNodeSibling.leftChild;
                    innerChild = doubleBlNodeSibling.rightChild;
                } else {
                    outerChild = doubleBlNodeSibling.rightChild;
                    innerChild = doubleBlNodeSibling.leftChild;
                }

                // Case 1: outerChild is red and innerChild is anything
                if (outerChild != null && outerChild.blackHeight == 0) {
                    this.rotate(doubleBlNodeSibling, doubleBlNode.parent);
                    int tempColor = doubleBlNodeSibling.blackHeight;
                    doubleBlNodeSibling.blackHeight = doubleBlNode.parent.blackHeight;
                    doubleBlNode.parent.blackHeight = tempColor;
                    doubleBlNode.blackHeight = 1; // Fixing double black color
                    outerChild.blackHeight = 1; // Changing the outerChild from red to black
                    if (doubleBlNode.data == null) {
                        destroyDBNode(doubleBlNode); // Destroying the old double black node if it is a null node
                    }
                    return;
                }

                // Case 2: outerChild is black and innerChild is red
                if ((outerChild == null || outerChild.blackHeight == 1)
                        && (innerChild != null && innerChild.blackHeight == 0)) {
                    this.rotate(innerChild, doubleBlNodeSibling);
                    innerChild.blackHeight = 1;
                    doubleBlNodeSibling.blackHeight = 0;
                    this.rotate(innerChild, innerChild.parent);
                    int tempColor = innerChild.blackHeight;
                    innerChild.blackHeight = doubleBlNode.parent.blackHeight;
                    doubleBlNode.parent.blackHeight = tempColor;
                    doubleBlNode.blackHeight = 1; // Fixing double black color
                    doubleBlNodeSibling.blackHeight = 1; // Changing the outerChild from red to black
                    if (doubleBlNode.data == null) {
                        destroyDBNode(doubleBlNode); // Destroying the old double black node if it is a null node
                    }
                    return;
                }
            }
        }

    }

    private void destroyDBNode(Node<IAirplane> doubleBlNode) {
        if (doubleBlNode.isLeftChild()) {
            doubleBlNode.parent.leftChild = null;
        } else {
            doubleBlNode.parent.rightChild = null;
        }
        doubleBlNode = null;
    }

    /**
     * This get method gets an airplane that departs at a certain time.
     * 
     * @param time the time in 24hr format which we are looking for an airplane for
     *             (NO 2 FLIGHTS HAVE THE SAME TIME)
     * @return a Airplane that takes off at the specified time
     * @throws NoSuchElementException if there is no airplane that departs at that
     *                                time
     */
    public IAirplane getByTime(ITime time) throws NoSuchElementException {
        // null references will not be stored within this tree
        if (time == null)
            throw new NullPointerException(
                    "This RedBlackTree cannot store null references.");
        return this.getByTimeHelper(time, this.root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * 
     * @param data    the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private IAirplane getByTimeHelper(ITime data, Node<IAirplane> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return null;
        } else {
            int compare = data.compareTo(subtree.data.getTime());
            if (compare < 0) {
                // go left in the tree
                return getByTimeHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return getByTimeHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return subtree.data;
            }
        }
    }

    /**
     * Checks whether the tree contains the value *data*.
     * 
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */

    private Node<IAirplane> findNode(IAirplane data) {
        // null references will not be stored within this tree
        if (data == null)
            throw new NullPointerException(
                    "This RedBlackTree cannot store null references.");
        return this.findNodeHelper(data, root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * 
     * @param data    the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private Node<IAirplane> findNodeHelper(IAirplane data, Node<IAirplane> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return null;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return findNodeHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return findNodeHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return subtree;
            }
        }
    }

    @Override
    /**
     * This method takes two time inputs and searches for any airplanes that
     * depart
     * during this time period.
     *
     * @param t1 the low-bound time period
     * @param t2 the high-bound time period
     * @return a list of airplanes that depart during this time period
     * @throws IllegalArgumentException when t1 is after t2
     */
    public ArrayList<IAirplane> searchByPeriod(ITime t1, ITime t2) throws IllegalArgumentException {
        if (t1.compareTo(t2) > 0) {
            throw new IllegalArgumentException(
                    "The first time you entered is after the second time. Pleases pass valid arguments...");
        }

        Iterator<IAirplane> treeNodeIterator = this.iterator();
        ArrayList<IAirplane> allPlanes = new ArrayList<>();

        if (treeNodeIterator.hasNext())
            allPlanes.add(treeNodeIterator.next());
        while (treeNodeIterator.hasNext()) {
            IAirplane data = treeNodeIterator.next();
            if (data.getTime().compareTo(t1) >= 0 && data.getTime().compareTo(t2) <= 0) {
                allPlanes.add(data);
            }
        }

        // Removing first node in list if it is not between the two times
        if (allPlanes.get(0).getTime().compareTo(t1) < 0 || allPlanes.get(0).getTime().compareTo(t2) > 0) {
            allPlanes.remove(0);
        }

        return allPlanes;
    }

    @Override
    /**
     * Returns a array list of airplanes in a tree instead of a String-
     * inOrderTraversal
     * 
     * @return a list of Airplanes
     */
    public ArrayList<IAirplane> inOrderPlanes() {

        Iterator<IAirplane> treeNodeIterator = this.iterator();
        ArrayList<IAirplane> allPlanes = new ArrayList<>();

        if (treeNodeIterator.hasNext())
            allPlanes.add(treeNodeIterator.next());
        while (treeNodeIterator.hasNext()) {
            IAirplane data = treeNodeIterator.next();
            allPlanes.add(data);
        }

        return allPlanes;
    }

    // /**
    // * Returns a array list of airplanes in a tree instead of a String-
    // * LevelOrderTraversal
    // *
    // * @return a list of Airplanes
    // */
    // public ArrayList<IAirplane> levelOrderPlanes() {
    // ArrayList<IAirplane> allPlanes = new ArrayList<>();
    // LinkedList<Node<IAirplane>> q = new LinkedList<>();
    // q.add(this.root);
    // while (!q.isEmpty()) {
    // Node<IAirplane> next = q.removeFirst();
    // if (next.leftChild != null) {
    // q.add(next.leftChild);
    // }

    // if (next.rightChild != null) {
    // q.add(next.rightChild);
    // }

    // // Adding the plane to the list of allPlanes
    // allPlanes.add(next.data);
    // }
    // return allPlanes;
    // }
}
