package bsr;

import pl.bank.bsr.*;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import java.util.List;

/**
 * Created by marcin on 01.12.16.
 */
public class ServiceUtil {

    public static LoginResponse logIn(String login, String password) throws BankException {
        BankPortType port = getBankServicePort();
        LoginRequest payload = new LoginRequest();
        payload.setLogin(login);
        payload.setPassword(password);
        LoginResponse response = port.logIn(payload);
        return response;
    }

    public static void register(String name, String surname, String login, String password) throws BankException {
        BankPortType port = getBankServicePort();
        RegisterRequest payload = new RegisterRequest();
        payload.setLogin(login);
        payload.setPassword(password);
        payload.setName(name);
        payload.setSurname(surname);
        port.register(payload);
    }

    public static AccountResponse addNewAccount(String uid) throws BankException {
        BankPortType port = getBankServicePort();
        NewAccountRequest payload = new NewAccountRequest();
        payload.setUid(uid);
        return port.createAccount(payload);
    }

    public static DepositResponse deposit(String uid, String accNumber, Integer value) throws BankException {
        BankPortType port = getBankServicePort();
        DepositMsg payload = new DepositMsg();
        payload.setUid(uid);
        payload.setAccountNumber(accNumber);
        payload.setAmount(value);
        return port.deposit(payload);
    }

    public static TransferResponse transfer(TransferRequest payload) throws BankException {
        BankPortType port = getBankServicePort();
        return port.transfer(payload);
    }

    public static GetAccountsResponse getAccounts(String uuid) throws BankException {
        BankPortType port = getBankServicePort();
        return port.getAccounts(uuid);
    }

    public static GetHistoryResponse getHistory(String uuid, String accNrb) throws BankException {
        BankPortType port = getBankServicePort();
        GetHistoryRequest request = new GetHistoryRequest();
        request.setAccountNumber(accNrb);
        request.setUid(uuid);
        return port.getHistory(request);
    }

    private static BankPortType getBankServicePort(){
        BankService bankService = new BankService();
        BankPortType port = bankService.getBankPort();
        BindingProvider bp = (BindingProvider) port;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8188/soap/BankService");

        List<Handler> handlerChain = bp.getBinding().getHandlerChain();
        handlerChain.add(new SOAPHandler());
        bp.getBinding().setHandlerChain(handlerChain);
        return port;
    }
}
