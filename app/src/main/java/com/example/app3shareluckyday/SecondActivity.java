package com.example.app3shareluckyday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    TextView welcomeTxt, luckyNumberTXT;
    Button share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        welcomeTxt = findViewById(R.id.textView2);
        luckyNumberTXT = findViewById(R.id.lucky_number_txt);
        share_btn = findViewById(R.id.share_btn);

        // Receiving the data from Main Activity
        // get the Intent information
        Intent i = getIntent(); //this will retrieve the value name&userName from  i.putExtra("name", userName);
        // need to get this instant and store it inside a userName:
        //the key is "name" (to store the value
        String userName = i.getStringExtra("name");

        //display the luckyNumber that I get, stored in another int variable:
        int random_num=generateRandomNumber();

        if(random_num==1){
            luckyNumberTXT.setText("Monday");
        }
        else if(random_num==2){
            luckyNumberTXT.setText(("Tuesday"));
        }
        else if(random_num==3){
            luckyNumberTXT.setText(("Wednesday"));
        }
        else if(random_num==4){
            luckyNumberTXT.setText(("Thursday"));
        }
        else if(random_num==5){
            luckyNumberTXT.setText(("Friday"));
        }
        else if(random_num==6){
            luckyNumberTXT.setText(("Saturday"));
        }
        else{
            luckyNumberTXT.setText(("Sunday"));
        }

        //luckyNumberTXT.setText("" + random_num);

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(userName, random_num);
            }
        });
    }

    //Generate Random Numbers
    public int generateRandomNumber() {
        Random random = new Random();
        int upper_limit = 7;

        int randomNumberGenerated = random.nextInt(upper_limit);
        return randomNumberGenerated;
    }

    public void shareData(String username, int randomNum){
        //Implicit Intent
        Intent i = new Intent(Intent.ACTION_SEND); // send data to other applications
        //specifying the data type we are sharing
        i.setType("text/plain");

        //additional information

        //share the date among other apps with some extra subject and extra title
        //putExtra is the same used at Explicit Intent, but using now the title
        //to pre-populate the subject and text fields when sharing content like
        //emails or messages
        i.putExtra(Intent.EXTRA_SUBJECT,username + " got lucky today");
        i.putExtra(Intent.EXTRA_TEXT, "His Lucky Number is: " + randomNum);

        // createChooser method allow to create a dialog that displays a list of apps
        //that can handle a specific Intent: giving the user a choise of apps for sharing content
        startActivity(Intent.createChooser(i, "Choose a platform"));


    }

}