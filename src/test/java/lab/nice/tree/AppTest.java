package lab.nice.tree;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void treeTest() {
        TreeNode<String> root = new TreeNode<>("root");
        TreeNode<String> a = new TreeNode<>("a");
        a.addChild("1");
        a.addChild("2");
        a.addChild("3");
        root.addChild(a);

        TreeNode<String> b = new TreeNode<>("b");
        b.addChild("4");
        b.addChild("5");
        b.addChild("6");
        root.addChild(b);

        root.addChild("c");

        Iterator<TreeNode<String>> iterator = new BFSTreeIterator<>(root);
        StringBuffer buffer = new StringBuffer();
        iterator.forEachRemaining(node -> buffer.append(node.getData()));
        Assert.assertEquals("rootabc123456", buffer.toString());
        Assert.assertEquals("a is in level 1", 1, a.getLevel());
    }
}
