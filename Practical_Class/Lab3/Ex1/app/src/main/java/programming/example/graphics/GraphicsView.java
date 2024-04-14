package programming.example.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class GraphicsView extends View {
    public GraphicsView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Vẽ màn hình trắng
        canvas.drawColor(Color.WHITE);
        // Ví dụ: vẽ một đường thẳng màu đen từ (0,0) đến (100,100)
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawLine(0, 0, 100, 100, paint);
        invalidate();
        // Thêm các thao tác vẽ khác tại đây
    }
}
