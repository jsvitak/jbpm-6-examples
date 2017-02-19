package org.jbpm.demo.travel;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.specialtripsagency.Applicant;
import org.specialtripsagency.BookingObject;
import org.specialtripsagency.Flight;
import org.specialtripsagency.Hotel;

public class CalculateTotalPriceBasedOnDiscountProvided {

    public static void main(String[] args) {

        fireRuleDirect();
    }

    static void fireRuleDirect() {
        KieHelper kieHelper = new KieHelper();
        KieBase kieBase = kieHelper
                .addResource(ResourceFactory.newClassPathResource("travel-agency/CalculateTotalPriceBasedOnDiscountProvided.drl"))
                .build();
        KieSession ksession = kieBase.newKieSession();
        
        Applicant applicant = new Applicant("Kylin Soong", "ksoong@redhat.com", 1, "N/A");
        Flight flight = new Flight("CSAir",1780, 1800, "Beijing", "ShangHai", "2017-01-24", "2017-01-29", 80);
        Hotel hotel = new Hotel("W", 1002, 800, 4, "5 Stars", "Shanghai", true, 80);
        BookingObject bookingObject = new BookingObject();
        bookingObject.setApplicant(applicant);
        bookingObject.setAvailableFlights(flight);
        bookingObject.setAvailableHotels(hotel);
        
        ksession.insert(applicant);
        ksession.insert(hotel);
        ksession.insert(flight);
        ksession.insert(bookingObject);
        ksession.fireAllRules();
        
        ksession.dispose();
    }

}
