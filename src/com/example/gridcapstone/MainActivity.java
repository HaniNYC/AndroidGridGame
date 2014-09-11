package com.example.gridcapstone;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnClickListener {
	/*
	*/

	GridView selectedCircle;
	public Integer[] mThumbIds = { R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.yellow3, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.green1, R.drawable.yellow3,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.yellow3, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.yellow3, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.yellow3, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.blue2,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.yellow3, R.drawable.yellow3,
			R.drawable.yellow3, R.drawable.blue2, R.drawable.yellow3,
			R.drawable.blue2, R.drawable.blue2, };

	@Override
	protected void onStart() {
		super.onStart();

		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog);
		dialog.setTitle("Game Rule");

		Button button = (Button) dialog.findViewById(R.id.dialogbutton);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// MediaPlayer mp = MediaPlayer.create(MainActivity.this,
				// R.raw.buttonsound);
				// mp.start();
				dialog.dismiss();

			}

		});

		dialog.show();
		// dialog.cancel();
	}

	@Override
	public void onDestroy() {

		super.onDestroy();
	}

	// this on onStart is an idea to let the user choose level of the game
	public static int count = 0;
	private Random mRandom = new Random();
	private static final int NUM_BACKGROUNDS = 10;
	private ImageButton[] imageButtons = new ImageButton[122];
	private Button goodGuy;
	private Button badGuy;
	private Button play_again;

	private GridLayout gridlayout;
	private Handler clock = new Handler();
	private Handler handler = new Handler();
	private Handler badGuyHandler = new Handler();
	private Handler messageDelayHandler = new Handler();
	private Runnable updateTimeTask;
	private Runnable BoardColor;

	private boolean _colock;
	private int[] GoodGuyPos = new int[1];
	private int[] BadGuyPos = new int[1];
	private boolean handlerStop;
	private boolean gameOn;
	private boolean messageD;
	private boolean badGuyPlay;
	private boolean xCord = false;
	private boolean YCordin = false;
	private boolean xMove = false;
	private boolean yMove = false;
	private boolean badOption = false;
	private Long j;
	private Long start;
	private Long go;
	private Integer incrementor = 0;
	private double badGuyDis;

	private int numfs = -1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// builder.setMessage("Have Fun!").create().show();

		// ((DialogInterface) builder).dismiss();
		/*
		 * The main class will change background randomly
		 */

		final int[] drawablearray = new int[] { R.drawable.background,
				R.drawable.planet2, R.drawable.ocean1, R.drawable.homer_ok,
				R.drawable.back_g_2, R.drawable.background7,
				R.drawable.homer_ok, R.drawable.back_g_3 };
		new Handler().postDelayed(new Runnable() {
			public void run() {

				if (count < drawablearray.length) {

					MainActivity.this
							.getWindow()
							.setBackgroundDrawableResource(drawablearray[count]);

					count++; // <<< increment counter here
				} else {
					// reset counter here
					count = 0;
				}

			}
		}, 5000);

		/*
		 * int res; int i = mRandom.nextInt(NUM_BACKGROUNDS); switch (i) { case
		 * 0: res = R.drawable.planet2; break; case 1: res = R.drawable.planet3;
		 * break; case 2: res = R.drawable.planet1; break; case 3: res =
		 * R.drawable.back_g_3; break; case 4: res = R.drawable.background;
		 * break; default: throw new IllegalArgumentException("oops?"); } //
		 * i.setBackgroundResource(res);
		 */
		gridlayout = (GridLayout) findViewById(R.id.GridLayout1);

		addListenerOnButtons();
		handlerStop = false;
		messageD = false;

		goodGuy = (Button) findViewById(R.id.goodguy);
		badGuy = (Button) findViewById(R.id.badguy);

		play_again = (Button) findViewById(R.id.play_again);

		play_again.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				if (!gameOn)
					recreate();

			}

		});
		GoodGuyPos = new int[] { 1, 11 };
		BadGuyPos = new int[] { 11, 11 };
		colorChanger();
		start = System.currentTimeMillis();
		j = 0L;
		go = 0L;
		_colock = false;

		gameOn = true;
		handler.post(gridChanger);
		badGuyHandler.post(badguyR);

	}

	GridUpdater gridChanger = new GridUpdater();
	badguyR badguyR = new badguyR();

	private class GridUpdater implements Runnable {
		public void run() {
			colorChanger();
			if (gameOn)
				handler.postDelayed(gridChanger, 1500);
		}
	}

	private class badguyR implements Runnable {
		public void run() {
			badGuy();
			if (gameOn)
				handler.postDelayed(badguyR, 800);
		}
	}

	public String getButtonID(int button) {
		return "button" + button;
	}

	public void colorChanger() {
		if (gameOn) {
			final double Pyellow = 0.40;
			Random randomGenerator = new Random();
			int goodGuyButton = idButton(GoodGuyPos);
			int badGuyplace = idButton(BadGuyPos);
			for (int i = 1; i <= 121; i++) {
				if (i != 61 && i != goodGuyButton && i != badGuyplace) {
					int randomInt = randomGenerator.nextInt(75) + 1;
					if (randomInt <= 100 * Pyellow)
						setButtonColor(i, "yellow");
					else
						setButtonColor(i, "blue");
				}

				if (i == 61)
					setButtonColor(i, "green");

				if (i == badGuyplace)
					setButtonColor(badGuyplace, "blue");
				if (i == goodGuyButton)
					setButtonColor(goodGuyButton, "yellow");
			}
		}
	}

	public int[] buttonPos(int id) {
		int row = (int) Math.ceil(id / 11.0);
		int column = id % 11;
		if (column == 0)
			column = 11;
		int[] coordinates = { column, row };
		return coordinates;
	}

	public int idButton(int[] coordinates) {
		return coordinates[1] * 11 - (11 - coordinates[0]);
	}

	public boolean valid(int[] clickedCoordinates) {
		if (Math.abs(clickedCoordinates[0] - GoodGuyPos[0]) == 1
				&& clickedCoordinates[1] == GoodGuyPos[1]) {
			if (!(imageButtons[idButton(clickedCoordinates)].getBackground()
					.getConstantState().equals(getResources().getDrawable(
					R.drawable.blue2).getConstantState()))) {
				return true;
			}
		} else if (Math.abs(clickedCoordinates[1] - GoodGuyPos[1]) == 1
				&& clickedCoordinates[0] == GoodGuyPos[0]) {
			if (!(imageButtons[idButton(clickedCoordinates)].getBackground()
					.getConstantState().equals(getResources().getDrawable(
					R.drawable.blue2).getConstantState()))) {
				return true;
			}
		}
		return false;
	}

	public void GoodG_Run(int[] newCoordinates) {
		if (gameOn) {
			GridLayout.Spec rowSpec = GridLayout.spec(newCoordinates[1]);
			GridLayout.Spec columnSpec = GridLayout.spec(newCoordinates[0]);
			GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(
					rowSpec, columnSpec);
			layoutParams.width = 29;
			layoutParams.height = 29;
			goodGuy.setLayoutParams(layoutParams);
			if (idButton(newCoordinates) == 61)

				displayWinMessage();

			MediaPlayer mp = MediaPlayer.create(MainActivity.this,
					R.raw.buttonbump);
			mp.start();
			// mp.stop();
			// mp.reset();

		}
	}

	public void displayWinMessage() {

		gameOn = false;
		Toast.makeText(MainActivity.this, "YOU WON!", Toast.LENGTH_SHORT)
				.show();

	}

	public void displayLoseMessage() {

		gameOn = false;
		Toast.makeText(MainActivity.this, "YOU LOSE", Toast.LENGTH_SHORT)
				.show();

	}

	public void displayRestartMessage() {

	}

	public void setBadGuyPos(int[] newCoordinates) {
		if (gameOn) {
			GridLayout.Spec rowSpec = GridLayout.spec(newCoordinates[1]);
			GridLayout.Spec columnSpec = GridLayout.spec(newCoordinates[0]);
			GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(
					rowSpec, columnSpec);
			layoutParams.width = 29;
			layoutParams.height = 29;
			badGuy.setLayoutParams(layoutParams);
		}
	}

	public void badGuyLeft() {
		BadGuyPos[0] = BadGuyPos[0] - 1;
		setBadGuyPos(BadGuyPos);
		badGuyPlay = true;
	}

	public void badGuyRight() {
		BadGuyPos[0] = BadGuyPos[0] + 1;
		setBadGuyPos(BadGuyPos);
		badGuyPlay = true;
	}

	public void badGuyUp() {
		BadGuyPos[1] = BadGuyPos[1] - 1;
		setBadGuyPos(BadGuyPos);
		badGuyPlay = true;
	}

	public void badGuyDown() {
		BadGuyPos[1] = BadGuyPos[1] + 1;
		setBadGuyPos(BadGuyPos);
		badGuyPlay = true;
	}

	public void badGuy() {
		badGuyPlay = false;
		badOption = false;
		badGuyDis = distance(BadGuyPos);
		int[] tempCoordinatesUp = new int[2];
		tempCoordinatesUp[0] = BadGuyPos[0];
		tempCoordinatesUp[1] = BadGuyPos[1] - 1;

		int[] _PosTem = new int[2];
		_PosTem[0] = BadGuyPos[0];
		_PosTem[1] = BadGuyPos[1] + 1;

		int[] tempCoordinatesLeft = new int[2];
		tempCoordinatesLeft[0] = BadGuyPos[0] - 1;
		tempCoordinatesLeft[1] = BadGuyPos[1];

		int[] tempCoordinatesRight = new int[2];
		tempCoordinatesRight[0] = BadGuyPos[0] + 1;
		tempCoordinatesRight[1] = BadGuyPos[1];

		double[] distances = new double[4];
		distances[0] = distance(tempCoordinatesUp);
		distances[1] = distance(_PosTem);
		distances[2] = distance(tempCoordinatesLeft);
		distances[3] = distance(tempCoordinatesRight);

		for (int x = 0; x < 4; x++) {
			distances[x] = Math.ceil(distances[x]);
		}

		int[] _pos = _num(distances);

		YCordin = false;
		xCord = false;
		yMove = false;
		xMove = false;
		int dx = BadGuyPos[0] - GoodGuyPos[0];
		int dy = BadGuyPos[1] - GoodGuyPos[1];

		if (_pos[1] == -1) {
			switch (_pos[0]) {
			case 0:
				if (imageButtons[idButton(tempCoordinatesUp)]
						.getBackground()
						.getConstantState()
						.equals(getResources().getDrawable(R.drawable.blue2)
								.getConstantState())
						|| Arrays.equals(tempCoordinatesUp, GoodGuyPos)) {
					badGuyUp();
					if (Arrays.equals(BadGuyPos, GoodGuyPos))
						displayLoseMessage();
				}

				break;
			case 1:
				if (imageButtons[idButton(_PosTem)]
						.getBackground()
						.getConstantState()
						.equals(getResources().getDrawable(R.drawable.blue2)
								.getConstantState())
						|| Arrays.equals(_PosTem, GoodGuyPos)) {
					badGuyDown();
					if (Arrays.equals(BadGuyPos, GoodGuyPos))
						displayLoseMessage();
				}

				break;
			case 2:
				if (imageButtons[idButton(tempCoordinatesLeft)]
						.getBackground()
						.getConstantState()
						.equals(getResources().getDrawable(R.drawable.blue2)
								.getConstantState())
						|| Arrays.equals(tempCoordinatesLeft, GoodGuyPos)) {
					badGuyLeft();
					if (Arrays.equals(BadGuyPos, GoodGuyPos))
						displayLoseMessage();
				}

				break;
			case 3:
				if (imageButtons[idButton(tempCoordinatesRight)]
						.getBackground()
						.getConstantState()
						.equals(getResources().getDrawable(R.drawable.blue2)
								.getConstantState())
						|| Arrays.equals(tempCoordinatesRight, GoodGuyPos)) {
					badGuyRight();
					if (Arrays.equals(BadGuyPos, GoodGuyPos))
						displayLoseMessage();
				}
				break;
			}
		} else {
			badOption = true;
			switch (RandOne(_pos)) {
			case 0:
				if (imageButtons[idButton(tempCoordinatesUp)]
						.getBackground()
						.getConstantState()
						.equals(getResources().getDrawable(R.drawable.blue2)
								.getConstantState())
						|| Arrays.equals(tempCoordinatesUp, GoodGuyPos)) {
					badGuyUp();
					if (Arrays.equals(BadGuyPos, GoodGuyPos))
						displayLoseMessage();
				}

				break;
			case 1:
				if (imageButtons[idButton(_PosTem)]
						.getBackground()
						.getConstantState()
						.equals(getResources().getDrawable(R.drawable.blue2)
								.getConstantState())
						|| Arrays.equals(_PosTem, GoodGuyPos)) {
					badGuyDown();
					if (Arrays.equals(BadGuyPos, GoodGuyPos))
						displayLoseMessage();
				}

				break;
			case 2:
				if (imageButtons[idButton(tempCoordinatesLeft)]
						.getBackground()
						.getConstantState()
						.equals(getResources().getDrawable(R.drawable.blue2)
								.getConstantState())
						|| Arrays.equals(tempCoordinatesLeft, GoodGuyPos)) {
					badGuyLeft();
					if (Arrays.equals(BadGuyPos, GoodGuyPos))
						displayLoseMessage();
				}

				break;
			case 3:
				if (imageButtons[idButton(tempCoordinatesRight)]
						.getBackground()
						.getConstantState()
						.equals(getResources().getDrawable(R.drawable.blue2)
								.getConstantState())
						|| Arrays.equals(tempCoordinatesRight, GoodGuyPos)) {
					badGuyRight();
					if (Arrays.equals(BadGuyPos, GoodGuyPos))
						displayLoseMessage();
				}
				break;
			}
		}
		if (!badGuyPlay && badOption) {
			switch (_pos[Math.abs(numfs - 1)]) {
			case 0:
				if (imageButtons[idButton(tempCoordinatesUp)]
						.getBackground()
						.getConstantState()
						.equals(getResources().getDrawable(R.drawable.blue2)
								.getConstantState())
						|| Arrays.equals(tempCoordinatesUp, GoodGuyPos)) {
					badGuyUp();
					if (Arrays.equals(BadGuyPos, GoodGuyPos))
						displayLoseMessage();
				}

				break;
			case 1:
				if (imageButtons[idButton(_PosTem)]
						.getBackground()
						.getConstantState()
						.equals(getResources().getDrawable(R.drawable.blue2)
								.getConstantState())
						|| Arrays.equals(_PosTem, GoodGuyPos)) {
					badGuyDown();
					if (Arrays.equals(BadGuyPos, GoodGuyPos))
						displayLoseMessage();
				}

				break;
			case 2:
				if (imageButtons[idButton(tempCoordinatesLeft)]
						.getBackground()
						.getConstantState()
						.equals(getResources().getDrawable(R.drawable.blue2)
								.getConstantState())
						|| Arrays.equals(tempCoordinatesLeft, GoodGuyPos)) {
					badGuyLeft();
					if (Arrays.equals(BadGuyPos, GoodGuyPos))
						displayLoseMessage();
				}

				break;
			case 3:
				if (imageButtons[idButton(tempCoordinatesRight)]
						.getBackground()
						.getConstantState()
						.equals(getResources().getDrawable(R.drawable.blue2)
								.getConstantState())
						|| Arrays.equals(tempCoordinatesRight, GoodGuyPos)) {
					badGuyRight();
					if (Arrays.equals(BadGuyPos, GoodGuyPos))
						displayLoseMessage();
				}
				break;
			}

		}

	}

	int RandOne(int[] array) {
		Random randomGenerator = new Random();
		int randomNumber = randomGenerator.nextInt(100) + 1;
		if (randomNumber < 40) {

			numfs = 0;
			return array[0];
		} else {

			numfs = 1;
			return array[1];
		}
	}

	int[] _num(double[] array) {
		int i = 1;
		int minIndex1 = 0;
		int minIndex2 = -1;
		int[] minIndexes = new int[] { -1, -1 };
		double min = array[0];
		while (i < array.length) {
			if (array[i] < min) {
				min = array[i];
				minIndex1 = i;
			}
			i++;
		}
		i = minIndex1 + 1;
		while (i < array.length) {
			if (array[i] == min) {
				minIndex2 = i;
			}
			i++;
		}
		minIndexes[0] = minIndex1;
		minIndexes[1] = minIndex2;
		return minIndexes;
	}

	double distance(int[] coordinates) {
		return Math.sqrt(Math.pow((GoodGuyPos[0] - coordinates[0]), 2)
				+ Math.pow((GoodGuyPos[1] - coordinates[1]), 2));
	}

	public void setButtonColor(int button, String color) {

		if (color == "yellow")
			imageButtons[button].setBackgroundResource(R.drawable.yellow3);
		else if (color == "blue")
			imageButtons[button].setBackgroundResource(R.drawable.blue2);
		else if (color == "green")
			imageButtons[button].setBackgroundResource(R.drawable.green1);
	}

	public void addListenerOnButtons() {
		for (int i = 1; i <= 121; i++) {
			int resID = getResources().getIdentifier(getButtonID(i), "id",
					getPackageName());
			imageButtons[i] = (ImageButton) findViewById(resID);
			imageButtons[i].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					/* Hani: Why not using a for loop instead? */

					switch (v.getId()) {
					case R.id.button1:
						if (valid(buttonPos(1))) {
							GoodG_Run(buttonPos(1));
							GoodGuyPos = buttonPos(1);
						}
						break;
					case R.id.button2:
						if (valid(buttonPos(2))) {
							GoodG_Run(buttonPos(2));
							GoodGuyPos = buttonPos(2);
						}
						break;
					case R.id.button3:
						if (valid(buttonPos(3))) {
							GoodG_Run(buttonPos(3));
							GoodGuyPos = buttonPos(3);
						}
						break;
					case R.id.button4:
						if (valid(buttonPos(4))) {
							GoodG_Run(buttonPos(4));
							GoodGuyPos = buttonPos(4);
						}
						break;
					case R.id.button5:
						if (valid(buttonPos(5))) {
							GoodG_Run(buttonPos(5));
							GoodGuyPos = buttonPos(5);
						}
						break;
					case R.id.button6:
						if (valid(buttonPos(6))) {
							GoodG_Run(buttonPos(6));
							GoodGuyPos = buttonPos(6);
						}
						break;
					case R.id.button7:
						if (valid(buttonPos(7))) {
							GoodG_Run(buttonPos(7));
							GoodGuyPos = buttonPos(7);
						}
						break;
					case R.id.button8:
						if (valid(buttonPos(8))) {
							GoodG_Run(buttonPos(8));
							GoodGuyPos = buttonPos(8);
						}
						break;
					case R.id.button9:
						if (valid(buttonPos(9))) {
							GoodG_Run(buttonPos(9));
							GoodGuyPos = buttonPos(9);
						}
						break;
					case R.id.button10:
						if (valid(buttonPos(10))) {
							GoodG_Run(buttonPos(10));
							GoodGuyPos = buttonPos(10);
						}
						break;
					case R.id.button11:
						if (valid(buttonPos(11))) {
							GoodG_Run(buttonPos(11));
							GoodGuyPos = buttonPos(11);
						}
						break;
					case R.id.button12:
						if (valid(buttonPos(12))) {
							GoodG_Run(buttonPos(12));
							GoodGuyPos = buttonPos(12);
						}
						break;
					case R.id.button13:
						if (valid(buttonPos(13))) {
							GoodG_Run(buttonPos(13));
							GoodGuyPos = buttonPos(13);
						}
						break;
					case R.id.button14:
						if (valid(buttonPos(14))) {
							GoodG_Run(buttonPos(14));
							GoodGuyPos = buttonPos(14);
						}
						break;
					case R.id.button15:
						if (valid(buttonPos(15))) {
							GoodG_Run(buttonPos(15));
							GoodGuyPos = buttonPos(15);
						}
						break;
					case R.id.button16:
						if (valid(buttonPos(16))) {
							GoodG_Run(buttonPos(16));
							GoodGuyPos = buttonPos(16);
						}
						break;
					case R.id.button17:
						if (valid(buttonPos(17))) {
							GoodG_Run(buttonPos(17));
							GoodGuyPos = buttonPos(17);
						}
						break;
					case R.id.button18:
						if (valid(buttonPos(18))) {
							GoodG_Run(buttonPos(18));
							GoodGuyPos = buttonPos(18);
						}
						break;
					case R.id.button19:
						if (valid(buttonPos(19))) {
							GoodG_Run(buttonPos(19));
							GoodGuyPos = buttonPos(19);
						}
						break;
					case R.id.button20:
						if (valid(buttonPos(20))) {
							GoodG_Run(buttonPos(20));
							GoodGuyPos = buttonPos(20);
						}
						break;
					case R.id.button21:
						if (valid(buttonPos(21))) {
							GoodG_Run(buttonPos(21));
							GoodGuyPos = buttonPos(21);
						}
						break;
					case R.id.button22:
						if (valid(buttonPos(22))) {
							GoodG_Run(buttonPos(22));
							GoodGuyPos = buttonPos(22);
						}
						break;
					case R.id.button23:
						if (valid(buttonPos(23))) {
							GoodG_Run(buttonPos(23));
							GoodGuyPos = buttonPos(23);
						}
						break;
					case R.id.button24:
						if (valid(buttonPos(24))) {
							GoodG_Run(buttonPos(24));
							GoodGuyPos = buttonPos(24);
						}
						break;
					case R.id.button25:
						if (valid(buttonPos(25))) {
							GoodG_Run(buttonPos(25));
							GoodGuyPos = buttonPos(25);
						}
						break;
					case R.id.button26:
						if (valid(buttonPos(26))) {
							GoodG_Run(buttonPos(26));
							GoodGuyPos = buttonPos(26);
						}
						break;
					case R.id.button27:
						if (valid(buttonPos(27))) {
							GoodG_Run(buttonPos(27));
							GoodGuyPos = buttonPos(27);
						}
						break;
					case R.id.button28:
						if (valid(buttonPos(28))) {
							GoodG_Run(buttonPos(28));
							GoodGuyPos = buttonPos(28);
						}
						break;
					case R.id.button29:
						if (valid(buttonPos(29))) {
							GoodG_Run(buttonPos(29));
							GoodGuyPos = buttonPos(29);
						}
						break;
					case R.id.button30:
						if (valid(buttonPos(30))) {
							GoodG_Run(buttonPos(30));
							GoodGuyPos = buttonPos(30);
						}
						break;
					case R.id.button31:
						if (valid(buttonPos(31))) {
							GoodG_Run(buttonPos(31));
							GoodGuyPos = buttonPos(31);
						}
						break;
					case R.id.button32:
						if (valid(buttonPos(32))) {
							GoodG_Run(buttonPos(32));
							GoodGuyPos = buttonPos(32);
						}
						break;
					case R.id.button33:
						if (valid(buttonPos(33))) {
							GoodG_Run(buttonPos(33));
							GoodGuyPos = buttonPos(33);
						}
						break;
					case R.id.button34:
						if (valid(buttonPos(34))) {
							GoodG_Run(buttonPos(34));
							GoodGuyPos = buttonPos(34);
						}
						break;
					case R.id.button35:
						if (valid(buttonPos(35))) {
							GoodG_Run(buttonPos(35));
							GoodGuyPos = buttonPos(35);
						}
						break;
					case R.id.button36:
						if (valid(buttonPos(36))) {
							GoodG_Run(buttonPos(36));
							GoodGuyPos = buttonPos(36);
						}
						break;
					case R.id.button37:
						if (valid(buttonPos(37))) {
							GoodG_Run(buttonPos(37));
							GoodGuyPos = buttonPos(37);
						}
						break;
					case R.id.button38:
						if (valid(buttonPos(38))) {
							GoodG_Run(buttonPos(38));
							GoodGuyPos = buttonPos(38);
						}
						break;
					case R.id.button39:
						if (valid(buttonPos(39))) {
							GoodG_Run(buttonPos(39));
							GoodGuyPos = buttonPos(39);
						}
						break;
					case R.id.button40:
						if (valid(buttonPos(40))) {
							GoodG_Run(buttonPos(40));
							GoodGuyPos = buttonPos(40);
						}
						break;
					case R.id.button41:
						if (valid(buttonPos(41))) {
							GoodG_Run(buttonPos(41));
							GoodGuyPos = buttonPos(41);
						}
						break;
					case R.id.button42:
						if (valid(buttonPos(42))) {
							GoodG_Run(buttonPos(42));
							GoodGuyPos = buttonPos(42);
						}
						break;
					case R.id.button43:
						if (valid(buttonPos(43))) {
							GoodG_Run(buttonPos(43));
							GoodGuyPos = buttonPos(43);
						}
						break;
					case R.id.button44:
						if (valid(buttonPos(44))) {
							GoodG_Run(buttonPos(44));
							GoodGuyPos = buttonPos(44);
						}
						break;
					case R.id.button45:
						if (valid(buttonPos(45))) {
							GoodG_Run(buttonPos(45));
							GoodGuyPos = buttonPos(45);
						}
						break;
					case R.id.button46:
						if (valid(buttonPos(46))) {
							GoodG_Run(buttonPos(46));
							GoodGuyPos = buttonPos(46);
						}
						break;
					case R.id.button47:
						if (valid(buttonPos(47))) {
							GoodG_Run(buttonPos(47));
							GoodGuyPos = buttonPos(47);
						}
						break;
					case R.id.button48:
						if (valid(buttonPos(48))) {
							GoodG_Run(buttonPos(48));
							GoodGuyPos = buttonPos(48);
						}
						break;
					case R.id.button49:
						if (valid(buttonPos(49))) {
							GoodG_Run(buttonPos(49));
							GoodGuyPos = buttonPos(49);
						}
						break;
					case R.id.button50:
						if (valid(buttonPos(50))) {
							GoodG_Run(buttonPos(50));
							GoodGuyPos = buttonPos(50);
						}
						break;
					case R.id.button51:
						if (valid(buttonPos(51))) {
							GoodG_Run(buttonPos(51));
							GoodGuyPos = buttonPos(51);
						}
						break;
					case R.id.button52:
						if (valid(buttonPos(52))) {
							GoodG_Run(buttonPos(52));
							GoodGuyPos = buttonPos(52);
						}
						break;
					case R.id.button53:
						if (valid(buttonPos(53))) {
							GoodG_Run(buttonPos(53));
							GoodGuyPos = buttonPos(53);
						}
						break;
					case R.id.button54:
						if (valid(buttonPos(54))) {
							GoodG_Run(buttonPos(54));
							GoodGuyPos = buttonPos(54);
						}
						break;
					case R.id.button55:
						if (valid(buttonPos(55))) {
							GoodG_Run(buttonPos(55));
							GoodGuyPos = buttonPos(55);
						}
						break;
					case R.id.button56:
						if (valid(buttonPos(56))) {
							GoodG_Run(buttonPos(56));
							GoodGuyPos = buttonPos(56);
						}
						break;
					case R.id.button57:
						if (valid(buttonPos(57))) {
							GoodG_Run(buttonPos(57));
							GoodGuyPos = buttonPos(57);
						}
						break;
					case R.id.button58:
						if (valid(buttonPos(58))) {
							GoodG_Run(buttonPos(58));
							GoodGuyPos = buttonPos(58);
						}
						break;
					case R.id.button59:
						if (valid(buttonPos(59))) {
							GoodG_Run(buttonPos(59));
							GoodGuyPos = buttonPos(59);
						}
						break;
					case R.id.button60:
						if (valid(buttonPos(60))) {
							GoodG_Run(buttonPos(60));
							GoodGuyPos = buttonPos(60);
						}
						break;
					case R.id.button61:
						if (valid(buttonPos(61))) {
							MediaPlayer mp = MediaPlayer.create(
									MainActivity.this, R.raw.buttonsound);
							mp.start();
							// mp.stop();
							// mp.reset();

							GoodG_Run(buttonPos(61));
							GoodGuyPos = buttonPos(61);
						}
						break;
					case R.id.button62:
						if (valid(buttonPos(62))) {
							GoodG_Run(buttonPos(62));
							GoodGuyPos = buttonPos(62);
						}
						break;
					case R.id.button63:
						if (valid(buttonPos(63))) {
							GoodG_Run(buttonPos(63));
							GoodGuyPos = buttonPos(63);
						}
						break;
					case R.id.button64:
						if (valid(buttonPos(64))) {
							GoodG_Run(buttonPos(64));
							GoodGuyPos = buttonPos(64);
						}
						break;
					case R.id.button65:
						if (valid(buttonPos(65))) {
							GoodG_Run(buttonPos(65));
							GoodGuyPos = buttonPos(65);
						}
						break;
					case R.id.button66:
						if (valid(buttonPos(66))) {
							GoodG_Run(buttonPos(66));
							GoodGuyPos = buttonPos(66);
						}
						break;
					case R.id.button67:
						if (valid(buttonPos(67))) {
							GoodG_Run(buttonPos(67));
							GoodGuyPos = buttonPos(67);
						}
						break;
					case R.id.button68:
						if (valid(buttonPos(68))) {
							GoodG_Run(buttonPos(68));
							GoodGuyPos = buttonPos(68);
						}
						break;
					case R.id.button69:
						if (valid(buttonPos(69))) {
							GoodG_Run(buttonPos(69));
							GoodGuyPos = buttonPos(69);
						}
						break;
					case R.id.button70:
						if (valid(buttonPos(70))) {
							GoodG_Run(buttonPos(70));
							GoodGuyPos = buttonPos(70);
						}
						break;
					case R.id.button71:
						if (valid(buttonPos(71))) {
							GoodG_Run(buttonPos(71));
							GoodGuyPos = buttonPos(71);
						}
						break;
					case R.id.button72:
						if (valid(buttonPos(72))) {
							GoodG_Run(buttonPos(72));
							GoodGuyPos = buttonPos(72);
						}
						break;
					case R.id.button73:
						if (valid(buttonPos(73))) {
							GoodG_Run(buttonPos(73));
							GoodGuyPos = buttonPos(73);
						}
						break;
					case R.id.button74:
						if (valid(buttonPos(74))) {
							GoodG_Run(buttonPos(74));
							GoodGuyPos = buttonPos(74);
						}
						break;
					case R.id.button75:
						if (valid(buttonPos(75))) {
							GoodG_Run(buttonPos(75));
							GoodGuyPos = buttonPos(75);
						}
						break;
					case R.id.button76:
						if (valid(buttonPos(76))) {
							GoodG_Run(buttonPos(76));
							GoodGuyPos = buttonPos(76);
						}
						break;
					case R.id.button77:
						if (valid(buttonPos(77))) {
							GoodG_Run(buttonPos(77));
							GoodGuyPos = buttonPos(77);
						}
						break;
					case R.id.button78:
						if (valid(buttonPos(78))) {
							GoodG_Run(buttonPos(78));
							GoodGuyPos = buttonPos(78);
						}
						break;
					case R.id.button79:
						if (valid(buttonPos(79))) {
							GoodG_Run(buttonPos(79));
							GoodGuyPos = buttonPos(79);
						}
						break;
					case R.id.button80:
						if (valid(buttonPos(80))) {
							GoodG_Run(buttonPos(80));
							GoodGuyPos = buttonPos(80);
						}
						break;
					case R.id.button81:
						if (valid(buttonPos(81))) {
							GoodG_Run(buttonPos(81));
							GoodGuyPos = buttonPos(81);
						}
						break;
					case R.id.button82:
						if (valid(buttonPos(82))) {
							GoodG_Run(buttonPos(82));
							GoodGuyPos = buttonPos(82);
						}
						break;
					case R.id.button83:
						if (valid(buttonPos(83))) {
							GoodG_Run(buttonPos(83));
							GoodGuyPos = buttonPos(83);
						}
						break;
					case R.id.button84:
						if (valid(buttonPos(84))) {
							GoodG_Run(buttonPos(84));
							GoodGuyPos = buttonPos(84);
						}
						break;
					case R.id.button85:
						if (valid(buttonPos(85))) {
							GoodG_Run(buttonPos(85));
							GoodGuyPos = buttonPos(85);
						}
						break;
					case R.id.button86:
						if (valid(buttonPos(86))) {
							GoodG_Run(buttonPos(86));
							GoodGuyPos = buttonPos(86);
						}
						break;
					case R.id.button87:
						if (valid(buttonPos(87))) {
							GoodG_Run(buttonPos(87));
							GoodGuyPos = buttonPos(87);
						}
						break;
					case R.id.button88:
						if (valid(buttonPos(88))) {
							GoodG_Run(buttonPos(88));
							GoodGuyPos = buttonPos(88);
						}
						break;
					case R.id.button89:
						if (valid(buttonPos(89))) {
							GoodG_Run(buttonPos(89));
							GoodGuyPos = buttonPos(89);
						}
						break;
					case R.id.button90:
						if (valid(buttonPos(90))) {
							GoodG_Run(buttonPos(90));
							GoodGuyPos = buttonPos(90);
						}
						break;
					case R.id.button91:
						if (valid(buttonPos(91))) {
							GoodG_Run(buttonPos(91));
							GoodGuyPos = buttonPos(91);
						}
						break;
					case R.id.button92:
						if (valid(buttonPos(92))) {
							GoodG_Run(buttonPos(92));
							GoodGuyPos = buttonPos(92);
						}
						break;
					case R.id.button93:
						if (valid(buttonPos(93))) {
							GoodG_Run(buttonPos(93));
							GoodGuyPos = buttonPos(93);
						}
						break;
					case R.id.button94:
						if (valid(buttonPos(94))) {
							GoodG_Run(buttonPos(94));
							GoodGuyPos = buttonPos(94);
						}
						break;
					case R.id.button95:
						if (valid(buttonPos(95))) {
							GoodG_Run(buttonPos(95));
							GoodGuyPos = buttonPos(95);
						}
						break;
					case R.id.button96:
						if (valid(buttonPos(96))) {
							GoodG_Run(buttonPos(96));
							GoodGuyPos = buttonPos(96);
						}
						break;
					case R.id.button97:
						if (valid(buttonPos(97))) {
							GoodG_Run(buttonPos(97));
							GoodGuyPos = buttonPos(97);
						}
						break;
					case R.id.button98:
						if (valid(buttonPos(98))) {
							GoodG_Run(buttonPos(98));
							GoodGuyPos = buttonPos(98);
						}
						break;
					case R.id.button99:
						if (valid(buttonPos(99))) {
							GoodG_Run(buttonPos(99));
							GoodGuyPos = buttonPos(99);
						}
						break;
					case R.id.button100:
						if (valid(buttonPos(100))) {
							GoodG_Run(buttonPos(100));
							GoodGuyPos = buttonPos(100);
						}
						break;
					case R.id.button101:
						if (valid(buttonPos(101))) {
							GoodG_Run(buttonPos(101));
							GoodGuyPos = buttonPos(101);
						}
						break;
					case R.id.button102:
						if (valid(buttonPos(102))) {
							GoodG_Run(buttonPos(102));
							GoodGuyPos = buttonPos(102);
						}
						break;
					case R.id.button103:
						if (valid(buttonPos(103))) {
							GoodG_Run(buttonPos(103));
							GoodGuyPos = buttonPos(103);
						}
						break;
					case R.id.button104:
						if (valid(buttonPos(104))) {
							GoodG_Run(buttonPos(104));
							GoodGuyPos = buttonPos(104);
						}
						break;
					case R.id.button105:
						if (valid(buttonPos(105))) {
							GoodG_Run(buttonPos(105));
							GoodGuyPos = buttonPos(105);
						}
						break;
					case R.id.button106:
						if (valid(buttonPos(106))) {
							GoodG_Run(buttonPos(106));
							GoodGuyPos = buttonPos(106);
						}
						break;
					case R.id.button107:
						if (valid(buttonPos(107))) {
							GoodG_Run(buttonPos(107));
							GoodGuyPos = buttonPos(107);
						}
						break;
					case R.id.button108:
						if (valid(buttonPos(108))) {
							GoodG_Run(buttonPos(108));
							GoodGuyPos = buttonPos(108);
						}
						break;
					case R.id.button109:
						if (valid(buttonPos(109))) {
							GoodG_Run(buttonPos(109));
							GoodGuyPos = buttonPos(109);
						}
						break;
					case R.id.button110:
						if (valid(buttonPos(110))) {
							GoodG_Run(buttonPos(110));
							GoodGuyPos = buttonPos(110);
						}
						break;
					case R.id.button111:
						if (valid(buttonPos(111))) {
							GoodG_Run(buttonPos(111));
							GoodGuyPos = buttonPos(111);
						}
						break;
					case R.id.button112:
						if (valid(buttonPos(112))) {
							GoodG_Run(buttonPos(112));
							GoodGuyPos = buttonPos(112);
						}
						break;
					case R.id.button113:
						if (valid(buttonPos(113))) {
							GoodG_Run(buttonPos(113));
							GoodGuyPos = buttonPos(113);
						}
						break;
					case R.id.button114:
						if (valid(buttonPos(114))) {
							GoodG_Run(buttonPos(114));
							GoodGuyPos = buttonPos(114);
						}
						break;
					case R.id.button115:
						if (valid(buttonPos(115))) {
							GoodG_Run(buttonPos(115));
							GoodGuyPos = buttonPos(115);
						}
						break;
					case R.id.button116:
						if (valid(buttonPos(116))) {
							GoodG_Run(buttonPos(116));
							GoodGuyPos = buttonPos(116);
						}
						break;
					case R.id.button117:
						if (valid(buttonPos(117))) {
							GoodG_Run(buttonPos(117));
							GoodGuyPos = buttonPos(117);
						}
						break;
					case R.id.button118:
						if (valid(buttonPos(118))) {
							GoodG_Run(buttonPos(118));
							GoodGuyPos = buttonPos(118);
						}
						break;
					case R.id.button119:
						if (valid(buttonPos(119))) {
							GoodG_Run(buttonPos(119));
							GoodGuyPos = buttonPos(119);
						}
						break;
					case R.id.button120:
						if (valid(buttonPos(120))) {
							GoodG_Run(buttonPos(120));
							GoodGuyPos = buttonPos(120);
						}
						break;
					case R.id.button121:
						if (valid(buttonPos(121))) {
							GoodG_Run(buttonPos(121));
							GoodGuyPos = buttonPos(121);
						}
						break;
					}
				}
			});
		}
	}

	/*
	 * First: create a onCreateOptionMenu method to take the menu inflater for
	 * the levels and the setting menu .
	 * 
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		MenuInflater setting = getMenuInflater();
		setting.inflate(R.menu.user_menu, menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.medium:

			// Intent i = new Intent(MainActivity.this, levels.class);
			// startActivity(i);
			startActivity(new Intent("com.example.gridcapstone.MED"));

			// startActivity(new Intent(this, medium.class));
			return true;

		case R.id.extreme:

			startActivity(new Intent("com.example.gridcapstone.EXTREME"));
			return true;

		case R.id.easy:
			startActivity(new Intent("com.example.gridcapstone.EASY"));
			break;

		case R.id.rate:

			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("market://search?q=foo"));
			PackageManager rate = getPackageManager();
			List<ResolveInfo> list = rate.queryIntentActivities(intent, 0);

			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse("market://details?id=" + getPackageName()));
			startActivity(i);
			return true;

		case R.id.background:

			return true;

		default:

			return super.onOptionsItemSelected(item);
		}// close the switch
		return YCordin;

	}// close onOptionItemSelected

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */

	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPostResume()
	 */
	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		super.onPostResume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

}
