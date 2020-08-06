package lab.nice.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Breadth First Search iterator for tree
 * It must from root to construct
 *
 * @param <T>
 */
public class BFSTreeIterator<T> implements Iterator<TreeNode<T>> {

    private final Queue<TreeNode<T>> current = new LinkedList<>();
    private final Queue<TreeNode<T>> nextLevel = new LinkedList<>();
    private STAGE stage;

    public BFSTreeIterator(TreeNode<T> root) {
        this.stage = STAGE.CURRENT;
        current.add(root);
    }

    @Override
    public boolean hasNext() {
        return !(current.isEmpty() && nextLevel.isEmpty());
    }

    @Override
    public TreeNode<T> next() {
        TreeNode<T> node;
        if (stage == STAGE.CURRENT) {
            node = current.remove();
            if (!node.isLeaf()) {
                nextLevel.addAll(node.getChildren());
            }
            if (current.isEmpty()) {
                stage = STAGE.CHILD;
            }
        } else {
            node = nextLevel.remove();
            if (!node.isLeaf()) {
                current.addAll(node.getChildren());
            }
            if (nextLevel.isEmpty()) {
                stage = STAGE.CURRENT;
            }
        }
        return node;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove is not supported");
    }

    @Override
    public void forEachRemaining(Consumer<? super TreeNode<T>> action) {
        while (hasNext()) {
            action.accept(next());
        }
    }

    private enum STAGE {
        CURRENT, CHILD
    }
}
