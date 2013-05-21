package com.jdix.animature.map;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.jdix.animature.BattleSceneActivity;
import com.jdix.animature.R;
import com.jdix.animature.entities.Player;

/**
 * @author Razican (Iban Eguia)
 */
public class MapView extends View implements OnTouchListener {

	private Map				map;
	private int				mWidth;
	private int				mHeight;
	private final Context	context;
	private int				control;
	private int				map0x, map0y;
	private MoveThread		move;

	private static int		NONE	= 0;
	private static int		UP		= 1;
	private static int		DOWN	= 2;
	private static int		LEFT	= 4;
	private static int		RIGHT	= 8;
	private static int		A		= 16;
	private static int		B		= 32;

	private int[]			posCross;
	private int[]			posA;
	private int[]			posB;
	private int[]			posUP;
	private int[]			posDOWN;
	private int[]			posLEFT;
	private int[]			posRIGHT;

	/**
	 * @param context - Context of the application
	 * @param map - Map to show
	 * @param sprite - The sprite to use
	 * @param sprbmp - The drawable of the sprite
	 */
	public MapView(final Context context, final int map, final int sprite,
	final int sprbmp)
	{
		super(context);
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

		Player
		.setPlayer(
		1,
		"TestName",
		Player.BOY,
		"TestEnemy",
		0,
		0,
		0,
		0,
		null,
		3,
		3,
		Player.SOUTH,
		0,
		0,
		0,
		BitmapFactory.decodeResource(context.getResources(), R.drawable.player),
		null);

		this.move = new MoveThread();

		setOnTouchListener(this);
	}

	private void calculateControlPositions(final Bitmap cross,
	final Bitmap ctrlA, final Bitmap ctrlB, final Canvas canvas)
	{
		// Cross
		final int cx = canvas.getWidth() / 24;
		final int cy = canvas.getHeight() / 2;

		posCross = new int[2];
		posCross[0] = cx;
		posCross[1] = cy;

		posUP = new int[4];
		posUP[0] = cx + cross.getWidth() / 3;
		posUP[1] = cy;
		posUP[2] = cx + 2 * cross.getWidth() / 3;
		posUP[3] = cy + cross.getHeight() / 3 - 1;

		posDOWN = new int[4];
		posDOWN[0] = cx + cross.getWidth() / 3;
		posDOWN[1] = cy + 2 * cross.getHeight() / 3 + 1;
		posDOWN[2] = cx + 2 * cross.getWidth() / 3;
		posDOWN[3] = cy + cross.getHeight();

		posLEFT = new int[4];
		posLEFT[0] = cx;
		posLEFT[1] = cy + cross.getHeight() / 3;
		posLEFT[2] = cx + cross.getWidth() / 3 - 1;
		posLEFT[3] = cy + 2 * cross.getHeight() / 3;

		posRIGHT = new int[4];
		posRIGHT[0] = cx + 2 * cross.getWidth() / 3 + 1;
		posRIGHT[1] = cy + cross.getHeight() / 3;
		posRIGHT[2] = cx + cross.getWidth();
		posRIGHT[3] = cy + 2 * cross.getHeight() / 3;

		// A
		final int ax = 17 * canvas.getWidth() / 20;
		final int ay = canvas.getHeight() / 3;

		posA = new int[4];
		posA[0] = ax;
		posA[1] = ay;
		posA[2] = ax + ctrlA.getWidth();
		posA[3] = ay + ctrlA.getHeight();

		// B
		final int bx = ax - 1 - ctrlB.getWidth();
		final int by = canvas.getHeight() / 2;

		posB = new int[4];
		posB[0] = bx;
		posB[1] = by;
		posB[2] = bx + ctrlB.getWidth();
		posB[3] = by + ctrlB.getHeight();
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

		map0x = cx;
		map0y = cy;

		canvas.drawBitmap(mapb, cx, cy, null);
		drawCharacter(canvas);
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

		if (posUP == null)
		{
			calculateControlPositions(cross, ctrlA, ctrlB, canvas);
		}

		canvas.drawBitmap(cross, posCross[0], posCross[1], null);
		canvas.drawBitmap(ctrlA, posA[0], posA[1], null);
		canvas.drawBitmap(ctrlB, posB[0], posB[1], null);
	}

	private void drawCharacter(final Canvas canvas)
	{
		canvas.drawBitmap(move.getBitmap(), map0x + move.getX(),
		map0y + move.getY() - Square.getSprite().getSize() / 2, null);
	}

	@Override
	public boolean onTouch(final View v, final MotionEvent event)
	{
		final Vibrator vibrator = (Vibrator) context
		.getSystemService(Context.VIBRATOR_SERVICE);

		final int control = getControl(event);

		if (event.getAction() == MotionEvent.ACTION_DOWN
		|| event.getAction() == MotionEvent.ACTION_MOVE)
		{
			if ((control & UP) == UP
			&& (control & (DOWN | LEFT | RIGHT)) == NONE)
			{
				if ((this.control & UP) == NONE)
				{
					this.control = UP;
					vibrator.vibrate(50);
					move.setOnFinishedListener(new Runnable()
					{

						@Override
						public void run()
						{
							(move = new MoveThread()).start();
						}
					});
				}
			}
			else if ((control & DOWN) == DOWN
			&& (control & (UP | LEFT | RIGHT)) == NONE)
			{
				if ((this.control & DOWN) == NONE)
				{
					this.control = DOWN;
					vibrator.vibrate(50);
					move.setOnFinishedListener(new Runnable()
					{

						@Override
						public void run()
						{
							(move = new MoveThread()).start();
						}
					});
				}
			}
			else if ((control & LEFT) == LEFT
			&& (control & (DOWN | UP | RIGHT)) == NONE)
			{
				if ((this.control & LEFT) == NONE)
				{
					this.control = LEFT;
					vibrator.vibrate(50);
					move.setOnFinishedListener(new Runnable()
					{

						@Override
						public void run()
						{
							(move = new MoveThread()).start();
						}
					});
				}
			}
			else if ((control & RIGHT) == RIGHT
			&& (control & (DOWN | LEFT | UP)) == NONE)
			{
				if ((this.control & RIGHT) == NONE)
				{
					this.control = RIGHT;
					vibrator.vibrate(50);
					move.setOnFinishedListener(new Runnable()
					{

						@Override
						public void run()
						{
							(move = new MoveThread()).start();
						}
					});
				}
			}

			if ((control & A) == A)
			{
				if ((this.control & A) == NONE)
				{
					this.control = control;
					vibrator.vibrate(50);
				}
			}

			if ((control & B) == B)
			{
				if ((this.control & B) == NONE)
				{
					this.control = control;
					vibrator.vibrate(50);
				}
			}
		}

		if (event.getAction() == MotionEvent.ACTION_UP || control == NONE
		|| control != this.control)
		{
			this.control = NONE;
			move.stay();
			move.setOnFinishedListener(null);
		}
		return true;
	}

	private int getControl(final MotionEvent event)
	{
		int control = NONE;

		for (int i = 0; i < event.getPointerCount(); i++)
		{
			final int x = (int) event.getX(i);
			final int y = (int) event.getY(i);

			if (x >= posUP[0] && y >= posUP[1] && x <= posUP[2]
			&& y <= posUP[3])
			{
				control |= UP;
			}

			if (x >= posDOWN[0] && y >= posDOWN[1] && x <= posDOWN[2]
			&& y <= posDOWN[3])
			{
				control |= DOWN;
			}

			if (x >= posLEFT[0] && y >= posLEFT[1] && x <= posLEFT[2]
			&& y <= posLEFT[3])
			{
				control |= LEFT;
			}

			if (x >= posRIGHT[0] && y >= posRIGHT[1] && x <= posRIGHT[2]
			&& y <= posRIGHT[3])
			{
				control |= RIGHT;
			}

			if (x >= posA[0] && y >= posA[1] && x <= posA[2] && y <= posA[3])
			{
				control |= A;
			}

			if (x >= posB[0] && y >= posB[1] && x <= posB[2] && y <= posB[3])
			{
				control |= B;
			}
		}

		return control;
	}

	private class MoveThread extends Thread {

		private boolean		stopped;
		private boolean		finished;
		private int			x, y;
		private Bitmap		bitmap;
		private Runnable	onFinishedListener;

		public MoveThread()
		{
			super();
			stopped = finished = true;
			x = y = 0;
			bitmap = Player.getInstance().getBitmap(
			Player.getInstance().getOrientation());
		}

		@Override
		public void run()
		{
			stopped = finished = false;
			final int control = MapView.this.control;

			while ( ! stopped)
			{
				if ((control & UP) != NONE)
				{
					move(Player.NORTH, Player.NORTHLEFT, Player.NORTHRIGHT, 0,
					- 1, false);
				}
				else if ((control & DOWN) != NONE)
				{
					move(Player.SOUTH, Player.SOUTHLEFT, Player.SOUTHRIGHT, 0,
					1, false);
				}
				else if ((control & LEFT) != NONE)
				{
					move(Player.WEST, Player.WESTLEFT, Player.WESTRIGHT, - 1,
					0, false);
				}
				else if ((control & RIGHT) != NONE)
				{
					move(Player.EAST, Player.EASTLEFT, Player.EASTRIGHT, 1, 0,
					false);
				}
			}
			if ( ! finished)
			{
				finish();
			}
		}

		private void move(final int direction, final int left, final int right,
		final int x, final int y, final boolean trainers)
		{

			if (Player.getInstance().getOrientation() != direction)
			{
				bitmap = Player.getInstance().getBitmap(direction);
				Player.getInstance().setOrientation(direction);
				((Activity) context).runOnUiThread(new Runnable()
				{

					@Override
					public void run()
					{
						invalidate();
					}
				});
				try
				{
					Thread.sleep(200);
				}
				catch (final InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				for (int i = 0; i < Square.getSprite().getSize(); i++)
				{
					if ( ! trainers)
					{
						if (i < Square.getSprite().getSize() / 2)
						{
							bitmap = Player.getInstance().getBitmap(left);
						}
						else
						{
							bitmap = Player.getInstance().getBitmap(right);
						}
					}
					else
					{
						if (i < Square.getSprite().getSize() / 4
						|| (i > Square.getSprite().getSize() / 2 && i < 3 * Square
						.getSprite().getSize() / 4))
						{
							bitmap = Player.getInstance().getBitmap(left);
						}
						else
						{
							bitmap = Player.getInstance().getBitmap(right);
						}
					}
					this.x += x;
					this.y += y;

					((Activity) context).runOnUiThread(new Runnable()
					{

						@Override
						public void run()
						{
							invalidate();
						}
					});

					try
					{
						Thread.sleep(trainers ? 4 : 8);
					}
					catch (final InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
			bitmap = Player.getInstance().getBitmap(direction);
			Player.getInstance().setOrientation(direction);
		}

		private void finish()
		{
			Player.getInstance().setCoord_X(
			Player.getInstance().getCoord_X()
			+ Math.round((float) x / Square.getSprite().getSize()));
			Player.getInstance().setCoord_Y(
			Player.getInstance().getCoord_Y()
			+ Math.round((float) y / Square.getSprite().getSize()));
			x = 0;
			y = 0;
			finished = true;

			if (Player.getInstance().getCoord_X() == 8
			&& Player.getInstance().getCoord_Y() == 4)
			{
				context.startActivity(new Intent(context,
				BattleSceneActivity.class));
			}

			if (this.onFinishedListener != null)
			{
				this.onFinishedListener.run();
			}
		}

		public void stay()
		{
			stopped = true;
		}

		public Bitmap getBitmap()
		{
			return bitmap;
		}

		public int getX()
		{
			return Player.getInstance().getCoord_X()
			* Square.getSprite().getSize() + x;
		}

		public int getY()
		{
			return Player.getInstance().getCoord_Y()
			* Square.getSprite().getSize() + y;
		}

		public void setOnFinishedListener(final Runnable r)
		{
			this.onFinishedListener = r;
			if (finished && r != null)
			{
				r.run();
			}
		}
	}
}