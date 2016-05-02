package com.example.coursequizard.coursequizard;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by daniel.hellgren on 2016-04-21.
 */
public class MyCoursesActivity extends AppCompatActivity {

    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<Course> myCourses = new ArrayList<Course>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        this.fetchCourses();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);
        ListView lv = (ListView) findViewById(R.id.listview);

        generateListContent();
        lv.setAdapter(new MyListAdapter(this, R.layout.list_item, data));
    }


    private void swapFavorite(int position){
        Course course = myCourses.get(position);
        BackgroundWithServer b = new BackgroundWithServer(this);
        String type = "swap favorites";
        //TODO IMPLEMENT USER
        String user_name = "Daniel";
        String c_id = "" + course.getC_ID();
        b.execute(type, c_id, user_name);
    }

    private void fetchCourses() {
        ArrayList<String> message = new ArrayList<String>();
        message = getIntent().getExtras().getStringArrayList("prevActivity");
        String data = message.get(1);

        Log.i("data0", message.get(0));
        Log.i("data1",message.get(1));


        CQParser parser = new CQParser();

        myCourses = parser.toCList(data);
    }

    private void generateListContent(){
        for(int i =0; i < myCourses.size(); i++){
            data.add(myCourses.get(i).toStringName());
        }
    }

    private class MyListAdapter extends ArrayAdapter<String> {
        private int layout;
        private int buttonmode = 1;



        public MyListAdapter(Context context, int resource, ArrayList<String> objects) {
            super(context, resource, objects);
            layout = resource;
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
    public class ViewHolder{
        ImageButton button;
        TextView text;
    }
}