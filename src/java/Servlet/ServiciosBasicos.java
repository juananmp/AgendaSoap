/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

    File file = new File("Agenda.xml");

    File xsdFile = new File("ValidarAgenda.xsd");
    public static Agenda nuestraAgenda = new Agenda();

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "ValidarXSD")
    public String validarXSD() {
String txt = "Juanan";
        try {

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(file));
            txt = (file + " is valid against the " + xsdFile + " Schema");
        } catch (SAXException ex) {
            txt = (file + " is not valid against the " + xsdFile + " Schema");
            Logger.getLogger(ServiciosBasicos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServiciosBasicos.class.getName()).log(Level.SEVERE, null, ex);
        }
        // return (file + " is valid against the " + xsdFile + " Schema");
        return txt;
    }

    @WebMethod(operationName = "Validar_DTD")
    public String validarDTD() {
String txt = "Juanan";
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            //In this case we are creating a different ErrorHandler, if not we do like the well-formed Checker+
            CustomErrorHandler customErrorHandler = new CustomErrorHandler();
            dBuilder.setErrorHandler(customErrorHandler);

            Document doc = dBuilder.parse(file);

            if (customErrorHandler.isValid()) {
                txt = (file + " was valid!");
            } else {
                txt = (file + " was not valid!");
            }
        } catch (ParserConfigurationException ex) {
            System.out.println(file + " error while parsing!");
            Logger.getLogger(ServiciosBasicos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            System.out.println(file + " was not well-formed!");
            Logger.getLogger(ServiciosBasicos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(file + " was not accesible!");
            Logger.getLogger(ServiciosBasicos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return txt;
    }
    @WebMethod(operationName = "MostrarAgenda")
    public Agenda mostrarAgenda() throws JAXBException {  
        UnMarshall u = new UnMarshall();
        u.UnMarshallAgenda(file);
        return nuestraAgenda;
    
    }

  
}
