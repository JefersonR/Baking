package br.udacity.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import br.udacity.R;


/**
 * Created by Jeferson on 21/06/2017.
 */

public class DialogCustomUtil {

    public static Dialog dialog(final Context context, String title, String message, String labelOk, String labelCancel, boolean cancelable, final boolean hasDismiss,
                                final boolean hasButtonOK, final boolean hasButtonCancel, final OnItemClick mItemOk, final OnItemClick mItemCancel) {

        final Dialog dialog = new Dialog(context, R.style.MythemeDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(cancelable);
        dialog.setContentView(R.layout.dialog_custom_scl);
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnConfirm = (Button) dialog.findViewById(R.id.btn_send);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        TextView txtTitle = (TextView) dialog.findViewById(R.id.txt_title);
        TextView txtMsg = (TextView) dialog.findViewById(R.id.txt_msg);

        setText(txtTitle, title);
        setText(txtMsg, message);
        setLabel(btnConfirm, labelOk);
        setLabel(btnCancel, labelCancel);
        buttonManager(dialog, btnConfirm, mItemOk, hasDismiss, hasButtonOK);
        buttonManager(dialog, btnCancel, mItemCancel, hasDismiss, hasButtonCancel);

        dialog.show();
        return dialog;
    }

   /* return dialog( context,  title,  message,  labelOk,  labelCancel,  cancelable,   hasDismiss,
                   hasButtonOK,  hasButtonCancel,  mItemOk, mItemCancel);*/

    public static Dialog dialog(final Context context, String title, String message, final boolean dismiss, final OnItemClick mOnItemClick) {
        return dialog(context, title, message, null, null, false, dismiss, true, false, mOnItemClick, null);
    }


    public static Dialog dialog(final Context context, String title, String message, String labelOk, String labelCancel, final OnItemClick mItemOk, final OnItemClick mItemCancel) {

        return dialog(context, title, message, labelOk, labelCancel, false, true,
                true, true, mItemOk, mItemCancel);
    }

    public static Dialog dialog(final Context context, String title, String message, String labelOk, final OnItemClick mItemOk, boolean cancelable) {
        return dialog(context, title, message, labelOk, null, cancelable, true,
                true, false, mItemOk, null);
    }

    public static Dialog dialog(final Context context, String title, String message, String labelOk, String labelCancel, final OnItemClick mItemOk) {
        return dialog(context, title, message, labelOk, labelCancel, false, true,
                true, true, mItemOk, null);
    }

    public static Dialog dialog(final Context context, String message, String labelOk, String labelCancel, final OnItemClick mItemOk) {
        return dialog(context, null, message, labelOk, labelCancel, false, true,
                true, true, mItemOk, null);
    }

    public static Dialog dialog(final Context context, String message, final boolean hasDismiss, boolean hasButtonCancel, final OnItemClick mOnItemClick) {
        return dialog(context, null, message, null, null, false, hasDismiss,
                true, hasButtonCancel, mOnItemClick, null);
    }


    public static Dialog dialog(final Context context, String title, String message, final boolean hasDismiss, boolean hasBtnCancel, final OnItemClick mOnItemClick) {
        return dialog(context, title, message, null, null, false, hasDismiss,
                true, hasBtnCancel, mOnItemClick, null);
    }


    public static Dialog dialog(final Context context, String title, String message, final OnItemClick mOnItemClick) {
        return dialog(context, title, message, null, null, true, true,
                true, false, mOnItemClick, null);
    }

    public static Dialog dialog(final Context context, String title, String message, final OnItemClick mOnItemClick, final OnItemClick mItemCancel) {
        return dialog(context, title, message, null, null, false, true,
                true, true, mOnItemClick, mItemCancel);
    }

    public static Dialog dialog(final Context context, String title, String message, final OnItemClick mOnItemClick, boolean hasButtonCancel) {
        return dialog(context, title, message, null, null, false, true,
                true, hasButtonCancel, mOnItemClick, null);
    }


    public static Dialog dialog(final Context context, String title, String message, String labelOk) {
        return dialog(context, title, message, labelOk, null, true, true,
                true, false, null, null);
    }

    public static Dialog dialog(final Context context, String title, String message) {
        return dialog(context, title, message, null, null, true, true,
                true, false, null, null);
    }

    public static Dialog dialog(final Context context, String message) {
        return dialog(context, null, message, null, null, true, true,
                true, false, null, null);
    }

    public interface OnItemClick {
        public void onItemClick(View view);
    }

    private static void buttonManager(final Dialog dialog, Button button, final OnItemClick onItemClick, final boolean hasDismiss, boolean hasButton) {
        if (onItemClick != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onItemClick(v);
                    if (hasDismiss)
                        dialog.dismiss();

                }
            });
        } else if (hasButton) {
            btnListener(dialog, button);
        } else {
            button.setVisibility(View.GONE);
        }
    }

    private static void btnListener(final Dialog dialog, Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
    }

    private static void setText(TextView txt, String text) {
        if (text != null) {
            txt.setText(text);
        } else {
            txt.setVisibility(View.GONE);
        }
    }


    private static void setLabel(Button button, String text) {
        if (text != null) {
            button.setText(text);
        }
    }

}
