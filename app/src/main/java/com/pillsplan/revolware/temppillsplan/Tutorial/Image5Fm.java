package com.pillsplan.revolware.temppillsplan.Tutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.pillsplan.revolware.temppillsplan.R;

/**
 * Created by Lenovo on 25.9.2016.
 */

public class Image5Fm extends Fragment {

    RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    Button bt;

/*
    public void init(){
        bt = (Button)findViewById(R.id.bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Image5Fm.this, MainActivity.class);
                startActivity(toy);
            }
        });
    }
*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.image5fm, container, false);

        //ImageView iv = new ImageView(getActivity());
        //iv.setImageResource(R.drawable.img5);

        bt = (Button)rootView.findViewById(R.id.bt);

        return rootView;
    }

}
