import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements Interface{
    
    private final int PUERTO = 5000;
    
    public Server() throws RemoteException{
    }
    
    public static void main(String[] args) throws RemoteException {
        (new Server()).iniciarServidor();
    }

    private void iniciarServidor() {
        try{
            String dirIP = (InetAddress.getLocalHost()).toString();
            System.out.println("Escuchando en.. "+dirIP+":"+PUERTO);
            Registry registry = LocateRegistry.createRegistry(PUERTO);
            registry.bind("Hello", (Interface) this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    

    @Override
    public String Mensaje(String mensaje) throws RemoteException {
        String request = "El mensaje recivido fue: "+ mensaje;
        System.out.println(""+request);
        return request;
    }
}