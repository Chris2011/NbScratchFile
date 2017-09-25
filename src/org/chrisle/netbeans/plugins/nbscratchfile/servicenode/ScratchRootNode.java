package org.chrisle.netbeans.plugins.nbscratchfile.servicenode;

import org.openide.awt.ActionID;
import org.netbeans.api.core.ide.ServicesTabNodeRegistration;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

@ActionID(
    category = "Tools",
    id = "org.chrisle.netbeans.plugins.nbscratchfile.servicenode.ScratchRootNode"
)
@ServicesTabNodeRegistration(
        name = "ScratchRootNode",
        displayName = "Scratches",
        shortDescription = "Saved scratches",
        iconResource = "org/chrisle/netbeans/plugins/nbscratchfile/resources/add_file.png",
        position = 2021)
public class ScratchRootNode extends AbstractNode {
    public ScratchRootNode() {
        super(Children.create(new ScratchRootNodeChildFactory(), true));
        super.setName("Scratches");
        super.setShortDescription("Saved scratches");
        super.setIconBaseWithExtension("org/chrisle/netbeans/plugins/nbscratchfile/resources/add_file.png");
    }
}