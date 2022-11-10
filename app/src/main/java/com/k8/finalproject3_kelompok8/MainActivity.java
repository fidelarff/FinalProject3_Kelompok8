package com.k8.finalproject3_kelompok8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText ed1;
    Pattern p;
    Button bl;
    String op = "+";
    String oldNumber = "";
    boolean isNewOp = true;

    private Button equalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.editText);
        bl = findViewById(R.id.buEqual);

        //Agar titik tidak bisa ditambahkan lagi setelah digunakan, != 1.0.0
        p = Pattern.compile("^(\\d+)?([.]?\\d{0,2})?$");

        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Matcher m = p.matcher(ed1.getText().toString());
                if (!m.matches()) {
                    ed1.setError("Enter valid number");
                    bl.setEnabled(false);
                }
                else{
                    bl.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        equalButton = findViewById(R.id.buEqual);
        ed1.addTextChangedListener(equalWatcher);
    }

    //Disable button equal jika tidak ada input
    private TextWatcher equalWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String inputNumber = ed1.getText().toString();

            //Enable jika field tidak kosong
            equalButton.setEnabled(!inputNumber.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void numberEvent(View view) {
        //Menghilangkan nol saat diklik
        if(isNewOp)
            ed1.setText("");
        isNewOp = false;

        //Menambahkan nomor ke edit text
        String number = ed1.getText().toString();
        switch (view.getId()){
            case R.id.bu1:
                number += "1";
                break;
            case R.id.bu2:
                number += "2";
                break;
            case R.id.bu3:
                number += "3";
                break;
            case R.id.bu4:
                number += "4";
                break;
            case R.id.bu5:
                number += "5";
                break;
            case R.id.bu6:
                number += "6";
                break;
            case R.id.bu7:
                number += "7";
                break;
            case R.id.bu8:
                number += "8";
                break;
            case R.id.bu9:
                number += "9";
                break;
            case R.id.bu0:
                number += "0";
                break;
            case R.id.buDot:
                number += ".";
                break;
            case R.id.buPlusMinus:
                number = "-" + number;
                break;
        }
        ed1.setText(number);
    }

    public void operatorEvent(View view) {
        isNewOp = true;
        oldNumber = ed1.getText().toString();
        switch (view.getId()){
            case R.id.buDivide: op = "/";
                break;
            case R.id.buMultiply: op = "*";
                break;
            case R.id.buPlus: op = "+";
                break;
            case R.id.buMinus: op = "-";
                break;
        }
    }

    public void equalEvent(View view) {
        //Operasi
        String newNumber = ed1.getText().toString();
        double result = 0.0;
        switch (op){
            case "+":
                result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
                break;
            case "-":
                result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
                break;
            case "*":
                result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
                break;
            case "/":
                result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
                break;
        }
        ed1.setText(result + "");
    }

    public void acEvent(View view) {
        ed1.setText("0");
        isNewOp = true;
    }

    public void percentEvent(View view) {
        double no = Double.parseDouble(ed1.getText().toString()) / 100;
        ed1.setText(no + "");
        isNewOp = true;
    }
}