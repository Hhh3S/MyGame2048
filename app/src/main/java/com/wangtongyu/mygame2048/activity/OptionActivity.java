package com.wangtongyu.mygame2048.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.wangtongyu.mygame2048.R;

public class OptionActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btn_option_back ;
    private Button btn_option_done ;
    private Button btn_option_setLine;
    private Button btn_option_setTarget;

    private int line;
    private int target;

    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        //初始化button
        btn_option_back = (Button) findViewById(R.id.btn_option_back);
        btn_option_done = (Button) findViewById(R.id.btn_option_done);
        btn_option_setLine = (Button) findViewById(R.id.btn_option_setLine);
        btn_option_setTarget = (Button) findViewById(R.id.btn_option_setTarget);

        btn_option_back.setOnClickListener(this);
        btn_option_done.setOnClickListener(this);
        btn_option_setLine.setOnClickListener(this);
        btn_option_setTarget.setOnClickListener(this);

        app = (MyApplication) getApplication();
        line=app.getLineNumber();
        target=app.getTarget();

        btn_option_setLine.setText(line+"");
        btn_option_setTarget.setText(target+"");


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_option_setLine:

                setLine();
                break;
            case R.id.btn_option_setTarget:

                setTarget();
                break;

            case R.id.btn_option_back:
                finish();
                break;

            case R.id.btn_option_done:
                done();
                break;
        }
    }

    private void done() {

        setResult(200);
        app.setLineNumber(line);
        app.setTarget(target);
        finish();
    }

    private void setTarget() {

        final String[] itmes ={"1024","2048","4096"};

        new    AlertDialog.Builder(this).setTitle("Change Target")
                .setItems(itmes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        target = Integer.parseInt(itmes[which]);
                        btn_option_setTarget.setText(target+"");

                    }
                })
                .show();
    }

    private void setLine() {
        final String[] levals = {"4","5","6"};

        new AlertDialog.Builder(this).setTitle("Change Line")
                .setItems(levals, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        line = Integer.parseInt(levals[which]);
                        btn_option_setLine.setText(line+"");
                    }
                }).show();
    }


}
