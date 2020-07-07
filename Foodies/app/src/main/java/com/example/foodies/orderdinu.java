package com.example.foodies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import lk.payhere.androidsdk.PHConfigs;
import lk.payhere.androidsdk.PHConstants;
import lk.payhere.androidsdk.PHMainActivity;
import lk.payhere.androidsdk.model.InitRequest;
import lk.payhere.androidsdk.model.Item;

public class orderdinu extends AppCompatActivity {


    String[] fooditems1={"select from here","Chicken Kottu(L) 350.00 lkr","Chicken Kottu(s) 250.00 lkr",
            "Chicken Noodles(L) 300.00 lkr","Chicken pasta 400.00 lkr","Special fried rice(R) 500.00 lkr"};


    Spinner spin1,spin2,spin3;
    TextView fitem1,fitem2,fitem3,price1,price2,price3,totaltext;
    ArrayAdapter<String> adapter;
    private DatabaseReference databaseReference;
    private Button gettotal,proceed,cashon;
    private EditText qqty1,qqty2,qqty3,ttpno,eemail,nname,iinstrustions,aaddress;
    private FirebaseAuth auth;
    private static final String TAG = "";
    private final static int PAYHERE_REQUEST = 11010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdinu);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();



        spin1=(Spinner)findViewById(R.id.spinner1);
        spin2=(Spinner)findViewById(R.id.spinner2);
        spin3=(Spinner)findViewById(R.id.spinner3);


        fitem1=findViewById(R.id.item1);
        fitem2=findViewById(R.id.item2);
        fitem3=findViewById(R.id.item3);
        price1=findViewById(R.id.price1);
        price2=findViewById(R.id.price2);
        price3=findViewById(R.id.price3);

        totaltext=findViewById(R.id.totaltext);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,fooditems1);
        spin1.setAdapter(adapter);
        spin2.setAdapter(adapter);
        spin3.setAdapter(adapter);



        gettotal=findViewById(R.id.gettotal);
        proceed=findViewById(R.id.onlinepay);
        cashon=findViewById(R.id.cashondelivery);



        qqty1=findViewById(R.id.qty1);
        qqty2=findViewById(R.id.qty2);
        qqty3=findViewById(R.id.qty3);


        ttpno=findViewById(R.id.mobilenum);
        nname=findViewById(R.id.name);
        aaddress=findViewById(R.id.address);
        eemail=findViewById(R.id.email);
        iinstrustions=findViewById(R.id.instructions);






        cashon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
                Toast.makeText(orderdinu.this,"Virtual Receipt has been sent to your email!!",Toast.LENGTH_SHORT).show();


            }

        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBookingDetails();
                sendMessage();

                String item1 =fitem1.getText().toString().trim();
                String qty1 =qqty1.getText().toString().trim();
                String item2 =fitem2.getText().toString().trim();
                String qty2 =qqty2.getText().toString().trim();
                String item3 =fitem3.getText().toString().trim();
                String qty3 =qqty3.getText().toString().trim();
                String name =nname.getText().toString().trim();
                String address = aaddress.getText().toString().trim();
                String  total = totaltext.getText().toString().trim();
                String  emailadd = eemail.getText().toString().trim();
                String tpnum = ttpno.getText().toString().trim();
                String  instructions = iinstrustions.getText().toString().trim();
                int totalfinal = Integer.parseInt(total);

                Toast.makeText(orderdinu.this,"Virtual Receipt has been sent to your email!!",Toast.LENGTH_SHORT).show();
                InitRequest req = new InitRequest();
                req.setMerchantId("1212396");
                req.setMerchantSecret("kavisac");// Your Merchant ID
                req.setAmount(totalfinal); // Amount which the customer should pay
                req.setCurrency("LKR"); // Currency
                req.setOrderId(item1); // Unique ID for your payment transaction
                req.setItemsDescription("ticket details   :"+"no of first class tickets :  "+item1+" "+"no of second class tickets :  "+item2+item3);
                req.setCustom1("");
                req.getCustomer().setFirstName(name);
                req.getCustomer().setLastName(name);
                req.getCustomer().setPhone(tpnum);
                req.getCustomer().setEmail(emailadd);
                req.getCustomer().getAddress().setAddress("address");
                req.getCustomer().getAddress().setCity("address");
                req.getCustomer().getAddress().setCountry("address");
                req.getCustomer().getDeliveryAddress().setAddress("address");
                req.getCustomer().getDeliveryAddress().setCity("address");
                req.getCustomer().getDeliveryAddress().setCountry("address");
                req.getItems().add(new Item(null, "", 1));

                Intent intent = new Intent(orderdinu.this, PHMainActivity.class);
                intent.putExtra(PHConstants.INTENT_EXTRA_DATA, req);
                PHConfigs.setBaseUrl(PHConfigs.SANDBOX_URL);
                startActivityForResult(intent, PAYHERE_REQUEST);
            }




        });





        //prices

        final String LCFR="350";
        final String SCFR="250";
        final String LEFR="300";
        final String SEFR="400";
        final String SFR="500";


        //ITEMS

        final String item1="Chicken Kottu (L)";
        final String item2="Chicken Kottu (s)";
        final String item3="Chicken Noodles (L)";
        final String item4="Pasta (s)";
        final String item5="Special Fried Rice (R)";

        gettotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item1price=price1.getText().toString();
                String item2price=price2.getText().toString();
                String item3price=price3.getText().toString();
                String qty1 =qqty1.getText().toString();
                String qty2 =qqty2.getText().toString();
                String qty3 =qqty3.getText().toString();

                if (item1price.isEmpty() || item2price.isEmpty()|| item3price.isEmpty()||qty1.isEmpty()||qty2.isEmpty()||qty3.isEmpty()){

                }
                else {
                    int intprice1=Integer.parseInt(item1price);
                    int intprice2=Integer.parseInt(item2price);
                    int intprice3=Integer.parseInt(item3price);
                    int intqty1=Integer.parseInt(qty1);
                    int intqty2=Integer.parseInt(qty2);
                    int intqty3=Integer.parseInt(qty3);
                    int totalamount = ((intprice1 * intqty1)+(intprice2*intqty2)+(intprice3*intqty3));
                    String totalstring=String.valueOf(totalamount);
                    totaltext.setText(totalstring);


                }
            }
        });







        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){

                    case 0:
                        break;


                    case 1:
                        fitem1.setText(item1);
                        price1.setText(LCFR);

                        break;
                    case 2:
                        fitem1.setText(item2);
                        price1.setText(SCFR);
                        break;
                    case 3:
                        fitem1.setText(item3);
                        price1.setText(LEFR);
                        break;
                    case 4:
                        fitem1.setText(item4);
                        price1.setText(SEFR);
                        break;
                    case 5:
                        fitem1.setText(item5);
                        price1.setText(SFR);
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){


                    case 0:
                        break;

                    case 1:
                        fitem2.setText(item1);
                        price2.setText(LCFR);

                        break;
                    case 2:
                        fitem2.setText(item2);
                        price2.setText(SCFR);
                        break;
                    case 3:
                        fitem2.setText(item3);
                        price2.setText(LEFR);
                        break;
                    case 4:
                        fitem2.setText(item4);
                        price2.setText(SEFR);
                        break;
                    case 5:
                        fitem2.setText(item5);
                        price2.setText(SFR);
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){

                    case 0:
                        break;

                    case 1:
                        fitem3.setText(item1);
                        price3.setText(LCFR);

                        break;
                    case 2:
                        fitem3.setText(item2);
                        price3.setText(SCFR);
                        break;
                    case 3:
                        fitem3.setText(item3);
                        price3.setText(LEFR);
                        break;
                    case 4:
                        fitem3.setText(item4);
                        price3.setText(SEFR);
                        break;
                    case 5:
                        fitem3.setText(item5);
                        price3.setText(SFR);
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private void saveBookingDetails(){

        String item1 =fitem1.getText().toString().trim();
        String qty1 =qqty1.getText().toString().trim();
        String item2 =fitem2.getText().toString().trim();
        String qty2 =qqty2.getText().toString().trim();
        String item3 =fitem3.getText().toString().trim();
        String qty3 =qqty3.getText().toString().trim();
        String name =nname.getText().toString().trim();
        String address = aaddress.getText().toString().trim();
        String  total = totaltext.getText().toString().trim();
        String  emailadd = eemail.getText().toString().trim();
        String tpnum = ttpno.getText().toString().trim();
        String  instructions = iinstrustions.getText().toString().trim();




        UserInformation userInformation =new UserInformation(name,address,emailadd,tpnum,instructions,item1,qty1,item2,qty2,item3,qty3,total);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this,"information saved!",Toast.LENGTH_SHORT).show();
    }

    private void sendMessage() {
        final String  total = totaltext.getText().toString().trim();
        final String item1 =fitem1.getText().toString().trim();
        final String item2 =fitem2.getText().toString().trim();
        final String item3 =fitem3.getText().toString().trim();

        final String qty1 =qqty1.getText().toString().trim();
        final String qty2 =qqty2.getText().toString().trim();
        final String qty3 =qqty3.getText().toString().trim();
        final String emailadd= eemail.getText().toString().trim();

        final String name =nname.getText().toString().trim();


        final ProgressDialog dialog = new ProgressDialog(orderdinu.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("recurvedclient@gmail.com", "Recurvedc");
                    sender.sendMail("Foodies",
                            "Thank you for using Foodies  " +
                                    " this is your virtual invoice from Dinu foods" +name+
                                    "you have order of  "+qty1+" "+item1+" "+qty2+" "+item2+" "+qty3+" "+item3+
                                    "and user have paid " +
                                    total+" successfully!!",
                            "recurvedclient@gmail.com",
                            emailadd);
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();

        final ProgressDialog dialog2 = new ProgressDialog(orderdinu.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender2 = new GMailSender("recurvedclient@gmail.com", "Recurvedc");
                    sender2.sendMail("Foodies",
                            "Thank you for using Foodies  " +
                                    " this is your order from  "+name +
                                    "you have orderd  "+qty1+" "+item1+" "+qty2+" "+item2+" "+qty3+" "+item3+
                                    "and you have paid " +
                                    total+" successfully!!",
                            "recurvedclient@gmail.com",
                            "Dinufoods@gmail.com");
                    dialog2.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender2.start();
    }

}
