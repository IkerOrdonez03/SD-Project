package es.deusto.sd.eurostyletuning.external;

public class EuroStyleGateway {

    public enum GatewayType {
        GACK,
        ZIL
    }

    public static Object createGateway(GatewayType type) {
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