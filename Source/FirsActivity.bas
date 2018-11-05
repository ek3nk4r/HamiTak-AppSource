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
	Dim aas As String
	Dim tim As Timer
	
	
End Sub

Sub Globals
	Private AHViewPager1 As AHViewPager
	Private PC As AHPageContainer
	Private DSTabLayout1 As DSTabLayout
	Private Label1 As Label
	Private login_btn As ACButton
	Private pass_txt As EditText
	Private name_txt As EditText
	Private register_btn As ACButton
	Dim ht_reg As HttpJob
	Private pass_txt1 As EditText
	Private phone_txt1 As EditText
	Private login_btn As ACButton
	Private pass_txt1 As EditText
	Dim ht_log As HttpJob
	Dim EditTextPlus As EditTextPlus
	Dim Phone As Phone
	Private phone_txt As EditText
	Dim ac As AppCompat
End Sub

Sub Activity_Create(FirstTime As Boolean)

	Activity.LoadLayout("Log_reg")
	
	Main.sql.Initialize(File.DirInternal,"data.db",False)

	
	ht_reg.Initialize("ht_reg",Me)
	ht_log.Initialize("ht_log",Me)
	
	PC.Initialize

	For i=0 To 1
		Dim p As Panel
		p.Initialize("")
		p.Color=Colors.White
		If i=0 Then p.LoadLayout("login")
		If i=1 Then p.LoadLayout("register")
		PC.AddPage(p,"")
	Next
	
	
	AHViewPager1.PageContainer = PC
	
	DSTabLayout1.SetViewPager(AHViewPager1)
	
	
	DSTabLayout1.Color=ac.GetThemeAttribute("colorPrimary")
	DSTabLayout1.TabIndicatorColor = ac.GetThemeAttribute("colorAccent")
	
	DSTabLayout1.SetTabText(1,"ثبت نام")
	DSTabLayout1.SetTabText(0,"ورود")
	
	DSTabLayout1.SetTabTextColors(Colors.White,ac.GetThemeAttribute("colorAccent"))
	
	DSTabLayout1.SelectTab(1)
	login_btn.ButtonColor = ac.GetThemeAttribute("colorAccent")
	Label1.TextColor = ac.GetThemeAttribute("colorAccent")
	SetTypeface(DSTabLayout1,Typeface.LoadFromAssets("iran-sans.ttf"))
	
	
End Sub

Sub SetTypeface(parent As Panel, t As Typeface)
	For Each v As View In parent
		If v Is Label Then
			Dim lbl As Label = v
			lbl.Typeface = t
		Else If v Is Panel Then
			SetTypeface(v, t)
		End If
	Next
End Sub


Sub Activity_Resume
	
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub register_btn_Click
	
	If name_txt.Text.Length < 5 Then
		EditTextPlus.setError(name_txt,"نام و نام خانوادگی نمیتواند کمتر از پنج حرف باشد")
		Return
	End If
	
	If phone_txt.Text.Length > 11 Then
		EditTextPlus.setError(phone_txt,"لطفا شماره موبایل را به درستی وارد کنید")
		Return
	End If
	If phone_txt.Text.Length < 11 Then
		EditTextPlus.setError(phone_txt,"لطفا شماره موبایل را به درستی وارد کنید")
		Return
	End If
	
	If pass_txt.Text.Length < 6 Then
		EditTextPlus.setError(pass_txt,"رمزعبور نمی تواند کمتر از شش حرف باشد")
		Return
	End If
	
	
	aas = phone_txt.Text
	File.WriteString(File.DirInternal,"phone_txt.txt",aas)

	
	ht_reg.PostString("http://apple-acc.ir/appmarket//signup.php","username="&name_txt.Text&"&password="&pass_txt.Text&"&phone="&phone_txt.Text)
	ht_reg.Tag = "ht_regg"
	
	
	ProgressDialogShow("در حال ثبت اطلاعات...")


End Sub

Sub login_btn_Click
	If phone_txt1.Text.Length > 11 Then
		EditTextPlus.setError(phone_txt1,"لطفا شماره موبایل را به درستی وارد کنید")
		Return
	End If
	If phone_txt1.Text.Length < 11 Then
		EditTextPlus.setError(phone_txt1,"لطفا شماره موبایل را به درستی وارد کنید")
		Return
	End If
	
	If pass_txt1.Text.Length < 6 Then
		EditTextPlus.setError(pass_txt1,"رمزعبور نمی تواند کمتر از شش حرف باشد")
		Return
	End If
	
	ProgressDialogShow("لطفا صبر کنید")
	ht_log.PostString("http://apple-acc.ir/appmarket//login.php","phone="&phone_txt1.Text&"&password="&pass_txt1.Text)
	ht_log.Tag = "ht_logg"
End Sub

Sub JobDone(Job As HttpJob)
	
	If Job.Tag = "ht_regg" Then
		If Job.Success Then
			If Job.GetString = "OK" Then
				ht_reg.PostString("http://apple-acc.ir/appmarket//tl_signup.php","msg="& "ثبت نام کاربر جدید" &CRLF& "نام و نام خانوادگی: "&name_txt.Text&CRLF&"رمزعبور: "&pass_txt.Text&CRLF&"شماره موبایل: "&phone_txt.Text&CRLF&"گوشی کابر: "&Phone.Product&" _ "&Phone.Model)
				Dim Cursor2 As Cursor
				Cursor2 = Main.sql1.ExecQuery("UPDATE tbl_profile SET Name_Family = '"& name_txt.Text &"',Phone = '"& phone_txt.Text &"'")
				Cursor2.Position = 0
				Cursor2.Close
				tim.Initialize("tim1",4000)
				tim.Enabled = True
			Else if Job.GetString = "OLD" Then
				EditTextPlus.setError(phone_txt,"قبلا با این شماره موبایل ثبت نام انجام شده است")
				tim.Enabled = False
				ProgressDialogHide
			End If
		Else
			Dim snackbar As DSSnackbar
			snackbar.Initialize("snack4",Activity,"خطا در برقراری ارتباط با سرور",snackbar.DURATION_LONG)
			SetSnackBarBackground(snackbar,ac.GetThemeAttribute("colorAccent"))
			SetSnackBarTextColor(snackbar,Colors.White)
			snackbar.Show
			tim.Enabled = False
			ProgressDialogHide
		End If
	End If
		
	If Job.Tag = "ht_logg" Then
		ProgressDialogHide
		If Job.Success Then
			If Job.GetString = "OK" Then
				Dim snackbar As DSSnackbar
				snackbar.Initialize("snack1",Activity,"با موفقیت وارد شدید",snackbar.DURATION_LONG)
				SetSnackBarBackground(snackbar,ac.GetThemeAttribute("colorAccent"))
				SetSnackBarTextColor(snackbar,Colors.White)
				snackbar.Show
				
				Dim Cursor1 As Cursor
				Cursor1 = Main.sql1.ExecQuery("UPDATE tbl_profile SET Name_Family = '', FirstOpen='1', Phone = '"& phone_txt1.Text &"'")
				Cursor1.Position = 0
				Cursor1.Close
				StartActivity(Home)
				Activity.Finish

			Else If Job.GetString = "WRONG" Then
				Dim snackbar As DSSnackbar
				snackbar.Initialize("snack1",Activity,"شماره موبایل یا رمزعبور اشتباه است",snackbar.DURATION_LONG)
				SetSnackBarBackground(snackbar,ac.GetThemeAttribute("colorAccent"))
				SetSnackBarTextColor(snackbar,Colors.White)
				snackbar.Show
				
			Else
				Dim snackbar As DSSnackbar
				snackbar.Initialize("snack2",Activity,"خطای ناشناخته",snackbar.DURATION_LONG)
				SetSnackBarBackground(snackbar,ac.GetThemeAttribute("colorAccent"))
				SetSnackBarTextColor(snackbar,Colors.White)
				snackbar.Show
			End If
		Else
			Dim snackbar As DSSnackbar
			snackbar.Initialize("snack3",Activity,"خطا در برقراری ارتباط با سرور",snackbar.DURATION_LONG)
			SetSnackBarBackground(snackbar,ac.GetThemeAttribute("colorAccent"))
			SetSnackBarTextColor(snackbar,Colors.White)
			snackbar.Show
		End If
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

Sub tim1_Tick
	ProgressDialogHide
	tim.Enabled = False
	StartActivity(Verify_ac)
	Activity.Finish
End Sub

Sub Label1_Click
	
End Sub

Sub Panel1_Click
	
End Sub