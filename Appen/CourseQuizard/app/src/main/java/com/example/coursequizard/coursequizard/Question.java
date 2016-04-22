package com.example.coursequizard.coursequizard;


public class Question {
	private int q_id = -1;
	private String question = "";
	private String answer = "";
	private String alt1 = "";
	private String alt2 = "";
	private String alt3 = "";
	private int c_id = -1;
	private int rating = 0;
	//private LinkedList tags = new LinkedList();

	public Question(String question, String answer, String alt1, String alt2, String alt3){
		this.question = question;
		this.answer = answer;
		this.alt1 = alt1;
		this.alt2 = alt2;
		this.alt3 = alt3;
	}
	public Question(int q_id, String question, String answer, String alt1, String alt2, String alt3, int c_id, int rating){
		this.question = question;
		this.answer = answer;
		this.alt1 = alt1;
		this.alt2 = alt2;
		this.alt3 = alt3;
	}

	public int getQ_id() {
		return q_id;
	}
	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating, int increment) {
		this.rating = rating + increment;
	}
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAlt1() {
		return alt1;
	}

	public void setAlt1(String alt1) {
		this.alt1 = alt1;
	}

	public String getAlt2() {
		return alt2;
	}

	public void setAlt2(String alt2) {
		this.alt2 = alt2;
	}

	public String getAlt3() {
		return alt3;
	}

	public void setAlt3(String alt3) {
		this.alt3 = alt3;
	}

	public String toString(){
		String str = "";
		str += "Question: " + this.question + "\n";
		str += "Correct Answer: " + this.answer + "\n";
		str += "Alternative 1: " + this.alt1 + "\n";
		str += "Alternative 1: " + this.alt2 + "\n";
		str += "Alternative 1: " + this.alt3 + "\n";
		return str;
	}
}
