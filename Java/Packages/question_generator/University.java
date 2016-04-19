public class University{
    private int U_ID;
    private String name = "";
    private String abbr = "";

    public University(int U_ID, String name, String abbr){
	this.U_ID = U_ID;
	this.name = name;
	this. abbr = abbr;

    }
    public int getU_ID(){
	return this.U_ID;
	
    }
    public String getName(){
	return this.name;
    }
    public String getAbbr(){
	return this.abbr;
    }

    public void setU_ID(int UID){
	this.U_ID = UID;
    }
    public void setName(String name){
	this.name = name;
    }
    public void setAbbr(String abbr){
	this.abbr = abbr;
    }
    public String utoString(){
	String str = "";
	str += "UD: " + this.U_ID + "\n";
	str += "NAME: " + this.name + "\n";
	str += "ABBR: " + this.abbr + "\n";
	return str;
    }
}
