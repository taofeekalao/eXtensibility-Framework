package net.taufic;

import java.util.ArrayList;
import java.util.List;

import com.temenos.t24.api.complex.eb.enquiryhook.EnquiryContext;
import com.temenos.t24.api.complex.eb.enquiryhook.FilterCriteria;
import com.temenos.t24.api.hook.system.Enquiry;
import com.temenos.t24.api.party.Customer;
import com.temenos.t24.api.records.account.AccountRecord;
import com.temenos.t24.api.system.DataAccess;

/**
 * TODO: Document me!
 *
 * @author Tawfiq
 *
 */
public class NoFileEnquiry extends Enquiry {

    @Override
    public List<String> setIds(List<FilterCriteria> filterCriteria, EnquiryContext enquiryContext) {
        Customer customer = new Customer(this);
        DataAccess dataAccess = new DataAccess();

        String customerId = filterCriteria.get(0).getValue();

        List<String> returnId = new ArrayList<String>();
        Double consolidatedBalance = 0.0;
        AccountRecord accountRecord;
        customer.setCustomerId(customerId);

        try {
            List<String> accountNumbers = customer.getAccountNumbers();
            Double workingBalance = 0.0;
            for (String accountNumber : accountNumbers) {
                accountRecord = new AccountRecord(dataAccess.getRecord("ACCOUNT", accountNumber));
                if (!(accountRecord.getWorkingBalance().getValue().isEmpty()
                        || accountRecord.getWorkingBalance().getValue().equals(""))) {
                    workingBalance = Double.parseDouble(accountRecord.getWorkingBalance().getValue());
                    consolidatedBalance += workingBalance;
                } else {
                    continue;
                }
            }
        } catch (Exception exception) {

        }
        returnId.add(customerId + "*" + consolidatedBalance.toString());
        return returnId;
    }

}
