package br.com.rafaelsoto.jxmind.test;

import org.junit.Test;

import br.com.rafaelsoto.jxmind.JxmindException;
import br.com.rafaelsoto.jxmind.ParseXmind;
import br.com.rafaelsoto.jxmind.XmindNode;

public class ParseXmindTest {

	@Test
	public void test() throws JxmindException {
		
		//ParseXmind parseXmind = new ParseXmind("src/main/resources/content.xml");
		ParseXmind parseXmind = new ParseXmind("src/main/resources/content.xml");
		XmindNode node = parseXmind.parseXmindFileDom4J();
		
		printXmindFile(node);
	}
	
	private void printXmindFile(XmindNode root)
	{
		System.out.println("Titulo: "+root.getTitle());
		System.out.println("Nós Filhos: "+root.getChildNodes().size());
		
		if(root.hasChild())
			for (XmindNode node : root.getChildNodes()) {
				printXmindFile(node);
			}
			
		
	}
	
	
}
