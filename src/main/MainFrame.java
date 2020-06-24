/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JFrame;
import javax.swing.Timer;
import panel.MenuJPanel;
import element.StaticElements;
import java.awt.Color;
import panel.BombJPanel;
import panel.DitgitJPanel;
//import ranking.Leaderboard;
//import timer.TimerListener;

public class MainFrame extends JFrame {

    public MenuJPanel optionJPanel;

    private DitgitJPanel faceJPanel;

    public BombJPanel bombJPanel;
    

   /* private TimerListener timerListener = new TimerListener(this);
    private Timer timer = new Timer(1000, timerListener);*/

    public MainFrame() {

        new StaticElements();
//        new Leaderboard();
        this.setIconImage(StaticElements.imageIcon.getImage());
        this.setTitle("É¨À×");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);

        this.setSize(1300, 1000);
        this.setLayout(null);
        init();

        optionJPanel.setSize(305, 282);
        faceJPanel.setSize(90, 60);
        bombJPanel.setSize(StaticElements.allcol * 26, StaticElements.allrow * 26);
        faceJPanel.setLocation(110, 270);
        optionJPanel.setLocation(0, 330);
        bombJPanel.setLocation(400 + 450 - StaticElements.allcol * 26 / 2, 500 - StaticElements.allrow * 26 / 2);
        this.getContentPane().setBackground(new Color(28, 60, 44));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
      //  timer.stop();
    }

    private void init() {
        optionJPanel = new MenuJPanel(this);
        faceJPanel = new DitgitJPanel(this);
        bombJPanel = new BombJPanel(this);
        this.add(faceJPanel);
        this.add(optionJPanel);
        this.add(bombJPanel);

    }

    public void reStartGame() {

        this.remove(faceJPanel);
        this.remove(bombJPanel);
        new StaticElements();
//        new Leaderboard();
        StaticElements.timer = 0;
        StaticElements.bombCount = StaticElements.allcount;
        StaticElements.isStart = false;
        bombJPanel = new BombJPanel(this);
        faceJPanel = new DitgitJPanel(this);
        faceJPanel.setSize(90, 60);
        faceJPanel.setLocation(110, 270);
        getFaceJPanel().setNumber(StaticElements.bombCount);
        if (StaticElements.allcol <= 9 && StaticElements.allrow <= 9) {
            int width = StaticElements.allcol * 65;
            int height = StaticElements.allrow * 65;
            bombJPanel.setSize(width, height);
            bombJPanel.setLocation(400 + 450 - width / 2, 500 - height / 2);
        } else if (StaticElements.allcol <= 16 && StaticElements.allrow <= 16) {
            int width = StaticElements.allcol * 45;
            int height = StaticElements.allrow * 45;
            bombJPanel.setSize(width, height);
            bombJPanel.setLocation(400 + 450 - width / 2, 500 - height / 2);
        } else {
            int width = StaticElements.allcol * 26;
            int height = StaticElements.allrow * 26;
            bombJPanel.setSize(width, height);
            bombJPanel.setLocation(400 + 450 - width / 2, 500 - height / 2);
        }
        this.add(faceJPanel);
        this.add(bombJPanel);
        this.revalidate();
        this.repaint();
     //   timer.stop();
    }

    public DitgitJPanel getFaceJPanel() {
        return faceJPanel;
    }

    public BombJPanel getBombJPanel() {
        return bombJPanel;
    }

   /* public Timer getTimer() {
        return timer;
    }*/

}
