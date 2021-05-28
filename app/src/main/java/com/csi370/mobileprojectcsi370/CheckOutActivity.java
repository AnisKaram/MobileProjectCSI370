package com.csi370.mobileprojectcsi370;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CheckOutActivity extends AppCompatActivity {

    boolean isGuest = true;

    TextView txtVTotalPrice, txtVForGuest;
    EditText edtAddress, edtPhoneNumber;
    Button btnConfirmPurchase,btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        if(AppUserDb.loggedInUserId != -1){
            isGuest = false;
        }

        edtAddress = (EditText) findViewById(R.id.edtAddressRegisterCheckOut);
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumberCheckOut);
        txtVTotalPrice = (TextView) findViewById(R.id.txtVTotalCheckout);
        txtVForGuest = (TextView) findViewById(R.id.txtVForGuest);
        btnConfirmPurchase = (Button) findViewById(R.id.btnConfirmPurchase);
        btnBack = (Button) findViewById(R.id.btnBackCheckout);

        Bundle bundle = getIntent().getExtras();
        txtVTotalPrice.setText("$" + bundle.getDouble("grandTotal", 0.0));

        if(!isGuest) {
            edtAddress.setVisibility(View.GONE);
            edtPhoneNumber.setVisibility(View.GONE);
            txtVForGuest.setVisibility(View.GONE);
        }

        btnConfirmPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isGuest) {
                    if(TextUtils.isEmpty(edtAddress.getText().toString()) && TextUtils.isEmpty(edtPhoneNumber.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Fill in the field(s)!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Order is processing!", Toast.LENGTH_SHORT).show();

                        txtVTotalPrice.setText("Thank you for your purchase!");

                        //Empty Cart
                        for(Purchase purchase: Cart.cart) {
                            Cart.cart.remove(purchase);
                        }
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Order is processing!", Toast.LENGTH_SHORT).show();

                    txtVTotalPrice.setText("Thank you for your purchase!");

                    //Empty Cart
                    for(Purchase purchase: Cart.cart) {
                        Cart.cart.remove(purchase);
                    }
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myCartActivity = new Intent(getApplicationContext(), MyCartActivity.class);
                startActivity(myCartActivity);
                finish();
            }
        });



    }
}