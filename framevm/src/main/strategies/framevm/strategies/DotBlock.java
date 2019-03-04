package framevm.strategies;

import java.util.ArrayList;
import java.util.List;

import framevm.strategies.util.Block;

public class DotBlock implements DotSerializable {

	private String dotString;
	private List<String> links;

	public DotBlock(Block block) {
		String name = "block_" + block.getName();
		
		this.links = new ArrayList<>();
		String instrsString = "";
		for (int i = 0; i < block.size(); i++) {
			instrsString += "|<" + i + ">" + block.getInstr(i).toString().replace("\"", "").replace(",Link(", ",").replace("[Link(", "[").replace("Slot(", "").replace("),", ",").replace(")]", "]"); 
		}
		this.dotString = "\t" + name + "[label=\"{" + block.getName() + instrsString + "}\"];";
	}

	@Override
	public List<String> links() {
		return links;
	}

	@Override
	public String toDotString() {
		return dotString;
	}

}
