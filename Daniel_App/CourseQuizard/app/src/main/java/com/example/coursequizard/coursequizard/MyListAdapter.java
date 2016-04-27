package com.example.coursequizard.coursequizard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coursequizard.coursequizard.R;

import java.util.ArrayList;
import java.util.LinkedList;

class MyListAdapter extends ArrayAdapter<String> {
    private int layout;
    private int buttonmode = 1;
    private LinkedList<Course> myCourses;


    private void swapFavorite(int position){
        Course course = myCourses.get(position);
        BackgroundWithServer b = new BackgroundWithServer(getContext());
        String type = "swap favorites";
        //TODO IMPLEMENT USER
        String user_name = "Daniel";
        String c_id = "" + course.getC_ID();
        b.execute(type, c_id, user_name);
    }

    public MyListAdapter(Context context, int resource, ArrayList<String> objects, LinkedList myCourses) {
        super(context, resource, objects);
        layout = resource;
        this.myCourses = myCourses;
    }


    private void swapButton(ImageButton button){

        //button.setImageResource(R.drawable.star_off);
        if(buttonmode == 1) {
            button.setImageResource(R.drawable.star_off);
            buttonmode = 0;
        }
        if(buttonmode == 0){
            button.setImageResource(R.drawable.star_on);
            buttonmode = 1;
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        buttonmode = 1;
        ViewHolder mainView = null;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.button = (ImageButton) convertView.findViewById(R.id.list_item_button);
            //ImageButton ib = ad.findViewById(R.id.list_item_button);
            viewHolder.text = (TextView) convertView.findViewById(R.id.list_item_text);
            convertView.setTag(viewHolder);
        }
        mainView = (ViewHolder) convertView.getTag();
        final ImageButton iB = mainView.button;
        iB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                      /*  if(buttonmode == 1) {
                            iB.setImageResource(R.drawable.star_off);
                            buttonmode = 0;
                        }
                        if(buttonmode == 0){
                            iB.setImageResource(R.drawable.star_on);
                            buttonmode = 1;
                        }*/
                swapFavorite(position);
                swapButton(iB);
                //iB.setImageResource(R.drawable.star_off);
                //Toast.makeText(getContext(), "Button was clicked for list item" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mainView.text.setText(getItem(position));

        return convertView;
    }
}