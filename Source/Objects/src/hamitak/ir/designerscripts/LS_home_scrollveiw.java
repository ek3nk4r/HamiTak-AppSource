package hamitak.ir.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_home_scrollveiw{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("imageview1").vw.setHeight((int)((33d / 100 * height)));
views.get("horizontalscrollview1").vw.setLeft((int)((50d / 100 * width) - (views.get("horizontalscrollview1").vw.getWidth() / 2)));
views.get("horizontalscrollview2").vw.setLeft((int)((50d / 100 * width) - (views.get("horizontalscrollview2").vw.getWidth() / 2)));
views.get("panel2").vw.setTop((int)((views.get("imageview1").vw.getTop() + views.get("imageview1").vw.getHeight())));
views.get("button2").vw.setLeft((int)((50d / 100 * width) - (views.get("button2").vw.getWidth() / 2)));
views.get("button5").vw.setLeft((int)((50d / 100 * width) - (views.get("button5").vw.getWidth() / 2)));
views.get("label2").vw.setLeft((int)((50d / 100 * width) - (views.get("label2").vw.getWidth() / 2)));
views.get("label5").vw.setLeft((int)((50d / 100 * width) - (views.get("label5").vw.getWidth() / 2)));
views.get("label1").vw.setLeft((int)((views.get("button1").vw.getLeft() + views.get("button1").vw.getWidth()/2) - (views.get("label1").vw.getWidth() / 2)));
views.get("label3").vw.setLeft((int)((views.get("button3").vw.getLeft() + views.get("button3").vw.getWidth()/2) - (views.get("label3").vw.getWidth() / 2)));
views.get("label4").vw.setLeft((int)((views.get("button4").vw.getLeft() + views.get("button4").vw.getWidth()/2) - (views.get("label4").vw.getWidth() / 2)));
views.get("label6").vw.setLeft((int)((views.get("button6").vw.getLeft() + views.get("button6").vw.getWidth()/2) - (views.get("label6").vw.getWidth() / 2)));
views.get("label1").vw.setTop((int)((views.get("button1").vw.getTop() + views.get("button1").vw.getHeight())-7d));
views.get("label2").vw.setTop((int)((views.get("button2").vw.getTop() + views.get("button2").vw.getHeight())-7d));
views.get("label3").vw.setTop((int)((views.get("button3").vw.getTop() + views.get("button3").vw.getHeight())-7d));
views.get("label4").vw.setTop((int)((views.get("button4").vw.getTop() + views.get("button4").vw.getHeight())-7d));
views.get("label5").vw.setTop((int)((views.get("button5").vw.getTop() + views.get("button5").vw.getHeight())-7d));
views.get("label6").vw.setTop((int)((views.get("button6").vw.getTop() + views.get("button6").vw.getHeight())-7d));
views.get("button7").vw.setLeft((int)((50d / 100 * width) - (views.get("button7").vw.getWidth() / 2)));
views.get("label7").vw.setLeft((int)((50d / 100 * width) - (views.get("label7").vw.getWidth() / 2)));
views.get("button7").vw.setTop((int)((views.get("imageview1").vw.getTop() + views.get("imageview1").vw.getHeight())-(30d * scale) - (views.get("button7").vw.getHeight())));
views.get("label7").vw.setTop((int)((views.get("button7").vw.getTop() + views.get("button7").vw.getHeight())-(63d * scale) - (views.get("label7").vw.getHeight())));
views.get("label8").vw.setLeft((int)((views.get("label8").vw.getLeft() + views.get("label8").vw.getWidth())-(10d * scale) - (views.get("label8").vw.getWidth())));
views.get("label9").vw.setLeft((int)((views.get("label9").vw.getLeft() + views.get("label9").vw.getWidth())+(8d * scale) - (views.get("label9").vw.getWidth())));
//BA.debugLineNum = 30;BA.debugLine="HorizontalScrollView2.Top = Label8.Bottom + 5dip"[home_scrollveiw/320x480,scale=1]
views.get("horizontalscrollview2").vw.setTop((int)((views.get("label8").vw.getTop() + views.get("label8").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 31;BA.debugLine="Label12.Top = HorizontalScrollView2.Bottom + 5dip"[home_scrollveiw/320x480,scale=1]
views.get("label12").vw.setTop((int)((views.get("horizontalscrollview2").vw.getTop() + views.get("horizontalscrollview2").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 32;BA.debugLine="Label12.Right = Label12.Right -10dip"[home_scrollveiw/320x480,scale=1]
views.get("label12").vw.setLeft((int)((views.get("label12").vw.getLeft() + views.get("label12").vw.getWidth())-(10d * scale) - (views.get("label12").vw.getWidth())));
//BA.debugLineNum = 33;BA.debugLine="HorizontalScrollView1.Top = Label12.Bottom + 5dip"[home_scrollveiw/320x480,scale=1]
views.get("horizontalscrollview1").vw.setTop((int)((views.get("label12").vw.getTop() + views.get("label12").vw.getHeight())+(5d * scale)));

}
}