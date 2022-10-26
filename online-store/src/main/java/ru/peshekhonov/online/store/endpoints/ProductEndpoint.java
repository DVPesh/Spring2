package ru.peshekhonov.online.store.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.peshekhonov.online.store.exceptions.ResourceNotFoundException;
import ru.peshekhonov.online.store.mappers.ProductWsDtoMapper;
import ru.peshekhonov.online.store.services.ProductService;
import ru.peshekhonov.online.store.soap.products.GetAllProductsRequest;
import ru.peshekhonov.online.store.soap.products.GetAllProductsResponse;
import ru.peshekhonov.online.store.soap.products.GetProductByIdRequest;
import ru.peshekhonov.online.store.soap.products.GetProductByIdResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://www.peshekhonov.ru/online/store/soap/products";
    private final ProductService productService;
    private final ProductWsDtoMapper productWsDtoMapper;

        /*
        Пример запроса: POST http://localhost:8189/market/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.peshekhonov.ru/online/store/soap/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getProductByIdRequest>
                    <f:id>4</f:id>
                </f:getProductByIdRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        long id = request.getId();
        response.setProduct(productWsDtoMapper.map(productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + id + " не найден"))));
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8189/market/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.peshekhonov.ru/online/store/soap/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productWsDtoMapper.map(productService.findAll()).forEach(response.getProducts()::add);
        return response;
    }
}
