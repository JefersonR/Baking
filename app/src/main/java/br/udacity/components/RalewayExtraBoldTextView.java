package br.udacity.components;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import br.udacity.R;


public class RalewayExtraBoldTextView extends android.support.v7.widget.AppCompatTextView{

    public RalewayExtraBoldTextView(Context context) {
        super(context);
        customizeStyle();
    }

    public RalewayExtraBoldTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        customizeStyle();
    }

    public RalewayExtraBoldTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        customizeStyle();
    }

    private void customizeStyle() {
        setTypeface(getContext().getString(R.string.font_raleway_extra_bold));
    }

    /**
     * Altera a font do componente
     *
     * @param fontPath Caminho do arquivo de font
     */
    public void setTypeface(String fontPath) {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), fontPath);
        setTypeface(typeface);
    }

}
