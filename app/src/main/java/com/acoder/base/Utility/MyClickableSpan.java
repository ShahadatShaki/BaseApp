package com.acoder.base.Utility;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class MyClickableSpan extends ClickableSpan {

    /*



      MyClickableSpan merchentName = new MyClickableSpan() {

                @Override
                public void onClick(View widget) {
                    MerchantProfile merchantProfile = new MerchantProfile();
                    merchantProfile.setId(review.getMerchantId());
                    merchantProfile.setName(review.getMerchantName());
                    merchantProfile.setProfilePicture(review.getMerchantThumbnail());
                    Intent intent = new Intent(context, MerchantActivity.class);
                    intent.putExtra("obj", merchantProfile);
                    context.startActivity(intent);
                }
            };

       MyClickableSpan userName = new MyClickableSpan() {

            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(context, UserProfileActivity.class);
                intent.putExtra("id", dataList.get(i).getUserId());
                context.startActivity(intent);
            }
        };


      SpannableString title;
      title = new SpannableString(review.getUserName() + " reviewed " + review.getMerchantName());
      title.setSpan(new StyleSpan(Typeface.BOLD), title.toString().indexOf(review.getUserName()), title.toString().indexOf(review.getUserName()) + review.getUserName().length(), 0);
      title.setSpan(new StyleSpan(Typeface.BOLD), title.toString().indexOf(review.getMerchantName()), title.toString().indexOf(review.getMerchantName()) + review.getMerchantName().length(), 0);

      title.setSpan(merchentName, title.toString().indexOf(review.getMerchantName()), title.toString().indexOf(review.getMerchantName()) + review.getMerchantName().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);



     */


    @Override
    public void onClick(View view) {

    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setUnderlineText(false);
        ds.setColor(Color.parseColor("#000000"));
    }
}