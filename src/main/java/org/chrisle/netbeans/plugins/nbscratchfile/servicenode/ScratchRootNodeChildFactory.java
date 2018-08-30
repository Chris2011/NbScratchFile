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

/**
 *
 * @author Chris2011
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
        AbstractNode result = new AbstractNode(Children.create(new ScratchFileNodeChildFactory(key), true), Lookups.singleton(key));

        result.setDisplayName(key.getName());
        result.setIconBaseWithExtension("org/chrisle/netbeans/plugins/nbscratchfile/folder.png");

        return result;
    }
}