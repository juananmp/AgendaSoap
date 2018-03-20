/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;

/**
 *
 * @author janto
 */
public class Marshall {
       
        CustomErrorHandler customError;
       
        public void MarshallAgenda(Agenda agenda){
          try {
            
            JAXBContext context = JAXBContext.newInstance(Agenda.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File XMLfile = new File("Agenda.xml");
            marshaller.marshal(ServiciosBasicos.nuestraAgenda, XMLfile);

            marshaller.marshal(ServiciosBasicos.nuestraAgenda, System.out);
        } catch (JAXBException ex) {
            Logger.getLogger(Marshall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
