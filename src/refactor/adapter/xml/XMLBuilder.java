package refactor.adapter.xml;

public class XMLBuilder extends AbstractBuilder {

	public XMLBuilder(String rootName) {
		init(rootName);
	}

	protected void init(String rootName) {
		root = new TagNode(rootName);
		commonInit();
	}

	public String toString() {
		return root.toString();
	}
}
