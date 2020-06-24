/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package element;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class StaticElements {

    public static int allcount = 99;
    public static int allcol = 30;
    public static int allrow = 16;
    public static int bombCount = allcount;
    public static int timer = 0;

    public static boolean isStart = false;

    public static ImageIcon[] num = new ImageIcon[9];
    public static ImageIcon[] time = new ImageIcon[10];

    public static ImageIcon imageIcon;
    public static ImageIcon blankIcon;
    public static ImageIcon bloodIcon;
    public static ImageIcon flagIcon;
    public static ImageIcon askIcon;
    public static ImageIcon errorBombIcon;
    public static ImageIcon bombIcon;

    public static ImageIcon[] button_1 = new ImageIcon[3];
    public static ImageIcon[] button_2 = new ImageIcon[3];
    public static ImageIcon confirm_1;
    public static ImageIcon confirm_2;

    public static ImageIcon background;
    public static ImageIcon defined;
    public static ImageIcon info;
    public static ImageIcon board;

    public StaticElements() {
        int n;
        if (allcol <= 9 && allrow <= 9) {
            n = 65;
        } else if (allcol <= 16 && allrow <= 16) {
            n = 45;
        } else {
            n = 26;
        }

        imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/image/icon.gif")));

        blankIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/image/bricks/blank.png")));
        blankIcon.setImage(blankIcon.getImage().getScaledInstance(n, n, Image.SCALE_DEFAULT));

        bloodIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/image/bricks/blood.png")));
        bloodIcon.setImage(bloodIcon.getImage().getScaledInstance(n, n, Image.SCALE_DEFAULT));

        flagIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/image/bricks/flag.png")));
        flagIcon.setImage(flagIcon.getImage().getScaledInstance(n, n, Image.SCALE_DEFAULT));

        askIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/image/bricks/ask.png")));
        askIcon.setImage(askIcon.getImage().getScaledInstance(n, n, Image.SCALE_DEFAULT));

        bombIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/image/bricks/mine1.png")));
        bombIcon.setImage(bombIcon.getImage().getScaledInstance(n, n, Image.SCALE_DEFAULT));

        errorBombIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/image/bricks/mine2.png")));
        errorBombIcon.setImage(errorBombIcon.getImage().getScaledInstance(n, n, Image.SCALE_DEFAULT));

        confirm_1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/image/buttons/confirm_1.png")));

        confirm_2 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/image/buttons/confirm_2.png")));

        background = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/image/frames/background.png")));

        defined = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/image/frames/defined.png")));


        for (int i = 0; i < button_1.length; i++) {
            button_1[i] = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
                    getClass().getResource("/res/image/buttons/button" + String.valueOf(i + 1) + "_1.png")));
        }

        for (int i = 0; i < button_2.length; i++) {
            button_2[i] = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
                    getClass().getResource("/res/image/buttons/button" + String.valueOf(i + 1) + "_2.png")));
        }

        for (int i = 0; i < num.length; i++) {
            num[i] = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/image/bricks/" + i + ".png")));
            num[i].setImage(num[i].getImage().getScaledInstance(n, n, Image.SCALE_DEFAULT));
        }
        for (int j = 0; j < time.length; j++) {
            time[j] = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/image/bar/d" + j + ".png")));
        }
    }

    public static int getLevel() {
        if (allrow == 9 && allcol == 9 && allcount == 10) {
            return 1;
        } else if (allrow == 16 && allcol == 16 && allcount == 40) {
            return 2;
        } else if (allrow == 16 && allcol == 30 && allcount == 99) {
            return 3;
        } else {
            return 0;
        }
    }

}
