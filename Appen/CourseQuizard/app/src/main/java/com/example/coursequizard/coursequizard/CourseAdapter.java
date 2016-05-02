package com.example.coursequizard.coursequizard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Simon on 2016-05-01.
 */
public class CourseAdapter extends ArrayAdapter<Course> {
    //private ArrayList<Game> objects;
    private Context context1;
    private ArrayList<Course> c = new ArrayList<Course>();
    private int p;

    public CourseAdapter(Context context, int textViewResourceId, ArrayList<Course> objects) {
        super(context, textViewResourceId, objects);
        //this.objects = objects;
        this.c = objects;
    }

    public CourseAdapter(Context context, int resource, int textViewResourceId, ArrayList<Course> items) {
        super(context, resource, textViewResourceId, items);
        this.context1 = context;
        this.c =items;
    }

    public String swapButton(ImageButton button,String tag){
        if (tag.equals("on")) {
            button.setImageResource(R.drawable.star_off);
            return "off";
        }
        else{
            button.setImageResource(R.drawable.star_on);
            return "on";

        }
    }
    private void swapFavorite(Course course){
        BackgroundWithServer b = new BackgroundWithServer(getContext());
        String type = "swap favorites";
        //TODO IMPLEMENT USER
        String user_name = "Daniel";
        String c_id = "" + course.getC_ID();
        b.execute(type, c_id);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        p =position;
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.list_item_course, null);
            //LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // v = inflater.inflate(R.layout.fragment_gameitem, null);

        }
        Course i = getItem(position);
      final ImageButton courseStar = (ImageButton) v.findViewById(R.id.list_item_course_button);
       // courseStar.setTag(position);
        courseStar.setTag(R.id.course_star_position,position);
        courseStar.setTag(R.id.course_star_image,"on");
       courseStar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int pos = (Integer) view.getTag(R.id.course_star_position);
                Course courseChosen = c.get(pos);
                Log.i("pushed on course", courseChosen.getName());
                swapFavorite(courseChosen);
               String editTag= swapButton(courseStar,(String) view.getTag(R.id.course_star_image));
                view.setTag(R.id.course_star_image,editTag);
            }

        });


        if (i != null) {



            TextView courseName = (TextView) v.findViewById(R.id.list_item_course_name);
            TextView courseCode = (TextView) v.findViewById(R.id.list_item_course_code);
            TextView universityName = (TextView) v.findViewById(R.id.list_item_course_university);


            if (courseName != null) {
                courseName.setText(i.getName());
            }
            if (courseCode != null) {
             courseCode.setText(i.getCourse_code());
            }
            if (universityName != null) {
                universityName.setText(i.getUni_name());
            }

        }
        return v;
    }
}