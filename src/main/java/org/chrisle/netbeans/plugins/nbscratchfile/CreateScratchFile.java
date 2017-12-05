package org.chrisle.netbeans.plugins.nbscratchfile;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import net.java.html.js.JavaScriptBody;
import org.netbeans.api.htmlui.HTMLComponent;
import org.netbeans.api.htmlui.HTMLDialog;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

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
    private final JDialog dialog;
    private final JComponent jfxPanel;
    private final NbScratchFileViewModel viewModel;

    public CreateScratchFile() {
        dialog = new JDialog();
        viewModel = new NbScratchFileViewModel(dialog);
        jfxPanel = Pages.initWebView(viewModel);
        initDialog();
    }

    private void initDialog() {
        dialog.add(jfxPanel);
        dialog.setSize(700, 450);
        dialog.setResizable(false);
        dialog.setAlwaysOnTop(true);
        dialog.setUndecorated(true);
        dialog.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                dialog.setVisible(false);
            }
        });

        dialog.getRootPane().registerKeyboardAction((ActionEvent e1) -> {
            dialog.setVisible(false);
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    @JavaScriptBody(
        args = { "id", "tag", "css" }, body = "\n"
                + "var sourceElem = id ? document.getElementById(id) : document.getElementsByTagName(tag)[0];\n"
                + "sourceElem.setAttribute('style', css);\n"
                + ""
    )
    private static native void colorizeElement(String id, String tag, String css);
    
    /* this would rather be done on the TypeScript side as it doesn't talk to Java:
    private void addHoverEffectToElement(Element sourceElem, String newCss, String oldCss) {
        ((EventTarget) sourceElem).addEventListener("mouseover", (elem) -> {
            sourceElem.setAttribute("style", newCss);
        }, false);

        ((EventTarget) sourceElem).addEventListener("mouseout", (elem) -> {
            sourceElem.setAttribute("style", oldCss);
        }, false);
    }

    private void addHoverEffectToElements(NodeList sourceElements, String newCss, String oldCss) {
        for (int i = 0; i < sourceElements.getLength(); i++) {
            addHoverEffectToElement((Element) sourceElements.item(i), newCss, oldCss);
        }
    }
*/

    @Override
    public void actionPerformed(ActionEvent e) {
        showDialog();
    }

    @HTMLComponent(url = "/org/chrisle/netbeans/plugins/nbscratchfile/ui/dist/index.html", type = JComponent.class)
    static void initWebView(NbScratchFileViewModel viewModel) {
        exposeModel("NbScratchFileViewModel", viewModel);

        colorizeElement("languageSearch", null, String.format("background-color: %s; color: %s;", viewModel.getColor("TextField.background", false), viewModel.getColor("TextField.foreground", false)));
        colorizeElement(null, "body", String.format("background-color: %s;", viewModel.getColor("Menu.background", false)));
        colorizeElement(null, "ul", String.format("color: %s;", viewModel.getColor("Label.foreground", false)));

//        addHoverEffectToElements(webEngine.getDocument().getElementsByTagName("li"), String.format("background-color: %s; color: %s;", viewModel.getColor("Menu.background", true), viewModel.getColor("Menu.foreground", true)), String.format("background-color: %s; color: %s;", viewModel.getColor("Menu.background", false), viewModel.getColor("Menu.foreground", false)));
    }
    
    @HTMLDialog(url = "/org/chrisle/netbeans/plugins/nbscratchfile/ui/dist/index.html")
    static void workaroundNetBeansBug148() {
    }
    
    @JavaScriptBody(args = { "name", "value" }, javacall = true, body = "\n"
        + "window[name] = {"
            + "setExt : function(ext, languageName) {\n"
            + "  value.@org.chrisle.netbeans.plugins.nbscratchfile.NbScratchFileViewModel::setExt(Ljava/lang/String;Ljava/lang/String;)(ext, languageName);\n"
            + "},\n"
            + "getColor : function(colorString, brighter) {\n"
            + "  return value.@org.chrisle.netbeans.plugins.nbscratchfile.NbScratchFileViewModel::getColor(Ljava/lang/String;Ljava/lang/Boolean;)(colorString, brighter);\n"
            + "}\n"
        + "};"
    )
    private static native void exposeModel(String name, NbScratchFileViewModel value);

    public void showDialog() {
        // try to use monitor, where the input focus is
        // therefor get the topmost component based on the input focus
        Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();

        if (null != focusOwner) {
            while (focusOwner.getParent() != null) {
                focusOwner = focusOwner.getParent();
            }
        }

        dialog.setLocationRelativeTo(focusOwner);
        dialog.setVisible(true);
    }
}