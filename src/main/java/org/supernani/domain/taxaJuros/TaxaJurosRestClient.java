package org.supernani.domain.taxaJuros;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import java.util.UUID;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/caixa-verso")
@RegisterRestClient(configKey = "apisparatestes")

public interface TaxaJurosRestClient {

    @GET
    @Path("/pega-taxa")
    TaxaJurosResponseDTO buscaTaxaJuros(@QueryParam("idClient") UUID idCliente);

}
