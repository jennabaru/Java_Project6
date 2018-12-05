package project6;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.lang.Iterable;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.Comparable;
import java.util.Iterator;



public class BST<E extends Comparable<E>> implements Collection<E>, Iterable<E> {

    // public static void main(String[] args){
    //     BST<Integer> a = new BST();
    //     a.add(50);
    //     a.add(30);
    //     a.add(70);
    //     a.add(20);
    //     a.add(40);
    //     a.add(60);
    //     a.add(80);
    //     a.add(25);

    //     System.out.println(a.higher(26));
    //     System.out.println(a.higher(70));
    //     System.out.println(a.higher(80));
    //     System.out.println(a.higher(19));
    // }
    
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
        //if e if null, return null
        if (value == null){
            return null;
        }
        if(getRecursive(root, value) == null){
            return null;
        }else{
            return getRecursive(root, value).data;
        }

    }

    private BSTNode<E> getRecursive(BSTNode<E> root, E e){
        if (root == null){
            return null;
        }
        if (root.data.compareTo(e) > 0){
            return getRecursive(root.left, e);
        } else if (root.data.compareTo(e) < 0){
            return getRecursive(root.right, e);
        } else {
            //return root.data;
            return root;
        }
        //return null;
    }


    @Override
    public String toString(){
        
        Iterator<E> iterator = iterator();

        StringBuilder str = new StringBuilder();
        
        str.append("[");
        while(iterator.hasNext()){
            //check to see if this line is correct, not sure
            str.append(String.valueOf(iterator.next()));
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
        if (e == null){
            return null;
        }
        if (isEmpty()){
            return null;
        }
        //see if data is null or exists
        //returns the least element in this set greateer than or equal to the given element of null if it doesn't exist
        if (contains(e)) {
            return e;
        }
        return higher(e);
    }

    public Object clone(){
        //returns a shallow copy of this tree instance
        //chnage this maybe!
        Iterator<E> preorderIterator = preorderIterator();
        BST<E> clone = new BST<E>();
        while (preorderIterator.hasNext()){
            clone.add(preorderIterator.next());
        }
        return clone;
    }

    public E first(){
        if (root == null){
            return null;
        }
        BSTNode<E> node = root;
        while (node.left != null){
            node = node.left;
        }
        return node.data;
    }

    public E floor (E e){
        if (e == null){
            return null;
        }
        if (isEmpty()){
            return null;
        }
        //returns the greatest elemenr in this set less than of equal to the given element, or null if there isnt
        if (contains(e)) {
            return e;
        }
        return lower(e);
    }

    public E higher (E e){
        if (e == null){
            return null;
        }
        if (isEmpty()){
            return null;
        }
        //returns the least element in this set strictly greater than the given element, or null if there isnt
        if (last().compareTo(e) <= 0){
            return null;
        }
        return higherRecursive(last(), root, e);

    }

    private E higherRecursive(E greater, BSTNode<E> current, E e){
        if (current == null){
            return greater;
        }
        else if (current.data.compareTo(e) <= 0) {
            //righ
            return higherRecursive(greater, current.right, e);
        }
        else{
            //left
            if (greater.compareTo(current.data) > 0){
                return higherRecursive(current.data, current.left, e);
            }
            return higherRecursive(greater, current.left, e);
        }
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        if (root == null){
            return null;
        }
        BSTNode<E> node = root;
        while (node.right != null){
            node = node.right;
        }
        return node.data;
    }

    public E lower (E e){
        //returns the greatest element in this set strictly less than the given elemtn, or null if there is none
        if (e == null){
            return null;
        }
        if (isEmpty()){
            return null;
        }
        if (first().compareTo(e) >= 0){
            return null;
        }
        return lowerRecursive(first(), root, e);
    }

    private E lowerRecursive(E lower, BSTNode<E> current, E e){
        if (current == null){
            return lower;
        }
        else if (current.data.compareTo(e) >= 0) {
            //righ
            return lowerRecursive(lower, current.left, e);
        }
        else{
            //left
            if (lower.compareTo(current.data) < 0){
                return lowerRecursive(current.data, current.right, e);
            }
            return lowerRecursive(lower, current.right, e);
        }
    }

    public boolean add (E e){
        //Ensures that this collection contains the specified element
        if (e == null){
            return false;
        }
        if (contains(e)){
            return false;
        }
        root = addRecursive(root, e);
        size += 1;
        return true;
    }

    private BSTNode<E> addRecursive(BSTNode<E> root, E e) {

        if (root == null){
            root = new BSTNode<E>(e);
        }

        if (root.data.compareTo(e) > 0) {
            root.left = addRecursive(root.left, e);
        } else if (root.data.compareTo(e) < 0){
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

    public void clear(){
        root = null;
        size = 0;
    }

    public boolean contains(Object o){
        //Returns true if this collection contains the specified element.
        if (o == null){
            return false;
        }
        if (containsRecursive(root, o) == null){
            return false;
        }
        return true;

    }

    private BSTNode<E> containsRecursive(BSTNode<E> root, Object o){

        E obj = (E)o;

        if (root == null || root.data.compareTo(obj) == 0){
            return root;
        }

	if (root.data.compareTo(obj) > 0){
            return containsRecursive(root.left, obj);
	}
            return containsRecursive(root.right, obj);
    }

    public boolean containsAll(Collection<?> c){
        //Returns true if this collection contains all of the elements in the specified collection.
        //for each item in collection, if contains returns false, return false, else return true
        
        for (Object o: c){
            if(contains(o) == false){
                return false;
            }
        }
        return true;

    }

    public boolean equals(Object o){
        //Compares the specified object with this collection for equality.

        if (this.getClass() == o.getClass() && this == o) {
            return true;
        }

        BST<?>objB = (BST<?>)o;
        //checks if size is same
        if (objB.size() != size()) {
            return false;
        }
    

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

    public boolean isEmpty(){
        //Returns true if this collection contains no elements.
        if (root == null){
            return true;
        }
        return false;
    }

    public Iterator<E> iterator(){
        //Returns an iterator over the elements in this collection. The elements should be 
        //returned in the order determined by the inorder traver- sal of the tree.
	    return new BSTinorderIterator();

    }

    //reference picture from class
    public boolean remove(Object o){
        // //Removes a single instance of the specified element from this collection, if it is present.
        // if (removeRecursive(root, o) == null){
        //     return false;
        // }
        return true;
        
    }

    private BSTNode<E> removeRecursive(BSTNode<E> root, Object o){
        E obj = (E)o;
        if (root == null){
            return root;
        }else if (contains(o)== false){
            return null;
        }else if (root.data.compareTo(obj)==1){
            root.left = addRecursive(root.left, obj);
        } else if (root.data.compareTo(obj)<0){
            root.right = addRecursive(root.right, obj);
        }
        return null;
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
        return size;
    }

    public Object[] toArray(){
        //Returns an array containing all of the elements in this collection.
        Object[] array = new Object[size()];

        //make new node, set to head
        //BSTNode<E> current = this.head;

        Iterator<E> iterator = iterator();
        int i = 0;
        while(iterator.hasNext()) {
            //add node to array
            array[i] = iterator.next();
            i = i + 1;
        }
        return array;

    }

    public <T> T[] toArray (T[] a){
        //Returns an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array.
        T[] A = (T[]) Array.newInstance(a.getClass().getComponentType(), size());

        Iterator<E> iterator = iterator();
        int i = 0;
        while (iterator.hasNext()){
            A[i] = (T) iterator.next();
            i += 1;
        }
        return A;
    }


}

