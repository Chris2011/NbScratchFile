package org.chrisle.netbeans.plugins.nbscratchfile;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.netbeans.api.actions.Openable;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;

public class NbScratchFileViewModel {
    private int counter = 1;
    private final JDialog dialog;

    public NbScratchFileViewModel(JDialog dialog) {
        this.dialog = dialog;
    }

    public void setExt(String ext, String languageName) {
        try {
            Path path = Paths.get(String.format("%s/.netbeans/scratches/%s/scratch%d.%s", System.getProperty("user.home"), languageName, this.counter++, ext));
            Files.createDirectories(path.getParent());
            Files.createFile(path);

            FileObject fo = FileUtil.toFileObject(FileUtil.normalizeFile(path.toFile()));
            DataObject dataObject = DataObject.find(fo);
            Openable openable = dataObject.getLookup().lookup(Openable.class);

            dialog.setVisible(false);
            openable.open();
        } catch (FileAlreadyExistsException e) {
            this.setExt(ext, languageName);
            System.err.println("already exists: " + e.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
    }

    public String getColor(String colorString, Boolean brighter) {
        return brighter ? getHex(UIManager.getColor(colorString).brighter()) : getHex(UIManager.getColor(colorString));
    }

    private String getHex(Color rgbColor) {
        return String.format("#%s%s%s", Integer.toHexString(rgbColor.getRed()), Integer.toHexString(rgbColor.getGreen()), Integer.toHexString(rgbColor.getBlue()));
    }
}