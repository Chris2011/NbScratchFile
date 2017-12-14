package org.chrisle.netbeans.plugins.nbscratchfile;

import javax.swing.JOptionPane;
import org.openide.modules.ModuleInstall;
import org.openide.windows.OnShowing;

@OnShowing
public class Installer extends ModuleInstall implements Runnable {
    @Override
    public void run() {
        JOptionPane.showMessageDialog(null, "Tesssssssss");
    }
}