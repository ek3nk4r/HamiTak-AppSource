package hamitak.ir;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class home extends Activity implements B4AActivity{
	public static home mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "hamitak.ir", "hamitak.ir.home");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (home).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "hamitak.ir", "hamitak.ir.home");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hamitak.ir.home", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (home) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (home) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return home.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (home) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            home mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (home) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrollveiw_home = null;
public anywheresoftware.b4a.phone.Phone.PhoneIntents _url = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview7 = null;
public anywheresoftware.b4a.objects.HorizontalScrollViewWrapper _horizontalscrollview1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel4 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview4 = null;
public anywheresoftware.b4a.objects.HorizontalScrollViewWrapper _horizontalscrollview2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
public anywheresoftware.b4a.sql.SQL.CursorWrapper _cs = null;
public anywheresoftware.b4a.objects.LabelWrapper _label11 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label24 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label7 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public hamitak.ir.main _main = null;
public hamitak.ir.verify_ac _verify_ac = null;
public hamitak.ir.firsactivity _firsactivity = null;
public hamitak.ir.service_ac _service_ac = null;
public hamitak.ir.profile_ac _profile_ac = null;
public hamitak.ir.services_ac _services_ac = null;
public hamitak.ir.description_ac _description_ac = null;
public hamitak.ir.sefaresh_ac _sefaresh_ac = null;
public hamitak.ir.sefareshat_ac _sefareshat_ac = null;
public hamitak.ir.login _login = null;
public hamitak.ir.homepage_ac _homepage_ac = null;
public hamitak.ir.starter _starter = null;
public hamitak.ir.frmlocation _frmlocation = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
int _left = 0;
int _i = 0;
 //BA.debugLineNum = 27;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 28;BA.debugLine="Activity.LoadLayout(\"Home\")";
mostCurrent._activity.LoadLayout("Home",mostCurrent.activityBA);
 //BA.debugLineNum = 29;BA.debugLine="cs = Main.sql1.ExecQuery(\"SELECT * FROM sefaresha";
mostCurrent._cs.setObject((android.database.Cursor)(mostCurrent._main._sql1.ExecQuery("SELECT * FROM sefareshat")));
 //BA.debugLineNum = 30;BA.debugLine="cs.Position = 0";
mostCurrent._cs.setPosition((int) (0));
 //BA.debugLineNum = 32;BA.debugLine="scrollveiw_home.Panel.LoadLayout(\"home_scrollveiw";
mostCurrent._scrollveiw_home.getPanel().LoadLayout("home_scrollveiw",mostCurrent.activityBA);
 //BA.debugLineNum = 33;BA.debugLine="Label7.Text = \"شما تا الان \" & cs.RowCount & \" خد";
mostCurrent._label7.setText(BA.ObjectToCharSequence("شما تا الان "+BA.NumberToString(mostCurrent._cs.getRowCount())+" خدمات از ما دریافت کرده اید"));
 //BA.debugLineNum = 34;BA.debugLine="scrollveiw_home.Panel.Height = HorizontalScrollVi";
mostCurrent._scrollveiw_home.getPanel().setHeight((int) (mostCurrent._horizontalscrollview1.getTop()+mostCurrent._horizontalscrollview1.getHeight()));
 //BA.debugLineNum = 35;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 36;BA.debugLine="r.Target = scrollveiw_home";
_r.Target = (Object)(mostCurrent._scrollveiw_home.getObject());
 //BA.debugLineNum = 37;BA.debugLine="r.RunMethod2(\"setVerticalScrollBarEnabled\", False";
_r.RunMethod2("setVerticalScrollBarEnabled",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.False),"java.lang.boolean");
 //BA.debugLineNum = 39;BA.debugLine="HorizontalScrollView2.Panel.Color = Colors.Transp";
mostCurrent._horizontalscrollview2.getPanel().setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 40;BA.debugLine="Dim left As Int = 15";
_left = (int) (15);
 //BA.debugLineNum = 41;BA.debugLine="cs = Main.sql.ExecQuery(\"SELECT * FROM service_ac";
mostCurrent._cs.setObject((android.database.Cursor)(mostCurrent._main._sql.ExecQuery("SELECT * FROM service_active ORDER BY id DESC")));
 //BA.debugLineNum = 42;BA.debugLine="For i = 0 To 6";
{
final int step13 = 1;
final int limit13 = (int) (6);
_i = (int) (0) ;
for (;_i <= limit13 ;_i = _i + step13 ) {
 //BA.debugLineNum = 43;BA.debugLine="cs.Position = i";
mostCurrent._cs.setPosition(_i);
 //BA.debugLineNum = 44;BA.debugLine="Dim pnl As Panel";
mostCurrent._pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 45;BA.debugLine="pnl.Initialize(\"pnl\")";
mostCurrent._pnl.Initialize(mostCurrent.activityBA,"pnl");
 //BA.debugLineNum = 46;BA.debugLine="pnl.LoadLayout(\"HorizontalScrollView_page\")";
mostCurrent._pnl.LoadLayout("HorizontalScrollView_page",mostCurrent.activityBA);
 //BA.debugLineNum = 47;BA.debugLine="pnl.Tag = i";
mostCurrent._pnl.setTag((Object)(_i));
 //BA.debugLineNum = 48;BA.debugLine="HorizontalScrollView2.Panel.AddView(pnl,left,Pan";
mostCurrent._horizontalscrollview2.getPanel().AddView((android.view.View)(mostCurrent._pnl.getObject()),_left,mostCurrent._panel4.getTop(),mostCurrent._panel4.getWidth(),mostCurrent._panel4.getHeight());
 //BA.debugLineNum = 49;BA.debugLine="left = left + Panel4.Width";
_left = (int) (_left+mostCurrent._panel4.getWidth());
 //BA.debugLineNum = 50;BA.debugLine="HorizontalScrollView2.Panel.Height = Panel4.Heig";
mostCurrent._horizontalscrollview2.getPanel().setHeight(mostCurrent._panel4.getHeight());
 //BA.debugLineNum = 51;BA.debugLine="If pnl.Tag = i Then";
if ((mostCurrent._pnl.getTag()).equals((Object)(_i))) { 
 //BA.debugLineNum = 52;BA.debugLine="Label11.Text = cs.GetString(\"servicename\")";
mostCurrent._label11.setText(BA.ObjectToCharSequence(mostCurrent._cs.GetString("servicename")));
 //BA.debugLineNum = 53;BA.debugLine="Label24.Text = Label11.Text";
mostCurrent._label24.setText(BA.ObjectToCharSequence(mostCurrent._label11.getText()));
 //BA.debugLineNum = 54;BA.debugLine="Label24.TextColor = Colors.Transparent";
mostCurrent._label24.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 55;BA.debugLine="Label24.Enabled = True";
mostCurrent._label24.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 56;BA.debugLine="Label24.Visible = True";
mostCurrent._label24.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 57;BA.debugLine="ImageView4.Bitmap = LoadBitmap(File.DirAssets,c";
mostCurrent._imageview4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),mostCurrent._cs.GetString("image")).getObject()));
 };
 }
};
 //BA.debugLineNum = 60;BA.debugLine="HorizontalScrollView2.Panel.Width = left";
mostCurrent._horizontalscrollview2.getPanel().setWidth(_left);
 //BA.debugLineNum = 61;BA.debugLine="HorizontalScrollView2.FullScroll(True)";
mostCurrent._horizontalscrollview2.FullScroll(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 62;BA.debugLine="HorizontalScrollView2.Panel.Height = Panel4.Heigh";
mostCurrent._horizontalscrollview2.getPanel().setHeight(mostCurrent._panel4.getHeight());
 //BA.debugLineNum = 63;BA.debugLine="CallSubDelayed(Me,\"setSV2_Right\")";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,home.getObject(),"setSV2_Right");
 //BA.debugLineNum = 65;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 66;BA.debugLine="r.Target = HorizontalScrollView2";
_r.Target = (Object)(mostCurrent._horizontalscrollview2.getObject());
 //BA.debugLineNum = 67;BA.debugLine="r.RunMethod2(\"setHorizontalScrollBarEnabled\", Fal";
_r.RunMethod2("setHorizontalScrollBarEnabled",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.False),"java.lang.boolean");
 //BA.debugLineNum = 70;BA.debugLine="HorizontalScrollView1.Panel.Color = Colors.Transp";
mostCurrent._horizontalscrollview1.getPanel().setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 71;BA.debugLine="Dim left As Int = 20";
_left = (int) (20);
 //BA.debugLineNum = 72;BA.debugLine="cs = Main.sql.ExecQuery(\"SELECT * FROM service_un";
mostCurrent._cs.setObject((android.database.Cursor)(mostCurrent._main._sql.ExecQuery("SELECT * FROM service_unactive ORDER BY id DESC")));
 //BA.debugLineNum = 73;BA.debugLine="For i = 0 To 9";
{
final int step41 = 1;
final int limit41 = (int) (9);
_i = (int) (0) ;
for (;_i <= limit41 ;_i = _i + step41 ) {
 //BA.debugLineNum = 74;BA.debugLine="cs.Position = i";
mostCurrent._cs.setPosition(_i);
 //BA.debugLineNum = 75;BA.debugLine="Dim pnl As Panel";
mostCurrent._pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 76;BA.debugLine="pnl.Initialize(\"pnl\")";
mostCurrent._pnl.Initialize(mostCurrent.activityBA,"pnl");
 //BA.debugLineNum = 77;BA.debugLine="pnl.LoadLayout(\"HorizontalScrollView_page\")";
mostCurrent._pnl.LoadLayout("HorizontalScrollView_page",mostCurrent.activityBA);
 //BA.debugLineNum = 78;BA.debugLine="pnl.Tag = i";
mostCurrent._pnl.setTag((Object)(_i));
 //BA.debugLineNum = 79;BA.debugLine="HorizontalScrollView1.Panel.AddView(pnl,left,Pan";
mostCurrent._horizontalscrollview1.getPanel().AddView((android.view.View)(mostCurrent._pnl.getObject()),_left,mostCurrent._panel4.getTop(),mostCurrent._panel4.getWidth(),mostCurrent._panel4.getHeight());
 //BA.debugLineNum = 80;BA.debugLine="left = left + Panel4.Width";
_left = (int) (_left+mostCurrent._panel4.getWidth());
 //BA.debugLineNum = 81;BA.debugLine="HorizontalScrollView1.Panel.Height = Panel4.Heig";
mostCurrent._horizontalscrollview1.getPanel().setHeight(mostCurrent._panel4.getHeight());
 //BA.debugLineNum = 82;BA.debugLine="If pnl.Tag = i Then";
if ((mostCurrent._pnl.getTag()).equals((Object)(_i))) { 
 //BA.debugLineNum = 83;BA.debugLine="Label11.Text = cs.GetString(\"servicename\")";
mostCurrent._label11.setText(BA.ObjectToCharSequence(mostCurrent._cs.GetString("servicename")));
 //BA.debugLineNum = 84;BA.debugLine="Label24.TextColor = Colors.Transparent";
mostCurrent._label24.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 85;BA.debugLine="Label24.Enabled = False";
mostCurrent._label24.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 86;BA.debugLine="Label24.Visible = False";
mostCurrent._label24.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 87;BA.debugLine="ImageView4.Bitmap = LoadBitmap(File.DirAssets,c";
mostCurrent._imageview4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),mostCurrent._cs.GetString("image")).getObject()));
 };
 }
};
 //BA.debugLineNum = 90;BA.debugLine="HorizontalScrollView1.Panel.Width = left";
mostCurrent._horizontalscrollview1.getPanel().setWidth(_left);
 //BA.debugLineNum = 91;BA.debugLine="HorizontalScrollView1.FullScroll(True)";
mostCurrent._horizontalscrollview1.FullScroll(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 92;BA.debugLine="HorizontalScrollView1.Panel.Height = Panel4.Heigh";
mostCurrent._horizontalscrollview1.getPanel().setHeight(mostCurrent._panel4.getHeight());
 //BA.debugLineNum = 93;BA.debugLine="CallSubDelayed(Me,\"setSV1_Right\")";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,home.getObject(),"setSV1_Right");
 //BA.debugLineNum = 95;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 96;BA.debugLine="r.Target = HorizontalScrollView1";
_r.Target = (Object)(mostCurrent._horizontalscrollview1.getObject());
 //BA.debugLineNum = 97;BA.debugLine="r.RunMethod2(\"setHorizontalScrollBarEnabled\", Fal";
_r.RunMethod2("setHorizontalScrollBarEnabled",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.False),"java.lang.boolean");
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 106;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 100;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
public static String  _btm_service_click() throws Exception{
 //BA.debugLineNum = 140;BA.debugLine="Sub btm_service_Click";
 //BA.debugLineNum = 141;BA.debugLine="StartActivity(Service_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._service_ac.getObject()));
 //BA.debugLineNum = 142;BA.debugLine="End Sub";
return "";
}
public static String  _btn_other_click() throws Exception{
 //BA.debugLineNum = 132;BA.debugLine="Sub btn_other_Click";
 //BA.debugLineNum = 133;BA.debugLine="StartActivity(Profile_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._profile_ac.getObject()));
 //BA.debugLineNum = 134;BA.debugLine="End Sub";
return "";
}
public static String  _btn_profile_click() throws Exception{
 //BA.debugLineNum = 136;BA.debugLine="Sub btn_profile_Click";
 //BA.debugLineNum = 137;BA.debugLine="StartActivity(Sefareshat_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._sefareshat_ac.getObject()));
 //BA.debugLineNum = 138;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
 //BA.debugLineNum = 178;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 179;BA.debugLine="ToastMessageShow(\"بزودی این امکان فعال میشود\",Fal";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("بزودی این امکان فعال میشود"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 180;BA.debugLine="End Sub";
return "";
}
public static String  _button2_click() throws Exception{
 //BA.debugLineNum = 182;BA.debugLine="Sub Button2_Click";
 //BA.debugLineNum = 183;BA.debugLine="StartActivity(Sefareshat_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._sefareshat_ac.getObject()));
 //BA.debugLineNum = 184;BA.debugLine="End Sub";
return "";
}
public static String  _button3_click() throws Exception{
 //BA.debugLineNum = 120;BA.debugLine="Sub Button3_Click";
 //BA.debugLineNum = 121;BA.debugLine="StartActivity(Service_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._service_ac.getObject()));
 //BA.debugLineNum = 122;BA.debugLine="End Sub";
return "";
}
public static String  _button4_click() throws Exception{
 //BA.debugLineNum = 128;BA.debugLine="Sub Button4_Click";
 //BA.debugLineNum = 129;BA.debugLine="StartActivity(url.OpenBrowser(\"https://t.me/hamit";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._url.OpenBrowser("https://t.me/hamitak.ir")));
 //BA.debugLineNum = 130;BA.debugLine="End Sub";
return "";
}
public static String  _button5_click() throws Exception{
 //BA.debugLineNum = 186;BA.debugLine="Sub Button5_Click";
 //BA.debugLineNum = 187;BA.debugLine="ToastMessageShow(\"بزودی این امکان فعال میشود\",Fal";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("بزودی این امکان فعال میشود"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 188;BA.debugLine="End Sub";
return "";
}
public static String  _button6_click() throws Exception{
 //BA.debugLineNum = 190;BA.debugLine="Sub Button6_Click";
 //BA.debugLineNum = 191;BA.debugLine="StartActivity(Profile_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._profile_ac.getObject()));
 //BA.debugLineNum = 192;BA.debugLine="End Sub";
return "";
}
public static String  _button7_click() throws Exception{
 //BA.debugLineNum = 124;BA.debugLine="Sub Button7_Click";
 //BA.debugLineNum = 125;BA.debugLine="StartActivity(Service_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._service_ac.getObject()));
 //BA.debugLineNum = 126;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Private scrollveiw_home As ScrollView";
mostCurrent._scrollveiw_home = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Dim url As PhoneIntents";
mostCurrent._url = new anywheresoftware.b4a.phone.Phone.PhoneIntents();
 //BA.debugLineNum = 14;BA.debugLine="Private ImageView7 As ImageView";
mostCurrent._imageview7 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private HorizontalScrollView1 As HorizontalScroll";
mostCurrent._horizontalscrollview1 = new anywheresoftware.b4a.objects.HorizontalScrollViewWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private Panel4 As Panel";
mostCurrent._panel4 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private ImageView4 As ImageView";
mostCurrent._imageview4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private HorizontalScrollView2 As HorizontalScroll";
mostCurrent._horizontalscrollview2 = new anywheresoftware.b4a.objects.HorizontalScrollViewWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Dim pnl As Panel";
mostCurrent._pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Dim cs As Cursor";
mostCurrent._cs = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private Label11 As Label";
mostCurrent._label11 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private Label24 As Label";
mostCurrent._label24 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private Label7 As Label";
mostCurrent._label7 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private Button1 As Button";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public static String  _imageview2_click() throws Exception{
 //BA.debugLineNum = 116;BA.debugLine="Sub ImageView2_Click";
 //BA.debugLineNum = 117;BA.debugLine="StartActivity(url.OpenBrowser(\"https://t.me/hamit";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._url.OpenBrowser("https://t.me/hamitak.ir")));
 //BA.debugLineNum = 118;BA.debugLine="End Sub";
return "";
}
public static String  _imageview7_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 144;BA.debugLine="Sub ImageView7_Click";
 //BA.debugLineNum = 146;BA.debugLine="Try";
try { //BA.debugLineNum = 147;BA.debugLine="Private i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 148;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"instagram://user?us";
_i.Initialize(_i.ACTION_VIEW,"instagram://user?username="+"hamitak.ir");
 //BA.debugLineNum = 149;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 } 
       catch (Exception e6) {
			processBA.setLastException(e6); //BA.debugLineNum = 151;BA.debugLine="StartActivity(url.OpenBrowser(\"https://instagram";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._url.OpenBrowser("https://instagram.com/hamitak.ir")));
 };
 //BA.debugLineNum = 155;BA.debugLine="End Sub";
return "";
}
public static String  _label24_click() throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.sql.SQL.CursorWrapper _cs1 = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl2 = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl3 = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl4 = null;
 //BA.debugLineNum = 157;BA.debugLine="Sub Label24_Click";
 //BA.debugLineNum = 158;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 159;BA.debugLine="lbl = Sender";
_lbl.setObject((android.widget.TextView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 160;BA.debugLine="Dim cs1 As Cursor";
_cs1 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 161;BA.debugLine="Dim lbl2 As Label";
_lbl2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 162;BA.debugLine="Dim lbl3 As Label";
_lbl3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 163;BA.debugLine="cs1 = Main.sql.ExecQuery(\"SELECT * FROM service_a";
_cs1.setObject((android.database.Cursor)(mostCurrent._main._sql.ExecQuery("SELECT * FROM service_active WHERE servicename = '"+_lbl.getText()+"'")));
 //BA.debugLineNum = 164;BA.debugLine="cs1.Position = 0";
_cs1.setPosition((int) (0));
 //BA.debugLineNum = 165;BA.debugLine="lbl2.Initialize(\"\")";
_lbl2.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 166;BA.debugLine="lbl2.Text = cs1.GetString(\"dbname\")";
_lbl2.setText(BA.ObjectToCharSequence(_cs1.GetString("dbname")));
 //BA.debugLineNum = 167;BA.debugLine="File.WriteString(File.DirInternal,\"servicename.tx";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"servicename.txt",_lbl2.getText());
 //BA.debugLineNum = 168;BA.debugLine="lbl3.Initialize(\"\")";
_lbl3.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 169;BA.debugLine="lbl3.Text = cs1.GetString(\"image\")";
_lbl3.setText(BA.ObjectToCharSequence(_cs1.GetString("image")));
 //BA.debugLineNum = 170;BA.debugLine="File.WriteString(File.DirInternal,\"image.txt\",lbl";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"image.txt",_lbl3.getText());
 //BA.debugLineNum = 171;BA.debugLine="Dim lbl4 As Label";
_lbl4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 172;BA.debugLine="lbl4.Initialize(\"\")";
_lbl4.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 173;BA.debugLine="lbl4.Text = cs1.GetString(\"servicename\")";
_lbl4.setText(BA.ObjectToCharSequence(_cs1.GetString("servicename")));
 //BA.debugLineNum = 174;BA.debugLine="File.WriteString(File.DirInternal,\"servicename3.t";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"servicename3.txt",_lbl4.getText());
 //BA.debugLineNum = 175;BA.debugLine="StartActivity(Services_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._services_ac.getObject()));
 //BA.debugLineNum = 176;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _setsv1_right() throws Exception{
 //BA.debugLineNum = 108;BA.debugLine="Sub setSV1_Right";
 //BA.debugLineNum = 109;BA.debugLine="HorizontalScrollView1.ScrollPosition = Horizontal";
mostCurrent._horizontalscrollview1.setScrollPosition(mostCurrent._horizontalscrollview1.getPanel().getWidth());
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _setsv2_right() throws Exception{
 //BA.debugLineNum = 112;BA.debugLine="Sub setSV2_Right";
 //BA.debugLineNum = 113;BA.debugLine="HorizontalScrollView2.ScrollPosition = Horizontal";
mostCurrent._horizontalscrollview2.setScrollPosition(mostCurrent._horizontalscrollview2.getPanel().getWidth());
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return "";
}
}
