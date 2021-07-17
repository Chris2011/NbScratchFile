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
import org.netbeans.api.actions.Openable;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;

/**
 *
 * @author Chrizzly
 */
public class NbScratchFileViewModel {
    private static int counter = 1;

    public static void setExt(String ext, String languageName) {
        try {
            Path path = Paths.get(String.format("%s/.netbeans/scratches/%s/scratch%d.%s", System.getProperty("user.home"), languageName, NbScratchFileViewModel.counter, ext));
            Files.createDirectories(path.getParent());
            Files.createFile(path);

            FileObject fo = FileUtil.toFileObject(FileUtil.normalizeFile(path.toFile()));
            DataObject dataObject = DataObject.find(fo);
            Openable openable = dataObject.getLookup().lookup(Openable.class);

            openable.open();
            NbScratchFileViewModel.counter = 1;
        } catch (FileAlreadyExistsException e) {
            NbScratchFileViewModel.counter++;
            NbScratchFileViewModel.setExt(ext, languageName);
            System.err.println("already exists: " + e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
