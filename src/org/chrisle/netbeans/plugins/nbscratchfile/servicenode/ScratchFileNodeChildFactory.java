package org.chrisle.netbeans.plugins.nbscratchfile.servicenode;

import java.io.File;
import java.util.List;
import org.openide.nodes.ChildFactory;

/**
 *
 * @author Chrl
 */
public class ScratchFileNodeChildFactory extends ChildFactory<File> {
    private File scratchDir;

    public ScratchFileNodeChildFactory(File scratchDir) {
        this.scratchDir = scratchDir;
    }

    @Override
    protected boolean createKeys(List<File> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}