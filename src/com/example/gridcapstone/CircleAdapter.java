package com.example.gridcapstone;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class CircleAdapter extends BaseAdapter implements OnTouchListener,
		Runnable {
	private Context mContext;

	// Constructor
	public CircleAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		return mThumbIds.length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(mThumbIds[position]);
		return imageView;
	}

	// Keep all Images in array
	public Integer[] mThumbIds = { R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.yellow3, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.green1, R.drawable.yellow3,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.yellow3,
			R.drawable.yellow3, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.yellow3,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.yellow3, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.yellow3, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2, };

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub

		return false;
	}
}