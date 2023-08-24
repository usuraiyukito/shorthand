package io.github.usuraiyukito.tutorail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private Boolean imageClick = true;
    private Drawable drawable;
    private String text;
    private Boolean showShorthand = true;
    private Boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();

        Button changeButton = findViewById(R.id.changeButton);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        ImageView imageView = findViewById(R.id.imageView);

        FloatingActionButton click = findViewById(R.id.floatingActionButton);

        click.setOnClickListener(view ->{
            if(showShorthand) {
                click.setImageResource(R.drawable.baseline_text_fields_24);
                toggleButton.setChecked(true);
                imageView.setImageResource(R.drawable.ic_launcher_foreground);
                imageClick = true;
            }else {
                click.setImageResource(R.drawable.baseline_edit_24);
                toggleButton.setChecked(false);
                imageView.setImageDrawable(drawable);
                imageClick = false;
            }

            clicked = true;
            showShorthand = !showShorthand;
        });


        changeButton.setOnClickListener(view -> {
            if(clicked) {
                setRandomShorthand();
                setToggleText();
                hideShorthand();
                clicked = false;
            }
        });

        toggleButton.setOnClickListener(view -> clicked = true);

        imageView.setOnClickListener(view -> {
            if(imageClick) imageView.setImageDrawable(drawable);
            else imageView.setImageResource(R.drawable.ic_launcher_foreground);

            imageClick = !imageClick;
            clicked = true;
        });
    }

    private void initialization(){
        setRandomShorthand();

        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        ImageView imageView = findViewById(R.id.imageView);

        imageView.setImageDrawable(drawable);

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

        if(showShorthand){
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