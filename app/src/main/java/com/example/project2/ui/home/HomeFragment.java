package com.example.project2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project2.LogIn2;
import com.example.project2.R;
import com.example.project2.adapter;
import com.example.project2.databinding.FragmentHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ListView lv;
    ArrayList<Car> carArrayList ;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://projectroo-1b18c-default-rtdb.firebaseio.com/");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_home,container,false);
        lv=root.findViewById(R.id.homeFrag);
        carArrayList=new ArrayList<>();
        databaseReference.child("cars").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot d:snapshot.getChildren()){
                    Car c=d.getValue(Car.class);
                    //Toast.makeText(HomeFragment.this.getActivity(),c.getType(),Toast.LENGTH_SHORT).show();
                    String type=c.getType();
                    String model=c.getModel();
                    String year=c.getYear();
                    Car c1=new Car(type,model,year);
                    carArrayList.add(c1);
                }
                adapter adp=new adapter(HomeFragment.this.getActivity(),carArrayList);
                lv.setAdapter(adp);
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