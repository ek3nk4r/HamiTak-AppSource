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
	Private scrollveiw_home As ScrollView
	Dim url As PhoneIntents
	Private ImageView7 As ImageView
	Private HorizontalScrollView1 As HorizontalScrollView
	Private Panel4 As Panel
	Private ImageView4 As ImageView
	Private HorizontalScrollView2 As HorizontalScrollView
	Dim pnl As Panel
	Dim cs As Cursor
	Private Label11 As Label
	Private Label24 As Label
	Private Label7 As Label
	Private Button1 As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Home")
	cs = Main.sql1.ExecQuery("SELECT * FROM sefareshat")
	cs.Position = 0
	
	scrollveiw_home.Panel.LoadLayout("home_scrollveiw")
	Label7.Text = "شما تا الان " & cs.RowCount & " خدمات از ما دریافت کرده اید"
	scrollveiw_home.Panel.Height = HorizontalScrollView1.Top + HorizontalScrollView1.Height
	Dim r As Reflector
	r.Target = scrollveiw_home
	r.RunMethod2("setVerticalScrollBarEnabled", False, "java.lang.boolean")
	
	HorizontalScrollView2.Panel.Color = Colors.Transparent
	Dim left As Int = 15
	cs = Main.sql.ExecQuery("SELECT * FROM service_active ORDER BY id DESC")
	For i = 0 To 6
		cs.Position = i
		Dim pnl As Panel
		pnl.Initialize("pnl")
		pnl.LoadLayout("HorizontalScrollView_page")
		pnl.Tag = i
		HorizontalScrollView2.Panel.AddView(pnl,left,Panel4.Top,Panel4.Width,Panel4.Height)
		left = left + Panel4.Width
		HorizontalScrollView2.Panel.Height = Panel4.Height
		If pnl.Tag = i Then
			Label11.Text = cs.GetString("servicename")
			Label24.Text = Label11.Text
			Label24.TextColor = Colors.Transparent
			Label24.Enabled = True
			Label24.Visible = True
			ImageView4.Bitmap = LoadBitmap(File.DirAssets,cs.GetString("image"))
		End If
	Next
	HorizontalScrollView2.Panel.Width = left
	HorizontalScrollView2.FullScroll(True)
	HorizontalScrollView2.Panel.Height = Panel4.Height
	CallSubDelayed(Me,"setSV2_Right")
	
	Dim r As Reflector
	r.Target = HorizontalScrollView2
	r.RunMethod2("setHorizontalScrollBarEnabled", False, "java.lang.boolean")
	
	
	HorizontalScrollView1.Panel.Color = Colors.Transparent
	Dim left As Int = 20
	cs = Main.sql.ExecQuery("SELECT * FROM service_unactive ORDER BY id DESC")
	For i = 0 To 9
		cs.Position = i
		Dim pnl As Panel
		pnl.Initialize("pnl")
		pnl.LoadLayout("HorizontalScrollView_page")
		pnl.Tag = i
		HorizontalScrollView1.Panel.AddView(pnl,left,Panel4.Top,Panel4.Width,Panel4.Height)
		left = left + Panel4.Width
		HorizontalScrollView1.Panel.Height = Panel4.Height
		If pnl.Tag = i Then
			Label11.Text = cs.GetString("servicename")
			Label24.TextColor = Colors.Transparent
			Label24.Enabled = False
			Label24.Visible = False
			ImageView4.Bitmap = LoadBitmap(File.DirAssets,cs.GetString("image"))
		End If
	Next
	HorizontalScrollView1.Panel.Width = left
	HorizontalScrollView1.FullScroll(True)
	HorizontalScrollView1.Panel.Height = Panel4.Height
	CallSubDelayed(Me,"setSV1_Right")
	
	Dim r As Reflector
	r.Target = HorizontalScrollView1
	r.RunMethod2("setHorizontalScrollBarEnabled", False, "java.lang.boolean")
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub setSV1_Right
	HorizontalScrollView1.ScrollPosition = HorizontalScrollView1.Panel.Width
End Sub

Sub setSV2_Right
	HorizontalScrollView2.ScrollPosition = HorizontalScrollView2.Panel.Width
End Sub

Sub ImageView2_Click
	StartActivity(url.OpenBrowser("https://t.me/hamitak.ir"))
End Sub

Sub Button3_Click
	StartActivity(Service_ac)
End Sub

Sub Button7_Click
	StartActivity(Service_ac)
End Sub

Sub Button4_Click
	StartActivity(url.OpenBrowser("https://t.me/hamitak.ir"))
End Sub

Sub btn_other_Click
	StartActivity(Profile_ac)
End Sub

Sub btn_profile_Click
	StartActivity(Sefareshat_ac)
End Sub

Sub btm_service_Click
	StartActivity(Service_ac)
End Sub

Sub ImageView7_Click

	Try
		Private i As Intent
		i.Initialize(i.ACTION_VIEW, "instagram://user?username="&"hamitak.ir")
		StartActivity(i)
	Catch
		StartActivity(url.OpenBrowser("https://instagram.com/hamitak.ir"))
	End Try

	
End Sub

Sub Label24_Click
	Dim lbl As Label
	lbl = Sender
	Dim cs1 As Cursor
	Dim lbl2 As Label
	Dim lbl3 As Label
	cs1 = Main.sql.ExecQuery("SELECT * FROM service_active WHERE servicename = '" & lbl.Text & "'")
	cs1.Position = 0
	lbl2.Initialize("")
	lbl2.Text = cs1.GetString("dbname")
	File.WriteString(File.DirInternal,"servicename.txt",lbl2.Text)
	lbl3.Initialize("")
	lbl3.Text = cs1.GetString("image")
	File.WriteString(File.DirInternal,"image.txt",lbl3.Text)
	Dim lbl4 As Label
	lbl4.Initialize("")
	lbl4.Text = cs1.GetString("servicename")
	File.WriteString(File.DirInternal,"servicename3.txt",lbl4.Text)
	StartActivity(Services_ac)
End Sub

Sub Button1_Click
	ToastMessageShow("بزودی این امکان فعال میشود",False)
End Sub

Sub Button2_Click
	StartActivity(Sefareshat_ac)
End Sub

Sub Button5_Click
	ToastMessageShow("بزودی این امکان فعال میشود",False)
End Sub

Sub Button6_Click
	StartActivity(Profile_ac)
End Sub