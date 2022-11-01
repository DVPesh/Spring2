package ru.peshekhonov.front.service.controllers;

import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.peshekhonov.dto.ProductDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestTemplateController {

    private final EurekaClient client;

    @GetMapping("/api/v2/products")
    public List<ProductDto> getProducts(RestTemplate restTemplate) {
        String url = client.getNextServerFromEureka("product-service", false).getHomePageUrl();
        return restTemplate.getForObject(url + "market/api/v1/products", List.class);
    }

    @GetMapping("/api/v2/hostname")
    public String getProducts() {
        return client.getNextServerFromEureka("product-service", false).getHomePageUrl();
    }
}
