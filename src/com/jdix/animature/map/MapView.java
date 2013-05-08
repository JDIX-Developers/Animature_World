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

	private Map			map;
	private int			mWidth;
	private int			mHeight;
	private Context		context;

	private static int	NONE	= 0;
	private static int	UP		= 1;
	private static int	DOWN	= 2;
	private static int	LEFT	= 3;
	private static int	RIGHT	= 4;
	private static int	A		= 5;
	private static int	B		= 6;

	private int			control;

	private int[]		posA;
	private int[]		posB;
	private int[]		posUP;
	private int[]		posDOWN;
	private int[]		posLEFT;
	private int[]		posRIGHT;

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
		this.control = NONE;

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
		final Bitmap ctrlA = BitmapFactory.decodeResource(
		context.getResources(), R.drawable.ctrla);
		final Bitmap ctrlB = BitmapFactory.decodeResource(
		context.getResources(), R.drawable.ctrlb);

		// Cross
		final int cx = canvas.getWidth() / 24;
		final int cy = canvas.getHeight() / 2;

		posUP = new int[4];
		posUP[0] = cx + cross.getWidth() / 3;
		posUP[1] = cy;
		posUP[2] = cx + 2 * cross.getWidth() / 3;
		posUP[3] = cy + cross.getHeight() / 3;

		posDOWN = new int[4];
		posDOWN[0] = cx + cross.getWidth() / 3;
		posDOWN[1] = cy + 2 * cross.getHeight() / 3;
		posDOWN[2] = cx + 2 * cross.getWidth() / 3;
		posDOWN[3] = cy + cross.getHeight();

		posLEFT = new int[4];
		posLEFT[0] = cx;
		posLEFT[1] = cy + cross.getHeight() / 3;
		posLEFT[2] = cx + cross.getWidth() / 3;
		posLEFT[3] = cy + 2 * cross.getHeight() / 3;

		posRIGHT = new int[4];
		posRIGHT[0] = cx + 2 * cross.getWidth() / 3;
		posRIGHT[1] = cy + cross.getHeight() / 3;
		posRIGHT[2] = cx + cross.getWidth();
		posRIGHT[3] = cy + 2 * cross.getHeight() / 3;

		canvas.drawBitmap(cross, cx, cy, null);

		// A
		final int ax = 17 * canvas.getWidth() / 20;
		final int ay = canvas.getHeight() / 3;

		canvas.drawBitmap(ctrlA, ax, ay, null);

		// B
		final int bx = 15 * canvas.getWidth() / 20;
		final int by = canvas.getHeight() / 2;

		canvas.drawBitmap(ctrlB, bx, by, null);
	}

	@Override
	public boolean onTouch(final View v, final MotionEvent event)
	{
		final Vibrator vibrator = (Vibrator) context
		.getSystemService(Context.VIBRATOR_SERVICE);

		if (event.getAction() == MotionEvent.ACTION_DOWN
		|| event.getAction() == MotionEvent.ACTION_MOVE)
		{
			final int control = getControl((int) event.getX(),
			(int) event.getY());
			if (control == UP)
			{
				if (this.control != UP)
				{
					this.control = UP;
					vibrator.vibrate(50);
				}
			}
			else if (control == DOWN)
			{
				if (this.control != DOWN)
				{
					this.control = DOWN;
					vibrator.vibrate(50);
				}
			}
			else if (control == LEFT)
			{
				if (this.control != LEFT)
				{
					this.control = LEFT;
					vibrator.vibrate(50);
				}
			}
			else if (control == RIGHT)
			{
				if (this.control != RIGHT)
				{
					this.control = RIGHT;
					vibrator.vibrate(50);
				}
			}
		}

		if (event.getAction() == MotionEvent.ACTION_UP)
		{
			this.control = NONE;
		}
		// TODO Controls

		return true;
	}

	private int getControl(final int x, final int y)
	{
		if (x >= posUP[0] && y >= posUP[1] && x <= posUP[2] && y <= posUP[3])
		{
			return UP;
		}

		if (x >= posDOWN[0] && y >= posDOWN[1] && x <= posDOWN[2]
		&& y <= posDOWN[3])
		{
			return DOWN;
		}

		if (x >= posLEFT[0] && y >= posLEFT[1] && x <= posLEFT[2]
		&& y <= posLEFT[3])
		{
			return LEFT;
		}

		if (x >= posRIGHT[0] && y >= posRIGHT[1] && x <= posRIGHT[2]
		&& y <= posRIGHT[3])
		{
			return RIGHT;
		}

		return 0;
	}
}