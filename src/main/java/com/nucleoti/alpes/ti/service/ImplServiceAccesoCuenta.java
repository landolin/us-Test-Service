package com.nucleoti.alpes.ti.service;

import com.nucleoti.alpes.ti.dao.LoginCuenta;
import com.nucleoti.alpes.ti.model.Cliente;
import com.nucleoti.alpes.ti.model.Cuenta;
import com.nucleoti.alpes.ti.model.ProductBean;
import com.nucleoti.alpes.ti.model.ResponCod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImplServiceAccesoCuenta implements ServiceAccesoCuenta {
    @Autowired
    LoginCuenta loginCuenta;

    @Override
    public ResponCod AccesoUser(Cuenta cuenta) throws Exception {
        // TODO Auto-generated method stub
        ResponCod row = loginCuenta.AccesoUser(cuenta);
        return row;
    }

    @Override
    public ResponCod CreateCliente(Cliente user) throws Exception {
        // TODO Auto-generated method stub
        ResponCod row = loginCuenta.CreateCliente(user);
        return row;
    }

    @Override
    public ResponCod ExistenciEmailServis(String email) throws Exception {
        // TODO Auto-generated method stub
        ResponCod row = null;
        try {
            row = new ResponCod();
            int cod = loginCuenta.ExistenciaEmail(email);
            switch (cod) {
                case 0:
                    ///
                    break;
                case 1:
                    row.setId(String.valueOf(cod));
                    row.setMensaje("Email no registrado");
                    break;
                case 2:
                    row.setId(String.valueOf(cod));
                    row.setMensaje("Email ya esta registrado");
                    break;

            }
        } catch (Exception e) {
            row.setId("0");
            row.setMensaje(e.getMessage());

        }

        return row;
    }

    @Override
    public List<ProductBean> listProduct(int codEmpresa) {
        return loginCuenta.listProduct(codEmpresa);
    }
    
    @Override
    public ProductBean listProductById(int codCompany,int codproducto) {
        //ServiceAccesoCuenta sm=(x,y) -> {codCompany,codproducto;};
        return loginCuenta.listProductById(codCompany,codproducto);
    }
}
