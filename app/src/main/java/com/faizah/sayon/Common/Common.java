package com.faizah.sayon.Common;

import com.faizah.sayon.Model.User;

/**
 * Created by USER on 24/12/2018.
 */

public class Common {
    public static User currentUser;

    public static String convertCodeStatus(String status) {
        if(status.equals("0"))
            return "Placed";
        else if(status.equals("1"))
            return "On my way";
        else
            return "Shipped";
    }
}
