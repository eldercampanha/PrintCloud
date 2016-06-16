package com.example.elder.printstop.model.dao;

import com.example.elder.printstop.model.Cliente;
import com.example.elder.printstop.util.DBConnection;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Elder on 5/14/2016.
 */
public class SessoesDao {


    public int registerSessao(int id, float novoSaldo, float saldoAntigo) {


        String dataAtual = Formaters.dataAtual();
        String horaAtual = Formaters.horaAtual();
        String valor = String.valueOf(saldoAntigo-novoSaldo);
        String tipoMov = "Impressao";
        String sql = "INSERT INTO conta (data, hora, valor, tipodemovimentacao, saldoanterior, saldoatual, fk_idcliente_cliente_conta) "
                + "VALUES ('" + dataAtual + "', '" + horaAtual + "', '" + valor + "', '" + tipoMov + "', "
                + "'" + saldoAntigo + "', '" + novoSaldo + "', "
                + "'" + id + "')";

        DBConnection.closeConnection();
        try {
            int result = DBConnection.getInstance().executeUpdate(sql);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

class Formaters {

    public static String dataAtual(){

        Calendar c = Calendar.getInstance();
        Date data = c.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        return sdf.format(data);

    }

    public static String horaAtual(){

        GregorianCalendar calendario = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:hh");

        return sdf.format(calendario.getTime());

    }

    public String convertFloatToStringReal(float valor){
        String str = String.format("%.2f", valor);
        return str;
    }

}