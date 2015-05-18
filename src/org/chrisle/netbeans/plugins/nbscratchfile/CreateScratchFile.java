package org.chrisle.netbeans.plugins.nbscratchfile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JDialog;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.cookies.*;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.text.DataEditorSupport;
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
public final class CreateScratchFile extends DataEditorSupport implements ActionListener, EditorCookie, EditCookie, PrintCookie, CloseCookie {

    public CreateScratchFile() {
        super(null, null);
    }
    
    public CreateScratchFile(DataObject obj, Env env) {
        super(obj, env);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            FileSystem fs = FileUtil.createMemoryFileSystem();
            FileObject fob = fs.getRoot().createData("Untitled");
            DataObject data = DataObject.find(fob);
            OpenCookie cookie = (OpenCookie)data.getLookup().lookup(OpenCookie.class);
            cookie.open();
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public void saveDocument() throws IOException {
        JDialog test = new JDialog();
        test.setVisible(true);
        
        super.saveDocument(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}