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

public class sefaresh_ac extends Activity implements B4AActivity{
	public static sefaresh_ac mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hamitak.ir", "hamitak.ir.sefaresh_ac");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (sefaresh_ac).");
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
		activityBA = new BA(this, layout, processBA, "hamitak.ir", "hamitak.ir.sefaresh_ac");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hamitak.ir.sefaresh_ac", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (sefaresh_ac) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (sefaresh_ac) Resume **");
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
		return sefaresh_ac.class;
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
        BA.LogInfo("** Activity (sefaresh_ac) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            sefaresh_ac mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (sefaresh_ac) Resume **");
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
public anywheresoftware.b4a.objects.LabelWrapper _lbl_name = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_name2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_phone = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_phone2 = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrollveiw_home = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_cancel = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_homepay = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_takmil = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edit_adress = null;
public anywheresoftware.b4a.sql.SQL.CursorWrapper _cs = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_sefaresh2 = null;
public static String _txt3 = "";
public ir.EditTextPlus.pejman.EditTextPlus _textplus = null;
public anywheresoftware.b4a.samples.httputils2.httpjob _ht_tl = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edit_name2 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public hamitak.ir.main _main = null;
public hamitak.ir.verify_ac _verify_ac = null;
public hamitak.ir.firsactivity _firsactivity = null;
public hamitak.ir.home _home = null;
public hamitak.ir.service_ac _service_ac = null;
public hamitak.ir.profile_ac _profile_ac = null;
public hamitak.ir.services_ac _services_ac = null;
public hamitak.ir.description_ac _description_ac = null;
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
 //BA.debugLineNum = 29;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 30;BA.debugLine="Activity.LoadLayout(\"Sefaresh_page\")";
mostCurrent._activity.LoadLayout("Sefaresh_page",mostCurrent.activityBA);
 //BA.debugLineNum = 31;BA.debugLine="ht_tl.Initialize(\"ht_tl\",Me)";
mostCurrent._ht_tl._initialize(processBA,"ht_tl",sefaresh_ac.getObject());
 //BA.debugLineNum = 32;BA.debugLine="scrollveiw_home.Panel.LoadLayout(\"sefaresh_scroll";
mostCurrent._scrollveiw_home.getPanel().LoadLayout("sefaresh_scrollveiw",mostCurrent.activityBA);
 //BA.debugLineNum = 33;BA.debugLine="scrollveiw_home.Panel.Height = btn_cancel.Height";
mostCurrent._scrollveiw_home.getPanel().setHeight((int) (mostCurrent._btn_cancel.getHeight()+mostCurrent._btn_cancel.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7))));
 //BA.debugLineNum = 34;BA.debugLine="cs = Main.sql1.ExecQuery(\"SELECT * FROM tbl_profi";
mostCurrent._cs.setObject((android.database.Cursor)(mostCurrent._main._sql1.ExecQuery("SELECT * FROM tbl_profile")));
 //BA.debugLineNum = 35;BA.debugLine="cs.Position=0";
mostCurrent._cs.setPosition((int) (0));
 //BA.debugLineNum = 36;BA.debugLine="lbl_name2.Text = cs.GetString(\"Name_Family\")";
mostCurrent._lbl_name2.setText(BA.ObjectToCharSequence(mostCurrent._cs.GetString("Name_Family")));
 //BA.debugLineNum = 37;BA.debugLine="edit_name2.Text = lbl_name2.Text";
mostCurrent._edit_name2.setText(BA.ObjectToCharSequence(mostCurrent._lbl_name2.getText()));
 //BA.debugLineNum = 38;BA.debugLine="lbl_phone2.Text = cs.GetString(\"Phone\")";
mostCurrent._lbl_phone2.setText(BA.ObjectToCharSequence(mostCurrent._cs.GetString("Phone")));
 //BA.debugLineNum = 39;BA.debugLine="txt3 = File.ReadString(File.DirInternal,\"servicen";
mostCurrent._txt3 = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"servicename2.txt");
 //BA.debugLineNum = 40;BA.debugLine="lbl_sefaresh2.Text = txt3 & \" به قیمت \" & Descrip";
mostCurrent._lbl_sefaresh2.setText(BA.ObjectToCharSequence(mostCurrent._txt3+" به قیمت "+mostCurrent._description_ac._price));
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 49;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 50;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 46;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return "";
}
public static String  _btm_service_click() throws Exception{
 //BA.debugLineNum = 103;BA.debugLine="Sub btm_service_Click";
 //BA.debugLineNum = 104;BA.debugLine="StartActivity(Service_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._service_ac.getObject()));
 //BA.debugLineNum = 105;BA.debugLine="End Sub";
return "";
}
public static String  _btn_cancel_click() throws Exception{
 //BA.debugLineNum = 54;BA.debugLine="Sub btn_cancel_Click";
 //BA.debugLineNum = 55;BA.debugLine="ToastMessageShow(\"سفارش شما لغو شد!\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("سفارش شما لغو شد!"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 56;BA.debugLine="StartActivity(Home)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._home.getObject()));
 //BA.debugLineNum = 57;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
return "";
}
public static String  _btn_home_click() throws Exception{
 //BA.debugLineNum = 95;BA.debugLine="Sub btn_home_Click";
 //BA.debugLineNum = 96;BA.debugLine="StartActivity(Home)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._home.getObject()));
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
return "";
}
public static String  _btn_homepay_click() throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cs1 = null;
String _adad = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cs2 = null;
 //BA.debugLineNum = 60;BA.debugLine="Sub btn_homepay_Click";
 //BA.debugLineNum = 61;BA.debugLine="If edit_name2.Text = \"\" Then";
if ((mostCurrent._edit_name2.getText()).equals("")) { 
 //BA.debugLineNum = 62;BA.debugLine="textplus.setError(edit_name2,\"نام کامل خود را وا";
mostCurrent._textplus.setError((android.widget.EditText)(mostCurrent._edit_name2.getObject()),"نام کامل خود را وارد کنید");
 //BA.debugLineNum = 63;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 65;BA.debugLine="If edit_adress.Text = \"\" Then";
if ((mostCurrent._edit_adress.getText()).equals("")) { 
 //BA.debugLineNum = 66;BA.debugLine="textplus.setError(edit_adress,\"آدرس دقیق خود را";
mostCurrent._textplus.setError((android.widget.EditText)(mostCurrent._edit_adress.getObject()),"آدرس دقیق خود را وارد کنید");
 //BA.debugLineNum = 67;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 69;BA.debugLine="Dim cs1 As Cursor";
_cs1 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 70;BA.debugLine="Dim adad As String";
_adad = "";
 //BA.debugLineNum = 71;BA.debugLine="adad = Rnd(12345679,87654321)";
_adad = BA.NumberToString(anywheresoftware.b4a.keywords.Common.Rnd((int) (12345679),(int) (87654321)));
 //BA.debugLineNum = 72;BA.debugLine="cs1 = Main.sql1.ExecQuery(\"INSERT INTO sefareshat";
_cs1.setObject((android.database.Cursor)(mostCurrent._main._sql1.ExecQuery("INSERT INTO sefareshat VALUES (null,'"+mostCurrent._txt3+"','"+mostCurrent._description_ac._txt2+"','"+mostCurrent._description_ac._price+"','"+BA.NumberToString(0)+"','"+_adad+"')")));
 //BA.debugLineNum = 73;BA.debugLine="cs1.Position = 0";
_cs1.setPosition((int) (0));
 //BA.debugLineNum = 74;BA.debugLine="cs1.Close";
_cs1.Close();
 //BA.debugLineNum = 75;BA.debugLine="ProgressDialogShow(\"در حال ارسال اطلاعات به سرور.";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("در حال ارسال اطلاعات به سرور..."));
 //BA.debugLineNum = 76;BA.debugLine="Dim cs2 As Cursor";
_cs2 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 77;BA.debugLine="cs2 = Main.sql1.ExecQuery(\"SELECT * FROM sefaresh";
_cs2.setObject((android.database.Cursor)(mostCurrent._main._sql1.ExecQuery("SELECT * FROM sefareshat WHERE servicename = '"+mostCurrent._txt3+"'")));
 //BA.debugLineNum = 78;BA.debugLine="cs2.Position = 0";
_cs2.setPosition((int) (0));
 //BA.debugLineNum = 79;BA.debugLine="ht_tl.PostString(\"http://apple-acc.ir/appmarket/t";
mostCurrent._ht_tl._poststring("http://apple-acc.ir/appmarket/tl_sefarsh.php","msg="+"سفارش جدید"+anywheresoftware.b4a.keywords.Common.CRLF+"نام و نام خانوادگی : "+mostCurrent._edit_name2.getText()+anywheresoftware.b4a.keywords.Common.CRLF+"نام سرویس : "+mostCurrent._txt3+anywheresoftware.b4a.keywords.Common.CRLF+"قیمت سرویس : "+mostCurrent._description_ac._price+anywheresoftware.b4a.keywords.Common.CRLF+"شماره تلفن : "+mostCurrent._lbl_phone2.getText()+anywheresoftware.b4a.keywords.Common.CRLF+"آدرس : "+mostCurrent._edit_adress.getText()+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"کد پیگیری : "+_adad);
 //BA.debugLineNum = 80;BA.debugLine="End Sub";
return "";
}
public static String  _btn_other_click() throws Exception{
 //BA.debugLineNum = 91;BA.debugLine="Sub btn_other_Click";
 //BA.debugLineNum = 92;BA.debugLine="StartActivity(Profile_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._profile_ac.getObject()));
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return "";
}
public static String  _btn_profile_click() throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub btn_profile_Click";
 //BA.debugLineNum = 100;BA.debugLine="StartActivity(Sefareshat_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._sefareshat_ac.getObject()));
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return "";
}
public static String  _btn_takmil_click() throws Exception{
 //BA.debugLineNum = 82;BA.debugLine="Sub btn_takmil_Click";
 //BA.debugLineNum = 83;BA.debugLine="If edit_adress.Text = \"\" Then";
if ((mostCurrent._edit_adress.getText()).equals("")) { 
 //BA.debugLineNum = 84;BA.debugLine="textplus.setError(edit_adress,\"آدرس دقیق خود را";
mostCurrent._textplus.setError((android.widget.EditText)(mostCurrent._edit_adress.getObject()),"آدرس دقیق خود را وارد کنید");
 }else {
 //BA.debugLineNum = 86;BA.debugLine="ToastMessageShow(\"امکان پرداخت انلاین بزودی فعال";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("امکان پرداخت انلاین بزودی فعال میشود"),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Private lbl_name As Label";
mostCurrent._lbl_name = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private lbl_name2 As Label";
mostCurrent._lbl_name2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private lbl_phone As Label";
mostCurrent._lbl_phone = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private lbl_phone2 As Label";
mostCurrent._lbl_phone2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private scrollveiw_home As ScrollView";
mostCurrent._scrollveiw_home = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private btn_cancel As Button";
mostCurrent._btn_cancel = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private btn_homepay As Button";
mostCurrent._btn_homepay = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private btn_takmil As Button";
mostCurrent._btn_takmil = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private edit_adress As EditText";
mostCurrent._edit_adress = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Dim cs As Cursor";
mostCurrent._cs = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private lbl_sefaresh2 As Label";
mostCurrent._lbl_sefaresh2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Dim txt3 As String";
mostCurrent._txt3 = "";
 //BA.debugLineNum = 24;BA.debugLine="Dim textplus As EditTextPlus";
mostCurrent._textplus = new ir.EditTextPlus.pejman.EditTextPlus();
 //BA.debugLineNum = 25;BA.debugLine="Dim ht_tl As HttpJob";
mostCurrent._ht_tl = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 26;BA.debugLine="Private edit_name2 As EditText";
mostCurrent._edit_name2 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
 //BA.debugLineNum = 107;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 108;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 109;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 110;BA.debugLine="ToastMessageShow(\"ارسال موفقیت آمیز بود\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("ارسال موفقیت آمیز بود"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 111;BA.debugLine="StartActivity(Sefareshat_ac)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._sefareshat_ac.getObject()));
 };
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
}
