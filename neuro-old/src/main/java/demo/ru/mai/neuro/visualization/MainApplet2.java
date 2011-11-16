/*
 * MainForm.java
 *
 * Created on 17 Январь 2008 г., 15:11
 */
package ru.mai.neuro.visualization;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import ru.mai.neuro.errors.IllegalDimensionException;
import ru.mai.neuro.errors.IllegalInitDataException;
import ru.mai.neuro.errors.NetIsNotInitalizedExceprion;
import ru.mai.neuro.kurs.*;
import ru.mai.neuro.perseptron.impl.LayerImpl;
import ru.mai.neuro.perseptron.impl.NeuroNetImpl;
import ru.mai.neuro.perseptron.impl.functions.BarierFunction;
import ru.mai.neuro.perseptron.impl.functions.SigmaFunction;
import ru.mai.neuro.perseptron.interfaces.Layer;
import ru.mai.neuro.perseptron.interfaces.NeuroNet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.event.ChangeListener;
import ru.mai.neuro.sofm.impl.SOFMImpl;
import ru.mai.neuro.sofm.interfaces.SOFM;

/**
 * @author and_hom
 */
public class MainApplet2 extends JApplet {

    private boolean teachMode = true;
    private List<Double> teachGroup1 = new LinkedList<Double>();
    private List<Double> teachGroup2 = new LinkedList<Double>();
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
    public MainApplet2() {
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
            sofm = new SOFMImpl();
            sofm.init(clusterCount, new double[]{0.5, 0.5});
        } catch (IllegalInitDataException ex) {
            Logger.getLogger(MainForm2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void buildNeuroNet() {
        neuroNet = new NeuroNetImpl();
        Layer layer = new LayerImpl(2, 4, new SigmaFunction(1, 0, 1, 1));
        neuroNet.addLayer(layer);
        layer = new LayerImpl(4, 1, new BarierFunction());
        neuroNet.addLayer(layer);
    }

    private void drawP(Graphics2D graphics2D) {
        Stroke st1 = new BasicStroke(1);
        Stroke st4 = new BasicStroke(4);


        for (double x = 0; x <= 1; x += 0.1) {
            Point from = virtual2screen(new Double(x, 0), drawPanel);
            Point to = virtual2screen(new Double(x, 1), drawPanel);
            graphics2D.drawLine(from.x, from.y, to.x, to.y);
        }
        for (double y = 0; y <= 1; y += 0.1) {
            Point from = virtual2screen(new Double(0, y), drawPanel);
            Point to = virtual2screen(new Double(1, y), drawPanel);
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
                    if (neuroNet.operate(src)[0] >= 0.5) {
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
            Point from = virtual2screen(new Double(x, 0), sofmPanel);
            Point to = virtual2screen(new Double(x, 1), sofmPanel);
            graphics2D.drawLine(from.x, from.y, to.x, to.y);
        }
        for (double y = 0; y <= 1; y += 0.1) {
            Point from = virtual2screen(new Double(0, y), sofmPanel);
            Point to = virtual2screen(new Double(1, y), sofmPanel);
            graphics2D.drawLine(from.x, from.y, to.x, to.y);
        }
        graphics2D.setStroke(new BasicStroke(2));
        for (int i=0;i<sofm.getClusters().length;i++) {
            double[] d=sofm.getClusters()[i];
            graphics2D.setColor(new Color(i*100%255, i*57%255, i*11%255));
            Point p = virtual2screen(new Double(d[0], d[1]), sofmPanel);
            graphics2D.drawLine(p.x - 3, p.y - 3, p.x + 3, p.y + 3);
            graphics2D.drawLine(p.x - 3, p.y + 3, p.x + 3, p.y - 3);
        }
        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(4));
        for (SofmPoint point : sofmPoints) {
            graphics2D.setColor(new Color(point.getClusterNumber()*100%255, point.getClusterNumber()*57%255, point.getClusterNumber()*11%255));
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
        GridBagConstraints gridBagConstraints;

        jTabbedPane1 = new JTabbedPane();
        jPanel3 = new JPanel();
        drawPanel = new JPanel();
        jPanel2 = new JPanel();
        teachModeButton = new JToggleButton();
        fillAreasButton = new JToggleButton();
        teachButton = new JButton();
        testButton = new JButton();
        jLabel1 = new JLabel();
        cycleCount = new JTextField();
        resetButton = new JButton();
        jPanel1 = new JPanel();
        jPanel4 = new JPanel();
        jLabel2 = new JLabel();
        clusterCountBox = new JTextField();
        sofmResetButton = new JButton();
        sofmPanel = new JPanel();

        jPanel3.setLayout(new BorderLayout());

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
            .addGap(0, 465, Short.MAX_VALUE)
        );
        drawPanelLayout.setVerticalGroup(
            drawPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );

        jPanel3.add(drawPanel, BorderLayout.CENTER);

        jPanel2.setLayout(new GridBagLayout());

        teachModeButton.setText("Teach"); // NOI18N
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

        fillAreasButton.setText("Fill areas"); // NOI18N
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

        teachButton.setText("Teach"); // NOI18N
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

        testButton.setText("Test"); // NOI18N
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

        jLabel1.setText("Teach cycle count"); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        jPanel2.add(jLabel1, gridBagConstraints);

        cycleCount.setText("20000"); // NOI18N
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

        resetButton.setText("Reset!"); // NOI18N
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


        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(0, 0, 0, 100);

        jPanel3.add(jPanel2, BorderLayout.SOUTH);

        jTabbedPane1.addTab("2 layer perseptron", jPanel3);

        jPanel1.setLayout(new BorderLayout());

        jPanel4.setLayout(new GridBagLayout());

        jLabel2.setText("Cluster number");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        jPanel4.add(jLabel2, gridBagConstraints);

        clusterCountBox.setText("10");
        clusterCountBox.setPreferredSize(new Dimension(100, 20));
        jPanel4.add(clusterCountBox, new GridBagConstraints());

        sofmResetButton.setText("Reset");
        sofmResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sofmResetButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel4.add(sofmResetButton, gridBagConstraints);

        jPanel1.add(jPanel4, BorderLayout.SOUTH);

        sofmPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                sofmPanelMousePressed(evt);
            }
        });

        GroupLayout sofmPanelLayout = new GroupLayout(sofmPanel);
        sofmPanel.setLayout(sofmPanelLayout);
        sofmPanelLayout.setHorizontalGroup(
            sofmPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
        );
        sofmPanelLayout.setVerticalGroup(
            sofmPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
        );

        jPanel1.add(sofmPanel, BorderLayout.CENTER);

        jTabbedPane1.addTab("SOFM", jPanel1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
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
            if (neuroNet.operate(input)[0] >= 0.5) {
                experimentalResult.setFromFirstCollection(true);
            } else {
                experimentalResult.setFromFirstCollection(false);
            }
        }
        repaint();
    }//GEN-LAST:event_testButtonActionPerformed

    private void teachButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teachButtonActionPerformed
        List<ExperimentalResult> results = merge(teachGroup1, teachGroup2);
        neuroNet.reset();
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
        neuroNet.reset();
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

    private void sofmPanelMousePressed(MouseEvent evt) {//GEN-FIRST:event_sofmPanelMousePressed
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
    private Double screen2virtual(Point p, JPanel panel) {
        double x = (double) p.x / (double) panel.getWidth();
        double y = (double) p.y / (double) panel.getHeight();
        return new Double(x, y);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainForm2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextField clusterCountBox;
    private JTextField cycleCount;
    private JPanel drawPanel;
//    private JButton exitButton;
    private JToggleButton fillAreasButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JTabbedPane jTabbedPane1;
    private JButton resetButton;
    private JPanel sofmPanel;
    private JButton sofmResetButton;
    private JButton teachButton;
    private JToggleButton teachModeButton;
    private JButton testButton;
    // End of variables declaration//GEN-END:variables
}