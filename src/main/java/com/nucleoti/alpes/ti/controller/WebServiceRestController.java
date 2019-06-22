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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;


@Controller
@RestController
@Api(value = "AlpesRest", produces = "application/json")
public class WebServiceRestController {
    private static final Logger logger = LoggerFactory.getLogger(WebServiceRestController.class);

    //Map to store employees, ideally we should use database
    // Map<Integer, Employee> empData = new HashMap<Integer, Employee>();

    @Autowired
    ServiceAccesoCuenta serviceACCES;

    @RequestMapping(value = Constantes.DUMMY_EMP, method = RequestMethod.GET)
    public @ResponseBody
    List<Usuario> getDataPrueba() {
        logger.info("Start getDummyEmployee");
        List<Usuario> list = new ArrayList<Usuario>();
        Usuario row = null;
        for (int i = 0; i < 10; i++) {
            row = new Usuario();
            row.setName("Usuario" + " " + String.valueOf(i));
            row.setIdUsuario(String.valueOf(i));
            row.setApaterno("xpater" + " " + String.valueOf(i));
            row.setAmaterno("xmater" + " " + String.valueOf(i));
            list.add(row);
        }
        return list;

    }

    @RequestMapping(value = Constantes.GET_USER, method = RequestMethod.GET)
    public @ResponseBody
    Usuario getUsuario() {
        Usuario user = new Usuario();
        user.setName("carlos");
        user.setApaterno("lopez");
        user.setAmaterno("acaro");
        user.setIdUsuario("1");
        return user;
    }

    @RequestMapping(value = Constantes.ACCES_CUENTA, method = RequestMethod.POST)
    public @ResponseBody
    ResponCod AccesCuenta(@RequestBody Cuenta cuenta) {
        System.out.println(cuenta.toString());
        ResponCod cod = null;
        try {
            cod = serviceACCES.AccesoUser(cuenta);
        } catch (Exception e) {
            System.out.println("mensajeError: " + e.getMessage());
        }

        return cod;
    }


    @RequestMapping(value = Constantes.CREATE_CLIENTE, method = RequestMethod.POST)
    public @ResponseBody
    ResponCod CreateCliente(@RequestBody Cliente cliente) {
        System.out.println(cliente.getName());

        ResponCod row = null;
        try {
            row = serviceACCES.CreateCliente(cliente);
            //cod=serviceAccesoCuenta.ExistenciEmailServis(cliente.getEmail());
            System.out.println("codigo-->" + row.getId() + "---email" + cliente.getUsrCuenta());
        } catch (Exception e) {

        }


        return row;
    }

    @RequestMapping(value = Constantes.EXISTE_EMAIL, method = RequestMethod.POST)
    public @ResponseBody
    ResponCod ExiseEmail(@RequestBody beanExistenciaEmail email) {

        ResponCod row = null;
        try {
            row = serviceACCES.ExistenciEmailServis(email.getEmail());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return row;
    }

    @GetMapping("/hola")
    @ResponseBody
    public String hola() {

        return "has hecho una peticion get";

    }

    @RequestMapping(value = "/prueba", method = RequestMethod.GET)
    public @ResponseBody
    String prueba() {
        return "HOLA MUNDO";
    }

    @RequestMapping(value = Constantes.LIST_PRODUCT, method = RequestMethod.GET)
    public @ResponseBody
    List<ProductBean> listProduct(@PathVariable int id) {
        List<ProductBean> lst = null;

        lst = serviceACCES.listProduct(id);

        return lst;
    }

    @RequestMapping(value = Constantes.PRODUCT_BY_ID, method = RequestMethod.GET)
    @ApiOperation(value = "Retorna un producto", tags = "Controlador Rest")
    public @ResponseBody
    ProductBean productById(@PathParam("empresaId") int empresaId, @PathParam("productoId") int productoId) {
        ProductBean bean = serviceACCES.listProductById(empresaId, productoId);
        return bean;
    }


    @RequestMapping(value = Constantes.PRODUCT_OBJECTO, method = RequestMethod.POST)
    public @ResponseBody
    ProductBean productOjectIn(@RequestBody ProductBean productBean) {
        ProductBean bean = serviceACCES.listProductById(30, productBean.getCodProducto());
        return bean;
    }
}

