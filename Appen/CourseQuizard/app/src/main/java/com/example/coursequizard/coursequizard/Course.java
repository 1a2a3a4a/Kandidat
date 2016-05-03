package com.example.coursequizard.coursequizard;

import android.util.Log;

public class Course{
    private int C_ID;
    private int Uni_ID;
    private String Uni_name = "";
    private String Course_code = "";
    private String name  = "";
    
    
    
    Course(int C_ID, int Uni_ID, String Uni_name, String Course_code, String name){
	this.C_ID = C_ID;
	this.Uni_ID = Uni_ID;
	this.Uni_name = Uni_name;
	this.Course_code = Course_code;
	this.name = name;
	
    }
    //WRITE THE GETTER /SETTTERS HERE
    public int getC_ID(){
	return this.C_ID;
    }
    public int  getUni_ID(){
	return this.Uni_ID;
    }
    public String getUni_name(){
	return this.Uni_name;
    }
    public String getCourse_code(){
	return this.Course_code;
    }
    public String getName(){
	return this.name;
    }
    public void setC_ID(int CID){
	this.C_ID = CID;
    }
    public void setUni_ID(int UID){
	this.Uni_ID = UID;
    }
    public void setUni_name(String Uname){
	this.Uni_name = Uname;
    }
    public void setCourse_code(String CC){
	this.Course_code = CC;
    }
    public void setname(String n){
	this.name = n;
    }

    public String ctoString(){
	String str = "";
	str += "CID: " + this.C_ID + "\n";
	str += "UNI ID: " + this.Uni_ID + "\n";
	str += "UNI NAME: " + this.Uni_name + "\n";
	str += "Course code: " + this.Course_code + "\n";
	str += "name: " + this.name + "\n";
	return str;
    }
    public String toStringName(){
        return this.name;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Course) {
            Course course = (Course) obj;
            Log.i("equals method", "equalsmethod");
            Log.i(String.valueOf(this.C_ID), String.valueOf(course.C_ID));
            return this.C_ID == course.C_ID;
        }
        return false;
    }
}


