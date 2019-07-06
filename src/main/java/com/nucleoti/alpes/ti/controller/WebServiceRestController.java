package com.nucleoti.alpes.ti.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.nucleoti.alpes.ti.constantes.Constantes;
import com.nucleoti.alpes.ti.model.*;
import com.nucleoti.alpes.ti.service.ServiceAccesoCuenta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;


@Controller
@RestController
@Api(value = "AlpesRest", produces = "application/json")
public class WebServiceRestController {
    private static final Logger logger = LoggerFactory.getLogger(WebServiceRestController.class);

    @Autowired
    ServiceAccesoCuenta serviceACCES;

    @RequestMapping(value = Constantes.LIST_PRODUCT, method = RequestMethod.GET)
    public @ResponseBody
    List<ProductBean> listProduct(@PathVariable int id) {
        List<ProductBean> lst = null;
        lst = serviceACCES.listProduct(id);
        return lst;
    }


    //Override timeouts in request factory
    private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory
                = new SimpleClientHttpRequestFactory();
        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(1000);

        //Read timeout
        clientHttpRequestFactory.setReadTimeout(1000);
        return clientHttpRequestFactory;
    }

    @RequestMapping(value = Constantes.PRODUCT_BY_ID, method = RequestMethod.GET)
    @ApiOperation(value = "Retorna un producto", tags = "Controlador Rest")
    public @ResponseBody
    ProductBean productById(@PathParam("empresaId") int empresaId, @PathParam("productoId") int productoId) {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        ProductBean bean = serviceACCES.listProductById(empresaId, productoId);
        try {
            ProductBean lst = restTemplate.getForObject("localhost:8086/rest/productoById?empresaId=30&productoId=92", ProductBean.class);
            System.out.println("MOSTRANDO RESPUESTA: " + lst.getCodProducto()+" -> "+lst.getDescription());
        }catch(Exception e){
            e.printStackTrace();
        }
        return bean;
    }


    @RequestMapping(value = Constantes.QUERY_PRODUCT_BY_ID, method = RequestMethod.POST)
    public @ResponseBody
    ProductBean productOjectIn(@RequestBody ProductBean productBean) {
        //ProductBean bean = serviceACCES.listProductById(30, productBean.getCodProducto());
        ProductBean p=new ProductBean();
        p.setCodProducto(93);
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        ProductBean lst = restTemplate.postForObject("http://localhost:8086/rest/producto", p ,ProductBean.class);
        System.out.println("***MOSTRANDO RESPUESTA***: " + lst.getCodProducto()+" -> "+lst.getDescription());
        return lst;
    }
}

