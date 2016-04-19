import java.util.LinkedList;

public class ParserTest {
	public static void main(String[] args){
		CQParser par = new CQParser();
		par.generate("THIS IS SENTENCE ONE%Q%QUESTION1%Q%ALTERNATIVE1%Q%ALT2%Q%ALT3%N%THIS IS SENTENCE TWO%Q%QUESTION2%Q%ALTERNATIVE1%Q%ALT2%Q%ALT3%N%");
		Question q = new Question("Q1", "A1", "Alt1", "Alt2", "Alt3");
		LinkedList q_list = par.getList();
		System.out.println(par.toString());
	       		LinkedList c_list = par.getGeneratedCList("1%C%1%C%Uppsala Univeresitet%C%1DT%C%HejHej%N%");
	       		System.out.println(par.ctoString());
	}
}
