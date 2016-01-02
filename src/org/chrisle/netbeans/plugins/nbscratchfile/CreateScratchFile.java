package org.chrisle.netbeans.plugins.nbscratchfile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JLabel;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
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
public final class CreateScratchFile extends AbstractSavable implements ActionListener {
    private DataObject _data;
    private final Object obj;

    public CreateScratchFile(Object obj) {
        this.obj = obj;
        register();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            FileSystem fs = FileUtil.createMemoryFileSystem();
            FileObject fob = fs.getRoot().createData("Untitled");
            this._data = DataObject.find(fob);
            OpenCookie cookie = (OpenCookie) this._data.getLookup().lookup(OpenCookie.class);
            cookie.open();
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CreateScratchFile) {
            return ((CreateScratchFile)o).obj.equals(obj);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.obj.hashCode();
    }

    @Override
    protected void handleSave() {
        JDialog test = new JDialog();
        JLabel ll = new JLabel();

        ll.setText("Es wird gespeichert.");
        test.add(ll);
        test.setSize(100, 100);
        test.setVisible(true);
    }

    @Override
    protected String findDisplayName() {
        return "My name is " + obj.toString(); // get display name somehow
    }
}