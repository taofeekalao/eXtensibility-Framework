package net.taufic;

import com.temenos.t24.api.complex.st.customerapi.PersonalInfo;
import com.temenos.t24.api.party.Customer;
import com.temenos.tafj.api.client.impl.T24Context;


/**
 * TODO: Document me!
 *
 * @author Tawfiq
 *
 */
public class GetT24Context {
    
    public static void main(String[] args) {
        System.setProperty("tafj.home", "C:/R20/TAFJ");
        T24Context t24Context = new T24Context("tafj");
        t24Context.setPassword("123456");
        t24Context.setUser("INPUTT");
        
        Customer customer = new Customer(t24Context);
        customer.setCustomerId("100352");
        
        PersonalInfo personalInfo = customer.getPersonalInfo();
        System.out.println("Nationality: " + personalInfo.getNationality());
        System.out.println("Residence: " + personalInfo.getResidence());
        System.out.println("Date of Birth: " + personalInfo.getDateOfBirth());;
        
    }
}
