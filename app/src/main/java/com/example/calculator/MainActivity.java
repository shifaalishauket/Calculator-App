package com.example.calculator;

import static android.os.Build.VERSION_CODES.P;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button AC,C,percentage,one,two,three,four,five,six,seven,eight,nine,zero,dot,divide,multiply,minus,plus,eq_to,viewHistoryDetailButton;
    String operators;
    List<model> history= new ArrayList<>();
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        dot = findViewById(R.id.dot);
        AC = findViewById(R.id.ac);
        C = findViewById(R.id.c);
        percentage = findViewById(R.id.percentage);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        divide = findViewById(R.id.divide);
        multiply = findViewById(R.id.multiply);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        eq_to = findViewById(R.id.eq_to);
        viewHistoryDetailButton = findViewById(R.id.viewHistoryDetailButton);

        /* concatenation in text view */

        operators = "";
        textView.setText("");
        final boolean[] dotAdded = {false};

        one.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textView.append("1");
            }
        });

        viewHistoryDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the HistoryDetailFragment.
                Bundle bundle = new Bundle();
                bundle.putSerializable("valuesArray", (Serializable) history);
                Fragment historyDetailFragment = new HIstoryFragment();
                historyDetailFragment.setArguments(bundle);
                // Use a FragmentManager to replace the current fragment with the HistoryDetailFragment.
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //frame_container is your layout name in xml file
                transaction.replace(R.id.loadfrag, historyDetailFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        two.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textView.append("2");
            }
        });

        three.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textView.append("3");
            }
        });

        four.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textView.append("4");
            }
        });

        five.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textView.append("5");
            }
        });

        six.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textView.append("6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textView.append("7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textView.append("8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textView.append("9");
            }
        });

        zero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textView.append("0");
            }
        });

        dot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String old = textView.getText().toString();
                if (!old.equals("")){
                    if (operators.equals("")){
                        if (!old.contains(".")){
                            textView.append(".");
                        }
                    }
                    else{
                        String[] separated = old.split(operators);
                        if (separated.length>1){
                            if (!separated[1].contains(".")){
                                textView.append(".");
                            }
                        }
                    }
                }
            }
        });

        divide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (operators=="") {
                    textView.append("/");
                    operators = "/";
                }
            }
        });

        multiply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (operators=="") {
                    textView.append("x");
                    operators = "x";
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (operators==""){
                textView.append("-");
                operators="-";
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (operators=="") {
                    textView.append("+");
                    operators = "\\+";
                }
            }
        });

        eq_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentString = textView.getText().toString();

                double result = 0;
                if (operators != "") {
                    String[] separated = currentString.split(operators);
                    float num1 = Float.parseFloat(separated[0]);
                    float num2 = Float.parseFloat(separated[1]);
                    if (operators == "-") {
                        result = num1 - num2;
                        textView.setText(String.valueOf(result));
                    } else if (operators == "\\+") {
                        result = num1 + num2;
                        textView.setText(String.valueOf(result));
                    } else if (operators == "/") {
                        result = num1 / num2;
                        textView.setText(String.valueOf(result));
                    } else if (operators == "x") {
                        result = num1 * num2;
                        textView.setText(String.valueOf(result));
                    } else if (operators == "%") {
                        result = (num1 / num2) * 100;

                        textView.setText(String.valueOf(result));
                    }
                    operators = "";
                }
                model obj = new model(currentString,String.valueOf(result));
                history.add(obj);
            }
        });

        C.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String old = textView.getText().toString();
                if (!old.equals("")){
                    String num = old.substring(0, old.length() - 1);
                    textView.setText(num);
                    if (!operators.equals("")){
                        if (old.endsWith(operators)){
                            operators = "";
                        }
                    }
                }

            }
        });

        AC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textView.setText("");
                textView.append("");
                operators = "";
            }
        });

        percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.append("%");
                operators="%";
            }
        });
    }
}