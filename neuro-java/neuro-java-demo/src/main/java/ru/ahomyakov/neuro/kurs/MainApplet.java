/*
 * MainForm.java
 *
 * Created on 17 Январь 2008 г., 15:11
 */
package ru.ahomyakov.neuro.kurs;

import ru.ahomyakov.neuro.base.BarierFunction;
import ru.ahomyakov.neuro.base.SigmaFunction;
import ru.ahomyakov.neuro.perseptron.impl.LayerImpl;
import ru.ahomyakov.neuro.perseptron.impl.PerseptronImpl;
import ru.ahomyakov.neuro.perseptron.interfaces.Layer;
import ru.ahomyakov.neuro.perseptron.interfaces.Perseptron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author and_hom
 */
public class MainApplet extends JApplet {

    private boolean teachMode = true;
    private List<Double> teachGroup1 = new LinkedList<>();
    private List<Double> teachGroup2 = new LinkedList<>();
    private List<ExperimentalResult> testGroup = new LinkedList<>();
    private Perseptron perseptron;
    private double eta = 0.3;
    private boolean fillAreas = false;

    /**
     * Creates new form MainForm
     */
    public MainApplet() {
        initComponents();
        teachModeButton.setSelected(teachMode);
        if (teachMode) {
            teachModeButton.setText("Test mode");
        } else {
            teachModeButton.setText("TeachMode");
        }
        buildNeuroNet();
    }

    private void buildNeuroNet() {
        perseptron = new PerseptronImpl();
        Layer layer = new LayerImpl(2, 4, new SigmaFunction(1, 0, 1, 1));
        perseptron.addLayer(layer);
        layer = new LayerImpl(4, 1, new BarierFunction());
        perseptron.addLayer(layer);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        drawPanel = new JPanel();
        jPanel2 = new JPanel();
        teachModeButton = new JToggleButton();
        fillAreasButton = new JToggleButton();
        teachButton = new JButton();
        testButton = new JButton();
        jLabel1 = new JLabel();
        cycleCount = new JTextField();
        resetButton = new JButton();
        exitButton = new JButton();


        drawPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                drawPanelMouseClicked(evt);
            }

            public void mousePressed(MouseEvent evt) {
                drawPanelMousePressed(evt);
            }
        });

        GroupLayout drawPanelLayout = new GroupLayout(drawPanel);
        drawPanel.setLayout(drawPanelLayout);
        drawPanelLayout.setHorizontalGroup(
                drawPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 470, Short.MAX_VALUE)
        );
        drawPanelLayout.setVerticalGroup(
                drawPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 373, Short.MAX_VALUE)
        );

        jPanel2.setLayout(new GridBagLayout());

        teachModeButton.setText("Teach");
        teachModeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teachModeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        jPanel2.add(teachModeButton, gridBagConstraints);

        fillAreasButton.setText("Fill areas");
        fillAreasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillAreasButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        jPanel2.add(fillAreasButton, gridBagConstraints);

        teachButton.setText("Teach");
        teachButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teachButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        jPanel2.add(teachButton, gridBagConstraints);

        testButton.setText("Test");
        testButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        jPanel2.add(testButton, gridBagConstraints);

        jLabel1.setText("Teach cycle count");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        jPanel2.add(jLabel1, gridBagConstraints);

        cycleCount.setText("20000");
        cycleCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cycleCountActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        jPanel2.add(cycleCount, gridBagConstraints);

        resetButton.setText("Reset!");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(0, 0, 0, 100);
        jPanel2.add(resetButton, gridBagConstraints);

        exitButton.setText("Exit :(");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(0, 0, 0, 100);
        jPanel2.add(exitButton, gridBagConstraints);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                        .addComponent(drawPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(drawPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

    }// </editor-fold>//GEN-END:initComponents

    private void teachModeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teachModeButtonActionPerformed
        teachMode = teachModeButton.isSelected();
        if (teachMode) {
            teachModeButton.setText("Test mode");
        } else {
            teachModeButton.setText("TeachMode");
        }
    }//GEN-LAST:event_teachModeButtonActionPerformed

    private void drawPanelMouseClicked(MouseEvent evt) {//GEN-FIRST:event_drawPanelMouseClicked

    }//GEN-LAST:event_drawPanelMouseClicked

    private void drawPanelMousePressed(MouseEvent evt) {//GEN-FIRST:event_drawPanelMousePressed
        if (teachMode) {
            if (evt.getButton() == MouseEvent.BUTTON1) {
                teachGroup1.add(screen2virtual(evt.getPoint()));
            } else {
                teachGroup2.add(screen2virtual(evt.getPoint()));
            }
        } else {
            testGroup.add(new ExperimentalResult(screen2virtual(evt.getPoint()), null));
        }
        repaint();
    }//GEN-LAST:event_drawPanelMousePressed

    private void cycleCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cycleCountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cycleCountActionPerformed

    private void testButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testButtonActionPerformed
        double[] input = new double[2];
        for (ExperimentalResult experimentalResult : testGroup) {
//            experimentalResult.setFromFirstCollection(null)
            input[0] = experimentalResult.getPoint().x;
            input[1] = experimentalResult.getPoint().y;
            if (perseptron.process(input)[0] >= 0.5) {
                experimentalResult.setFromFirstCollection(true);
            } else {
                experimentalResult.setFromFirstCollection(false);
            }
        }
        repaint();
    }//GEN-LAST:event_testButtonActionPerformed

    private void teachButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teachButtonActionPerformed
        List<ExperimentalResult> results = merge(teachGroup1, teachGroup2);
        perseptron.reset();
        int selectedCycleCount = 20000;
        try {
            selectedCycleCount = Integer.valueOf(this.cycleCount.getText());
        } catch (Exception e) {
            this.cycleCount.setText("20000");
            JOptionPane.showMessageDialog(this, "Invalid cycle count - using 20000");
        }
        double[] success = new double[]{1d};
        double[] fail = new double[]{0d};
        double[] input = new double[2];
        for (int i = 0; i < selectedCycleCount; i++) {
            for (ExperimentalResult point : results) {
                input[0] = point.getPoint().x;
                input[1] = point.getPoint().y;
                if (point.isFromFirstCollection()) {
                    perseptron.teach(input, success, eta);
                } else {
                    perseptron.teach(input, fail, eta);
                }
            }
        }
        repaint();
    }//GEN-LAST:event_teachButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        testGroup.clear();
        teachGroup1.clear();
        teachGroup2.clear();
        repaint();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void fillAreasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillAreasButtonActionPerformed
        fillAreas = fillAreasButton.isSelected();
        testButtonActionPerformed(evt);
        repaint();
    }//GEN-LAST:event_fillAreasButtonActionPerformed

    private List<ExperimentalResult> merge(List<Double> teachGroup1, List<Double> teachGroup2) {
        List<ExperimentalResult> results = new ArrayList<>(teachGroup1.size() + teachGroup2.size() + 5);
        for (Double point : teachGroup1) {
            results.add(new ExperimentalResult(point, true));
        }
        for (Double point : teachGroup2) {
            results.add(new ExperimentalResult(point, false));
        }
        Collections.shuffle(results);
        return results;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Stroke st1 = new BasicStroke(1);
        Stroke st4 = new BasicStroke(4);
        Graphics2D graphics2D = (Graphics2D) drawPanel.getGraphics();
        for (double x = 0; x <= 1; x += 0.1) {
            Point from = virtual2screen(new Double(x, 0));
            Point to = virtual2screen(new Double(x, 1));
            graphics2D.drawLine(from.x, from.y, to.x, to.y);
        }
        for (double y = 0; y <= 1; y += 0.1) {
            Point from = virtual2screen(new Double(0, y));
            Point to = virtual2screen(new Double(1, y));
            graphics2D.drawLine(from.x, from.y, to.x, to.y);
        }
        graphics2D.setStroke(st4);
        graphics2D.setColor(Color.BLUE);
        for (Double point : teachGroup1) {
            Point p = virtual2screen(point);
            graphics2D.drawLine(p.x - 3, p.y - 3, p.x + 3, p.y + 3);
            graphics2D.drawLine(p.x - 3, p.y + 3, p.x + 3, p.y - 3);
        }
        graphics2D.setColor(Color.RED);
        for (Double point : teachGroup2) {
            Point p = virtual2screen(point);
            graphics2D.drawLine(p.x - 3, p.y - 3, p.x + 3, p.y + 3);
            graphics2D.drawLine(p.x - 3, p.y + 3, p.x + 3, p.y - 3);
        }
        for (ExperimentalResult point : testGroup) {
            graphics2D.setColor(Color.BLACK);
            graphics2D.setStroke(st4);
            Point p = virtual2screen(point.getPoint());
            graphics2D.drawLine(p.x - 3, p.y - 3, p.x + 3, p.y + 3);
            graphics2D.drawLine(p.x - 3, p.y + 3, p.x + 3, p.y - 3);
            if (point.isFromFirstCollection() != null) {
                graphics2D.setStroke(st1);
                if (point.isFromFirstCollection()) {
                    graphics2D.setColor(Color.BLUE);
                } else {
                    graphics2D.setColor(Color.RED);
                }
                graphics2D.drawOval(p.x - 4, p.y - 4, 8, 8);
            }
        }
        graphics2D.setStroke(st1);
        Point pnt = new Point();
        double[] src = new double[2];
        if (fillAreas) {
            for (int x = 0; x <= drawPanel.getWidth(); x += 5) {
                for (int y = 0; y <= drawPanel.getHeight(); y += 5) {
                    pnt.x = x;
                    pnt.y = y;
                    Double point = screen2virtual(pnt);
                    src[0] = point.x;
                    src[1] = point.y;
                    if (perseptron.process(src)[0] >= 0.5) {
                        graphics2D.setColor(Color.BLUE);
                    } else {
                        graphics2D.setColor(Color.RED);
                    }
                    graphics2D.drawLine(x - 1, y - 1, x + 1, y + 1);
                    graphics2D.drawLine(x - 1, y + 1, x + 1, y - 1);
                }
            }
        }
    }

    /**
     * Преобразование координат из виртуальных в квадрате 1х1
     * в координаты panel
     *
     * @param p
     * @return
     */
    private Point virtual2screen(Double p) {
        double x = p.x * (double) drawPanel.getWidth();
        double y = (p.y) * (double) drawPanel.getHeight();
        return new Point((int) x, (int) y);
    }

    /**
     * Преобразование координат panel в виртуальные в квадрате 1x1
     *
     * @param p
     * @return
     */
    private Double screen2virtual(Point p) {
        double x = (double) p.x / (double) drawPanel.getWidth();
        double y = (double) p.y / (double) drawPanel.getHeight();
        return new Double(x, y);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextField cycleCount;
    private JPanel drawPanel;
    private JButton exitButton;
    private JToggleButton fillAreasButton;
    private JLabel jLabel1;
    private JPanel jPanel2;
    private JButton resetButton;
    private JButton teachButton;
    private JToggleButton teachModeButton;
    private JButton testButton;
    // End of variables declaration//GEN-END:variables
}