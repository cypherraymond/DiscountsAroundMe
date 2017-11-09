package com.tl.discountsaroundme;

import android.app.Activity;
import android.support.v4.widget.Space;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tl.discountsaroundme.FirebaseData.DiscountsManager;
import com.tl.discountsaroundme.UiControllers.ItemViewAdapter;

public class AddCategoryToLayout {
    private LinearLayout linearLayout;
    private Activity activity;

    private int spaceWidth;
    private int buttonHeight;

    public AddCategoryToLayout(LinearLayout linearLayout, Activity activity) {
        this.linearLayout = linearLayout;
        this.activity = activity;
        buttonHeight = getPixelsFromDp(28);
        spaceWidth = getPixelsFromDp(6);
    }

    /**
     * Add a button to the linearLayout you just passed to the constructor
     * Adds a space before adding the button
     */
    public void addCategory(final String buttonText, final DiscountsManager discountsManager) {
        LayoutInflater inflater = LayoutInflater.from(activity);

        Button button = (Button) inflater.inflate(R.layout.button_category_tag, null, false);
        button.setText(buttonText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discountsManager.getDiscountsByCategory(buttonText);
            }
        });
        button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, buttonHeight));

        addSpace();
        linearLayout.addView(button, linearLayout.getChildCount());
    }

    private void addSpace() {
        Space space = new Space(activity);
        space.setLayoutParams(new LinearLayout.LayoutParams(spaceWidth, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.addView(space, linearLayout.getChildCount());
    }

    public void clearCategories() {
        linearLayout.removeAllViews();
    }

    /**
     * Converts dp to pixels
     * @return pixels
     */
    private int getPixelsFromDp(int dp) {
        DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }
}
