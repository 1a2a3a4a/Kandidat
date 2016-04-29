package com.example.coursequizard.coursequizard;
import com.example.coursequizard.coursequizard.Course;
import com.example.coursequizard.coursequizard.Question;
import com.example.coursequizard.coursequizard.University;


import java.util.ArrayList;
import java.util.LinkedList;


public class CQParser {
	private LinkedList<Question> qlist;
	private LinkedList<Course> clist;
	private LinkedList<University> ulist;
	private ArrayList<Game> glist;
	public  CQParser(){
		qlist = new LinkedList<Question>();
		clist = new LinkedList<Course>();
		ulist = new LinkedList<University>();
		glist = new ArrayList<Game>();
	}



	//to be used with a coded string without Q_ID etc in it i.e when generation questions
	public LinkedList<Question> generateQuestions(String text){
		try {
			this.translateText(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.qlist;
	}

	private void translateText(String text) throws Exception{
		//split sentences, marked by %N%
		String splits[] = text.split("%N%");
		//for each sentence
		for(int i=0; i < splits.length; i++){
			//split sentence into parts marked by %Q%
			String sentence_splits[] = splits[i].split("%Q%");
			for(int y = 0; y < sentence_splits.length; y++){
				System.out.println(sentence_splits[y]);
			}
			if(sentence_splits.length != 5) throw new Exception("FORMATTING ERRROR ON QUESTION STRING");

			//create a new question with the parts
			Question q = new Question(sentence_splits[0], sentence_splits[1], sentence_splits[2], sentence_splits[3], sentence_splits[4]);
			//add new question to the linkedlist
			qlist.add(q);
		}
	}

	//Use when you have a coded string with Q_ID etc in it i.e when fetching questions from database
	public LinkedList<Question> toQList(String text){
		this.questionParser(text);
		return this.qlist;
	}

	private void questionParser(String text){
		String splits[] = text.split("%N%");
		for(int i=0; i < splits.length; i++){
			String sentence_splits[] = splits[i].split("%Q%");
			Question q = new Question(Integer.parseInt(sentence_splits[0]),
					sentence_splits[1],
					sentence_splits[2],
					sentence_splits[3],
					sentence_splits[4],
					sentence_splits[5],
					Integer.parseInt(sentence_splits[6]),
					Integer.parseInt(sentence_splits[7]));
			qlist.add(q);
		}
	}


	//Use when having a coded string of courses
	public LinkedList<Course> toCList(String courseText){
		courseParser(courseText);
		return this.clist;

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
		}
	}


	//Use when having a coded University string
	public LinkedList<University> toUList(String UniText){
		universityParser(UniText);
		return this.ulist;
	}

	private void universityParser(String text){
		String splits[] = text.split("%N%");
		for(int i=0; i < splits.length; i++){
			String sentence_splits[] = splits[i].split("%U%");
			University u  = new University(Integer.parseInt(sentence_splits[0]),
					sentence_splits[1],
					sentence_splits[2]);
			ulist.add(u);
		}
	}

	public ArrayList<Game> toGList(String text){
		gameParser(text);
		return this.glist;
	}

	private void gameParser(String text){
		String splits[] = text.split("%N%");
		for(int i = 0; i < splits.length; i++){
			String sentence_splits[] = splits[i].split("%G%");
			Game g = new Game(Integer.parseInt(sentence_splits[0]),
					Integer.parseInt(sentence_splits[1]),
					Integer.parseInt(sentence_splits[2]),
					Integer.parseInt(sentence_splits[3]),
					Integer.parseInt(sentence_splits[4]),
					Integer.parseInt(sentence_splits[5]),
					sentence_splits[6],
					sentence_splits[7],
					Integer.parseInt(sentence_splits[8]),
					Integer.parseInt(sentence_splits[9]),
					Integer.parseInt(sentence_splits[10]),
					sentence_splits[11],
					sentence_splits[12]);
			glist.add(g);
		}

	}
	////////////////////////////////////////////////
	/// Getters n Setters
	////////////////////////////////////////////////

	public ArrayList<Game> getGList(){
		return this.glist;
	}

	public void setGList(ArrayList<Game> glist){
		this.glist = glist;
	}

	public LinkedList<Question> getQlist() {
		return qlist;
	}



	public void setQlist(LinkedList<Question> qlist) {
		this.qlist = qlist;
	}



	public LinkedList<Course> getClist() {
		return clist;
	}



	public void setClist(LinkedList<Course> clist) {
		this.clist = clist;
	}



	public LinkedList<University> getUlist() {
		return ulist;
	}



	public void setUlist(LinkedList<University> ulist) {
		this.ulist = ulist;
	}


	//////////////////////////////////
	/// toString methods
	///////////////////////////////////
	public String toString(){
		String str = "";
		for(int i=0; i < this.qlist.size(); i++){
			str += this.qlist.get(i).toString() + "\n";
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
	public String utoString(){
		String str = "";
		for(int i=0; i < this.ulist.size(); i++){
			str += this.ulist.get(i).utoString() + "\n";
		}
		return str;
	}
}