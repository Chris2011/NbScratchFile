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
package org.chrisle.netbeans.plugins.nbscratchfile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.chrisle.netbeans.plugins.utils.BaseWebViewDialogViewModel;
import org.netbeans.api.actions.Openable;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;

/**
 *
 * @author Chris2011
 */
public class NbScratchFileViewModel extends BaseWebViewDialogViewModel {
    private int counter = 1;
    private final JDialog dialog;

    public NbScratchFileViewModel(JDialog dialog) {
        this.dialog = dialog;
    }

    public void setExt(String ext, String languageName) {
        System.out.println(languageName);
        
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
}