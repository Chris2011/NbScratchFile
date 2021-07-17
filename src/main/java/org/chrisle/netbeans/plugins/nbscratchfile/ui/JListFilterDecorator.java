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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.chrisle.netbeans.plugins.nbscratchfile.model.LanguageType;

/**
 *
 * @author Chris
 */
public class JListFilterDecorator {

    public static JListFilterDecorator getInstance() {
        return new JListFilterDecorator();
    }

    public <T> void decorate(FilterableFileTypeDialog dialog, JList<LanguageType> jList, JTextField searchField, JScrollPane listScrollPane, DefaultListModel<LanguageType> listModel, BiPredicate<T, String> userFilter) {
        if (!(jList.getModel() instanceof DefaultListModel)) {
            throw new IllegalArgumentException("List model must be an instance of DefaultListModel");
        }

        DefaultListModel<T> model = (DefaultListModel<T>) jList.getModel();
        List<T> items = getItems(model);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();

                searchFieldNothingFound();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();

                searchFieldNothingFound();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();

                searchFieldNothingFound();
            }

            private void searchFieldNothingFound() {
                searchField.setForeground(listModel.getSize() == 0 ? Color.red : UIManager.getColor("Label.foreground"));
            }

            private void filter() {
                model.clear();

                String s = searchField.getText();

                items.stream().filter(item -> (userFilter.test(item, s))).forEachOrdered(item -> {
                    model.addElement(item);
                });
            }
        });

        searchField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();

                switch (code) {
                    case KeyEvent.VK_UP: {
                        cycleTableSelectionUp(jList, listModel);
                        break;
                    }

                    case KeyEvent.VK_DOWN: {
                        cycleTableSelectionDown(jList, listModel);
                        break;
                    }
                }
            }
        });

        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

        searchField.getInputMap().put(keyStroke, KeyEvent.VK_ESCAPE);
        searchField.getActionMap().put(KeyEvent.VK_ESCAPE, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                searchField.setText("");
            }
        });

        listScrollPane.setViewportView(jList);
    }

    private void cycleTableSelectionUp(JList<LanguageType> languagesList, DefaultListModel<LanguageType> listModel) {
        ListSelectionModel selModel = languagesList.getSelectionModel();
        int index0 = selModel.getMinSelectionIndex();

        if (index0 > 0) {
            selModel.setSelectionInterval(index0 - 1, index0 - 1);
        } else {
            selModel.setSelectionInterval(listModel.getSize() - 1, listModel.getSize() - 1);
        }
    }

    private void cycleTableSelectionDown(JList<LanguageType> languagesList, DefaultListModel<LanguageType> listModel) {
        ListSelectionModel selModel = languagesList.getSelectionModel();
        int index0 = selModel.getMinSelectionIndex();

        if (index0 == -1) {
            // Before first element.
            selModel.setSelectionInterval(0, 0);
        } else if (index0 == listModel.getSize() - 1) {
            // Last element
            selModel.setSelectionInterval(0, 0);
        } else {
            selModel.setSelectionInterval(index0 + 1, index0 + 1);
        }
    }

    private <T> List<T> getItems(DefaultListModel<T> model) {
        List<T> list = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            list.add(model.elementAt(i));
        }

        return list;
    }
}
