package org.chrisle.netbeans.plugins.nbscratchfile;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import netscape.javascript.JSObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventTarget;

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
    private final JFXPanel jfxPanel;
    private WebView webView;
    private WebEngine webEngine;
    private final NbScratchFileViewModel viewModel;

    public CreateScratchFile() {
        dialog = new JDialog();
        jfxPanel = new JFXPanel();
        viewModel = new NbScratchFileViewModel(dialog);

        initDialog();

        Platform.runLater(() -> {
            webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            webEngine = webView.getEngine();
        });
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

    private void colorizeElement(Element sourceElem, String css) {
        sourceElem.setAttribute("style", css);
    }

    private void colorizeElements(NodeList sourceElements, String css) {
        for (int i = 0; i < sourceElements.getLength(); i++) {
            colorizeElement((Element) sourceElements.item(i), css);
        }
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        showDialog();
        initWebView();
    }

    private void initWebView() {
        Platform.runLater(() -> {
            webEngine.load(this.getClass().getResource("/org/chrisle/netbeans/plugins/nbscratchfile/ui/dist/index.html").toExternalForm());

            try {
                webEngine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
                    if (newState == State.SUCCEEDED) {
                        JSObject win = (JSObject) webView.getEngine().executeScript("window");

                        win.setMember("NbScratchFileViewModel", CreateScratchFile.this.viewModel);

                        colorizeElement(webEngine.getDocument().getElementById("languageSearch"), String.format("background-color: %s; color: %s;", viewModel.getColor("TextField.background", false), viewModel.getColor("TextField.foreground", false)));
                        colorizeElement((Element) webEngine.getDocument().getElementsByTagName("body").item(0), String.format("background-color: %s;", viewModel.getColor("Menu.background", false)));
                        colorizeElement((Element) webEngine.getDocument().getElementsByTagName("ul").item(0), String.format("color: %s;", viewModel.getColor("Label.foreground", false)));

                        addHoverEffectToElements(webEngine.getDocument().getElementsByTagName("li"), String.format("background-color: %s; color: %s;", viewModel.getColor("Menu.background", true), viewModel.getColor("Menu.foreground", true)), String.format("background-color: %s; color: %s;", viewModel.getColor("Menu.background", false), viewModel.getColor("Menu.foreground", false)));
                    }
                });
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        });
    }

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