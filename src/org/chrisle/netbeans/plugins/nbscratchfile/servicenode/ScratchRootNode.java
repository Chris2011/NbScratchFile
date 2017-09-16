package org.chrisle.netbeans.plugins.nbscratchfile.servicenode;

import javax.swing.AbstractAction;
import org.openide.awt.ActionID;
import javax.swing.Action;
import javax.swing.event.ChangeListener;
import org.netbeans.api.core.ide.ServicesTabNodeRegistration;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

@ActionID(
    category = "Team",
    id = "org.chrisle.netbeans.modules.gitrepoviewer.service.GitRepoNode"
)
@ServicesTabNodeRegistration(
        name = "ScratchRootNode",
        displayName = "Scratches",
        shortDescription = "Saved scratches",
        iconResource = "org/chrisle/netbeans/plugins/nbscratchfile/resources/add_file.png",
        position = 2021)
public class ScratchRootNode extends AbstractNode {
    ChangeListener _listener;
    ScratchRootNodeChildFactory _scratchRootNodeChildFactory;
    AbstractAction hostAction;

    public ScratchRootNode() {
        super(Children.create(new ScratchRootNodeChildFactory(), true));
        super.setName("Scratches");
        super.setShortDescription("Saved scratches");
        super.setIconBaseWithExtension("org/chrisle/netbeans/plugins/nbscratchfile/resources/add_file.png");
    }

    @Override
    public Action[] getActions(boolean context) {
        Action[] result = new Action[] {
            hostAction
        };
        return result;
    }
}