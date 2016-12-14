/*
 * PictureFrame - a simple picture viewer written in JavaFX 
 *
 * Copyright (C) 2014 Burton Alexander
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 * 
 */
package Preview;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class PictureView {
	private static final BigDecimal ONE_THOUSAND = new BigDecimal(1000);

	//private static final Logger log = LoggerFactory.getLogger(PictureView.class);

	private static final long SLIDER_FADE_TIME = 1000;

	private Timer timer;

	private PictureScanner scanner = new PictureScanner();
	private Settings settings;

	private ImageView view1 = new ImageView();
	private ImageView view2 = new ImageView();
	private Rectangle r;

	private FadeTransition fade1 = new FadeTransition();
	private FadeTransition fade2 = new FadeTransition();

	private ParallelTransition pt = new ParallelTransition(fade1, fade2);

	private FillTransition fillTransition;

	private Slider durationSlider = new Slider(200, 60000, 5000);
	private Label durationLabel = new Label();
	private Slider transitionSlider = new Slider(200, 20000, 5000);
	private Label transitionLabel = new Label();
	private VBox sliderBox = new VBox(10, transitionLabel, transitionSlider, durationLabel, durationSlider);
	private FadeTransition sliderFade = new FadeTransition(Duration.millis(SLIDER_FADE_TIME), sliderBox);

	private StackPane stackPane = new StackPane(sliderBox, view2, view1);

	private long duration = 5000;
	private long transition = 5000;

	private Random rand = new Random(System.nanoTime());

	private VBox vbox = new VBox(stackPane);

	private volatile boolean running;

	private boolean dirChooserShowing;

	public PictureView(Settings settings) {
		this.settings = settings;
		init();
	}

	public void start() {
		while (scanner.size() == 0) {
			showDirectoryChooser();
		}

		running = true;

		startImpl();
	}

	public void stop() {
//		log.debug("stop");
		running = false;
		if (timer != null) {
			timer.cancel();
			timer = null;
		}

		stopAnimations();
		reset();
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getTransition() {
		return transition;
	}

	public void setTransition(long transition) {
		this.transition = transition;
	}

	public Parent getView() {
		return vbox;
	}

	public void setHeight(double height) {
		view1.setFitHeight(height);
		view2.setFitHeight(height);
		r.setHeight(height);
	}

	public void setWidth(double width) {
		view1.setFitWidth(width);
		view2.setFitWidth(width);
		r.setWidth(width);
	}

	public void zoom(double factor) {
		scale(view1, factor);
		scale(view2, factor);
	}

	private void scale(ImageView view, double factor) {
		view.setScaleX(view.getScaleX() * factor);
		view.setScaleY(view.getScaleY() * factor);
	}

	private void reset() {
		fillTransition.setToValue(Color.WHITE);
		fillTransition.playFromStart();
	}

	private void stopAnimations() {
		fillTransition.stop();
	}

	private void setDirectory(String directory) {
		scanner.setDirectory(directory);
	}

	private void startImpl() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				transition();
				transitionBackground();
			}
		});
	}

	private void schedule() {
		if (timer == null) timer = new Timer("PictureFrame image switch timer", true);

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (running) switchImages();
			}
		}, getDuration());
	}

	private void switchImages() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				transition();
			}
		});
	}

	private void transition() {
		boolean isFrom1 = fade1.getNode().getOpacity() == 1 || fade1.getCurrentRate() > 0;

		setImage((ImageView) (isFrom1 ? fade2.getNode() : fade1.getNode()));

		pt.stop();

		fade1.setFromValue(view1.getOpacity());
		fade1.setToValue(isFrom1 ? 0.0 : 1.0);
		fade1.setDuration(Duration.millis(getTransition()));

		fade2.setFromValue(view2.getOpacity());
		fade2.setToValue(isFrom1 ? 1.0 : 0.0);
		fade2.setDuration(Duration.millis(getTransition()));

		pt.play();
	}

	private void setImage(ImageView view) {
		try {
			Image image = scanner.getRandomImage();
			view.setImage(image);
			view.setOpacity(0.0);
		} catch (FileNotFoundException e) {
//			log.error("Could not load random image", e);
		}
	}

	private void init() {
		view2.setOpacity(0.0);
		view1.setPreserveRatio(true);
		view2.setPreserveRatio(true);

		fade1.setNode(view1);
		fade1.setDuration(Duration.millis(getTransition()));
		fade1.setInterpolator(Interpolator.LINEAR);

		fade2.setNode(view2);
		fade2.setDuration(Duration.millis(getTransition()));
		fade2.setInterpolator(Interpolator.LINEAR);

		fade1.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				schedule();
			}
		});

		r = getRectangle();
		stackPane.getChildren().add(0, r);

		fillTransition = new FillTransition(Duration.seconds(3), r);
		fillTransition.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (!running) return;
				transitionBackground();
			}
		});

		initControls();
		noCursor();
		setDirectory(settings.getDirectory());
		setDuration(settings.getDuration());
	}

	private void initControls() {
		durationLabel.setFont(Font.font(32));
		transitionLabel.setFont(Font.font(32));
		durationLabel.setText(getFadeDurationText(durationSlider.getValue()));
		transitionLabel.setText(getTransitionText(transitionSlider.getValue()));

		sliderBox.setOpacity(0.0);

		durationSlider.setMaxWidth(500);
		transitionSlider.setMaxWidth(500);

		durationSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				setDuration(newValue.longValue());
				durationLabel.setText(getFadeDurationText(getDuration()));
				settings.setDuration(getDuration());
			}
		});

		transitionSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				setTransition(newValue.longValue());
				transitionLabel.setText(getTransitionText(getTransition()));
				settings.setTransition(getTransition());
			}
		});

		durationSlider.setValue(settings.getDuration());
		transitionSlider.setValue(settings.getTransition());

		sliderFade.setFromValue(0.0);
		sliderFade.setToValue(1.0);
		sliderFade.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (sliderBox.getOpacity() == 0 && !dirChooserShowing) noCursor();
			}
		});

		sliderBox.setAlignment(Pos.CENTER);
	}

	private String getFadeDurationText(double millis) {
		return "Duration: " + getSeconds(millis) + " seconds";
	}

	private String getTransitionText(double millis) {
		return "Transition: " + getSeconds(millis) + " seconds";
	}

	private String getSeconds(double millis) {
		return new BigDecimal(millis).divide(ONE_THOUSAND, 3, RoundingMode.HALF_UP).toString();
	}

	private void next() {
		boolean b = running;
		stop();
		running = b;
		if (running) {
			startImpl();
		} else {
			transition();
		}
	}

	private void transitionBackground() {
		fillTransition.setToValue(new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), rand.nextDouble()));
		fillTransition.playFromStart();
	}

	private Rectangle getRectangle() {
		Rectangle r = new Rectangle(stackPane.getWidth(), stackPane.getHeight());

		r.setFill(Color.WHITE);

		return r;
	}

	private void handleMove(MouseEvent event) {
		if (sliderBox.getOpacity() == 0) {
			defaultCursor();
			sliderFade.stop();
			sliderFade.setFromValue(0.0);
			sliderFade.setToValue(1.0);
			sliderFade.play();
			sliderBox.toFront();
		}
	}

	private void defaultCursor() {
		vbox.setCursor(Cursor.DEFAULT);
	}

	private void noCursor() {
		vbox.setCursor(Cursor.NONE);
	}

	private void showDirectoryChooser() {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Picture Directories");
		chooser.setInitialDirectory(scanner.getDirectory());

		dirChooserShowing = true;
		defaultCursor();
		File chosen = chooser.showDialog(PictureFrame.primaryStage);
		noCursor();
		dirChooserShowing = false;
		if (chosen != null) {
			scanner.setDirectory(chosen);
			settings.setDirectory(chosen.getAbsolutePath());
		}
	}

	public EventHandler<MouseEvent> getMouseEventHandler() {

		return new EventHandler<MouseEvent>() {

			private Thread singleClick;
			private MouseDetectThread mouseDetectThread;
			private double dragX = -1;
			private double dragY = -1;
			private double posX = -1;
			private double posY = -1;

			@Override
			public void handle(MouseEvent event) {
				mouseDetectCheck();

				if (MouseEvent.MOUSE_PRESSED == event.getEventType()) {
					handleMousePressed(event);
				} else if (MouseEvent.MOUSE_MOVED == event.getEventType()) {
					handleMove(event);
				} else if (MouseEvent.MOUSE_DRAGGED == event.getEventType()) {
					movingPictures(event);
				} else if (MouseEvent.MOUSE_RELEASED == event.getEventType()) {
					dragX = -1;
					posX = -1;
					dragY = -1;
					posY = -1;
				}
			}

			private void movingPictures(MouseEvent event) {
				stopThread();

				if (dragX == -1) {
					posX = view1.getX();
					posY = view1.getY();
					dragX = event.getSceneX();
					dragY = event.getSceneY();
				} else {
					double x = posX + (event.getSceneX() - dragX);
					double y = posY + (event.getSceneY() - dragY);

					view1.setTranslateX(x);
					view1.setTranslateY(y);
					view2.setTranslateX(x);
					view2.setTranslateY(y);
				}
			}

			private void mouseDetectCheck() {
				if (mouseDetectThread == null || !mouseDetectThread.isAlive()) {
					mouseDetectThread = new MouseDetectThread();
					mouseDetectThread.lastDetection = System.currentTimeMillis();
					mouseDetectThread.start();
				} else {
					mouseDetectThread.lastDetection = System.currentTimeMillis();
				}
			}

			private void handleMousePressed(MouseEvent event) {
				if (event.isDragDetect() || isSliderEvent(event)) return;

				if (event.getClickCount() == 2) {
					stopThread();
					if (running) {
						stop();
					} else {
						start();
					}
				} else if (event.getButton() == MouseButton.SECONDARY) {
					showDirectoryChooser();
				} else if (event.getClickCount() == 1) {
					startThread();
				}
			}

			private boolean isSliderEvent(MouseEvent event) {
				return isSliderEvent(event, durationSlider) || isSliderEvent(event, transitionSlider);
			}

			private boolean isSliderEvent(MouseEvent event, Slider slider) {
				Rectangle rect = new Rectangle(slider.getLayoutX(), slider.getLayoutY(), slider.getWidth(), slider.getHeight());
				return rect.contains(event.getSceneX(), event.getSceneY());
			}

			private void startThread() {
				stopThread();
				singleClick = new Thread("Single click detector") {
					public void run() {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
						}
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								next();
							}
						});
					}
				};
				singleClick.start();
			}

			@SuppressWarnings("deprecation")
			private void stopThread() {
				if (singleClick == null || !singleClick.isAlive()) return;

				singleClick.stop();
			}
		};
	}

	private class MouseDetectThread extends Thread {
		volatile long lastDetection;

		public void run() {
			while (mouseMoved()) {
				try {
					sleep(SLIDER_FADE_TIME);
				} catch (InterruptedException e) {

				}
			}

			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					sliderFade.stop();
					sliderFade.setFromValue(sliderBox.getOpacity());
					sliderFade.setToValue(0.0);
					sliderFade.play();
				}
			});
		}

		private boolean mouseMoved() {
			return dirChooserShowing || durationSlider.isValueChanging() || transitionSlider.isValueChanging()
					|| System.currentTimeMillis() - lastDetection < SLIDER_FADE_TIME;
		}
	}

}
