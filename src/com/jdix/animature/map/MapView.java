package com.jdix.animature.map;

import java.io.IOException;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * @author Razican (Iban Eguia)
 * 
 */
public class MapView extends View {

	private Map	map;

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
	protected void onDraw(Canvas canvas)
	{
		canvas.drawBitmap(map.getBitmap(), 0, 0, null);
	}
}