package com.krishna.localizationdemo;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.krishna.localizationdemo.LocaleHelper;

public class MainActivity extends AppCompatActivity {
    TextView language_dialog,text1;
    boolean lang_selected;
    Context context;
    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        language_dialog = (TextView)findViewById(R.id.lang_text);
        text1=(TextView)findViewById(R.id.textView);
        language_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] Language = {"ENGLISH", "हिन्दी","मराठी"};
                final int checkedItem;
                if(lang_selected)
                {
                    checkedItem=1;
                }else
                {
                    checkedItem=0;
                }
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select a Language...")
                        .setSingleChoiceItems(Language, checkedItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,""+which,Toast.LENGTH_SHORT).show();
                                language_dialog.setText(Language[which]);
                                lang_selected= Language[which].equals("ENGLISH");
                                //if user select prefered language as English then
                                if(Language[which].equals("ENGLISH"))
                                {
                                    context = LocaleHelper.setLocale(MainActivity.this, "en");
                                    resources = context.getResources();
                                    text1.setText(resources.getString(R.string.text));
                                }
                                //if user select prefered language as Hindi then
                                if(Language[which].equals("हिन्दी"))
                                {
                                    context = LocaleHelper.setLocale(MainActivity.this, "hi");
                                    resources = context.getResources();
                                    text1.setText(resources.getString(R.string.text));
                                }

                                if(Language[which].equals("मराठी"))
                                {
                                    context = LocaleHelper.setLocale(MainActivity.this, "mr");
                                    resources = context.getResources();
                                    text1.setText(resources.getString(R.string.text));
                                }
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        });
    }
}