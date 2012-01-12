/*
 * MainForm.java
 *
 * Created on 17 Январь 2008 г., 15:11
 */
package ru.ahomyakov.neuro.visualization;

import ru.ahomyakov.neuro.PerseptronHelper;
import ru.ahomyakov.neuro.base.NeuroNet;
import ru.ahomyakov.neuro.errors.IllegalInitDataException;
import ru.ahomyakov.neuro.kurs.ExperimentalResult;
import ru.ahomyakov.neuro.kurs.SofmPoint;
import ru.ahomyakov.neuro.sofm.impl.EuclidVectorSimilarityRate;
import ru.ahomyakov.neuro.sofm.impl.SOFMImpl;
import ru.ahomyakov.neuro.sofm.impl.regressor.GeometricEduactionRateRegressor;
import ru.ahomyakov.neuro.sofm.interfaces.SOFM;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author and_hom
 */
public class MainForm2 extends javax.swing.JFrame {

    private boolean teachMode = true;
    private List<Point.Double> teachGroup1 = new LinkedList<Double>();
    private List<Point.Double> teachGroup2 = new LinkedList<Double>();
    private List<SofmPoint> sofmPoints = new LinkedList<SofmPoint>();
    private List<ExperimentalResult> testGroup = new LinkedList<ExperimentalResult>();
    private NeuroNet neuroNet;
    private SOFM sofm;
    private double eta = 0.3;
    private boolean fillAreas = false;
    private static int clusterCount = 10;

    /**
     * Creates new form MainForm
     */
    public MainForm2() {
        initComponents();
        teachModeButton.setSelected(teachMode);
        if (teachMode) {
            teachModeButton.setText("Test mode");
        } else {
            teachModeButton.setText("TeachMode");
        }
        jTabbedPane1.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                repaint();
            }
        });
        buildNeuroNet();
        buildSOFM();
    }

    private void buildSOFM() {
        try {
            sofm = new SOFMImpl(new GeometricEduactionRateRegressor(0.7), new EuclidVectorSimilarityRate(), 0.9);
            sofm.init(clusterCount, new double[]{0.5, 0.5});
        } catch (IllegalInitDataException ex) {
            Logger.getLogger(MainForm2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void buildNeuroNet() {
//        Layer layer1 = new LayerImpl(2, 4, new SigmaFunction(1, 0, 1, 1));
//        Layer layer2 = new LayerImpl(4, 1, new BarierFunction());
//        neuroNet = new NeuroNetImpl(Arrays.asList(layer1, layer2));
        neuroNet = PerseptronHelper.create();
    }

    private void drawP(Graphics2D graphics2D) {
        Stroke st1 = new BasicStroke(1);
        Stroke st4 = new BasicStroke(4);


        for (double x = 0; x <= 1; x += 0.1) {
            Point from = virtual2screen(new Point.Double(x, 0), drawPanel);
            Point to = virtual2screen(new Point.Double(x, 1), drawPanel);
            graphics2D.drawLine(from.x, from.y, to.x, to.y);
        }
        for (double y = 0; y <= 1; y += 0.1) {
            Point from = virtual2screen(new Point.Double(0, y), drawPanel);
            Point to = virtual2screen(new Point.Double(1, y), drawPanel);
            graphics2D.drawLine(from.x, from.y, to.x, to.y);
        }
        graphics2D.setStroke(st4);
        graphics2D.setColor(Color.BLUE);
        for (Double point : teachGroup1) {
            Point p = virtual2screen(point, drawPanel);
            graphics2D.drawLine(p.x - 3, p.y - 3, p.x + 3, p.y + 3);
            graphics2D.drawLine(p.x - 3, p.y + 3, p.x + 3, p.y - 3);
        }
        graphics2D.setColor(Color.RED);
        for (Double point : teachGroup2) {
            Point p = virtual2screen(point, drawPanel);
            graphics2D.drawLine(p.x - 3, p.y - 3, p.x + 3, p.y + 3);
            graphics2D.drawLine(p.x - 3, p.y + 3, p.x + 3, p.y - 3);
        }
        for (ExperimentalResult point : testGroup) {
            graphics2D.setColor(Color.BLACK);
            graphics2D.setStroke(st4);
            Point p = virtual2screen(point.getPoint(), drawPanel);
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
                    Double point = screen2virtual(pnt, drawPanel);
                    src[0] = point.x;
                    src[1] = point.y;
                    if (neuroNet.process(src)[0] >= 0.5) {
                        graphics2D.setColor(Color.BLUE);
                        graphics2D.drawLine(x - 1, y - 1, x + 1, y + 1);
                        graphics2D.drawLine(x - 1, y + 1, x + 1, y - 1);
                    } else {
                        graphics2D.setColor(Color.RED);
                    }
                }
            }
        }
    }

    private void drawS(Graphics2D graphics2D) {
        if (sofm == null) {
            JOptionPane.showMessageDialog(this, "Sofm is not initalized!");
            return;
        }
        graphics2D.setStroke(new BasicStroke(1));
        for (double x = 0; x <= 1; x += 0.1) {
            Point from = virtual2screen(new Point.Double(x, 0), sofmPanel);
            Point to = virtual2screen(new Point.Double(x, 1), sofmPanel);
            graphics2D.drawLine(from.x, from.y, to.x, to.y);
        }
        for (double y = 0; y <= 1; y += 0.1) {
            Point from = virtual2screen(new Point.Double(0, y), sofmPanel);
            Point to = virtual2screen(new Point.Double(1, y), sofmPanel);
            graphics2D.drawLine(from.x, from.y, to.x, to.y);
        }
        graphics2D.setStroke(new BasicStroke(2));
        for (int i = 0; i < sofm.getClusters().length; i++) {
            double[] d = sofm.getClusters()[i];
            graphics2D.setColor(new Color(i * 121 % 255, i * 97 % 255, i * 51 % 255));
            Point p = virtual2screen(new Double(d[0], d[1]), sofmPanel);
            graphics2D.drawLine(p.x - 3, p.y - 3, p.x + 3, p.y + 3);
            graphics2D.drawLine(p.x - 3, p.y + 3, p.x + 3, p.y - 3);
        }
        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(4));
        for (SofmPoint point : sofmPoints) {
            graphics2D.setColor(new Color(point.getClusterNumber() * 121 % 255, point.getClusterNumber() * 97 % 255, point.getClusterNumber() * 51 % 255));
            Point p = virtual2screen(point, sofmPanel);
            graphics2D.drawLine(p.x - 1, p.y - 1, p.x + 1, p.y + 1);
            graphics2D.drawLine(p.x - 1, p.y + 1, p.x + 1, p.y - 1);
        }

    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        drawPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        teachModeButton = new javax.swing.JToggleButton();
        fillAreasButton = new javax.swing.JToggleButton();
        teachButton = new javax.swing.JButton();
        testButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cycleCount = new javax.swing.JTextField();
        resetButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        clusterCountBox = new javax.swing.JTextField();
        sofmResetButton = new javax.swing.JButton();
        sofmPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        jPanel3.setLayout(new java.awt.BorderLayout());

        drawPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drawPanelMouseClicked(evt);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                drawPanelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout drawPanelLayout = new javax.swing.GroupLayout(drawPanel);
        drawPanel.setLayout(drawPanelLayout);
        drawPanelLayout.setHorizontalGroup(
                drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 465, Short.MAX_VALUE)
        );
        drawPanelLayout.setVerticalGroup(
                drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 386, Short.MAX_VALUE)
        );

        jPanel3.add(drawPanel, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        teachModeButton.setText("Teach"); // NOI18N
        teachModeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teachModeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(teachModeButton, gridBagConstraints);

        fillAreasButton.setText("Fill areas"); // NOI18N
        fillAreasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillAreasButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(fillAreasButton, gridBagConstraints);

        teachButton.setText("Teach"); // NOI18N
        teachButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teachButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(teachButton, gridBagConstraints);

        testButton.setText("Test"); // NOI18N
        testButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(testButton, gridBagConstraints);

        jLabel1.setText("Teach cycle count"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel1, gridBagConstraints);

        cycleCount.setText("20000"); // NOI18N
        cycleCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cycleCountActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(cycleCount, gridBagConstraints);

        resetButton.setText("Reset!"); // NOI18N
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jPanel2.add(resetButton, gridBagConstraints);

        exitButton.setText("Exit :("); // NOI18N
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jPanel2.add(exitButton, gridBagConstraints);

        jPanel3.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.addTab("2 layer perseptron", jPanel3);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Cluster number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(jLabel2, gridBagConstraints);

        clusterCountBox.setText("10");
        clusterCountBox.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel4.add(clusterCountBox, new java.awt.GridBagConstraints());

        sofmResetButton.setText("Reset");
        sofmResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sofmResetButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel4.add(sofmResetButton, gridBagConstraints);

        jPanel1.add(jPanel4, java.awt.BorderLayout.SOUTH);

        sofmPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sofmPanelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout sofmPanelLayout = new javax.swing.GroupLayout(sofmPanel);
        sofmPanel.setLayout(sofmPanelLayout);
        sofmPanelLayout.setHorizontalGroup(
                sofmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 465, Short.MAX_VALUE)
        );
        sofmPanelLayout.setVerticalGroup(
                sofmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 409, Short.MAX_VALUE)
        );

        jPanel1.add(sofmPanel, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("SOFM", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void teachModeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teachModeButtonActionPerformed
        teachMode = teachModeButton.isSelected();
        if (teachMode) {
            teachModeButton.setText("Test mode");
        } else {
            teachModeButton.setText("TeachMode");
        }
    }//GEN-LAST:event_teachModeButtonActionPerformed

    private void drawPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanelMouseClicked

    }//GEN-LAST:event_drawPanelMouseClicked

    private void drawPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanelMousePressed
        if (teachMode) {
            if (evt.getButton() == MouseEvent.BUTTON1) {
                teachGroup1.add(screen2virtual(evt.getPoint(), drawPanel));
            } else {
                teachGroup2.add(screen2virtual(evt.getPoint(), drawPanel));
            }
        } else {
            testGroup.add(new ExperimentalResult(screen2virtual(evt.getPoint(), drawPanel), null));
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
            if (neuroNet.process(input)[0] >= 0.5) {
                experimentalResult.setFromFirstCollection(true);
            } else {
                experimentalResult.setFromFirstCollection(false);
            }
        }
        repaint();
    }//GEN-LAST:event_testButtonActionPerformed

    private void teachButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teachButtonActionPerformed
        List<ExperimentalResult> results = merge(teachGroup1, teachGroup2);
        buildNeuroNet();
        int selectedCycleCount = 200000;
        try {
            selectedCycleCount = Integer.valueOf(this.cycleCount.getText());
        } catch (Exception e) {
            this.cycleCount.setText("200000");
            JOptionPane.showMessageDialog(this, "Invalid cycle count - using 200000");
        }
        double[] success = new double[]{1d};
        double[] fail = new double[]{0d};
        double[] input = new double[2];
        for (int i = 0; i < selectedCycleCount; i++) {
            for (ExperimentalResult point : results) {
                input[0] = point.getPoint().x;
                input[1] = point.getPoint().y;
                if (point.isFromFirstCollection()) {
                    neuroNet.teach(input, success, eta);
                } else {
                    neuroNet.teach(input, fail, eta);
                }
            }
        }
        repaint();
    }//GEN-LAST:event_teachButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        buildNeuroNet();
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

    private void sofmResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sofmResetButtonActionPerformed
        try {
            try {
                clusterCount = Integer.parseInt(clusterCountBox.getText());
            } catch (Exception e) {
                clusterCount = 10;
                clusterCountBox.setText("" + clusterCount);
            }
            sofm.init(clusterCount, new double[]{0.5, 0.5});//GEN-LAST:event_sofmResetButtonActionPerformed
            sofmPoints.clear();
            repaint();
        } catch (IllegalInitDataException ex) {
            JOptionPane.showMessageDialog(this, ex.getStackTrace(), "SOFM init failed", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(MainForm2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sofmPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sofmPanelMousePressed
        try {
            Double point = screen2virtual(evt.getPoint(), sofmPanel);
            SofmPoint sofmPoint = new SofmPoint(point, sofm.operate(new double[]{point.x, point.y}));
            sofmPoints.add(sofmPoint);
            repaint();//GEN-LAST:event_sofmPanelMousePressed
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
            Logger.getLogger(MainForm2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<ExperimentalResult> merge(List<Double> teachGroup1, List<Double> teachGroup2) {
        List<ExperimentalResult> results = new ArrayList<ExperimentalResult>(teachGroup1.size() + teachGroup2.size() + 5);
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
        if (jTabbedPane1.getSelectedIndex() == 0) {
            drawP((Graphics2D) drawPanel.getGraphics());
        } else {
            drawS((Graphics2D) sofmPanel.getGraphics());
        }

    }

    /**
     * Преобразование координат из виртуальных в квадрате 1х1
     * в координаты panel
     *
     * @param p
     * @return
     */
    private Point virtual2screen(Double p, JPanel panel) {
        double x = p.x * (double) panel.getWidth();
        double y = (p.y) * (double) panel.getHeight();
        return new Point((int) x, (int) y);
    }

    /**
     * Преобразование координат panel в виртуальные в квадрате 1x1
     *
     * @param p
     * @return
     */
    private Point.Double screen2virtual(Point p, JPanel panel) {
        double x = (double) p.x / (double) panel.getWidth();
        double y = (double) p.y / (double) panel.getHeight();
        return new Double(x, y);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainForm2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField clusterCountBox;
    private javax.swing.JTextField cycleCount;
    private javax.swing.JPanel drawPanel;
    private javax.swing.JButton exitButton;
    private javax.swing.JToggleButton fillAreasButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton resetButton;
    private javax.swing.JPanel sofmPanel;
    private javax.swing.JButton sofmResetButton;
    private javax.swing.JButton teachButton;
    private javax.swing.JToggleButton teachModeButton;
    private javax.swing.JButton testButton;
    // End of variables declaration//GEN-END:variables
}
