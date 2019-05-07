package sg.edu.rp.c346.billplease;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //declare the field variable
    TextView tvAmt;
    TextView tvPax;
    TextView tvServices;
    TextView tvTotal;
    TextView tvChoices;
    TextView tvPerPax;
    TextView tvDiscount;
    TextView tvError;
    TextView tvDist;

    Button btnReset;
    Button btnCalc;

    CheckBox cbSC;
    CheckBox cbGST;

    EditText etAmt;
    EditText etPax;
    EditText etDiscount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect the variables to the respective UI elements
        //linking UI elements
        tvAmt = findViewById(R.id.tvAmt);
        etAmt = findViewById(R.id.etAmt);

        tvDiscount = findViewById(R.id.tvDiscount);
        etDiscount = findViewById(R.id.etDiscount);

        tvPax = findViewById(R.id.tvPax);
        etPax = findViewById(R.id.etPax);

        tvServices = findViewById(R.id.tvServices);
        cbSC = findViewById(R.id.cbSC);
        cbGST = findViewById(R.id.cbGST);

        tvTotal = findViewById(R.id.tvTotal);
        tvPerPax = findViewById(R.id.tvPerPax);
        tvError = findViewById(R.id.tvError);
        tvDist = findViewById(R.id.tvDist);

        tvChoices = findViewById(R.id.tvChoices);
        btnReset = findViewById(R.id.btnReset);
        btnCalc = findViewById(R.id.btnCalc);


        //when user clicks calculate button
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                tvDist.setText("");
                tvError.setText("");
                //Double.parseDouble
                //Integer.parseInt
                if (etAmt.getText().toString().length() != 0 && etPax.getText().toString().length() != 0) {
                    double netAmount = Double.parseDouble(etAmt.getText().toString());
                    int numpax = Integer.parseInt(etPax.getText().toString());


                    //check total amount
                    double ttlBill = netAmount;

                    if (cbGST.isChecked() && cbSC.isChecked()) {
                        ttlBill = ttlBill * 1.1;
                        ttlBill = ttlBill * 1.07;
                    } else {
                        //for gst
                        if (cbGST.isChecked()) {
                            ttlBill = ttlBill * 1.07;
                        }
                        //for service charges
                        if (cbSC.isChecked()) {
                            ttlBill = ttlBill * 1.1;
                        }
                    }

                    //per person
                    double billPerPax = ttlBill / numpax;

                    //discount
                    if (etDiscount.getText().toString().length() != 0) {
                        double c = 0.0;
                        double disc = Double.parseDouble(etDiscount.getText().toString());
                        c = (ttlBill * disc) / 100;


                        tvDist.setText("After Discount : $" + String.format("%.2f", c));

                    }


                    //display text
                    //2 decimal place
                    //Log.d("App", "Total amount: " + String.format("%.2f", ttlBill));
                    //Log.d("App", "Total bill and the amount each pay: " + String.format("%.2f", billPerPax));
                    tvTotal.setText("Total amount: $" + String.format("%.2f ", ttlBill));
                    tvPerPax.setText("Total bill and the amount each pay: $" + String.format("%.2f", billPerPax));


                } else {
                    //if user dont enter anything
                    tvError.setText("Please fill in the fields.");
                }

            }
        });

        //when user clicks reset button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clear text
                etAmt.setText("");
                etPax.setText("");
                tvPerPax.setText("");
                tvTotal.setText("");
                tvDist.setText("");
                etDiscount.setText("");
                cbSC.setChecked(false);
                cbGST.setChecked(false);
            }
        });
    }
}
