package com.sam.lollipopactivitytranstionsample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView mGrid= (GridView) findViewById(R.id.mGrid);
        MyGridAdapter myGridAdapter=new MyGridAdapter(this);
        mGrid.setAdapter(myGridAdapter);
    }
    class MyGridAdapter extends BaseAdapter{
        private Activity mActivity;

        private ArrayList<BirdInfo> mBirds;
        public MyGridAdapter(Activity context){
            mActivity =context;
            mBirds =new ArrayList<BirdInfo>();
            mBirds.add(new BirdInfo(getString(R.string.red_name), getString(R.string.red_role), R.drawable.red));
            mBirds.add(new BirdInfo(getString(R.string.chuck_name), getString(R.string.chuck_role), R.drawable.chuck));
            mBirds.add(new BirdInfo(getString(R.string.bomb_name), getString(R.string.bomb_role), R.drawable.bomb));
            mBirds.add(new BirdInfo(getString(R.string.the_blues_name), getString(R.string.the_blues_role), R.drawable.the_blue));
            mBirds.add(new BirdInfo(getString(R.string.stella_name), getString(R.string.stella_role), R.drawable.stella));
            mBirds.add(new BirdInfo(getString(R.string.matilda_name), getString(R.string.matilda_role), R.drawable.matilda));
            mBirds.add(new BirdInfo(getString(R.string.bubbles_name), getString(R.string.bubbles_role), R.drawable.bubbles));
        }
        @Override
        public int getCount() {
            return mBirds.size();
        }

        @Override
        public Object getItem(int position) {
            return mBirds.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            LayoutInflater inflater = (LayoutInflater) mActivity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.grid_item, null);
                viewHolder=new ViewHolder();
                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.gridItemText);
                viewHolder.ivIcon = (ImageView)convertView.findViewById(R.id.gridItemImage);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvTitle.setText(mBirds.get(position).name);
            viewHolder.ivIcon.setImageResource(mBirds.get(position).imageRes);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, MainActivity2.class);
                    intent.putExtra(MainActivity2.EXTRA_PARAM_ID,(BirdInfo) getItem(position));

                    ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            mActivity
                            ,new Pair<View, String>(viewHolder.ivIcon,MainActivity2.VIEW_NAME_HEADER_IMAGE)
                            ,new Pair<View, String>(viewHolder.tvTitle,MainActivity2.VIEW_NAME_HEADER_TITLE));

                    // Now we can start the Activity, providing the activity options as a bundle
                    ActivityCompat.startActivity(mActivity, intent, activityOptions.toBundle());
                    // END_INCLUDE(start_activity)
                }
            });
            return convertView;
        }
        private class ViewHolder {
            ImageView ivIcon;
            TextView tvTitle;
        }
    }
}
