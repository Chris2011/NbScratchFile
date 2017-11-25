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

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Chris2011
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
