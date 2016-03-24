package day18.com.wangtongyu.mygame2048.game;

import android.util.Log;

import day18.com.wangtongyu.mygame2048.Activity.HomeActivity;
import day18.com.wangtongyu.mygame2048.view.GameView;
import day18.com.wangtongyu.mygame2048.view.NumberView;

/**
 * Created by wangtongyu on 2016/3/22.
 */
public class Undo {

    private int count;
    private NumberView[][] mNumberItemMatrix;
    public static int[][] histroyMatrix;
    private boolean canRevert;
    private GameView gameView ;
    private int histScore;


    public Undo(GameView gameView) {
        this.count = gameView.getmRowCount();
        this.mNumberItemMatrix = gameView.getNumberViewMatrix();
        this.gameView = gameView;
    }

    //把当前的记录保存到history矩阵中
    public void saveHistroy() {

        //保存历史记录的矩阵初始化
        histroyMatrix = new int[count][count];
        histScore = Merge.getCurrentScore();

        for (int i=0;i<count;i++)
            for (int j=0;j<count;j++){
                histroyMatrix[i][j] =  mNumberItemMatrix[i][j].getTextNumber() ;
            }

        canRevert=true;
    }

    //恢复上一步的状态
    public void restore(){

        //方法2，添加一个flag，当且仅当histroy矩阵有过赋值之后，才置位1.

        Log.i("Undo","restore");
        if (canRevert) {
            for (int i = 0; i < count; i++)
                for (int j = 0; j < count; j++) {
                    mNumberItemMatrix[i][j].setTextNumber(histroyMatrix[i][j]);

                }

            Merge.setCurrentScore(histScore);
            HomeActivity homeActivity = HomeActivity.getHomeActivity();
            homeActivity.updateCurrentScore(Merge.getCurrentScore());
        }
    }


    public void restart(){
        gameView.removeAllViews();
        gameView.init(gameView.getWidth(), gameView.getWdith());
        Merge.setCurrentScore(0);
        HomeActivity homeActivity = HomeActivity.getHomeActivity();
        homeActivity.updateCurrentScore(Merge.getCurrentScore());


    }


}
