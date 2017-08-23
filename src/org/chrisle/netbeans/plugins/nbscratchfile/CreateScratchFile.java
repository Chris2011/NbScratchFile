package org.chrisle.netbeans.plugins.nbscratchfile;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import netscape.javascript.JSObject;
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
        iconBase = "org/chrisle/netbeans/plugins/nbscratchfile/resources/add_file.png"
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
        this.viewModel = new NbScratchFileViewModel(dialog);

        dialog.add(jfxPanel);
        dialog.setSize(700, 680);
        dialog.setResizable(false);
        dialog.setAlwaysOnTop(true);
        dialog.setUndecorated(true);

        dialog.getRootPane().registerKeyboardAction((ActionEvent e1) -> {
            dialog.setVisible(false);
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Platform.runLater(() -> {
            dialog.add(new JLabel("Loading..."));
            webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            webEngine = webView.getEngine();

            webEngine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
                if (newState == State.SUCCEEDED) {
                    JSObject win = (JSObject) webView.getEngine().executeScript("window");
                    win.setMember("NbScratchFileViewModel", this.viewModel);
                }
            });

            webEngine.load("file:///C:/Projekte/Netbeans%20Plugins/NbScratchFile/src/org/chrisle/netbeans/plugins/nbscratchfile/components/filetypewindow/UI/index.html");

        });

        showDialog();
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
