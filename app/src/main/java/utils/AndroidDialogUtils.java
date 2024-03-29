package utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created By Viet Hua on 4/7/2020
 */
public class AndroidDialogUtils {
    private static AndroidDialogUtils INSTANCE;
    private AlertDialog alertDialog;

    private AndroidDialogUtils() {

    }

    public static AndroidDialogUtils getInstance() {
        if (INSTANCE == null) {
            synchronized (AndroidDialogUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AndroidDialogUtils();
                }
            }
        }
        return INSTANCE;
    }

    public void showAlertDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });
        alertDialog = builder.create();
        alertDialog.show();
    }
    private ProgressDialog mProgressDialog = null;

    public void showLoadingDialog(Context ctx, String message) {
        hideLoadingDialog();
        mProgressDialog = ProgressDialog.show(ctx, null, message, false, false);

    }

    public void hideLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


}
