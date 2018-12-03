package project6;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.lang.Iterable;
import java.util.Iterator;
import java.util.ArrayList;


public class BST<E> extends Comparable<E> implements Collection<E>, Iterable<E> {
    
    private BSTNode<E> root = null;
    private int size = 0;

    //default constructor
    public BST(){

    }

    private class BSTNode<E> {

        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        //BSTNode(E data, BSTNode<E> left, BSTNode<E> right){
        BSTNode(E data){
            this.data = data;
            this.left = null;
            this.right = null;
        }

    }

    public class BSTinorderIterator implements Iterator<E>{
        
        BSTNode<E> current = root;
        ArrayList<E> inList = new ArrayList<E>();
        int index = 0;

        public BSTinorderIterator(){
            inorderIteratorRecursive(root);
        }

        private void inorderIteratorRecursive(BSTNode<E> root){
            if (root!= null){
                inorderIteratorRecursive(root.left);
                inList.add(root.data);
                inorderIteratorRecursive(root.right);
            }
        }

        /**
         * This method returns the data inside current node
         * and then moves the pointer to current.next
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

        public boolean hasNext(){
            if (index < inList.size()){
                return true;
            }
            return false;
        }
    }

    public class BSTpreorderIterator implements Iterator<E>{
        BSTNode<E> current = root;
        ArrayList<E> preList = new ArrayList<E>();
        int index = 0;

        public BSTpreorderIterator(){
            preorderIteratorRecursive(root);
        }

        private void preorderIteratorRecursive(BSTNode<E> root){
            if (root!= null){
                preList.add(root.data);
                preorderIteratorRecursive(root.left);
                preorderIteratorRecursive(root.right);
            }
        }

        /**
         * This method returns the data inside current node
         * and then moves the pointer to current.next
         * @return data stored inside the node
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

        public boolean hasNext(){
            if (index < preList.size()){
                return true;
            }
            return false;
        }
    }

    public class BSTpostorderIterator implements Iterator<E>{
        BSTNode<E> current = root;
        ArrayList<E> postList = new ArrayList<E>();
        int index = 0;

        public BSTpostorderIterator(){
            postorderIteratorRecursive(root);
        }

        private void postorderIteratorRecursive(BSTNode<E> root){
            if (root!= null){
                postorderIteratorRecursive(root.left);
                postorderIteratorRecursive(root.right);
                postList.add(root.data);
            }
        }
        
        public E next() throws NoSuchElementException{
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E nextValue = postList.get(index);
            index += 1;
            return nextValue;
        }
        
        public boolean hasNext(){
            if (index < postList.size()){
                return true;
            }
            return false;
        }
    }

    public E get(E value){
    }

    public String toString(){

    }

    /**
    * Produces tree like string representation of this BST.
    * @return string containing tree-like representation of this BST. 
    * @author Joanna Klukowski
    */
    public String toStringTreeFormat() {
        StringBuilder s = new StringBuilder();
        preOrderPrint(root, 0, s);
        return s.toString(); 
    }
    /*
    * Uses pre-order traversal to produce a tree-like representation of this BST.
    * @param tree the root of the current subtree
    * @param level level (depth) of the current recursive call in the tree to
    * determine the indentation of each item
    * @param output the string that accumulated the string representation of this * BST
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

    public Iterator<E> preorderIterator(){
	    return new BSTpreorderIterator();
    }

    public Iterator<E> postorderIterator(){
	    return new BSTpostorderIterator();
    }

    public E ceiling (E e){
        //see if data is null or exists
        //returns the least element in this set greateer than or equal to the given element of null if it doesn't exist
        if (e == null){
            return null;
        }

        if (root.data.compareTo(e)<0){
            root = root.right;
        }
        if (root.data.comparTo(e) == 1){
            root = root.left;
        }

    }

    public Object clone(){
        //returns a shallow copy of this tree instance
    }

    public E first(){
        if (root == null){
            return null;
        }
        while (root.left != null){
            root = root.left;
        }
        return root.data;
    }

    public E floor (E e){
        //returns the greatest elemenr in this set less than of equal to the given element, or null if there isnt
    }

    public E higher (E e){
        //returns the least element in this set strictly greater than the given element, or null if there isnt

    }

    public E last(){
        if (root == null){
            return null;
        }
        while (root.right != null){
            root = root.right;
        }
        return root.data;
    }

    public E lower (E e){
        //returns the greatest element in this set strictly less than the given elemtn, or null if there is none
        if (root == null){

        }

    }

    public boolean add (E e){
        //Ensures that this collection contains the specified element
        if (e == null){
            return false;
        }

	    root = addRecursive(root, e);
        return true;
    }

    BSTNode<E> addRecursive(BSTNode<E> root, E e) {

        if (root == null){
            root = new BSTNode<E>(e);
            size += 1;
        }

        if (root.data.comparTo(e)==1){
            root.left = addRecursive(root.left, e);
            size += 1;
        } else if (root.data.compareTo(e)<0){
            root.right = addRecursive(root.right, e);
            size += 1;
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

    public void clear(){
        root = null;
    }

    public boolean contains(Object o){
        //Returns true if this collection contains the specified element.

    }

    public boolean containsAll(Collection<?> c){
        //Returns true if this collection contains all of the elements in the specified collection.

    }

    public boolean equals(Object o){
        //Compares the specified object with this collection for equality.

    }

/**
 * This method throws an exception if called
 * @throws UnsupportedOperationException
 */
    public int hashCode() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("hashCode not supported");
    }

    public boolean isEmpty(){
        //Returns true if this collection contains no elements.
    }

    public Iterator<E> iterator(){
        //Returns an iterator over the elements in this collection. The elements should be 
        //returned in the order determined by the inorder traver- sal of the tree.
	    return new BSTinorderIterator();

    }

    public boolean remove (Object o){
        //Removes a single instance of the specified element from this collection, if it is present.
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

    public int size(){
        //Returns the number of elements in this collection.
    }

    public Object[] toArray(){
        //Returns an array containing all of the elements in this collection.

    }

    public <T> T[] toArray (T[] a){
        //Returns an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array.
    }

}
