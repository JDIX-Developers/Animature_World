package com.jdix.animature.map;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.jdix.animature.R;

/**
 * @author Razican (Iban Eguia)
 */
public class MapView extends View implements OnTouchListener {

	private Map		map;
	private int		mWidth;
	private int		mHeight;
	private Context	context;

	/**
	 * @param context Context of the application
	 */
	public MapView(final Context context)
	{
		super(context);
		this.context = context;
	}

	/**
	 * @param context - Context of the application
	 * @param map - Map to show
	 * @param sprite - The sprite to use
	 * @param sprbmp - The drawable of the sprite
	 */
	public MapView(final Context context, final int map, final int sprite,
	final int sprbmp)
	{
		this(context);
		this.context = context;

		Square.setSprite(new Sprite(context, sprite, sprbmp));
		Map.setContext(context);
		try
		{
			this.map = new Map(map);
		}
		catch (final IOException e)
		{
			System.err.println(e.getMessage());
		}

		setOnTouchListener(this);
	}

	@Override
	protected void onMeasure(final int widthMeasureSpec,
	final int heightMeasureSpec)
	{
		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		mHeight = MeasureSpec.getSize(heightMeasureSpec);

		setMeasuredDimension(mWidth, mHeight);
	}

	@Override
	protected void onDraw(final Canvas canvas)
	{
		super.onDraw(canvas);
		canvas.drawColor(Color.BLACK);
		final Bitmap mapb = map.getBitmap();

		final int cx = (mWidth - mapb.getWidth()) / 2;
		final int cy = (mHeight - mapb.getHeight()) / 2;

		canvas.drawBitmap(mapb, cx, cy, null);
		drawControls(canvas);
	}

	private void drawControls(final Canvas canvas)
	{
		final Bitmap cross = BitmapFactory.decodeResource(
		context.getResources(), R.drawable.cross);

		final int x = canvas.getWidth() / 24;
		final int y = canvas.getHeight() / 2;

		canvas.drawBitmap(cross, x, y, null);

	}

	@Override
	public boolean onTouch(final View v, final MotionEvent event)
	{
		// TODO Controls
		final Vibrator vibrator = (Vibrator) context
		.getSystemService(Context.VIBRATOR_SERVICE);

		vibrator.vibrate(100);
		return false;
	}
}