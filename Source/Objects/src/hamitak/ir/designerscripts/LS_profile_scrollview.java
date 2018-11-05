package hamitak.ir.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_profile_scrollview{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("lbl_hamedan").vw.setLeft((int)((50d / 100 * width) - (views.get("lbl_hamedan").vw.getWidth() / 2)));
views.get("lbl_version").vw.setLeft((int)((views.get("lbl_hamedan").vw.getLeft() + views.get("lbl_hamedan").vw.getWidth()/2) - (views.get("lbl_version").vw.getWidth() / 2)));
views.get("lbl_version").vw.setTop((int)((views.get("lbl_hamedan").vw.getTop() + views.get("lbl_hamedan").vw.getHeight())-(16d * scale)));
views.get("lbl_description").vw.setWidth((int)((92d / 100 * width)));
views.get("lbl_description").vw.setLeft((int)((50d / 100 * width) - (views.get("lbl_description").vw.getWidth() / 2)));
views.get("lbl_description").vw.setTop((int)((views.get("lbl_version").vw.getTop() + views.get("lbl_version").vw.getHeight())+(25d * scale)));
views.get("btn_call").vw.setTop((int)((views.get("lbl_description").vw.getTop() + views.get("lbl_description").vw.getHeight())+(10d * scale)));
views.get("btn_call").vw.setWidth((int)((92d / 100 * width)));
views.get("btn_call").vw.setLeft((int)((50d / 100 * width) - (views.get("btn_call").vw.getWidth() / 2)));
views.get("btn_telegram").vw.setTop((int)((views.get("btn_call").vw.getTop() + views.get("btn_call").vw.getHeight())+(5d * scale)));
views.get("btn_telegram").vw.setWidth((int)((92d / 100 * width)));
views.get("btn_telegram").vw.setLeft((int)((50d / 100 * width) - (views.get("btn_telegram").vw.getWidth() / 2)));
views.get("btn_insta").vw.setTop((int)((views.get("btn_telegram").vw.getTop() + views.get("btn_telegram").vw.getHeight())+(5d * scale)));
views.get("btn_channel").vw.setTop((int)((views.get("btn_telegram").vw.getTop() + views.get("btn_telegram").vw.getHeight())+(5d * scale)));
views.get("btn_site").vw.setTop((int)((views.get("btn_telegram").vw.getTop() + views.get("btn_telegram").vw.getHeight())+(5d * scale)));
views.get("btn_insta").vw.setWidth((int)((30.4d / 100 * width)));
views.get("btn_channel").vw.setWidth((int)((30.4d / 100 * width)));
views.get("btn_site").vw.setWidth((int)((30.4d / 100 * width)));
views.get("btn_insta").vw.setLeft((int)((views.get("btn_telegram").vw.getLeft() + views.get("btn_telegram").vw.getWidth()) - (views.get("btn_insta").vw.getWidth())));
views.get("btn_channel").vw.setLeft((int)((50d / 100 * width) - (views.get("btn_channel").vw.getWidth() / 2)));
views.get("btn_site").vw.setLeft((int)((views.get("btn_telegram").vw.getLeft())));
views.get("btn_insta").vw.setHeight((int)((views.get("btn_insta").vw.getHeight())));
views.get("btn_channel").vw.setHeight((int)((views.get("btn_insta").vw.getHeight())));
views.get("btn_site").vw.setHeight((int)((views.get("btn_insta").vw.getHeight())));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}