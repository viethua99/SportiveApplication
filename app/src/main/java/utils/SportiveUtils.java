package utils;

/**
 * Created by Viet Hua on 4/11/2020
 */
public class SportiveUtils {

    public static String getPricePerHourFormat(int price) {
        return String.format("%,d VND /1h", price).replace(",", ".");
    }

    public static String getTotalPriceFormat(int price) {
        return String.format("Tổng: %,dđ", price).replace(",", ".");
    }

    public static String getPriceWithDotAndVietnameseCurrencyFormat(int price) {
        return String.format("%,dđ", price).replace(",", ".");

    }

    public static String getDurationFormat(int duration) {
        return String.format("%d Giờ", duration);
    }

    public static String convertShortNameFormatToFullName(String name) {
        String formattedName = "";
        switch (name) {
            case "Q.":
                formattedName.replace(name, "Quận ");
                break;
            case "Đ.":
                formattedName.replace(name, "Đường");
                break;
            default:
                formattedName = "*****";
                break;
        }
        return formattedName;
    }
}
