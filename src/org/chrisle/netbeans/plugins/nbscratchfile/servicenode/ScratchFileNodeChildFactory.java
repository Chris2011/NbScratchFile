package org.chrisle.netbeans.plugins.nbscratchfile.servicenode;

import java.io.File;
import java.util.List;
import org.openide.nodes.ChildFactory;

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
        return true;
    }
}