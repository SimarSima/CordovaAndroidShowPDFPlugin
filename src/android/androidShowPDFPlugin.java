package cordova.plugin.androidShowPDFPlugin;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;

import io.ionic.starter.R;

/**
 * This class echoes a string called from JavaScript.
 */
public class androidShowPDFPlugin extends CordovaPlugin {

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if (action.equals("coolMethod")) {
      String message = args.getString(0);
      Log.d("TAG", "PDF Path:" + message);
      this.coolMethod(message, callbackContext);
      return true;
    }
    return false;
  }

  private void coolMethod(String message, CallbackContext callbackContext) {
    if (message != null && !"".equals(message)) {
      File file = new File(message);
      //file = new File(file.getAbsolutePath());
      if (file.exists()) {
        Intent intent = new Intent();
        intent.setClass(cordova.getActivity(), Main.class);
        intent.putExtra("PDF_PATH", message);
        cordova.getActivity().startActivity(intent);
      } else {
        Toast.makeText(cordova.getActivity(), cordova.getActivity().getString(R.string.error_path), Toast.LENGTH_LONG).show();
        callbackContext.error(cordova.getActivity().getString(R.string.error_path));
      }
    } else {
      Toast.makeText(cordova.getActivity(), cordova.getActivity().getString(R.string.error_path), Toast.LENGTH_LONG).show();
      callbackContext.error(cordova.getActivity().getString(R.string.error_path));
    }
  }
}
