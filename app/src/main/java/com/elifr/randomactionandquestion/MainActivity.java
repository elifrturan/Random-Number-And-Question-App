package com.elifr.randomactionandquestion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public ToggleButton toggleBtnSum, toggleBtnSub, toggleBtnMultiply, toggleBtnDiv;
    public TextView txtQuestion, txtResult;
    public EditText editGuess;
    public Button btnBring, btnMake;
    public ArrayList<String> operationTypes;
    public Random rndOperation = new Random();
    public Random rndNumber = new Random();
    public int operation, number, n1, n2, result;
    public String question, txtGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operationTypes = new ArrayList<>();

        //Toggle Buttons
        toggleBtnSum = (ToggleButton) findViewById(R.id.toggleSum);
        toggleBtnSub = (ToggleButton) findViewById(R.id.toggleSub);
        toggleBtnMultiply = (ToggleButton) findViewById(R.id.toggleMultiply);
        toggleBtnDiv = (ToggleButton) findViewById(R.id.toggleDivision);

        //Text Views
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        txtResult = (TextView) findViewById(R.id.txtResult);

        //Edit Text
        editGuess = (EditText) findViewById(R.id.editTextGuess);

        //Buttons
        btnBring = (Button) findViewById(R.id.btnBring);
        btnMake = (Button) findViewById(R.id.btnGuess);

        toggleBtnSum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    operationTypes.add("+");
                else
                    operationTypes.remove("+");
            }
        });

        toggleBtnSub.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    operationTypes.add("-");
                else
                    operationTypes.remove("-");
            }
        });

        toggleBtnMultiply.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    operationTypes.add("*");
                else
                    operationTypes.remove("*");
            }
        });

        toggleBtnDiv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    operationTypes.add("/");
                else
                    operationTypes.remove("/");
            }
        });

        btnBring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtQuestion.setText(DetermineOperationAndQuestion());
                txtResult.setText("");
                editGuess.setText("");
            }
        });

        btnMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessControl();
            }
        });
    }

    public void guessControl(){
        txtGuess = editGuess.getText().toString();

        if (TextUtils.isEmpty(txtGuess)){
            txtQuestion.setText("The entered value cannot be empty!");
        }else{
            if(txtGuess.matches(String.valueOf(result))){
                txtResult.setText("Congratulations! You answered the question correctly.");
            }else{
                txtResult.setText("You answered wrong!");
            }
        }

    }

    public String DetermineOperationAndQuestion(){

        question = "";
        if (operationTypes.size() > 0){
            operation = rndOperation.nextInt(operationTypes.size());

            n1 = BringRandomNumber();
            question += n1;
            question += " ";
            question += operationTypes.get(operation);
            question += " ";
            n2 = BringRandomNumber();
            question += n2;

            switch (operationTypes.get(operation)){
                case "+":
                    result = n1 + n2;
                    break;

                case "-":
                    result = n1 - n2;
                    break;

                case "*":
                    result = n1 * n2;
                    break;

                case  "/":
                    result = n1 / n2;
                    break;
            }
        }

        return question;
    }

    public int BringRandomNumber(){
        number = rndNumber.nextInt(10) + 1;
        return number;
    }

}