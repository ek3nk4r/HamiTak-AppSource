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

public class profile_ac extends Activity implements B4AActivity{
	public static profile_ac mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hamitak.ir", "hamitak.ir.profile_ac");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (profile_ac).");
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
		activityBA = new BA(this, layout, processBA, "hamitak.ir", "hamitak.ir.profile_ac");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hamitak.ir.profile_ac", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (profile_ac) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (profile_ac) Resume **");
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
		return profile_ac.class;
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
        BA.LogInfo("** Activity (profile_ac) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            profile_ac mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (profile_ac) Resume **");
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
public anywheresoftware.b4a.phone.Phone.PhoneIntents _url = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrollveiw_home = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_call = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_channel = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_insta = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_site = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_telegram = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public hamitak.ir.main _main = null;
public hamitak.ir.verify_ac _verify_ac = null;
public hamitak.ir.firsactivity _firsactivity = null;
public hamitak.ir.home _home = null;
public hamitak.ir.service_ac _service_ac = null;
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
 //BA.debugLineNum = 20;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 21;BA.debugLine="Activity.LoadLayout(\"Profile\")";
mostCurrent._activity.LoadLayout("Profile",mostCurrent.activityBA);
 //BA.debugLineNum = 22;BA.debugLine="scrollveiw_home.Panel.LoadLayout(\"Profile_ScrollV";
mostCurrent._scrollveiw_home.getPanel().LoadLayout("Profile_ScrollView",mostCurrent.activityBA);
 //BA.debugLineNum = 23;BA.debugLine="scrollveiw_home.Panel.Height = btn_insta.Top + bt";
mostCurrent._scrollveiw_home.getPanel().setHeight((int) (mostCurrent._btn_insta.getTop()+mostCurrent._btn_insta.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7))));
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 30;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 26;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return "";
}
public static String  _btm_service_click() throws Exception{
 //BA.debugLineNum = 74;BA.debugLine="Sub btm_service_Click";
 //BA.debugLineNum = 75;BA.debugLine="StartActivity(Service_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._service_ac.getObject()));
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _btn_call_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 60;BA.debugLine="Sub btn_call_Click";
 //BA.debugLineNum = 61;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 62;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"tel:04446333610\")";
_i.Initialize(_i.ACTION_VIEW,"tel:04446333610");
 //BA.debugLineNum = 63;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return "";
}
public static String  _btn_channel_click() throws Exception{
 //BA.debugLineNum = 56;BA.debugLine="Sub btn_channel_Click";
 //BA.debugLineNum = 57;BA.debugLine="StartActivity(url.OpenBrowser(\"https://t.me/hamit";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._url.OpenBrowser("https://t.me/hamitak.ir")));
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
return "";
}
public static String  _btn_home_click() throws Exception{
 //BA.debugLineNum = 66;BA.debugLine="Sub btn_home_Click";
 //BA.debugLineNum = 67;BA.debugLine="StartActivity(Home)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._home.getObject()));
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _btn_insta_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 46;BA.debugLine="Sub btn_insta_Click";
 //BA.debugLineNum = 47;BA.debugLine="Try";
try { //BA.debugLineNum = 48;BA.debugLine="Private i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 49;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"instagram://user?us";
_i.Initialize(_i.ACTION_VIEW,"instagram://user?username="+"hamitak.ir");
 //BA.debugLineNum = 50;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 } 
       catch (Exception e6) {
			processBA.setLastException(e6); //BA.debugLineNum = 52;BA.debugLine="StartActivity(url.OpenBrowser(\"https://instagram";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._url.OpenBrowser("https://instagram.com/hamitak.ir")));
 };
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return "";
}
public static String  _btn_profile_click() throws Exception{
 //BA.debugLineNum = 70;BA.debugLine="Sub btn_profile_Click";
 //BA.debugLineNum = 71;BA.debugLine="StartActivity(Sefareshat_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._sefareshat_ac.getObject()));
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return "";
}
public static String  _btn_site_click() throws Exception{
 //BA.debugLineNum = 42;BA.debugLine="Sub btn_site_Click";
 //BA.debugLineNum = 43;BA.debugLine="ToastMessageShow(\"سایت حامی تک در دست ساخت میباشد";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("سایت حامی تک در دست ساخت میباشد"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return "";
}
public static String  _btn_telegram_click() throws Exception{
 //BA.debugLineNum = 38;BA.debugLine="Sub btn_telegram_Click";
 //BA.debugLineNum = 39;BA.debugLine="StartActivity(url.OpenBrowser(\"https://t.me/hamit";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._url.OpenBrowser("https://t.me/hamitak.ir")));
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Dim url As PhoneIntents";
mostCurrent._url = new anywheresoftware.b4a.phone.Phone.PhoneIntents();
 //BA.debugLineNum = 12;BA.debugLine="Private scrollveiw_home As ScrollView";
mostCurrent._scrollveiw_home = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private btn_call As Button";
mostCurrent._btn_call = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private btn_channel As Button";
mostCurrent._btn_channel = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private btn_insta As Button";
mostCurrent._btn_insta = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private btn_site As Button";
mostCurrent._btn_site = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private btn_telegram As Button";
mostCurrent._btn_telegram = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public static String  _label1_click() throws Exception{
 //BA.debugLineNum = 34;BA.debugLine="Sub Label1_Click";
 //BA.debugLineNum = 35;BA.debugLine="StartActivity(Home)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._home.getObject()));
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
}
