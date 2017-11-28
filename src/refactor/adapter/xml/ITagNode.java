package refactor.adapter.xml;

import org.w3c.dom.Element;

import java.util.List;

public interface ITagNode {
    void appendChild(ITagNode childNode);

    void setAttribute(String attribute, String value);

    void addValue(String value);

    List children();

    Element getElement();

    ITagNode newNode(String child);
}
