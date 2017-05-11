package com.example.wuxie.common.correct;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wuxie.common.correct.impl.InputDoubleImpl;
import com.example.wuxie.common.correct.impl.InputNoEmptyImpl;

/**
 * Created by huangzhixiong on 16/8/17.
 * 控制View的样式
 */
public class ViewStyleHelper {

    public static InputRequest NumberRequest = new InputDoubleImpl();

    public static InputRequest NoEmptyRequest = new InputNoEmptyImpl();

    private static void updateStyle(View button , IOnTextChange... edits) {
        for (IOnTextChange item : edits) {
            if (null == item)
                continue;

            if (item instanceof View) {
                View v = (View) item;
                if (v.getVisibility() != View.VISIBLE) {
                    continue;
                }
            }

            if (!item.isCorrectFormat()) {
                setEnableWithAlpha(button,false);
                return;
            }
        }
        setEnableWithAlpha(button,true);
    }

//    ---------------------dai yang zheng---------------------------------------------------------------

    // 用于Edittext
    public static void editTextBindButton(final View button, final EditText... edits) {
        for (EditText edit : edits) {
            edit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    updateStyle(button, NoEmptyRequest, edits);
                }
            });
        }

        updateStyle(button, NoEmptyRequest, edits);
    }

    public static void editTextBindButton(final View button, final InputRequest request, final EditText... edits) {
        for (EditText edit : edits) {
            edit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    updateStyle(button, request, edits);
                }
            });
        }

        updateStyle(button, request, edits);
    }

    private static<T extends TextView> void updateStyle(View button, InputRequest request, T... edits) {
        for (T item : edits) {
            if (null == item)
                continue;

            if (item.getVisibility() != View.VISIBLE) {
                continue;
            }

            String input = item.getText().toString().trim();
            if (! request.isCorrectFormat(input)) {
                setEnableWithAlpha(button,false);
                return;
            }
        }
        setEnableWithAlpha(button,true);
    }



    public static void setEnableWithAlpha(View btn, boolean enable){
        if (btn == null) return;

        if (btn.isEnabled() != enable){
            btn.setEnabled(enable);
        }

        setAlpha(btn, enable ? 1f : 0.6f);
    }

    public static void setAlpha(View btn,float alpha){
        if (btn  == null) return;

        if (btn.getAlpha() != alpha){
            btn.setAlpha(alpha);
        }
    }

    public static void setVisibility(View view, int visiblity) {
        if (view == null) return;

        if (view.getVisibility() != visiblity) {
            view.setVisibility(visiblity);
        }
    }



}
