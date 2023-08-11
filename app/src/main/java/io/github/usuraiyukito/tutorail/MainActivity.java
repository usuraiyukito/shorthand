package io.github.usuraiyukito.tutorail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private Boolean lastToggleClickedIs;
    private Boolean imageClick = true;
    private Drawable drawable;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
        Button changeButton = findViewById(R.id.changeButton);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        ImageView imageView = findViewById(R.id.imageView);

        changeButton.setOnClickListener(view -> {
            setRandomShorthand();
            setToggleText();
            hideShorthand();
        });

        toggleButton.setOnClickListener(view -> lastToggleClickedIs = true);

        imageView.setOnClickListener(view -> {
            if(imageClick) imageView.setImageDrawable(drawable);
            else imageView.setImageResource(R.drawable.ic_launcher_foreground);

            imageClick = !imageClick;
            lastToggleClickedIs = false;
        });
    }

    private void initialization(){
        setRandomShorthand();

        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        ImageView imageView = findViewById(R.id.imageView);

        imageView.setImageResource(R.drawable.ic_launcher_foreground);

        toggleButton.setChecked(false);
        setToggleText();
    }

    private void setToggleText(){
        ToggleButton toggleButton = findViewById(R.id.toggleButton);

        toggleButton.setTextOn(text);
        CharSequence now_str = toggleButton.isChecked() ? toggleButton.getTextOn() : toggleButton.getTextOff();
        toggleButton.setText(now_str);
    }

    private void hideShorthand(){
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        ImageView imageView = findViewById(R.id.imageView);

        if(lastToggleClickedIs.equals(null)){
            if(lastToggleClickedIs)toggleButton.setChecked(false);
            imageView.setImageResource(R.drawable.ic_launcher_foreground);
        }
        else if(lastToggleClickedIs){
            imageClick = false;
            toggleButton.setChecked(false);
            imageView.setImageDrawable(drawable);
        }else {
            imageClick = true;
            toggleButton.setChecked(true);
            imageView.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }

    private void setRandomShorthand(){
        // ランダム画像のリソースの配列を宣言
        TypedArray shorthand = getResources().obtainTypedArray(R.array.shorthand);
        TypedArray shorthand_str = getResources().obtainTypedArray(R.array.shorthand_str);

        // ランダムな数値を設定
        int rand_result = new Random().nextInt(shorthand.length());

        // ランダムで画像を選択する
        drawable = shorthand.getDrawable(rand_result);
        text = shorthand_str.getString(rand_result);
    }
}