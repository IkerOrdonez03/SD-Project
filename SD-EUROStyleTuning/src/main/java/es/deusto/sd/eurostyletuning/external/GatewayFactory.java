package es.deusto.sd.eurostyletuning.external;

public interface GatewayFactory<T> {
    T createGateway();
}