package day18.com.wangtongyu.mygame2048.Activity;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by wangtongyu on 2016/3/22.
 */

/**
 * 一般用来保存App的全局信息(全局共享数据)
 * 还可以用来保存app挂掉的信息
 * 当当前application创建的时候创建.每次app启动的时候都会创建
 */
public class MyApplication extends Application {
    private  int lineNumber;
    private  int highestRecord;
    private int target;

    private SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();

        sp = getSharedPreferences("config", MODE_PRIVATE);

        lineNumber = sp.getInt("lineNumber", 4);
        highestRecord = sp.getInt("highestRecord",0);
        target = sp.getInt("target",2048);
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getHighestRecord() {
        return highestRecord;
    }

    public int getTarget() {
        return target;
    }

    public SharedPreferences getSp() {
        return sp;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("lineNumber",lineNumber);
        edit.commit();
    }

    public void setHighestRecord(int highestRecord) {
        this.highestRecord = highestRecord;
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("highestRecord",highestRecord);
        edit.commit();
    }

    public void setTarget(int target) {
        this.target = target;
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("target",target);
        edit.commit();
    }
}
