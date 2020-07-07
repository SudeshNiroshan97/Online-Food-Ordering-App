package com.example.foodies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class foodAmalya extends AppCompatActivity {

    ViewFlipper viewFlipper;
    Toolbar toolbar;
    Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_amalya);



        toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle("Welcome to Amalya Reach");

        order=findViewById(R.id.order);


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent set = new Intent(foodAmalya.this, orderamalya.class);
                foodAmalya.this.
                        startActivity(set);
            }
        });


        //account header settings
        AccountHeader headerResult = new AccountHeaderBuilder()
                .addProfiles(
                        new ProfileDrawerItem().withName("Foodies").withEmail("FoodiesNSBM@gmail.com").withIcon(getResources().getDrawable(R.mipmap.mainicon))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .withActivity(this)
                .withHeaderBackground(R.drawable.navigationback)
                .build();

        //drawer items

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(1).withName("Available Restaurants");
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(1).withName("kfc");
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withIdentifier(1).withName("kfc");
        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withIdentifier(1).withName("kfc");
        SecondaryDrawerItem item6 = new SecondaryDrawerItem().withIdentifier(1).withName("kfc");
        SecondaryDrawerItem item7 = new SecondaryDrawerItem().withIdentifier(1).withName("kfc");
        PrimaryDrawerItem item8 = new PrimaryDrawerItem().withIdentifier(1).withName("Fast food restaurants");
        SecondaryDrawerItem item9 = new SecondaryDrawerItem().withIdentifier(1).withName("Pizza hut");
        SecondaryDrawerItem item10 = new SecondaryDrawerItem().withIdentifier(1).withName("kfc");
        SecondaryDrawerItem item11 = new SecondaryDrawerItem().withIdentifier(1).withName("Burger king");


        PrimaryDrawerItem item12 = new PrimaryDrawerItem().withIdentifier(1).withName("Settings");




        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        item3, item4,item5,item6,item7,
                        item8,
                        item9,item10,item11,
                        new DividerDrawerItem(),
                        item12



                )

                //drawer item add onclick listener
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position){

                            case 1:
                                Intent drawer1 = new Intent(foodAmalya.this,MainActivity.class);
                                foodAmalya.this.
                                        startActivity(drawer1);
                                break;


                            case 3:
                                Intent drawer2 = new Intent(foodAmalya.this,AvailableResturants.class);
                                foodAmalya.this.
                                        startActivity(drawer2);
                                break;
                            case 4:
                                Intent drawer3 = new Intent(foodAmalya.this,fooddhm.class);
                                foodAmalya.this.
                                        startActivity(drawer3);
                                break;
                            case 5:
                                Intent drawer4 = new Intent(foodAmalya.this,fooddinu.class);
                                foodAmalya.this.
                                        startActivity(drawer4);
                                break;
                            case 6:
                                Intent drawer5 = new Intent(foodAmalya.this,foodkalana.class);
                                foodAmalya.this.
                                        startActivity(drawer5);
                                break;
                            case 7:
                                Intent drawer6 = new Intent(foodAmalya.this,foodAmalya.class);
                                foodAmalya.this.
                                        startActivity(drawer6);
                                break;

                            case 8:
                                Intent drawer8 = new Intent(foodAmalya.this,Fastfoodmain.class);
                                foodAmalya.this.
                                        startActivity(drawer8);
                                break;
                            case 9:
                                Intent drawer9 = new Intent(foodAmalya.this,Pizzahutweb.class);
                                foodAmalya.this.
                                        startActivity(drawer9);
                                break;
                            case 10:
                                Intent drawer10 = new Intent(foodAmalya.this,kfcweb.class);
                                foodAmalya.this.
                                        startActivity(drawer10);
                                break;
                            case 11:
                                Intent drawer11 = new Intent(foodAmalya.this,burgerkingweb.class);
                                foodAmalya.this.
                                        startActivity(drawer11);
                                break;
                            case 12:
                                Intent drawer12 = new Intent(foodAmalya.this,Settings.class);
                                foodAmalya.this.
                                        startActivity(drawer12);
                                break;



                        }
                        return true;

                    }
                })

                .build();


        int images[]= {R.drawable.friedrice,R.drawable.rice1,R.drawable.rice3,
                R.drawable.rice2};

        viewFlipper =findViewById(R.id.viewflip);

        for (int image:images){
            flipimages(image);
        }
    }

    public void flipimages(int image){

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);


        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }
}
