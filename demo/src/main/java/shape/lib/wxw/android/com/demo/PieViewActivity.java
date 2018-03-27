package shape.lib.wxw.android.com.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.android.wxw.lib.shape.view.PieView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weici on 2018/3/27.
 */

public class PieViewActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pieview);
        initPieView();
    }

    private void initPieView(){

        int[] colorIds = {R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent, R.color.color_fc7bee,
                R.color.color_30a2e4, R.color.color_00ad00, R.color.color_ffba07, R.color.color_616161,
                R.color.color_39bec6, R.color.color_f68181};
        PieView pieView = findViewById(R.id.pieview);
        List<PieView.PieData> list = new ArrayList<>();
        for(int i=0; i<10; i++){
            PieView.PieData data = new PieView.PieData();
            data.progress = (float) (Math.random() * 100);
            data.text = "占比" + (int)data.progress;
            data.color = ContextCompat.getColor(this, colorIds[i]);

            list.add(data);
        }

        pieView.setData(list);
    }
}
