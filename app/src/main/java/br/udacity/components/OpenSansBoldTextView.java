package br.udacity.components;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.AttributeSet;

import br.udacity.R;


public class OpenSansBoldTextView extends android.support.v7.widget.AppCompatTextView{

    public OpenSansBoldTextView(Context context) {
        super(context);
        customizeStyle();
    }

    public OpenSansBoldTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        customizeStyle();
    }

    public OpenSansBoldTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        customizeStyle();
    }

    private void customizeStyle() {
        setTypeface(getContext().getString(R.string.font_openSans_bold));
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

    /**
     * Altera o typeface de parte do texto
     *
     * @param middleText Texto que tera a typeface alterada
     * @param fontPath   Caminho do arquivo de font
     */
    public void setTypefaceOnMiddleText(String middleText, String fontPath) {
        if (getText().toString().contains(middleText)) {
            Typeface font = Typeface.createFromAsset(getContext().getAssets(), fontPath);

            SpannableStringBuilder stringBuilder = new SpannableStringBuilder(getText());
            int startPosition = getText().toString().indexOf(middleText);
            int endPosition = getText().toString().indexOf(middleText) + middleText.length();
            stringBuilder.setSpan(new CustomTypefaceSpan(font), startPosition, endPosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

            setText(stringBuilder);
        }
    }
}
