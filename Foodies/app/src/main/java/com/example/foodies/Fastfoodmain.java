package com.example.foodies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Fastfoodmain extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String[] F_name;
    String[] D_name;

    int[] img_res ={R.drawable.pizahut,R.drawable.kfc,R.drawable.burgerking};
    ArrayList<dataprovider> arrayList = new ArrayList<dataprovider>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fastfoodmain);

        recyclerView = (RecyclerView)findViewById(R.id.recylerview);

        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListner(Fastfoodmain.this, recyclerView, new ClickListner() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(view.getContext() ,MainActivity.class);
                startActivity(intent);

                Toast.makeText(Fastfoodmain.this,String.valueOf(position),Toast.LENGTH_LONG).show();
                switch (position){
                    case 0:
                        intent =  new Intent(Fastfoodmain.this, Pizzahutweb.class);
                        break;

                    case 1:
                        intent =  new Intent(Fastfoodmain.this, kfcweb.class);
                        break;
                    case 2:
                        intent =  new Intent(Fastfoodmain.this, burgerkingweb.class);
                        break;

                    default:
                        intent =  new Intent(Fastfoodmain.this, MainActivity.class);
                        break;
                }
                Fastfoodmain.this.startActivity(intent);

            }
        }));

        F_name = getResources().getStringArray(R.array.namenew);
        D_name = getResources().getStringArray(R.array.name);

        int i = 0;
        for (String name : F_name)
        {
            dataprovider dataprovider = new dataprovider(img_res[i],name,D_name[i]);
            arrayList.add(dataprovider);
            i++;
        }


        adapter = new Recycleradapter(arrayList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public class RecyclerTouchListner implements RecyclerView.OnItemTouchListener{

        private GestureDetector gestureDetector;
        private ClickListner clickListner;

        public RecyclerTouchListner(Context context, RecyclerView recyclerView, ClickListner clickListner){
            this.clickListner=clickListner;

            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if (child!=null && clickListner!=null && gestureDetector.onTouchEvent(e)){
                clickListner.onClick(child,rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public static interface ClickListner{
        public void onClick(View view, int position);

    }

    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(Fastfoodmain.this, MainActivity.class);
        startActivity(intent);
    }
}