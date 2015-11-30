package br.com.rafaelsoto.jxmind;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class ParseXmind {

	private String filePath;	
	private File file;
	
	public ParseXmind(String xmindFilePath)
	{
		this.filePath = xmindFilePath;
		this.file = new File(filePath);
	}
	
	
	public XmindNode parseXmindFileDom4J() throws JxmindException
	{
		SAXReader reader = new SAXReader();
		XmindNode root = new XmindNode();
		try {
			Document document = reader.read(file);
			root.setTitle("root");			
			treeWalk(document, root);

		} catch (Exception e) {
			throw new JxmindException("Exception during parsing XmindFileDom4j", e);
		}
		
		return root;
	}
	
	public void treeWalk(Document document, XmindNode parentNode) {
        treeWalk(document.getRootElement(),parentNode);
    }

	/**
	 * Recursive method to walk on the Xmind XML Tree Document
	 * @param element - the element to walk
	 * @param parentNode - the parent element reference
	 */
    public void treeWalk(Element element, XmindNode parentNode) {
    	
    	XmindNode currentNode;  	
    	
        for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
            Node node = element.node(i);
            
            //First node on xmind file is a sheet we need goes to child element
            if(node.getName() != null && node.getName().equalsIgnoreCase("sheet"))
            {
            	this.treeWalk((Element) node, parentNode);
            }
            
            //We are interesting on a topic Element only
            else if ( node instanceof Element && node.getName().equalsIgnoreCase("topic")) {

            	currentNode = new XmindNode();
            	currentNode.setTitle(this.searchElementByText((Element) node,"title").getStringValue().trim());
            	parentNode.addChild(currentNode);
            	
            	Node children = this.searchElementByText((Element) node,"children");
            	if (children != null){
            		Node topics = this.searchElementByText((Element) children,"topics");
            		
            		if(topics != null){
            			 treeWalk( (Element) topics, currentNode);
            		}
            	}
            	
               
            }
           
        }
    }

    /**
     * Find each child elements to find a element by @String
     * @param element
     * @param elementName
     * @return
     */
	private Node searchElementByText(Element element, String elementName)
	{
		// System.out.println(element.getName());
		
		  for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
			  Node node = element.node(i);
	            if (node instanceof Element && node.getName().equalsIgnoreCase(elementName)) {
	            	//System.out.println("["+i+"] - Node.Name: " + node.getName() + " - Node.Value: " + node.getStringValue());
	            	return node;
	            }
		  }
		  
		  return null;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
	
}
