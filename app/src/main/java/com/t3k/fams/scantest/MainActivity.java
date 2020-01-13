package com.t3k.fams.scantest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ScanBroadcast mScanBroadcast;
    MAdapter mAdapter;
    String StartScan = "com.jbservice.action.START_SCAN";
    String OpenScan = "com.jbservice.action.OPEN_SCAN";
    String CloseScan = "com.jbservice.action.STOP_SCAN";

    String ScanResult = "com.jbservice.action.GET_SCANDATA";

    RecyclerView rvMain;
    List<String> datalist = new ArrayList<>();
    Button btnScan;

    // private static int g_ScanKey = 139;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMain = findViewById(R.id.rvMain);

        btnScan = findViewById(R.id.btnScan);

        getSupportActionBar().setTitle("Scan Test");
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation myFadeInAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.blink);

                v.startAnimation(myFadeInAnimation);
                Intent i = new Intent(StartScan);
                sendBroadcast(i);
            }
        });

        registerReceiverScanBroadcast();

    }

    public class ScanBroadcast extends BroadcastReceiver {
        /**
         * Receiving the scan and bind to adapter
         *
         * @param context
         * @param intent
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(ScanResult)) {
                String datas = intent.getStringExtra("data");
                rvMain.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                rvMain.setHasFixedSize(true);
                datalist.add(datas);
                mAdapter = new MAdapter(datalist);
                rvMain.setAdapter(mAdapter);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mScanBroadcast);
    }

    /**
     * Registering the Broadcast, can also register in xml.
     */
    public void registerReceiverScanBroadcast() {
        mScanBroadcast = new ScanBroadcast();
        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction(ScanResult);
        registerReceiver(mScanBroadcast, intentFilter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {

        switch (item.getItemId()) {

            case R.id.clear: {

                if (datalist != null) {
                    datalist.clear();
                }

                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                }

                break;
            }

            case R.id.activate: {

                Intent i;

                if (item.getTitle() == "Activate") {

                    item.setTitle("Deactivate");
                    item.setIcon(R.drawable.ic_visibility_off_black_24dp);

                    i = new Intent(CloseScan);

                } else {

                    item.setTitle("Activate");
                    item.setIcon(R.drawable.ic_visibility_black_24dp);

                    i = new Intent(OpenScan);
                }

                sendBroadcast(i);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
