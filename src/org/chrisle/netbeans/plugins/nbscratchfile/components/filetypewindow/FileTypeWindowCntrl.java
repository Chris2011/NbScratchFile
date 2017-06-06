/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chrisle.netbeans.plugins.nbscratchfile.components.filetypewindow;

import net.java.html.boot.BrowserBuilder;
import net.java.html.json.Model;
import net.java.html.json.Function;
import net.java.html.json.Property;
import net.java.html.json.ComputedProperty;
import org.netbeans.api.htmlui.OpenHTMLRegistration;
import org.netbeans.api.htmlui.HTMLDialog;
import org.openide.util.NbBundle;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;

/**
 * HTML page which displays a window and also a dialog.
 */
@Model(className = "FileTypeWindow", targetId = "", properties = {
    @Property(name = "text", type = String.class)
})
public final class FileTypeWindowCntrl {
//    @ComputedProperty
//    static String templateName() {
//        return "window";
//    }
    
    @Function
    public static void open() {
        BrowserBuilder.newBrowser()
                .loadPage("FileTypeWindow.html")
                .loadClass(FileTypeWindowCntrl.class)
                .invoke("onPageLoad")
                .showAndWait();
        
        System.exit(0);
    }

    @Function
    static void showDialog(FileTypeWindow model) {
        String reply = Pages.showFileTypeWindowDialog(model.getText());
        if ("OK".equals(reply)) {
            model.setText("Happy World!");
        } else {
            model.setText("Sad World!");
        }
    }

    @ActionID(
            category = "Tools",
            id = "org.chrisle.netbeans.plugins.nbscratchfile.FileTypeWindow"
    )
    @ActionReferences({
        @ActionReference(path = "Menu/Tools"),
        @ActionReference(path = "Toolbars/File")
    })
    @NbBundle.Messages("CTL_FileTypeWindow=Open HTML Hello World!")
    @OpenHTMLRegistration(
            url = "FileTypeWindow.html",
            displayName = "#CTL_FileTypeWindow"
    //, iconBase="SET/PATH/TO/ICON/HERE"
    )
    public static void onPageLoad() {
        new FileTypeWindow("Hello World!").applyBindings();
    }

    //
    // dialog UI
    // 
    @HTMLDialog(url = "FileTypeWindow.html")
    static void showFileTypeWindowDialog(String t) {
        new FileTypeWindowDialog(t, false).applyBindings();
    }

    @Model(className = "FileTypeWindowDialog", targetId = "", properties = {
        @Property(name = "text", type = String.class),
        @Property(name = "ok", type = boolean.class)
    })
    static final class DialogCntrl {
        @ComputedProperty
        static String templateName() {
            return "dialog";
        }
    }
}