package org.chrisle.netbeans.plugins.nbscratchfile.components.filetypewindow.model;

import net.java.html.json.Model;
import net.java.html.json.Property;

/**
 *
 * @author Chris2011
 */
@Model(className = "LanguageViewModel", properties = {
    @Property(name = "icon", type = String.class),
    @Property(name = "languageName", type = String.class),
    @Property(name = "fileExt", type = String.class),
    @Property(name = "pluginNeeded", type = boolean.class),
})
public class LanguageDataModel {
}
