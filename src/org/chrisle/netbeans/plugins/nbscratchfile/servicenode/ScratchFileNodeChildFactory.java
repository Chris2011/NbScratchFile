package org.chrisle.netbeans.plugins.nbscratchfile.servicenode;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.tools.FileObject;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Node;
import org.openide.nodes.Children;
import org.openide.nodes.ChildFactory;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Chrl
 */
public class ScratchFileNodeChildFactory extends ChildFactory<File> {
    private final File scratchDir;

    public ScratchFileNodeChildFactory(File scratchDir) {
        this.scratchDir = scratchDir;
    }

    @Override
    protected boolean createKeys(List<File> list) {
        list.addAll(Arrays.asList(new File(this.scratchDir.getPath()).listFiles()));

        return true;
    }

    @Override
    protected Node createNodeForKey(File key) {
        AbstractNode result = new AbstractNode(Children.LEAF, Lookups.singleton(key));

        result.setDisplayName(key.getName());
//        result.setIconBaseWithExtension("org/chrisle/netbeans/plugins/nbscratchfile/resources/folder.png");
//        result.setIconBaseWithExtension(key.to);

        return result;
    }
}
