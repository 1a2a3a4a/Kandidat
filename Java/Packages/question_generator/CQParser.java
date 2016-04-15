import java.util.LinkedList;

public class CQParser {
	private LinkedList<Question> list = new LinkedList<Question>();


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

	public LinkedList<Question> generate(String text){
		this.translateText(text);
		return this.list;
	}

	public LinkedList<Question> getList(){
		return this.list;
	}


	public String toString(){
		String str = "";
		for(int i=0; i < this.list.size(); i++){
			str += this.list.get(i).toString() + "\n";
		}
		return str;
	}
}
