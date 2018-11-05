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

public class firsactivity extends Activity implements B4AActivity{
	public static firsactivity mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hamitak.ir", "hamitak.ir.firsactivity");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (firsactivity).");
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
		activityBA = new BA(this, layout, processBA, "hamitak.ir", "hamitak.ir.firsactivity");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hamitak.ir.firsactivity", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (firsactivity) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (firsactivity) Resume **");
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
		return firsactivity.class;
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
        BA.LogInfo("** Activity (firsactivity) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            firsactivity mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (firsactivity) Resume **");
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
public static String _aas = "";
public static anywheresoftware.b4a.objects.Timer _tim = null;
public de.amberhome.viewpager.AHViewPager _ahviewpager1 = null;
public de.amberhome.viewpager.AHPageContainer _pc = null;
public de.amberhome.objects.TabLayoutWrapper _dstablayout1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _login_btn = null;
public anywheresoftware.b4a.objects.EditTextWrapper _pass_txt = null;
public anywheresoftware.b4a.objects.EditTextWrapper _name_txt = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _register_btn = null;
public anywheresoftware.b4a.samples.httputils2.httpjob _ht_reg = null;
public anywheresoftware.b4a.objects.EditTextWrapper _pass_txt1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _phone_txt1 = null;
public anywheresoftware.b4a.samples.httputils2.httpjob _ht_log = null;
public ir.EditTextPlus.pejman.EditTextPlus _edittextplus = null;
public anywheresoftware.b4a.phone.Phone _phone = null;
public anywheresoftware.b4a.objects.EditTextWrapper _phone_txt = null;
public de.amberhome.objects.appcompat.AppCompatBase _ac = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public hamitak.ir.main _main = null;
public hamitak.ir.verify_ac _verify_ac = null;
public hamitak.ir.home _home = null;
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
int _i = 0;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
 //BA.debugLineNum = 35;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 37;BA.debugLine="Activity.LoadLayout(\"Log_reg\")";
mostCurrent._activity.LoadLayout("Log_reg",mostCurrent.activityBA);
 //BA.debugLineNum = 39;BA.debugLine="Main.sql.Initialize(File.DirInternal,\"data.db\",Fa";
mostCurrent._main._sql.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"data.db",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 42;BA.debugLine="ht_reg.Initialize(\"ht_reg\",Me)";
mostCurrent._ht_reg._initialize(processBA,"ht_reg",firsactivity.getObject());
 //BA.debugLineNum = 43;BA.debugLine="ht_log.Initialize(\"ht_log\",Me)";
mostCurrent._ht_log._initialize(processBA,"ht_log",firsactivity.getObject());
 //BA.debugLineNum = 45;BA.debugLine="PC.Initialize";
mostCurrent._pc.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 47;BA.debugLine="For i=0 To 1";
{
final int step6 = 1;
final int limit6 = (int) (1);
_i = (int) (0) ;
for (;_i <= limit6 ;_i = _i + step6 ) {
 //BA.debugLineNum = 48;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 49;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 50;BA.debugLine="p.Color=Colors.White";
_p.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 51;BA.debugLine="If i=0 Then p.LoadLayout(\"login\")";
if (_i==0) { 
_p.LoadLayout("login",mostCurrent.activityBA);};
 //BA.debugLineNum = 52;BA.debugLine="If i=1 Then p.LoadLayout(\"register\")";
if (_i==1) { 
_p.LoadLayout("register",mostCurrent.activityBA);};
 //BA.debugLineNum = 53;BA.debugLine="PC.AddPage(p,\"\")";
mostCurrent._pc.AddPage((android.view.View)(_p.getObject()),"");
 }
};
 //BA.debugLineNum = 57;BA.debugLine="AHViewPager1.PageContainer = PC";
mostCurrent._ahviewpager1.setPageContainer(mostCurrent._pc);
 //BA.debugLineNum = 59;BA.debugLine="DSTabLayout1.SetViewPager(AHViewPager1)";
mostCurrent._dstablayout1.SetViewPager(mostCurrent._ahviewpager1);
 //BA.debugLineNum = 62;BA.debugLine="DSTabLayout1.Color=ac.GetThemeAttribute(\"colorPri";
mostCurrent._dstablayout1.setColor(mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorPrimary"));
 //BA.debugLineNum = 63;BA.debugLine="DSTabLayout1.TabIndicatorColor = ac.GetThemeAttri";
mostCurrent._dstablayout1.setTabIndicatorColor(mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorAccent"));
 //BA.debugLineNum = 65;BA.debugLine="DSTabLayout1.SetTabText(1,\"ثبت نام\")";
mostCurrent._dstablayout1.SetTabText((int) (1),"ثبت نام");
 //BA.debugLineNum = 66;BA.debugLine="DSTabLayout1.SetTabText(0,\"ورود\")";
mostCurrent._dstablayout1.SetTabText((int) (0),"ورود");
 //BA.debugLineNum = 68;BA.debugLine="DSTabLayout1.SetTabTextColors(Colors.White,ac.Get";
mostCurrent._dstablayout1.SetTabTextColors(anywheresoftware.b4a.keywords.Common.Colors.White,mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorAccent"));
 //BA.debugLineNum = 70;BA.debugLine="DSTabLayout1.SelectTab(1)";
mostCurrent._dstablayout1.SelectTab((int) (1));
 //BA.debugLineNum = 71;BA.debugLine="login_btn.ButtonColor = ac.GetThemeAttribute(\"col";
mostCurrent._login_btn.setButtonColor(mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorAccent"));
 //BA.debugLineNum = 72;BA.debugLine="Label1.TextColor = ac.GetThemeAttribute(\"colorAcc";
mostCurrent._label1.setTextColor(mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorAccent"));
 //BA.debugLineNum = 73;BA.debugLine="SetTypeface(DSTabLayout1,Typeface.LoadFromAssets(";
_settypeface((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._dstablayout1.getObject())),(anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("iran-sans.ttf"))));
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 95;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 90;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 15;BA.debugLine="Private AHViewPager1 As AHViewPager";
mostCurrent._ahviewpager1 = new de.amberhome.viewpager.AHViewPager();
 //BA.debugLineNum = 16;BA.debugLine="Private PC As AHPageContainer";
mostCurrent._pc = new de.amberhome.viewpager.AHPageContainer();
 //BA.debugLineNum = 17;BA.debugLine="Private DSTabLayout1 As DSTabLayout";
mostCurrent._dstablayout1 = new de.amberhome.objects.TabLayoutWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private login_btn As ACButton";
mostCurrent._login_btn = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private pass_txt As EditText";
mostCurrent._pass_txt = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private name_txt As EditText";
mostCurrent._name_txt = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private register_btn As ACButton";
mostCurrent._register_btn = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Dim ht_reg As HttpJob";
mostCurrent._ht_reg = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 24;BA.debugLine="Private pass_txt1 As EditText";
mostCurrent._pass_txt1 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private phone_txt1 As EditText";
mostCurrent._phone_txt1 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private login_btn As ACButton";
mostCurrent._login_btn = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private pass_txt1 As EditText";
mostCurrent._pass_txt1 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Dim ht_log As HttpJob";
mostCurrent._ht_log = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 29;BA.debugLine="Dim EditTextPlus As EditTextPlus";
mostCurrent._edittextplus = new ir.EditTextPlus.pejman.EditTextPlus();
 //BA.debugLineNum = 30;BA.debugLine="Dim Phone As Phone";
mostCurrent._phone = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 31;BA.debugLine="Private phone_txt As EditText";
mostCurrent._phone_txt = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim ac As AppCompat";
mostCurrent._ac = new de.amberhome.objects.appcompat.AppCompatBase();
 //BA.debugLineNum = 33;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cursor2 = null;
de.amberhome.objects.SnackbarWrapper _snackbar = null;
anywheresoftware.b4a.sql.SQL.CursorWrapper _cursor1 = null;
 //BA.debugLineNum = 154;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 156;BA.debugLine="If Job.Tag = \"ht_regg\" Then";
if ((_job._tag).equals((Object)("ht_regg"))) { 
 //BA.debugLineNum = 157;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 158;BA.debugLine="If Job.GetString = \"OK\" Then";
if ((_job._getstring()).equals("OK")) { 
 //BA.debugLineNum = 159;BA.debugLine="ht_reg.PostString(\"http://apple-acc.ir/appmark";
mostCurrent._ht_reg._poststring("http://apple-acc.ir/appmarket//tl_signup.php","msg="+"ثبت نام کاربر جدید"+anywheresoftware.b4a.keywords.Common.CRLF+"نام و نام خانوادگی: "+mostCurrent._name_txt.getText()+anywheresoftware.b4a.keywords.Common.CRLF+"رمزعبور: "+mostCurrent._pass_txt.getText()+anywheresoftware.b4a.keywords.Common.CRLF+"شماره موبایل: "+mostCurrent._phone_txt.getText()+anywheresoftware.b4a.keywords.Common.CRLF+"گوشی کابر: "+mostCurrent._phone.getProduct()+" _ "+mostCurrent._phone.getModel());
 //BA.debugLineNum = 160;BA.debugLine="Dim Cursor2 As Cursor";
_cursor2 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 161;BA.debugLine="Cursor2 = Main.sql1.ExecQuery(\"UPDATE tbl_prof";
_cursor2.setObject((android.database.Cursor)(mostCurrent._main._sql1.ExecQuery("UPDATE tbl_profile SET Name_Family = '"+mostCurrent._name_txt.getText()+"',Phone = '"+mostCurrent._phone_txt.getText()+"'")));
 //BA.debugLineNum = 162;BA.debugLine="Cursor2.Position = 0";
_cursor2.setPosition((int) (0));
 //BA.debugLineNum = 163;BA.debugLine="Cursor2.Close";
_cursor2.Close();
 //BA.debugLineNum = 164;BA.debugLine="tim.Initialize(\"tim1\",4000)";
_tim.Initialize(processBA,"tim1",(long) (4000));
 //BA.debugLineNum = 165;BA.debugLine="tim.Enabled = True";
_tim.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else if((_job._getstring()).equals("OLD")) { 
 //BA.debugLineNum = 167;BA.debugLine="EditTextPlus.setError(phone_txt,\"قبلا با این ش";
mostCurrent._edittextplus.setError((android.widget.EditText)(mostCurrent._phone_txt.getObject()),"قبلا با این شماره موبایل ثبت نام انجام شده است");
 //BA.debugLineNum = 168;BA.debugLine="tim.Enabled = False";
_tim.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 169;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 };
 }else {
 //BA.debugLineNum = 172;BA.debugLine="Dim snackbar As DSSnackbar";
_snackbar = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 173;BA.debugLine="snackbar.Initialize(\"snack4\",Activity,\"خطا در ب";
_snackbar.Initialize(mostCurrent.activityBA,"snack4",(android.view.View)(mostCurrent._activity.getObject()),"خطا در برقراری ارتباط با سرور",_snackbar.DURATION_LONG);
 //BA.debugLineNum = 174;BA.debugLine="SetSnackBarBackground(snackbar,ac.GetThemeAttri";
_setsnackbarbackground(_snackbar,mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorAccent"));
 //BA.debugLineNum = 175;BA.debugLine="SetSnackBarTextColor(snackbar,Colors.White)";
_setsnackbartextcolor(_snackbar,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 176;BA.debugLine="snackbar.Show";
_snackbar.Show();
 //BA.debugLineNum = 177;BA.debugLine="tim.Enabled = False";
_tim.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 178;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 };
 };
 //BA.debugLineNum = 182;BA.debugLine="If Job.Tag = \"ht_logg\" Then";
if ((_job._tag).equals((Object)("ht_logg"))) { 
 //BA.debugLineNum = 183;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 184;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 185;BA.debugLine="If Job.GetString = \"OK\" Then";
if ((_job._getstring()).equals("OK")) { 
 //BA.debugLineNum = 186;BA.debugLine="Dim snackbar As DSSnackbar";
_snackbar = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 187;BA.debugLine="snackbar.Initialize(\"snack1\",Activity,\"با موفق";
_snackbar.Initialize(mostCurrent.activityBA,"snack1",(android.view.View)(mostCurrent._activity.getObject()),"با موفقیت وارد شدید",_snackbar.DURATION_LONG);
 //BA.debugLineNum = 188;BA.debugLine="SetSnackBarBackground(snackbar,ac.GetThemeAttr";
_setsnackbarbackground(_snackbar,mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorAccent"));
 //BA.debugLineNum = 189;BA.debugLine="SetSnackBarTextColor(snackbar,Colors.White)";
_setsnackbartextcolor(_snackbar,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 190;BA.debugLine="snackbar.Show";
_snackbar.Show();
 //BA.debugLineNum = 192;BA.debugLine="Dim Cursor1 As Cursor";
_cursor1 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 193;BA.debugLine="Cursor1 = Main.sql1.ExecQuery(\"UPDATE tbl_prof";
_cursor1.setObject((android.database.Cursor)(mostCurrent._main._sql1.ExecQuery("UPDATE tbl_profile SET Name_Family = '', FirstOpen='1', Phone = '"+mostCurrent._phone_txt1.getText()+"'")));
 //BA.debugLineNum = 194;BA.debugLine="Cursor1.Position = 0";
_cursor1.setPosition((int) (0));
 //BA.debugLineNum = 195;BA.debugLine="Cursor1.Close";
_cursor1.Close();
 //BA.debugLineNum = 196;BA.debugLine="StartActivity(Home)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._home.getObject()));
 //BA.debugLineNum = 197;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else if((_job._getstring()).equals("WRONG")) { 
 //BA.debugLineNum = 200;BA.debugLine="Dim snackbar As DSSnackbar";
_snackbar = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 201;BA.debugLine="snackbar.Initialize(\"snack1\",Activity,\"شماره م";
_snackbar.Initialize(mostCurrent.activityBA,"snack1",(android.view.View)(mostCurrent._activity.getObject()),"شماره موبایل یا رمزعبور اشتباه است",_snackbar.DURATION_LONG);
 //BA.debugLineNum = 202;BA.debugLine="SetSnackBarBackground(snackbar,ac.GetThemeAttr";
_setsnackbarbackground(_snackbar,mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorAccent"));
 //BA.debugLineNum = 203;BA.debugLine="SetSnackBarTextColor(snackbar,Colors.White)";
_setsnackbartextcolor(_snackbar,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 204;BA.debugLine="snackbar.Show";
_snackbar.Show();
 }else {
 //BA.debugLineNum = 207;BA.debugLine="Dim snackbar As DSSnackbar";
_snackbar = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 208;BA.debugLine="snackbar.Initialize(\"snack2\",Activity,\"خطای نا";
_snackbar.Initialize(mostCurrent.activityBA,"snack2",(android.view.View)(mostCurrent._activity.getObject()),"خطای ناشناخته",_snackbar.DURATION_LONG);
 //BA.debugLineNum = 209;BA.debugLine="SetSnackBarBackground(snackbar,ac.GetThemeAttr";
_setsnackbarbackground(_snackbar,mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorAccent"));
 //BA.debugLineNum = 210;BA.debugLine="SetSnackBarTextColor(snackbar,Colors.White)";
_setsnackbartextcolor(_snackbar,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 211;BA.debugLine="snackbar.Show";
_snackbar.Show();
 };
 }else {
 //BA.debugLineNum = 214;BA.debugLine="Dim snackbar As DSSnackbar";
_snackbar = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 215;BA.debugLine="snackbar.Initialize(\"snack3\",Activity,\"خطا در ب";
_snackbar.Initialize(mostCurrent.activityBA,"snack3",(android.view.View)(mostCurrent._activity.getObject()),"خطا در برقراری ارتباط با سرور",_snackbar.DURATION_LONG);
 //BA.debugLineNum = 216;BA.debugLine="SetSnackBarBackground(snackbar,ac.GetThemeAttri";
_setsnackbarbackground(_snackbar,mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorAccent"));
 //BA.debugLineNum = 217;BA.debugLine="SetSnackBarTextColor(snackbar,Colors.White)";
_setsnackbartextcolor(_snackbar,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 218;BA.debugLine="snackbar.Show";
_snackbar.Show();
 };
 };
 //BA.debugLineNum = 221;BA.debugLine="End Sub";
return "";
}
public static String  _label1_click() throws Exception{
 //BA.debugLineNum = 250;BA.debugLine="Sub Label1_Click";
 //BA.debugLineNum = 252;BA.debugLine="End Sub";
return "";
}
public static String  _login_btn_click() throws Exception{
 //BA.debugLineNum = 134;BA.debugLine="Sub login_btn_Click";
 //BA.debugLineNum = 135;BA.debugLine="If phone_txt1.Text.Length > 11 Then";
if (mostCurrent._phone_txt1.getText().length()>11) { 
 //BA.debugLineNum = 136;BA.debugLine="EditTextPlus.setError(phone_txt1,\"لطفا شماره موب";
mostCurrent._edittextplus.setError((android.widget.EditText)(mostCurrent._phone_txt1.getObject()),"لطفا شماره موبایل را به درستی وارد کنید");
 //BA.debugLineNum = 137;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 139;BA.debugLine="If phone_txt1.Text.Length < 11 Then";
if (mostCurrent._phone_txt1.getText().length()<11) { 
 //BA.debugLineNum = 140;BA.debugLine="EditTextPlus.setError(phone_txt1,\"لطفا شماره موب";
mostCurrent._edittextplus.setError((android.widget.EditText)(mostCurrent._phone_txt1.getObject()),"لطفا شماره موبایل را به درستی وارد کنید");
 //BA.debugLineNum = 141;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 144;BA.debugLine="If pass_txt1.Text.Length < 6 Then";
if (mostCurrent._pass_txt1.getText().length()<6) { 
 //BA.debugLineNum = 145;BA.debugLine="EditTextPlus.setError(pass_txt1,\"رمزعبور نمی توا";
mostCurrent._edittextplus.setError((android.widget.EditText)(mostCurrent._pass_txt1.getObject()),"رمزعبور نمی تواند کمتر از شش حرف باشد");
 //BA.debugLineNum = 146;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 149;BA.debugLine="ProgressDialogShow(\"لطفا صبر کنید\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("لطفا صبر کنید"));
 //BA.debugLineNum = 150;BA.debugLine="ht_log.PostString(\"http://apple-acc.ir/appmarket/";
mostCurrent._ht_log._poststring("http://apple-acc.ir/appmarket//login.php","phone="+mostCurrent._phone_txt1.getText()+"&password="+mostCurrent._pass_txt1.getText());
 //BA.debugLineNum = 151;BA.debugLine="ht_log.Tag = \"ht_logg\"";
mostCurrent._ht_log._tag = (Object)("ht_logg");
 //BA.debugLineNum = 152;BA.debugLine="End Sub";
return "";
}
public static String  _panel1_click() throws Exception{
 //BA.debugLineNum = 254;BA.debugLine="Sub Panel1_Click";
 //BA.debugLineNum = 256;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Dim aas As String";
_aas = "";
 //BA.debugLineNum = 9;BA.debugLine="Dim tim As Timer";
_tim = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _register_btn_click() throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub register_btn_Click";
 //BA.debugLineNum = 101;BA.debugLine="If name_txt.Text.Length < 5 Then";
if (mostCurrent._name_txt.getText().length()<5) { 
 //BA.debugLineNum = 102;BA.debugLine="EditTextPlus.setError(name_txt,\"نام و نام خانواد";
mostCurrent._edittextplus.setError((android.widget.EditText)(mostCurrent._name_txt.getObject()),"نام و نام خانوادگی نمیتواند کمتر از پنج حرف باشد");
 //BA.debugLineNum = 103;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 106;BA.debugLine="If phone_txt.Text.Length > 11 Then";
if (mostCurrent._phone_txt.getText().length()>11) { 
 //BA.debugLineNum = 107;BA.debugLine="EditTextPlus.setError(phone_txt,\"لطفا شماره موبا";
mostCurrent._edittextplus.setError((android.widget.EditText)(mostCurrent._phone_txt.getObject()),"لطفا شماره موبایل را به درستی وارد کنید");
 //BA.debugLineNum = 108;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 110;BA.debugLine="If phone_txt.Text.Length < 11 Then";
if (mostCurrent._phone_txt.getText().length()<11) { 
 //BA.debugLineNum = 111;BA.debugLine="EditTextPlus.setError(phone_txt,\"لطفا شماره موبا";
mostCurrent._edittextplus.setError((android.widget.EditText)(mostCurrent._phone_txt.getObject()),"لطفا شماره موبایل را به درستی وارد کنید");
 //BA.debugLineNum = 112;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 115;BA.debugLine="If pass_txt.Text.Length < 6 Then";
if (mostCurrent._pass_txt.getText().length()<6) { 
 //BA.debugLineNum = 116;BA.debugLine="EditTextPlus.setError(pass_txt,\"رمزعبور نمی توان";
mostCurrent._edittextplus.setError((android.widget.EditText)(mostCurrent._pass_txt.getObject()),"رمزعبور نمی تواند کمتر از شش حرف باشد");
 //BA.debugLineNum = 117;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 121;BA.debugLine="aas = phone_txt.Text";
_aas = mostCurrent._phone_txt.getText();
 //BA.debugLineNum = 122;BA.debugLine="File.WriteString(File.DirInternal,\"phone_txt.txt\"";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"phone_txt.txt",_aas);
 //BA.debugLineNum = 125;BA.debugLine="ht_reg.PostString(\"http://apple-acc.ir/appmarket/";
mostCurrent._ht_reg._poststring("http://apple-acc.ir/appmarket//signup.php","username="+mostCurrent._name_txt.getText()+"&password="+mostCurrent._pass_txt.getText()+"&phone="+mostCurrent._phone_txt.getText());
 //BA.debugLineNum = 126;BA.debugLine="ht_reg.Tag = \"ht_regg\"";
mostCurrent._ht_reg._tag = (Object)("ht_regg");
 //BA.debugLineNum = 129;BA.debugLine="ProgressDialogShow(\"در حال ثبت اطلاعات...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("در حال ثبت اطلاعات..."));
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public static String  _setsnackbarbackground(de.amberhome.objects.SnackbarWrapper _psnack,int _pcolor) throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
 //BA.debugLineNum = 223;BA.debugLine="Public Sub SetSnackBarBackground(pSnack As DSSnack";
 //BA.debugLineNum = 224;BA.debugLine="Dim v As View";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
 //BA.debugLineNum = 225;BA.debugLine="v = pSnack.View";
_v.setObject((android.view.View)(_psnack.getView()));
 //BA.debugLineNum = 226;BA.debugLine="v.Color = pColor";
_v.setColor(_pcolor);
 //BA.debugLineNum = 227;BA.debugLine="End Sub";
return "";
}
public static String  _setsnackbartextcolor(de.amberhome.objects.SnackbarWrapper _psnack,int _pcolor) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.LabelWrapper _textv = null;
 //BA.debugLineNum = 229;BA.debugLine="Public Sub SetSnackBarTextColor(pSnack As DSSnackb";
 //BA.debugLineNum = 230;BA.debugLine="Dim p As Panel = pSnack.View";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p.setObject((android.view.ViewGroup)(_psnack.getView()));
 //BA.debugLineNum = 231;BA.debugLine="For Each v As View In p.GetAllViewsRecursive";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
{
final anywheresoftware.b4a.BA.IterableList group2 = _p.GetAllViewsRecursive();
final int groupLen2 = group2.getSize()
;int index2 = 0;
;
for (; index2 < groupLen2;index2++){
_v.setObject((android.view.View)(group2.Get(index2)));
 //BA.debugLineNum = 232;BA.debugLine="If v Is Label Then";
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 233;BA.debugLine="Dim textv As Label";
_textv = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 234;BA.debugLine="textv = v";
_textv.setObject((android.widget.TextView)(_v.getObject()));
 //BA.debugLineNum = 235;BA.debugLine="textv.TextColor = pColor";
_textv.setTextColor(_pcolor);
 //BA.debugLineNum = 236;BA.debugLine="textv.Typeface = Typeface.LoadFromAssets(\"iran-";
_textv.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("iran-sans.ttf"));
 //BA.debugLineNum = 237;BA.debugLine="textv.TextSize = 11";
_textv.setTextSize((float) (11));
 //BA.debugLineNum = 238;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 241;BA.debugLine="End Sub";
return "";
}
public static String  _settypeface(anywheresoftware.b4a.objects.PanelWrapper _parent,anywheresoftware.b4a.keywords.constants.TypefaceWrapper _t) throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
 //BA.debugLineNum = 78;BA.debugLine="Sub SetTypeface(parent As Panel, t As Typeface)";
 //BA.debugLineNum = 79;BA.debugLine="For Each v As View In parent";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
{
final anywheresoftware.b4a.BA.IterableList group1 = _parent;
final int groupLen1 = group1.getSize()
;int index1 = 0;
;
for (; index1 < groupLen1;index1++){
_v.setObject((android.view.View)(group1.Get(index1)));
 //BA.debugLineNum = 80;BA.debugLine="If v Is Label Then";
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 81;BA.debugLine="Dim lbl As Label = v";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl.setObject((android.widget.TextView)(_v.getObject()));
 //BA.debugLineNum = 82;BA.debugLine="lbl.Typeface = t";
_lbl.setTypeface((android.graphics.Typeface)(_t.getObject()));
 }else if(_v.getObjectOrNull() instanceof android.view.ViewGroup) { 
 //BA.debugLineNum = 84;BA.debugLine="SetTypeface(v, t)";
_settypeface((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_v.getObject())),_t);
 };
 }
};
 //BA.debugLineNum = 87;BA.debugLine="End Sub";
return "";
}
public static String  _tim1_tick() throws Exception{
 //BA.debugLineNum = 243;BA.debugLine="Sub tim1_Tick";
 //BA.debugLineNum = 244;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 245;BA.debugLine="tim.Enabled = False";
_tim.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 246;BA.debugLine="StartActivity(Verify_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._verify_ac.getObject()));
 //BA.debugLineNum = 247;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 248;BA.debugLine="End Sub";
return "";
}
}
