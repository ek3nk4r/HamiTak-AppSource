package hamitak.ir.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_horizontalscrollview_page{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("imageview4").vw.setLeft((int)((views.get("panel4").vw.getLeft() + views.get("panel4").vw.getWidth()/2) - (views.get("imageview4").vw.getWidth() / 2)));
views.get("label11").vw.setWidth((int)((views.get("panel4").vw.getWidth())));
views.get("label11").vw.setLeft((int)((views.get("panel4").vw.getLeft() + views.get("panel4").vw.getWidth()/2) - (views.get("label11").vw.getWidth() / 2)));
views.get("imageview4").vw.setTop((int)((views.get("panel4").vw.getTop())+(8d * scale)));
views.get("label11").vw.setTop((int)((views.get("imageview4").vw.getTop() + views.get("imageview4").vw.getHeight())+(4d * scale)));
views.get("label24").vw.setHeight((int)((views.get("panel4").vw.getHeight())));
views.get("label24").vw.setWidth((int)((views.get("panel4").vw.getWidth())));
views.get("label24").vw.setTop((int)((views.get("panel4").vw.getTop())));
views.get("label24").vw.setLeft((int)((views.get("panel4").vw.getLeft() + views.get("panel4").vw.getWidth()/2) - (views.get("label24").vw.getWidth() / 2)));
views.get("label24").vw.setTop((int)((views.get("panel4").vw.getTop() + views.get("panel4").vw.getHeight()/2) - (views.get("label24").vw.getHeight() / 2)));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}