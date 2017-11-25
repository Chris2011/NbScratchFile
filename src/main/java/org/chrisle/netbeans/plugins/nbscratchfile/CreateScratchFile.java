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
import org.chrisle.netbeans.plugins.utils.BaseWebViewDialogViewModel;
import org.chrisle.netbeans.plugins.utils.WebViewDialog;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.w3c.dom.Element;

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
    @ActionReference(path = "Menu/File", position = 150)
    ,
    @ActionReference(path = "Shortcuts", name = "DOS-N")
})
@Messages("CTL_CreateScratchFile=New Scratch File...")
public final class CreateScratchFile implements ActionListener {

    private final WebViewDialog dialog;
    private final BaseWebViewDialogViewModel viewModel;

    public CreateScratchFile() {
        dialog = new WebViewDialog();
        viewModel = new NbScratchFileViewModel(dialog);

        dialog.setViewModel("NbScratchFileViewModel", viewModel);
        dialog.setViewPath("/org/chrisle/netbeans/plugins/nbscratchfile/ui/dist/index.html");
        dialog.init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dialog.showDialog();
        dialog.initWebView(() -> {
            dialog.colorizeElement(dialog.getWebEngine().getDocument().getElementById("languageSearch"), String.format("background-color: %s; color: %s;", viewModel.getColor("TextField.background", false), viewModel.getColor("TextField.foreground", false)));
            dialog.colorizeElement((Element) dialog.getWebEngine().getDocument().getElementsByTagName("body").item(0), String.format("background-color: %s;", viewModel.getColor("Menu.background", false)));
            dialog.colorizeElement((Element) dialog.getWebEngine().getDocument().getElementsByTagName("ul").item(0), String.format("color: %s;", viewModel.getColor("Label.foreground", false)));

            dialog.addHoverEffectToElements(dialog.getWebEngine().getDocument().getElementsByTagName("li"), String.format("background-color: %s; color: %s;", viewModel.getColor("Menu.background", true), viewModel.getColor("Menu.foreground", true)), String.format("background-color: %s; color: %s;", viewModel.getColor("Menu.background", false), viewModel.getColor("Menu.foreground", false)));
        });
    }
}
