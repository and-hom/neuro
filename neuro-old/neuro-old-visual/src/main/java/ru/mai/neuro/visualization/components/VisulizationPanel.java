/*
 * SOFMPanel.java
 *
 * Created on 20 Январь 2008 г., 22:57
 */
package ru.mai.neuro.visualization.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author  and_hom
 */
public class VisulizationPanel extends javax.swing.JPanel {

    protected double minX = -1;
    protected double minY = -1;
    protected double xSize = 2;
    protected double ySize = 2;
    protected VisualizationListener listener = null;
    protected Color color1 = Color.BLUE;
    protected Color color2 = Color.RED;

    public double getMinX() {
        return minX;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public double getMinY() {
        return minY;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public double getXSize() {
        return xSize;
    }

    public void setXSize(double xSize) {
        this.xSize = xSize;
    }

    public double getYSize() {
        return ySize;
    }

    public void setYSize(double ySize) {
        this.ySize = ySize;
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public VisualizationListener getListener() {
        return listener;
    }

    public void setListener(VisualizationListener listener) {
        this.listener = listener;
    }
    
    

    /** Creates new form SOFMPanel */
    public VisulizationPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    private List<Point2D.Double> points1 = new LinkedList<Point2D.Double>();
    private List<Point2D.Double> points2 = new LinkedList<Point2D.Double>();

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Point2D.Double point = screen2virtual(evt.getPoint());
        if (evt.getButton() == MouseEvent.BUTTON1) {
            points1.add(point);
        } else {
            points2.add(point);
        }
    }//GEN-LAST:event_formMousePressed

    public void clear() {
        points1.clear();
        points2.clear();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawGrid(g);
        if (listener == null) {
            return;
        }
    }

    public void repaint(VisualizationListener listener) {
        this.listener = listener;
        repaint();
    }

    private void drawGrid(Graphics g) {

    }

    /**
     * Преобразование координат из виртуальных в квадрате 1х1
     * в координаты устройства рисования
     *
     * @param p
     * @return
     */
    private Point virtual2screen(Point2D.Double p) {
        double x = (p.x - minX) * (double) this.getWidth() / xSize;
        double y = (ySize - p.y + minY) * (double) this.getHeight() / ySize;
        return new Point((int) x, (int) y);
    }

    /**
     * Преобразование координат устройства рисования в виртуальные в квадрате 1x1
     *
     * @param p
     * @return
     */
    private Point.Double screen2virtual(Point p) {
        double x = (double) p.x / (double) this.getWidth() * xSize + minX;
        double y = (double) p.y / (double) this.getHeight() * xSize + minY;
        return new Point2D.Double(x, y);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
