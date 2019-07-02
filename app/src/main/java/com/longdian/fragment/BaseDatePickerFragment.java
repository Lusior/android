package com.longdian.fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import com.longdian.R;
import com.longdian.util.ToastUtils;

import java.util.Calendar;

/**
 * Created by phoenix on 2017/6/17.
 */

public abstract class BaseDatePickerFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    protected TextView textViewStart;
    protected TextView textViewEnd;

    protected void initDatePicker(View baseView) {
        textViewStart = (TextView) baseView.findViewById(R.id.id_date_start);
        textViewStart.setOnTouchListener(this);
        textViewEnd = (TextView) baseView.findViewById(R.id.id_date_end);
        if (textViewEnd != null) {
            textViewEnd.setOnTouchListener(this);
        }
        baseView.findViewById(R.id.id_search).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.id_search) {
            doSearch();
        }
    }

    protected abstract void doSearch();

    private void showDatePickerDialog(final TextView textView) {
        String dateStr = textView.getText().toString().trim();
        String[] array = dateStr.split("-");

        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        if (array.length == 3) {
            year = Integer.parseInt(array[0]);
            month = Integer.parseInt(array[1]) - 1;
            dayOfMonth = Integer.parseInt(array[2]);
        }
        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String monthStr = (month + 1) < 10 ? "0" + (month + 1) : month + 1 + "";
                String dayOfMonthStr = dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth + "";
                textView.setText(year + "-" + monthStr + "-" + dayOfMonthStr);
            }
        }, year, month, dayOfMonth);
        dialog.show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.id_date_start:
                    showDatePickerDialog(textViewStart);
                    break;
                case R.id.id_date_end:
                    showDatePickerDialog(textViewEnd);
                    break;
            }
        }
        return false;
    }
}
