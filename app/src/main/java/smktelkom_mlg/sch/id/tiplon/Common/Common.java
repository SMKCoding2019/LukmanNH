package smktelkom_mlg.sch.id.tiplon.Common;

import smktelkom_mlg.sch.id.tiplon.Model.User;

public class Common {
    public static User currentUser;

    public static String convertCodeToStatus(String status) {
        if(status.equals("0"))
            return "Penjemputan Laundry";
        else if(status.equals("1"))
            return "Selesai";
        else
            return "Pengerjaan/Antrian";
    }
}
