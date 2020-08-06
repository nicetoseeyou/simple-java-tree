package lab.nice.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TreeNode<T> {
    private T data;
    private TreeNode<T> parent;
    private List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        children = new LinkedList<>();
    }

    /**
     * indicate whether the tree node is root of tree
     *
     * @return true if root of tree
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * indicate whether the tree node is leaf of tree
     *
     * @return true if leaf of tree
     */
    public boolean isLeaf() {
        return children.size() == 0;
    }

    /**
     * add node as child node
     *
     * @param child the child data to add
     */
    public void addChild(T child) {
        TreeNode<T> childNode = new TreeNode<>(child);
        childNode.parent = this;
        children.add(childNode);
    }

    public void addChild(TreeNode<T> childNode) {
        childNode.parent = this;
        children.add(childNode);
    }

    /**
     * The level of the tree (zero-based)
     *
     * @return the level of the tree
     */
    public int getLevel() {
        if (this.isRoot()) {
            return 0;
        } else {
            return this.parent.getLevel() + 1;
        }
    }

    /**
     * find the first matched child
     *
     * @param searchCriteria the search criteria
     * @return null if none matched, the first node matched if find
     */
    public TreeNode<T> findChild(Comparable<T> searchCriteria) {
        if (!isLeaf()) {
            for (TreeNode<T> node : children) {
                T val = node.data;
                if (searchCriteria.compareTo(val) == 0) {
                    return node;
                }
            }
        }
        return null;
    }

    /**
     * find all matched children
     *
     * @param searchCriteria the search criteria
     * @return null if none matched, all matched nodes in list if any matched
     */
    public List<TreeNode<T>> searchChild(Comparable<T> searchCriteria) {
        if (!isLeaf()) {
            List<TreeNode<T>> list = new LinkedList<>();
            for (TreeNode<T> node : children) {
                T val = node.data;
                if (searchCriteria.compareTo(val) == 0) {
                    list.add(node);
                }
            }
            if (list.size() > 0) {
                return list;
            }
        }
        return null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TreeNode<?> treeNode = (TreeNode<?>) o;
        return Objects.equals(data, treeNode.data) &&
                Objects.equals(parent, treeNode.parent) &&
                Objects.equals(children, treeNode.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, parent, children);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                ", parent=" + parent +
                ", children=" + children +
                '}';
    }
}
