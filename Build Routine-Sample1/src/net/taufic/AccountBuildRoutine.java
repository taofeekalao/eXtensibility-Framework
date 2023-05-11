package net.taufic;

import java.util.List;

import com.temenos.t24.api.complex.eb.enquiryhook.EnquiryContext;
import com.temenos.t24.api.complex.eb.enquiryhook.FilterCriteria;
import com.temenos.t24.api.hook.system.Enquiry;

/**
 * TODO: Document me!
 *
 * @author Tawfiq
 *
 */
public class AccountBuildRoutine extends Enquiry {

    @Override
    public List<FilterCriteria> setFilterCriteria(List<FilterCriteria> filterCriteria, EnquiryContext enquiryContext) {
        FilterCriteria criteria = new FilterCriteria();
        String fieldName = filterCriteria.get(0).getFieldname();
        String value = filterCriteria.get(0).getValue();
        
        criteria.setFieldname("WORKING.BALANCE");
        criteria.setOperand("RG");
        if (fieldName.equals("CATEGORY")) {
            if (Integer.parseInt(value) > 7000) {
                criteria.setValue("50000 100000");
            } else {
                switch (value) {
                case "1001":
                    criteria.setValue("0 10000");
                    break;
                    
                case "6001":
                    criteria.setValue("10000 50000");
                    break;

                default:
                    break;
                }
            }
        }
        filterCriteria.add(criteria);
        return filterCriteria;
    }

    
}
