/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import element.StaticElements;

import main.MainFrame;

public class DitgitJPanel extends JPanel {

    private JLabel labelCountG = new JLabel();

    private JLabel labelCountS = new JLabel();

    private JLabel labelCountB = new JLabel();

   /* private JLabel labelTimeG = new JLabel();

    private JLabel labelTimeS = new JLabel();

    private JLabel labelTimeB = new JLabel();*/

    MainFrame mainFrame;

    public DitgitJPanel(MainFrame frame) {
        this.mainFrame = frame;
        this.setLayout(new BorderLayout());
        init();

    }

    private void init() {
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.LINE_AXIS);
        panel.setLayout(boxLayout);

        int b = 0;
        int count = StaticElements.bombCount;
        if (count < 0) {

            b = 10;

        } else {

            b = count / 100;
        }

        int g = Math.abs(count) % 10;
        int s = Math.abs(count) / 10 % 10;

        labelCountG.setIcon(StaticElements.time[g]);
        labelCountS.setIcon(StaticElements.time[s]);
        labelCountB.setIcon(StaticElements.time[b]);
       

      /*  labelTimeS.setIcon(StaticElements.time[0]);
        labelTimeG.setIcon(StaticElements.time[0]);
        labelTimeB.setIcon(StaticElements.time[0]);*/

        panel.add(Box.createHorizontalStrut(2));
        panel.add(labelCountB);
        panel.add(labelCountS);
        panel.add(labelCountG);
        panel.add(Box.createHorizontalGlue());

     /*   panel.add(Box.createHorizontalGlue());
        panel.add(labelTimeB);
        panel.add(labelTimeS);
        panel.add(labelTimeG);
        panel.add(Box.createHorizontalStrut(2));*/

        Border borderLow = BorderFactory.createLoweredBevelBorder();

        Border borderEmpty = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        Border borderCom1 = BorderFactory.createCompoundBorder(borderLow,
                borderEmpty);

        panel.setBorder(borderCom1);
        panel.setBackground(Color.white);

        this.add(panel);
        Border borderEmpty2 = BorderFactory.createEmptyBorder(2, 2, 2, 2);

        this.setBorder(borderEmpty2);
        this.setBackground(Color.lightGray);

    }

 /*   public void setTime(int count) {
        int g = count % 10;
        int s = count / 10 % 10;
        int b = count / 100;

        labelTimeG.setIcon(StaticElements.time[g]);
        labelTimeS.setIcon(StaticElements.time[s]);
        labelTimeB.setIcon(StaticElements.time[b]);

    }*/

    public void setNumber(int count) {
        int b = 0;
        if (count < 0) {

            b = 10;

        } else {

            b = count / 100;
        }

        int g = Math.abs(count) % 10;
        int s = Math.abs(count) / 10 % 10;

        labelCountG.setIcon(StaticElements.time[g]);
        labelCountS.setIcon(StaticElements.time[s]);
        labelCountB.setIcon(StaticElements.time[b]);

    }

}
