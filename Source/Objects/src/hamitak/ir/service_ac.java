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

public class service_ac extends Activity implements B4AActivity{
	public static service_ac mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hamitak.ir", "hamitak.ir.service_ac");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (service_ac).");
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
		activityBA = new BA(this, layout, processBA, "hamitak.ir", "hamitak.ir.service_ac");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hamitak.ir.service_ac", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (service_ac) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (service_ac) Resume **");
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
		return service_ac.class;
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
        BA.LogInfo("** Activity (service_ac) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            service_ac mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (service_ac) Resume **");
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
public anywheresoftware.b4a.objects.PanelWrapper _panel4 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_profile = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label11 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview4 = null;
public anywheresoftware.b4a.sql.SQL.CursorWrapper _cs = null;
public anywheresoftware.b4a.objects.LabelWrapper _label50 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl3 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public hamitak.ir.main _main = null;
public hamitak.ir.verify_ac _verify_ac = null;
public hamitak.ir.firsactivity _firsactivity = null;
public hamitak.ir.home _home = null;
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
int _top = 0;
int _i = 0;
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
 //BA.debugLineNum = 24;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 25;BA.debugLine="Activity.LoadLayout(\"Service\")";
mostCurrent._activity.LoadLayout("Service",mostCurrent.activityBA);
 //BA.debugLineNum = 27;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 28;BA.debugLine="r.Target = scrollveiw_home";
_r.Target = (Object)(mostCurrent._scrollveiw_home.getObject());
 //BA.debugLineNum = 29;BA.debugLine="r.RunMethod2(\"setVerticalScrollBarEnabled\", False";
_r.RunMethod2("setVerticalScrollBarEnabled",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.False),"java.lang.boolean");
 //BA.debugLineNum = 30;BA.debugLine="cs = Main.sql.ExecQuery(\"SELECT * FROM service_ac";
mostCurrent._cs.setObject((android.database.Cursor)(mostCurrent._main._sql.ExecQuery("SELECT * FROM service_active ORDER BY id")));
 //BA.debugLineNum = 31;BA.debugLine="Dim top As Int = 10";
_top = (int) (10);
 //BA.debugLineNum = 32;BA.debugLine="For i = 0 To cs.RowCount -1";
{
final int step7 = 1;
final int limit7 = (int) (mostCurrent._cs.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 33;BA.debugLine="cs.Position=i";
mostCurrent._cs.setPosition(_i);
 //BA.debugLineNum = 34;BA.debugLine="Dim pnl As Panel";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="pnl.Initialize(\"pnl\")";
_pnl.Initialize(mostCurrent.activityBA,"pnl");
 //BA.debugLineNum = 36;BA.debugLine="pnl.LoadLayout(\"Service_page_ScrollView\")";
_pnl.LoadLayout("Service_page_ScrollView",mostCurrent.activityBA);
 //BA.debugLineNum = 37;BA.debugLine="pnl.Tag = i";
_pnl.setTag((Object)(_i));
 //BA.debugLineNum = 38;BA.debugLine="scrollveiw_home.Panel.AddView(pnl,Panel4.Left+5d";
mostCurrent._scrollveiw_home.getPanel().AddView((android.view.View)(_pnl.getObject()),(int) (mostCurrent._panel4.getLeft()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),_top,mostCurrent._panel4.getWidth(),(int) (mostCurrent._panel4.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3))));
 //BA.debugLineNum = 39;BA.debugLine="top = top + Panel4.Height + 7dip";
_top = (int) (_top+mostCurrent._panel4.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7)));
 //BA.debugLineNum = 40;BA.debugLine="If pnl.tag = i Then";
if ((_pnl.getTag()).equals((Object)(_i))) { 
 //BA.debugLineNum = 41;BA.debugLine="Label11.Text = cs.GetString(\"servicename\")";
mostCurrent._label11.setText(BA.ObjectToCharSequence(mostCurrent._cs.GetString("servicename")));
 //BA.debugLineNum = 42;BA.debugLine="Label50.Text = Label11.Text";
mostCurrent._label50.setText(BA.ObjectToCharSequence(mostCurrent._label11.getText()));
 //BA.debugLineNum = 43;BA.debugLine="ImageView4.Bitmap = LoadBitmap(File.DirAssets,c";
mostCurrent._imageview4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),mostCurrent._cs.GetString("image")).getObject()));
 };
 //BA.debugLineNum = 45;BA.debugLine="scrollveiw_home.Panel.Height = top + 3dip";
mostCurrent._scrollveiw_home.getPanel().setHeight((int) (_top+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3))));
 }
};
 //BA.debugLineNum = 47;BA.debugLine="cs.Close";
mostCurrent._cs.Close();
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 54;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 50;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static String  _btn_home_click() throws Exception{
 //BA.debugLineNum = 70;BA.debugLine="Sub btn_home_Click";
 //BA.debugLineNum = 71;BA.debugLine="StartActivity(Home)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._home.getObject()));
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return "";
}
public static String  _btn_other_click() throws Exception{
 //BA.debugLineNum = 66;BA.debugLine="Sub btn_other_Click";
 //BA.debugLineNum = 67;BA.debugLine="StartActivity(Profile_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._profile_ac.getObject()));
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _btn_profile_click() throws Exception{
 //BA.debugLineNum = 74;BA.debugLine="Sub btn_profile_Click";
 //BA.debugLineNum = 75;BA.debugLine="StartActivity(Sefareshat_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._sefareshat_ac.getObject()));
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Private scrollveiw_home As ScrollView";
mostCurrent._scrollveiw_home = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Private Panel4 As Panel";
mostCurrent._panel4 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private btn_profile As Button";
mostCurrent._btn_profile = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private Label11 As Label";
mostCurrent._label11 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private ImageView4 As ImageView";
mostCurrent._imageview4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Dim cs As Cursor";
mostCurrent._cs = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private Label50 As Label";
mostCurrent._label50 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Public lbl As Label";
mostCurrent._lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Dim lbl2 As Label";
mostCurrent._lbl2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Dim lbl3 As Label";
mostCurrent._lbl3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
public static String  _label1_click() throws Exception{
 //BA.debugLineNum = 58;BA.debugLine="Sub Label1_Click";
 //BA.debugLineNum = 59;BA.debugLine="StartActivity(Home)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._home.getObject()));
 //BA.debugLineNum = 60;BA.debugLine="End Sub";
return "";
}
public static String  _label11_click() throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cs1 = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl4 = null;
 //BA.debugLineNum = 79;BA.debugLine="Sub Label11_Click";
 //BA.debugLineNum = 80;BA.debugLine="Dim cs1 As Cursor";
_cs1 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 81;BA.debugLine="lbl = Sender";
mostCurrent._lbl.setObject((android.widget.TextView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 82;BA.debugLine="cs1 = Main.sql.ExecQuery(\"SELECT * FROM service_a";
_cs1.setObject((android.database.Cursor)(mostCurrent._main._sql.ExecQuery("SELECT * FROM service_active WHERE servicename = '"+mostCurrent._lbl.getText()+"'")));
 //BA.debugLineNum = 83;BA.debugLine="cs1.Position = 0";
_cs1.setPosition((int) (0));
 //BA.debugLineNum = 84;BA.debugLine="lbl2.Initialize(\"\")";
mostCurrent._lbl2.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 85;BA.debugLine="lbl2.Text = cs1.GetString(\"dbname\")";
mostCurrent._lbl2.setText(BA.ObjectToCharSequence(_cs1.GetString("dbname")));
 //BA.debugLineNum = 86;BA.debugLine="File.WriteString(File.DirInternal,\"servicename.tx";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"servicename.txt",mostCurrent._lbl2.getText());
 //BA.debugLineNum = 87;BA.debugLine="lbl3.Initialize(\"\")";
mostCurrent._lbl3.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 88;BA.debugLine="lbl3.Text = cs1.GetString(\"image\")";
mostCurrent._lbl3.setText(BA.ObjectToCharSequence(_cs1.GetString("image")));
 //BA.debugLineNum = 89;BA.debugLine="File.WriteString(File.DirInternal,\"image.txt\",lbl";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"image.txt",mostCurrent._lbl3.getText());
 //BA.debugLineNum = 90;BA.debugLine="Dim lbl4 As Label";
_lbl4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 91;BA.debugLine="lbl4.Initialize(\"\")";
_lbl4.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 92;BA.debugLine="lbl4.Text = cs1.GetString(\"servicename\")";
_lbl4.setText(BA.ObjectToCharSequence(_cs1.GetString("servicename")));
 //BA.debugLineNum = 93;BA.debugLine="File.WriteString(File.DirInternal,\"servicename3.t";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"servicename3.txt",_lbl4.getText());
 //BA.debugLineNum = 94;BA.debugLine="StartActivity(Services_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._services_ac.getObject()));
 //BA.debugLineNum = 95;BA.debugLine="End Sub";
return "";
}
public static String  _label3_click() throws Exception{
 //BA.debugLineNum = 62;BA.debugLine="Sub Label3_Click";
 //BA.debugLineNum = 63;BA.debugLine="StartActivity(Profile_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._profile_ac.getObject()));
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
}
