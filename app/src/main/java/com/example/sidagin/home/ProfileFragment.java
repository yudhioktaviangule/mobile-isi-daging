package com.example.sidagin.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sidagin.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    Context AppContext;
    private View iflater;
    private Bundle bundle;
    TextView email,ikm,alamat,telepon;
    public ProfileFragment(Context context){
        AppContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        iflater =  inflater.inflate(R.layout.fr_profile,container,false);
        bundle = getArguments();
        init();
        return iflater;
    }

    private void init() {
        email = iflater.findViewById(R.id.emailProfile);
        ikm = iflater.findViewById(R.id.ikmProfile);
        alamat=iflater.findViewById(R.id.alamatProfile);
        telepon = iflater.findViewById(R.id.teleponProfile);

        String Remail,RIkm,Ralamat,Rtlp;
        Remail = bundle.getString("user.email");
        RIkm = bundle.getString("ikm.name");
        Ralamat = bundle.getString("ikm.alamat");
        Rtlp = bundle.getString("ikm.telepon");
        email.setText(Remail);
        ikm.setText(RIkm);
        alamat.setText(Ralamat);
        telepon.setText(Rtlp);
    }
}
