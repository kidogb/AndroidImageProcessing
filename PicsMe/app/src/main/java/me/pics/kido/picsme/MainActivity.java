package me.pics.kido.picsme;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

//

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("opencv_java3");
    }
    private RecyclerView horizontalRecyclerView;
    private ArrayList<Bitmap> bitmapArr = new ArrayList<Bitmap>();
    private HorizontalAdapter horizontalAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Example of a call to a native method
        Bitmap b1 = BitmapFactory.decodeResource(getResources(),
                R.drawable.la1);
        Bitmap b2 = BitmapFactory.decodeResource(getResources(),
                R.drawable.la2);
        Bitmap b3 = BitmapFactory.decodeResource(getResources(),
                R.drawable.la3);
        Bitmap b4 = BitmapFactory.decodeResource(getResources(),
                R.drawable.la4);
        Bitmap b5 = BitmapFactory.decodeResource(getResources(),
                R.drawable.la5);
        Bitmap b6 = BitmapFactory.decodeResource(getResources(),
                R.drawable.la6);
        Bitmap b7 = BitmapFactory.decodeResource(getResources(),
                R.drawable.la7);
        Bitmap b8 = BitmapFactory.decodeResource(getResources(),
                R.drawable.la8);
        ImageView avatarImgView = (ImageView) findViewById(R.id.avatarImgView);
        avatarImgView.setImageBitmap(b1);
        bitmapArr.add(b2);
        bitmapArr.add(b3);
        bitmapArr.add(b4);
        bitmapArr.add(b5);
        bitmapArr.add(b6);
        bitmapArr.add(b7);
        bitmapArr.add(b8);
        horizontalAdapter = new HorizontalAdapter(MainActivity.this, bitmapArr);
        horizontalRecyclerView = (RecyclerView)findViewById(R.id.avartarRecyclerView);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontalRecyclerView.setLayoutManager(horizontalLayoutManager);
        horizontalRecyclerView.setAdapter(horizontalAdapter);
//        Mat a = new Mat();
//        Utils.bitmapToMat(b,a);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
/**
 * Created by kido on 10/28/2016.
 */
 class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

    private List<Bitmap> horizontalList;
    private Activity activity;
    private ImageView avatarImgView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgView;


        public MyViewHolder(View view) {
            super(view);
            imgView = (ImageView) view.findViewById(R.id.avatarCellView);
        }
    }


    public HorizontalAdapter(Activity act, List<Bitmap> horizontalList) {
        this.horizontalList = horizontalList;
        this.activity = act;
        this.avatarImgView =  (ImageView) activity.findViewById(R.id.avatarImgView);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.avatar_cell_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.imgView.setImageBitmap(horizontalList.get(position));

        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.clickimage));
                avatarImgView.setImageBitmap(horizontalList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }
}