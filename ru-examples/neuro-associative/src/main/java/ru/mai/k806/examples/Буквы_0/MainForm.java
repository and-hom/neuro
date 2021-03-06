package ru.mai.k806.examples.Буквы_0;

import ru.mai.k806.examples.letters.LetterInput;
import ru.mai.k806.commons.Sample;
import ru.mai.k806.commons.ГетероассоциативнаяСеть_0;

import javax.swing.*;
import java.util.List;
import java.util.LinkedList;

/**
 * @author Andrew
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        letterInputA.setImageEditable(false);
        letterInputB.setImageEditable(false);
        letterInputC.setImageEditable(false);
        letterUserInput.setImageEditable(true);
        jPanel1.add(letterInputA);
        jPanel1.add(letterInputB);
        jPanel1.add(letterInputC);

        jPanel3.add(letterUserInput);
        letterUserInput.setBounds(0, 0, jPanel3.getWidth(), jPanel3.getHeight());
        //jPanel3.getGraphics().fillRect(0,0,10,10);
    }

    //0
    private LetterInput letterInputA = new LetterInput(new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
    });
    //1
    private LetterInput letterInputB = new LetterInput(new int[][]{
            {0, 0, 1, 0},
            {0, 1, 1, 0},
            {1, 1, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 1, 1, 1}
    });
    //2
    private LetterInput letterInputC = new LetterInput(new int[][]{
            {1, 1, 1, 1},
            {0, 0, 0, 1},
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {1, 0, 0, 0},
            {1, 1, 1, 1}
    });
    private LetterInput letterUserInput = new LetterInput(4, 6);

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        saveButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("\u041f\u043e\u043a\u0430 \u043d\u0435 \u0431\u0443\u0434\u0435\u0442 95% \u043f\u043e\u043f\u0430\u0434\u0430\u043d\u0438\u044f (<100 \u043f\u043e\u043f\u044b\u0442\u043e\u043a)");
        jRadioButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("\u0421 \u043e\u0434\u043d\u043e\u0439 \u043f\u043e\u043f\u044b\u0442\u043a\u0438");
        jRadioButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("\u042d\u0442\u0430\u043b\u043e\u043d\u043d\u044b\u0435 \u0441\u0438\u043c\u0432\u043e\u043b\u044b"));

        jPanel3.setLayout(null);

        jPanel3.setMinimumSize(new java.awt.Dimension(0, 0));

        jButton1.setText("\u041f\u0443\u0441\u043a!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("\u0425\u043e\u0442\u0438\u0442\u0435 \u044f \u0434\u043e\u0433\u0430\u0434\u0430\u044e\u0441\u044c, \u0447\u0442\u043e \u0432\u044b \u0442\u0443\u0442 \u043d\u0430\u0440\u0438\u0441\u043e\u0432\u0430\u043b\u0438?");

        jCheckBox1.setText("\u0420\u0430\u0437\u0440\u0435\u0448\u0438\u0442\u044c \u0440\u0435\u0434\u0430\u043a\u0442\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u0435 \u043e\u0431\u0440\u0430\u0437\u0446\u043e\u0432");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        saveButton.setText("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c \u043f\u0440\u0438\u043c\u0435\u0440");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        loadButton.setText("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044c \u043f\u0440\u0438\u043c\u0435\u0440");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(loadButton, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                                                        .addComponent(jCheckBox1)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)))
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCheckBox1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(loadButton)
                                                        .addComponent(saveButton)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_saveButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_loadButtonActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        letterInputA.setImageEditable(jCheckBox1.isSelected());
        letterInputB.setImageEditable(jCheckBox1.isSelected());
        letterInputC.setImageEditable(jCheckBox1.isSelected());
        letterInputA.repaint();
        letterInputB.repaint();
        letterInputC.repaint();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        List<Sample> samples = new LinkedList<Sample>();
        samples.add(new Sample(letterInputA.getLinearizedContent(), new double[]{1, 0, 0}));
        samples.add(new Sample(letterInputB.getLinearizedContent(), new double[]{0, 1, 0}));
        samples.add(new Sample(letterInputC.getLinearizedContent(), new double[]{0, 0, 1}));
        int index = (int) new ГетероассоциативнаяСеть_0(samples).whatIsIt(letterUserInput.getLinearizedContent());
        if (index < 0)
            JOptionPane.showMessageDialog(this, "Похожих образцов не найдено..");
        else
            JOptionPane.showMessageDialog(this, "Ваш образец похож на образец №" + (index + 1));
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

}
