package hamitak.ir.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_verify_sms{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("lbl_homepage").vw.setWidth((int)((70d / 100 * width)));
views.get("lbl_homepage").vw.setLeft((int)((50d / 100 * width) - (views.get("lbl_homepage").vw.getWidth() / 2)));
views.get("verify_txt").vw.setWidth((int)((90d / 100 * width)));
views.get("verify_txt").vw.setLeft((int)((50d / 100 * width) - (views.get("verify_txt").vw.getWidth() / 2)));
views.get("verify_btn").vw.setWidth((int)((75d / 100 * width)));
views.get("verify_btn").vw.setLeft((int)((50d / 100 * width) - (views.get("verify_btn").vw.getWidth() / 2)));
views.get("label1").vw.setLeft((int)((50d / 100 * width) - (views.get("label1").vw.getWidth() / 2)));
views.get("label2").vw.setLeft((int)((50d / 100 * width) - (views.get("label2").vw.getWidth() / 2)));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}