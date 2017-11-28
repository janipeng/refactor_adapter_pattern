package refactor.adapter.xml;

import java.util.Stack;

public abstract class AbstractBuilder implements OutputBuilder {
	static final protected String CANNOT_ADD_ABOVE_ROOT = "Cannot appendChild node above the root node.";
	static final protected String CANNOT_ADD_BESIDE_ROOT = "Cannot appendChild node beside the root node.";
	protected Stack history = new Stack();

	protected ITagNode root;
	protected ITagNode parent;
	protected ITagNode current;

	public void addAbove(String uncle) {
		if (current == root)
			throw new RuntimeException(CANNOT_ADD_ABOVE_ROOT);
		history.pop();
		boolean atRootNode = (history.size() == 1);
		if (atRootNode)
			throw new RuntimeException(CANNOT_ADD_ABOVE_ROOT);
		history.pop();
		current = (ITagNode) history.peek();
		addBelow(uncle);
	}

	public void addGrandfather(String uncle) {
		if (current == root)
			throw new RuntimeException(CANNOT_ADD_ABOVE_ROOT);
		history.pop();
		boolean atRootNode = (history.size() == 1);
		if (atRootNode)
			throw new RuntimeException(CANNOT_ADD_ABOVE_ROOT);
		history.pop();
		history.pop();
		current = (ITagNode) history.peek();
		addBelow(uncle);
	}

	public void addAttribute(String name, String value) {
		current.setAttribute(name, value);
	}

	protected abstract void init(String rootName);

	public void startNewBuild(String rootName) {
		init(rootName);
	}


	public void addValue(String value) {
		current.addValue(value);
	}

	public void addBelow(String child) {
		ITagNode childNode = current.newNode(child);
		current.appendChild(childNode);
		parent = current;
		current = childNode;
		history.push(current);
	}

	public void addBeside(String sibling) {
		if (current == root)
			throw new RuntimeException(CANNOT_ADD_BESIDE_ROOT);
		ITagNode siblingNode = parent.newNode(sibling);
		parent.appendChild(siblingNode);
		current = siblingNode;
		history.pop();
		history.push(current);
	}

	protected void commonInit() {
		current = root;
		parent = root;
		history = new Stack();
		history.push(current);
	}
}
