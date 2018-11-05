package hamitak.ir.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_login{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("phone_txt1").vw.setWidth((int)((90d / 100 * width)));
views.get("phone_txt1").vw.setLeft((int)((50d / 100 * width) - (views.get("phone_txt1").vw.getWidth() / 2)));
views.get("pass_txt1").vw.setWidth((int)((90d / 100 * width)));
views.get("pass_txt1").vw.setLeft((int)((50d / 100 * width) - (views.get("pass_txt1").vw.getWidth() / 2)));
views.get("login_btn").vw.setWidth((int)((75d / 100 * width)));
views.get("login_btn").vw.setLeft((int)((50d / 100 * width) - (views.get("login_btn").vw.getWidth() / 2)));
views.get("label1").vw.setWidth((int)((75d / 100 * width)));
views.get("label1").vw.setLeft((int)((50d / 100 * width) - (views.get("label1").vw.getWidth() / 2)));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}