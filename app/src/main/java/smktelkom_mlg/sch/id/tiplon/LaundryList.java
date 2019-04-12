package smktelkom_mlg.sch.id.tiplon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import smktelkom_mlg.sch.id.tiplon.Interface.ItemClickListener;
import smktelkom_mlg.sch.id.tiplon.Model.Laundry;
import smktelkom_mlg.sch.id.tiplon.ViewHolder.LaundryViewHolder;

public class LaundryList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference laundryList;

    String laundryId = "";

    FirebaseRecyclerAdapter<Laundry, LaundryViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_list);


        // Firebase
        database = FirebaseDatabase.getInstance();
        laundryList = database.getReference("Laundries");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_laundry);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Get Intent
        if (getIntent() != null)
            laundryId = getIntent().getStringExtra("LaundryId");
        if (!laundryId.isEmpty() && laundryId != null) {
            loadListLaundry(laundryId);
        }

    }

    private void loadListLaundry(String laundryId) {
        adapter = new FirebaseRecyclerAdapter<Laundry, LaundryViewHolder>(Laundry.class,
                R.layout.laundry_item,
                LaundryViewHolder.class,
                laundryList.orderByChild("LaundryId").equalTo(laundryId)) {
            @Override
            protected void populateViewHolder(LaundryViewHolder viewHolder, Laundry model, int position) {
                viewHolder.laundry_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.laundry_image);

                final Laundry local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        // Start new Activity

                        Intent laundryDetail = new Intent(LaundryList.this,LaundryDetail.class);
                        laundryDetail.putExtra("LaundryId",adapter.getRef(position).getKey()); // Send LaundryId to new Activity
                        startActivity(laundryDetail);
                    }
                });
            }
        };

        // Set Adapter
        recyclerView.setAdapter(adapter);
    }
}
