package programming.example.graphics;

import android.os.Bundle;

import com.example.ex2.MainActivity;

public class SurfaceView extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MySurfaceView(this));
}
