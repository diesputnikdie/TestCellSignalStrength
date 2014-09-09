package com.brooks.testcellsignalstrength;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	TelephonyManager Tel;
	MyPhoneStateListener MyListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		/* Update the listener, and start it */
        MyListener = new MyPhoneStateListener();
        Tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Tel.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
	}
	
    private class MyPhoneStateListener extends PhoneStateListener
    {
        TextView gsmLevelTextView = (TextView) findViewById(R.id.gsmLevelTextView);
        TextView cdmaLevelTextView = (TextView) findViewById(R.id.cdmaLevelTextView);
        TextView lteLevelTextView = (TextView) findViewById(R.id.lteLevelTextView);
        TextView wcdmaLevelTextView = (TextView) findViewById(R.id.wcdmaLevelTextView);
        ImageView lteSignalImageView = (ImageView) findViewById(R.id.imageView1);
        ImageView cdmaSignalImageView = (ImageView) findViewById(R.id.imageView2);
        ImageView gsmSignalImageView = (ImageView) findViewById(R.id.imageView3);
        ImageView wcdmaSignalImageView = (ImageView) findViewById(R.id.imageView4);

        /* Get the Signal strength from the provider, each time there is an update */
        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength)
        {
            super.onSignalStrengthsChanged(signalStrength);
            
            try {
                final TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                for (final CellInfo info : tm.getAllCellInfo()) {
                    if (info instanceof CellInfoGsm) {
                        final CellSignalStrengthGsm gsm = ((CellInfoGsm) info).getCellSignalStrength();
                        gsmLevelTextView.setText("GSM signal strength is " + gsm.getLevel() + " out of 4");
                        switch (gsm.getLevel()) {
                        case 0:
                        	gsmSignalImageView.setImageResource(R.drawable.stat_sys_signal_null);
                        	break;
                        case 1:
                        	gsmSignalImageView.setImageResource(R.drawable.stat_sys_signal_1);
                        	break;
                        case 2:
                        	gsmSignalImageView.setImageResource(R.drawable.stat_sys_signal_2);
                        	break;
                        case 3:
                        	gsmSignalImageView.setImageResource(R.drawable.stat_sys_signal_3);
                        	break;
                        case 4:
                        	gsmSignalImageView.setImageResource(R.drawable.stat_sys_signal_4);
                        	break;
                        }
                    } else if (info instanceof CellInfoCdma) {
                        final CellSignalStrengthCdma cdma = ((CellInfoCdma) info).getCellSignalStrength();
                        cdmaLevelTextView.setText("CDMA signal strength is " + cdma.getCdmaLevel() + " out of 4");
                        switch (cdma.getLevel()) {
                        case 0:
                        	cdmaSignalImageView.setImageResource(R.drawable.stat_sys_signal_null);
                        	break;
                        case 1:
                        	cdmaSignalImageView.setImageResource(R.drawable.stat_sys_signal_1);
                        	break;
                        case 2:
                        	cdmaSignalImageView.setImageResource(R.drawable.stat_sys_signal_2);
                        	break;
                        case 3:
                        	cdmaSignalImageView.setImageResource(R.drawable.stat_sys_signal_3);
                        	break;
                        case 4:
                        	cdmaSignalImageView.setImageResource(R.drawable.stat_sys_signal_4);
                        	break;
                        }
                    } else if (info instanceof CellInfoLte) {
                        final CellSignalStrengthLte lte = ((CellInfoLte) info).getCellSignalStrength();
                        lteLevelTextView.setText("LTE signal strength is " + lte.getLevel() + " out of 4");
                        switch (lte.getLevel()) {
                        case 0:
                        	lteSignalImageView.setImageResource(R.drawable.stat_sys_signal_null);
                        	break;
                        case 1:
                        	lteSignalImageView.setImageResource(R.drawable.stat_sys_signal_1);
                        	break;
                        case 2:
                        	lteSignalImageView.setImageResource(R.drawable.stat_sys_signal_2);
                        	break;
                        case 3:
                        	lteSignalImageView.setImageResource(R.drawable.stat_sys_signal_3);
                        	break;
                        case 4:
                        	lteSignalImageView.setImageResource(R.drawable.stat_sys_signal_4);
                        	break;
                        }
                    } else if (info instanceof CellInfoWcdma) {
                    	final CellSignalStrengthWcdma wcdma = ((CellInfoWcdma) info).getCellSignalStrength();
                    	wcdmaLevelTextView.setText("WCDMA signal strength is " + wcdma.getLevel() + " out of 4");
                    	switch (wcdma.getLevel()) {
                        case 0:
                        	wcdmaSignalImageView.setImageResource(R.drawable.stat_sys_signal_null);
                        	break;
                        case 1:
                        	wcdmaSignalImageView.setImageResource(R.drawable.stat_sys_signal_1);
                        	break;
                        case 2:
                        	wcdmaSignalImageView.setImageResource(R.drawable.stat_sys_signal_2);
                        	break;
                        case 3:
                        	wcdmaSignalImageView.setImageResource(R.drawable.stat_sys_signal_3);
                        	break;
                        case 4:
                        	wcdmaSignalImageView.setImageResource(R.drawable.stat_sys_signal_4);
                        	break;
                        }
                    } else {
                        throw new Exception("Unknown type of cell signal!");
                    }
                }
            } catch (Exception e) {
                Log.e("TAG", "Unable to obtain cell signal information", e);
            }
        }

    };
    	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
}
