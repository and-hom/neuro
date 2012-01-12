package ru.mai.k806.Examples.

�����_0;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.*;

/**
 */
public class LetterInput extends JPanel {
    public LetterInput() {
        super();
        this.addMouseListener(mouseListener);
    }

    public LetterInput(int letterWidth, int letterHeight) {
        super();
        content = new int[letterHeight][letterWidth];
        this.addMouseListener(mouseListener);
    }


    public LetterInput(int[][] content) {
        super();
        this.content = content;
        this.addMouseListener(mouseListener);
    }


    private MouseListener mouseListener = new MouseListener() {

        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
            if (imageEditable == false)
                return;
            int x = e.getX();
            int y = e.getY();
            int j = (int) ((double) x * (double) getLetterWidth() / (double) getWidth());
            if (j >= getLetterWidth())
                j--;
            int i = (int) ((double) y * (double) getLetterHeight() / (double) getHeight());
            if (i >= getLetterHeight())
                i--;
            if (content[i][j] == 0)
                content[i][j] = 1;
            else
                content[i][j] = 0;
            repaint();
        }

        public void mouseEntered(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public void mouseExited(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    };

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
//        g.setColor(Color.red);
//        g.fillRect(0,0,this.getWidth(),this.getHeight());
        if (imageEditable)
            g.setColor(Color.black);
        else
            g.setColor(Color.gray);
        try {
            int wStep = this.getWidth() / getLetterWidth();
            int hStep = this.getHeight() / getLetterHeight();
            for (int i = 0; i < this.getLetterHeight(); i++) {
                int y = i * this.getHeight() / getLetterHeight();
                for (int j = 0; j < this.getLetterWidth(); j++) {
                    int x = j * this.getWidth() / getLetterWidth();
                    if (content[i][j] > 0)
                        g.fillRect(x, y, wStep, hStep);
                }
            }
        } catch (Exception e) {

        }
    }


    public boolean isImageEditable() {
        return imageEditable;
    }

    public void setImageEditable(boolean imageEditable) {
        this.imageEditable = imageEditable;
    }

    public int getLetterWidth() {
        if (content == null || content.length == 0)
            return 0;
        return content[0].length;
    }

    public int getLetterHeight() {
        if (content == null)
            return 0;
        return content.length;
    }


    public int[][] getContent() {
        return content;
    }

    public void setContent(int[][] content) {
        this.content = content;
    }

    public double[] getLinearizedContent() {
        double[] result = new double[getLetterHeight() * getLetterWidth()];
        for (int i = 0; i < getLetterHeight(); i++)
            for (int j = 0; j < getLetterWidth(); j++)
                result[i * getLetterWidth() + j] = content[i][j];
        return result;
    }

    protected int[][] content = new int[6][4];
    protected boolean imageEditable = true;
}
