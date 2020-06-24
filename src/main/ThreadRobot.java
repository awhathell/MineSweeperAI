/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author w1585
 */
import dialog.ResultDialog;
import operation.Observe;
import java.awt.Robot;
import java.awt.event.InputEvent;
import element.StaticElements;
import java.util.Vector;
import javafx.util.Pair;
import solver.MineSolver;

public class ThreadRobot extends Thread {

    //下一步要点击的砖块的坐标
    MainFrame mainFrame;
    Observe ob;
    MineSolver minesolver;
    Vector<Pair<Integer, Integer>> points;
    public static int sumAIWork;
    public static int win;
    public static int lose;

    public ThreadRobot(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        ob = new Observe(this.mainFrame.bombJPanel.labels);
        points = new Vector<>();
        minesolver = new MineSolver();
        win = 0;
        lose = 0;
        sumAIWork = 10;
    }

    public void run() {
        int i, j;
        try {
            Robot robot = new Robot();
            for (int times = 0; times < sumAIWork; times++) {//当扫雷没结束时（成功||失败）
                mainFrame.reStartGame();
                robot.delay(200);

                //mn获取要点击的坐标
                ob = new Observe(this.mainFrame.bombJPanel.labels);
                Vector<Pair<Integer, Integer>> opens = new Vector<>();
                boolean succ = true;
                //System.out.println(times);
                /*    for(i=0;i<StaticElements.allrow;i++){
                        for(j=0;j<StaticElements.allcol;j++){
                            System.out.print(ob.mineLable[i][j].counAround);
                        }
                        System.out.println("*");
                    }
                    System.out.println("***************");*/
                boolean last = false;
                while (opens.size() < StaticElements.allcol * StaticElements.allcol - StaticElements.allcount && succ && !last) {
                    //    ob=new observe(this.mainFrame.bombJPanel.labels);

                    int[][] a = ob.obtainBrick();
                    
                    //  robot.delay(100);
                    /*  Scanner sc = new Scanner(System.in); 
                    int mn;
                    //mn=returnmn();
                    mn=sc.nextInt();*/
                    minesolver.getCLKPoints(a, points);
                    for (i = 0; i < points.size(); i++) {
                        //        System.out.println("points的尺寸为"+points.size());
                        //System.out.println(points.get(i).getKey() + "*" + points.get(i).getValue());
                        opens.add(new Pair<>(0, 0));
                        if (points.get(i).getKey() != -1 && points.get(i).getValue() != -1) {

                            //    movemouse(robot, 870+65*(points.get(i).getValue())+32,254+65*(points.get(i).getKey())+32);//前面是横尺寸，后面是竖尺寸
                            moveMouse(robot, 770 + 26 * (points.get(i).getValue()) + 13, 337 + 26 * (points.get(i).getKey()) + 13);//前面是横尺寸，后面是竖尺寸
                            pressMouseLeft(robot);
                            //    robot.delay(10);
                            if (this.mainFrame.bombJPanel.labels[points.get(i).getKey()][points.get(i).getValue()].isMineTag()) {
                                succ = false;
                                //    robot.delay(10);
                                
                            }

                        } else {
                            last = true;
                        }
                    }
                    robot.delay(10);
                    //System.out.println("AI点击一次");
                }
                if (succ) {
                    win++;
                } else {
                    lose++;
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("捕获异常");
            e.printStackTrace();
        }
        new ResultDialog(mainFrame);
        System.out.println("AI结束");

    }

    private static void moveMouse(Robot robot, int x, int y) {
        robot.mouseMove(x, y);
        // robot.delay(500);
    }

    private static void pressMouseLeft(Robot robot) {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        //robot.delay(10);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        //robot.delay(10);
    }

}
