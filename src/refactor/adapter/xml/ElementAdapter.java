package refactor.adapter.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.util.List;

public class ElementAdapter implements ITagNode {

    private Document doc;
    private Element element;

    public ElementAdapter(Element element, Document doc) {
        this.element = element;
        this.doc = doc;
    }

    @Override
    public void appendChild(ITagNode childNode) {
        element.appendChild(childNode.getElement());
    }

    @Override
    public void setAttribute(String attribute, String value) {
        element.setAttribute(attribute, value);
    }

    @Override
    public void addValue(String value) {
        element.appendChild(doc.createTextNode(value));
    }

    @Override
    public List children() {
        return null;
    }

    @Override
    public Element getElement() {
        return element;
    }

    @Override
    public ITagNode newNode(String child) {
        return new ElementAdapter(doc.createElement(child), doc);
    }

}
