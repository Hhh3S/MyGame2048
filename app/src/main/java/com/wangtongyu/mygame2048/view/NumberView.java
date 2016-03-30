package com.wangtongyu.mygame2048.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wangtongyu.mygame2048.R;

/**
 * Created by wangtongyu on 2016/3/21.
 */
public class NumberView extends FrameLayout{
    private TextView mTv;
    private int number;

    public NumberView(Context context) {
        super(context);
        initView(number);

    }
    public NumberView(Context context,int number) {
        super(context);

        initView(number);

    }



    public NumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(0);

    }

    private void initView(int number) {

        setBackgroundColor(Color.parseColor("#CDBA96"));
        mTv = new TextView(getContext());
        mTv.setGravity(Gravity.CENTER);
        mTv.setTextSize(25);

        setTextNumber(number);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        params.setMargins(2,2,2,2);
        addView(mTv, params);

        //给成员变量赋值
        this.number = number;
    }

    // 返回方格内的数字的值
    public int getTextNumber(){
        return number;
    }

    //设置方格内的数字值
    public void setTextNumber(int number){
        this.number = number;

        if(number==0){
            mTv.setText("");
        }else{
            mTv.setText(number+"");
        }

        switch (number){
            case 2:
                mTv.setBackgroundColor(getResources().getColor(R.color.number_2_color));
                break;
            case 4:
                mTv.setBackgroundColor(getResources().getColor(R.color.number_4_color));
                break;
            case 8:
                mTv.setBackgroundColor(getResources().getColor(R.color.number_8_color));
                break;
            case 16:
                mTv.setBackgroundColor(getResources().getColor(R.color.number_16_color));
                break;
            case 32:
                mTv.setBackgroundColor(getResources().getColor(R.color.number_32_color));
                break;
            case 64:
                mTv.setBackgroundColor(getResources().getColor(R.color.number_64_color));
                break;
            case 128:
                mTv.setBackgroundColor(getResources().getColor(R.color.number_128_color));
                break;
            case 256:
                mTv.setBackgroundColor(getResources().getColor(R.color.number_256_color));
                break;
            case 512:
                mTv.setBackgroundColor(getResources().getColor(R.color.number_512_color));
                break;
            case 1024:
                mTv.setBackgroundColor(getResources().getColor(R.color.number_1024_color));
                break;
            case 2048:
                mTv.setBackgroundColor(getResources().getColor(R.color.number_2048_color));
                break;
            default:
                mTv.setBackgroundColor(getResources().getColor(R.color.default_color));
                break;
        }

    }

}
