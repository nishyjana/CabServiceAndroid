package com.example.registration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ADminlogbookView extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerViewFireStore;
    private FirestoreRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_dminlogbook_view);
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerViewFireStore = findViewById(R.id.logbookView);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        Toast.makeText(ADminlogbookView.this, "sucess"+email , Toast.LENGTH_SHORT).show();

        CollectionReference ref = firebaseFirestore.collection("Logbook");


        FirestoreRecyclerOptions<Logbook> bookinglistoptions = new FirestoreRecyclerOptions.Builder<Logbook>()
                .setQuery(ref,Logbook.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Logbook, ADminlogbookView.bookingViewholder>(bookinglistoptions) {


            @NonNull
            @Override
            public ADminlogbookView.bookingViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.logbookview,parent,false);

                return new ADminlogbookView.bookingViewholder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final ADminlogbookView.bookingViewholder holder, int position, @NonNull final Logbook model) {

                holder.booking_name.setText("Customer: "+model.getCustomerEmail());
                holder.booking_location.setText("Location: "+model.getLocation());
                holder.booking_number.setText("Amount: "+model.getAmount());
                holder.DriverID.setText("Driver: "+model.getDriverID());










                //Toast.makeText(AdminBookingList.this, "This is" +model.getName(), Toast.LENGTH_SHORT).show();

            }
        };
        recyclerViewFireStore.setHasFixedSize(true);
        recyclerViewFireStore.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFireStore.setAdapter(adapter);

    }


    private class bookingViewholder extends RecyclerView.ViewHolder {
        private TextView booking_name, booking_location, booking_number, DriverID;





        public bookingViewholder(@NonNull View itemView) {
            super(itemView);
            booking_name = itemView.findViewById(R.id.bookingname);
            booking_location= itemView.findViewById(R.id.bookinglocation);
            booking_number= itemView.findViewById(R.id.bookingnumber);
            DriverID = itemView.findViewById(R.id.driverid);

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