package de.jd.bp.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.jd.bp.BoringPongGame;
import de.jd.bp.Constants;

public class HtmlLauncher extends GwtApplication {

	static HtmlLauncher instance;

	public static void handleResize() {
		instance.scaleCanvas();
	}

	@Override
	public ApplicationListener createApplicationListener() {
		instance = this;
		setLogLevel(LOG_NONE);
		setLoadingListener(new LoadingListener() {
			@Override
			public void afterSetup() {
				scaleCanvas();
				setupResizeHook();
			}

			@Override
			public void beforeSetup() {

			}
		});
		return new BoringPongGame();
	}

	@Override
	public GwtApplicationConfiguration getConfig() {
		final GwtApplicationConfiguration config = new GwtApplicationConfiguration((int) Constants.WORLD_WIDTH,
				(int) Constants.WORLD_HEIGHT);

		final Element element = Document.get().getElementById("embed-html");
		final VerticalPanel panel = new VerticalPanel();
		panel.setWidth("100%");
		panel.setHeight("100%");
		panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		element.appendChild(panel.getElement());
		config.rootPanel = panel;

		return config;
	}

	native int getWindowInnerHeight() /*-{
										return $wnd.innerHeight;
										}-*/;

	native int getWindowInnerWidth() /*-{
										return $wnd.innerWidth;
										}-*/;

	void scaleCanvas() {
		final Element element = Document.get().getElementById("embed-html");
		final int innerWidth = getWindowInnerWidth();
		final int innerHeight = getWindowInnerHeight();
		int newWidth = innerWidth;
		int newHeight = innerHeight;
		final float ratio = innerWidth / (float) innerHeight;
		final float viewRatio = Constants.WORLD_WIDTH / Constants.WORLD_HEIGHT;

		if (ratio > viewRatio) {
			newWidth = (int) (innerHeight * viewRatio);
		}
		else {
			newHeight = (int) (innerWidth / viewRatio);
		}

		final NodeList<Element> nl = element.getElementsByTagName("canvas");

		if (nl != null && nl.getLength() > 0) {
			final Element canvas = nl.getItem(0);
			canvas.setAttribute("width", "" + newWidth + "px");
			canvas.setAttribute("height", "" + newHeight + "px");
			canvas.getStyle().setWidth(newWidth, Style.Unit.PX);
			canvas.getStyle().setHeight(newHeight, Style.Unit.PX);
			canvas.getStyle().setTop((int) ((innerHeight - newHeight) * 0.5f), Style.Unit.PX);
			canvas.getStyle().setLeft((int) ((innerWidth - newWidth) * 0.5f), Style.Unit.PX);
			canvas.getStyle().setPosition(Style.Position.ABSOLUTE);
		}
	}

	native void setupResizeHook() /*-{
									var htmlLauncher_onWindowResize = $entry(@de.jd.bp.client.HtmlLauncher::handleResize());
									$wnd.addEventListener('resize', htmlLauncher_onWindowResize, false);
									}-*/;
}