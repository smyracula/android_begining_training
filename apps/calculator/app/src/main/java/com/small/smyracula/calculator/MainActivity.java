package com.small.smyracula.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.small.smyracula.calculator.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import static android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private ActivityMainBinding binding;
    private double number1 = 0.0;
    private double number2 = 0.0;
    private String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.result.setText("0");
        OnClickListener oplistener = new OnClickListener() {
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.acButton: {
                        number1 = 0.0;
                        number2 = 0.0;
                        operation = "";
                        binding.result.setText("");
                        break;
                    }
                    case R.id.plusminusButton: {
                        if (number2 != 0.0) {
                            number2 = -number2;
                            binding.result.setText(String.valueOf(number2));
                        } else {
                            number1 = -number1;
                            binding.result.setText(String.valueOf(number1));
                        }
                        break;
                    }
                    case R.id.modButton: {
                        if (number2 != 0.0) {
                            number2 = number2 / 100;
                            binding.result.setText(String.valueOf(number2));
                        } else {
                            number1 = number1 / 100;
                            binding.result.setText(String.valueOf(number1));
                        }
                        break;
                    }
                    case R.id.equalsButton: {
                        double r = 0.0;
                        switch (operation) {
                            case "-": {
                                r = number1 - number2;
                                break;
                            }
                            case "+": {
                                r = number1 + number2;
                                break;
                            }
                            case "X": {
                                r = number1 * number2;
                                break;
                            }
                            case "/": {
                                r = number1 / number2;
                                break;
                            }
                        }
                        number1 = r;
                        binding.result.setText(String.valueOf(r));
                    }
                    default: {
                        operation = ((Button) view).getText().toString();
                    }
                }
            }
        };
        binding.acButton.setOnClickListener(oplistener);
        binding.plusminusButton.setOnClickListener(oplistener);
        binding.modButton.setOnClickListener(oplistener);
        binding.dividedButton.setOnClickListener(oplistener);
        binding.sevenButton.setOnClickListener(this);
        binding.eightButton.setOnClickListener(this);
        binding.nineButton.setOnClickListener(this);
        binding.multiplyButton.setOnClickListener(oplistener);
        binding.fourButton.setOnClickListener(this);
        binding.fiveButton.setOnClickListener(this);
        binding.sixButton.setOnClickListener(this);
        binding.subtractionButton.setOnClickListener(oplistener);
        binding.oneButton.setOnClickListener(this);
        binding.twoButton.setOnClickListener(this);
        binding.threeButton.setOnClickListener(this);
        binding.plusButton.setOnClickListener(oplistener);
        binding.zeroButton.setOnClickListener(this);
        binding.commaButton.setOnClickListener(oplistener);
        binding.equalsButton.setOnClickListener(oplistener);
    }

    @Override
    public void onClick(View view) {
        if (operation.isEmpty()) {
            String currentNumber1 = "";
            if((number1 - (int)number1 ) > 0){
                currentNumber1 = String.valueOf(number1);
            } else {
                currentNumber1 = String.valueOf((int)number1);
            }
            String numberAdd = ((Button) view).getText().toString();
            String newNumber = currentNumber1 + numberAdd;
            number1 = Double.parseDouble(newNumber);
            binding.result.setText(String.valueOf(number1));
        } else {
            String currentNumber2 = "";
            if((number2 - (int)number2 ) > 0){
                currentNumber2 = String.valueOf(number2);
            } else {
                currentNumber2 = String.valueOf((int)number2);
            }
            String numberAdd = ((Button) view).getText().toString();
            String newNumber = currentNumber2 + numberAdd;
            number2 = Double.parseDouble(newNumber);
            binding.result.setText(String.valueOf(number2));
        }
    }
}
