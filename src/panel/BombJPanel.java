/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import java.awt.GridLayout;
import javax.swing.JPanel;
import operation.BricksListener;
import main.MainFrame;
import element.StaticElements;
import element.Brick;

public class BombJPanel extends JPanel {

	public Brick[][] labels = new Brick[StaticElements.allrow][StaticElements.allcol];
	private BricksListener listener;
	private MainFrame mainFrame;
        public boolean gameover=false;
        
	public BombJPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
                //this.setBorder(BorderFactory.createTitledBorder("БъЬт") );
		this.setLayout(new GridLayout(StaticElements.allrow, StaticElements.allcol));
                //this.mainFrame.pack();
                
		init();

	}

	private void init() {

		listener = new BricksListener(labels, mainFrame);

		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j] = new Brick(i, j);
				labels[i][j].setIcon(StaticElements.blankIcon);
				labels[i][j].addMouseListener(listener);
                                labels[i][j].openTag=false;
				this.add(labels[i][j]);
			}
		}
		

	}

}
