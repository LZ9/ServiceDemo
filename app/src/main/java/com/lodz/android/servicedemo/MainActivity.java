package com.lodz.android.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lodz.android.component.base.activity.AbsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AbsActivity {

    /** 启动 */
    @BindView(R.id.start_service_btn)
    Button mStartServiceBtn;

    /** 停止 */
    @BindView(R.id.stop_service_btn)
    Button mStopServiceBtn;

    /** 绑定 */
    @BindView(R.id.bind_service_btn)
    Button mBindServiceBtn;

    /** 解绑 */
    @BindView(R.id.unbind_service_btn)
    Button mUnbindServiceBtn;

    private TestService.TestBinder mBinder;

    @Override
    protected int getAbsLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    protected void setListeners() {
        super.setListeners();

        // 启动
        mStartServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getContext(), TestService.class);
                startService(startIntent);
            }
        });

        // 停止
        mStopServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getContext(), TestService.class);
                stopService(startIntent);
            }
        });

        // 绑定
        mBindServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bindIntent = new Intent(getContext(), TestService.class);
                bindService(bindIntent, mServiceConnection, BIND_AUTO_CREATE);
            }
        });

        // 解绑
        mUnbindServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    unbindService(mServiceConnection);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.e(TestService.TAG, "已解绑");
                }
            }
        });
    }


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (TestService.TestBinder) service;
            mBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TestService.TAG, "onServiceDisconnected name : " + name);
        }
    };


}
