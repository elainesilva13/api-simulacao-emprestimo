package org.supernani.domain.taxaJuros;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import java.util.UUID;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.supernani.domain.exceptions.TaxaJurosExceptionMapper;

@Path("/api/caixa-verso")
@RegisterRestClient(configKey = "apisparatestes")
@RegisterProvider(TaxaJurosExceptionMapper.class)

public interface TaxaJurosRestClient {

    @GET
    @Path("/pega-taxa")
    ///Response buscaTaxaJuros(@QuerParam("idClient") UUID idCliente);
    TaxaJurosResponseDTO buscaTaxaJuros(@QueryParam("idClient") UUID idCliente);

}
