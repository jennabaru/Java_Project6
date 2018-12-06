package project6;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.lang.Iterable;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.Comparable;
import java.util.Iterator;


/**
* This class extends the Comparable<E> interface 
* and implements Collection<E> and Iterable<E> and
* creates a binary search tree of any element E.
* It houses internal node and three iterator classes 
* as well as many methods for the BST.
* @author Jenna Baruch * @version 12/05/2018
*/
public class BST<E extends Comparable<E>> implements Collection<E>, Iterable<E> {
    
    //BSTNode root element
    private BSTNode<E> root = null;
    // private size
    private int size = 0;

    /**
     * default constructor
     */
    public BST(){

    }

/**
 * Private bstnode class, contains a constructor
 * @param <E> accepts data to be put into the node
 */
    private class BSTNode<E> {

        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        /**
         * BSTNode constructor sets data, left, and right data fields
         * @param data type E
         */
        BSTNode(E data){
            this.data = data;
            this.left = null;
            this.right = null;
        }

    }

/**
 * This private internal class inorderIterator implemets
 * the Iterator<E> interface. The constructor makes an inorder
 * iterator and the class contains next and hasNext methods.
 * 
 * @author Jenna Baruch @version 12/4/18
 */
    private class BSTinorderIterator implements Iterator<E>{
        
        BSTNode<E> current = root;
        //make new array list to store data type E
        ArrayList<E> inList = new ArrayList<E>();
        int index = 0;

        /**
         * The BSTinorderIterartor constructor calls the recursive
         * method to iterator using the inorder iterator
         */
        public BSTinorderIterator(){
            inorderIteratorRecursive(root);
        }

        /**
         * This recursive method iterates through the tree
         * in inorder while there are still roots in the tree.
         * @param root BTSNode root passed in
         */
        private void inorderIteratorRecursive(BSTNode<E> root){
            if (root!= null){
                inorderIteratorRecursive(root.left);
                inList.add(root.data);
                inorderIteratorRecursive(root.right);
            }
        }

        /**
         * This method returns the data inside current node
         * and then moves to the next element in the ArrayList.
         * @return data stored inside the node
         * @throws NoSuchElementException
         */
        public E next() throws NoSuchElementException{
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E nextValue = inList.get(index);
            index += 1;
            return nextValue;
        }

        /**
         * This method checks to see if there is a next value by 
         * seeing if there is a next value in the ArrayList created by
         * the inorder iterator.
         * @return boolean value, true if hasNext, false if next is null
         */
        public boolean hasNext(){
            if (index < inList.size()){
                return true;
            }
            return false;
        }
    }

/**
 * This private internal class preorderIterator implemets
 * the Iterator<E> interface. The constructor makes a preorder
 * iterator and the class contains next and hasNext methods.
 * 
 * @author Jenna Baruch @version 12/4/18
 */
    private class BSTpreorderIterator implements Iterator<E>{
        BSTNode<E> current = root;
        //make new array list to store data type E
        ArrayList<E> preList = new ArrayList<E>();
        int index = 0;

        /**
         * BSTpreorderIterator constructor calls the preorder recursive
         * method that maks a preorder iterator.
         */
        public BSTpreorderIterator(){
            preorderIteratorRecursive(root);
        }

        /**
         * This recursive method iterates through the tree in
         * preorder and adds node values to array list while node
         * values are not null.
         * @param root bstnode to be passed in as current
         */
        private void preorderIteratorRecursive(BSTNode<E> root){
            if (root!= null){
                preList.add(root.data);
                preorderIteratorRecursive(root.left);
                preorderIteratorRecursive(root.right);
            }
        }

        /**
         * This method returns the data inside current node
         * and then moves to the next element in the ArrayList.
         * @return data type E stored inside the node
         * @throws NoSuchElementException
         */
        public E next() throws NoSuchElementException{
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E nextValue = preList.get(index);
            index += 1;
            return nextValue;
        }
        /**
         * This method checks to see if there is a next value by 
         * seeing if there is a next value in the ArrayList created by
         * the inorder iterator.
         * @return boolean value, true if hasNext, false if next is null
         */
        public boolean hasNext(){
            if (index < preList.size()){
                return true;
            }
            return false;
        }
    }

/** 
 * This private internal class preorderIterator implements
 * the Iterator<E> interface. The constructor makes an preorder
 * iterator and the class contains next and hasNext methods.
 * 
 * @author Jenna Baruch @version 12/4/18
 */
    private class BSTpostorderIterator implements Iterator<E>{
        BSTNode<E> current = root;
        ArrayList<E> postList = new ArrayList<E>();
        int index = 0;

        /**
         * BSTpostorderIterator constructor calls the postorder recursive
         * method that makes a postorder iterator.
         */
        public BSTpostorderIterator(){
            postorderIteratorRecursive(root);
        }

        /**
         * This recursive method iterates through the tree in
         * postorder and adds node values to array list while node
         * values are not null.
         * @param root bstnode to be passed in as current
         */
        private void postorderIteratorRecursive(BSTNode<E> root){
            if (root!= null){
                postorderIteratorRecursive(root.left);
                postorderIteratorRecursive(root.right);
                postList.add(root.data);
            }
        }
        /**
         * This method returns the data inside current node
         * and then moves to the next element in the ArrayList.
         * @return data type E stored inside the node
         * @throws NoSuchElementException
         */
        public E next() throws NoSuchElementException{
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E nextValue = postList.get(index);
            index += 1;
            return nextValue;
        }
        /**
         * This method checks to see if there is a next value by 
         * seeing if there is a next value in the ArrayList created by
         * the inorder iterator.
         * @return boolean value, true if hasNext, false if next is null
         */
        public boolean hasNext(){
            if (index < postList.size()){
                return true;
            }
            return false;
        }
    }

/**
 * This wrapper method error checks the data type E and makes sure the 
 * tree is not empty and then calls the get recursive method.
 * 
 * @param value E data to be found in a node
 * @return E refernce in tree if data was there, null if it wasn't
 */
    public E get(E value){
        //if e is null, return null
        if (value == null){
            return null;
        }
        //if tree is empty
        if (isEmpty()) {
            return null;
        }
        //if element was not gotten
        if(getRecursive(root, value) == null){
            return null;
        }else{
            return getRecursive(root, value).data;
        }

    }
/**
 * This method houses the recursive funtions to move through
 * the tree and find the reference to E value if it
 * exists in the tree.
 * @param root BSTNode root for currenet refernce
 * @param e date E to look for in the tree
 * @return BSTNode root if element id found, null if doesn't exist in tree 
 */
    private BSTNode<E> getRecursive(BSTNode<E> root, E e){
        if (root == null){
            return null;
        }
        // if e is less than curr data, move left
        if (root.data.compareTo(e) > 0){
            return getRecursive(root.left, e);
        // if e is greater than curr data, move right
        } else if (root.data.compareTo(e) < 0){
            return getRecursive(root.right, e);
        } else {
            return root;
        }
    }

    /**
     * This method overrides the toString method and
     * uses iterator to traverse through bst and add data
     * to the string, separated by commas enclosed in brackets.
     * @return string concatenation of all items in collection
     */
    @Override
    public String toString(){
        //create iterator
        Iterator<E> iterator = iterator();

        StringBuilder str = new StringBuilder();
        
        str.append("[");
        //while there is a next element
        while(iterator.hasNext()){
            //add value of E to string
            str.append(String.valueOf(iterator.next()));
            //add comma if another item will be added
            if (iterator.hasNext()){
                str.append(",");
            }
        }
        str.append("]");
        return str.toString();
    }

    /**
    * Produces tree like string representation of this BST.
    * @return string containing tree-like representation of this BST. 
    * @author Joanna Klukowska 
    */
    public String toStringTreeFormat() {
        StringBuilder s = new StringBuilder();
        preOrderPrint(root, 0, s);
        return s.toString(); 
    }
    /**
    * Uses pre-order traversal to produce a tree-like representation of this BST.
    * @param tree the root of the current subtree
    * @param level level (depth) of the current recursive call in the tree to
    * determine the indentation of each item
    * @param output the string that accumulated the string representation of this * BST
    * @author Joanna Klukowska
    */
    private void preOrderPrint(BSTNode<E> tree, int level, StringBuilder output) { 
        if (tree != null) {
        String spaces = "\n";
            if (level > 0) {
                for (int i = 0; i < level - 1; i++)
                        spaces += "   ";
                    spaces += "|--";
            }
            output.append(spaces);
            output.append(tree.data);
            preOrderPrint(tree.left, level + 1, output);
            preOrderPrint(tree.right , level + 1, output);
        }

        else { // print the null children
            String spaces = "\n"; 
            if (level > 0) {
                for (int i = 0; i < level - 1; i++) 
                    spaces += " ";
                    spaces += "|--";
                }
            output.append(spaces);
            output.append("null");
        }
    }
/**
 * This method when called creates and returns a new
 * BST preorderIterator
 * @return returns a new preorderIterator
 */
    public Iterator<E> preorderIterator(){
	    return new BSTpreorderIterator();
    }
/**
 * This method when called creates and returns a new
 * BST postorderIterator
 * @return returns a new postorderIterator
 */
    public Iterator<E> postorderIterator(){
	    return new BSTpostorderIterator();
    }
/**
 * This method accepts data of type E and then looks for 
 * an element in the bst greater than or equal to it,
 * calls the higher method to do so.
 * @param e data stored in node of type E
 * @return returns the least element in this set greater than or equal 
 * to the given element, or null if there is no such element.
 */
    public E ceiling (E e){
        //if e is null
        if (e == null){
            return null;
        }
        //if tree is empty
        if (isEmpty()){
            return null;
        }
        //if elemnt is in tree, return element
        if (contains(e)) {
            return e;
        }
        return higher(e);
    }
/**
 * This method creates a shallow copy of the
 * bst tree.
 * @return BST<E> clone object
 */
    public Object clone(){
        //returns a shallow copy of this tree instance
        Iterator<E> preorderIterator = preorderIterator();
        //create new BST
        BST<E> clone = new BST<E>();
        while (preorderIterator.hasNext()){
            clone.add(preorderIterator.next());
        }
        return clone;
    }
/**
 * This method returns the lowest element in the
 * bst tree.
 * @return data type E of the element of the lowest node in the bst
 */
    public E first(){
        //if root is null, return null
        if (root == null){
            return null;
        }
        // if tree is empty, return null
        if (isEmpty()){
            return null;
        }
        //make new node
        BSTNode<E> node = root;

        //while there is a left child
        while (node.left != null){
            //set node to node.left
            node = node.left;
        }
        //return node.data
        return node.data;
    }
/**
 * This method finds the greatest element in a set less
 * than or equal to the element parameter, calls lower method.
 * @param e data type E to be compare to data in BST nodes
 * @return E date type if there is an element less than or equal to e, 
 * null if this doesn't exist
 */
    public E floor (E e){
        //if e is null
        if (e == null){
            return null;
        }
        //if bst is empty
        if (isEmpty()){
            return null;
        }
        //if the tree has the element, return element
        if (contains(e)) {
            return e;
        }
        //call lower(e) to find right data value
        return lower(e);
    }
/**
 * This method calls the higher recursive method to find 
 * the least element in the BST strictly greater then the element E parameter.
 * @param e data type E to be compared to bst
 * @return E data in the node that satisfies higher conditions, 
 * or null if this doesn't exist
 */
    public E higher (E e){
        //if e is null
        if (e == null){
            return null;
        }
        //if bst is empty
        if (isEmpty()){
            return null;
        }
        //if last is less than or equal to e
        if (last().compareTo(e) <= 0){
            return null;
        }
        //call recursive method
        return higherRecursive(last(), root, e);

    }
/**
 * This method houses the recursive function to find the node with the data
 * that is the least element strictly greater than parameter e.
 * @param greater data type E that holds the current greatest data value
 * @param current BSTNode<E> that references current node being looked at
 * @param e data type E that is being compared to elements in tree
 * @return data type E of element that satisfires higher
 */
    private E higherRecursive(E greater, BSTNode<E> current, E e){
        //if node is null
        if (current == null){
            return greater;
        }
        //move to node current.right
        else if (current.data.compareTo(e) <= 0) {
            //call recursive function with current.right
            return higherRecursive(greater, current.right, e);
        }
        else{
            //move to node current.left
            if (greater.compareTo(current.data) > 0){
                //call recursive function with current.left and current.data
                return higherRecursive(current.data, current.left, e);
            }
            //call recursive function with current.left 
            return higherRecursive(greater, current.left, e);
        }
    }
/**
 * This method finds the greatest element in the bst.
 * @return data type E of the greatest element
 */
    public E last(){
        //if tree is empty
        if (isEmpty()){
            return null;
        }
        //if root is null
        if (root == null){
            return null;
        }
        //make new node
        BSTNode<E> node = root;
        //while there is a node.right
        while (node.right != null){
            //set current node to node.right
            node = node.right;
        }
        //return node.data
        return node.data;
    }
/**
 * This method calls the recursive function to finds the greatest element in the bst
 * strictly less than the element parameter.
 * @param e data type E to be compared in the tree
 * @return data type E of lower node or null if doesn't exist
 */
    public E lower (E e){
        //returns the greatest element in this set strictly less than the given elemtn, or null if there is none
        if (e == null){
            return null;
        }
        //if bst is empty
        if (isEmpty()){
            return null;
        }
        //if first is >= e return null
        if (first().compareTo(e) >= 0){
            return null;
        }
        //call recursive method
        return lowerRecursive(first(), root, e);
    }
/**
 * This is the recursive method that finds the greatest element in the bst
 * strictly less than the element parameter.
 * @param lower type E current lower
 * @param current BSTNode for current pointer in tree
 * @param e E data to compare to tree
 * @return data type E returns correct node data or null if doesn't exist
 */
    private E lowerRecursive(E lower, BSTNode<E> current, E e){
        //if current is null
        if (current == null){
            return lower;
        }
        //if compate to >=0, move to right node
        else if (current.data.compareTo(e) >= 0) {
            //call recursive with current.left
            return lowerRecursive(lower, current.left, e);
        }
        else{
            //if compate to <0, move to left node
            if (lower.compareTo(current.data) < 0){
                //call recursive with current.right
                return lowerRecursive(current.data, current.right, e);
            }
            //call recursive with current.night and lower
            return lowerRecursive(lower, current.right, e);
        }
    }
/**
 * This wrapper method error checks the data and then calls the 
 * recursive add function to add the data to the bst.
 * @param e data type E to add to tree
 * @return boolean, true if element was added, false if wasn't
 */
    public boolean add (E e){
        //if e is null
        if (e == null){
            return false;
        }
        //if e is already in collection
        if (contains(e)){
            return false;
        }
        //call recursive function
        root = addRecursive(root, e);
        //increment size
        size += 1;
        return true;
    }
/**
 * This recursive function adds a node with data type E to the
 * bst tree.
 * @param root BSTNode to keep current reference in tree
 * @param e data type E to add to the tree
 * @return BSTNode that was added to the tree
 */
    private BSTNode<E> addRecursive(BSTNode<E> root, E e) {
        //if root is null
        if (root == null){
            root = new BSTNode<E>(e);
        }
        // if root>e, move left
        if (root.data.compareTo(e) > 0) {
            //call recursive on root.left and set equal to root.left
            root.left = addRecursive(root.left, e);
        //if root<e, move right
        } else if (root.data.compareTo(e) < 0){
            //call recursive on root.right and set equal to root.righ
            root.right = addRecursive(root.right, e);
        }

    return root;
    
    }

/**
 * This method throws an error if called
 * @param c Collection
 * @throws UnsupportedOperationException
 */
    public boolean addAll(Collection<? extends E> c) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("addAll not supported");
    }
/**
 * This method clears data from bst and sets size to zero
 */
    public void clear(){
        root = null;
        size = 0;
    }
/**
 * This wrapper method error checks and then calls the recursive method to
 * to see if object is currently in the bst.
 * @param o object to be passed in
 * @return boolean value, false if bst doesn't contain, true if it does
 */
    public boolean contains(Object o){
        //Returns true if this collection contains the specified element.
        if (o == null){
            return false;
        }
        //if bst is empty
        if (isEmpty()) {
            return false;
        }
        //if contains is false
        if (containsRecursive(root, o) == null){
            return false;
        }
        return true;

    }
/**
 * This recursive method moves through the tree to see if
 * object is located as data in the tree
 * @param root BSTNode to keep current reference in tree
 * @param o object to be compared to elements in tree
 * @return BSTNode tree reference that contains object 
 */
    private BSTNode<E> containsRecursive(BSTNode<E> root, Object o){
        //cast o to type E
        E obj = (E)o;

        // if root is null or obj is equal to root
        if (root == null || root.data.compareTo(obj) == 0){
            return root;
        }
        //root> obj move left
        if (root.data.compareTo(obj) > 0){
            //call recursive on root.left
            return containsRecursive(root.left, obj);
        }
        //call recursive on root.right
        return containsRecursive(root.right, obj);
    }
/**
 * This method calls contains on each element to see if all elements of first
 * collection are in the collection.
 * @param c collection to be compared to BST
 * @return boolean true if collection contains all elements, false if any are missing
 */
    public boolean containsAll(Collection<?> c){
        //for each object in the collection
        for (Object o: c){
            //if contains is false, return false
            if(contains(o) == false){
                return false;
            }
        }
        return true;

    }
/**
 * This method compares an object to a collection to see if they 
 * contain the same elements.
 * @param o object to be compared to specific collection
 * @return boolean value, true if collections contain same elements, false if any are missing
 */
    public boolean equals(Object o){
        //check to see if classes are the same
        if (this.getClass() == o.getClass() && this == o) {
            return true;
        }
        // cast obj as BST<?> so they are comparable
        BST<?>objB = (BST<?>)o;
        //checks if size is same
        if (objB.size() != size()) {
            return false;
        }
        //for each obj in objB, check contains
        for (Object obj : objB) {
            //if each node has same data is false
            if(!contains(obj)) {
                return false;
            }
        }
        return true;

    }

/**
 * This method throws an exception if called
 * @throws UnsupportedOperationException
 */
    public int hashCode() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("hashCode not supported");
    }
/**
 * This method checks to see if any elements exist in the bst.
 * @return boolean true if root is null, false otherwise
 */
    public boolean isEmpty(){
        //Returns true if this collection contains no elements.
        if (root == null){
            return true;
        }
        return false;
    }
/**
 * This method when called creates and returns a new
 * BSTinorderIterator
 * @return returns a new inorderIterator
 */
    public Iterator<E> iterator(){
        //Returns an iterator over the elements in this collection. The elements should be 
        //returned in the order determined by the inorder traver- sal of the tree.
	    return new BSTinorderIterator();

    }
 
/**
 * This method error checks and calls the remove recursive method
 * to remove an element from the BST. This method was written based off 
 * of remove() code discussed in class.
 * @param o object to be removed from a BST
 * @return boolean value, true if removed, false if not
 */
    public boolean remove(Object o){
        //if object is null
        if (o == null) {
            return false;
        }
        //if bst is empty
        if (isEmpty()) {
            return false;
        }
        //if recursive is null
        if (removeRecursive(root, o) == null){
            return false;
        }
        //decrease size by 1
        size -=1;
        return true;
        
    }
/**
 * This method houses the recursive method to remove a node from a BST
 * @param root BSTNode to keep reference in tree
 * @param o object to be removes from tree
 * @return BSTNode reference of removed object
 */
    private BSTNode<E> removeRecursive(BSTNode<E> root, Object o){
        //cast object to E
        E obj = (E)o;
        //if root is null
        if (root == null){
            return null;
        }
        //if bst doesn't contain element
        if (contains(o)== false){
            return null;
        }
        //if root < obj
        if (root.data.compareTo(obj) < 0){
            //call recursive with root.right
            root.right = removeRecursive(root.right, obj);
        } 
        //if root > 0
        else if (root.data.compareTo(obj) > 0){
            //call recursive with root.left
            root.left = removeRecursive(root.left, obj);
        }else{
            //if node has only 1 child
            if (root.right == null){
                return root.left;
            }else if (root.left == null){
                return root.right;
            }
            //set new root to minvalue of right tree
            root.data = minValue(root.right);
            //remove replaced node
            root.right = removeRecursive(root.right, root.data);
        }
        return root;
    }
/**
 * This method is a helper method for remove and finds the minimum 
 * value in the bst.
 * @param root BSTNode to keep reference in tree
 * @return data type E of minValue
 */
    public E minValue(BSTNode<E> root){
        //set minValue to root.data
        E minValue = root.data;
        //while root.left exists
        while (root.left != null){
            //set minValue to root.left
            minValue = root.left.data;
            //move root to root.left
            root = root.left;
        }
        return minValue;
    }

/**
* This method throws an error if called
* @param c Collection
* @throws UnsupportedOperationException
*/
    public boolean removeAll(Collection<?> c) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("removeAll not supported");
    }

/**
* This method throws an error if called
* @param c Collection
* @throws UnsupportedOperationException
*/
    public boolean retainAll(Collection<?> c) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("retainAll not supported");
    }

/**
 * This method retrieves the private int size for the BST
 * @return int size
 */
    public int size(){
        //Returns the number of elements in this collection.
        return size;
    }

/**
 * This method returns an array containing all of the elements in this collection.
 * @return array of objects
 */
    public Object[] toArray(){
        //creates new array of objects using size()
        Object[] array = new Object[size()];

        //make new inorder iterator
        Iterator<E> iterator = iterator();
        int i = 0;
        //while iterator hasNext
        while(iterator.hasNext()) {
            //add node to array
            array[i] = iterator.next();
            //increment i
            i = i + 1;
        }
        
        return array;
    }

/**
* This method either adds linkedlist to an existing array
* or creates a new array of correct size and then adds
* linkedlist.
* @param a T[]
* @return array of type T
*/
    public <T> T[] toArray (T[] a){
        //if a is too small
        if(a.length < size()){
            //create new array
            T[] A = (T[]) Array.newInstance(a.getClass().getComponentType(), size());
            //create inorder iterator
            Iterator<E> iterator = iterator();
            int i = 0;
            //while iterator has next
            while (iterator.hasNext()){
                //add next element to array A
                A[i] = (T) iterator.next();
                //increment i
                i += 1;
            }
            return A;
        }
        //if size is okay
        else{
            ///create iteratos
            Iterator<E> iterator = iterator();
            int i = 0;
            //while iterator has next
            while (iterator.hasNext()){
                //add node to array
                a[i] = (T)iterator.next();
                //increment i
                i += 1;
            }
            return a;
        } 
    }
}