package es.deusto.sd.eurostyletuning.external;

import org.springframework.stereotype.Component;

@Component //Por el autowired del service
public class EuroStyleGatewayFactory {
	
    public enum GatewayType {
        GACK,
        ZIL
    }

    public Object createGateway(GatewayType type) { //deberia devolver un IGateway (interfaz) pero tiene que tener mismo nombre de metodos
        switch (type) {
            case GACK:
                return new GackServiceGatewayImpl();
            case ZIL:
                return new ZILServiceGatewayImpl();
            default:
                throw new IllegalArgumentException("Unsupported gateway type: " + type);
        }
    }
}