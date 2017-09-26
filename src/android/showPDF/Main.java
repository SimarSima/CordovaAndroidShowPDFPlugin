package cordova.plugin.androidShowPDFPlugin;;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;
import simar.android.showpdf.R;

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
        if(fileURL!=null&&!"".equals(fileURL)){
            File pdfFile = new File(fileURL);
            pdfView.fromFile(pdfFile)
                    .defaultPage(0)
                    .onPageChange(this)
                    .enableAnnotationRendering(true)
                    .onLoad(this)
                    .scrollHandle(new DefaultScrollHandle(this))
                    .load();
        }else{
            Toast.makeText(this,getString(R.string.error_path),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void loadComplete(int nbPages) {

    }
}
