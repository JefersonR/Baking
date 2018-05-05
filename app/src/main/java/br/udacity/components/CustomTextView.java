package br.udacity.components;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

import br.udacity.R;


public class CustomTextView extends android.support.v7.widget.AppCompatTextView{

    public CustomTextView(Context context) {
        super(context);
        customizeStyle();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        customizeStyle();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        customizeStyle();
    }

    private void customizeStyle() {
        setTypeface(getContext().getString(R.string.font_openSans_regular));
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
        try {
            if (getText().toString().contains(middleText)) {
                Typeface font = Typeface.createFromAsset(getContext().getAssets(), fontPath);

                SpannableStringBuilder stringBuilder = new SpannableStringBuilder(getText());
                int startPosition = getText().toString().indexOf(middleText);
                int endPosition = getText().toString().indexOf(middleText) + middleText.length();
                stringBuilder.setSpan(new CustomTypefaceSpan(font), startPosition, endPosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

                setText(stringBuilder);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    /**
     * Define a cor de uma parte do texto retornando string maiuscula ou minuscula
     *
     * @param middleText Trecho do texto que deve ser colorido
     * @param color      Cor do trecho de texto
     */
    public void setColorOnMiddleText(String middleText, int color) {
        try {
            String text = getText().toString();

            SpannableStringBuilder spannable;

            spannable = new SpannableStringBuilder(getText());

            spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), color)),
                    text.toUpperCase().indexOf(middleText.toUpperCase()),
                    text.toUpperCase().indexOf(middleText.toUpperCase()) + middleText.length(),
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            setText(spannable);
        }catch (Exception e){
            e.getMessage();
        }
    }

}
