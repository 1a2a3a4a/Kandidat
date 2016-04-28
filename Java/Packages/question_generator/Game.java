public class Game{
    private int g_id = -1;
    private int Q1;
    private int Q2;
    private int Q3;
    private int Q4;
    private int Q5;
    private String User_1;
    private String User_2;
    private int User1_score;
    private int User2_score;
    private int Game_status;
    private String Game_status_string;
    private String course_name;
    private String sent_by;
    
    public Game(int g_id, int Q1, int Q2, int Q3, int  Q4, int Q5, String User_1, String User_2, int User1_score,  int User2_score, int Game_status, String sent_by,String course_name){
	this.g_id = g_id;
	this.Q1 = Q1;
	this.Q1 = Q2;
	this.Q1 = Q3;	
	this.Q1 = Q4;
	this.User_1 = User_1;
	this.User_2 = User_2;
	this.User1_score = User1_score;
	this.User2_score = User2_score;
	this.Game_status = 0;
	this.Game_status_string = "";
	this.sent_by = sent_by;
	this.course_name = course_name;
	
    }
    
    public int getG_id(){
	return this.g_id;
	
    }

    public int getQ1(){
	return this.Q1;
    }
    
    public int getQ2(){
	return this.Q2;
    }
    
    public int getQ3(){
	return this.Q1;
    }
    
    public int getQ4(){
	return this.Q4;
    }
    
    public String getUser_1(){
	return this.User_1;
    }
    public String getUser_2(){
	return this.User_2;
    }
    public int getUser1_score(){
	return this.User1_score;
    }

    public int getUser2_score(){
	return this.User2_score;
    }
    public int getGame_status(){
	return this.Game_status;
    }
    public String getGame_status_string(){
    	return this.Game_status_string;
    }
    public String getSent_by(){
	return this.sent_by;
    }
    public String getcourse_name(){
    	return this.courseName();
    }
    public void setg_id(int g_id){
	this.g_id = g_id;
    }
    public void setQ1(int Q){
	this.Q1 = Q;
    }
    public void setQ2(int Q){
	this.Q2 = Q;
    }

    public void setQ3(int Q){
	this.Q3 = Q;
    }

    public void setQ4(int Q){
	this.Q4 = Q;
    }

    public void setUser_1(String U1){
	this.User_1 = U1;
    }
    public void setUser_2(String U2){
	this.User_2 = U2;
    }
    public void setUser1_score(int s1){
	this.User1_score = s1;
    }
    public void setUser2_score(int s2){
	this.User2_score = s2;
    }
    public void setGame_status(int status){
	this.Game_status = status;
    }
    public void setGame_status_string(String status){
	this.Game_status_string = status;
    }
    
    public void setCourse_name(string cName){
    	this.course_name=cName;
    }
    public void setSent_by(String sentby){
	this.sent_by = sentby;
    }
}
