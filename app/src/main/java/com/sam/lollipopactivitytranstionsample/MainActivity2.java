package com.sam.lollipopactivitytranstionsample;

import android.app.Activity;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.os.Bundle;
import android.transition.Transition;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity2 extends Activity {

    public static final String EXTRA_PARAM_ID = "MainActivity2.extra";
    public static final String VIEW_NAME_HEADER_IMAGE = "detail:header:image";
    public static final String VIEW_NAME_HEADER_TITLE = "detail:header:title";

    private ImageView mHeaderImageView;
    private TextView mHeaderText;
    private TextView mBodyText;
    private BirdInfo mBirdInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        mBirdInfo=getIntent().getExtras().getParcelable(EXTRA_PARAM_ID);

        mHeaderImageView = (ImageView) findViewById(R.id.imageview_header);
        mHeaderText = (TextView) findViewById(R.id.textview_title);
        mBodyText= (TextView) findViewById(R.id.textview_body);
        ViewCompat.setTransitionName(mHeaderImageView, VIEW_NAME_HEADER_IMAGE);
        ViewCompat.setTransitionName(mHeaderText, VIEW_NAME_HEADER_TITLE);

        loadItem();
    }


    private void loadItem() {
        // Set the title TextView to the item's name and author
        mHeaderText.setText(mBirdInfo.name + "\n" + mBirdInfo.role);

        mHeaderImageView.setImageResource(mBirdInfo.imageRes);

       mBodyText.setText(mBirdInfo.role);
        //in case you want to do some staff while the image is being transitioned maybe load images from internet or other stuffs u know
    }





    private boolean addTransitionListener() {
        final Transition transition = getWindow().getSharedElementEnterTransition();

        if (transition != null) {
            // There is an entering shared element transition so add a listener to it
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    // As the transition has ended, we can now load the full-size image here


                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionStart(Transition transition) {
                    // No-op
                }

                @Override
                public void onTransitionCancel(Transition transition) {
                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionPause(Transition transition) {
                    // No-op
                }

                @Override
                public void onTransitionResume(Transition transition) {
                    // No-op
                }
            });
            return true;
        }

        // If we reach here then we have not added a listener
        return false;
    }
}
