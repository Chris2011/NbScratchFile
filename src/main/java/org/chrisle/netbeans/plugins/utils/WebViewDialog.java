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

import java.awt.Color;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import net.java.html.js.JavaScriptBody;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventTarget;

/**
 *
 * @author Chris2011
 */
public class WebViewDialog extends JDialog {
    private static final long serialVersionUID = 5885621373197292877L;

    public void init(JComponent jfxPanel) {
        super.add(jfxPanel);
        super.setSize(550, 436);
        super.setResizable(false);
        super.setAlwaysOnTop(true);
        super.setUndecorated(true);
        super.getRootPane().setOpaque(false);
        super.getContentPane().setBackground(new Color(0, 0, 0, 0));
        super.setBackground(new Color(0, 0, 0, 0));

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
    }

    @JavaScriptBody(args = { "id", "tag", "css" }, body = ""
            + "var sourceElem = id ? document.getElementById(id) : document.getElementsByTagName(tag)[0];"
            + "sourceElem.setAttribute('style', css);")
    public native void colorizeElement(String id, String tag, String css);

    private void addHoverEffectToElement(Element sourceElem, String newCss, String oldCss) {
        ((EventTarget) sourceElem).addEventListener("mouseover", (Event elem) -> {
            sourceElem.setAttribute("style", newCss);
        }, false);

        ((EventTarget) sourceElem).addEventListener("mouseout", (Event elem) -> {
            sourceElem.setAttribute("style", oldCss);
        }, false);
    }

    public void addHoverEffectToElements(NodeList sourceElements, String newCss, String oldCss) {
        for (int i = 0; i < sourceElements.getLength(); i++) {
            addHoverEffectToElement((Element) sourceElements.item(i), newCss, oldCss);
        }
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
}
