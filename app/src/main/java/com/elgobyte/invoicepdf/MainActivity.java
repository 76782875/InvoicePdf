package com.elgobyte.invoicepdf;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.elgobyte.invoicepdfcreater.InvoicePdf;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnClick;

    InvoicePdf obj;

    private ArrayList<String> description = new ArrayList<>();
    private ArrayList<Integer> amount = new ArrayList<>();
    private ArrayList<String> quantity = new ArrayList<>();
    private ArrayList<Integer> price = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClick = findViewById(R.id.btnClick);

        amount.add(1);
        amount.add(2);
        amount.add(3);
        amount.add(4);
        amount.add(5);
        amount.add(6);
        amount.add(7);
        amount.add(8);
        amount.add(9);
        amount.add(10);

        price.add(1);
        price.add(2);
        price.add(3);
        price.add(4);
        price.add(5);
        price.add(6);
        price.add(7);
        price.add(8);
        price.add(9);
        price.add(10);

        description.add("1");
        description.add("2");
        description.add("3");
        description.add("4");
        description.add("5");
        description.add("6");
        description.add("7");
        description.add("8");
        description.add("9");
        description.add("10");


        quantity.add("1");
        quantity.add("2");
        quantity.add("3");
        quantity.add("4");
        quantity.add("5");
        quantity.add("6");
        quantity.add("7");
        quantity.add("8");
        quantity.add("9");
        quantity.add("10");


        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Create new object of the Invoice PDF
                obj = new InvoicePdf(MainActivity.this);

                // send data to setData Method
                obj.setData("PdfName", "12/12/2020",
                        "#123", "Sender", "senderEmail"
                        , "Fsd", "Receiver",
                        "receiverEmail", "FSD",
                        description, quantity, price, amount, "this is the Short note to Save PDF");

                //This Method to Create pdf
                obj.createPdf();

            }
        });


    }
}
