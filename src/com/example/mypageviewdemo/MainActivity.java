package com.example.mypageviewdemo;

import java.util.ArrayList;
import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String TAG = "compass";
	private ArrayList<View> list;
	float[] accelerometerValues = new float[3];
	float[] magneticFieldValues = new float[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initData();
		viewPager = (ViewPager) findViewById(R.id.viewpage);
		MyPageAdapte pageAdapte = new MyPageAdapte(list);
		viewPager.setOffscreenPageLimit(7);
		viewPager.setAdapter(pageAdapte);
//		viewPager.setPageMargin(30);
		viewPager.setClipChildren(false);
		viewPager.setPageTransformer(true, new ZoominPagerTransFormer());
		pageAdapte.notifyDataSetChanged();
		viewPager.setCurrentItem(Integer.MAX_VALUE / 2);
		findViewById(R.id.main).setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return viewPager.dispatchTouchEvent(event);
			}
		});
//		setViewPagerScrollSpeed();
	}

	/**
	 * 设置ViewPager的滑动速度
	 */
	private void setViewPagerScrollSpeed() {
		try {
			java.lang.reflect.Field mScroller = null;
			mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);
			FixedSpeedScroller scroller = new FixedSpeedScroller(
					this);
			mScroller.set(viewPager, scroller);
		} catch (NoSuchFieldException e) {

		} catch (IllegalArgumentException e) {

		} catch (IllegalAccessException e) {

		}
	}

	/*
	 * 
	 * 初始化数据，
	 * 
	 * 图片数据
	 */
	int images[] = {
			R.drawable.ic_media,
			R.drawable.ic_nav,
			R.drawable.ic_setting ,
			R.drawable.ic_bt,
			R.drawable.ic_camera,
			R.drawable.ic_carinfo,
			R.drawable.ic_kongtiao,
			R.drawable.ic_media,
			R.drawable.ic_nav,
			R.drawable.ic_setting ,
			R.drawable.ic_bt,
			R.drawable.ic_camera,
			R.drawable.ic_carinfo,
			};
	private ViewPager viewPager;
	private SensorManager sm;
	private TextView valueView;

	private void initData() {
		// 创建图片集合
		list = new ArrayList<View>();
		for (int i = 0; i < images.length; i++) {
			LayoutParams params = new LinearLayout.LayoutParams(100, 300);
			ImageView imageView1 = new ImageView(this);
			// 设置拉伸方式
			imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
			// 设置图片
			imageView1.setImageResource(images[i]);
			imageView1.setLayoutParams(params);
			// 把图片添加到集合
			imageView1.setTag(i);
			// imageView1.setClickable(true);
			imageView1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// Log.d("HYH", "onClick:0"+v.getTag());
				}
			});
			list.add(imageView1);
		}
	}

	public void onClick(View view) {
		switch (view.getId()) {
		// case R.id.send:
		// Intent intent=new Intent("AUTONAVI_STANDARD_BROADCAST_SEND");
		// intent.putExtra("NEXT_ROAD_NAME","南山");
		// sendBroadcast(intent);
		// break;

		default:
			break;
		}
	}
}
