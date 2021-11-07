package tw.cody.bmi;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;


import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.EditText;

import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends Activity {
    private EditText cm, kgw;
    private TextView figure, health, range, text;
    private RadioGroup group;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cm = findViewById(R.id.cm);
        kgw = findViewById(R.id.kgw);
        figure = findViewById(R.id.figure);
        health = findViewById(R.id.health);
        group = findViewById(R.id.group);
        range = findViewById(R.id.range);
        text = findViewById(R.id.text);


    }


    public void count (View view) {
        if (group.getCheckedRadioButtonId() == -1) {
            Toast.makeText(MainActivity.this, "請選擇性別", Toast.LENGTH_SHORT).show();
        } else if (cm.getText().toString().length() == 0) {
            Toast.makeText(MainActivity.this, "請輸入身高", Toast.LENGTH_SHORT).show();
        } else if (kgw.getText().toString().length() == 0) {
            Toast.makeText(MainActivity.this, "請輸入體重", Toast.LENGTH_SHORT).show();
        }


        if (group.getCheckedRadioButtonId() != -1 && cm.getText().toString().length() != 0 && kgw.getText().toString().length() != 0) {
            figure.setText("");
            health.setText("");
            range.setText("");
            double cm1 = Double.parseDouble(cm.getText().toString());
            double kgw1 = Double.parseDouble(kgw.getText().toString());
            double h = cm1 / 100;
            double bmi = kgw1 / h / h;
            double bmi1 = Math.round(bmi * 10);
            double bmi2 = bmi1 / 10;
            figure.setText("" + bmi2);

            if (group.getCheckedRadioButtonId() == R.id.men) {
                if (bmi2 < 18.5) {
                    health.setText("男性,過輕");
                    health.setTextColor(Color.parseColor("#828f35"));
                    health.setTypeface(Typeface.DEFAULT_BOLD);
                    range.setText("BMI<18.5");
                }
                else if (18.5 <= bmi2 && bmi2 < 24) {
                    health.setText("男性,正常");
                    health.setTextColor(Color.parseColor("#358f3f"));
                    health.setTypeface(Typeface.DEFAULT_BOLD);
                    range.setText("18.5<=BMI<24");
                }
                else if (24 <= bmi2 && bmi2 < 27) {
                    health.setText("男性,過重");
                    health.setTextColor(Color.parseColor("#8f3535"));
                    health.setTypeface(Typeface.DEFAULT_BOLD);
                    range.setText("24<=BMI<27");
                }
                else if (27 <= bmi2 && bmi2 < 30) {
                    health.setText("男性,輕度肥胖");
                    health.setTextColor(Color.parseColor("#8f3535"));
                    health.setTypeface(Typeface.DEFAULT_BOLD);
                    range.setText("27 <= BMI < 30");
                }
                else if (30 <= bmi2 && bmi2 < 35) {
                    health.setText("男性,中度肥胖");
                    health.setTextColor(Color.parseColor("#8f3535"));
                    health.setTypeface(Typeface.DEFAULT_BOLD);
                    range.setText("30 <= BMI < 35");
                }
                else if (35 <= bmi2) {
                    health.setText("男性,重度肥胖");
                    health.setTextColor(Color.parseColor("#8f3535"));
                    health.setTypeface(Typeface.DEFAULT_BOLD);
                    range.setText("35 <= BMI");
                }
            }else if (group.getCheckedRadioButtonId() == R.id.women) {
                if (bmi2 < 18.5) {
                    health.setText("女性,過輕");
                    health.setTextColor(Color.parseColor("#828f35"));
                    health.setTypeface(Typeface.DEFAULT_BOLD);
                    range.setText("BMI<18.5");
                }
                else if (18.5 <= bmi2 && bmi2 < 24) {
                    health.setText("女性,正常");
                    health.setTextColor(Color.parseColor("#358f3f"));
                    health.setTypeface(Typeface.DEFAULT_BOLD);
                    range.setText("18.5<=BMI<24");
                }
                else if (24 <= bmi2 && bmi2 < 27) {
                    health.setText("女性,過重");
                    health.setTextColor(Color.parseColor("#8f3535"));
                    health.setTypeface(Typeface.DEFAULT_BOLD);
                    range.setText("24<=BMI<27");
                }
                else if (27 <= bmi2 && bmi2 < 30) {
                    health.setText("女性,輕度肥胖");
                    health.setTextColor(Color.parseColor("#8f3535"));
                    health.setTypeface(Typeface.DEFAULT_BOLD);
                    range.setText("27 <= BMI < 30");
                }
                else if (30 <= bmi2 && bmi2 < 35) {
                    health.setText("女性,中度肥胖");
                    health.setTextColor(Color.parseColor("#8f3535"));
                    health.setTypeface(Typeface.DEFAULT_BOLD);
                    range.setText("30 <= BMI < 35");
                }
                else if (35 <= bmi2) {
                    health.setText("女性,重度肥胖");
                    health.setTextColor(Color.parseColor("#8f3535"));
                    health.setTypeface(Typeface.DEFAULT_BOLD);
                    range.setText("35 <= BMI");
                }
            }
            text.setText("");
            if ( bmi2 < 18.5 || bmi2 > 24) {
                double among = Math.round(18.5*h*h*10);
                double among1 = among/10;
                double among2 = Math.round(24*h*h*10);
                double among3 = among2/10;
                text.setText("您適中的體重是:" +among1 + "~"  + among3 );
            }

            group.clearCheck();
            cm.setText("");
            kgw.setText("");
        }
    }

    public void end(View view) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("要離開嗎")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("no",null)
                .create();
        dialog.show();
    }


}