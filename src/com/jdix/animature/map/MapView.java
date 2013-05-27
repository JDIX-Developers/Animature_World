package com.jdix.animature.map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.jdix.animature.BattleActivity;
import com.jdix.animature.R;
import com.jdix.animature.entities.Animature;
import com.jdix.animature.entities.Attack;
import com.jdix.animature.entities.Player;

/**
 * @author Razican (Iban Eguia)
 */
public class MapView extends View implements OnTouchListener {

	private final Map			map;
	private int					mWidth;
	private int					mHeight;
	private final Context		context;
	private int					control;
	private final MoveThread	move;

	private static int			NONE	= 0;
	private static int			UP		= 1;
	private static int			DOWN	= 2;
	private static int			LEFT	= 4;
	private static int			RIGHT	= 8;
	private static int			A		= 16;
	private static int			B		= 32;

	private int[]				posCross;
	private int[]				posA;
	private int[]				posB;
	private int[]				posUP;
	private int[]				posDOWN;
	private int[]				posLEFT;
	private int[]				posRIGHT;

	/**
	 * @param context - Context of the application
	 */
	public MapView(final Context context)
	{
		super(context);

		this.context = context;
		this.control = NONE;

		this.map = Player.getInstance().getMap();
		this.move = new MoveThread();
		setOnTouchListener(this);

		this.move.start();
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

		final int size = Square.getSprite().getSize();

		final int pX = Player.getInstance().getX() * size;
		final int pY = Player.getInstance().getY() * size;

		canvas.drawBitmap(mapb, mWidth / 2 - pX - move.getX() - size / 2,
		mHeight / 2 - pY - move.getY() - size / 2, null);
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
		canvas.drawBitmap(move.getBitmap(), (mWidth - Square.getSprite()
		.getSize()) / 2, mHeight / 2 - Square.getSprite().getSize(), null);
	}

	@Override
	public boolean onTouch(final View v, final MotionEvent event)
	{
		final Vibrator vibrator = (Vibrator) context
		.getSystemService(Context.VIBRATOR_SERVICE);

		final int lastControl = this.control;

		if (event.getAction() == MotionEvent.ACTION_DOWN
		|| event.getAction() == MotionEvent.ACTION_MOVE)
		{
			this.control = getControl(event);

			if ((getArrowControl() | getABControls()) != NONE
			&& this.control != lastControl)
			{
				vibrator.vibrate(50);
			}
		}
		else if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN)
		{
			this.control |= getControl(event, event.getActionIndex());

			if ((getArrowControl() | getABControls()) != NONE
			&& this.control != lastControl)
			{
				vibrator.vibrate(50);
			}
		}
		else if (event.getAction() == MotionEvent.ACTION_UP)
		{
			this.control = NONE;
		}
		else if ((event.getActionMasked() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_UP)
		{
			this.control &= ~ getControl(event, event.getActionIndex());
		}

		return true;
	}

	private int getControl(final MotionEvent event, final int actionIndex)
	{
		if (posUP == null)
		{
			return NONE;
		}

		int control = NONE;

		final int x = (int) event.getX(actionIndex);
		final int y = (int) event.getY(actionIndex);

		if (x >= posUP[0] && y >= posUP[1] && x <= posUP[2] && y <= posUP[3])
		{
			control = UP;
		}

		if (x >= posDOWN[0] && y >= posDOWN[1] && x <= posDOWN[2]
		&& y <= posDOWN[3])
		{
			control = DOWN;
		}

		if (x >= posLEFT[0] && y >= posLEFT[1] && x <= posLEFT[2]
		&& y <= posLEFT[3])
		{
			control = LEFT;
		}

		if (x >= posRIGHT[0] && y >= posRIGHT[1] && x <= posRIGHT[2]
		&& y <= posRIGHT[3])
		{
			control = RIGHT;
		}

		if (x >= posA[0] && y >= posA[1] && x <= posA[2] && y <= posA[3])
		{
			control = A;
		}

		if (x >= posB[0] && y >= posB[1] && x <= posB[2] && y <= posB[3])
		{
			control = B;
		}

		return control;
	}

	private int getControl(final MotionEvent event)
	{
		int control = NONE;

		for (int i = 0; i < event.getPointerCount(); i++)
		{
			control |= getControl(event, i);
		}

		return control;
	}

	/**
	 * @return The current arrow control
	 */
	public int getArrowControl()
	{
		final int arrows = MapView.this.control & ~ B & ~ A;
		if ((arrows & - arrows) == arrows)
		{
			return arrows;
		}
		return NONE;
	}

	/**
	 * @return The current A and B controls
	 */
	public int getABControls()
	{
		return NONE | (MapView.this.control & A) | (MapView.this.control & B);
	}

	/**
	 * Continues the movement of the map
	 */
	public void continueMoving()
	{
		move.continueMoving();
	}

	private class MoveThread extends Thread {

		private boolean	stopped;
		private boolean	finished;
		private int		x;
		private int		y;
		private Bitmap	bitmap;
		private int		control;
		private boolean	inBattle;

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
			poll();
		}

		/**
		 * Does the next move
		 */
		public void nextMove()
		{
			bitmap = Player.getInstance().getBitmap(
			Player.getInstance().getOrientation());

			if ((control & UP) != NONE)
			{
				move(Player.NORTH, Player.NORTHLEFT, Player.NORTHRIGHT, 0, - 1,
				(control & B) == B);
			}
			else if ((control & DOWN) != NONE)
			{
				move(Player.SOUTH, Player.SOUTHLEFT, Player.SOUTHRIGHT, 0, 1,
				(control & B) == B);
			}
			else if ((control & LEFT) != NONE)
			{
				move(Player.WEST, Player.WESTLEFT, Player.WESTRIGHT, - 1, 0,
				(control & B) == B);
			}
			else if ((control & RIGHT) != NONE)
			{
				move(Player.EAST, Player.EASTLEFT, Player.EASTRIGHT, 1, 0,
				(control & B) == B);
			}
		}

		private Square next()
		{
			return map.getSquareAt(nextX(), nextY());
		}

		private void move(final int direction, final int left, final int right,
		final int x, final int y, final boolean trainers)
		{
			finished = false;

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

					if (next() != null
					&& (next().isOfType(Square.WALKABLE) || map.getLinkAt(
					nextX(), nextY()) != null))
					{
						this.x += x;
						this.y += y;
					}

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
						Thread.sleep(trainers ? 4 : 7);
					}
					catch (final InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
			bitmap = Player.getInstance().getBitmap(direction);
			Player.getInstance().setOrientation(direction);
			finish();
		}

		private byte nextX()
		{
			final Player p = Player.getInstance();
			byte nextX = 0;
			switch (p.getOrientation())
			{
				case Player.NORTH:
					nextX = (byte) p.getX();
				break;
				case Player.WEST:
					nextX = (byte) (p.getX() - 1);
				break;
				case Player.SOUTH:
					nextX = (byte) p.getX();
				break;
				case Player.EAST:
					nextX = (byte) (p.getX() + 1);
			}
			return nextX;
		}

		private byte nextY()
		{
			final Player p = Player.getInstance();
			byte nextY = 0;
			switch (p.getOrientation())
			{
				case Player.NORTH:
					nextY = (byte) (p.getY() - 1);
				break;
				case Player.WEST:
					nextY = (byte) p.getY();
				break;
				case Player.SOUTH:
					nextY = (byte) (p.getY() + 1);
				break;
				case Player.EAST:
					nextY = (byte) p.getY();
			}
			return nextY;
		}

		private void finish()
		{
			Player.getInstance().setX(
			Player.getInstance().getX()
			+ Math.round((float) x / Square.getSprite().getSize()));
			Player.getInstance().setY(
			Player.getInstance().getY()
			+ Math.round((float) y / Square.getSprite().getSize()));
			x = 0;
			y = 0;
			finished = true;

			startEvents();
		}

		private void poll()
		{
			do
			{
				try
				{
					Thread.sleep(50);
				}
				catch (final InterruptedException e)
				{
					e.printStackTrace();
				}

				stopped = inBattle || getArrowControl() == NONE;
				while ( ! stopped && finished)
				{
					this.control = getArrowControl() | getABControls();
					nextMove();
					stopped = inBattle || getArrowControl() == NONE;
				}
			}
			while (true);
		}

		private void startEvents()
		{
			// TODO Start events for the current square
			final Player p = Player.getInstance();
			final Square sq = map.getSquareAt((byte) p.getX(), (byte) p.getY());

			Link l;
			if ((l = map.getLinkAt((byte) p.getX(), (byte) p.getY())) != null)
			{
				// TODO new map if needed
				p.setX(l.getX());
				p.setY(l.getY());

				try
				{
					Thread.sleep(250);
				}
				catch (final InterruptedException e)
				{
					e.printStackTrace();
				}

				((Activity) context).runOnUiThread(new Runnable()
				{

					@Override
					public void run()
					{
						invalidate();
					}
				});
			}

			else if (sq.isOfType(Square.GRASSANIM))
			{
				final Animature wildAnim = new Animature(0, 10, 0, null,
				Animature.OK, Attack.load(1, context), Attack.load(1, context)
				.getMaxPP(), Attack.load(2, context), Attack.load(2, context)
				.getMaxPP(), null, 0, null, 0, 1, 0, Animature.getMaxHealth(10,
				1, context), context);

				final Intent intent = new Intent(context, BattleActivity.class);
				final Bundle b = new Bundle();
				b.putSerializable("wild_animature", wildAnim);
				intent.putExtras(b);
				((Activity) context).startActivityForResult(intent, 1);

				inBattle = true;

				// if (Math.random() < 0.7)
				// {
				// // TODO start battle
				// }
			}

		}

		public void continueMoving()
		{
			inBattle = false;
		}

		public Bitmap getBitmap()
		{
			return bitmap;
		}

		public int getX()
		{
			return x;
		}

		public int getY()
		{
			return y;
		}
	}
}