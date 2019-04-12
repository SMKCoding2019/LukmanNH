package smktelkom_mlg.sch.id.tiplon;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import smktelkom_mlg.sch.id.tiplon.Database.Database;
import smktelkom_mlg.sch.id.tiplon.Model.Laundry;
import smktelkom_mlg.sch.id.tiplon.Model.Order;

public class LaundryDetail extends AppCompatActivity {

    TextView laundry_name, laundry_price, laundry_description;
    ImageView laundry_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String laundryId ="";
    FirebaseDatabase database;
    DatabaseReference laundry;

    Laundry currentLaundry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_detail);


        //Firebase
        database = FirebaseDatabase.getInstance();
        laundry = database.getReference("Laundries");

        //Init view
        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btnCart = (FloatingActionButton) findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        laundryId,
                        currentLaundry.getName(),
                        numberButton.getNumber(),
                        currentLaundry.getPrice(),
                        currentLaundry.getDiscount()
                ));
                Toast.makeText(LaundryDetail.this, "Added To Cart", Toast.LENGTH_SHORT).show();
            }
        });

        laundry_description = (TextView)findViewById(R.id.laundry_description);
        laundry_name = (TextView)findViewById(R.id.laundry_name);
        laundry_price = (TextView)findViewById(R.id.laundry_price);
        laundry_image=(ImageView)findViewById(R.id.img_laundry);



        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        //Get Food Id from Intent
        if(getIntent() != null){
            laundryId = getIntent().getStringExtra("LaundryId");
        }
        if (!laundryId.isEmpty()){
            getDetailLaundry(laundryId);
        }
    }

    private void getDetailLaundry(final String laundryId) {
        laundry.child(laundryId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentLaundry = dataSnapshot.getValue(Laundry.class);

                //Set image
                Picasso.with(getBaseContext()).load(currentLaundry.getImage()).into(laundry_image);
                collapsingToolbarLayout.setTitle(currentLaundry.getName());
                laundry_price.setText(currentLaundry.getPrice());
                laundry_name.setText(currentLaundry.getName());
                laundry_description.setText(currentLaundry.getDescription());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }
}