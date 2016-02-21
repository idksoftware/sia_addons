using System;
using System.Collections.Generic;
using System.Text;
using System.Xml;
using System.Diagnostics;
 
namespace IMGProperties
{
    class Element
    {
        public String name;
        public String value;
        public List<Element> list;
        
    }

   

    class XMLPropertiesWriter
    {
        XmlTextWriter writer = null;
        List<NameValue> propertiesList = null;
        Element currentElement = null;
        Element parentElement = null;
        public XMLPropertiesWriter(string fileName)
        {
           
        }

        public void Process()
        {
       
            
            /*
           while (reader.Read())
           {
               switch (reader.NodeType)
               {
                   case XmlNodeType.Element: // The node is an element.
                       currentElement = new Element();
                       currentElement.name = reader.Name;
                       Debug.Write("<" + reader.Name);
                       Debug.WriteLine(">");
                       break;
                   case XmlNodeType.Text: //Display the text in each element.
                       currentElement.value = reader.Value;
                       Debug.WriteLine(reader.Value);
                       break;
                   case XmlNodeType.EndElement: //Display the end of the element.
                       propertiesList.Add(currentElement);
                       Debug.Write("</" + reader.Name);
                       Debug.WriteLine(">");
                       break;
                   
               }
           }
           */
	            
          
        }

        public List<NameValue> GetList()
        {
            return propertiesList;
        }
    }
}
