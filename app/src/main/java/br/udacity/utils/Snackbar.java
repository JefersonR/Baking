package br.udacity.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import br.udacity.R;
import br.udacity.models.ErrorResponse;
import okhttp3.ResponseBody;

/**
 * Created by Jeferson on 30/07/2016.
 */
public class Snackbar {
    public static void make(View view, String message){
        try {
            if(view != null && message != null && !message.isEmpty()){
                android.support.design.widget.Snackbar.make(view, message, android.support.design.widget.Snackbar.LENGTH_LONG).show();

            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    public static void make(Context context, ResponseBody response){
        try {
            String message = "";
            if(response != null){
                ErrorResponse error = ErrorResponse.getResponseError(response, 0);
                message =  error.getMessage();
            }else{
                message = context.getString(R.string.label_connection_fail);
            }

            if(context != null && ((Activity)context).findViewById(android.R.id.content) != null && message != null ){
                android.support.design.widget.Snackbar.make(((Activity)context).findViewById(android.R.id.content), message, android.support.design.widget.Snackbar.LENGTH_LONG).show();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }

    public static void make(Context context, ErrorResponse response){
        try {
            String message = "";
            if(response != null){
                message =  response.getMessageServer();
            }else{
                message = context.getString(R.string.label_connection_fail);
            }

            if(context != null && ((Activity)context).findViewById(android.R.id.content) != null && message != null ){
                android.support.design.widget.Snackbar.make(((Activity)context).findViewById(android.R.id.content), message, android.support.design.widget.Snackbar.LENGTH_LONG).show();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }

    public static void make(Context context, String message){
        try {
            Activity activity = (Activity) context;
            if(activity != null && activity.findViewById(android.R.id.content) != null && message != null ){
                android.support.design.widget.Snackbar.make(activity.findViewById(android.R.id.content), message, android.support.design.widget.Snackbar.LENGTH_LONG).show();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }

    public static void make(Context context, String message, String titleAction,final View.OnClickListener listener){
        android.support.design.widget.Snackbar snackbar;
        try {
            Activity activity = (Activity) context;
            if(activity != null && activity.findViewById(android.R.id.content) != null && message != null ){
                snackbar = android.support.design.widget.Snackbar.make(activity.findViewById(android.R.id.content), message, android.support.design.widget.Snackbar.LENGTH_LONG);
                snackbar .setAction(titleAction, listener);
                snackbar.show();
            }
        }catch(Exception e){
            e.getMessage();
        }

    }
    public static void make(View view, String message, String titleAction,final View.OnClickListener listener){
        android.support.design.widget.Snackbar snackbar;
        try {

            if(view != null  && message != null ){
                snackbar = android.support.design.widget.Snackbar.make(view, message, android.support.design.widget.Snackbar.LENGTH_LONG);
                snackbar .setAction(titleAction, listener);
                snackbar.show();
            }
        }catch(Exception e){
            e.getMessage();
        }

    }



    public static void make(Activity activity, String message){
        try {
            if(activity != null && activity.findViewById(android.R.id.content) != null && message != null && !message.isEmpty()){
                android.support.design.widget.Snackbar.make(activity.findViewById(android.R.id.content), message, android.support.design.widget.Snackbar.LENGTH_LONG).show();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }

    public static void make(Activity activity, int msg){
        try {
            if(activity != null && activity.findViewById(android.R.id.content) != null){
                String message = activity.getResources().getString(msg);
                android.support.design.widget.Snackbar.make(activity.findViewById(android.R.id.content), message, android.support.design.widget.Snackbar.LENGTH_LONG).show();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    public static void make(View view,  int msg){
        try {
            if(view != null){
                String message = view.getResources().getString(msg);
                android.support.design.widget.Snackbar.make(view, message, android.support.design.widget.Snackbar.LENGTH_LONG).show();

            }
        }catch(Exception e){
            e.getMessage();
        }
    }


}
