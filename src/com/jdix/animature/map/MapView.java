package com.jdix.animature.map;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

/**
 * @author Razican (Iban Eguia)
 * 
 */
public class MapView extends View {

	private Map	map;
	private int	mWidth;
	private int	mHeight;

	/**
	 * @param context Context of the application
	 */
	public MapView(Context context)
	{
		super(context);
	}

	/**
	 * @param context Context of the application
	 * @param map Map to show
	 */
	public MapView(Context context, int map)
	{
		this(context);
		Map.setContext(context);
		try
		{
			this.map = new Map(map);
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		mHeight = MeasureSpec.getSize(heightMeasureSpec);

		setMeasuredDimension(mWidth, mHeight);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		canvas.drawColor(Color.BLACK);
		Bitmap mapb = map.getBitmap();

		int cx = (mWidth - mapb.getWidth()) / 2;
		int cy = (mHeight - mapb.getHeight()) / 2;

		canvas.drawBitmap(mapb, cx, cy, null);
	}
}