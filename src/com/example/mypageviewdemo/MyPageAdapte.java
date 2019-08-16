package com.example.mypageviewdemo;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class MyPageAdapte extends PagerAdapter {
	private List<View> mList;

	public MyPageAdapte(List<View> list) {
		this.mList = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		int p = position % mList.size();
		View view = mList.get(p);
		ViewGroup parent = (ViewGroup) view.getParent();
		// Log.i("ViewPaperAdapter", parent.toString());
		if (parent != null) {
			parent.removeView(view);
		}
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("HYH", "onClick:0"+v.getTag());
			}
		});
		Log.d("HYH", "parent:"+position+","+p);
		container.addView(mList.get(p));
		return mList.get(p);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
//		container.removeView((View) object);
	}
}
