package br.com.rafaelsoto.jxmind;

import java.lang.reflect.Field;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Executable class to open, parse and convert a Xmind File in Json format
 * @author rafaelsoto
 *
 */
public class JSONConverter {

	public static void main(String[] args) throws JxmindException {
		
		ParseXmind parseXmind = new ParseXmind("src/main/resources/content.xml");
		XmindNode node = parseXmind.parseXmindFileDom4J();
		
		GsonBuilder builder = new GsonBuilder();
		
		builder.setFieldNamingStrategy(new FieldNamingStrategy() {
			
			public String translateName(Field field) {
				
				if(field.getName().equalsIgnoreCase("title"))
				{
					return "name";
				}
				else if(field.getName().equalsIgnoreCase("childNodes"))
				{
					return "children";
				}
				else
					return field.getName();
			}
		});
		
        Gson gson = builder.setPrettyPrinting().create();
        System.out.println(gson.toJson(node));
		
	}
	
	
}
