/*
 * Copyright 2021 Chris.
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
package org.chrisle.netbeans.plugins.nbscratchfile.model;

import java.io.IOException;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.chrisle.netbeans.plugins.nbscratchfile.CreateScratchFile;

/**
 *
 * @author Chrl
 */
public class LanguageType {

    private final String icon;
    private String languageName;
    private final String fileExt;
    private boolean isPluginRequired = false;

    public LanguageType(String languageName, String fileExt, boolean isPluginRequired) {
        this.icon = "".equals(fileExt) ? languageName.toLowerCase() : fileExt;
        this.languageName = languageName;
        this.fileExt = "".equals(fileExt) ? languageName.toLowerCase() : fileExt;
        this.isPluginRequired = isPluginRequired;
    }

    public String Icon() {
        return this.icon.toLowerCase();
    }

    public Icon IconPath() throws IOException {
        URL resource = CreateScratchFile.class.getResource(String.format("icons/%s.png", this.fileExt));

        if (resource != null) {
            return new ImageIcon(resource);
        }

        return new ImageIcon();
    }

    public String LanguageLabel() {
        return String.format("%s (.%S) %s", this.languageName, this.fileExt, this.isPluginRequired ? "- plugin required" : "");
    }

    public String LanguageName() {
        return this.languageName;
    }

    public void LanguageName(String value) {
        this.languageName = value;
    }

    public String FileExt() {
        return this.fileExt.toLowerCase();
    }

//    public boolean IsPluginRequired() {
//        return this.isPluginRequired;
//    }
    public void setExt(LanguageType languageType) {
//        NbScratchFileViewModel.setExt(languageType.FileExt, languageType.LanguageLabel);
    }

    //    public showPluginRequiredMessage(): string {
    //        return this.isPluginRequired ? ' - plugin is required' : '';
    //    }
}
