/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

    private Image image;

    public BackgroundPanel(ImageIcon imageIcon) {

        image = imageIcon.getImage();
    }

    // �̶�����ͼƬ���������JPanel������ͼƬ������������  
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
