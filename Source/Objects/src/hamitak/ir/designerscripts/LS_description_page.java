package hamitak.ir.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_description_page{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 1;BA.debugLine="btn_other.HorizontalCenter = 25%x/2"[description_page/320x480,scale=1]
views.get("btn_other").vw.setLeft((int)((25d / 100 * width)/2d - (views.get("btn_other").vw.getWidth() / 2)));
//BA.debugLineNum = 2;BA.debugLine="btn_profile.HorizontalCenter = 75%x/2"[description_page/320x480,scale=1]
views.get("btn_profile").vw.setLeft((int)((75d / 100 * width)/2d - (views.get("btn_profile").vw.getWidth() / 2)));
//BA.debugLineNum = 3;BA.debugLine="btm_service.HorizontalCenter = 125%x/2"[description_page/320x480,scale=1]
views.get("btm_service").vw.setLeft((int)((125d / 100 * width)/2d - (views.get("btm_service").vw.getWidth() / 2)));
//BA.debugLineNum = 4;BA.debugLine="btn_home.HorizontalCenter = 175%x/2"[description_page/320x480,scale=1]
views.get("btn_home").vw.setLeft((int)((175d / 100 * width)/2d - (views.get("btn_home").vw.getWidth() / 2)));
//BA.debugLineNum = 5;BA.debugLine="lbl_Home.Width = 70%x"[description_page/320x480,scale=1]
views.get("lbl_home").vw.setWidth((int)((70d / 100 * width)));
//BA.debugLineNum = 6;BA.debugLine="lbl_Home.HorizontalCenter = 50%x"[description_page/320x480,scale=1]
views.get("lbl_home").vw.setLeft((int)((50d / 100 * width) - (views.get("lbl_home").vw.getWidth() / 2)));
//BA.debugLineNum = 7;BA.debugLine="Label1.HorizontalCenter = btn_home.HorizontalCenter"[description_page/320x480,scale=1]
views.get("label1").vw.setLeft((int)((views.get("btn_home").vw.getLeft() + views.get("btn_home").vw.getWidth()/2) - (views.get("label1").vw.getWidth() / 2)));
//BA.debugLineNum = 8;BA.debugLine="Label2.HorizontalCenter = btm_service.HorizontalCenter"[description_page/320x480,scale=1]
views.get("label2").vw.setLeft((int)((views.get("btm_service").vw.getLeft() + views.get("btm_service").vw.getWidth()/2) - (views.get("label2").vw.getWidth() / 2)));
//BA.debugLineNum = 9;BA.debugLine="Label3.HorizontalCenter = btn_profile.HorizontalCenter"[description_page/320x480,scale=1]
views.get("label3").vw.setLeft((int)((views.get("btn_profile").vw.getLeft() + views.get("btn_profile").vw.getWidth()/2) - (views.get("label3").vw.getWidth() / 2)));
//BA.debugLineNum = 10;BA.debugLine="Label4.HorizontalCenter = btn_other.HorizontalCenter"[description_page/320x480,scale=1]
views.get("label4").vw.setLeft((int)((views.get("btn_other").vw.getLeft() + views.get("btn_other").vw.getWidth()/2) - (views.get("label4").vw.getWidth() / 2)));
//BA.debugLineNum = 11;BA.debugLine="btn_sabt.Width = 92%x"[description_page/320x480,scale=1]
views.get("btn_sabt").vw.setWidth((int)((92d / 100 * width)));
//BA.debugLineNum = 12;BA.debugLine="btn_sabt.HorizontalCenter = 50%x"[description_page/320x480,scale=1]
views.get("btn_sabt").vw.setLeft((int)((50d / 100 * width) - (views.get("btn_sabt").vw.getWidth() / 2)));
//BA.debugLineNum = 13;BA.debugLine="btn_sabt.Top = scrollveiw_home.Bottom +7dip"[description_page/320x480,scale=1]
views.get("btn_sabt").vw.setTop((int)((views.get("scrollveiw_home").vw.getTop() + views.get("scrollveiw_home").vw.getHeight())+(7d * scale)));
//BA.debugLineNum = 14;BA.debugLine="Panel4.Width = 97%x"[description_page/320x480,scale=1]
views.get("panel4").vw.setWidth((int)((97d / 100 * width)));
//BA.debugLineNum = 15;BA.debugLine="Panel4.HorizontalCenter = 50%x"[description_page/320x480,scale=1]
views.get("panel4").vw.setLeft((int)((50d / 100 * width) - (views.get("panel4").vw.getWidth() / 2)));
//BA.debugLineNum = 16;BA.debugLine="Panel4.Top = Lg_Reg_pane1.Bottom +4dip"[description_page/320x480,scale=1]
views.get("panel4").vw.setTop((int)((views.get("lg_reg_pane1").vw.getTop() + views.get("lg_reg_pane1").vw.getHeight())+(4d * scale)));
//BA.debugLineNum = 17;BA.debugLine="ImageView4.VerticalCenter = Panel4.VerticalCenter"[description_page/320x480,scale=1]
views.get("imageview4").vw.setTop((int)((views.get("panel4").vw.getTop() + views.get("panel4").vw.getHeight()/2) - (views.get("imageview4").vw.getHeight() / 2)));
//BA.debugLineNum = 18;BA.debugLine="ImageView4.Right = Panel4.Right - 4dip"[description_page/320x480,scale=1]
views.get("imageview4").vw.setLeft((int)((views.get("panel4").vw.getLeft() + views.get("panel4").vw.getWidth())-(4d * scale) - (views.get("imageview4").vw.getWidth())));
//BA.debugLineNum = 19;BA.debugLine="Label50.Width = 43%x"[description_page/320x480,scale=1]
views.get("label50").vw.setWidth((int)((43d / 100 * width)));
//BA.debugLineNum = 20;BA.debugLine="Label50.Right = ImageView4.Left -7dip"[description_page/320x480,scale=1]
views.get("label50").vw.setLeft((int)((views.get("imageview4").vw.getLeft())-(7d * scale) - (views.get("label50").vw.getWidth())));
//BA.debugLineNum = 21;BA.debugLine="Label50.VerticalCenter = Panel4.VerticalCenter"[description_page/320x480,scale=1]
views.get("label50").vw.setTop((int)((views.get("panel4").vw.getTop() + views.get("panel4").vw.getHeight()/2) - (views.get("label50").vw.getHeight() / 2)));
//BA.debugLineNum = 22;BA.debugLine="Label60.Left = Panel4.Left + 4dip"[description_page/320x480,scale=1]
views.get("label60").vw.setLeft((int)((views.get("panel4").vw.getLeft())+(4d * scale)));
//BA.debugLineNum = 23;BA.debugLine="Label60.VerticalCenter = Panel4.VerticalCenter"[description_page/320x480,scale=1]
views.get("label60").vw.setTop((int)((views.get("panel4").vw.getTop() + views.get("panel4").vw.getHeight()/2) - (views.get("label60").vw.getHeight() / 2)));
//BA.debugLineNum = 24;BA.debugLine="scrollveiw_home.Width = 96%x"[description_page/320x480,scale=1]
views.get("scrollveiw_home").vw.setWidth((int)((96d / 100 * width)));
//BA.debugLineNum = 25;BA.debugLine="scrollveiw_home.HorizontalCenter = 50%x"[description_page/320x480,scale=1]
views.get("scrollveiw_home").vw.setLeft((int)((50d / 100 * width) - (views.get("scrollveiw_home").vw.getWidth() / 2)));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}