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
package org.chrisle.netbeans.plugins.utils;

import java.awt.Color;
import javax.swing.UIManager;

/**
 *
 * @author Chris2011
 */
public class BaseWebViewDialogViewModel {
    public String getColor(String colorString, Boolean brighter) {
        return brighter ? getHex(UIManager.getColor(colorString).brighter()) : getHex(UIManager.getColor(colorString));
    }

    private String getHex(Color rgbColor) {
        return String.format("#%s%s%s", Integer.toHexString(rgbColor.getRed()), Integer.toHexString(rgbColor.getGreen()), Integer.toHexString(rgbColor.getBlue()));
    }
}