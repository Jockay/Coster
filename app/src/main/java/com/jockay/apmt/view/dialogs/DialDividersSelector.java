package com.jockay.apmt.view.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.jockay.apmt.R;
import com.jockay.apmt.controller.SharedPref;
import com.jockay.apmt.controller.util.MyUtil;
import com.jockay.apmt.view.activities.MainActivity;

public class DialDividersSelector {

    private AlertDialog dialog;

    public final String[] numbers = new String[]{
            "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

    public DialDividersSelector(final MainActivity mainActivity) {
        int index = MyUtil.getIndexOfElement(numbers, String.valueOf(new SharedPref(mainActivity).getPeopleNumber()));

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mainActivity);
        builder.setTitle(R.string.people_number)
                .setSingleChoiceItems(numbers, index, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
//                        item.setTitle(numbers[i]);
                        mainActivity.setPeopleNumActionIcon(numbers[i]);
                        new SharedPref(mainActivity).storePeopleNumber(numbers[i]);
                        mainActivity.calculateCosts(new SharedPref(mainActivity).getPeopleNumber());
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
        ;
        dialog = builder.create();
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }
}
