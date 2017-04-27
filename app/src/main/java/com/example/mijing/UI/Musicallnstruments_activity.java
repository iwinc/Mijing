package com.example.mijing.UI;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mijing.R;
import com.example.mijing.Tools.BaseActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Musicallnstruments_activity extends BaseActivity {
    private String stLnstrumentsname;
    private TextView tv_instrumentName, tv_instrumentJS, tv_instrumentJQ;
    private  ImageView  iv_instrumentImager;




    private SeekBar sbv;
    private ImageView iv_musicplay;//播放按钮
    private MediaPlayer mediaPlayer;
    private String stMusicpath;//音乐文件路径
    private boolean isfirst=false;//是否位第一次
    private boolean isplay=false;//是否在运行
    private Timer mTimer;
    private TimerTask mTimerTask;
    private boolean isChanging=false;//互斥变量
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_musicallnstruments_activity);
            Intent Intent = getIntent();
            stLnstrumentsname = Intent.getStringExtra("music");
            intview();
            getTextString("编钟");
            setTextString("编钟");
            Bitmap btmap = getBtmap("编钟");
            iv_instrumentImager.setImageBitmap(btmap);




            /*音乐*/
            mediaPlayer=new MediaPlayer();
            stMusicpath=Intent.getStringExtra("musicpath");
            iv_musicplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaPlayer != null && !isplay) {
                        iv_musicplay.setBackgroundResource(R.drawable.stop);
                        if (!isfirst) {
                            mediaPlayer.reset();
                            try {
                                mediaPlayer.setDataSource(stMusicpath);
                                mediaPlayer.prepare();// 准备
                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sbv.setMax(mediaPlayer.getDuration());//设置进度条
                            //----------定时器记录播放进度---------//
                            mTimer = new Timer();
                            mTimerTask = new TimerTask() {
                                @Override
                                public void run() {
                                    if(isChanging==true) {
                                        return;
                                    }
                                    sbv.setProgress(mediaPlayer.getCurrentPosition());
                                }
                            };
                            mTimer.schedule(mTimerTask, 0, 10);
                            isfirst=true;
                        }
                        mediaPlayer.start();// 开始
                        isplay = true;
                    } else if (isplay) {
                        sbv.setBackgroundResource(R.drawable.play);
                        mediaPlayer.pause();
                        isplay = false;
                    }
                }
            });





    }

    /**
     * 绑定view
     */
    private void intview() {
        tv_instrumentName = (TextView) findViewById(R.id.tv_instrumentName);//乐器名称
        tv_instrumentJS = (TextView) findViewById(R.id.tv_instrumentJS);//乐器介绍
        tv_instrumentJQ = (TextView) findViewById(R.id.tv_instrumentJQ);//乐器技巧
        iv_instrumentImager= (ImageView) findViewById(R.id.instrumentImager);//乐器图片

        sbv= (SeekBar) findViewById(R.id.sbv_musiclenth);//音乐的进度条
        iv_musicplay= (ImageView) findViewById(R.id.iv_musicplay);//音乐的播放按钮
        sbv.setOnSeekBarChangeListener(new MySeekbar());


    }

    /**
     * 设置文本和设置图片
     */
    private void setTextString(String LnstrumentName) {
        HashMap hashMap = getTextString(LnstrumentName);
        String instrumentJS= (String) hashMap.get("instrumentJS");
        String instrumentJQ= (String) hashMap.get("instrumentJQ");
        tv_instrumentJS.setText(instrumentJS);
        tv_instrumentJQ.setText(instrumentJQ);
        tv_instrumentName.setText(LnstrumentName);
//    设置图片
        Bitmap btmap = getBtmap(LnstrumentName);
        iv_instrumentImager.setImageBitmap(btmap);
    }
    /**
     * 从SD卡中获取Txt文本内容
     */
    private HashMap getTextString(String LnstrumentName) {
        HashMap hashMap=null;
//        判断SD卡是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(getApplicationContext(),"SD卡存在",Toast.LENGTH_SHORT).show();

            try {
                hashMap = new HashMap();
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), LnstrumentName + ".txt");
//                获取到txt问你本中的内容
                if (file.exists()){
                    BufferedReader reader=new BufferedReader(new FileReader(file));
                    String readLine;
                    StringBuilder sb = new StringBuilder();
                    while ((readLine=reader.readLine())!=null){
                        sb.append(readLine);
                    }
                    String[] ss = sb.toString().split("右");
                    System.out.println(ss[0]);
                    //获取到乐器介绍
                    hashMap.put("instrumentJS", ss[0]);
//                获取到乐器的演奏技巧
                    hashMap.put("instrumentJQ", ss[1]);
                }else {
                    Log.e("text","文件不存在");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(),"SD卡不存在",Toast.LENGTH_SHORT).show();
        }
        return hashMap;
    }

    /**
     * 从SD卡中获取照片
     */
    private Bitmap getBtmap(String LnstrumentName) {
        Bitmap Bitmap=null;
//        判断SD卡是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String sdpath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();// 获取sdcard的根路径
            String filepath = sdpath + LnstrumentName + ".png";
            File file = new File(filepath);
            if (file.exists()) {
                 Bitmap= BitmapFactory.decodeFile(filepath);
            }
        }
        return Bitmap;

    }







   /**
    * 进度条处理
    * */
    class MySeekbar implements SeekBar.OnSeekBarChangeListener {
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            isChanging=true;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            mediaPlayer.seekTo(sbv.getProgress());
            isChanging=false;
        }

    }
    protected void onDestroy() {
        if(mediaPlayer != null){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    protected void onPause() {
        if(mediaPlayer != null){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        }
        super.onPause();
    }

    protected void onResume() {
        if(mediaPlayer != null){
            if(!mediaPlayer.isPlaying()){
                mediaPlayer.start();
            }
        }
        super.onResume();
    }
}