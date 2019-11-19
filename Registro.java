import java.util.ArrayList;

public class Registro {
	
	ArrayList<String> cols = new ArrayList<String>();

	public Registro(ArrayList<String> cols){
		this.cols = cols;
	}

	public String toString(){

		String registroString = "";

		for(String c : this.cols){
			registroString += c+"\t\t";
		}

		return registroString;
	}

}