////////////////////////////////////////////////////////////////////
// Davide Sut 1201267
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

import java.util.List;

public class RestaurantBillException extends Exception {
    private errInfo;
    public RestaurantBillException(String errInfo){
        errInfo = this.errInfo;
        if(errInfo == "To many items"){
            System.out.println("Items must be < 30");
        }else{
            System.out.println("Generic error");
        }
    }
}