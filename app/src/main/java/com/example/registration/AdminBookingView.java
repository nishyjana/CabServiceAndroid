package com.example.registration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AdminBookingView extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerViewFireStore;
    private FirestoreRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booking_view);
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerViewFireStore = findViewById(R.id.RecycleDriverDet);

        Query query = firebaseFirestore.collection("Bookings");
        FirestoreRecyclerOptions<cabBook> bookinglistoptions = new FirestoreRecyclerOptions.Builder<cabBook>()
                .setQuery(query,cabBook.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<cabBook, AdminBookingView.bookingViewholder>(bookinglistoptions) {
            @NonNull
            @Override
            public AdminBookingView.bookingViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlebookingviiew,parent,false);

                return new AdminBookingView.bookingViewholder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull AdminBookingView.bookingViewholder holder, int position, @NonNull final cabBook model) {
                holder.booking_name.setText(model.getName());
                holder.booking_location.setText(model.getPickup());
                holder.driverone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(AdminBookingView.this, "This is" +model.getName(), Toast.LENGTH_SHORT).show();

                    }
                });
                //Toast.makeText(AdminBookingList.this, "This is" +model.getName(), Toast.LENGTH_SHORT).show();

            }
        };
        recyclerViewFireStore.setHasFixedSize(true);
        recyclerViewFireStore.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFireStore.setAdapter(adapter);

    }

    private class bookingViewholder extends RecyclerView.ViewHolder {
        private TextView booking_name, booking_location;
        private Button driverone;

        public bookingViewholder(@NonNull View itemView) {
            super(itemView);
            booking_name = itemView.findViewById(R.id.bookingname);
            booking_location= itemView.findViewById(R.id.bookinglocation);
            driverone =itemView.findViewById(R.id.driveronedets);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}