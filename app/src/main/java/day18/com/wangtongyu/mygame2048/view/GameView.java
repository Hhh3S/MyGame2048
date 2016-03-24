package day18.com.wangtongyu.mygame2048.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

import day18.com.wangtongyu.mygame2048.Activity.HomeActivity;
import day18.com.wangtongyu.mygame2048.Activity.MyApplication;
import day18.com.wangtongyu.mygame2048.game.Merge;
import day18.com.wangtongyu.mygame2048.game.Number;
import day18.com.wangtongyu.mygame2048.game.Undo;

/**
 * Created by wangtongyu on 2016/3/21.
 */
public class GameView extends GridLayout {

    private static final String TAG = "GameView";
    private int mColumnCount;
    private int mRowCount;
    private List<Position> blankList;
    private NumberView[][] numberViewMatrix;
    private float startX;
    private float startY;
    private float stopX;
    private float stopY;
    private HomeActivity homeActivity;
    //初始化方法的参数宽和高
    private int wdith;
    private Number mNumber;
    private Undo undo;

    private int currentScore;
    private int highestScore;
    private int target;
    SharedPreferences sp;

    Merge merge;



    public Undo getUndo() {
        return undo;
    }

    public int getmRowCount() {
        return mRowCount;
    }

    public NumberView[][] getNumberViewMatrix() {
        return numberViewMatrix;
    }

    public int getWdith() {
        return wdith;
    }

    public GameView(Context context,int width,int rowCount) {


        super(context);

        this.wdith = width;

        //行数和列数相等
        this.mRowCount = rowCount;
        this.mColumnCount = rowCount;
        init(width, width);
    }



    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void init(int width,int height ) {




        homeActivity = HomeActivity.getHomeActivity();



        MyApplication app = (MyApplication) homeActivity.getApplication();


        mColumnCount = app.getLineNumber();
        mRowCount = app.getLineNumber();
        target = app.getTarget();

        Merge.setCurrentScore(0);

        setColumnCount(mColumnCount);
        setRowCount(mRowCount);


        blankList = new ArrayList<Position>();
        numberViewMatrix = new NumberView[mRowCount][mColumnCount];
        mNumber = new Number(mRowCount,numberViewMatrix,blankList);


        for (int i = 0; i < mRowCount; i++){
            for (int j = 0; j < mColumnCount; j++){

                NumberView numberView = new NumberView(getContext());

                addView(numberView, width / mColumnCount, height / mRowCount);//可以指定增加子控件的宽高

                //保存该numberView的位置
                blankList.add(new Position(i,j));
                //保存numberView的引用
                numberViewMatrix[i][j] = numberView;
            }
        }

        //棋盘初始化文笔,在棋盘上随机增加;两个数字

        undo = new Undo(this);
        mNumber.addRandomNumberToGameView(2);
        mNumber.addRandomNumberToGameView(2);


    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                startX = event.getX();
                startY = event.getY();
                Log.i("MotionEvent.ACTION_DOWN", startX +","+ startY);

                undo.saveHistroy();

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                stopX = event.getX();
                stopY = event.getY();
                Log.i("MotionEvent.ACTION_UP", stopX +","+ stopY);

                //手指抬起,判断手指滑动的方向
                getMoveDeriction(startX,startY,stopX,stopY);
                updateCurrentScore();
                handleResult(isOver());
                break;
        }

        return true;
    }






    private void updateCurrentScore() {
        //更新当前的分数
        homeActivity.updateCurrentScore(Merge.getCurrentScore());
    }

    public void getMoveDeriction(float startX,float startY,float stopX,float stopY){
        boolean flag = Math.abs(startX - stopX) > Math.abs(startY - stopY) ? true : false;
        Merge merge = new Merge();

        if (flag){//水平滑动

            if ((startX - stopX) > 0){//←
                merge.mergeToLeft(mRowCount, numberViewMatrix);
                mNumber.addRandomNumberToGameView(2);
            }else if ((startX - stopX) < 0){//→
                merge.mergeToRight(mRowCount, numberViewMatrix);
                mNumber.addRandomNumberToGameView(2);
            }

        }else{//上下滑动
            if ((startY - stopY) > 0){//↑

                merge.mergeToUp(mRowCount, numberViewMatrix);
                mNumber.addRandomNumberToGameView(2);

            }else if ((startY - stopY) < 0){//↓
                merge.mergeToDown(mRowCount, numberViewMatrix);
                mNumber.addRandomNumberToGameView(2);
            }
        }


    }


    private void handleResult(int result) {
        if (result==2){//完成游戏

            if (highestScore<currentScore){

                MyApplication app = (MyApplication) homeActivity.getApplication();
                app.setHighestRecord(currentScore);
                homeActivity.updateHighestScore(currentScore);
            }

            new     AlertDialog.Builder(getContext()).
                    setTitle("恭喜")
                    .setMessage("您已经完成游戏！")
                    .setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            undo.restart();
                        }
                    })
                    .setNegativeButton("挑战更难", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();

        }else  if (result==3){//gameover

            new     AlertDialog.Builder(getContext()).
                    setTitle("失败")
                    .setMessage("游戏结束")
                    .setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            undo.restart();

                        }
                    })
                    .setNegativeButton("退出游戏", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            homeActivity.finish();//当前Activity关闭
                        }
                    })
                    .show();

        }else{//1 表示继续，则给出一个随机数

        }
    }



    // 2 成功了    1 可以继续玩   3 gameover
    private int isOver(){

        for (int i=0;i<mRowCount;i++)
            for (int j=0;j<mColumnCount;j++){

                if (numberViewMatrix[i][j].getTextNumber()==target){
                    return 2;
                }

            }

        //说明没有成功。

        mNumber.updateBlanklist();
        if (blankList.size()==0){

            //这种情况下如果还有可以合并的，则返回1

            for (int i=0;i<mRowCount;i++)
                for (int j=0;j<mColumnCount-1;j++){
                    int current = numberViewMatrix[i][j].getTextNumber();
                    int next = numberViewMatrix[i][j+1] .getTextNumber();
                    if (current==next){
                        return  1;
                    }
                }

            for (int i=0;i<mRowCount;i++)
                for (int j=0;j<mColumnCount-1;j++){
                    int current = numberViewMatrix[j][i].getTextNumber();
                    int next = numberViewMatrix[j+1][i] .getTextNumber();
                    if (current==next){
                        return  1;
                    }
                }

            //如果没有可以合并的了，返回3
            return 3;


        }

        return 1;


    }


}
