package day18.com.wangtongyu.mygame2048.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import day18.com.wangtongyu.mygame2048.R;
import day18.com.wangtongyu.mygame2048.view.GameView;

public class HomeActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = "HomeActivity";
    private GameView gameView;

    private static HomeActivity homeActivity;
    private MyApplication myApplication;

    private TextView tv_home_score;
    private TextView tv_home_record;
    private TextView tv_home_target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        homeActivity = this;

        final int[] rl_home_centerHeight = new int[1];
        final int[] rl_home_centerWidth = new int[1];

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final RelativeLayout rl_home_center = (RelativeLayout) findViewById(R.id.rl_home_center);
        Button btn_home_revert = (Button) findViewById(R.id.btn_home_revert);
        Button btn_home_restart = (Button) findViewById(R.id.btn_home_restart);
        Button btn_home_option = (Button) findViewById(R.id.btn_home_option);
        btn_home_revert.setOnClickListener(this);
        btn_home_restart.setOnClickListener(this);
        btn_home_option.setOnClickListener(this);


        //初始化显示当前分数的tv
        tv_home_score = (TextView) findViewById(R.id.tv_home_score);
        tv_home_record = (TextView) findViewById(R.id.tv_home_record);
        tv_home_target = (TextView) findViewById(R.id.tv_home_target);

        myApplication = (MyApplication) getApplication();

        //此处有坑。
        tv_home_target.setText(myApplication.getTarget()+"");
        tv_home_record.setText(myApplication.getHighestRecord()+"");


        /**
         * 获得布局中某个子控件 (RelativeLayout)rl_home_center的宽和高的监听事件
         */
        rl_home_center.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {

                        rl_home_centerHeight[0] = rl_home_center.getHeight();
                        rl_home_centerWidth[0] = rl_home_center.getWidth();
                        Log.i(TAG, rl_home_centerHeight[0] + ":" + rl_home_centerWidth[0]);

                        //如果rl_home_center不是正方形,则选其小的边
                        int width = (rl_home_centerWidth[0] > rl_home_centerHeight[0]) ? rl_home_centerHeight[0] : rl_home_centerWidth[0];

                        //创建GameView对象,并添加到rl_home_center上
                        gameView = new GameView(HomeActivity.this, width, 5);
                        rl_home_center.addView(gameView);

                        //移除监听
                        rl_home_center.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_home_revert:
                gameView.getUndo().restore();
                break;

            case R.id.btn_home_restart:
                restart();
                break;

            case R.id.btn_home_option:
                option();
                break;

        }
    }


    /**
     * restart方法
     */
    public void restart(){
        new AlertDialog.Builder(this)
                .setTitle("确认")
                .setMessage("真的要重新开始吗？")
                .setPositiveButton("是的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameView.getUndo().restart();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    /**
     * 点击option按钮执行的方法
     */
    public void option(){

        startActivityForResult(new Intent(this, OptionActivity.class), 100);

    }

    public static HomeActivity getHomeActivity() {
        return homeActivity;
    }


    //更新分数
    public void updateCurrentScore(int score){

        tv_home_score.setText(score + "");

    }

    public void updateHighestScore(int score){

        tv_home_record.setText(score + "");

    }

    public void updateTargetScore(int score){

        tv_home_target.setText(score+"");

    }


    //拿到OptioActivity返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==200){//如果用户在OptionActivity点的Done按钮

            //设置界面销毁之后重新加载gameView
            gameView.getUndo().restart();
            //重新设置Target
            updateTargetScore(myApplication.getTarget());
        }


    }

    @Override
    public void onBackPressed() {
        new     AlertDialog.Builder(this).
                setTitle("退出")
                .setMessage("确定要退出吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("继续玩", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();

    }
}
