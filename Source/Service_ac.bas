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
	Private Panel4 As Panel
	Private btn_profile As Button
	Private Label3 As Label
	Private Label11 As Label
	Private ImageView4 As ImageView
	Dim cs As Cursor
	Private Label50 As Label
	Public lbl As Label
	Dim lbl2 As Label
	Dim lbl3 As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Service")
	
	Dim r As Reflector
	r.Target = scrollveiw_home
	r.RunMethod2("setVerticalScrollBarEnabled", False, "java.lang.boolean")
	cs = Main.sql.ExecQuery("SELECT * FROM service_active ORDER BY id")
	Dim top As Int = 10
	For i = 0 To cs.RowCount -1
		cs.Position=i
		Dim pnl As Panel
		pnl.Initialize("pnl")
		pnl.LoadLayout("Service_page_ScrollView")
		pnl.Tag = i
		scrollveiw_home.Panel.AddView(pnl,Panel4.Left+5dip,top,Panel4.Width,Panel4.Height+3dip)
		top = top + Panel4.Height + 7dip
		If pnl.tag = i Then
			Label11.Text = cs.GetString("servicename")
			Label50.Text = Label11.Text
			ImageView4.Bitmap = LoadBitmap(File.DirAssets,cs.GetString("image"))
		End If
		scrollveiw_home.Panel.Height = top + 3dip
	Next
	cs.Close
End Sub

Sub Activity_Resume
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	
End Sub

Sub Label1_Click
	StartActivity(Home)
End Sub

Sub Label3_Click
	StartActivity(Profile_ac)
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


Sub Label11_Click
	Dim cs1 As Cursor
	lbl = Sender
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