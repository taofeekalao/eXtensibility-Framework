package com.temenos.t24;

import com.temenos.api.TField;
import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.fundstransfer.FundsTransferRecord;

/**
 * TODO: Document me!
 *
 * @author Tawfiq
 *
 */
public class FTVersionTest extends RecordLifecycle {

    @Override
    public TValidationResponse validateRecord(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {
        
        FundsTransferRecord fundsTransferRecord = new FundsTransferRecord(currentRecord);
        TField field1 = fundsTransferRecord.getDebitCurrency();
        TField field2 = fundsTransferRecord.getCreditCurrency();
        
        if (!field1.getValue().equals(field2.getValue())) {
            field2.setError("CREDIT CURRENCY IS NOT EQUAL TO DEBIT CURRENCY");
        }
        
        return fundsTransferRecord.getValidationResponse();
        
    }
}
