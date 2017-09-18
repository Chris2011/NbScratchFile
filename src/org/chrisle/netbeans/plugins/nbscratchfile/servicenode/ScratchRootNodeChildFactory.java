package org.chrisle.netbeans.plugins.nbscratchfile.servicenode;

/**
 *
 * @author Chris
 */
import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;

public class ScratchRootNodeChildFactory extends ChildFactory<File> {
    @Override
    protected boolean createKeys(List<File> scratchDirs) {
        File[] directories = new File(String.format("%s/.netbeans/scratches", System.getProperty("user.home"))).listFiles((File pathname) -> pathname.isDirectory());

        scratchDirs.addAll(Arrays.asList(directories));

        return true;
    }

    @Override
    protected Node createNodeForKey(File key) {
//        Node result = new AbstractNode(Children.create(new ScratchRootNodeChildFactory(_host), true), Lookups.singleton(key));
        AbstractNode result = new AbstractNode(Children.create(new ScratchFileNodeChildFactory(key), true), Lookups.singleton(key));

        result.setDisplayName(key.getName());
        result.setIconBaseWithExtension("org/chrisle/netbeans/plugins/nbscratchfile/resources/folder.png");

        return result;
    }
}