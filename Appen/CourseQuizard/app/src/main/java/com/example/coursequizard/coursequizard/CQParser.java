package com.example.coursequizard.coursequizard;

import java.util.LinkedList;

public class CQParser {
    private LinkedList<Question> list;
    private LinkedList<Course> clist;


    public  CQParser(){
	list = new LinkedList<Question>();
	clist = new LinkedList<Course>();

	
    }
    
    private void translateText(String text){
	String splits[] = text.split("%N%");
	for(int i=0; i < splits.length; i++){
	    String sentence_splits[] = splits[i].split("%Q%");
	    //System.out.print(splits[i] + " ");
	    /*for(int y = 0; y < sentence_splits.length; y++){
	      System.out.print(sentence_splits[y] + ", ");			
	      }*/
	    Question q = new Question(sentence_splits[0], sentence_splits[1], sentence_splits[2], sentence_splits[3], sentence_splits[4]);
	    list.add(q);

	    System.out.println("");
	}
    }
    private void courseParser(String text){
	String splits[] = text.split("%N%");
	for(int i=0; i < splits.length; i++){
	    String sentence_splits[] = splits[i].split("%C%");
	    Course c  = new Course(Integer.parseInt(sentence_splits[0]),
				   Integer.parseInt(sentence_splits[1]),
				   sentence_splits[2],
				   sentence_splits[3],
				   sentence_splits[4]);
	    clist.add(c);
	    System.out.println("");
	}
    }

    public LinkedList<Question> generate(String text){
	this.translateText(text);
	return this.list;
    }

    public LinkedList<Question> getList(){
	return this.list;
    }
    
    public LinkedList<Course> getGeneratedCList(String courseText){
	courseParser(courseText);
	return this.clist;
	
    }
    
    public LinkedList<Course> getcList(){
	return this.clist;
    }

    public String toString(){
	String str = "";
	for(int i=0; i < this.list.size(); i++){
	    str += this.list.get(i).toString() + "\n";
	}
	return str;
    }
    public String ctoString(){
	String str = "";
	for(int i=0; i < this.clist.size(); i++){
	    str += this.clist.get(i).ctoString() + "\n";
	}
	return str;
    }
}
