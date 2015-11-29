package br.com.rafaelsoto.jxmind;

import java.util.ArrayList;
import java.util.List;

public class XmindNode {

	private String title;
	private String comment;
	private List<XmindNode> childNodes = new ArrayList<XmindNode>();
	private XmindNode parentNode;
	
	
	public void addChild(String title, String comment)
	{
		XmindNode node = new XmindNode();
		node.setTitle(title);
		node.setComment(comment);
		this.childNodes.add(node);
	}
	
	public void addChild(XmindNode child)
	{
		this.childNodes.add(child);
		
	}
	
	public boolean hasChild()
	{
		if(this.childNodes != null && this.childNodes.size() > 0)
			return true;
		else
			return false;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<XmindNode> getChildNodes() {
		return childNodes;
	}
	public void setChildNodes(List<XmindNode> childNodes) {
		this.childNodes = childNodes;
	}
	public XmindNode getParentNode() {
		return parentNode;
	}
	public void setParentNode(XmindNode parentNode) {
		this.parentNode = parentNode;
	}
	
	
}
