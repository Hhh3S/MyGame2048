package com.wangtongyu.mygame2048.game;

import com.wangtongyu.mygame2048.view.NumberView;
import com.wangtongyu.mygame2048.view.Position;

import java.util.List;

/**
 * Created by wangtongyu on 2016/3/22.
 */
public class Number {

    private NumberView[][] numberViewMatrix;
    private List<Position> blankList;
    private int count;

    public Number(int rowCount,NumberView[][] numberViewMatrix,List<Position> blankList) {

        this.count = rowCount;
        this.numberViewMatrix = numberViewMatrix;
        this.blankList = blankList;

    }

    public void addRandomNumberToGameView(int num) {
        //随机生成0-15的随机数,决定格子的位置,在该位置上显示数字2
        //添加数字之前需要先检查,现在剩余的空格子有哪些
        updateBlanklist();

        int size = blankList.size();
        //如果棋盘有空格子,那么就在随机挑选一个空格子,添加一个数字
        if (size!=0) {
            int location = (int) Math.floor(Math.random() * size);
            Position position = blankList.get(location);
            numberViewMatrix[position.getX()][position.getY()].setTextNumber(num);

        }else{
            //游戏结束
        }

    }

    public void updateBlanklist() {
        blankList.clear();
        //遍历整个棋盘,若里面的数字等于0,就把它的位置添加到blankList中
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {

                NumberView numberView = numberViewMatrix[i][j];
                if (numberView.getTextNumber()==0){
                    blankList.add(new Position(i,j));
                }

            }
        }

    }
}
