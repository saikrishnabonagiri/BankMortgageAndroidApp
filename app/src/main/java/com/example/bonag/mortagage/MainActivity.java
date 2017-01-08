package com.example.bonag.mortagage;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends Activity {
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getCurrencyInstance();
    private double billAmount = 0.0;
    private double rate=15;
    private int period = 15;

    private TextView loanTextView;
    private TextView rateTextView;
    private TextView periodTextView;
    private TextView monthTextView;
    private TextView totalTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  loanTextView = (TextView) findViewById(R.id.loanTextView);
      //  rateTextView = (TextView) findViewById(R.id.rateTextView);
        periodTextView = (TextView) findViewById(R.id.periodTextView);
        monthTextView = (TextView) findViewById(R.id.monthTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);
        EditText rateEditText = (EditText) findViewById(R.id.rateEditText);
        rateEditText.addTextChangedListener(rateEditTextWatcher);
        SeekBar percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);

    }


    private void calculate() {
        periodTextView.setText("Years:"+Double.toString(period));


        double monthlyInterestRate = rate / 1200;
        double monthlyPayment = (billAmount * monthlyInterestRate) / (1 - (1 / Math.pow((1 + monthlyInterestRate), (period * 12))));



        monthTextView.setText("$"+Double.toString(monthlyPayment));

        double totalPayment = monthlyPayment * period * 12;


        totalTextView.setText("$"+Double.toString(totalPayment));


    }

    private final OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener()

    {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            period = progress ;
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };


    private final TextWatcher rateEditTextWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                rate = Double.parseDouble(s.toString());
                //rateTextView.setText(currencyFormat.format(rate));
            } catch (NumberFormatException e) {
               // rateTextView.setText("");
                rate = 0.0;
            }
            calculate();

        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {      }


    };




    private final TextWatcher amountEditTextWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                billAmount = Double.parseDouble(s.toString()) ;
               // loanTextView.setText(currencyFormat.format(billAmount));
            } catch (NumberFormatException e) {
              //  loanTextView.setText("");
                billAmount = 0.0;
            }
            calculate();

        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {      }


    };






}
