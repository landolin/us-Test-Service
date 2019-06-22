package com.nucleoti.alpes.ti.dao;


import com.nucleoti.alpes.ti.model.Cliente;
import com.nucleoti.alpes.ti.model.Cuenta;
import com.nucleoti.alpes.ti.model.ProductBean;
import com.nucleoti.alpes.ti.model.ResponCod;

import java.util.List;

public interface LoginCuenta {
    public ResponCod AccesoUser(Cuenta cuenta);

    public ResponCod CreateCliente(Cliente cliente) throws Exception;

    public int ExistenciaEmail(String email) throws Exception;

    public List<ProductBean> listProduct(int codEmpresa);

    public ProductBean listProductById(int codCompany,int codProduct);
}
