package br.udacity.utils;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by Jeferson on 24/07/2017.
 */

public class DialogSingleton {
    private Dialog dialog;
    // Variável estática que conterá a instância do método
    private static DialogSingleton instance;

    static {
        // Operações de inicialização da classe

    }

    /**
     * Método estático de acesso único ao objeto
     * @return instância de MeuSingleton
     */
   public static DialogSingleton getInstance(){

        if(instance == null)
        {
            inicializaInstancia();
            // O valor é retornado para quem está pedindo
        }
        return instance;
        // Retorna o a instância do objeto

    }

    /**
     * Este metodo é sincronizado para evitar que devido a concorrencia sejam criados mais de
     * uma instancia.
     */
    private static synchronized void inicializaInstancia()
    {
        if (instance == null)
        {
            instance = new DialogSingleton();
        }
    }

    public Dialog dialog(Context context, String Title, String message, boolean tryAgain, DialogCustomUtil.OnItemClick onItemClick) {
        try {
            dismiss();
            if (tryAgain) {
                dialog =    DialogCustomUtil.dialog(context, Title, message, "Tentar novamente", "OK", onItemClick);
            } else {
                dialog =     DialogCustomUtil.dialog(context,Title, message, onItemClick);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return  dialog;
    }


    public Dialog dialog(Context context,String Title, String message, DialogCustomUtil.OnItemClick onItemClick) {
        try {
            dismiss();
            dialog = DialogCustomUtil.dialog(context, Title, message, onItemClick);
        } catch (Exception e) {
            e.getMessage();
        }
        return  dialog;
    }

    private void dismiss(){
        if (dialog != null) {
            dialog.dismiss();
        }
    }


}
