package refactor.adapter.xml;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TagNode implements ITagNode {
	private String name = "";
	private StringBuffer attributes;
	private List children;
	private String value = "";

	public TagNode(String name) {
		this.name = name;
		attributes = new StringBuffer("");
	}

	@Override
	public void appendChild(ITagNode childNode) {
		children().add(childNode);
	}

	@Override
	public void setAttribute(String attribute, String value) {
		attributes.append(" ");
		attributes.append(attribute);
		attributes.append("='");
		attributes.append(value);
		attributes.append("'");
	}

	@Override
	public void addValue(String value) {
		this.value = value;
	}

	@Override
	public List children() {
		if (children == null)
			children = new ArrayList();
		return children;
	}

	@Override
	public Element getElement() {
		return null;
	}

	@Override
	public ITagNode newNode(String child) {
		return new TagNode(child);
	}

	public String toString() {
		return toStringHelper(new StringBuffer(""));
	}

	protected String toStringHelper(StringBuffer result) {
		writeOpenTagTo(result);
		writeValueTo(result);
		writeChildrenTo(result);
		writeEndTagTo(result);
		return result.toString();
	}

	protected void writeChildrenTo(StringBuffer result) {
		Iterator it = children().iterator();
		while (it.hasNext()) {
			TagNode node = (TagNode) it.next();
			node.toStringHelper(result);
		}
	}

	private void writeValueTo(StringBuffer result) {
		if (!value.equals(""))
			result.append(value);
	}

	protected void writeEndTagTo(StringBuffer result) {
		result.append("</");
		result.append(name);
		result.append(">");
	}

	protected void writeOpenTagTo(StringBuffer result) {
		result.append("<");
		result.append(name);
		result.append(attributes.toString());
		result.append(">");
	}
}
