package utils;

/**
 * Created by Viet Hua on 4/11/2020
 */
public class SportiveUtils {

    public static String getPricePerHourFormat(int price) {
        return String.format("%,d VND /1h", price).replace(",",".");
    }

    public static String getTotalPriceFormat(int price) {
        return String.format("Tổng tiền: %,dđ", price).replace(",",".");
    }
}
