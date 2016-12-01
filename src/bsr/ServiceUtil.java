package bsr;

import pl.bank.bsr.*;

import javax.xml.ws.BindingProvider;

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

    private static BankPortType getBankServicePort(){
        BankService bankService = new BankService();
        BankPortType port = bankService.getBankPort();
        BindingProvider bp = (BindingProvider) port;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8188/soap/BankService");
        return port;
    }
}
