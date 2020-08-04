package com.Solar.SolarEnergy.Utitlity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Configuration;

import com.Solar.SolarEnergy.Controller.PaymentController.InnerPayment;
import com.Solar.SolarEnergy.Domain.Payment;
import com.Solar.SolarEnergy.Domain.Registrar;
import com.braintreegateway.*;

@Configuration
public class BrainTreeConfiguration {
    
 private static final String MERCHANT_ID ="zymjcc6f4r5s26jm";
 private static final String PUBLIC_KEY="kzf8pg2v7m859tyb";
 private static final String PRIVATE_KEY="328e47a495b8265010797d9c0f01d2e2";

    /**
     * Generating client token
     * @return
     */
    public static String brainTreeAnvironment(){
         final BraintreeGateway gateway= new BraintreeGateway(Environment.SANDBOX, MERCHANT_ID,
                PUBLIC_KEY, PRIVATE_KEY);
                ClientTokenRequest clientTokenRequest = new ClientTokenRequest();
                    String clientToken = gateway.clientToken().generate(clientTokenRequest);
                return clientToken;
    }

    public static ResponceObject createTransaction(InnerPayment po){
        final BraintreeGateway gateway = new BraintreeGateway(Environment.SANDBOX, MERCHANT_ID, PUBLIC_KEY,PRIVATE_KEY);
        TransactionRequest request = new TransactionRequest()
        		
                .amount(new BigDecimal(po.getAmount()))
                
                .merchantAccountId("jado")
                .paymentMethodNonce(po.getClientNonce())
                .customer()
                //.customerId(po.getUuid())
                .firstName(po.getFirstname())
                .lastName(po.getLastname())
                .email(po.getEmail())
                .done()
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> result = gateway.transaction().sale(request);
        ResponceObject r=new ResponceObject();
        if(result.isSuccess()){
          r.code=Messages.SUCCESS_CODE;
          r.object=result;
        }else{
          r.code=Messages.ERROR_CODE;
          r.object=result;
        }
        return r;
    }



    public static List<Transaction> findAllTransactions(){
    /*    System.out.println("test 64654654");*/
        final BraintreeGateway gateway = new BraintreeGateway(Environment.SANDBOX, MERCHANT_ID, PUBLIC_KEY,PRIVATE_KEY);
        TransactionSearchRequest request = new TransactionSearchRequest()
                .type().is(Transaction.Type.SALE);

        ResourceCollection<Transaction> collection = gateway.transaction().search(request);
        List<Transaction> transactions=new ArrayList<>();
        collection.iterator().forEachRemaining(transactions::add);
        return transactions;
    }

    public static class ResponceObject{
        private int code;
        private Object object;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }
    }
}