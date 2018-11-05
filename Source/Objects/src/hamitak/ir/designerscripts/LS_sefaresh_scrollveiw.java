package hamitak.ir.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_sefaresh_scrollveiw{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("lbl_name").vw.setWidth((int)((47d / 100 * width)));
views.get("lbl_name2").vw.setWidth((int)((47d / 100 * width)));
views.get("edit_name2").vw.setWidth((int)((47d / 100 * width)));
views.get("lbl_name").vw.setLeft((int)((74d / 100 * width) - (views.get("lbl_name").vw.getWidth() / 2)));
views.get("lbl_name2").vw.setLeft((int)((25d / 100 * width) - (views.get("lbl_name2").vw.getWidth() / 2)));
views.get("edit_name2").vw.setLeft((int)((25d / 100 * width) - (views.get("edit_name2").vw.getWidth() / 2)));
views.get("lbl_phone").vw.setWidth((int)((47d / 100 * width)));
views.get("lbl_phone2").vw.setWidth((int)((47d / 100 * width)));
views.get("lbl_phone").vw.setLeft((int)((74d / 100 * width) - (views.get("lbl_phone").vw.getWidth() / 2)));
views.get("lbl_phone2").vw.setLeft((int)((25d / 100 * width) - (views.get("lbl_phone2").vw.getWidth() / 2)));
views.get("lbl_sefaresh").vw.setWidth((int)((47d / 100 * width)));
views.get("lbl_sefaresh2").vw.setWidth((int)((47d / 100 * width)));
views.get("lbl_sefaresh").vw.setLeft((int)((74d / 100 * width) - (views.get("lbl_sefaresh").vw.getWidth() / 2)));
views.get("lbl_sefaresh2").vw.setLeft((int)((25d / 100 * width) - (views.get("lbl_sefaresh2").vw.getWidth() / 2)));
views.get("lbl_adress").vw.setWidth((int)((47d / 100 * width)));
views.get("lbl_adress").vw.setLeft((int)((74d / 100 * width) - (views.get("lbl_adress").vw.getWidth() / 2)));
views.get("edit_adress").vw.setWidth((int)((95d / 100 * width)));
views.get("edit_adress").vw.setLeft((int)((50d / 100 * width) - (views.get("edit_adress").vw.getWidth() / 2)));
views.get("btn_takmil").vw.setWidth((int)((95d / 100 * width)));
views.get("btn_takmil").vw.setLeft((int)((50d / 100 * width) - (views.get("btn_takmil").vw.getWidth() / 2)));
views.get("btn_cancel").vw.setWidth((int)((47d / 100 * width)));
views.get("btn_homepay").vw.setWidth((int)((47d / 100 * width)));
views.get("btn_homepay").vw.setLeft((int)((74d / 100 * width) - (views.get("btn_homepay").vw.getWidth() / 2)));
views.get("btn_cancel").vw.setLeft((int)((26d / 100 * width) - (views.get("btn_cancel").vw.getWidth() / 2)));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}