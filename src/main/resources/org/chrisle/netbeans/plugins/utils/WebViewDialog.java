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
package org.chrisle.netbeans.plugins.utils;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import netscape.javascript.JSObject;
import org.openide.util.Exceptions;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventTarget;

/**
 *
 * @author Chris2011
 */
public class WebViewDialog extends JDialog {
    private static final long serialVersionUID = 5885621373197292877L;
    private final JFXPanel jfxPanel;
    private WebView webView;
    private WebEngine webEngine;
    private BaseWebViewDialogViewModel viewModel;
    private String viewModelName;
    private String viewPath;

    public WebViewDialog() {
        jfxPanel = new JFXPanel();
    }

    public void init() {
        super.add(jfxPanel);
        super.setSize(700, 450);
        super.setResizable(false);
        super.setAlwaysOnTop(true);
        super.setUndecorated(true);

        super.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                WebViewDialog.this.setVisible(false);
            }
        });

        super.getRootPane().registerKeyboardAction((ActionEvent e1) -> {
            super.setVisible(false);
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

                Platform.runLater(() -> {
            webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            webEngine = webView.getEngine();
        });
    }

    public void colorizeElement(Element sourceElem, String css) {
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

    public void addHoverEffectToElements(NodeList sourceElements, String newCss, String oldCss) {
        for (int i = 0; i < sourceElements.getLength(); i++) {
            addHoverEffectToElement((Element) sourceElements.item(i), newCss, oldCss);
        }
    }

    public void setViewModel(String viewModelName, BaseWebViewDialogViewModel viewModel) {
        this.viewModelName = viewModelName;
        this.viewModel = viewModel;
    }

    public void initWebView(Runnable extraCallback) {
        Platform.runLater(() -> {
            webEngine.load(this.getClass().getResource(this.viewPath).toExternalForm());

            try {
                webEngine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) -> {
                    if (newState == Worker.State.SUCCEEDED) {
                        JSObject win = (JSObject) webView.getEngine().executeScript("window");

                        win.setMember(this.viewModelName, this.viewModel);

                        extraCallback.run();
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

        super.setLocationRelativeTo(focusOwner);
        super.setVisible(true);
    }

    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }

    public WebEngine getWebEngine() {
        return this.webEngine;
    }
}
