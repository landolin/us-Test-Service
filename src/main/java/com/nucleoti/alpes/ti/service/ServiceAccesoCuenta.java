package com.nucleoti.alpes.ti.service;


import com.nucleoti.alpes.ti.model.Cliente;
import com.nucleoti.alpes.ti.model.Cuenta;
import com.nucleoti.alpes.ti.model.ProductBean;
import com.nucleoti.alpes.ti.model.ResponCod;

import java.util.List;

public interface ServiceAccesoCuenta {
    public ResponCod AccesoUser(Cuenta cuenta) throws Exception;

    public ResponCod CreateCliente(Cliente user) throws Exception;

    public ResponCod ExistenciEmailServis(String email) throws Exception;

    public List<ProductBean> listProduct(int codEmpresa);

    public ProductBean listProductById(int codCompany, int codproducto);

}
