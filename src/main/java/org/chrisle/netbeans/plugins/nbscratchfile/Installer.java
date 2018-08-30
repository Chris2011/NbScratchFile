package org.chrisle.netbeans.plugins.nbscratchfile;

import javax.swing.JComponent;
import net.java.html.js.JavaScriptBody;
import org.chrisle.netbeans.plugins.utils.WebViewDialog;
import org.netbeans.api.htmlui.HTMLComponent;
import org.netbeans.api.htmlui.HTMLDialog;
import org.openide.modules.ModuleInstall;
import org.openide.windows.OnShowing;

@OnShowing
public class Installer extends ModuleInstall implements Runnable {
    private NbScratchFileViewModel viewModel;
    private static final WebViewDialog dialog = new WebViewDialog();

    @Override
    public void run() {
        viewModel = new NbScratchFileViewModel(dialog);

        System.out.println("Init Webview stuff.");

        dialog.init(Pages.initWebView(viewModel, dialog));
        CreateScratchFile.setDialog(dialog);
    }

    @HTMLComponent(url = CreateScratchFile.viewPath, type = JComponent.class)
    static void initWebView(NbScratchFileViewModel viewModel, WebViewDialog dialog) {
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
}