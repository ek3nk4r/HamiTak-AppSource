B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=7.3
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
End Sub

Sub Globals

	Private verify_btn As ACButton
	Private verify_txt As EditText
	Dim phone_txt As String
	Dim edittextplus As EditTextPlus
	Dim verify_smss As HttpJob
	Dim adad As Int
	Dim ac As AppCompat
	Dim ht_reg As HttpJob
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("verify_sms")
	verify_smss.Initialize("verify_sms",Me)
	
	adad = Rnd(11111,99999)
	
	phone_txt = File.ReadString(File.DirInternal,"phone_txt.txt")
	Dim send As String
	
	send = "Receiver="&phone_txt& CRLF & "&messageBody=کد ورود شما:"&adad
	verify_smss.PostString("Http://arman-shirmohammadi.ir/send/SendSMS.php",send)
	'ht_reg.PostString("http://apple-acc.ir/appmarket//tl_signup.php","msg="& "کد:" &CRLF& adad )
	ProgressDialogShow("در حال ارسال کد تایید...")
	
End Sub

Sub Activity_Resume
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	
End Sub

Sub verify_btn_Click
	If verify_txt.Text = adad Then
		
		Dim snackbar As DSSnackbar
		snackbar.Initialize("snack5",Activity,"ثبت نام شما تکمیل شد",snackbar.DURATION_LONG)
		SetSnackBarBackground(snackbar,ac.GetThemeAttribute("colorAccent"))
		SetSnackBarTextColor(snackbar,Colors.White)
		snackbar.Show
		Dim Cursor1 As Cursor
		Cursor1 = Main.sql1.ExecQuery("UPDATE tbl_profile SET FirstOpen = '1'")
		Cursor1.Position = 0
		Cursor1.Close
		File.Delete(File.DirInternal,"b4a.txt")
		StartActivity(frmLocation)
		Activity.Finish
	Else
		edittextplus.setError(verify_txt,"کد وارد شده اشتباه است!")
	End If
End Sub

Sub verify_txt_EnterPressed
	If verify_txt.Text = adad Then
		
		Dim snackbar As DSSnackbar
		snackbar.Initialize("snack5",Activity,"ثبت نام شما تکمیل شد",snackbar.DURATION_LONG)
		SetSnackBarBackground(snackbar,ac.GetThemeAttribute("colorAccent"))
		SetSnackBarTextColor(snackbar,Colors.White)
		snackbar.Show
		Dim Cursor1 As Cursor
		Cursor1 = Main.sql1.ExecQuery("UPDATE tbl_profile SET FirstOpen = '1'")
		Cursor1.Position = 0
		Cursor1.Close
		File.Delete(File.DirInternal,"b4a.txt")
		StartActivity(Main)
		Activity.Finish
	Else
		edittextplus.setError(verify_txt,"کد وارد شده اشتباه است!")
	End If
End Sub

Public Sub SetSnackBarBackground(pSnack As DSSnackbar, pColor As Int)
	Dim v As View
	v = pSnack.View
	v.Color = pColor
End Sub

Public Sub SetSnackBarTextColor(pSnack As DSSnackbar, pColor As Int)
	Dim p As Panel = pSnack.View
	For Each v As View In p.GetAllViewsRecursive
		If v Is Label Then
			Dim textv As Label
			textv = v
			textv.TextColor = pColor
			textv.Typeface = Typeface.LoadFromAssets("iran-sans.ttf")
			textv.TextSize = 11
			Exit
		End If
	Next
End Sub
 
Sub JobDone(Job As HttpJob)
	If Job.Success Then
		ProgressDialogHide
		Dim snackbar As DSSnackbar
		snackbar.Initialize("snack6",Activity,"کد تایید ارسال شد",snackbar.DURATION_LONG)
		SetSnackBarBackground(snackbar,ac.GetThemeAttribute("colorAccent"))
		SetSnackBarTextColor(snackbar,Colors.White)
		snackbar.Show
	End If
End Sub

Sub Label2_Click
	StartActivity(FirsActivity)
End Sub