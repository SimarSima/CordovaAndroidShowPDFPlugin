package cordova.plugin.androidShowPDFPlugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;

import io.ionic.starter.R;

;


public class Main extends Activity implements OnPageChangeListener, OnLoadCompleteListener {
  private PDFView pdfView;
  private String fileURL;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_androidshowpdfplugin_main);
    pdfView = (PDFView) findViewById(R.id.pdfView);
    setPdfView();
  }

  private void setPdfView() {
    Intent intent = getIntent();
    fileURL = intent.getStringExtra("PDF_PATH");
    File pdfFile = new File(fileURL);
    if (pdfFile.exists()) {
      pdfView.fromFile(pdfFile)
        .defaultPage(0)
        .onPageChange(this)
        .enableAnnotationRendering(true)
        .onLoad(this)
        .scrollHandle(new DefaultScrollHandle(this))
        .load();

//    if (fileURL != null && !"".equals(fileURL)) {
//
//      pdfFile = new File(pdfFile.getAbsolutePath());
//
//      } else {
//        Toast.makeText(this, getString(R.string.error_path), Toast.LENGTH_LONG).show();
//      }
//
//    } else {
//      Toast.makeText(this, getString(R.string.error_path), Toast.LENGTH_LONG).show();
    }

  }

  @Override
  public void onPageChanged(int page, int pageCount) {

  }

  @Override
  public void loadComplete(int nbPages) {

  }
}
