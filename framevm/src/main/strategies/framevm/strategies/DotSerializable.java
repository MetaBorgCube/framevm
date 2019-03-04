package framevm.strategies;

import java.util.List;

public interface DotSerializable {
	public List<String> links();
	public String toDotString();
}
