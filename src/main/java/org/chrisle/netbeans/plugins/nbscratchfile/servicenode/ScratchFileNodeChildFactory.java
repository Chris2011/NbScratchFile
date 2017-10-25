package org.chrisle.netbeans.plugins.nbscratchfile.servicenode;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Node;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
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
        Node result = null;

        try {
            DataObject dataObject = DataObject.find(FileUtil.toFileObject(key));

            result = dataObject.getNodeDelegate();
            result.setDisplayName(key.getName());

        } catch (DataObjectNotFoundException ex) {
            result = new AbstractNode(Children.LEAF, Lookups.singleton(key));
        }

        return result;
    }
}
