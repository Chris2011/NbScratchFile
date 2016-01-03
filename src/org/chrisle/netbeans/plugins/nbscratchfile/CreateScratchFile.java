package org.chrisle.netbeans.plugins.nbscratchfile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import org.netbeans.api.actions.Openable;
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
import org.openide.util.Exceptions;
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
        try {
            DataObject gdo = getDataObject();
            Openable openable = gdo.getLookup().lookup(Openable.class);

            openable.open();
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    protected DataObject getDataObject() throws DataObjectNotFoundException, IOException {
        String templateName = getTemplate();

        FileObject fo = FileUtil.getConfigRoot().getFileObject(templateName);
        DataObject template = DataObject.find(fo);

        FileSystem memFS = FileUtil.createMemoryFileSystem();
        FileObject root = memFS.getRoot();

        DataFolder dataFolder = DataFolder.findFolder(root);
        DataObject gdo = template.createFromTemplate(dataFolder, "New" + getNextCount());

        return gdo;
    }

    protected String getTemplate() {
        return "Templates/Other/file";
    }

    private static int getNextCount() {
        return _atomInt.incrementAndGet();
    }
}