package org.chrisle.netbeans.plugins.nbscratchfile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;
import org.chrisle.netbeans.plugins.nbscratchfile.components.filetypewindow.FileTypeWindow;
import org.chrisle.netbeans.plugins.nbscratchfile.components.scratchfilecomponent.ScratchFileTopComponent;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
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
    @ActionReference(path = "Menu/File", position = 150),
    @ActionReference(path = "Shortcuts", name = "DOS-N")
})
@Messages("CTL_CreateScratchFile=New Scratch File...")
public final class CreateScratchFile implements ActionListener {
    private static final AtomicInteger _atomInt = new AtomicInteger(0);

    @Override
    public void actionPerformed(ActionEvent e) {
        FileTypeWindow.open();
        // TODO: Can't add the editorKit with the mimeType properly.
        ScratchFileTopComponent scratchFileTopComponent = new ScratchFileTopComponent();

        scratchFileTopComponent.open();
        scratchFileTopComponent.requestActive();

        scratchFileTopComponent.setMimeType("text/x-java");
        
//        try {
//            DataObject gdo = getDataObject();
//            Openable openable = gdo.getLookup().lookup(Openable.class);
//
//            openable.open();
//        } catch (DataObjectNotFoundException ex) {
//            Exceptions.printStackTrace(ex);
//        } catch (IOException ex) {
//            Exceptions.printStackTrace(ex);
//        }
    }

    protected DataObject getDataObject() throws DataObjectNotFoundException, IOException {
        String templateName = getTemplate();

        FileObject fo = FileUtil.getConfigRoot().getFileObject(templateName);
        Enumeration<String> attributes = fo.getAttributes();
        String test = "";
        
//        for (String attribute : attributes) {
//            test += "\n" + attribute;
//        }
        
//        while (attributes.hasMoreElements()) {
//            test += "\n" + attributes.nextElement();
//        }
//        
//        JOptionPane.showMessageDialog(null, test);
        
        fo.setAttribute("mimeType", "text/javascript");
        DataObject template = DataObject.find(fo);
        
        FileSystem memFS = FileUtil.createMemoryFileSystem();
        FileObject root = memFS.getRoot();
        
        DataFolder dataFolder = DataFolder.findFolder(root);
        DataObject gdo = template.createFromTemplate(dataFolder, "untitled" + getNextCount());
        
        return gdo;
    }

    protected String getTemplate() {
        return "Templates/Other/file";
    }

    private static int getNextCount() {
        return _atomInt.incrementAndGet();
    }
}
