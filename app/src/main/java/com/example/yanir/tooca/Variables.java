package com.example.yanir.tooca;

import android.app.Application;

/**
 * Created by Miguel on 03/05/2015.
 */
public class Variables extends Application{

    private String test1, test2_1, test2_2, test2_3, test3_1, test3_2,test3_3, test4, test5;

    public String getResultados_autoexamen(int posicion){

        switch (posicion){
            case 0:
                return this.test1;
            case 1:
                return this.test2_1;
            case 2:
                return this.test2_2;
            case 3:
                return this.test2_3;
            case 4:
                return this.test3_1;
            case 5:
                return this.test3_2;
            case 6:
                return this.test3_3;
            case 7:
                return this.test4;
            case 8:
                return this.test5;
            default:
                return "";
        }
    }

    public void setResultados_autoexamen(String valor, int posicion){

        switch (posicion){
            case 0:
                 this.test1 = valor;
            break;
            case 1:
                 this.test2_1 = valor;
            break;
            case 2:
                 this.test2_2 = valor;
            break;
            case 3:
                 this.test2_3 = valor;
            break;
            case 4:
                 this.test3_1 = valor;
            break;
            case 5:
                 this.test3_2 = valor;
            break;
            case 6:
                 this.test3_3 = valor;
            break;
            case 7:
                 this.test4 = valor;
            break;
            case 8:
                 this.test5 = valor;
            break;
        }
    }
}
