package sjoshi11_java_part2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Dictionary {

	public ArrayList<String> term;
	public ArrayList<Integer> df;
	public ArrayList<Integer> offset;
	protected Fileprocessor fp = null;

	public Dictionary() {
		term = new ArrayList<String>();
		df = new ArrayList<Integer>();
		offset = new ArrayList<Integer>();
	}

	public Dictionary(Fileprocessor fp) {
		term = new ArrayList<String>();
		df = new ArrayList<Integer>();
		offset = new ArrayList<Integer>();
		this.fp = fp;
	}

	// Iterator<String> it = fp.toks.iterator();

	public void createDictionary(String dict) {

		int i = 0, docFreq = 0, currOffset = 0, offset = 0;
		for (Entry<String, HashMap<Integer, Integer>> e : fp.tokens.entrySet()) {
			if (i == 0) {
				String text = "Token" + "," + "Doc Freq" + ","  + "Offset" + "\n";
				String y = "" + e.getKey() + "," + e.getValue().size() + "," + offset + "\n";
				fp.openOfile(dict);
				fp.writeFile(text.toString());
				fp.writeFile(y.toString());
				currOffset = 0;
				offset = 0;
				docFreq = e.getValue().size();
				i++;
				continue;
			} else {
				currOffset += docFreq + offset;
				String d = "" + e.getKey() + "," + e.getValue().size() + "," + currOffset + "\n";
				fp.writeFile(d);
				docFreq = e.getValue().size();
			}
		}
		fp.closeFile();
	}

	public void createPost(String post) {

		fp.openOfile(post);
		String x = "File" + "," + "Occurence" + "\n";
		fp.writeFile(x.toString());
		for (Entry<String, HashMap<Integer, Integer>> e : fp.tokens.entrySet()) {
			for (Map.Entry<Integer, Integer> ent : e.getValue().entrySet()) {
				String data = "" + ent.getKey() + "," + ent.getValue() + "\n";
				fp.writeFile(data.toString());
			}
		}
		fp.closeFile();
	}

}
