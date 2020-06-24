/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation;
import element.Brick;
import element.StaticElements;
import main.MainFrame;
import panel.BombJPanel;
/**
 *S
 * @author w1585
 */

public class Observe {
    Brick[][] mineLabel;
   // public static boolean gameover;
    public Observe(Brick[][] mineLabel) {
        this.mineLabel = mineLabel;
     //   this.gameover=false;
    }
    public int[][] obtainBrick(){
        int[][] board=new int[StaticElements.allrow][StaticElements.allcol];
        for(int i=0;i<StaticElements.allrow;i++){
            for(int j=0;j<StaticElements.allcol;j++){
                if(!mineLabel[i][j].openTag)//如果没打开
                    board[i][j]=-1;
                else//打开状态
                    board[i][j]=mineLabel[i][j].counAround;
            }
        }
        return board;
    }
}
