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
package org.chrisle.netbeans.plugins.nbscratchfile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import org.chrisle.netbeans.plugins.nbscratchfile.ui.FilterableFileTypeDialog;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

/**
 *
 * @author Chris2011
 */
@ActionID(
    category = "Tools",
    id = "org.chrisle.netbeans.plugins.nbscratchfile.CreateScratchFile"
)
@ActionRegistration(
    displayName = "#CTL_CreateScratchFile",
    iconBase = "org/chrisle/netbeans/plugins/nbscratchfile/add_file.png"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 150),
    @ActionReference(path = "Shortcuts", name = "DOS-N")
})
@Messages("CTL_CreateScratchFile=New Scratch File...")
public final class CreateScratchFile implements ActionListener {
    private final FilterableFileTypeDialog _dialog = new FilterableFileTypeDialog(new JFrame(), false);

    @Override
    public void actionPerformed(ActionEvent e) {
        _dialog.showDialog();
    }
}
