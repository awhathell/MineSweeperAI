/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

//import dialog.InfoJDialog;
//import dialog.BoardDialog;;
import dialog.ModeJDialog;
import element.StaticElements;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import main.ThreadRobot;

import main.MainFrame;

public class MenuJPanel extends JPanel {

    JButton btStart = new JButton();
    JButton btMode = new JButton();
    JButton btAI = new JButton();
    //JButton btBoard = new JButton();
    // JButton btAbout = new JButton();
    MainFrame mainFrame;

    public MenuJPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        btStart.setSize(320, 85);
        btStart.setFocusPainted(false);
        btMode.setSize(320, 85);
        btMode.setFocusPainted(false);
        btAI.setSize(320, 85);
        btAI.setFocusPainted(false);
        //  btBoard.setSize(320,85);
        // btBoard.setFocusPainted(false);
        //btAbout.setSize(320,85);
        //btAbout.setFocusPainted(false);

        btStart.setOpaque(false);
        btMode.setOpaque(false);
        btAI.setOpaque(false);
        // btBoard.setOpaque(false);
        //btAbout.setOpaque(false);

        btStart.setIcon(StaticElements.button_1[0]);
        btMode.setIcon(StaticElements.button_1[1]);
        btAI.setIcon(StaticElements.button_1[2]);
        //btBoard.setIcon(StaticElements.button_1[2]);
        //btAbout.setIcon(StaticElements.button_1[3]);
        init();
    }

    private void init() {

        btStart.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.reStartGame();
                //   System.out.println(BricksListener.gameover);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                btStart.setIcon(StaticElements.button_1[0]);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btStart.setIcon(StaticElements.button_2[0]);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btStart.setIcon(StaticElements.button_1[0]);
            }
        });
        btMode.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ModeJDialog(mainFrame);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                btMode.setIcon(StaticElements.button_1[1]);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btMode.setIcon(StaticElements.button_2[1]);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btMode.setIcon(StaticElements.button_1[1]);
            }
        });
        btAI.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // new BoardDialog(mainFrame);  
                /*   System.out.println(BricksListener.gameover);
                       observe ob=new observe();
                       ob.start();
                       System.out.println(BricksListener.gameover);*/
                mainFrame.reStartGame();
                ThreadRobot th = new ThreadRobot(mainFrame);
                th.start();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                btAI.setIcon(StaticElements.button_1[2]);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btAI.setIcon(StaticElements.button_2[2]);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btAI.setIcon(StaticElements.button_1[2]);
            }
        });

        /*btAbout.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new InfoJDialog(mainFrame);                    
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                       btAbout.setIcon(StaticElements.button_1[3]);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        btAbout.setIcon(StaticElements.button_2[3]);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        btAbout.setIcon(StaticElements.button_1[3]);
                    }
		});*/
        this.add(btStart);
        this.add(btMode);
        this.add(btAI);
        //this.add(btAbout);
    }

}
