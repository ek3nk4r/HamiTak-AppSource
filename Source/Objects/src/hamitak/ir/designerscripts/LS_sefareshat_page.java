package hamitak.ir.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_sefareshat_page{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("btn_other").vw.setLeft((int)((25d / 100 * width)/2d - (views.get("btn_other").vw.getWidth() / 2)));
views.get("btn_profile").vw.setLeft((int)((75d / 100 * width)/2d - (views.get("btn_profile").vw.getWidth() / 2)));
views.get("btm_service").vw.setLeft((int)((125d / 100 * width)/2d - (views.get("btm_service").vw.getWidth() / 2)));
views.get("btn_home").vw.setLeft((int)((175d / 100 * width)/2d - (views.get("btn_home").vw.getWidth() / 2)));
views.get("lbl_home").vw.setWidth((int)((70d / 100 * width)));
views.get("lbl_home").vw.setLeft((int)((50d / 100 * width) - (views.get("lbl_home").vw.getWidth() / 2)));
views.get("label1").vw.setLeft((int)((views.get("btn_home").vw.getLeft() + views.get("btn_home").vw.getWidth()/2) - (views.get("label1").vw.getWidth() / 2)));
views.get("label2").vw.setLeft((int)((views.get("btm_service").vw.getLeft() + views.get("btm_service").vw.getWidth()/2) - (views.get("label2").vw.getWidth() / 2)));
views.get("label3").vw.setLeft((int)((views.get("btn_profile").vw.getLeft() + views.get("btn_profile").vw.getWidth()/2) - (views.get("label3").vw.getWidth() / 2)));
views.get("label4").vw.setLeft((int)((views.get("btn_other").vw.getLeft() + views.get("btn_other").vw.getWidth()/2) - (views.get("label4").vw.getWidth() / 2)));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}