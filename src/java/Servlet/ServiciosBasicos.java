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
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 *
 * @author janto
 */
@WebService(serviceName = "ServiciosBasicos")
public class ServiciosBasicos {

    File file = new File("Agenda.xml");

    File xsdFile = new File("ValidarAgenda.xsd");

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "Validar_DTD")
    public String validarXSD(@WebParam(name = "name") String txt) {

        try {

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(file));
            
        } catch (SAXException ex) {
            return (file + " is not valid against the " + xsdFile + " Schema");
           // Logger.getLogger(ServiciosBasicos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServiciosBasicos.class.getName()).log(Level.SEVERE, null, ex);
        }
            return (file + " is valid against the " + xsdFile + " Schema");
            
    }

    @WebMethod(operationName = "suma")
    public int suma(@WebParam(name = "name") int a, int b) {
        return a + b;
    }

}
