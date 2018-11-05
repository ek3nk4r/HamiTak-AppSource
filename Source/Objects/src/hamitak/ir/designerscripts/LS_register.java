package hamitak.ir.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_register{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("name_txt").vw.setWidth((int)((90d / 100 * width)));
views.get("name_txt").vw.setLeft((int)((50d / 100 * width) - (views.get("name_txt").vw.getWidth() / 2)));
views.get("phone_txt").vw.setWidth((int)((90d / 100 * width)));
views.get("phone_txt").vw.setLeft((int)((50d / 100 * width) - (views.get("phone_txt").vw.getWidth() / 2)));
views.get("pass_txt").vw.setWidth((int)((90d / 100 * width)));
views.get("pass_txt").vw.setLeft((int)((50d / 100 * width) - (views.get("pass_txt").vw.getWidth() / 2)));
views.get("register_btn").vw.setWidth((int)((75d / 100 * width)));
views.get("register_btn").vw.setLeft((int)((50d / 100 * width) - (views.get("register_btn").vw.getWidth() / 2)));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}