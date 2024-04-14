package programming.example.graphics;

import android.os.Bundle;
import com.example.ex1.MainActivity;

public class Graphics extends MainActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(new GraphicsView(this));
        }
}
