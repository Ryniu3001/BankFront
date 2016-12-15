
package pl.bank.bsr;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.bank.bsr package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _WithdrawRequest_QNAME = new QName("http://bsr.bank.pl", "withdrawRequest");
    private final static QName _AccountResponse_QNAME = new QName("http://bsr.bank.pl", "accountResponse");
    private final static QName _NewAccountRequest_QNAME = new QName("http://bsr.bank.pl", "newAccountRequest");
    private final static QName _DepositRequest_QNAME = new QName("http://bsr.bank.pl", "depositRequest");
    private final static QName _LoginRequest_QNAME = new QName("http://bsr.bank.pl", "loginRequest");
    private final static QName _DepositMsg_QNAME = new QName("http://bsr.bank.pl", "depositMsg");
    private final static QName _CreateAccountResponse_QNAME = new QName("http://bsr.bank.pl", "createAccountResponse");
    private final static QName _DepositResponse_QNAME = new QName("http://bsr.bank.pl", "depositResponse");
    private final static QName _WithdrawResponse_QNAME = new QName("http://bsr.bank.pl", "withdrawResponse");
    private final static QName _TransferResponse_QNAME = new QName("http://bsr.bank.pl", "transferResponse");
    private final static QName _RegisterRequest_QNAME = new QName("http://bsr.bank.pl", "registerRequest");
    private final static QName _LogOutRequest_QNAME = new QName("http://bsr.bank.pl", "logOutRequest");
    private final static QName _BankServiceException_QNAME = new QName("http://bsr.bank.pl", "BankServiceException");
    private final static QName _TransferRequest_QNAME = new QName("http://bsr.bank.pl", "transferRequest");
    private final static QName _LogInResponse_QNAME = new QName("http://bsr.bank.pl", "logInResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.bank.bsr
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AccountResponse }
     * 
     */
    public AccountResponse createAccountResponse() {
        return new AccountResponse();
    }

    /**
     * Create an instance of {@link DepositResponse }
     * 
     */
    public DepositResponse createDepositResponse() {
        return new DepositResponse();
    }

    /**
     * Create an instance of {@link DepositMsg }
     * 
     */
    public DepositMsg createDepositMsg() {
        return new DepositMsg();
    }

    /**
     * Create an instance of {@link LoginRequest }
     * 
     */
    public LoginRequest createLoginRequest() {
        return new LoginRequest();
    }

    /**
     * Create an instance of {@link NewAccountRequest }
     * 
     */
    public NewAccountRequest createNewAccountRequest() {
        return new NewAccountRequest();
    }

    /**
     * Create an instance of {@link WithdrawMsg }
     * 
     */
    public WithdrawMsg createWithdrawMsg() {
        return new WithdrawMsg();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link FaultBean }
     * 
     */
    public FaultBean createFaultBean() {
        return new FaultBean();
    }

    /**
     * Create an instance of {@link TransferRequest }
     * 
     */
    public TransferRequest createTransferRequest() {
        return new TransferRequest();
    }

    /**
     * Create an instance of {@link LogOutRequest }
     * 
     */
    public LogOutRequest createLogOutRequest() {
        return new LogOutRequest();
    }

    /**
     * Create an instance of {@link RegisterRequest }
     * 
     */
    public RegisterRequest createRegisterRequest() {
        return new RegisterRequest();
    }

    /**
     * Create an instance of {@link TransferResponse }
     * 
     */
    public TransferResponse createTransferResponse() {
        return new TransferResponse();
    }

    /**
     * Create an instance of {@link WithdrawResponse }
     * 
     */
    public WithdrawResponse createWithdrawResponse() {
        return new WithdrawResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WithdrawMsg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bsr.bank.pl", name = "withdrawRequest")
    public JAXBElement<WithdrawMsg> createWithdrawRequest(WithdrawMsg value) {
        return new JAXBElement<WithdrawMsg>(_WithdrawRequest_QNAME, WithdrawMsg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bsr.bank.pl", name = "accountResponse")
    public JAXBElement<AccountResponse> createAccountResponse(AccountResponse value) {
        return new JAXBElement<AccountResponse>(_AccountResponse_QNAME, AccountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewAccountRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bsr.bank.pl", name = "newAccountRequest")
    public JAXBElement<NewAccountRequest> createNewAccountRequest(NewAccountRequest value) {
        return new JAXBElement<NewAccountRequest>(_NewAccountRequest_QNAME, NewAccountRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepositMsg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bsr.bank.pl", name = "depositRequest")
    public JAXBElement<DepositMsg> createDepositRequest(DepositMsg value) {
        return new JAXBElement<DepositMsg>(_DepositRequest_QNAME, DepositMsg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bsr.bank.pl", name = "loginRequest")
    public JAXBElement<LoginRequest> createLoginRequest(LoginRequest value) {
        return new JAXBElement<LoginRequest>(_LoginRequest_QNAME, LoginRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepositMsg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bsr.bank.pl", name = "depositMsg")
    public JAXBElement<DepositMsg> createDepositMsg(DepositMsg value) {
        return new JAXBElement<DepositMsg>(_DepositMsg_QNAME, DepositMsg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bsr.bank.pl", name = "createAccountResponse")
    public JAXBElement<AccountResponse> createCreateAccountResponse(AccountResponse value) {
        return new JAXBElement<AccountResponse>(_CreateAccountResponse_QNAME, AccountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepositResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bsr.bank.pl", name = "depositResponse")
    public JAXBElement<DepositResponse> createDepositResponse(DepositResponse value) {
        return new JAXBElement<DepositResponse>(_DepositResponse_QNAME, DepositResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WithdrawResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bsr.bank.pl", name = "withdrawResponse")
    public JAXBElement<WithdrawResponse> createWithdrawResponse(WithdrawResponse value) {
        return new JAXBElement<WithdrawResponse>(_WithdrawResponse_QNAME, WithdrawResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransferResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bsr.bank.pl", name = "transferResponse")
    public JAXBElement<TransferResponse> createTransferResponse(TransferResponse value) {
        return new JAXBElement<TransferResponse>(_TransferResponse_QNAME, TransferResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bsr.bank.pl", name = "registerRequest")
    public JAXBElement<RegisterRequest> createRegisterRequest(RegisterRequest value) {
        return new JAXBElement<RegisterRequest>(_RegisterRequest_QNAME, RegisterRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogOutRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bsr.bank.pl", name = "logOutRequest")
    public JAXBElement<LogOutRequest> createLogOutRequest(LogOutRequest value) {
        return new JAXBElement<LogOutRequest>(_LogOutRequest_QNAME, LogOutRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bsr.bank.pl", name = "BankServiceException")
    public JAXBElement<FaultBean> createBankServiceException(FaultBean value) {
        return new JAXBElement<FaultBean>(_BankServiceException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransferRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bsr.bank.pl", name = "transferRequest")
    public JAXBElement<TransferRequest> createTransferRequest(TransferRequest value) {
        return new JAXBElement<TransferRequest>(_TransferRequest_QNAME, TransferRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bsr.bank.pl", name = "logInResponse")
    public JAXBElement<LoginResponse> createLogInResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LogInResponse_QNAME, LoginResponse.class, null, value);
    }

}
