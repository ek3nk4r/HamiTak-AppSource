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

public class verify_ac extends Activity implements B4AActivity{
	public static verify_ac mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hamitak.ir", "hamitak.ir.verify_ac");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (verify_ac).");
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
		activityBA = new BA(this, layout, processBA, "hamitak.ir", "hamitak.ir.verify_ac");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hamitak.ir.verify_ac", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (verify_ac) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (verify_ac) Resume **");
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
		return verify_ac.class;
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
        BA.LogInfo("** Activity (verify_ac) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            verify_ac mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (verify_ac) Resume **");
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
public de.amberhome.objects.appcompat.ACButtonWrapper _verify_btn = null;
public anywheresoftware.b4a.objects.EditTextWrapper _verify_txt = null;
public static String _phone_txt = "";
public ir.EditTextPlus.pejman.EditTextPlus _edittextplus = null;
public anywheresoftware.b4a.samples.httputils2.httpjob _verify_smss = null;
public static int _adad = 0;
public de.amberhome.objects.appcompat.AppCompatBase _ac = null;
public anywheresoftware.b4a.samples.httputils2.httpjob _ht_reg = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public hamitak.ir.main _main = null;
public hamitak.ir.firsactivity _firsactivity = null;
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
String _send = "";
 //BA.debugLineNum = 21;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 22;BA.debugLine="Activity.LoadLayout(\"verify_sms\")";
mostCurrent._activity.LoadLayout("verify_sms",mostCurrent.activityBA);
 //BA.debugLineNum = 23;BA.debugLine="verify_smss.Initialize(\"verify_sms\",Me)";
mostCurrent._verify_smss._initialize(processBA,"verify_sms",verify_ac.getObject());
 //BA.debugLineNum = 25;BA.debugLine="adad = Rnd(11111,99999)";
_adad = anywheresoftware.b4a.keywords.Common.Rnd((int) (11111),(int) (99999));
 //BA.debugLineNum = 27;BA.debugLine="phone_txt = File.ReadString(File.DirInternal,\"pho";
mostCurrent._phone_txt = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"phone_txt.txt");
 //BA.debugLineNum = 28;BA.debugLine="Dim send As String";
_send = "";
 //BA.debugLineNum = 30;BA.debugLine="send = \"Receiver=\"&phone_txt& CRLF & \"&messageBod";
_send = "Receiver="+mostCurrent._phone_txt+anywheresoftware.b4a.keywords.Common.CRLF+"&messageBody=کد ورود شما:"+BA.NumberToString(_adad);
 //BA.debugLineNum = 31;BA.debugLine="verify_smss.PostString(\"Http://arman-shirmohammad";
mostCurrent._verify_smss._poststring("Http://arman-shirmohammadi.ir/send/SendSMS.php",_send);
 //BA.debugLineNum = 33;BA.debugLine="ProgressDialogShow(\"در حال ارسال کد تایید...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("در حال ارسال کد تایید..."));
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 41;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 37;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 9;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Private verify_btn As ACButton";
mostCurrent._verify_btn = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Private verify_txt As EditText";
mostCurrent._verify_txt = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Dim phone_txt As String";
mostCurrent._phone_txt = "";
 //BA.debugLineNum = 14;BA.debugLine="Dim edittextplus As EditTextPlus";
mostCurrent._edittextplus = new ir.EditTextPlus.pejman.EditTextPlus();
 //BA.debugLineNum = 15;BA.debugLine="Dim verify_smss As HttpJob";
mostCurrent._verify_smss = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 16;BA.debugLine="Dim adad As Int";
_adad = 0;
 //BA.debugLineNum = 17;BA.debugLine="Dim ac As AppCompat";
mostCurrent._ac = new de.amberhome.objects.appcompat.AppCompatBase();
 //BA.debugLineNum = 18;BA.debugLine="Dim ht_reg As HttpJob";
mostCurrent._ht_reg = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
de.amberhome.objects.SnackbarWrapper _snackbar = null;
 //BA.debugLineNum = 105;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 106;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 107;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 108;BA.debugLine="Dim snackbar As DSSnackbar";
_snackbar = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 109;BA.debugLine="snackbar.Initialize(\"snack6\",Activity,\"کد تایید";
_snackbar.Initialize(mostCurrent.activityBA,"snack6",(android.view.View)(mostCurrent._activity.getObject()),"کد تایید ارسال شد",_snackbar.DURATION_LONG);
 //BA.debugLineNum = 110;BA.debugLine="SetSnackBarBackground(snackbar,ac.GetThemeAttrib";
_setsnackbarbackground(_snackbar,mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorAccent"));
 //BA.debugLineNum = 111;BA.debugLine="SetSnackBarTextColor(snackbar,Colors.White)";
_setsnackbartextcolor(_snackbar,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 112;BA.debugLine="snackbar.Show";
_snackbar.Show();
 };
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return "";
}
public static String  _label2_click() throws Exception{
 //BA.debugLineNum = 116;BA.debugLine="Sub Label2_Click";
 //BA.debugLineNum = 117;BA.debugLine="StartActivity(FirsActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._firsactivity.getObject()));
 //BA.debugLineNum = 118;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
public static String  _setsnackbarbackground(de.amberhome.objects.SnackbarWrapper _psnack,int _pcolor) throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
 //BA.debugLineNum = 85;BA.debugLine="Public Sub SetSnackBarBackground(pSnack As DSSnack";
 //BA.debugLineNum = 86;BA.debugLine="Dim v As View";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
 //BA.debugLineNum = 87;BA.debugLine="v = pSnack.View";
_v.setObject((android.view.View)(_psnack.getView()));
 //BA.debugLineNum = 88;BA.debugLine="v.Color = pColor";
_v.setColor(_pcolor);
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return "";
}
public static String  _setsnackbartextcolor(de.amberhome.objects.SnackbarWrapper _psnack,int _pcolor) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.LabelWrapper _textv = null;
 //BA.debugLineNum = 91;BA.debugLine="Public Sub SetSnackBarTextColor(pSnack As DSSnackb";
 //BA.debugLineNum = 92;BA.debugLine="Dim p As Panel = pSnack.View";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p.setObject((android.view.ViewGroup)(_psnack.getView()));
 //BA.debugLineNum = 93;BA.debugLine="For Each v As View In p.GetAllViewsRecursive";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
{
final anywheresoftware.b4a.BA.IterableList group2 = _p.GetAllViewsRecursive();
final int groupLen2 = group2.getSize()
;int index2 = 0;
;
for (; index2 < groupLen2;index2++){
_v.setObject((android.view.View)(group2.Get(index2)));
 //BA.debugLineNum = 94;BA.debugLine="If v Is Label Then";
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 95;BA.debugLine="Dim textv As Label";
_textv = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 96;BA.debugLine="textv = v";
_textv.setObject((android.widget.TextView)(_v.getObject()));
 //BA.debugLineNum = 97;BA.debugLine="textv.TextColor = pColor";
_textv.setTextColor(_pcolor);
 //BA.debugLineNum = 98;BA.debugLine="textv.Typeface = Typeface.LoadFromAssets(\"iran-";
_textv.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("iran-sans.ttf"));
 //BA.debugLineNum = 99;BA.debugLine="textv.TextSize = 11";
_textv.setTextSize((float) (11));
 //BA.debugLineNum = 100;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 103;BA.debugLine="End Sub";
return "";
}
public static String  _verify_btn_click() throws Exception{
de.amberhome.objects.SnackbarWrapper _snackbar = null;
anywheresoftware.b4a.sql.SQL.CursorWrapper _cursor1 = null;
 //BA.debugLineNum = 45;BA.debugLine="Sub verify_btn_Click";
 //BA.debugLineNum = 46;BA.debugLine="If verify_txt.Text = adad Then";
if ((mostCurrent._verify_txt.getText()).equals(BA.NumberToString(_adad))) { 
 //BA.debugLineNum = 48;BA.debugLine="Dim snackbar As DSSnackbar";
_snackbar = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 49;BA.debugLine="snackbar.Initialize(\"snack5\",Activity,\"ثبت نام ش";
_snackbar.Initialize(mostCurrent.activityBA,"snack5",(android.view.View)(mostCurrent._activity.getObject()),"ثبت نام شما تکمیل شد",_snackbar.DURATION_LONG);
 //BA.debugLineNum = 50;BA.debugLine="SetSnackBarBackground(snackbar,ac.GetThemeAttrib";
_setsnackbarbackground(_snackbar,mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorAccent"));
 //BA.debugLineNum = 51;BA.debugLine="SetSnackBarTextColor(snackbar,Colors.White)";
_setsnackbartextcolor(_snackbar,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 52;BA.debugLine="snackbar.Show";
_snackbar.Show();
 //BA.debugLineNum = 53;BA.debugLine="Dim Cursor1 As Cursor";
_cursor1 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Cursor1 = Main.sql1.ExecQuery(\"UPDATE tbl_profil";
_cursor1.setObject((android.database.Cursor)(mostCurrent._main._sql1.ExecQuery("UPDATE tbl_profile SET FirstOpen = '1'")));
 //BA.debugLineNum = 55;BA.debugLine="Cursor1.Position = 0";
_cursor1.setPosition((int) (0));
 //BA.debugLineNum = 56;BA.debugLine="Cursor1.Close";
_cursor1.Close();
 //BA.debugLineNum = 57;BA.debugLine="File.Delete(File.DirInternal,\"b4a.txt\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"b4a.txt");
 //BA.debugLineNum = 58;BA.debugLine="StartActivity(frmLocation)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._frmlocation.getObject()));
 //BA.debugLineNum = 59;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 61;BA.debugLine="edittextplus.setError(verify_txt,\"کد وارد شده اش";
mostCurrent._edittextplus.setError((android.widget.EditText)(mostCurrent._verify_txt.getObject()),"کد وارد شده اشتباه است!");
 };
 //BA.debugLineNum = 63;BA.debugLine="End Sub";
return "";
}
public static String  _verify_txt_enterpressed() throws Exception{
de.amberhome.objects.SnackbarWrapper _snackbar = null;
anywheresoftware.b4a.sql.SQL.CursorWrapper _cursor1 = null;
 //BA.debugLineNum = 65;BA.debugLine="Sub verify_txt_EnterPressed";
 //BA.debugLineNum = 66;BA.debugLine="If verify_txt.Text = adad Then";
if ((mostCurrent._verify_txt.getText()).equals(BA.NumberToString(_adad))) { 
 //BA.debugLineNum = 68;BA.debugLine="Dim snackbar As DSSnackbar";
_snackbar = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 69;BA.debugLine="snackbar.Initialize(\"snack5\",Activity,\"ثبت نام ش";
_snackbar.Initialize(mostCurrent.activityBA,"snack5",(android.view.View)(mostCurrent._activity.getObject()),"ثبت نام شما تکمیل شد",_snackbar.DURATION_LONG);
 //BA.debugLineNum = 70;BA.debugLine="SetSnackBarBackground(snackbar,ac.GetThemeAttrib";
_setsnackbarbackground(_snackbar,mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorAccent"));
 //BA.debugLineNum = 71;BA.debugLine="SetSnackBarTextColor(snackbar,Colors.White)";
_setsnackbartextcolor(_snackbar,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 72;BA.debugLine="snackbar.Show";
_snackbar.Show();
 //BA.debugLineNum = 73;BA.debugLine="Dim Cursor1 As Cursor";
_cursor1 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 74;BA.debugLine="Cursor1 = Main.sql1.ExecQuery(\"UPDATE tbl_profil";
_cursor1.setObject((android.database.Cursor)(mostCurrent._main._sql1.ExecQuery("UPDATE tbl_profile SET FirstOpen = '1'")));
 //BA.debugLineNum = 75;BA.debugLine="Cursor1.Position = 0";
_cursor1.setPosition((int) (0));
 //BA.debugLineNum = 76;BA.debugLine="Cursor1.Close";
_cursor1.Close();
 //BA.debugLineNum = 77;BA.debugLine="File.Delete(File.DirInternal,\"b4a.txt\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"b4a.txt");
 //BA.debugLineNum = 78;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 79;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 81;BA.debugLine="edittextplus.setError(verify_txt,\"کد وارد شده اش";
mostCurrent._edittextplus.setError((android.widget.EditText)(mostCurrent._verify_txt.getObject()),"کد وارد شده اشتباه است!");
 };
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
}
