package org.chrisle.netbeans.plugins.nbscratchfile.components.filetypewindow;

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import net.java.html.boot.BrowserBuilder;
import net.java.html.json.Model;
import net.java.html.json.Function;
import net.java.html.json.Property;
import org.netbeans.api.htmlui.OpenHTMLRegistration;
import org.openide.util.NbBundle;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;

/**
 * HTML page which displays a window and also a dialog.
 */
@Model(className = "FileTypeWindowViewModel", targetId = "", properties = {
    @Property(name = "language", type = String.class),
    @Property(name = "languageTypes", type = org.chrisle.netbeans.plugins.nbscratchfile.components.filetypewindow.model.LanguageViewModel.class, array = true)
})
public final class FileTypeWindow extends JFrame {
    private static FileTypeWindowViewModel fileTypeWindowViewModel;
    private static org.chrisle.netbeans.plugins.nbscratchfile.components.filetypewindow.model.LanguageViewModel languageViewModel;

    public FileTypeWindow() throws HeadlessException {
        setSize(500, 500);
        setResizable(false);
    }

    @Function
    public static void open() {
        BrowserBuilder.newBrowser()
                .loadPage("FileTypeWindow.html")
                .loadClass(FileTypeWindow.class)
                .invoke("onPageLoad")
                .showAndWait();

        System.exit(0);
    }

    @ActionID(
            category = "Tools",
            id = "org.chrisle.netbeans.plugins.nbscratchfile.FileTypeWindow"
    )
    @ActionReferences({
        @ActionReference(path = "Menu/Tools")
        ,
        @ActionReference(path = "Toolbars/File")
    })
    @NbBundle.Messages("CTL_FileTypeWindow=Open HTML Hello World!")
    @OpenHTMLRegistration(
            url = "FileTypeWindow.html",
            displayName = "#CTL_FileTypeWindow"
    )
    public static void onPageLoad() {
        fileTypeWindowViewModel = new FileTypeWindowViewModel();
        languageViewModel = new org.chrisle.netbeans.plugins.nbscratchfile.components.filetypewindow.model.LanguageViewModel();

        List<org.chrisle.netbeans.plugins.nbscratchfile.components.filetypewindow.model.LanguageViewModel> languages = new ArrayList<>();

        languageViewModel.setLanguageName("JavaScript");
        languageViewModel.setFileExt("js");
        languageViewModel.setPluginNeeded(false);
        languages.add(languageViewModel);

        languageViewModel.setLanguageName("JSX");
        languageViewModel.setFileExt("jsx");
        languageViewModel.setPluginNeeded(false);
        languages.add(languageViewModel);

        languageViewModel.setLanguageName("HTML");
        languageViewModel.setFileExt("html");
        languageViewModel.setPluginNeeded(false);
        languages.add(languageViewModel);

        fileTypeWindowViewModel.applyBindings();
    }
}
