/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import element.StaticElements;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.MainFrame;
import main.ThreadRobot;
import panel.BackgroundPanel;
import panel.MenuJPanel;

/**
 *
 * @author w1585
 */
public class ResultDialog extends JDialog {

    MainFrame mainFrame;
    private BackgroundPanel panel = null;
    private JLabel jLabe0 = new JLabel("总场次：");
    private JLabel jLabe1 = new JLabel("获胜场次：");
    private JLabel jLabe2 = new JLabel("获胜率：");
    private JLabel jLabe0num = new JLabel(Integer.toString(ThreadRobot.sumAIWork));
    private JLabel jLabe1num = new JLabel(Integer.toString(ThreadRobot.win));
    private JLabel jLabe2num = new JLabel(String.format("%.5f", ((double) ThreadRobot.win / ThreadRobot.sumAIWork) * 100) + "%");

    public ResultDialog(MainFrame mainFrame) {
        super(mainFrame);
        this.mainFrame = mainFrame;
        this.setTitle("AI Result");
        this.add(getPanel());
        this.setSize(new Dimension(558, 375));

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                mainFrame.reStartGame();
            }

        });
        this.setModal(true);
        this.setVisible(true);
        //   System.out.println(MenuJPanel.win);
        //   System.out.println(MenuJPanel.win/2);
    }

    public JPanel getPanel() {
        panel = new BackgroundPanel(StaticElements.background);
        panel.setOpaque(false);
        panel.setLayout(null);
        Font font = new Font("宋体", Font.PLAIN, 25);//创建1个字体实bai例
        jLabe0.setFont(font);//设置JLabel的字体
        jLabe0.setForeground(Color.WHITE);//设置文字的颜色
        jLabe1.setFont(font);//设置JLabel的字体
        jLabe1.setForeground(Color.WHITE);//设置文字的颜色
        jLabe2.setFont(font);//设置JLabel的字体
        jLabe2.setForeground(Color.WHITE);//设置文字的颜色
        jLabe0num.setFont(font);//设置JLabel的字体
        jLabe0num.setForeground(Color.WHITE);//设置文字的颜色
        jLabe1num.setFont(font);//设置JLabel的字体
        jLabe1num.setForeground(Color.WHITE);//设置文字的颜色
        jLabe2num.setFont(font);//设置JLabel的字体
        jLabe2num.setForeground(Color.WHITE);//设置文字的颜色
        jLabe0.setBounds(200, 40, 280, 60);
        jLabe1.setBounds(200, 100, 280, 110);
        jLabe2.setBounds(200, 150, 280, 160);
        jLabe0num.setBounds(330, 40, 400, 60);
        jLabe1num.setBounds(330, 100, 400, 110);
        jLabe2num.setBounds(330, 150, 400, 160);
        panel.add(jLabe0);
        panel.add(jLabe1);
        panel.add(jLabe2);
        panel.add(jLabe0num);
        panel.add(jLabe1num);
        panel.add(jLabe2num);

        return panel;
    }
}
