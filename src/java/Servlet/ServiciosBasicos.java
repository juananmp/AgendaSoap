/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author janto
 */

@WebService(serviceName = "ServiciosBasicos")
public class ServiciosBasicos {

     static Scanner scanner = new Scanner(System.in);
    File file = new File("Agenda.xml");

    File xsdFile = new File("ValidarAgenda.xsd");
    public static Agenda nuestraAgenda = new Agenda();

    /**
     * This is a sample web service operation
     */

    @WebMethod(operationName = "MostrarAgenda")
    public Agenda mostrarAgenda() throws JAXBException {  
        UnMarshall u = new UnMarshall();
        u.UnMarshallAgenda(file);
        return nuestraAgenda;
    
    }
    
    @WebMethod(operationName = "CrearContacto")
    public void crearContacto(Persona p) {
       nuestraAgenda.setPersona(p);
       Marshall m = new Marshall();
       m.MarshallAgenda(nuestraAgenda);
       
    }

    @WebMethod(operationName = "MostarPersona")
    public Persona mostrarPersona(String nombre) throws JAXBException {  
        UnMarshall u = new UnMarshall();
        u.UnMarshallAgenda(file);
        Persona p = null;
        for(int i=0;i<nuestraAgenda.persona.size();i++){
            if(nuestraAgenda.persona.get(i).getName().equals(nombre)){
                p = nuestraAgenda.getPersona().get(i);
                break;
            }
        }
        return p;
    
    }
    
  
}
