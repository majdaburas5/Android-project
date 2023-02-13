package com.example.project2.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project2.R;
import com.example.project2.adapter1;
import com.example.project2.databinding.FragmentGalleryBinding;
import com.example.project2.ui.home.Car;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    ListView lv;
    String user;
    ArrayList<Car> carArrayList ;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://projectroo-1b18c-default-rtdb.firebaseio.com/");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_gallery,container,false);
        carArrayList=new ArrayList<>();
        lv=root.findViewById(R.id.cart);
        databaseReference.child("CurrentUser").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user=snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("Cart").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(user)){
                    databaseReference.child("Cart").child(user).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot d:snapshot.getChildren()){
                                Car c=d.getValue(Car.class);
                                String type=c.getType();
                                String mode=c.getModel();
                                String year=c.getYear();
                                Car c1=new Car(type,mode,year);
                                carArrayList.add(c1);
                            }
                            adapter1 ada=new adapter1(GalleryFragment.this.getActivity(),carArrayList);
                            lv.setAdapter(ada);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}