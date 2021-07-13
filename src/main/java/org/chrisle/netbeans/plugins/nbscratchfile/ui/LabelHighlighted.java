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
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author Chris
 */
public class LabelHighlighted extends JLabel {

    private final List<Rectangle2D> rectangles = new ArrayList<>();
    private final Color colorHighlight = Color.YELLOW;

    public void reset() {
        rectangles.clear();

        repaint();
    }

    public void highlightText(String textToHighlight) {
        if (textToHighlight == null) {
            return;
        }

        reset();

        final String textToMatch = textToHighlight.toLowerCase().trim();
        if (textToMatch.length() == 0) {
            return;
        }
        textToHighlight = textToHighlight.trim();

        final String labelText = getText().toLowerCase();
        if (labelText.contains(textToMatch)) {
            FontMetrics fm = getFontMetrics(getFont());
            float w = -1;
            final float h = fm.getHeight() - 1;
            int i = 0;

            while (true) {
                i = labelText.indexOf(textToMatch, i);

                if (i == -1) {
                    break;
                }

                if (w == -1) {
                    String matchingText = getText().substring(i,
                            i + textToHighlight.length());
                    w = fm.stringWidth(matchingText);
                }

                String preText = getText().substring(0, i);
                float x = fm.stringWidth(preText);

                rectangles.add(new Rectangle2D.Float(x + 20, 1, w, h));

                i = i + textToMatch.length();
            }

            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        if (rectangles.size() > 0) {
            Graphics2D g2d = (Graphics2D) g;
            Color c = g2d.getColor();

            rectangles.stream().map(rectangle -> {
                g2d.setColor(colorHighlight);
                g2d.fill(rectangle);

                return rectangle;
            }).forEachOrdered(rectangle -> {
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.draw(rectangle);
            });

            g2d.setColor(c);
        }

        super.paintComponent(g);
    }
}
