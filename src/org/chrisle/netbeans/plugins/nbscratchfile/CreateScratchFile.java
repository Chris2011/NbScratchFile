package org.chrisle.netbeans.plugins.nbscratchfile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JLabel;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.cookies.OpenCookie;
import org.openide.cookies.SaveCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;

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
public final class CreateScratchFile implements ActionListener, LookupListener {
    private DataObject _data;
    private Lookup.Result<SaveCookie> _result;

    public CreateScratchFile() {
        this(Utilities.actionsGlobalContext());
    }

    public CreateScratchFile(Lookup lookup) {
        final Lookup.Template<SaveCookie> tmpl = new Lookup.Template<>(SaveCookie.class);
        this._result = lookup.lookup(tmpl);
        this._result.addLookupListener(new LookupListener() {
            @Override
            public void resultChanged(LookupEvent le) {
                if() {
                    JDialog test = new JDialog();
                    JLabel ll = new JLabel();

                    ll.setText(le.toString());
                    test.add(ll);
                    test.setSize(100, 100);
                    test.setVisible(true);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            FileSystem fs = FileUtil.createMemoryFileSystem();
            FileObject fob = fs.getRoot().createData("Untitled");
            this._data = DataObject.find(fob);
            OpenCookie cookie = (OpenCookie) this._data.getLookup().lookup(OpenCookie.class);
            cookie.open();

//            if (this._result != null && this._result.allInstances().size() > 0) {
//                for (SaveCookie saveCookie : this._result.allInstances()) {
//                    try {
//                        saveCookie.save();
//                    } catch(IOException ex) {
//                        Exceptions.printStackTrace(ex);
//                    }
//                }
//            }
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public void resultChanged(LookupEvent le) {

    }
}
