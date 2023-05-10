package net.taufiq;

import com.temenos.api.T24Record;
import com.temenos.api.TField;
import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.fundstransfer.FundsTransferRecord;
import com.temenos.t24.api.records.teller.TellerRecord;

/**
 * TODO: Document me!
 *
 * @author Tawfiq
 *
 */
public class SameCurrencyCheck extends RecordLifecycle {
    
    TField drCurrency;
    TField crCurrency;
    T24Record transactionRecord;

    @Override
    public TValidationResponse validateRecord(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {
        
        if (application.equals("FUNDS.TRANSFER")) {
            transactionRecord = new FundsTransferRecord(currentRecord);
            drCurrency = ((FundsTransferRecord) transactionRecord).getDebitCurrency();
            crCurrency = ((FundsTransferRecord) transactionRecord).getCreditCurrency();
        }
        
        if (application.equals("TELLER")) {
            transactionRecord = new TellerRecord(currentRecord);
            drCurrency = ((TellerRecord) transactionRecord).getCurrency1();
            crCurrency = ((TellerRecord) transactionRecord).getCurrency2();
        }
        
        if (!drCurrency.getValue().equals(crCurrency.getValue()) ) {
            drCurrency.setError("Debit currency is not the same as the credit currency");
        }
        
        return transactionRecord.getValidationResponse();
    }

}
