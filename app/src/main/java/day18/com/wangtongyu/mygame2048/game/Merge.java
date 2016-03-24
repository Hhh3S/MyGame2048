package day18.com.wangtongyu.mygame2048.game;

import java.util.ArrayList;
import java.util.List;

import day18.com.wangtongyu.mygame2048.view.NumberView;

/**
 * Created by wangtongyu on 2016/3/22.
 * 该类用于处理,游戏界面的滑动事件,合并数字相同的格子
 */
public class Merge {


    private static int currentScore=0;


    public static int getCurrentScore() {
        return currentScore;
    }

    public static void setCurrentScore(int currentScore) {
        Merge.currentScore = currentScore;
    }

    /**
     * 向上合并
     * @param Count 行数/或列数
     * @param numberViewMatrix 存放棋盘上每个NumberView的二维数组
     */
    public void mergeToUp(int Count,NumberView[][] numberViewMatrix){

        List<Integer> calculaterList = new ArrayList<Integer>();
        int preNumber = -1;
        for (int i=0;i<Count;i++){
            for (int j=0;j<Count;j++){
                int textNumber = numberViewMatrix[j][i].getTextNumber();

                if (textNumber!=0){
                    if (textNumber!=preNumber && preNumber!=-1){

                        calculaterList.add(preNumber);

                    }else if (preNumber!=-1){
                        calculaterList.add(textNumber*2);
                        currentScore += textNumber*2;
                        preNumber = -1;
                        continue;
                    }

                    preNumber = textNumber;
                }
            }

            if (preNumber!=0&&preNumber!=-1)
                calculaterList.add(preNumber);

            //把合并后数字放到矩阵中
            for (int p = 0;p<calculaterList.size();p++){
                numberViewMatrix[p][i].setTextNumber(calculaterList.get(p));

            }


            //合并长度之后的格子用0填充
            for (int q = calculaterList.size();q<Count;q++){
                numberViewMatrix[q][i].setTextNumber(0);
            }

            //每一列每循环结束,清空calculaterList,并把preNumber置为初始值-1
            calculaterList.clear();
            preNumber = -1;
        }
    }

    /**
     * 想下合并
     * @param Count
     * @param numberViewMatrix
     */
    public void mergeToDown(int Count, NumberView[][] numberViewMatrix) {

        List<Integer> calculaterList = new ArrayList<Integer>();
        int preNumber = -1;
        for (int i=0;i<Count;i++){
            for (int j=Count-1;j>=0;j--){
                int textNumber = numberViewMatrix[j][i].getTextNumber();

                if (textNumber!=0){
                    if (textNumber!=preNumber && preNumber!=-1){

                        calculaterList.add(preNumber);

                    }else if (preNumber!=-1){
                        calculaterList.add(textNumber*2);
                        currentScore += textNumber*2;
                        preNumber = -1;
                        continue;
                    }

                    preNumber = textNumber;
                }
            }

            if (preNumber!=0&&preNumber!=-1)
                calculaterList.add(preNumber);

            //把合并后数字放到矩阵中
            for (int p = Count-1; p>Count-1-calculaterList.size() ; p--){
                numberViewMatrix[p][i].setTextNumber(calculaterList.get(Count-p-1));
            }


            //合并长度之后的格子用0填充
            for (int q = 0; q<Count-calculaterList.size() ;q++){
                numberViewMatrix[q][i].setTextNumber(0);
            }

            //每一列每循环结束,清空calculaterList,并把preNumber置为初始值-1
            calculaterList.clear();
            preNumber = -1;
        }
    }


    /**
     * 向左合并
     * @param Count
     * @param numberViewMatrix
     */
    public void mergeToLeft(int Count, NumberView[][] numberViewMatrix) {

        List<Integer> calculaterList = new ArrayList<Integer>();
        int preNumber = -1;
        for (int i=0;i<Count;i++){
            for (int j=0;j<Count;j++){
                int textNumber = numberViewMatrix[i][j].getTextNumber();

                if (textNumber!=0){
                    if (textNumber!=preNumber && preNumber!=-1){

                        calculaterList.add(preNumber);

                    }else if (preNumber!=-1){
                        calculaterList.add(textNumber*2);
                        currentScore += textNumber*2;
                        preNumber = -1;
                        continue;
                    }

                    preNumber = textNumber;
                }
            }

            if (preNumber!=0&&preNumber!=-1)
                calculaterList.add(preNumber);

            //把合并后数字放到矩阵中
            for (int p = 0;p<calculaterList.size();p++){
                numberViewMatrix[i][p].setTextNumber(calculaterList.get(p));
            }


            //合并长度之后的格子用0填充
            for (int q = calculaterList.size();q<Count;q++){
                numberViewMatrix[i][q].setTextNumber(0);
            }

            //每一列每循环结束,清空calculaterList,并把preNumber置为初始值-1
            calculaterList.clear();
            preNumber = -1;
        }

    }

    /**
     * 向右合并
     * @param Count
     * @param numberViewMatrix
     */
    public void mergeToRight(int Count, NumberView[][] numberViewMatrix) {
        List<Integer> calculaterList = new ArrayList<Integer>();
        int preNumber = -1;
        for (int i=0;i<Count;i++){
            for (int j=Count-1;j>=0;j--){
                int textNumber = numberViewMatrix[i][j].getTextNumber();

                if (textNumber!=0){
                    if (textNumber!=preNumber && preNumber!=-1){

                        calculaterList.add(preNumber);

                    }else if (preNumber!=-1){
                        calculaterList.add(textNumber*2);
                        currentScore += textNumber*2;
                        preNumber = -1;
                        continue;
                    }

                    preNumber = textNumber;
                }
            }

            if (preNumber!=0&&preNumber!=-1)
                calculaterList.add(preNumber);

            //把合并后数字放到矩阵中
            for (int p = Count-1; p>Count-1-calculaterList.size() ; p--){
                numberViewMatrix[i][p].setTextNumber(calculaterList.get(Count-p-1));
            }


            //合并长度之后的格子用0填充
            for (int q = 0; q<Count-calculaterList.size() ;q++){
                numberViewMatrix[i][q].setTextNumber(0);
            }

            //每一列每循环结束,清空calculaterList,并把preNumber置为初始值-1
            calculaterList.clear();
            preNumber = -1;
        }
    }




}
