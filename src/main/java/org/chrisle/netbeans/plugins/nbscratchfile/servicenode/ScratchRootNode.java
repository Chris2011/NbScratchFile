/*
 * Copyright 2017 Chris2011.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chrisle.netbeans.plugins.nbscratchfile.servicenode;

import org.netbeans.api.core.ide.ServicesTabNodeRegistration;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author Chris2011
 */
@ServicesTabNodeRegistration(
    name = "ScratchRootNode",
    displayName = "Scratches",
    shortDescription = "Saved scratches",
    iconResource = "org/chrisle/netbeans/plugins/nbscratchfile/add_file.png",
    position = 2021
)
public class ScratchRootNode extends AbstractNode {
    public ScratchRootNode() {
        super(Children.create(new ScratchRootNodeChildFactory(), true));
        super.setName("Scratches");
        super.setShortDescription("Saved scratches");
        super.setIconBaseWithExtension("org/chrisle/netbeans/plugins/nbscratchfile/add_file.png");
    }
}
