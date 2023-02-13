package com.example.project2.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project2.R;
import com.example.project2.databinding.FragmentSlideshowBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SlideshowFragment extends Fragment {
    int index=0;
    Button btn,btn1;
    String user;
    private FragmentSlideshowBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
         ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =new ViewModelProvider(this).get(SlideshowViewModel.class);
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_slideshow,container,false);
        int[] arrpic=new int[]{R.drawable.x64,R.drawable.x65,R.drawable.x67};
        ImageView mypic=(ImageView)root.findViewById(R.id.img);
        mypic.setImageResource(arrpic[index]);
        btn=root.findViewById(R.id.next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mypic.setImageResource(arrpic[index]);
                index++;
                if(index>2)
                    index=0;
            }
        });
        btn1=root.findViewById(R.id.pay);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://projectroo-1b18c-default-rtdb.firebaseio.com/");

                databaseReference.child("CurrentUser").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        user=snapshot.getValue().toString();
                        databaseReference.child("Cart").child(user).setValue(null);
                        Toast.makeText(SlideshowFragment.this.getActivity(),"CheckOut is Done",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

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