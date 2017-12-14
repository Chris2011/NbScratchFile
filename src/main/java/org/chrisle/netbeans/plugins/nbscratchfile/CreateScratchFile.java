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
import javax.swing.JComponent;
import net.java.html.js.JavaScriptBody;
import org.chrisle.netbeans.plugins.utils.WebViewDialog;
import org.netbeans.api.htmlui.HTMLComponent;
import org.netbeans.api.htmlui.HTMLDialog;
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
    private static final WebViewDialog dialog = new WebViewDialog();
    private static final String viewPath = "/org/chrisle/netbeans/plugins/nbscratchfile/ui/dist/index.html";
    private final NbScratchFileViewModel viewModel;

    public CreateScratchFile() {
        viewModel = new NbScratchFileViewModel(dialog);

        dialog.init(Pages.initWebView(viewModel));
    }

    @HTMLComponent(url = CreateScratchFile.viewPath, type = JComponent.class)
    static void initWebView(NbScratchFileViewModel viewModel) {
        exposeModel("NbScratchFileViewModel", viewModel);

        dialog.colorizeElement("languageSearch", null, String.format("background-color: %s; color: %s;", viewModel.getColor("TextField.background", false), viewModel.getColor("TextField.foreground", false)));
        dialog.colorizeElement(null, "body", String.format("background-color: %s;", viewModel.getColor("Menu.background", false)));
        dialog.colorizeElement(null, "ul", String.format("color: %s;", viewModel.getColor("Label.foreground", false)));

//        dialog.addHoverEffectToElements(dialog.getWebEngine().getDocument().getElementsByTagName("li"), String.format("background-color: %s; color: %s;", viewModel.getColor("Menu.background", true), viewModel.getColor("Menu.foreground", true)), String.format("background-color: %s; color: %s;", viewModel.getColor("Menu.background", false), viewModel.getColor("Menu.foreground", false)));
    }

    @HTMLDialog(url = CreateScratchFile.viewPath)
    public static void workaroundNetBeansBug148() {}

    @JavaScriptBody(args = { "name", "value" }, javacall = true, body = ""
        + "window[name] = {"
            + "setExt : function(ext, languageName) {"
            + "  value.@org.chrisle.netbeans.plugins.nbscratchfile.NbScratchFileViewModel::setExt(Ljava/lang/String;Ljava/lang/String;)(ext, languageName);"
            + "},"
            + "getColor : function(colorString, brighter) {"
            + "  return value.@org.chrisle.netbeans.plugins.utils.BaseWebViewDialogViewModel::getColor(Ljava/lang/String;Ljava/lang/Boolean;)(colorString, brighter);"
            + "}"
        + "};"
    )
    private static native void exposeModel(String name, NbScratchFileViewModel value);

    @Override
    public void actionPerformed(ActionEvent e) {
        dialog.showDialog();
    }
}