/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import element.StaticElements;

import main.MainFrame;

import dialog.ModeJDialog;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import static operation.BricksListener.mainFrame;

public class ModeListener implements MouseListener {

    ModeJDialog settingDialog;

    MainFrame mainFrame;

    public ModeListener(ModeJDialog settingDialog,
            MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.settingDialog = settingDialog;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = 0;
        int col = 0;
        int mine = 0;
        if (settingDialog.getJRadioButtonChu().isSelected()) {
            row = 9;
            col = 9;
            mine = 10;
        }
        if (settingDialog.getJRadioButtonZhong().isSelected()) {
            row = 16;
            col = 16;
            mine = 40;
        }
        if (settingDialog.getJRadioButtonGao().isSelected()) {
            row = 16;
            col = 30;
            mine = 99;
        }
        if (settingDialog.getJRadioButtonSelf().isSelected()) {
            String highT = settingDialog.getjTextFieldWide().getText();
            Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
            Matcher matcher = pattern.matcher(highT);

            if (!matcher.matches()) {
                settingDialog.getJLabelMessage()
                        .setText("����ĸ߶ȷ�ΧӦ��9-24֮��");
                settingDialog.getJLabelMessage().setFont(new Font("����", Font.BOLD, 20));
                settingDialog.getJLabelMessage().setForeground(Color.white);
                return;
            } else {
                row = Integer.parseInt(highT);
                if (row < 9 || row > 24) {
                    settingDialog.getJLabelMessage().setText(
                            "����ĸ߶ȷ�ΧӦ��9-24֮��");
                    settingDialog.getJLabelMessage().setFont(new Font("����", Font.BOLD, 20));
                    settingDialog.getJLabelMessage().setForeground(Color.white);
                    return;
                }

            }
            String colT = settingDialog.getjTextFieldHigh().getText();

            try {
                col = Integer.parseInt(colT);
                if (col < 9 || col > 30) {
                    settingDialog.getJLabelMessage().setText(
                            "����Ŀ�ȷ�ΧӦ��9-30֮��");
                    settingDialog.getJLabelMessage().setFont(new Font("����", Font.BOLD, 20));
                    settingDialog.getJLabelMessage().setForeground(Color.white);
                    return;
                }
            } catch (Exception e2) {
                settingDialog.getJLabelMessage().setText(
                        "���Ӧ��Ϊ�����ҷ�ΧӦ��9-30֮��");
                settingDialog.getJLabelMessage().setFont(new Font("����", Font.BOLD, 20));
                settingDialog.getJLabelMessage().setForeground(Color.white);
                return;
            }

            String mineT = settingDialog.getjTextFieldBomb().getText();
            try {
                mine = Integer.parseInt(mineT);
                if (mine < 10) {
                    mine = 10;
                } else {
                    mine = Math.min(mine, StaticElements.allrow * StaticElements.allcol * 4 / 5);
                }
            } catch (Exception e3) {
                settingDialog.getJLabelMessage().setText("����Ӧ��Ϊ����");
                settingDialog.getJLabelMessage().setFont(new Font("����", Font.BOLD, 20));
                settingDialog.getJLabelMessage().setForeground(Color.white);
                return;
            }
        }
        StaticElements.allrow = row;
        StaticElements.allcol = col;
        StaticElements.allcount = mine;
        //StaticElements.bombCount =StaticElements.allcount;
       // mainFrame.getFaceJPanel().setNumber(StaticElements.bombCount);
        
        mainFrame.reStartGame();
        settingDialog.dispose();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        settingDialog.getButtonSure().setIcon(StaticElements.confirm_2);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        settingDialog.getButtonSure().setIcon(StaticElements.confirm_1);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
