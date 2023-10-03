import material.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * This class represents a tree data structure using a linked implementation.
 * It implements the NAryTree interface.
 *
 * @param <E> the type of element stored in the tree
 */
public class LinkedTree<E> implements NAryTree<E> {
    private class TreeNode<E> implements Position<E>{
        private E element;
        private TreeNode<E> parent;

        public TreeNode(E element, TreeNode<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public TreeNode(E element) {
            this.element = element;
        }

        private List<TreeNode<E>> children = new ArrayList<>();

        @Override
        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public TreeNode<E> getParent() {
            return parent;
        }

        public void setParent(TreeNode<E> parent) {
            this.parent = parent;
        }

        public List<TreeNode<E>> getChildren() {
            return children;
        }

        public void setChildren(List<TreeNode<E>> children) {
            this.children = children;
        }
    }
    private TreeNode<E> root;
    private int size;
    @Override
    public Position<E> addRoot(E e) {
        if (!this.isEmpty()){throw new RuntimeException("The tree already has a root!");}
        root = new TreeNode<E>(e);
        size++;
        return root;
    }

    private TreeNode<E> checkPosition(Position<E> p){
        if (!(p instanceof TreeNode<E>)){throw new RuntimeException("The position is invalid");}
        return (TreeNode<E>) p;
    }

    @Override
    public Position<E> add(E element, Position<E> p) {
        TreeNode<E> parent = checkPosition(p);
        TreeNode<E> newNode = new TreeNode<>(element, parent);
        parent.getChildren().add(newNode);
        size++;
        return newNode;
    }

    private static <E> void checkPositionOfChildrenList(int n, LinkedTree<E>.TreeNode<E> parent){
        if(n< 0|| n > parent.getChildren().size()){throw new RuntimeException("The position is invalid");}
    }

    @Override
    public Position<E> add(E element, Position<E> p, int n) {
        TreeNode<E> parent = checkPosition(p);
        checkPositionOfChildrenList(n, parent);
        TreeNode<E> newNode = new TreeNode<>(element, parent);
        parent.getChildren().add(newNode);
        size++;
        return newNode;
    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2) {
        TreeNode<E> n1 = checkPosition(p1);
        TreeNode<E> n2 = checkPosition(p2);
        E aux = n1.getElement();
        n1.element = n2.getElement();
        n2.element = aux;
    }

    @Override
    public E replace(Position<E> p, E e) {
        TreeNode<E> node = checkPosition(p);
        E old = node.getElement();
        node.element = e;
        return old;
    }

    @Override
    public void remove(Position<E> p) {
        TreeNode<E> node = checkPosition(p);
        if (node == root){
            root = null;
            size = 0;
        }else{
            node.getParent().getChildren().remove(node);
        }
    }

    private int computeSize(TreeNode<E> node){
        int size =  1;
        for (TreeNode<E> child: node.getChildren()) {
            size += computeSize(child);
        }
        return size;
    }

    @Override
    public NAryTree<E> subTree(Position<E> v) {
        TreeNode<E> node = checkPosition(v);
        LinkedTree<E> tree = new LinkedTree<>();
        tree.root = node;
        tree.size = computeSize(node);
        return tree;

    }

    @Override
    public void attach(Position<E> p, NAryTree<E> t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<E> root() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> parent(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isInternal(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isLeaf(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isRoot(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<Position<E>> iterator() {
        List<Position<E>>positions = new ArrayList<>();

        return positions.iterator();
    }

    private void breadthOrder(TreeNode<E> root, List<Position<E>> positions){
        if (root != null){
            List<TreeNode> queue = new ArrayList<>();
            queue.add(root);
            while(!queue.isEmpty()){
                TreeNode<E> toExplore = queue.remove(0);
                positions.add(toExplore);
                queue.addAll(root.getChildren());
            }
        }
    }

    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
