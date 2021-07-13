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
package org.chrisle.netbeans.plugins.nbscratchfile.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import org.chrisle.netbeans.plugins.nbscratchfile.NbScratchFileViewModel;
import org.chrisle.netbeans.plugins.nbscratchfile.model.LanguageType;
import org.openide.util.Exceptions;

/**
 *
 * @author Chrizzly
 */
public class FilterableFileTypeDialog extends javax.swing.JDialog {

    private final DefaultListModel<LanguageType> listModel = new DefaultListModel<>();
    private static JList<LanguageType> languagesList;

    public FilterableFileTypeDialog(Frame parent, boolean modal) {
        super(parent, modal);

        super.setResizable(false);
        super.setUndecorated(true);

        initComponents();

        fillListWithLanguages();

        languagesList = new JList<>(listModel);
        languagesList.setCellRenderer(createListRenderer());
        languagesList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                int index = list.locationToIndex(e.getPoint());
                LanguageType languageType = (LanguageType) listModel.getElementAt(index);

                NbScratchFileViewModel.setExt(languageType.FileExt(), languageType.LanguageName());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JList list = (JList) e.getSource();
                int index = list.locationToIndex(e.getPoint());

                list.setSelectedIndex(index);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JList list = (JList) e.getSource();
                int index = list.locationToIndex(e.getPoint());

                list.setSelectedIndex(index);
            }
        });

        languagesList.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int code = e.getKeyCode();

                switch (code) {
                    case KeyEvent.VK_ENTER: {
                        JList list = (JList) e.getSource();
                        int index = list.getSelectedIndex();

                        LanguageType languageType = (LanguageType) listModel.getElementAt(index);

                        NbScratchFileViewModel.setExt(languageType.FileExt(), languageType.LanguageName());
                    }
                }
            }
        });

        JListFilterDecorator.getInstance().decorate(this, languagesList, searchField, listScrollPane, listModel, FilterableFileTypeDialog::languagesFilter);

        super.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                searchField.setFocusable(true);
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                FilterableFileTypeDialog.this.setVisible(false);
                searchField.setText("");
            }
        });

        super.getRootPane().registerKeyboardAction((ActionEvent e) -> {
            FilterableFileTypeDialog.this.setVisible(false);
            searchField.setText("");
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    public void showDialog() {
        // try to use monitor, where the input focus is
        // therefor get the topmost component based on the input focus
        Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();

        if (null != focusOwner) {
            while (focusOwner.getParent() != null) {
                focusOwner = focusOwner.getParent();
            }
        }

        super.setLocationRelativeTo(focusOwner);
        super.setVisible(true);
    }

    private static boolean languagesFilter(LanguageType lang, String str) {
        return lang.LanguageName().toLowerCase().contains(str.toLowerCase());
    }

    private void fillListWithLanguages() {
        listModel.addElement(new LanguageType("ANTLRv3", "g", true));
        listModel.addElement(new LanguageType("ANTLRv4", "g4", true));
        listModel.addElement(new LanguageType("Assembler", "asm", false));
        listModel.addElement(new LanguageType("Batch", "bat", false));
        listModel.addElement(new LanguageType("C", "c", false));
        listModel.addElement(new LanguageType("C#", "cs", true));
        listModel.addElement(new LanguageType("C++", "cpp", false));
        listModel.addElement(new LanguageType("CSS", "css", false));
        listModel.addElement(new LanguageType("Clojure", "clj", true));
        listModel.addElement(new LanguageType("CoffeeScript", "coffee", true));
        listModel.addElement(new LanguageType("Dockerfile", "", false));
        listModel.addElement(new LanguageType("Freemarker", "ftl", true));
        listModel.addElement(new LanguageType("Galen", "gspec", true));
        listModel.addElement(new LanguageType("GLSL", "glsl", true));
        listModel.addElement(new LanguageType("Go", "go", true));
        listModel.addElement(new LanguageType("Groovy", "groovy", true));
        listModel.addElement(new LanguageType("HAML", "haml", true));
        listModel.addElement(new LanguageType("Handlebars", "hbs", true));
        listModel.addElement(new LanguageType("HTML", "html", false));
        listModel.addElement(new LanguageType("Ini", "ini", false));
        listModel.addElement(new LanguageType("Jade/Pug", "pug", false));
        listModel.addElement(new LanguageType("Java", "java", false));
        listModel.addElement(new LanguageType("JavaScript", "js", false));
        listModel.addElement(new LanguageType("JavaScript React", "jsx", false));
        listModel.addElement(new LanguageType("JSP", "jsp", false));
        listModel.addElement(new LanguageType("JSON", "json", false));
        listModel.addElement(new LanguageType("Kotlin", "kt", true));
        listModel.addElement(new LanguageType("Less", "less", false));
        listModel.addElement(new LanguageType("LISP", "lisp", true));
        listModel.addElement(new LanguageType("Lua", "lua", true));
        listModel.addElement(new LanguageType("Makefile", "", false));
        listModel.addElement(new LanguageType("Markdown", "md", true));
        listModel.addElement(new LanguageType("NetBeans Mind Map", "mmd", true));
        listModel.addElement(new LanguageType("Perl", "pl", true));
        listModel.addElement(new LanguageType("PHP", "php", false));
        listModel.addElement(new LanguageType("Plain Text", "txt", false));
        listModel.addElement(new LanguageType("PLSQL", "plsql", true));
        listModel.addElement(new LanguageType("Puppet", "pp", true));
        listModel.addElement(new LanguageType("Python", "py", true));
        listModel.addElement(new LanguageType("R", "r", true));
        listModel.addElement(new LanguageType("Ruby", "rb", true));
        listModel.addElement(new LanguageType("Rust", "rs", true));
        listModel.addElement(new LanguageType("Sass", "sass", false));
        listModel.addElement(new LanguageType("Scala", "scala", true));
        listModel.addElement(new LanguageType("Scss", "scss", false));
        listModel.addElement(new LanguageType("Smarty", "tpl", false));
        listModel.addElement(new LanguageType("SQL", "sql", false));
        listModel.addElement(new LanguageType("Shell Script", "sh", true));
        listModel.addElement(new LanguageType("Tex", "tex", true));
        listModel.addElement(new LanguageType("Twig", "twig", false));
        listModel.addElement(new LanguageType("TypeScript", "ts", true));
        listModel.addElement(new LanguageType("TypeScript React", "tsx", true));
        listModel.addElement(new LanguageType("Vue", "vue", true));
        listModel.addElement(new LanguageType("XML", "xml", false));
        listModel.addElement(new LanguageType("XSL", "xsl", false));
        listModel.addElement(new LanguageType("YAML", "yaml", false));
    }

    private ListCellRenderer<? super LanguageType> createListRenderer() {
        return new DefaultListCellRenderer() {
            private final Color background = new Color(0, 100, 255, 15);
//            private final Color defaultBackground = (Color) UIManager.get("List.background");
            private final Color defaultBackground = new Color(255, 255, 255, 15);

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (c instanceof JLabel) {
                    try {
                        JLabel original = (JLabel) c;
                        LabelHighlighted label = new LabelHighlighted();
                        LanguageType language = (LanguageType) value;

                        label.setIcon(language.IconPath());
                        label.setText(language.LanguageLabel());
                        label.setBackground(original.getBackground());
                        label.setForeground(original.getForeground());
                        label.setOpaque(original.isOpaque());
                        label.setHorizontalTextPosition(original.getHorizontalTextPosition());
                        label.highlightText(searchField.getText());

                        if (!isSelected) {
                            label.setBackground(index % 2 == 0 ? background : defaultBackground);
                        }

                        return label;
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }

                return c;
            }
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchPane = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        listPane = new javax.swing.JPanel();
        listScrollPane = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        searchField.setText(org.openide.util.NbBundle.getMessage(FilterableFileTypeDialog.class, "FilterableFileTypeDialog.searchField.text")); // NOI18N

        javax.swing.GroupLayout searchPaneLayout = new javax.swing.GroupLayout(searchPane);
        searchPane.setLayout(searchPaneLayout);
        searchPaneLayout.setHorizontalGroup(
            searchPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );
        searchPaneLayout.setVerticalGroup(
            searchPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchField, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout listPaneLayout = new javax.swing.GroupLayout(listPane);
        listPane.setLayout(listPaneLayout);
        listPaneLayout.setHorizontalGroup(
            listPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        listPaneLayout.setVerticalGroup(
            listPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(listPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JPanel listPane;
    private static javax.swing.JScrollPane listScrollPane;
    private static javax.swing.JTextField searchField;
    private javax.swing.JPanel searchPane;
    // End of variables declaration//GEN-END:variables
}
