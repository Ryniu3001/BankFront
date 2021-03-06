
package pl.bank.bsr;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "BankPortType", targetNamespace = "http://bsr.bank.pl")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BankPortType {


    /**
     * 
     * @param payload
     * @return
     *     returns pl.bank.bsr.LoginResponse
     * @throws BankException
     */
    @WebMethod
    @WebResult(name = "logInResponse", targetNamespace = "http://bsr.bank.pl", partName = "logInResponse")
    @Action(input = "http://bsr.bank.pl/BankPortType/logInRequest", output = "http://bsr.bank.pl/BankPortType/logInResponse", fault = {
        @FaultAction(className = BankException.class, value = "http://bsr.bank.pl/BankPortType/logIn/Fault/BankServiceException")
    })
    public LoginResponse logIn(
        @WebParam(name = "loginRequest", targetNamespace = "http://bsr.bank.pl", partName = "payload")
        LoginRequest payload)
        throws BankException
    ;

    /**
     * 
     * @param payload
     * @throws BankException
     */
    @WebMethod
    @Action(input = "http://bsr.bank.pl/BankPortType/logOutRequest", output = "http://bsr.bank.pl/BankPortType/logOutResponse", fault = {
        @FaultAction(className = BankException.class, value = "http://bsr.bank.pl/BankPortType/logOut/Fault/BankServiceException")
    })
    public void logOut(
        @WebParam(name = "logOutRequest", targetNamespace = "http://bsr.bank.pl", partName = "payload")
        LogOutRequest payload)
        throws BankException
    ;

    /**
     * 
     * @param payload
     * @return
     *     returns pl.bank.bsr.AccountResponse
     * @throws BankException
     */
    @WebMethod
    @WebResult(name = "createAccountResponse", targetNamespace = "http://bsr.bank.pl", partName = "createAccountResponse")
    @Action(input = "http://bsr.bank.pl/BankPortType/createAccountRequest", output = "http://bsr.bank.pl/BankPortType/createAccountResponse", fault = {
        @FaultAction(className = BankException.class, value = "http://bsr.bank.pl/BankPortType/createAccount/Fault/BankServiceException")
    })
    public AccountResponse createAccount(
        @WebParam(name = "newAccountRequest", targetNamespace = "http://bsr.bank.pl", partName = "payload")
        NewAccountRequest payload)
        throws BankException
    ;

    /**
     * 
     * @param payload
     * @return
     *     returns pl.bank.bsr.DepositResponse
     * @throws BankException
     */
    @WebMethod
    @WebResult(name = "depositResponse", targetNamespace = "http://bsr.bank.pl", partName = "depositResponse")
    @Action(input = "http://bsr.bank.pl/BankPortType/depositRequest", output = "http://bsr.bank.pl/BankPortType/depositResponse", fault = {
        @FaultAction(className = BankException.class, value = "http://bsr.bank.pl/BankPortType/deposit/Fault/BankServiceException")
    })
    public DepositResponse deposit(
        @WebParam(name = "depositRequest", targetNamespace = "http://bsr.bank.pl", partName = "payload")
        DepositMsg payload)
        throws BankException
    ;

    /**
     * 
     * @param payload
     * @return
     *     returns pl.bank.bsr.WithdrawResponse
     * @throws BankException
     */
    @WebMethod
    @WebResult(name = "withdrawResponse", targetNamespace = "http://bsr.bank.pl", partName = "withdrawResponse")
    @Action(input = "http://bsr.bank.pl/BankPortType/withdrawRequest", output = "http://bsr.bank.pl/BankPortType/withdrawResponse", fault = {
        @FaultAction(className = BankException.class, value = "http://bsr.bank.pl/BankPortType/withdraw/Fault/BankServiceException")
    })
    public WithdrawResponse withdraw(
        @WebParam(name = "withdrawRequest", targetNamespace = "http://bsr.bank.pl", partName = "payload")
        WithdrawMsg payload)
        throws BankException
    ;

    /**
     * 
     * @param payload
     * @return
     *     returns pl.bank.bsr.GetAccountsResponse
     * @throws BankException
     */
    @WebMethod
    @WebResult(name = "getAccountsResponse", targetNamespace = "http://bsr.bank.pl", partName = "getAccountsResponse")
    @Action(input = "http://bsr.bank.pl/BankPortType/getAccountsRequest", output = "http://bsr.bank.pl/BankPortType/getAccountsResponse", fault = {
        @FaultAction(className = BankException.class, value = "http://bsr.bank.pl/BankPortType/getAccounts/Fault/BankServiceException")
    })
    public GetAccountsResponse getAccounts(
        @WebParam(name = "getAccountsRequest", targetNamespace = "http://bsr.bank.pl", partName = "payload")
        String payload)
        throws BankException
    ;

    /**
     * 
     * @param payload
     * @return
     *     returns pl.bank.bsr.GetHistoryResponse
     * @throws BankException
     */
    @WebMethod
    @WebResult(name = "getHistoryResponse", targetNamespace = "http://bsr.bank.pl", partName = "getHistoryResponse")
    @Action(input = "http://bsr.bank.pl/BankPortType/getHistoryRequest", output = "http://bsr.bank.pl/BankPortType/getHistoryResponse", fault = {
        @FaultAction(className = BankException.class, value = "http://bsr.bank.pl/BankPortType/getHistory/Fault/BankServiceException")
    })
    public GetHistoryResponse getHistory(
        @WebParam(name = "getHistoryRequest", targetNamespace = "http://bsr.bank.pl", partName = "payload")
        GetHistoryRequest payload)
        throws BankException
    ;

    /**
     * 
     * @param payload
     * @throws BankException
     */
    @WebMethod
    @Action(input = "http://bsr.bank.pl/BankPortType/registerRequest", output = "http://bsr.bank.pl/BankPortType/registerResponse", fault = {
        @FaultAction(className = BankException.class, value = "http://bsr.bank.pl/BankPortType/register/Fault/BankServiceException")
    })
    public void register(
        @WebParam(name = "registerRequest", targetNamespace = "http://bsr.bank.pl", partName = "payload")
        RegisterRequest payload)
        throws BankException
    ;

    /**
     * 
     * @param payload
     * @return
     *     returns pl.bank.bsr.TransferResponse
     * @throws BankException
     */
    @WebMethod
    @WebResult(name = "transferResponse", targetNamespace = "http://bsr.bank.pl", partName = "transferResponse")
    @Action(input = "http://bsr.bank.pl/BankPortType/transferRequest", output = "http://bsr.bank.pl/BankPortType/transferResponse", fault = {
        @FaultAction(className = BankException.class, value = "http://bsr.bank.pl/BankPortType/transfer/Fault/BankServiceException")
    })
    public TransferResponse transfer(
        @WebParam(name = "transferRequest", targetNamespace = "http://bsr.bank.pl", partName = "payload")
        TransferRequest payload)
        throws BankException
    ;

}
