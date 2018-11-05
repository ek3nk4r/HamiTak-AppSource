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
	
	Private lbl_name As Label
	Private lbl_name2 As Label
	Private lbl_phone As Label
	Private lbl_phone2 As Label
	Private scrollveiw_home As ScrollView
	Private btn_cancel As Button
	Private btn_homepay As Button
	Private btn_takmil As Button
	Private edit_adress As EditText
	Dim cs As Cursor
	Private lbl_sefaresh2 As Label
	Dim txt3 As String
	Dim textplus As EditTextPlus
	Dim ht_tl As HttpJob
	Private edit_name2 As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Sefaresh_page")
	ht_tl.Initialize("ht_tl",Me)
	scrollveiw_home.Panel.LoadLayout("sefaresh_scrollveiw")
	scrollveiw_home.Panel.Height = btn_cancel.Height + btn_cancel.Top + 7dip
	cs = Main.sql1.ExecQuery("SELECT * FROM tbl_profile")
	cs.Position=0
	lbl_name2.Text = cs.GetString("Name_Family")
	edit_name2.Text = lbl_name2.Text
	lbl_phone2.Text = cs.GetString("Phone")
	txt3 = File.ReadString(File.DirInternal,"servicename2.txt")
	lbl_sefaresh2.Text = txt3 & " به قیمت " & Description_ac.price
	
	
	
End Sub

Sub Activity_Resume
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Activity.Finish
End Sub


Sub btn_cancel_Click
	ToastMessageShow("سفارش شما لغو شد!",False)
	StartActivity(Home)
	Activity.Finish
End Sub

Sub btn_homepay_Click
	If edit_name2.Text = "" Then
		textplus.setError(edit_name2,"نام کامل خود را وارد کنید")
		Return
	End If
	If edit_adress.Text = "" Then
		textplus.setError(edit_adress,"آدرس دقیق خود را وارد کنید")
		Return
	End If
	Dim cs1 As Cursor
	Dim adad As String
	adad = Rnd(12345679,87654321)
	cs1 = Main.sql1.ExecQuery("INSERT INTO sefareshat VALUES (null,'"& txt3 &"','" & Description_ac.txt2 & "','" & Description_ac.price & "','" & 0 & "','" & adad &"')")
	cs1.Position = 0
	cs1.Close
	ProgressDialogShow("در حال ارسال اطلاعات به سرور...")
	Dim cs2 As Cursor
	cs2 = Main.sql1.ExecQuery("SELECT * FROM sefareshat WHERE servicename = '" & txt3 & "'")
	cs2.Position = 0
	ht_tl.PostString("http://apple-acc.ir/appmarket/tl_sefarsh.php","msg=" & "سفارش جدید"& CRLF & "نام و نام خانوادگی : " & edit_name2.Text & CRLF & "نام سرویس : " & txt3 & CRLF & "قیمت سرویس : " & Description_ac.price & CRLF & "شماره تلفن : " & lbl_phone2.Text & CRLF & "آدرس : " & edit_adress.Text & CRLF & CRLF & "کد پیگیری : " & adad )
End Sub

Sub btn_takmil_Click
	If edit_adress.Text = "" Then
		textplus.setError(edit_adress,"آدرس دقیق خود را وارد کنید")
	Else
		ToastMessageShow("امکان پرداخت انلاین بزودی فعال میشود",False)
	
	End If
End Sub

Sub btn_other_Click
	StartActivity(Profile_ac)
End Sub

Sub btn_home_Click
	StartActivity(Home)
End Sub

Sub btn_profile_Click
	StartActivity(Sefareshat_ac)
End Sub

Sub btm_service_Click
	StartActivity(Service_ac)
End Sub

Sub JobDone(Job As HttpJob)
	If Job.Success Then
		ProgressDialogHide
		ToastMessageShow("ارسال موفقیت آمیز بود",False)
		StartActivity(Sefareshat_ac)
	End If
	
End Sub
